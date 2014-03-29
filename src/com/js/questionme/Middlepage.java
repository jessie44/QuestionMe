package com.js.questionme;

import com.parse.ParseUser;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;

import android.content.DialogInterface;
import android.content.Intent;
import android.widget.Toast;

public class Middlepage extends Activity {

	@Override
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ConnectionDetector cd = new ConnectionDetector(getApplicationContext());
		Boolean isInternetPresent = cd.isConnectingToInternet();
		if(isInternetPresent == true){
			Toast.makeText(getApplicationContext(), "Connected", Toast.LENGTH_LONG).show();
			ParseUser currentUser = ParseUser.getCurrentUser();
		  if (currentUser.get("user") != null) {
		 Intent log = new Intent(this,Questionstarter.class);
			  startActivity(log);
		  } else {
		   Intent user = new Intent(this,Questionlogin.class);
		   startActivity(user);
		  }
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
		ConnectionDetector cd = new ConnectionDetector(getApplicationContext());
		Boolean isInternetPresent = cd.isConnectingToInternet();
		if(isInternetPresent == true){
			Toast.makeText(getApplicationContext(), "Connected", Toast.LENGTH_LONG).show();
			ParseUser currentUser = ParseUser.getCurrentUser();
		  if (currentUser.get("user") != null) {
		 Intent log = new Intent(this,Questionstarter.class);
			  startActivity(log);
		  } else {
		   Intent user = new Intent(this,Questionlogin.class);
		   startActivity(user);
		  }
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

}
