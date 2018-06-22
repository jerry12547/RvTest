package rvtest.jerry.com.recyclerviewtest.fragment;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;

import rvtest.jerry.com.recyclerviewtest.R;

/**
 * @author Jerry on 2018/6/22.
 * @function
 */
public class OneActivity extends Activity {



	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_one);

		OneFragment fragment = OneFragment.getInstance();
		FragmentTransaction ft= getFragmentManager().beginTransaction();
		ft.add(R.id.activity_one_frame,fragment);
		ft.commit();

	}
}
