package com.js.questionme;



import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseFacebookUtils;
import com.parse.ParseObject;
import com.parse.ParseTwitterUtils;
import com.parse.ParseUser;

public class Questionlogin extends Activity {
	 Intent log;
	 String twitterscreen;
	 
	private String newname;
	protected ProgressDialog progressDialog;
	private ViewGroup hiddenPanel;
	private ViewGroup mainScreen;
	private boolean isPanelShown;
	ProgressDialog dialog;
	private ViewGroup root;

	int screenHeight = 0;
	/** Called when the activity is first created. */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		check(); 
		log = new Intent (this,Questionstarter.class);
		  
		setContentView(R.layout.activity_questionlogin);
		
		final ParseObject questions = new ParseObject("Questions");

		//Panel declare 
		final EditText email = (EditText) findViewById(R.id.emailmain);
		final EditText password = (EditText) findViewById(R.id.password);
		final Button login = (Button) findViewById(R.id.signintwice);
		 dialog = ProgressDialog.show(this, "", "", true);
        dialog.setCancelable(true);
        dialog.cancel();
		//finish Panel Declare
		mainScreen = (ViewGroup)findViewById(R.id.main_screen);
	    ViewTreeObserver vto = mainScreen.getViewTreeObserver(); 
	    vto.addOnGlobalLayoutListener(new OnGlobalLayoutListener() { 
	        @SuppressWarnings("deprecation")
			@Override 
	        public void onGlobalLayout() { 
	            screenHeight = mainScreen.getHeight();
	            mainScreen.getViewTreeObserver().removeGlobalOnLayoutListener(this); 
	        } 
	    }); 

	    root = (ViewGroup)findViewById(android.R.id.content);

	    hiddenPanel = (ViewGroup)getLayoutInflater().inflate(R.layout.slideuplay, root, false);
	    hiddenPanel.setVisibility(View.INVISIBLE);
	    root.addView(hiddenPanel);

	    isPanelShown = false;
	

		final Button signup = (Button) findViewById(R.id.signup);
		final Button logins = (Button) findViewById(R.id.loginbutt);
		final ImageButton face = (ImageButton) findViewById(R.id.facebook);
		final ImageButton twit = (ImageButton) findViewById(R.id.twitter);
		
		
		
