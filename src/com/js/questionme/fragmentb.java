package com.js.questionme;

import com.parse.CountCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;

public class fragmentb extends Fragment {

	int highsco;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.fragmentblay, container, false);
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		Button gtbButton = (Button) getActivity().findViewById(R.id.answer2);
		final Intent gtb = new Intent(getActivity(),Questionsurvival.class);
	
		gethighscore();
		gtbButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				gtb.putExtra("high", highsco);
				gtb.putExtra("gt", 3);
				startActivity(gtb);
				
			}
		});
		
	}
	
	public void gethighscore(){
		highsco = ParseUser.getCurrentUser().getInt("gamerscore");
	}
}
