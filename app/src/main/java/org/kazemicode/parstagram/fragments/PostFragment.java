package org.kazemicode.parstagram.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import org.kazemicode.parstagram.Post;
import org.kazemicode.parstagram.PostAdapter;
import org.kazemicode.parstagram.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PostFragment extends Fragment {

    private RecyclerView rvPosts;
    private static final String TAG = "PostFragment";
    protected PostAdapter adapter;
    protected List<Post> allPosts;
    private SwipeRefreshLayout swipeContainer;

    public PostFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_post, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvPosts = view.findViewById(R.id.rvPosts);
        swipeContainer = (SwipeRefreshLayout) view.findViewById(R.id.swipeContainer);
        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to refresh the list here.
                // Make sure you call swipeContainer.setRefreshing(false)
                // once the network request has completed successfully.
                queryPosts();

            }
        });
        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);


        allPosts = new ArrayList<>();
        adapter = new PostAdapter(getContext(), allPosts);

        // set adapter and layout manager
        rvPosts.setAdapter(adapter);
        rvPosts.setLayoutManager(new LinearLayoutManager(getContext()));
        // get last 20 posts
        queryPosts();

    }


    protected void queryPosts() {
        int LIMIT = 20;
        // Specify which class to query
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        query.include(Post.KEY_USERNAME);
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
                adapter.clear();
                allPosts.addAll(posts);
                swipeContainer.setRefreshing(false);
            }
        });
    }
}