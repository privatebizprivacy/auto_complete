package controller;

import container.HttpRequest;
import container.HttpResponse;

public interface Controller {

    public void service(HttpRequest req, HttpResponse res);
}
