package org.kazemicode.parstagram;

import com.parse.Parse;
import android.app.Application;


public class ParseApplication extends Application {

    // Initializes Parse SDK as soon as the application is created
    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("ZIKEyly1vST8i18erkgW3rxs3bi8wN08HdZapIAS")
                .clientKey("pStcBlGG3U7Wipit4rmY4bOf3Zj6dMKfdiNA8dDB")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}