		face.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			     progressDialog = ProgressDialog.show(Questionlogin.this, "",
	                        "Logging In with Facebook...", true);
				facelogin();
				
			}
		});
		
		signup.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
			Intent signup = new Intent(Questionlogin.this,Questionsignup .class);
				
			startActivity(signup);
			}
		});

		logins.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				slideUpDown(v);
			}
					
				});
				
		twit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			     progressDialog = ProgressDialog.show(Questionlogin.this, "",
	                        "Logging in with Twitter...", true);
			login();
			
				
			}
		});
			}
			
	{
		ParseAnalytics.trackAppOpened(getIntent());
	}
	public void login(){
			ParseTwitterUtils.logIn(this, new LogInCallback() {
					  @Override
					  public void done(ParseUser user, ParseException err) {
					    if (user == null) {
					      Log.d("MyApp", "Uh oh. The user cancelled the Twitter login.");
					      progressDialog.dismiss();
					    } else if (user.isNew()) {
					      Log.d("MyApp", "User signed up and logged in through Twitter!");
					      startActivity(log);
					    } else {
					      Log.d("MyApp", "User logged in through Twitter!");
					      startActivity(log);
					    }
					  }
					});// TODO Auto-generated method stub
	}
	public void facelogin(){
		List<String> permissions = Arrays.asList("basic_info", "user_about_me"
				);
		ParseFacebookUtils.logIn(Questionlogin.this, new LogInCallback() {
			  @Override
			  public void done(ParseUser user, ParseException err) {
			    if (user == null) {
			      Log.d("MyApp", "Uh oh. The user cancelled the Facebook login."+ err);
			     progressDialog.dismiss();
			    } else if (user.isNew()) {
			      Log.d("MyApp", "User signed up and logged in through Facebook!");
			       Intent start = new Intent(Questionlogin.this, Questionstarter.class);
			       startActivity(start);
			    } else {
			      Log.d("MyApp", "User logged in through Facebook!");
			      Intent start = new Intent(Questionlogin.this, Questionstarter.class);
			      startActivity(start);
			      
			    }
			  }
			
		            
		  }); 	
		if(isPanelShown = true ){
			
		
		}
	}
	public void slideUpDown(final View view) {
	    if(!isPanelShown) {
	        // Show the panel
	    	
	    	Animation bottomUp = AnimationUtils.loadAnimation(this,
	                R.anim.bottom_up);
	    	 mainScreen.startAnimation(bottomUp);
	    	
	        mainScreen.layout(mainScreen.getLeft(),
	                          mainScreen.getTop() - (screenHeight * 100/100), 
	                          mainScreen.getRight(),
	                          mainScreen.getBottom() - (screenHeight * 100/100));



	        hiddenPanel.layout(mainScreen.getLeft(), mainScreen.getBottom(), mainScreen.getRight(), screenHeight);
	        hiddenPanel.setVisibility(View.VISIBLE);
	     
	        setContentView(R.layout.slideuplay);
 
	        hiddenPanel.startAnimation(bottomUp);
	     	final EditText email = (EditText) findViewById(R.id.emailmain);
			final EditText password = (EditText) findViewById(R.id.password);
			final Button login = (Button) findViewById(R.id.signintwice);
			
			login.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
				     progressDialog = ProgressDialog.show(Questionlogin.this, "",
		                        "Logging In...", true);
					String ee =	email.getText().toString();
				String pp = password.getText().toString();
				ParseUser.logInInBackground(ee, pp, new LogInCallback() {
					  public void done(ParseUser user, ParseException e) {
					    if (user != null) {
					      startActivity(log);
					    } else {
					      Toast.makeText(Questionlogin.this ,"Login information is incorrect, please try again.", Toast.LENGTH_LONG).show();
					    }
					  }
					});
				}
			});

	        isPanelShown = true;
	    }
	    else {
	        isPanelShown = false;

	        // Hide the Panel
	        Animation bottomDown = AnimationUtils.loadAnimation(this,
	                R.anim.bottom_down);
	        bottomDown.setAnimationListener(new AnimationListener() {

	            @Override
	            public void onAnimationStart(Animation arg0) {
	                // TODO Auto-generated method stub

	            }

	            @Override
	            public void onAnimationRepeat(Animation arg0) {
	                // TODO Auto-generated method stub

	            }

	            @Override
	            public void onAnimationEnd(Animation arg0) {
	                isPanelShown = false;

	                mainScreen.layout(mainScreen.getLeft(),
	                          mainScreen.getTop() + (screenHeight * 100/100), 
	                          mainScreen.getRight(),
	                          mainScreen.getBottom() + (screenHeight * 100/100));

	                hiddenPanel.layout(mainScreen.getLeft(), mainScreen.getBottom(), mainScreen.getRight(), screenHeight);
	            }
	        });
	        mainScreen.startAnimation(bottomDown);
	        hiddenPanel.startAnimation(bottomDown);
	    }

	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	  super.onActivityResult(requestCode, resultCode, data);
	  ParseFacebookUtils.finishAuthentication(requestCode, resultCode, data);
	}
	public void check(){
		ConnectionDetector cd = new ConnectionDetector(getApplicationContext());
		Boolean isInternetPresent = cd.isConnectingToInternet();
		if(isInternetPresent == true){
			
		
		}else if (isInternetPresent == false){
			AlertDialog.Builder alertdialogs = new AlertDialog.Builder(this);
			alertdialogs.setTitle("No Connection");
			alertdialogs
					.setMessage("Please Connect to the internet and then Try Again.")
					.setCancelable(false)
					.setPositiveButton("OK",new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
				
						moveTaskToBack(true);
					} 
				})	;
				
			AlertDialog alertDialog = alertdialogs.create();
			alertDialog.show();
		}
		
	}
	@Override
	protected void onResume() {
		
		super.onResume();
		check();
	}
	
	}
	
