package container;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import controller.Controller;

public class RequestHandler extends Thread {

    private static final Logger log = Logger.getLogger(RequestHandler.class.getName());

    private Socket connection;

    public RequestHandler(Socket connection) {
        this.connection = connection;
    }

    @Override
    public void run() {
        try (InputStream in = connection.getInputStream();
            OutputStream out = connection.getOutputStream();) {

            HttpRequest request = new HttpRequest(in);
            HttpResponse response = new HttpResponse(out);

            String path = getDefaultPath(request.getPath());

            Controller controller = HandlerMapping.getController(path);

            if(controller == null){
                response.forward(path);
                return;
            }

            controller.service(request, response);

        } catch (IOException e) {
            // TODO: handle exception
            log.log(Level.SEVERE, e.getMessage());
        }
    }

    private String getDefaultPath(String path) {
        if(path.equals("/")) {
            return "/index.html";
        }else {
            return path;
        }
    }

}
