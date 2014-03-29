package com.js.questionme;
import java.util.ArrayList;
import java.util.List;

import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseImageView;
import com.parse.ParseObject;
import com.parse.ParseUser;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListViewAdaptercurrentgames extends ArrayAdapter<ParseObject> {
  private Context mContext;
  private List<ParseObject> mTasks;

  public  ListViewAdaptercurrentgames(Context context, List<ParseObject> objects) {
      super(context, R.layout.list_item, objects);
      
      this.mContext = context;
      
      this.mTasks = objects;
  }

  

public View getView(int position, View convertView, ViewGroup parent){
      if(convertView == null){
          LayoutInflater mLayoutInflater = LayoutInflater.from(mContext);
          convertView = mLayoutInflater.inflate(R.layout.cglist_item, null);
      }
       
      ParseObject use = mTasks.get(position);
     /* ParseFile fileObject = (ParseFile) user.get("userpic");
      fileObject.getDataInBackground(new GetDataCallback() {
    	  public void done(byte[] data,ParseException e) {
                      if (e == null) {
                          Log.d("test", "We've got data in data.");
                         
                          Bitmap bmp = BitmapFactory .decodeByteArray(data, 0,data.length);
                          image.setImageBitmap(bmp);

                        

                      } else {
                          Log.d("test",
                                  "There was a problem downloading the data.");
                      }
                  }
              });*/
     
    
	

			   TextView name = (TextView) convertView.findViewById(R.id.put1);
      TextView p2name = (TextView) convertView.findViewById(R.id.textView200);
      TextView round = (TextView) convertView.findViewById(R.id.roundText);
     round.setText(use.getString("round"));
      ParseUser p1 = use.getParseUser("player1");
      ParseUser p2 = use.getParseUser("player2");
     name.setText(p1.getUsername());
     if(p2 != null){
     p2name.setText(p2.getUsername());
     }else{
    p2name.setText("Waiting");
     }
      return convertView;
  }

}
