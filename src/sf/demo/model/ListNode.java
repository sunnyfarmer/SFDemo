package sf.demo.model;

import java.util.ArrayList;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

public class ListNode implements Parcelable{
	public static final String TAG = "ListNode";

	private String mName = null;
	private Class<?> mActivity = null;
	private List<ListNode> mChidListNode = null;

	public static final Parcelable.Creator<ListNode> CREATOR = new Creator<ListNode>() {

		@Override
		public ListNode createFromParcel(Parcel source) {
			try {
				String name = source.readString();
				Class<?> activity = null;
				String activityName = source.readString();
				if (!activityName.equals("null")) {
					activity = Class.forName(activityName);
				}
				List<ListNode> childListNodes = new ArrayList<ListNode>();
				source.readTypedList(childListNodes, ListNode.CREATOR);
				if (activity!=null) {
					return new ListNode(name, activity);
				} else {
					return new ListNode(name, childListNodes);
				}
			} catch (ClassNotFoundException exception) {
				return null;
			}
		}

		@Override
		public ListNode[] newArray(int size) {
			return new ListNode[size];
		}
		
	};

	public ListNode(String name, List<ListNode> childListNode) {
		this.mName = name;
		this.mChidListNode = childListNode;
	}
	
	public ListNode(String name, Class<?> activity) {
		this.mName = name;
		this.mActivity = activity;
	}

	public List<ListNode> getmChidListNode() {
		return mChidListNode;
	}

	public Class<?> getmActivity() {
		return mActivity;
	}

	public String getmName() {
		return mName;
	}

	public static class ListNodeFactory {
		public static ListNode produceListNode(String name, Object object) {
			if (object instanceof Class<?>) {
				return new ListNode(name, (Class<?>)object);
			} else if (object instanceof List) {
				return new ListNode(name, (List<ListNode>)object);
			} else {
				throw new RuntimeException("未知的ListNode");
			}
		}
	}
	
	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.mName);
		dest.writeString(this.mActivity==null ? "null" : this.mActivity.getName());
		dest.writeTypedList(this.mChidListNode);
	}

	@Override
	public String toString() {
		return this.mName;
	}
}
