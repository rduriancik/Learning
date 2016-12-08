package afterChapterApps.exercise31_06;


import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by robert on 14.11.2016.
 */
public class Exercise31_06Server {
    private static Logger logger = Logger.getLogger("Exercise31_06");
    private static LinkedList<StudentAddress> addresses = new LinkedList<>();

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8000, 2);
            logger.log(Level.INFO, "Exercise31_06Server started at " + new Date());
            Socket socket1 = new Socket();
            socket1.close();
            Socket socket2 = new Socket();
            socket2.close();

            while (true) {
                if (socket1.isClosed()) {
                    socket1 = serverSocket.accept();
                    logger.log(Level.INFO, "Connected to a client 1 at " + new Date());
                    new Thread(new HandleClient(socket1, 1)).start();
                }

                if (socket2.isClosed()) {
                    socket2 = serverSocket.accept();
                    logger.log(Level.INFO, "Connected to a client 2 at " + new Date());
                    new Thread(new HandleClient(socket2, 2)).start();
                }
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    static class HandleClient implements Runnable {
        private Socket socket;
        private int id;
        private int index = 0;

        public HandleClient(Socket socket, int id) {
            this.id = id;
            this.socket = socket;
        }

        @Override
        public void run() {
            try (
                    ObjectOutputStream toClient = new ObjectOutputStream(socket.getOutputStream());
                    ObjectInputStream fromClient = new ObjectInputStream(socket.getInputStream())
            ) {
                while (true) {
                    int action = fromClient.readInt();
                    switch (action) {
                        case Exercise31_06Constants.ADD:
                            logger.log(Level.INFO, "Client " + id + " - ADD request received");

                            StudentAddress address = (StudentAddress) fromClient.readObject();

                            logger.log(Level.INFO, "Client " + id + " - received address: " + address);

                            addresses.add(address);
                            index = addresses.size() - 1;
                            break;
                        case Exercise31_06Constants.FIRST:
                            logger.log(Level.INFO, "Client " + id + " - FIRST request received");

                            index = 0;
                            toClient.writeObject(addresses.getFirst());

                            logger.log(Level.INFO, "Client " + id + " - first object sent");
                            break;
                        case Exercise31_06Constants.LAST:
                            logger.log(Level.INFO, "Client " + id + " - LAST request received");

                            index = addresses.size() - 1;
                            toClient.writeObject(addresses.getLast());

                            logger.log(Level.INFO, "Client " + id + " - last object sent");
                            break;
                        case Exercise31_06Constants.NEXT:
                            logger.log(Level.INFO, "Client " + id + " - NEXT request received");

                            index = (index + 1) % addresses.size();
                            toClient.writeObject(addresses.get(index));

                            logger.log(Level.INFO, "Client " + id + " - next object sent");
                            break;
                        case Exercise31_06Constants.PREVIOUS:
                            logger.log(Level.INFO, "Client " + id + " - PREVIOUS request received");

                            index = index == 0 ? addresses.size() - 1 : --index;
                            toClient.writeObject(addresses.get(index));

                            logger.log(Level.INFO, "Client " + id + " - previous object sent");
                            break;
                        default:
                            break;
                    }
                }
            } catch (EOFException ex) {
                try {
                    socket.close();
                    logger.log(Level.INFO, "Client " + id + " - disconnected");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
