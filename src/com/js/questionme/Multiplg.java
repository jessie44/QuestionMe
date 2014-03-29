package com.js.questionme;

import java.util.HashMap;
import java.util.List;

import com.parse.CountCallback;
import com.parse.FindCallback;
import com.parse.FunctionCallback;
import com.parse.GetCallback;
import com.parse.ParseCloud;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Multiplg extends Activity {
 int counts;
 Button startround;
String obje;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_multiplg);
		Bundle extras = getIntent().getExtras();
		
		if (extras != null) {
	    obje = extras.getString("obj");
	}
		multp();
		
	 startround = (Button) findViewById(R.id.currentgames);
		startround.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			Intent start = new Intent(Multiplg.this, Questionmaingame.class);
			start.putExtra("gt", 3);
			start.putExtra("number", counts);
			start.putExtra("qc", 10);
			start.putExtra("obj", obje);
			startActivity(start);
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.multiplg, menu);
		return true;
	}
	
	
	public void multp(){
		ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Match");
        query.whereEqualTo("objectId", obje);
        query.include("player1");
        query.include("player2");
        query.getInBackground(obje, new GetCallback<ParseObject>() {

			@Override
			public void done(ParseObject object, ParseException e) {
			UserClass hello = new UserClass();
			ParseUser p2 = object.getParseUser("player2");
			ParseUser p1 = object.getParseUser("player1");
			 
			TextView player2 = (TextView) findViewById(R.id.player2textView2);
			TextView player = (TextView) findViewById(R.id.player1textView3);
			player.setText(p1.getUsername());
			if(p2 != null){
				player2.setText(p2.getUsername());
			}
			
			/*if(object.getString("turn").equals("player_2") ){
				TextView turn1 = (TextView) findViewById(R.id.textView9);
				turn1.setVisibility(View.INVISIBLE);
					if(ParseUser.getCurrentUser().getObjectId() == p1.getObjectId()){
						startround.setEnabled(false);
					}
			}else if(object.getString("turn").equals("player_1")){
				TextView turn2 = (TextView) findViewById(R.id.textView8);
				turn2.setVisibility(View.INVISIBLE);
		
				if(ParseUser.getCurrentUser().getObjectId() == p2.getObjectId()){
						startround.setEnabled(false);
					}else{
						Toast.makeText(getApplicationContext(), "no", Toast.LENGTH_LONG).show();
					}
				
			}*/
		
		
			
		}
		
	
        });
	}
	
}