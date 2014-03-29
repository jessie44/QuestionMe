package com.js.questionme;

import java.util.ArrayList;
import java.util.List;

import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQuery.CachePolicy;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class Questionleader extends Activity {
	private ParseQueryAdapter<UserClass> mainAdapter;
	private Leadercl favoritesAdapter;
	Bitmap bmp;
	private ListViewAdapter mAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_questionleader);
		ListView lets = (ListView)findViewById(R.id.task_list);
		
	mAdapter = new ListViewAdapter(this, new ArrayList<ParseUser>());
		lets.setAdapter(mAdapter);
		updateData();
		lets.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				
				
			}
		});
	}

	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.questionleader, menu);
		return true;
	} 
	public void updateData(){
        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.addDescendingOrder("point");
        query.findInBackground(new FindCallback<ParseUser>() {
		
                @Override
                public void done(List<ParseUser> tasks, ParseException error) {
                        if(tasks != null){
                              
                                mAdapter.addAll(tasks);
                                Toast.makeText(getApplicationContext(), "heol", Toast.LENGTH_LONG).show();	
                        }
                        else if (tasks == null){
                        Toast.makeText(getApplicationContext(), "heool", Toast.LENGTH_LONG).show();	
                        }
                }

				
        });
}
	

}
