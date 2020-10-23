package org.kazemicode.parstagram;

import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseClassName;
import com.parse.ParseUser;

@ParseClassName("Post")
public class Post extends ParseObject {
    // Ensure that your subclass has a public default constructor
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_IMAGE = "image";


    public Post() {
    }



    public ParseUser getUsername() {
        return getParseUser(KEY_USERNAME);
    }

    public String getDescription() {
        return getString(KEY_DESCRIPTION);
    }

    public ParseFile getImage() {
        return getParseFile(KEY_IMAGE);
    }

    public void setUsername(ParseUser username) {
        put(KEY_USERNAME, username);
    }

    public void setDescription(String description) {
        put(KEY_DESCRIPTION, description);
    }

    public void setImage(ParseFile image) {
        put(KEY_IMAGE, image);
    }


}