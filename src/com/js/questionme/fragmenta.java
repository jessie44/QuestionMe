package com.js.questionme;


import java.io.ObjectOutputStream.PutField;

import com.parse.CountCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.support.v4.app.Fragment;

public class fragmenta extends Fragment {
	
	int qq =0;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		return inflater.inflate(R.layout.fragmentalay, container, false);
		
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		final Intent reg = new Intent(getActivity(), Questionmaingame.class);
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		Button button = (Button) getActivity().findViewById(R.id.regular);
		final RadioButton fiv = (RadioButton) getActivity().findViewById(R.id.five);
		final RadioButton ten = (RadioButton) getActivity().findViewById(R.id.ten);
		final RadioButton fift = (RadioButton) getActivity().findViewById(R.id.fifteen);
		final RadioButton twen = (RadioButton) getActivity().findViewById(R.id.twenty);
		final RadioButton twntf = (RadioButton) getActivity().findViewById(R.id.twentyfive);

		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if (fiv.isChecked()){
					qq = 5;
				}
			if (ten.isChecked()){
					qq = 10;
				}
			if (fift.isChecked()){
				qq= 15;
			}
			if (twen.isChecked()){
				qq= 20;
			}
			if (twntf.isChecked()){
				qq=25;
			}
				
				reg.putExtra("gt", 1);
				reg.putExtra("qc", qq);
				startActivity(reg);
			}
		});
		
	}
	

}
