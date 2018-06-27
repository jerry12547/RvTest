package rvtest.jerry.com.recyclerviewtest.divider;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import rvtest.jerry.com.recyclerviewtest.R;

/**
 * @author Jerry on 2018/6/26.
 * @function 基础RV的分割线
 */
public class BasicDividerItem extends RecyclerView.ItemDecoration {

	private Paint dividerPaint;
	private int dividerHeight;

	public BasicDividerItem(Context context){
		dividerPaint = new Paint();
		dividerPaint.setColor(context.getResources().getColor(R.color.colorAccent));
		dividerHeight = 2;
	}
	@Override
	public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
//		super.onDraw(c, parent, state);
		int childCount =  parent.getChildCount();
		int left  = parent.getPaddingLeft()+30;
		int right = parent.getWidth() - parent.getPaddingRight()-30;
		Log.i("jerryTest","left: "+left+" right: "+right);
		for (int i = 0; i <childCount -1 ; i++) {
			View view = parent.getChildAt(i);
			float top = view.getBottom();
			float bottom = view.getBottom() +dividerHeight;
			c.drawRect(left,top,right, bottom,dividerPaint);
		}
	}

	@Override
	public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
		super.onDrawOver(c, parent, state);
	}

	@Override
	public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
		super.getItemOffsets(outRect, view, parent, state);
		outRect.bottom = dividerHeight;
	}

}
