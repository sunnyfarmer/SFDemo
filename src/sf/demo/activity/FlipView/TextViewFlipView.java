package sf.demo.activity.FlipView;


import sf.demo.R;

import com.aphidmobile.flip.FlipViewController;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class TextViewFlipView extends Activity {
	public static final String TAG = "TextViewFlipView";

	protected FlipViewController flipView;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);

	    this.setTitle(TAG);

	    flipView = new FlipViewController(this, FlipViewController.HORIZONTAL);
	    
	    flipView.setAdapter(new BaseAdapter() {
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				TextView view = null;
				if (convertView==null) {
					final Context context = parent.getContext();
					view = new TextView(context);
					view.setTextSize(context.getResources().getDimension(R.dimen.text_size));
					view.setGravity(Gravity.CENTER);
					view.setTextColor(Color.BLACK);
					view.setBackgroundColor(Color.WHITE);
				} else {
					view = (TextView) convertView;
				}
				
				view.setText(String.valueOf(position));
				
				return view;
			}
			
			@Override
			public long getItemId(int position) {
				return position;
			}
			
			@Override
			public Object getItem(int position) {
				return position;
			}
			
			@Override
			public int getCount() {
				return 10;
			}
		});
	    
	    this.setContentView(flipView);
	}

	@Override
	protected void onResume() {
		super.onResume();
		flipView.onResume();
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		flipView.onPause();
	}
}
