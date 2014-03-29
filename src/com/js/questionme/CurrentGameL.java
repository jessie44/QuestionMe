package com.js.questionme;

import java.util.ArrayList;
import java.util.List;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class CurrentGameL extends Activity {
	private ParseQueryAdapter<UserClass> mainAdapter;
	private Leadercl favoritesAdapter;
	List<ParseObject> object;
	String toob;
	private ListViewAdaptercurrentgames mAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_current_game_l);
ListView lets = (ListView)findViewById(R.id.cg);
		
	mAdapter = new ListViewAdaptercurrentgames(this, new ArrayList<ParseObject>());
		lets.setAdapter(mAdapter);
		updateData();
		lets.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
		            int position, long id) {
				ParseObject cd = object.get(position);
				 toob = cd.getObjectId();
				start();
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.current_game_l, menu);
		return true;
	}
	public void updateData(){
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Match");
        query.addDescendingOrder("createdAt");
       query.include("player1");
       query.include("player2");
        query.findInBackground(new FindCallback<ParseObject>() {
			
			@Override
			public void done(List<ParseObject> objects, ParseException e) {
				 if(objects != null){
					 object =  objects;
                    mAdapter.addAll(objects);
                     Toast.makeText(getApplicationContext(), "heol", Toast.LENGTH_LONG).show();	
             }
             else if (objects == null){
             Toast.makeText(getApplicationContext(), "heool", Toast.LENGTH_LONG).show();	
             }
				
			}
		});
				

    

}
	public void start(){
		 Toast.makeText(getApplicationContext(), toob , Toast.LENGTH_LONG).show();	
		Intent multi = new Intent(this, Multiplg.class);
		multi.putExtra("obj",toob );
		
		startActivity(multi);
	}


}
