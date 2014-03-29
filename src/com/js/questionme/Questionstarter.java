package com.js.questionme;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;
import com.facebook.widget.ProfilePictureView;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.model.GraphUser;
import com.parse.FunctionCallback;
import com.parse.GetCallback;
import com.parse.GetDataCallback;
import com.parse.ParseCloud;
import com.parse.ParseFacebookUtils;
import com.parse.ParseFile;
import com.parse.ParseTwitterUtils;
import com.parse.ParseUser;
import com.parse.ParseQuery;
import com.parse.ParseObject;
import com.parse.PushService;

import android.net.ParseException;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Questionstarter extends Activity {
	RoundedImageView userProfilePictureView;
	ImageView iconImage;
	String usename;
	String id;
	ParseFile usesr;
	Bitmap bmp;
	Intent starts;
	
	
	public ProgressDialog progress;
    protected void onCreate(Bundle savedInstanceState) {
           // TODO Auto-generated method stub
           super.onCreate(savedInstanceState);
           setContentView(R.layout.activity_questionstarter);
           progress = new ProgressDialog(this);
           progress.show();

		
		Button news = (Button) findViewById(R.id.newgames);
		 
		userProfilePictureView = (RoundedImageView) findViewById(R.id.userProfilePicture);
		final int usepoints = ParseUser.getCurrentUser().getInt("point");
		
		TextView pointsto= (TextView) findViewById(R.id.scoretext);
		TextView nameers = (TextView) findViewById(R.id.namestart);
	
		 starts = new Intent(this, Questiongamemode.class);
		final Intent sour = new Intent(this, Questionuser.class);
		if(ParseTwitterUtils.isLinked(ParseUser.getCurrentUser())==true){
			usename = ParseTwitterUtils.getTwitter().getScreenName();
		}else if(ParseFacebookUtils.isLinked(ParseUser.getCurrentUser())== true){
			
			 makeMeRequest(); 
			 setpictureuser();
		}else{
			usename =ParseUser.getCurrentUser().getUsername();
			setpictureuser();
			
			
		}

		nameers.setText("Hello "+ usename); 
		pointsto.setText("Total Points: "+usepoints);
		progress.dismiss();
		Button lead = (Button) findViewById(R.id.button3rd);
		lead.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			Intent leads = new Intent (getApplicationContext(), Questionleader.class);
				startActivity(leads);
			}
		});
		news.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//TODO Auto-generated method stub
				starts.putExtra("name", usename);
				startActivity(starts);
			
			}
		});
		userProfilePictureView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
			sour.putExtra("picture", bmp);
			sour.putExtra("name", usename);
			sour.putExtra("pointe", usepoints);
			startActivity(sour);

			}
		});
		Button sub = (Button)findViewById(R.id.subaque);
		sub.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			Intent quest = new Intent(Questionstarter.this, Questionsubmit.class);
			wassup();
			//startActivity(quest);
		
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.questionstarter, menu);
		return true;
	}
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
	}
	
	@Override
	public void onBackPressed() {

		AlertDialog.Builder alertdialogs = new AlertDialog.Builder(this);
		alertdialogs.setTitle("Sure");
		alertdialogs
				.setMessage("Are you sure you want to exit.")
				.setCancelable(false)
				.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						moveTaskToBack(true);	
					
					
				} 
			})	
				.setNegativeButton("No", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
					}
				});
				
		AlertDialog alertDialog = alertdialogs.create();
		alertDialog.show();
	}
	public void setpictureuser(){
		ParseFile userpic = (ParseFile)ParseUser.getCurrentUser().get("userpic");
		userpic.getDataInBackground(new GetDataCallback() {
			
			@Override
			public void done(byte[] data, com.parse.ParseException e) {
		 if(data == null){
			 
		 }else{
			 	bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
			userProfilePictureView.setImageBitmap(bmp);
		 }
			
			}
		});
	}
	private void makeMeRequest() {
	    Request request = Request.newMeRequest(ParseFacebookUtils.getSession(),
	            new Request.GraphUserCallback() {
	                @Override
	                public void onCompleted(GraphUser user, Response response) {
	                    if (user != null) {
	                        // Create a JSON object to hold the profile info
	                      
	                           usename= user.getFirstName();
	                           Toast.makeText(getApplicationContext(), usename, Toast.LENGTH_LONG).show();
	                            id=user.getId();
	                            TextView nameers= (TextView) findViewById(R.id.namestart);
	                            ParseUser.getCurrentUser().setUsername(usename);
	                            
									ParseUser.getCurrentUser().saveEventually();
						
								nameers.setText("Hello "+ usename); 
	                       
	    
	                    } else if (response.getError() != null) {
	                    	Log.d("Questionstar",  "Error parsing returned user data.");
	                    }                 
	                }              
	            });
	    request.executeAsync();
	    
	}
	 public void wassup(){
		 HashMap<String, Object> params = new HashMap<String, Object>();
		 params.put("objects", "yViWr5yFmJ");
		 ParseCloud.callFunctionInBackground("turns", params, new FunctionCallback<String>() {
			@Override
			public void done(String o, com.parse.ParseException e) {
			Toast.makeText(getApplicationContext(), o, Toast.LENGTH_SHORT).show();
			}
		 });
	
		
		
	 }
		public void time(){
			
		
			CountDownTimer buttonsslide = new CountDownTimer(2000,1000) {
				
				@Override
				public void onTick(long time) {
					
				}
				
				@Override
				public void onFinish() {
					setContentView(R.layout.activity_questionstarter);
				}
			}.start();
						   
				        
					}
}
         
