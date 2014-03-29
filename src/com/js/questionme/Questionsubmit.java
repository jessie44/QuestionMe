package com.js.questionme;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseRelation;
import com.parse.ParseUser;

import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class Questionsubmit extends Activity {
int count;
String a1;
String a2;
String a3;
String Q;
String A;
ProgressDialog progressDialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_questionsubmit);
		Button suds = (Button) findViewById(R.id.substhebest);
		
		suds.setOnClickListener(new OnClickListener() {
			 
			@Override
			public void onClick(View v) {
				progressDialog = ProgressDialog.show(Questionsubmit.this, "",
		                        "Submitting Question...", true);
				submit();
				progressDialog.dismiss();
			}
			});
		
		
	
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.questionsubmit, menu);
		return true;
	}
	public void submit(){
		final EditText questions = (EditText) findViewById(R.id.QuestionM);
		final EditText mult1 = (EditText) findViewById(R.id.Question1);
		final EditText mult2 = (EditText) findViewById(R.id.Question2);
		final EditText mult3 = (EditText) findViewById(R.id.Question3);
		final RadioButton button1 = (RadioButton) findViewById(R.id.RB0);
		final RadioButton button2 = (RadioButton) findViewById(R.id.RB1);
		final RadioButton button3 = (RadioButton) findViewById(R.id.RB2);
	
		
				
			Q=	questions.getText().toString();
			a1= mult1.getText().toString();
			a2= mult2.getText().toString();
			a3= mult3.getText().toString();
			
			if(button1.isChecked()){
				A = a1;
			}
			else if(button2.isChecked()){
				A = a2;
			}
			else if(button3.isChecked()){
				A = a3;
			}
			

			Questionclass wow = new Questionclass();
			
	wow.setMC1(a1);
	wow.setMC2(a2);
	wow.setMC3(a3);
	wow.setMCA(Q);
	wow.setMA(A);
	try {
		wow.save();
	} catch (ParseException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	ParseUser user = ParseUser.getCurrentUser();
	ParseRelation<Questionclass> relation = user.getRelation("usqc");
	relation.add(wow);
	try {
		user.save();
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	questions.setText("");
	mult1.setText("");
	mult2.setText("");
	mult3.setText("");

	
			}
			
		
		
		
	
		
	}


