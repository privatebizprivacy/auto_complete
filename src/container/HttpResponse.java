package container;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HttpResponse {

    private static final Logger log = Logger.getLogger(HttpResponse.class.getName());
    
    private DataOutputStream dos;

    private Map<String, String> headers = new HashMap<>();

    public HttpResponse(OutputStream out){
        dos = new DataOutputStream(out);
    }

    public void forward(String path){
        try {
            byte[] body = Files.readAllBytes(new File("./src/resources" + path).toPath());

            if(path.endsWith(".css")) {
                addHeader("Content-Type", "text/css");
            }else if(path.endsWith(".js")) {
                addHeader("Content-Type", "application/javascript");
            }else{
                addHeader("Content-Type", "text/html;charset=utf-8");
            }

            response200Header(body.length);
            responseBody(body);
        } catch (IOException e) {
            // TODO: handle exception
            log.log(Level.SEVERE, e.getMessage());
        }
    }
    public void forwardBody(String body) {
        try {
            byte[] contents = body.getBytes("UTF-8");
            addHeader("Content-Type", "text/html;chraset=utf-8");

            response200Header(contents.length);
            responseBody(contents);
        } catch (IOException e) {
            // TODO: handle exception
            log.log(Level.SEVERE, e.getMessage());
        }
    }

    public void addHeader(String key, String value) {
        this.headers.put(key, value);
    }

    public String getHeader(String header) {
        return this.headers.get(header);
    }

    private void response200Header(int contentLength) {
        try {
            dos.writeBytes("HTTP/1.1 200 OK \r\n");
            dos.writeBytes("Content-Type: " + getHeader("Content-Type")+"\r\n");
            dos.writeBytes("Content-Length: " + contentLength+"\r\n");
            processHeaders();
            dos.writeBytes("\r\n");
        } catch (IOException e) {
            // TODO: handle exception
            log.log(Level.SEVERE, e.getMessage());
        }
    }

    private void responseBody(byte[] body) {
        try {
            dos.write(body, 0, body.length);
            dos.writeBytes("\r\n");
            dos.flush();
        } catch (IOException e) {
            // TODO: handle exception
            log.log(Level.SEVERE, e.getMessage());
        }
    }

    private void processHeaders() {
        try {
            for(Entry<String, String> entry : headers.entrySet()) {
                dos.writeBytes(entry.getKey() + ": " + entry.getKey()+"\r\n");
            }
        } catch (IOException e) {
            // TODO: handle exception
            log.log(Level.SEVERE, e.getMessage());
        }
    }
}
