import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;

public class ServerBotTrecker implements Runnable {
    Socket socket;

    public ServerBotTrecker(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (DataOutputStream out = new DataOutputStream(socket.getOutputStream());
             DataInputStream in = new DataInputStream(new BufferedInputStream(socket.getInputStream()))) {

            byte [] messageIn = new byte [in.available()];
            in.readFully(messageIn);

            MessageCipher messageCipher = new MessageCipher();

            try {
                MessageCipher.printByteArr( messageCipher.getDecrypt(messageIn));
            } catch (GeneralSecurityException e) {
                e.printStackTrace();
            }

            out.writeUTF("Server got the link");
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
