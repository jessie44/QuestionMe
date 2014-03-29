package com.js.questionme;

import android.app.Application;
import android.content.Intent;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseFacebookUtils;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseTwitterUtils;
import com.parse.ParseUser;
import com.parse.PushService;



public class Questioninit extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		
		// Add your insitialization code here
		
	ParseObject.registerSubclass(Questionclass.class);
	ParseObject.registerSubclass(UserClass.class);
	
		Parse.initialize(this, "nLqwIqqD9lymOyhWC3aZkCA6Ltyl4qdHBzGFrA7k", "gnenxNrjaD6njdHbYdjGvFFUTbzEbj9Mequbp1TH");
		ParseFacebookUtils.initialize("1484723758421013");
		ParseTwitterUtils.initialize("LaldasC440N01NcwvfiryA", "U5kzgNQRe4UxtF9uBE0Vq9J5UnuL9eXwLI2Yew3gw9o");
		ParseInstallation.getCurrentInstallation().saveInBackground();
		PushService.setDefaultPushCallback(this, Questionstarter.class);
		ParseUser.enableAutomaticUser();
		ParseACL defaultACL = new ParseACL();
	    
		// If you would like all objects to be private by default, remove this line.
		defaultACL.setPublicReadAccess(true);
		
		
		
		
	}

}