package sf.demo.activity.FlipView;

import sf.demo.R;

import com.aphidmobile.flip.FlipViewController;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Toast;

public class ButtonFlipView extends Activity {
	public static final String TAG = "ButtonFlipView";
	
	FlipViewController mFlipViewController = null;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    
	    mFlipViewController = new FlipViewController(this);
	    this.mFlipViewController.setAdapter(new BaseAdapter() {
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				Button button = null;
				if (convertView==null) {
					final Context context = parent.getContext();
					button = new Button(context, null, android.R.attr.buttonStyleInset);
//					button.setBackgroundColor(Color.WHITE);
//					button.setTextColor(Color.BLACK);
//					button.setGravity(Gravity.CENTER);
					button.setTextSize(context.getResources().getDimension(R.dimen.text_size));

					button.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							Toast.makeText(context, v.getTag().toString(), Toast.LENGTH_SHORT).show();
						}
					});
				} else {
					button = (Button) convertView;
				}

				button.setText(Integer.toString(position));
				button.setTag(new Integer(position));
				
				return button;
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
	    
	    this.setContentView(mFlipViewController);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		this.mFlipViewController.onResume();
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		this.mFlipViewController.onPause();
	}

}
