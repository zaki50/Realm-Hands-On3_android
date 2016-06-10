package io.realm.handson3.twitter;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import twitter4j.TwitterFactory;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        TwitterAuthUtil.init(this);

        TwitterFactory.getSingleton().setOAuthConsumer(
                "<consumerKey>",
                "<consumerSecret>");

        Realm.setDefaultConfiguration(new RealmConfiguration.Builder(this).build());
    }
}
