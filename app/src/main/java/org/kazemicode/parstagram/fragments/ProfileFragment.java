package org.kazemicode.parstagram.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import org.kazemicode.parstagram.Post;
import org.kazemicode.parstagram.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends PostFragment {

    private static final String TAG = "ProfileFragment";


    @Override
    protected void queryPosts() {
        int LIMIT = 20;
        // Specify which class to query
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        query.include(Post.KEY_USERNAME);
        query.whereEqualTo(Post.KEY_USERNAME, ParseUser.getCurrentUser());
        // Limit to 20 posts
        query.setLimit(LIMIT);
        // ORDER BY MOST RECENT
        query.addDescendingOrder(Post.KEY_CREATED_AT);
        // Retrieve all posts
        query.findInBackground(new FindCallback<Post>() {
            public void done(List<Post> posts, ParseException e) {
                if (e == null) {
                    for(Post post : posts) {
                        Log.i(TAG, "Post: " + post.getDescription()  + " Author: " + post.getUsername().getUsername());
                    }
                } else {
                    // something went wrong
                    Log.e(TAG, "Issue with getting posts", e);
                }
                allPosts.addAll(posts);
                adapter.notifyDataSetChanged();
            }
        });
    }
}