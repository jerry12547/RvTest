package rvtest.jerry.com.recyclerviewtest.divider;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.view.View;

import rvtest.jerry.com.recyclerviewtest.R;

/**
 * @author Jerry on 2018/6/26.
 * @function 分组带标签的RV 分割线
 */
public class SectionDecoration extends RecyclerView.ItemDecoration {

	private DecorationCallback callback;
	private Paint mPaint;
	private TextPaint textPaint;
	private int topGap;


	public SectionDecoration(Context context,DecorationCallback callback){
		this.callback = callback;

		mPaint = new Paint();
		mPaint.setColor(context.getResources().getColor(R.color.colorPrimary));

		textPaint = new TextPaint();
		textPaint.setTypeface(Typeface.MONOSPACE);
		textPaint.setAntiAlias(true);
		textPaint.setTextSize(80);
		textPaint.setColor(Color.BLACK);
		textPaint.setTextAlign(Paint.Align.LEFT);

		topGap = context.getResources().getDimensionPixelSize(R.dimen.top_height);

	}


	@Override
	public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
		super.onDraw(c, parent, state);

		int left = parent.getPaddingLeft();
		int right = parent.getWidth() - parent.getPaddingRight();
		int childCount = parent.getChildCount();
		for (int i = 0; i < childCount ; i++) {
			View view = parent.getChildAt(i);
			int position = parent.getChildAdapterPosition(view);


			long groupId = callback.getGroupId(position);
			if (groupId <0){
				return;
			}

			String textLine = callback.getGroupFirstLetter(position).toUpperCase();
			if (position == 0 || isFirstInGroup(position) ){
				float top = view.getTop() - topGap;
				float bottom = view.getTop();
				c.drawRect(left,top,right,bottom,mPaint);
				c.drawText(textLine,left,bottom,textPaint);
			}
		}

	}

	@Override
	public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
		super.getItemOffsets(outRect, view, parent, state);

		int pos = parent.getChildAdapterPosition(view);
		long groupId = callback.getGroupId(pos);
		if (groupId <0){
			return;
		}
		if (pos == 0 || isFirstInGroup(pos)){
			outRect.top = topGap;
		}else {
			outRect.top = 0;
		}

	}

	/**
	 * 是否是一组数据的第一个
	 * @param position
	 * @return
	 */
	private boolean isFirstInGroup(int position){
		if (position == 0){
			return true;
		}else {
			long previousGroupId = callback.getGroupId(position-1);
			long groupId = callback.getGroupId(position);

			return previousGroupId !=groupId;
		}
	}

	public interface DecorationCallback{
		long getGroupId(int position);

		String getGroupFirstLetter(int position);

//		boolean  showSubItem(int position);
	}
}
