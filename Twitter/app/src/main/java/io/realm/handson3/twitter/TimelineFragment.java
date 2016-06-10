package io.realm.handson3.twitter;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import io.realm.Realm;
import io.realm.RealmBaseAdapter;
import io.realm.RealmResults;
import io.realm.handson3.twitter.entity.Tweet;

public class TimelineFragment extends ListFragment {

    private Realm realm;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        realm = Realm.getDefaultInstance();

        final RealmResults<Tweet> tweets = realm.where(Tweet.class).findAll();
        final RealmBaseAdapter<Tweet> adapter = new RealmBaseAdapter<Tweet>(getContext(), tweets) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                final Tweet tweet = getItem(position);

                if (convertView == null) {
                    convertView = inflater.inflate(R.layout.listitem_tweet, parent, false);
                }

                // TODO 余裕があればViewHolderパターンを適用してください
                ((TextView) convertView.findViewById(R.id.screen_name)).setText(tweet.getScreenName());
                ((TextView) convertView.findViewById(R.id.text)).setText(tweet.getText());

                return convertView;
            }
        };

        setListAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ((RealmBaseAdapter<?>) getListAdapter()).updateData(null);
        realm.close();
        realm = null;
    }
}
