package com.js.questionme;


import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class Questionsignup extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_questionsignup);
		final Intent go = new Intent(this,Questionstarter.class);
		Button newuser = (Button) findViewById(R.id.buttonnewuser);
		final TextView name = (TextView) findViewById(R.id.etname);
		final TextView email = (TextView) findViewById(R.id.email);
		final TextView password = (TextView) findViewById(R.id.etpass);
		final ProgressBar pb = (ProgressBar) findViewById(R.id.loginprogressBar1);
		pb.setVisibility(View.GONE);
		newuser.setOnClickListener(new OnClickListener() {
		
			@Override
			public void onClick(View v) {
				pb.setVisibility(View.VISIBLE);
				String username = name.getText().toString();
				String emails = email.getText().toString();
				String passwords = password.getText().toString();
				ParseUser user = new ParseUser();
				user.setUsername(username);
				user.setEmail(emails);
				user.setPassword(passwords);
														
				
				user.signUpInBackground(new SignUpCallback() {
					public void done(ParseException e) {
					pb.setVisibility(View.GONE);
						if (e == null) {
							startActivity(go);
						} else {
							
							Log.e("hell", e.toString());
						}
					}
				});
			}
		});
	}

	

}

