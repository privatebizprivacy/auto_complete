package controller;

import container.HttpRequest;
import container.HttpResponse;

public abstract class AbstractController implements Controller {

    @Override
    public void service(HttpRequest req, HttpResponse res) {

        if(req.getMethod().isPost()) {
            doPost(req, res);
        }else {
            doGet(req, res);
        }

    }

    protected void doGet(HttpRequest req, HttpResponse res) {
    }

    protected void doPost(HttpRequest req, HttpResponse res) {
    }

}
