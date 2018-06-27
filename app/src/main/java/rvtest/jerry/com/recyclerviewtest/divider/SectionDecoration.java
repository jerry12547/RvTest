package rvtest.jerry.com.recyclerviewtest.divider;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

/**
 * @author Jerry on 2018/6/26.
 * @function 分组带标签的RV 分割线
 */
public class SectionDecoration extends RecyclerView.ItemDecoration {

	private DecorationCallback callback;
	public SectionDecoration(Context context,DecorationCallback callback){
		this.callback = callback;
	}


	public interface DecorationCallback{
		long getGroupId(int position);

		String getGroupFirstLine(int position);
	}
}
