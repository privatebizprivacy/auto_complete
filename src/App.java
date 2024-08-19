import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import container.RequestHandler;

public class App {

    private static final Logger log = Logger.getLogger(App.class.getName());
    private static final int DEFAULT_PORT = 8080;

    public static void main(String[] args) throws Exception {
        
        try (ServerSocket listenSocket = new ServerSocket(DEFAULT_PORT)) {
            log.log(Level.INFO, "Web Application Server Started {0} port.", String.valueOf(DEFAULT_PORT));

            Socket connection;
            while((connection = listenSocket.accept()) != null) {
                RequestHandler requestHandler = new RequestHandler(connection);
                requestHandler.start();
            }

        } catch (Exception e) {
            // TODO: handle exception
            log.log(Level.SEVERE, "WebServer Launch fails.");
        }

    }
}
