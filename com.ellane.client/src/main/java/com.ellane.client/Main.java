package com.ellane.client;


import com.ellane.app.EllaneApp;
import com.ellane.view.EllaneView;

import java.io.FileNotFoundException;
import java.io.IOException;


public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {

        EllaneApp app = new EllaneApp();
        app.initialize();
        //app.verifyLocation();
        //TODO: move logic to SRC

        //TODO: need to test all items...//main shouldn't throw exceptions
    }
}