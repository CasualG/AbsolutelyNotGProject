import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Floglor on 20.08.2017.
 */
public class TestNetworkingServer implements Runnable {

    Character Reven = new Character(13, 10, 14, 9, "Reven");
    static private Socket connection;
    static private ObjectOutputStream output;
    static private ObjectInputStream input;
    int port = 10800;

    private void CharInitialize() {
        CharWeapon Sabre = new CharWeapon("Sabre", true, 3, "SabreSkill");
        Reven.setWeapon(Sabre);
        Reven.AddArmor(new CharArmor("Armor Plate", "Torso", 6));
        System.out.println("Reven uses Armor Plate, Sabre+3 and Shield+1");
    }


    @Override
    public void run() {
        CharInitialize();
        while (true) {
            try {

                System.out.println("Waiting for connection");
                connection = new Socket(InetAddress.getByName("127.0.0.1"), 6112);
                System.out.println("Got him!");

                //in & out streams, we need to send and get some data
                output = new ObjectOutputStream(connection.getOutputStream());
                input = new ObjectInputStream(connection.getInputStream());

                //Exchanging characters
                sendData(Reven);
                Character enemyChar = (Character) getData();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void sendData(Object obj) {
        try {
            output.flush();
            output.writeObject(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Object getData() {
        try {
            Object obj = input.readObject();
            return obj;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


}


