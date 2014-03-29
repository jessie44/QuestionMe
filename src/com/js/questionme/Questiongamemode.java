package com.js.questionme;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

public class Questiongamemode extends FragmentActivity {
	ViewPager viewpager= null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_questiongamemode);
		viewpager= (ViewPager) findViewById(R.id.pager);
		FragmentManager fragmentManager=getSupportFragmentManager();
		viewpager.setAdapter(new MyAdaptor(fragmentManager));
	}
	@Override
	public void onBackPressed() {

		Intent sag = new Intent(this, Questionstarter.class);
		startActivity(sag);
	}
	
}

class MyAdaptor extends FragmentPagerAdapter{

	public MyAdaptor(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Fragment getItem(int i) {
		Fragment fragment = null;
		if(i == 0){
			fragment = new fragmenta();
		}
		if(i == 1){
			fragment = new fragmentb();
		}
		if(i == 2){
			fragment = new fragmentc();
		}
		if(i == 3){
			fragment = new fragmentd();
		}
		return fragment;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 3;
	}
	
	@Override
	public CharSequence getPageTitle(int position) {
		if(position == 0){
			return "Question Me";
		}
		if(position == 1){
			return "Question Me Survival";
		}
		if(position == 2){
			return "Multiplayer";
		}
		if(position == 3){
			return "Homework Calender";
		}
		return null;
		
	}
	
}
	
