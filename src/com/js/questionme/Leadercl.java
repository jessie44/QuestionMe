package com.js.questionme;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseImageView;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

/*
 * The FavoriteMealAdapter is an extension of ParseQueryAdapter
 * that has a custom layout for favorite meals, including a 
 * bigger preview image, the meal's rating, and a "favorite"
 * star. 
 */

public class Leadercl  extends ParseQueryAdapter<UserClass> {

	public Leadercl(Context context) {
		super(context,new ParseQueryAdapter.QueryFactory<UserClass>() {

			@Override
			public ParseQuery<UserClass> create() {
					ParseQuery query = new ParseQuery("Meal");
				
				query.orderByDescending("point");
				return query;// TODO Auto-generated method stub
			}
			
		});
		
			
			}
	
	

	

}
