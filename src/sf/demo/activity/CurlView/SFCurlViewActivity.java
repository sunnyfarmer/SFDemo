package sf.demo.activity.CurlView;

import sf.demo.R;
import sf.utils.log.SFLog;
import sf.view.SFPageView;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.AdapterView.OnItemLongClickListener;

public class SFCurlViewActivity extends Activity {
	public static final String TAG = "SFCurlViewActivity";

	private SFPageView mSFPageView = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);

	    this.mSFPageView = new SFPageView(this);
	    this.mSFPageView.setAdapter(new SFPageViewAdapter());
	    this.mSFPageView.setBackgroundColor(Color.BLUE);

	    this.setContentView(this.mSFPageView);
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	public class SFPageViewAdapter extends BaseAdapter {
		public static final String TAG = "SFPageViewAdapter";
		
		private int size = 10;

		@Override
		public int getCount() {
			return this.size;
		}

		@Override
		public Object getItem(int position) {
			return position;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			final int TEXTVIEW_ID = 1;
			final int IMAGEVIEW_ID = 2;
			
			LinearLayout view = null;
			TextView textview = null;
			ImageView imageview = null;
			if (convertView==null) {
				Context context = parent.getContext();
				view = new LinearLayout(context);
//				LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
				view.setBackgroundColor(Color.GREEN);
//				view.setLayoutParams(params);
				view.setOrientation(LinearLayout.VERTICAL);

				textview = new TextView(context);
				LayoutParams p1 = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
				textview.setLayoutParams(p1);
				textview.setTextSize(100.0f);
				textview.setGravity(Gravity.CENTER);
				textview.setTextColor(Color.BLACK);
				textview.setBackgroundColor(Color.WHITE);
				textview.setId(TEXTVIEW_ID);

				imageview = new ImageView(context);
				LayoutParams p2 = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
				imageview.setLayoutParams(p2);
				imageview.setBackgroundResource(R.drawable.ic_launcher);
				imageview.setId(IMAGEVIEW_ID);

				view.addView(textview);
				view.addView(imageview);
				convertView = view;
			} else {
				view = (LinearLayout)convertView;
				textview = (TextView) view.findViewById(TEXTVIEW_ID);
				imageview = (ImageView) view.findViewById(IMAGEVIEW_ID);
			}

			final SFPageView tmp = (SFPageView) parent;
			view.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
SFLog.d(TAG, String.format("before onClick: %d/%d", position, tmp.getCount()));
					int index = position>=tmp.getCount()-1 ? 0 : position+1;
SFLog.d(TAG, String.format("after onClick: %d/%d", index, tmp.getCount()));
					tmp.setSelection(index);
				}
			});

			textview.setText(Integer.toString(position));
			return view;
		}
	}
}
