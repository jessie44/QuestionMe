package com.js.questionme;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;
@ParseClassName("User")
public class UserClass extends ParseObject {
    public UserClass() {
        // A default constructor is required.
    }
 
    public String getTitle() {
        return getObjectId();
    }
    
    public int gethigh(){
    	return getInt("point");
    }
    public void setTitle(String title) {
        put("title", title);
    }
    public String getusername(){
    	return getString("username");
    }

 
   
   
    public ParseFile getPhotoFile() {
        return getParseFile("userpic");
    }
 
    public void setPhotoFile(ParseFile file) {
        put("photo", file);
    }
    
 
}

