package com.js.questionme;


import com.parse.CountCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseRelation;
import com.parse.ParseUser;



import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.AvoidXfermode.Mode;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class Questionadd extends Activity {
	String mc1;
	String mc2;
	String mc3;
	String qs;
	String aa;
	int counts = 0;
	private ProgressDialog progress;

	 
	ParseUser user = ParseUser.getCurrentUser();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_questionadd);
		final EditText q = (EditText) findViewById(R.id.sq);
		final EditText mca = (EditText) findViewById(R.id.mca1);
	
		final EditText mcb = (EditText) findViewById(R.id.mcb2);
		final EditText mcc = (EditText) findViewById(R.id.mcc3);
		final RadioButton a1 = (RadioButton) findViewById(R.id.radioa);
		final RadioButton a2 = (RadioButton) findViewById(R.id.radiob);
		final RadioButton a3 = (RadioButton) findViewById(R.id.radioc);
		Button sub = (Button) findViewById(R.id.submit);
	
		sub.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
					
				ParseQuery<ParseObject> query = ParseQuery.getQuery("Questionss");
				
				try {
					counts = query.count();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				  
				
				
			qs = q.getText().toString();
			mc1=mca.getText().toString();
			mc2=mcb.getText().toString();
			mc3=mcc.getText().toString();
			
			if(a1.isChecked()){
				aa = mc1;
		}
			else if(a2.isChecked()){
				aa = mc2;
			}
			else{
				aa = mc3;
			}
			counts+=1;
			
		
			
				Questionclass wow = new Questionclass();
				wow.setQid(counts);
		wow.setMC1(mc1);
		wow.setMC2(mc2);
		wow.setMC3(mc3);
		wow.setMCA(qs);
		wow.setMA(aa);
		
		
		
		try{
			
			ParseUser user = ParseUser.getCurrentUser();
			
			user.save();
			
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			Log.e("hello", "msg"+e1);
		}
		mcb.setText("");
		mcc.setText("");
		mca.setText("");
		q.setText("");
	
			}
		});
	
	
		
	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.questionadd, menu);
		return true;
	}
	public void putinfo(){
		
	
		
	}
	
}
