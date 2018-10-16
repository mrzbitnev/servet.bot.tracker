import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws Exception {
        try (ServerSocket serverSocket = new ServerSocket(8675)) {
            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(new ServerBotTrecker(socket)).start();
            }
        }
    }
}
