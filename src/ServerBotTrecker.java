import java.io.*;
import java.net.Socket;

public class ServerBotTrecker implements Runnable {
    Socket socket;

    public ServerBotTrecker(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (DataOutputStream out = new DataOutputStream(socket.getOutputStream());
             DataInputStream in = new DataInputStream(new BufferedInputStream(socket.getInputStream()))) {

            String magnetLink = in.readUTF();
            System.out.println(magnetLink);

            out.writeUTF("Server got the link");
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
