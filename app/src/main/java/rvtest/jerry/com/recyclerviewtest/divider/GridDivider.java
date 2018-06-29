package rvtest.jerry.com.recyclerviewtest.divider;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import rvtest.jerry.com.recyclerviewtest.R;

/**
 * @author Jerry on 2018/6/29.
 * @function
 */
public class GridDivider extends RecyclerView.ItemDecoration {

	private Drawable mDivider  = null;
	public GridDivider(Context context){
		mDivider = context.getResources().getDrawable(R.drawable.grid_item_divider);
	}

	@Override
	public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
		super.onDraw(c, parent, state);
		drawVertical(c,parent);
		drawHorizontal(c,parent);
	}

//	@Override
//	public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
//		super.getItemOffsets(outRect, view, parent, state);
//		outRect.set(0,0,mDivider.getIntrinsicWidth(),mDivider.getIntrinsicHeight());
//
//	}

	@Override
	public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {
		super.getItemOffsets(outRect, itemPosition, parent);
		int right = mDivider.getIntrinsicWidth();
		int bottom = mDivider.getIntrinsicHeight();
		if (isLastSpan(itemPosition, parent)) {
			right = 0;
		}

		if (isLastRow(itemPosition, parent)) {
			bottom = 0;
		}
		outRect.set(0, 0, right, bottom);



	}


	private  boolean isLastRow(int itemPosition, RecyclerView parent) {
		RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
		if (layoutManager instanceof GridLayoutManager) {
			int spanCount = ((GridLayoutManager) layoutManager).getSpanCount(); //列数

			int itemCount = parent.getAdapter().getItemCount();//item的个数
			Log.i("jerryTest", "isLastRow: spanCount"+spanCount  +"  itemCount"+itemCount);
			return (itemCount - itemPosition - 1) < spanCount;
		}
		return false;
	}

	private  boolean isLastSpan(int itemPosition, RecyclerView parent) {
		RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
		if (layoutManager instanceof GridLayoutManager) {
			int spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
			return (itemPosition + 1) % spanCount == 0;
		}
		return false;
	}


	private void drawVertical(Canvas c, RecyclerView rv){
		int childCount = rv.getChildCount();
		for (int i = 0; i < childCount; i++) {
			View child = rv.getChildAt(i);
			RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
			int left = child.getRight() + params.rightMargin;
			int top = child.getTop() - params.topMargin;
			int right = left + mDivider.getIntrinsicWidth();
			int bottom = child.getBottom() + params.bottomMargin;
			mDivider.setBounds(left, top, right, bottom);
			mDivider.draw(c);
		}


	}
	private void drawHorizontal(Canvas c,RecyclerView rv){
		int childCount = rv.getChildCount();
		for (int i = 0; i < childCount; i++) {
			View child = rv.getChildAt(i);
			RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
			int left = child.getLeft() - params.leftMargin;
			int top = child.getBottom() + params.bottomMargin;
			int right = child.getRight() + params.rightMargin;
			int bottom = top + mDivider.getMinimumHeight();
			mDivider.setBounds(left, top, right, bottom);
			mDivider.draw(c);
		}


	}
}
