package container;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HttpRequest {

    private static final Logger log = Logger.getLogger(HttpRequest.class.getName());

    private HttpMethod method;

    private String path;

    private Map<String, String> headers = new HashMap<>();

    private Map<String, String> parameters = new HashMap<>();

    public HttpRequest(InputStream in) {

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            String line = br.readLine();
            log.info(line);

            if(null == line) {
                log.info("It's not Http Protocol");
                return;
            }

            String[] tokens = line.split(" ");
            int index = tokens[1].indexOf('?');

            method = HttpMethod.valueOf(tokens[0]);
            path = index > -1 ? tokens[1].substring(0, index) : tokens[1];

            while (!line.equals("")) {

                line = br.readLine();
                index = line.indexOf(':');

                if(index > -1) {
                    headers.put(line.substring(0, index), line.substring(index));
                }

            }

        } catch (IOException e) {
            // TODO: handle exception
            log.log(Level.SEVERE, e.getMessage());
        }

        
    }

    public HttpMethod getMethod() {
        return this.method;
    }

    public String getPath() {
        return this.path;
    }

    public String getHeader(String header){
        return this.headers.get(header);
    }

    public String getParameter(String parameter) {
        return this.parameters.get(parameter);
    }
    
}
