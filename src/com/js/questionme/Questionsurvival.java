package com.js.questionme;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class Questionsurvival extends Activity {
	MediaPlayer mc;
	MediaPlayer md;
	int score = 0;
	String ms = "";
	Intent finish;
	int gt =2;
	int totalq = 0;
	int highscores = 0;
	CountDownTimer cd;
	int num;
	Animation animFadein;
	CountDownTimer fade;
	Animation animbounce;
	long suntilfinished;
	int counts = 1;
	
	@Override	
	protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	final int rand = 0 ;
	
	setContentView(R.layout.activity_questionsurvival);
	Button next = (Button) findViewById(R.id.nextq);
	
	
	finish = new Intent(this, Questionfinish.class);
	final Button m1 = (Button) findViewById(R.id.facebook);
	final Button m2 = (Button) findViewById(R.id.answer2);
	final Button m3 = (Button) findViewById(R.id.answer3);
	final ProgressBar pb = (ProgressBar) findViewById(R.id.progressBarrank);
	Bundle extras = getIntent().getExtras();
	if (extras != null) {
	     totalq = extras.getInt("countss");
	     highscores = extras.getInt("high");
	}
	 animFadein = AnimationUtils.loadAnimation(getApplicationContext(),
             R.anim.fade_in);   
	 animbounce = AnimationUtils.loadAnimation(getApplicationContext(),
             R.anim.bounce); 
	m1.setVisibility(View.INVISIBLE);
	m2.setVisibility(View.INVISIBLE);
	m3.setVisibility(View.INVISIBLE);
	 
	final TextView tv = (TextView) findViewById(R.id.countview);
	
	pb.setMax(highscores);
	
	
	
	rr(rand );
	
	
	
	
m1.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		cd.cancel();
		if(m1.getText().equals(ms) ){
			
			score +=1;
			Toast.makeText(Questionsurvival.this, "Correct"+score, Toast.LENGTH_LONG).show();
			pb.setProgress(score);
			mc = MediaPlayer.create(getApplicationContext(), R.raw.smallbellring01a);mc.start();
			rr(rand );
		}
		else{
			 md = MediaPlayer.create(getApplicationContext(), R.raw.failbuzzer01);md.start();
			Toast.makeText(Questionsurvival.this, "Wrong", Toast.LENGTH_LONG).show();
			
			finish.putExtra("types", gt);
			finish.putExtra("suscore", score);
			startActivity(finish);
		}
		
	}
});
	
			

m2.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			cd.cancel();
			if(m2.getText().equals(ms) ){
				score+=1;
				Toast.makeText(Questionsurvival.this, "Correct"+ score, Toast.LENGTH_LONG).show();
				pb.setProgress(score);
			
				mc = MediaPlayer.create(getApplicationContext(), R.raw.smallbellring01a);mc.start();
				
				rr(rand );
			}
			else{
				 md = MediaPlayer.create(getApplicationContext(), R.raw.failbuzzer01);md.start();
				Toast.makeText(Questionsurvival.this, "Wrong", Toast.LENGTH_LONG).show();
				
				finish.putExtra("types", gt);
				finish.putExtra("suscore", score);
				startActivity(finish);
			}
			
			
		}
	});
		m3.setOnClickListener(new OnClickListener() {
	
			@Override
			public void onClick(View v) {
				cd.cancel();
				if(m3.getText().equals(ms)){
					
					score+=1;
					mc = MediaPlayer.create(getApplicationContext(), R.raw.smallbellring01a);mc.start();
					Toast.makeText(Questionsurvival.this, "Correct"+score, Toast.LENGTH_SHORT).show();
					pb.setProgress(score);
					
					
							
					
					
					
					rr(rand );
					
				}
					else{
						 md = MediaPlayer.create(getApplicationContext(), R.raw.failbuzzer01);md.start();
						Toast.makeText(Questionsurvival.this, "", Toast.LENGTH_LONG).show();
						finish.putExtra("types", gt);
						finish.putExtra("suscore", score);
						startActivity(finish);
						
					}
				
	}
});
		

    }
	@Override
	protected void onStop() {
	    super.onStop();  // Always call the superclass method first

	   cd.cancel();
	  
	}
	@Override
	public void onBackPressed()
	{
		cd.cancel();
	    Intent sag = new Intent(this ,Questiongamemode.class);
	    startActivity(sag);
	}
	public static int randInt(int min, int max) {

	    // Usually this can be a field rather than a method variable
	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive	
	    int randomNum = rand.nextInt((max - min) - 1) + min;
	    	
	    return randomNum;
	}

	public void rr (final int rands){
		final TextView tv = (TextView) findViewById(R.id.countview);
		
		tv.setText("Question "+counts);
		final TextView t2= (TextView) findViewById(R.id.timeText);
		counts+=1;
		
		int rand;
		
		rand = randInt(1, totalq);
		
		
		final Button m1 = (Button) findViewById(R.id.facebook);
		final Button m2 = (Button) findViewById(R.id.answer2);
		final Button m3 = (Button) findViewById(R.id.answer3);
		m2.clearAnimation();
		m1.clearAnimation();
		m3.clearAnimation();
		 	m1.setVisibility(View.INVISIBLE);
		m2.setVisibility(View.INVISIBLE);
		m3.setVisibility(View.INVISIBLE);
		
			 
		 
	
		cd = new CountDownTimer(5000, 1000){
				
			int count = 0;	
				@Override
				public void onTick(long untilfinished) {
						
					
					  
	 
					
					  
					 
					   
			        
				}
				
				@Override
				public void onFinish() {
					m1.setVisibility(View.INVISIBLE);
					m2.setVisibility(View.INVISIBLE);
					m3.setVisibility(View.INVISIBLE);
					Vibrator vs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
			        vs.vibrate(300);
			        finish.putExtra("types", gt);
					finish.putExtra("suscore", score);
			    	startActivity(finish);
			
				}
			};
			
			final ParseQuery<ParseObject> query = ParseQuery.getQuery("Questionss");
			
			query.setMaxCacheAge(TimeUnit.MINUTES.toMillis(4));
			query.setCachePolicy(ParseQuery.CachePolicy.CACHE_ELSE_NETWORK);
			query.findInBackground(new FindCallback<ParseObject>(){
			  
			@Override
			public void done(List<ParseObject> objects, ParseException e) {
				// TODO Auto-generated method stub
				int s = 0;
				try {
					s = query.count();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ParseObject object = objects.get(randInt(1, s));
				  String groop = object.getString("Question");
			      String mc1 = object.getString("MC1");
			      String mc2 = object.getString("MC2");
			      String mc3 = object.getString("MC3");
			      String ma = object.getString("MA");
			    
			       assign(groop,mc1,mc2,mc3,ma);
			       m1.setVisibility(View.INVISIBLE);
					m2.setVisibility(View.INVISIBLE);
					m3.setVisibility(View.INVISIBLE);
			}
			});
	       
	}

	

	public void assign(String groop,String mcf,String mcs, String mct, String mca){
final TextView tv = (TextView) findViewById(R.id.congrat);
		
		final Button m1 = (Button) findViewById(R.id.facebook);
		final Button m2 = (Button) findViewById(R.id.answer2);
		final Button m3 = (Button) findViewById(R.id.answer3);
		
			tv.setText(groop);
		      m1.setText(mcf);
		      m2.setText(mcs);
		      m3.setText(mct);
		  ms = mca;
		  m1.startAnimation(animbounce);
		   m2.startAnimation(animbounce);
		   m3.startAnimation(animbounce);
		cd.start();
	}
	
	
}
