package com.js.questionme;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import com.parse.CountCallback;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseRelation;
import com.parse.ParseUser;

import de.passsy.holocircularprogressbar.HoloCircularProgressBar;


import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager.OnActivityResultListener;
import android.provider.MediaStore;
import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class Questionuser extends Activity {
    private static final int MenuShootImage = 0;
    Byte[] wassup;
	Button btnTackPic;
	Button library;
	Uri mImageUri;
    ImageView ivThumbnailPhoto;
    Bitmap picture;
    ParseObject use;
    static int TAKE_PICTURE = 1;
	ParseFile photoFile;
	String username;
	Bitmap dd;
	int questions;
	int tpoint;
	Bitmap photo;
	int wrong;

    private static int RESULT_LOAD_IMAGE = 1;
     
	float percent;
		private HoloCircularProgressBar mHoloCircularProgressBar;
		private ObjectAnimator mProgressBarAnimator;
		protected boolean mAnimationHasEnded = false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	setContentView(R.layout.activity_questionuser);// TODO Auto-generated method stub

	super.onCreate(savedInstanceState);
		Bundle extras = getIntent().getExtras();
		mHoloCircularProgressBar = (HoloCircularProgressBar) findViewById(R.id.holoCircular);
		wrong = ParseUser.getCurrentUser().getInt("TQA");
		if (extras != null) {
	    dd = (Bitmap) extras.get("picture");
		username = extras.getString("name");
	     tpoint = extras.getInt("pointe");
	}
		percent = (float) ((tpoint/wrong)*100); 
		if (mProgressBarAnimator != null) {
			mProgressBarAnimator.cancel();
		}
		animate(mHoloCircularProgressBar, null, percent/100, 1500);
		mHoloCircularProgressBar.setMarkerProgress(1f);
		mHoloCircularProgressBar.setProgressColor(Color.CYAN);

	
		TextView grade = (TextView)findViewById(R.id.gradetext);
		grade.setText(""+percent+"%");
		userrank();
		ivThumbnailPhoto = (ImageView) findViewById(R.id.imageViewpic);
		if(dd == null){
		
		}
		else{
			ivThumbnailPhoto.setImageBitmap(dd);
		}
		Button takepicture = (Button)findViewById(R.id.picture);
		Button logout = (Button) findViewById(R.id.facebook);
		TextView namer = (TextView) findViewById(R.id.usernamess);
		TextView scorer = (TextView) findViewById(R.id.survhigh);
	
		use = ParseUser.getCurrentUser();
		
		ParseQuery<ParseObject> query = ParseQuery.getQuery("User");
		library.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) { 
				
			
			Intent i = new Intent(
					Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
					 // TODO Auto-generated method stub
				
			}
		});
		takepicture.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				try {
					ByteArrayOutputStream stream = new ByteArrayOutputStream();
		            photo.compress(Bitmap.CompressFormat.PNG, 100, stream);
		            byte[] byteArray = stream.toByteArray();
		            ParseFile file = new ParseFile("userpic.png", byteArray);
		            use.put("userpic", file);
					use.save();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});

		namer.setText(""+username);
		scorer.setText(""+tpoint);
		ivThumbnailPhoto.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
			 Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
		            startActivityForResult(intent, 0);
		            	
				
			}
		});
		logout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ParseUser.logOut();
				ParseUser CurrentUser = ParseUser.getCurrentUser();
				Intent signin = new Intent(Questionuser.this ,Questionlogin.class);
				
				startActivity(signin);
			}
		
		});
		countsubq();
}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    super.onActivityResult(requestCode, resultCode, data);

	    if(requestCode == 0)
	    {
	        if(data != null)
	        {
	             photo = (Bitmap) data.getExtras().get("data");
	            photo = Bitmap.createScaledBitmap(photo, 160, 160, false);
	            ivThumbnailPhoto.setImageBitmap(photo);
	            
	            
	        }
	        else{
	        	Intent main = new Intent(this, Questionuser.class);
	        	startActivity(main);
	        }
	    }else if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
	            Uri selectedImage = data.getData();
	            String[] filePathColumn = { MediaStore.Images.Media.DATA };
	 
	            Cursor cursor = getContentResolver().query(selectedImage,
	                    filePathColumn, null, null, null);
	            cursor.moveToFirst();
	 
	            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
	            String picturePath = cursor.getString(columnIndex);
	            cursor.close();
	             
	           
	            ivThumbnailPhoto.setImageBitmap(BitmapFactory.decodeFile(picturePath));
	         
	        }
	     
	     
	    }
	
	
	     
	    
	public void userrank(){
		ProgressBar pbr = (ProgressBar) findViewById(R.id.progressBarrank);
		TextView scoresthe = (TextView) findViewById(R.id.scoretext);
		
		pbr.setMax(1000);
		pbr.setProgress(tpoint);
		if (pbr.getProgress()==pbr.getMax()){
			scoresthe.setText("Complete");}
			else{
				scoresthe.setText("Incomplete");
			
		}
	}
	@Override
	public void onBackPressed() {
		Intent back = new Intent(Questionuser.this, Questionstarter.class);
		startActivity(back);
	}
	
	public void countsubq(){
		
		ParseQuery<ParseObject> query = ParseQuery.getQuery("Questionss");
	
		try {
			query.find();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		ParseUser user = ParseUser.getCurrentUser();
		ParseRelation<ParseObject> relatq = user.getRelation("usqc");
		try {
		TextView cou = (TextView) findViewById(R.id.subquet);
		questions=	relatq.getQuery().count();
		cou.setText(""+questions);
		
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	private void animate(final HoloCircularProgressBar progressBar, final AnimatorListener listener) {
		final float progress = (float) (Math.random() * 2);
		int duration = 3000;
		animate(progressBar, listener, progress, duration);
	}

	private void animate(final HoloCircularProgressBar progressBar, final AnimatorListener listener,
			final float progress, final int duration) {

		mProgressBarAnimator = ObjectAnimator.ofFloat(progressBar, "progress", progress);
		mProgressBarAnimator.setDuration(duration);

		mProgressBarAnimator.addListener(new AnimatorListener() {

			@Override
			public void onAnimationCancel(final Animator animation) {
			}

			@Override
			public void onAnimationEnd(final Animator animation) {
				progressBar.setProgress(progress);
			}

			@Override
			public void onAnimationRepeat(final Animator animation) {
			}

			@Override
			public void onAnimationStart(final Animator animation) {
			}
		});
		if (listener != null) {
			mProgressBarAnimator.addListener(listener);
		}
		mProgressBarAnimator.reverse();
		mProgressBarAnimator.addUpdateListener(new AnimatorUpdateListener() {

			@Override
			public void onAnimationUpdate(final ValueAnimator animation) {
				progressBar.setProgress((Float) animation.getAnimatedValue());
			}
		});
		progressBar.setMarkerProgress(progress);
		mProgressBarAnimator.start();
	}
	
}
