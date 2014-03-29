package com.js.questionme;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseTwitterUtils;
import com.parse.ParseUser;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

public class Questionfinish extends Activity {
	Animation animbounce;
	Intent main;
	int gamet = 0;
	String usename;
	CountDownTimer cd ;
	int wrong;
	String obj;
	int score;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_questionfinish);
		main = new Intent(this, Questiongamemode.class);



		int gamet2 = 0;
		int count = 0;
		int wwows = 0;
		final TextView out = (TextView) findViewById(R.id.out);

		final TextView secsc = (TextView) findViewById(R.id.secondsco);
		TextView congrat = (TextView) findViewById(R.id.congrat);
		TextView mid = (TextView) findViewById(R.id.middle);
		ParseUser fed = ParseUser.getCurrentUser();
		if(ParseTwitterUtils.isLinked(ParseUser.getCurrentUser())==true){
			usename = ParseTwitterUtils.getTwitter().getScreenName();
		}else{
			usename =ParseUser.getCurrentUser().getUsername();
		}

		String intent = getIntent().toString();
		animbounce = AnimationUtils.loadAnimation(getApplicationContext(),
				R.anim.bounce);
		out.setVisibility(View.INVISIBLE);
		secsc.setVisibility(View.INVISIBLE);

		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			gamet = extras.getInt("types");
			if (gamet == 1) {
				score = extras.getInt("scores");
				count = extras.getInt("counter");
				wrong = extras.getInt("wrong");
			}
			else if (gamet == 2) {
				wwows = extras.getInt("suscore");

			}else if (gamet == 3) {
				score = extras.getInt("scores");
				count = extras.getInt("counter");
				obj = extras.getString("obj");
			}

		}
		if (gamet == 1 ) {
			congrat.setText("Good Job " + usename);

			out.setText("" + score);
			secsc.setText("" + count);
			int hsc = ParseUser.getCurrentUser().getInt("highScore");
			if (score > hsc) {
				
				fed.put("highScore", score);
				fed.increment("point", score);
				fed.increment("QW", wrong);
				fed.increment("TGPlayed");
				fed.saveInBackground();
			} else {
				fed.increment("point", score);
				fed.increment("QW", wrong);
				fed.increment("TGPlayed");
				fed.saveInBackground();
			}

		}
		else if (gamet == 2) {
			congrat.setText("Good Job " + usename);
			
			out.setText("" + wwows);

			secsc.setVisibility(View.GONE);
			mid.setVisibility(View.GONE);

			int Ghsc = fed.getInt("gamerscore");

			if (wwows > Ghsc) {
				fed.put("gamerscore", wwows);
				fed.increment("TGPlayed");
				fed.saveInBackground();

			} else {
				fed.increment("TGPlayed");
				fed.saveInBackground();
			}
		}
		else if (gamet == 3) {
			congrat.setText("Good Job " + usename);

			out.setText("" + score);
			secsc.setText("" + count);
			
				ParseQuery<ParseObject> gamem = new ParseQuery<ParseObject>("Match");
				gamem.include("player1");
				gamem.include("player2");
				gamem.getInBackground(obj, new GetCallback<ParseObject>() {
					
					@Override
					public void done(ParseObject object, ParseException e) {
						// TODO Auto-generated method stub
					ParseUser d = object.getParseUser("player1");
					ParseUser es = object.getParseUser("player2");
					if(object.getString("round").equals("Round 1")){
						if(ParseUser.getCurrentUser().getObjectId().equals(d.getObjectId())){
							object.put("Round1", score);
							Toast.makeText(getApplicationContext(), "No Save", Toast.LENGTH_LONG).show();
						}else if(ParseUser.getCurrentUser().getObjectId().equals(es.getObjectId())){
							object.put("Round1p2", score);
							object.saveInBackground();
							Toast.makeText(getApplicationContext(), "No Save2", Toast.LENGTH_LONG).show();
						}
					}else if(object.getString("round").equals("Round 2") ){
						if(ParseUser.getCurrentUser().getObjectId().equals(d.getObjectId())){
							object.put("Round2", score);
							Toast.makeText(getApplicationContext(), "No Save3", Toast.LENGTH_LONG).show();
						}else if(ParseUser.getCurrentUser().getObjectId().equals(es.getObjectId())){
							object.put("Round2p2", score);
							Toast.makeText(getApplicationContext(), "No Save5", Toast.LENGTH_LONG).show();
						}else{
							Toast.makeText(getApplicationContext(), "No Save4", Toast.LENGTH_LONG).show();
						}
						
					}
					
				} 
		
				});		
			
			
		}
		
		cd = new CountDownTimer(8000, 1000) {
			int conts = 0;

			@Override
			public void onTick(long millisUntilFinished) {

				if (millisUntilFinished / 1000 == 5) { // TODO Auto-generated
														// method
					if (gamet == 1) {
						out.startAnimation(animbounce);
						secsc.startAnimation(animbounce);
					}
					if (gamet == 2) {
						out.startAnimation(animbounce);
					}	
					if (gamet == 3) {
						out.startAnimation(animbounce);
						secsc.startAnimation(animbounce);
					}

				}
				
				
			}

			@Override
			public void onFinish() {
				// TODO Auto-generated method stub
				startActivity(main);
			}
		}.start();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.questionfinish, menu);
		return true;
	}

	@Override
	public void onBackPressed() {
		cd.cancel();
		Intent sag = new Intent(this, Questiongamemode.class);
		startActivity(sag);
	}
}



