import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Floglor on 20.08.2017.
 */
public class TestNetworkingClient implements Runnable {

    static private Socket connection;
    static private ObjectOutputStream output;
    static private ObjectInputStream input;
    int port = 10800;

    private void Initialization() {
        while (true) {
            try {
                ServerSocket ss = new ServerSocket(port);
                System.out.println("Waiting for connection");
                Socket socket = ss.accept();
                System.out.println("Got him!");

                //in & out streams, we need to send and get some data
                InputStream instream = socket.getInputStream();
                OutputStream outstream = socket.getOutputStream();

                //Converting to other type (wut)
                DataInputStream in = new DataInputStream(instream);
                DataOutputStream out = new DataOutputStream(outstream);

                Object inputChar = null;

                while (true) {
                    //Тут нужно переслать объект!
                }


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void run() {
        try {
            connection = new Socket(InetAddress.getByName("127.0.0.1"), 6112);
            while (true) {
                output = new ObjectOutputStream(connection.getOutputStream());
                input = new ObjectInputStream(connection.getInputStream());
            }
        } catch (IOException ex) {

        }
    }

    private static void sendData(Object obj) {
        try {
            output.flush();
            output.writeObject(obj);
        } catch (IOException ex) {

        }
    }
}
