package controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

import container.HttpRequest;
import container.HttpResponse;
import model.SimpleJosnObject;
import model.SimpleJsonArray;

public class ResourceController extends AbstractController {

    private static final Logger log = Logger.getLogger(ResourceController.class.getName());

    @Override
    public void doPost(HttpRequest req, HttpResponse res) {
        // TODO Auto-generated method stub
        super.doPost(req, res);

        try (InputStream in = new FileInputStream("./src/resources"+req.getPath())) {
            BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));

            String line = br.readLine();
            String[] tokens = line.split("\\|");

            if(!tokens[0].equals("key") ||!tokens[1].equals("value")) {
                throw new IOException("Key/Value Error");
            }

            SimpleJosnObject josnObject = new SimpleJosnObject();
            SimpleJsonArray jsonArray = new SimpleJsonArray();

            while((line = br.readLine())!=null) {
                tokens = line.split("\\|");
                jsonArray.add(tokens[1]);
            }

            josnObject.put("data", jsonArray);

            res.addHeader("Content-Type", "application/json");
            res.forwardBody(josnObject.toString());
            
        } catch (IOException e) {
            // TODO: handle exception
            log.log(Level.SEVERE, e.getMessage());
        }

    }
    
}
