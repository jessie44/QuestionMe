package com.js.questionme;
import java.util.ArrayList;
import java.util.List;

import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseImageView;
import com.parse.ParseObject;
import com.parse.ParseUser;


import de.passsy.holocircularprogressbar.HoloCircularProgressBar;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.Animator.AnimatorListener;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListViewAdapter extends ArrayAdapter<ParseUser> {
  private Context mContext;
  private List<ParseUser> mTasks;
  ParseUser user;
  float percent;
  Bitmap bmp;
  RoundedImageView userProfile;
  private HoloCircularProgressBar mHoloCircularProgressBar;
	private ObjectAnimator mProgressBarAnimator;
	protected boolean mAnimationHasEnded = false;
  public  ListViewAdapter(Context context, ArrayList<ParseUser> arrayList) {
      super(context, R.layout.list_item, arrayList);
      this.mContext = context;
      this.mTasks = arrayList;
      
  }

  

public View getView(int position, View convertView, ViewGroup parent){
      if(convertView == null){
          LayoutInflater mLayoutInflater = LayoutInflater.from(mContext);
          convertView = mLayoutInflater.inflate(R.layout.list_item, null);
      }
 

     
      user = mTasks.get(position);
    
     setpictureuser();
     userProfile = (RoundedImageView) convertView.findViewById(R.id.userProfilePicture);

			   TextView name = (TextView) convertView.findViewById(R.id.task_description);
      TextView topoint = (TextView) convertView.findViewById(R.id.pointtext);
          mHoloCircularProgressBar = (HoloCircularProgressBar) convertView.findViewById(R.id.holoCircular);
      if (mProgressBarAnimator != null) {
			mProgressBarAnimator.cancel();
		}

    	  
    	  if(user.getInt("QW") == 0){
    		  percent = 1f;
    	  }else if (user.getInt("TQA") == 0){
    		   percent = 0f;
    	  }else{
    		  percent = (user.getInt("TQA")/user.getInt("QW")*100);
    	  }
     
 
	  animate(mHoloCircularProgressBar, null,percent, 1000);
		mHoloCircularProgressBar.setMarkerProgress(.5f);
		mHoloCircularProgressBar.setProgressColor(Color.CYAN); 
 

		
   
     name.setText(user.getUsername());
     topoint.setText("Total Points:"+user.getInt("point"));
      return convertView;
  }

public void setpictureuser(){
	
	try {
		user.getParseFile("userpic").getData();
		
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

private void animate(final HoloCircularProgressBar progressBar, final AnimatorListener listener) {
	final float progress = (float) (Math.random() * 2);
	int duration = 3000;
	animate(progressBar, listener, progress, duration);
}

private void animate(final HoloCircularProgressBar progressBar, final AnimatorListener listener,
		final float progress, final int duration) {

	mProgressBarAnimator = ObjectAnimator.ofFloat(progressBar, "progress", progress);
	mProgressBarAnimator.setDuration(duration);

	mProgressBarAnimator.addListener(new AnimatorListener() {

		@Override
		public void onAnimationCancel(final Animator animation) {
		}

		@Override
		public void onAnimationEnd(final Animator animation) {
			progressBar.setProgress(progress);
		}

		@Override
		public void onAnimationRepeat(final Animator animation) {
		}

		@Override
		public void onAnimationStart(final Animator animation) {
		}
	});
	if (listener != null) {
		mProgressBarAnimator.addListener(listener);
	}
	mProgressBarAnimator.reverse();
	mProgressBarAnimator.addUpdateListener(new AnimatorUpdateListener() {

		@Override
		public void onAnimationUpdate(final ValueAnimator animation) {
			progressBar.setProgress((Float) animation.getAnimatedValue());
		}
	});
	progressBar.setMarkerProgress(progress);
	mProgressBarAnimator.start();
}

}


	


