package io.realm.handson3.twitter;

import android.app.Application;

import twitter4j.TwitterFactory;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        TwitterAuthUtil.init(this);

        TwitterFactory.getSingleton().setOAuthConsumer(
                "<consumerKey>",
                "<consumerSecret>");
    }
}
