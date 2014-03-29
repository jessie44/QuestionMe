package com.js.questionme;

import java.util.GregorianCalendar;
import java.util.List;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.CalendarView.OnDateChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class fragmentd extends Fragment{
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.fragmentdlay, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
		final CalendarView v = (CalendarView) getActivity().findViewById(R.id.calendarView1);
		 v.setOnDateChangeListener( new CalendarView.OnDateChangeListener() {
		    private GregorianCalendar calendar;
 TextView ev= (TextView) getActivity().findViewById(R.id.calenderdate);
			public void onSelectedDayChange(CalendarView view, int month, int dayOfMonth, int year) {
		
		     
		       
			}
		 

});
	}
}
	
