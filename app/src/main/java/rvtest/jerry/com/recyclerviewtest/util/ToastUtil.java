package rvtest.jerry.com.recyclerviewtest.util;

import android.content.Context;
import android.widget.Toast;

/**
 * @author Jerry on 2018/6/22.
 * @function
 */
public class ToastUtil {
	public static void showShort(Context context,String text){
		Toast.makeText(context.getApplicationContext(),text,Toast.LENGTH_SHORT).show();
	}
}
