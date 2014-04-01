package sf.demo.activity.CurlView;

import fi.harism.curl.CurlPage;
import fi.harism.curl.CurlView;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;

public class BitmapCurlView extends Activity {
	public static final String TAG = "BitmapCurlView";

	private CurlView mCurlView = null;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);

	    this.mCurlView = new CurlView(this);

	    this.setContentView(mCurlView);

	    this.init();
	}

	private void init() {
		this.mCurlView.setPageProvider(new PageProvider());
		mCurlView.setBackgroundColor(0xFF202830);
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		this.mCurlView.onPause();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		this.mCurlView.onResume();
	}

	private class PageProvider implements CurlView.PageProvider {
		public static final String TAG = "PageProvider";

		public static final int PAGE_SIZE = 26;

		@Override
		public int getPageCount() {
			return PAGE_SIZE;
		}

		private Bitmap loadBitmap(int width, int height, int index) {

			Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_4444);
			bitmap.eraseColor(0xFF00FFFF);
			Canvas canvas = new Canvas(bitmap);

			Paint p = new Paint();
			p.setColor(0xFF000000);
			p.setTextSize(200.0f);

			canvas.drawText(Integer.valueOf(index).toString(), 400, 400, p);
			canvas.drawRect(10, 10, 300, 300, p);
			
			return bitmap;
		}
		
		@Override
		public void updatePage(CurlPage page, int width, int height, int index) {
//			Bitmap front = this.loadBitmap(width, height, index-1);
			Bitmap cur = this.loadBitmap(width, height, index);
			Bitmap next = this.loadBitmap(width, height, index+1);
			page.setTexture(cur, CurlPage.SIDE_FRONT);
			page.setTexture(next, CurlPage.SIDE_BACK);
		}

	}
}
