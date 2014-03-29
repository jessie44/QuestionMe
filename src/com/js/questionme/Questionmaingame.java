                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           package com.js.questionme;


import java.io.ObjectOutputStream.PutField;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.CountCallback;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;



public class Questionmaingame extends Activity {
	String groop;
    String mc1;
    Random wow;
    String mc2;
    String mc3;
    String ma;
	Button one;
	boolean out = false;
	Button two;
	Button three;
	int score = 0;
	int gt = 3;
	int qc = 0;
	MediaPlayer mp;
	int qw = 0;
	MediaPlayer mc;
	MediaPlayer md;
	int totalq= 0;
	int count = 0;
	int pusht1= 4;
	int pusht3= 4;
	String ms = "";
	CountDownTimer cd;
	Animation animFadein;
	Animation fadeout;
	CountDownTimer fade;
	Animation move;
	Animation movein;
	Animation moveleftin;
	CountDownTimer buttonsslide;
	Animation animbounce;
	long suntilfinished;
	int counts = 0;
	Boolean clockstop=false;
	Intent finish;
	String obj;
	@Override	
	protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_questionmaingame);
	final int rand = 0 ;
	finish = new Intent(this,Questionfinish.class);
	Bundle extras = getIntent().getExtras();
	if (extras != null) {
	     totalq = extras.getInt("number");
	     obj = extras.getString("obj");
	     gt = extras.getInt("gt");
	     qc =extras.getInt("qc");
	}

	setContentView(R.layout.activity_questionmaingame);
	Button next = (Button) findViewById(R.id.nextq);
	final ParseUser users = ParseUser.getCurrentUser();
	 wow = new Random();
	final Button m1 = (Button) findViewById(R.id.facebook);
	final Button m2 = (Button) findViewById(R.id.answer2);
	mp = MediaPlayer.create(getApplicationContext(), R.raw.clockticking4); mp.start();
	final Button m3 = (Button) findViewById(R.id.answer3);
	final Button bu1 = (Button) findViewById(R.id.button1st);
	final Button bu2 = (Button) findViewById(R.id.button2nd);
	final Button bu3 = (Button) findViewById(R.id.button3rd);
	final ProgressBar pb = (ProgressBar) findViewById(R.id.progressBarrank);
	move= AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move);
	 animFadein = AnimationUtils.loadAnimation(getApplicationContext(),
             R.anim.fade_in);   
	 fadeout = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out);
	 
	 animbounce = AnimationUtils.loadAnimation(getApplicationContext(),
             R.anim.bounce); 
	 movein = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.movein);
	 moveleftin = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.moveleftin);
	 final Animation mf = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.moveleft);
	 bu1.setText(""+pusht1);
	m1.setVisibility(View.INVISIBLE);
	m2.setVisibility(View.INVISIBLE);
	m3.setVisibility(View.INVISIBLE);
	
	
	 
	final TextView tv = (TextView) findViewById(R.id.countview);
	
	pb.setMax(qc);
	
	
	
	rr(rand );
	bu1.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
		
		
		if(pusht1 == 1){
		bu1.setText("X");
		bu1.setClickable(false);	
		}else{
			
			pusht1 -=1;
			bu1.setText(""+pusht1);
			cd.cancel();
			clockstop = true;
			bu1.setClickable(false);
		}
		}
	});
	bu2.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			
			if(clockstop == true ){
				bu1.setVisibility(View.INVISIBLE);
			}
				if(out == false){
					bu1.setVisibility(View.VISIBLE);
					bu3.setVisibility(View.VISIBLE);
					
					
					bu1.startAnimation(mf);
					bu3.startAnimation(move);	
									
						out = true;
						timeisfade();
					
				}else{
				buttonsslide.cancel();
				
					bu1.startAnimation(moveleftin);
					
					
					bu3.startAnimation(movein);
					bu1.setVisibility(View.INVISIBLE);
					bu3.setVisibility(View.INVISIBLE);
					
					
					out = false;
					
				}
					
						
				}
			
						   
				        
					
		
	});
	bu3.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
		if(pusht3 == 1){
			bu3.setText("X");
			bu3.setClickable(false);
			
		}else{
			pusht3 -=1;
			bu3.setText(""+pusht3);
			take1();
			Toast.makeText(getApplicationContext(), "hello", Toast.LENGTH_LONG).show();
		}
		}
	});
	
	next.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			qw+=1;
			mp.stop();
			count+=1;
			cd.cancel();
			Vibrator vs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
	        vs.vibrate(300);
	      
	       
			rr(rand );
			
		}
		});
m1.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		mp.stop();
		cd.cancel();
		
		if(m1.getText().equals(ms) ){
			count +=1;
			score +=1;
			Toast.makeText(Questionmaingame.this, "Correct"+score, Toast.LENGTH_LONG).show();
			pb.setProgress(score);
			
			 mc = MediaPlayer.create(getApplicationContext(), R.raw.smallbellring01a);mc.start();
			
		}
		else{
			count += 1;
			qw+=1;
			Toast.makeText(Questionmaingame.this, "Wrong", Toast.LENGTH_LONG).show();
			 mc = MediaPlayer.create(getApplicationContext(), R.raw.failbuzzer01);mc.start();
		}
		rr(rand );
	}
});
	
			

