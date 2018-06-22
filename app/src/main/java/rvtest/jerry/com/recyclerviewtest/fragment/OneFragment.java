package rvtest.jerry.com.recyclerviewtest.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import rvtest.jerry.com.recyclerviewtest.BaseFragment;
import rvtest.jerry.com.recyclerviewtest.R;

/**
 * @author Jerry on 2018/6/22.
 * @function
 */
public class OneFragment extends BaseFragment {
	private RecyclerView rvMain;
	private List<Object> data ;


	public static OneFragment getInstance(){
		Bundle bundle = new Bundle();
		OneFragment fragment = new OneFragment();
		fragment.setArguments(bundle);
		return fragment;
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
		View view =  inflater.inflate(R.layout.fragment_one,container,false);
		rvMain = view.findViewById(R.id.fragment_one_rv_main);
		return rvMain;
	}


}
