package com.js.questionme;

import java.util.HashMap;

import com.parse.FunctionCallback;
import com.parse.ParseCloud;
import com.parse.ParseObject;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.net.ParseException;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class fragmentc extends Fragment {
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.fragmentclay, container, false);
		
	
	}
	
		@Override
		public void onActivityCreated(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onActivityCreated(savedInstanceState);
			Button start = (Button)getActivity().findViewById(R.id.random);
			Button currentG = (Button)getActivity().findViewById(R.id.currentgames);
		currentG.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent cl = new Intent(getActivity(), CurrentGameL.class);
				startActivity(cl);
			}
		});
			start.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
				
					wassup();

		}
			});
		}
			 public void wassup(){
					
					
					
				 ParseCloud.callFunctionInBackground("joinNewGame", new HashMap<String , Object>(), new FunctionCallback<ParseObject>() {
		 

					@Override
					
					public void done (ParseObject match, com.parse.ParseException e) {
						
				if(match == null){
					Toast.makeText(getActivity(), "No data", Toast.LENGTH_LONG).show();
						}else{
				
							Toast.makeText(getActivity(), match.getObjectId(), Toast.LENGTH_LONG).show();
							Intent multi = new Intent(getActivity(), Multiplg.class);
							String obj =	match.getObjectId();
							multi.putExtra("obj", obj);
							startActivity(multi);
						}
						
					}
				 });
		}
}