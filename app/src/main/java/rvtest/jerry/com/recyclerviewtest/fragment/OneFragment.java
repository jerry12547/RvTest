package rvtest.jerry.com.recyclerviewtest.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import rvtest.jerry.com.recyclerviewtest.BaseFragment;
import rvtest.jerry.com.recyclerviewtest.R;
import rvtest.jerry.com.recyclerviewtest.adapter.OneAdapter;

/**
 * @author Jerry on 2018/6/22.
 * @function  显示一种类型文字的listView
 */
public class OneFragment extends BaseFragment {
	private RecyclerView rvMain;
	private List<Object> data ;
	private String[] hotelPeopleData  = {"10-50","51-100","101-150","151-300","301-500","501-100","1000以上"};
	private String[] hotelPlaceType = {"五星/顶级","五星/豪华","四星/高档","三星/舒适","经济型","度假村","会议中心"};


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
		return view;
	}

	@Override
	public void onStart() {
		super.onStart();

		rvMain.setAdapter(new OneAdapter(addData()));
	}

	private List<String> addData(){
		return new ArrayList<>(Arrays.asList(hotelPeopleData));
	}
}
