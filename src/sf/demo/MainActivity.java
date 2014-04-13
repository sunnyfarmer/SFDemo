package sf.demo;

import java.util.ArrayList;
import java.util.List;

import sf.demo.activity.CurlView.BitmapCurlView;
import sf.demo.activity.CurlView.SFCurlViewActivity;
import sf.demo.activity.FlipView.ButtonFlipView;
import sf.demo.activity.FlipView.TextViewFlipView;
import sf.demo.model.ListNode;
import sf.demo.model.ListNode.ListNodeFactory;
import sf.demo.utils.SFGlobalValue;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {
	public static final String TAG = "MainActivity";

	private ListView lvDemoList = null;

	private List<ListNode> mNodeArray = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		this.initData();
		this.initView();
		this.setListener();
	}

	private static List<ListNode> allListNode() {
		List<ListNode> listNodeArray = new ArrayList<ListNode>();

		List<ListNode> tmpArray = null;
		ListNode childListNode = null;
		ListNode parentListNode = null;

		//FlipView
		tmpArray = new ArrayList<ListNode>();
		childListNode = ListNode.ListNodeFactory.produceListNode("TextViewFlipView", TextViewFlipView.class);
		tmpArray.add(childListNode);
		childListNode = ListNode.ListNodeFactory.produceListNode("ButtonFlipView", ButtonFlipView.class);
		tmpArray.add(childListNode);
		parentListNode = ListNodeFactory.produceListNode("FlipView", tmpArray);
		listNodeArray.add(parentListNode);

		//CurlView
		tmpArray = new ArrayList<ListNode>();
		childListNode = ListNode.ListNodeFactory.produceListNode("BitmapCurlView", BitmapCurlView.class);
		tmpArray.add(childListNode);
		childListNode = ListNode.ListNodeFactory.produceListNode("SFCurlView", SFCurlViewActivity.class);
		tmpArray.add(childListNode);
		parentListNode = ListNodeFactory.produceListNode("CurlView", tmpArray);
		listNodeArray.add(parentListNode);

		return listNodeArray;
	}

	private void initData() {
		Intent intent = this.getIntent();
		ListNode listNode = intent.getParcelableExtra(SFGlobalValue.EXTRA_LIST_NODE_ARRAY);

		if (listNode != null) {
			this.mNodeArray = listNode.getmChidListNode();
		} else {
			this.mNodeArray = allListNode();
		}
	}
	
	private void initView() {
		this.lvDemoList = (ListView) this.findViewById(R.id.lvDemoList);
		this.lvDemoList.setAdapter(new ArrayAdapter<ListNode>(this, android.R.layout.simple_list_item_1, this.mNodeArray));
	}

	private void setListener() {
		this.lvDemoList.setOnItemClickListener(new OnListNodeClickListener());
	}

	public class OnListNodeClickListener implements android.widget.AdapterView.OnItemClickListener{
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			ListNode listNode = mNodeArray.get(position);
			if (listNode != null) {
				Intent intent = null;
				if (null != listNode.getmActivity()) {
					intent = new Intent(MainActivity.this, listNode.getmActivity());
				} else {
					intent = new Intent(MainActivity.this, MainActivity.class);
					intent.putExtra(SFGlobalValue.EXTRA_LIST_NODE_ARRAY, listNode);
				}
				startActivity(intent);
			}
		}
	}
}
