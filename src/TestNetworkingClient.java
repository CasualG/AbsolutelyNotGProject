import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by Floglor on 20.08.2017.
 */
public class TestNetworkingClient implements Runnable {

    static private Socket connection;
    static private ObjectOutputStream output;
    static private ObjectInputStream input;

    @Override
    public void run() {
        try {
            connection = new Socket(InetAddress.getByName("127.0.0.1"), 6112);
            while (true) {
                output = new ObjectOutputStream(connection.getOutputStream());
                input = new ObjectInputStream(connection.getInputStream());

            }
        }
        catch (IOException ex) {

        }
    }

    private static void sendData(Object obj) {
        try{
            output.flush();
            output.writeObject(obj);
        } catch (IOException ex) {

        }
    }
}