m2.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			mp.stop();
			cd.cancel();
			
			if(m2.getText().equals(ms) ){
				count +=1;
				score+=1;
				Toast.makeText(Questionmaingame.this, "Correct"+ score, Toast.LENGTH_LONG).show();
				pb.setProgress(score);
				mc = MediaPlayer.create(getApplicationContext(), R.raw.smallbellring01a);mc.start();
				
				
			}
			else{
				count+=1;
				qw+=1;
				Toast.makeText(Questionmaingame.this, "Wrong", Toast.LENGTH_LONG).show();
				 mc = MediaPlayer.create(getApplicationContext(), R.raw.failbuzzer01);mc.start();
			}
			
			rr(rand );
		}
	});
		m3.setOnClickListener(new OnClickListener() {
	
			@Override
			public void onClick(View v) {
				mp.stop();
				cd.cancel();
				
				if(m3.getText().equals(ms)){
					count +=1;
					score+=1;
					Toast.makeText(Questionmaingame.this, "Correct"+score, Toast.LENGTH_SHORT).show();
					pb.setProgress(score);
					mc = MediaPlayer.create(getApplicationContext(), R.raw.smallbellring01a);mc.start();
					
							
					
					
				}
					else{
						count+=1;
						qw+=1;
						Toast.makeText(Questionmaingame.this, "Wrong", Toast.LENGTH_LONG).show();
						 mc = MediaPlayer.create(getApplicationContext(), R.raw.failbuzzer01);mc.start();
						
					}
				rr(rand );
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
		mp.stop();
		cd.cancel();
	    Intent sag = new Intent(this ,Questiongamemode.class);
	    startActivity(sag);
	}
	public static int randInt(int min, int max) {

	    // Usually this can be a field rather than a method variable
	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive	
	    int randomNum = rand.nextInt(max + 1);
	    	
	    return randomNum;
	}

	public void rr (final int rands){
		
		if(count>=qc){
			finish.putExtra("wrong", qw);
			finish.putExtra("counter", count);
			finish.putExtra("scores", score);
			finish.putExtra("types", gt);
			finish.putExtra("obj", obj);
			mp.stop();
			startActivity(finish);
		}
		
		Button first = (Button)findViewById(R.id.button1st);
		final TextView t2= (TextView) findViewById(R.id.timeText);
		
		first.setClickable(true);
		int rand;
		
		
		final TextView tv = (TextView) findViewById(R.id.congrat);
		
		final Button m1 = (Button) findViewById(R.id.facebook);
		final Button m2 = (Button) findViewById(R.id.answer2);
		final Button m3 = (Button) findViewById(R.id.answer3);
		m2.clearAnimation();
		m1.clearAnimation();
		m3.clearAnimation();
		 	m1.setVisibility(View.INVISIBLE);
		m2.setVisibility(View.INVISIBLE);
		m3.setVisibility(View.INVISIBLE);
		
	
		cd = new CountDownTimer(7000, 1000){
				
			int count = 0;	
				@Override
				public void onTick(long untilfinished) {
						suntilfinished = untilfinished/1000;
					   t2.setText("00:0"+untilfinished / 1000);
					  
	 
					 if(untilfinished/1000==4){
					
					  count+=1;
					  if(count < 2){
					   m1.startAnimation(animbounce);
					   m2.startAnimation(animbounce);
					   m3.startAnimation(animbounce);
					  }
					 }
					   
			        
				}
				
				@Override
				public void onFinish() {
					 mp.stop();
					 mc = MediaPlayer.create(getApplicationContext(), R.raw.failbuzzer01);mc.start();
					m1.setVisibility(View.INVISIBLE);
					m2.setVisibility(View.INVISIBLE);
					m3.setVisibility(View.INVISIBLE);
					Vibrator vs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
			        vs.vibrate(300);
			        count+=1;
					rr(0);
					
				}
			}.start();
		
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
		       mc1 = object.getString("MC1");
		       mc2 = object.getString("MC2");
		       mc3 = object.getString("MC3");
		        ma = object.getString("MA");
		    
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
		  
		 mp.start();
	}

		
		public void timeisfade(){
			final Button bu1 = (Button) findViewById(R.id.button1st);
			final Button bu2 = (Button) findViewById(R.id.button2nd);
			final Button bu3 = (Button) findViewById(R.id.button3rd);
			buttonsslide = new CountDownTimer(5000,1000) {
				
				@Override
				public void onTick(long time) {
					
				}
				
				@Override
				public void onFinish() {
					
					bu1.startAnimation(moveleftin);
					
					bu3.startAnimation(movein);
					bu1.setVisibility(View.INVISIBLE);
					bu3.setVisibility(View.INVISIBLE);
					out = false;
					
				}
			}.start();
						   
				        
					}
		public void take1(){
			
			int hello = wow.nextInt(2);
			
			final Button m1 = (Button) findViewById(R.id.facebook);
			final Button m2 = (Button) findViewById(R.id.answer2);
			final Button m3 = (Button) findViewById(R.id.answer3);
			if(ms.equals(mc1)){
					
					m2.clearAnimation();
					m2.setVisibility(View.INVISIBLE);
				
			}
			else if(ms.equals(mc2)){
				
					m3.clearAnimation();
					m3.setVisibility(View.INVISIBLE);
				
			}
				
			
			else if(ms.equals(m3)){
				
					m2.clearAnimation();
					m2.setVisibility(View.INVISIBLE);
				
			}	
			else{
				Toast.makeText(getApplicationContext(), "Nope", Toast.LENGTH_SHORT).show();
			}
			}
		}
		
		
	
	



