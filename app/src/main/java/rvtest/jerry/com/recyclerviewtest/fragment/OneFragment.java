package rvtest.jerry.com.recyclerviewtest.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import rvtest.jerry.com.recyclerviewtest.BaseFragment;
import rvtest.jerry.com.recyclerviewtest.R;
import rvtest.jerry.com.recyclerviewtest.adapter.BasicAdapter;
import rvtest.jerry.com.recyclerviewtest.adapter.PlaceAdapter;
import rvtest.jerry.com.recyclerviewtest.divider.BasicDividerItem;

/**
 * @author Jerry on 2018/6/22.
 * @function  显示一种类型文字的listView
 */
public class OneFragment extends BaseFragment {
	private RecyclerView rvMain;
//	private List<Object> data ;
	private String[] hotelPeopleData  = {"10-50","51-100","101-150","151-300","301-500","501-100","1000以上"};
	private String[] hotelPlaceType = {"五星/顶级","五星/豪华","四星/高档","三星/舒适","经济型","度假村","会议中心"};

	private   int default_mode = 0;
	public static final int BASIC_RV = 0;
	public static final int WATERFALL_RV = 1;
	public static final int MIX_RV= 2;
	public static final int TIME_WIDGET_RV = 3;
	public static final int CHOOSE_PLACE_RV  = 4;

	private static final String COME_IN_MODE = "COME_IN_MODE";


	private BasicAdapter basicAdapter = null;

	public static OneFragment getInstance(int tag){
		Bundle bundle = new Bundle();
		bundle.putInt(COME_IN_MODE,tag);
		OneFragment fragment = new OneFragment();
		fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		if (default_mode != CHOOSE_PLACE_RV){
			inflater.inflate(R.menu.fragment_one_menu,menu);

			//换成下面的方法了
//			MenuItem addItem = menu.findItem(R.id.menu_add_item);
//			MenuItem delItem = menu.findItem(R.id.menu_del_item);
//
//			addItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
//				@Override
//				public boolean onMenuItemClick(MenuItem item) {
//					Log.i("jerryTest", "onMenuItemClick: "+item);
//					return false;
//				}
//			});
//
//			delItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
//				@Override
//				public boolean onMenuItemClick(MenuItem item) {
//					return false;
//				}
//			});

		}else {

		}


	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()){
			case R.id.menu_add_top_item:
				Log.i("jerryTest", "onOptionsItemSelected: "+item);
				addNewData(true);
				break;
			case R.id.menu_add_bottom_item:
				addNewData(false);
				break;
			case R.id.menu_del_top_item:
				removeData(true);
				break;
			case R.id.menu_del_bottom_item:
				removeData(false);
				break;
			default:
				break;
		}

		return false;
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
		int tag = getArguments().getInt(COME_IN_MODE);
		default_mode = tag;
		switch (tag){
			case BASIC_RV:
				basicAdapter = new BasicAdapter(getActivity(),addBasicData());
				rvMain.setAdapter(basicAdapter);
				rvMain.setLayoutManager(new LinearLayoutManager(getActivity()));
				rvMain.setItemAnimator(new DefaultItemAnimator());//默认动画
//				rvMain.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));//默认形式分割线
				rvMain.addItemDecoration(new BasicDividerItem(getActivity()));//自定义分割线
				setTitle("基础的RV");
				break;
			case WATERFALL_RV:
				break;
			case MIX_RV:
				break;
			case TIME_WIDGET_RV:
				break;
			case CHOOSE_PLACE_RV:
				rvMain.setAdapter(new PlaceAdapter(addPlaceData()));
//				rvMain.setLayoutManager(new LinearLayoutManager(getActivity()));
				rvMain.setLayoutManager(new GridLayoutManager(getActivity(),3));
				setTitle("选择地点RV");
				break;
				default:
					break;
		}

	}
	private List<String> addBasicData(){
		List<String> basicData = new ArrayList<>();
		for (int i = 0; i <40 ; i++) {
			basicData.add(i+"条数据");
		}
		return basicData;
	}
	/**
	 * 添加地点选择器的数据
	 * @return
	 */
	private List<String> addPlaceData(){
		return new ArrayList<>(Arrays.asList(hotelPeopleData));
	}


	/**
	 * 更换标题名称
	 * @param text
	 */
	private void setTitle(String text){
		getActivity().setTitle(text);
	}

	/**
	 * 插入一条数据 从头或者从尾部
	 * @param head
	 */
	private void addNewData(boolean head){
		basicAdapter.addData(head);
//		List<String> list;
//		if (head){
//			list = addBasicData();
//			list.add(1,"插入一条头部数据");
//			basicAdapter.notifyItemInserted(0);
//			basicAdapter.notifyItemRangeChanged(0,list.size());
//		}else {
//			addBasicData().add(addBasicData().size(),"插入一条尾部数据");
//		}

	}

	/**
	 * 删除一条数据 从头或者从尾部
	 */
	private void removeData(boolean head){
		basicAdapter.removeData(head);

	}
}
