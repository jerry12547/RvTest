package rvtest.jerry.com.recyclerviewtest.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
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
import rvtest.jerry.com.recyclerviewtest.adapter.CityAdapter;
import rvtest.jerry.com.recyclerviewtest.adapter.PlaceAdapter;
import rvtest.jerry.com.recyclerviewtest.beans.CityBean;
import rvtest.jerry.com.recyclerviewtest.divider.BasicDividerItem;
import rvtest.jerry.com.recyclerviewtest.divider.GridDivider;
import rvtest.jerry.com.recyclerviewtest.divider.SectionDecoration;
import rvtest.jerry.com.recyclerviewtest.interfaces.OnRvItemClickListener;

/**
 * @author Jerry on 2018/6/22.
 * @function  显示一种类型文字的listView
 */
public class OneFragment extends BaseFragment {
	private RecyclerView rvMain;
//	private List<Object> data ;
	private String[] hotelPeopleData  = {"10-50","51-100","101-150","151-300","301-500","501-100","1000以上"};
	private String[] hotelPlaceType = {"五星/顶级","五星/豪华","四星/高档","三星/舒适","经济型","度假村","会议中心","野人岛","总统套房"};
	private List<String> hotelData;

	private List<CityBean> cityBeanList;



	private  int default_mode = 0;
	public static final int BASIC_RV = 0;
	public static final int WATERFALL_RV = 1;
	public static final int MIX_RV= 2;
	public static final int CITY_WIDGET_RV = 3;
	public static final int CHOOSE_PLACE_RV  = 4;
	public static final int GRID_RV = 5;

	private static final String COME_IN_MODE = "COME_IN_MODE";


	private BasicAdapter basicAdapter = null;
	private PlaceAdapter placeAdapter = null;
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

		switch (default_mode){
			case CHOOSE_PLACE_RV:
				inflater.inflate(R.menu.fragment_place_menu,menu);
				break;
			case BASIC_RV:
				inflater.inflate(R.menu.fragment_common_menu,menu);
				break;
			case WATERFALL_RV:
				inflater.inflate(R.menu.fragment_common_menu,menu);
				break;
			case GRID_RV:
				inflater.inflate(R.menu.fragment_common_menu,menu);
				break;
		}


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


			case R.id.menu_place_choose_one:
				changeSinglePlaceData(true);
				break;
			case R.id.menu_place_choose_more:
				changeSinglePlaceData(false);
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
			case CITY_WIDGET_RV:
				rvMain.setAdapter(new CityAdapter(addCityData()));
				rvMain.setLayoutManager(new LinearLayoutManager(getActivity()));
				rvMain.addItemDecoration(new SectionDecoration(getActivity(), new SectionDecoration.DecorationCallback() {
					@Override
					public long getGroupId(int position) {
						return Character.toUpperCase(cityBeanList.get(position).getMemberName().charAt(0));
//						return cityBeanList.get(position).getMemberName();
					}

					@Override
					public String getGroupFirstLetter(int position) {
//						return cityBeanList.get(position).getMemberName().substring(0,1).toUpperCase();
						//网上做法是对英文通用，如果不想通过工具类提取中文首字母,还是手动添加字幕方便点
						//只要数据集是正确的，基本不会有问题
						return cityBeanList.get(position).getGroupName().toUpperCase();
					}
				}));
				setTitle("城市选择器");

				break;
			case CHOOSE_PLACE_RV:
				hotelData = new ArrayList<>();
				addDefaultPlaceData();
				placeAdapter = new PlaceAdapter(hotelData);
				placeAdapter.setSingleMode(false);
				placeAdapter.setOnRvItemClickListener(new OnRvItemClickListener() {
					@Override
					public void onItemClick(View view) {
						int position = rvMain.getChildAdapterPosition(view);
						//需要切换不同的item，因为当前只能选择一个
						Log.i("jerryTest", "onItemClick: "+position);
					}
				});
				rvMain.setAdapter(placeAdapter);

//				rvMain.setLayoutManager(new LinearLayoutManager(getActivity()));
				rvMain.setLayoutManager(new GridLayoutManager(getActivity(),3));
				setTitle("选择地点RV");
				break;
			case GRID_RV:
				basicAdapter = new BasicAdapter(getActivity(),addDoubleBasicData());
				rvMain.setAdapter(basicAdapter);
				rvMain.addItemDecoration(new GridDivider(getActivity()));
				rvMain.setLayoutManager(new GridLayoutManager(getActivity(),3));
				rvMain.setItemAnimator(new DefaultItemAnimator());//默认动画

				setTitle("Grid RV");
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
	private List<String> addDoubleBasicData(){
		List<String> data = new ArrayList<>();
		for (int i = 0; i <80 ; i++) {
			data.add(i+"条数据");
		}
		return data;
	}


	/**
	 * 添加地点数据
	 * @return
	 */
	private List<CityBean> addCityData(){
		//先填充假数据 后期更改
		cityBeanList= new ArrayList<>();
		cityBeanList.add(new CityBean("A","安徽",true));
		cityBeanList.add(new CityBean("A","安庆",false));
		cityBeanList.add(new CityBean("A","安吉拉大宝贝",false));
		cityBeanList.add(new CityBean("B","北京",true));
		cityBeanList.add(new CityBean("B","北京",false));
		cityBeanList.add(new CityBean("B","北京",false));
		cityBeanList.add(new CityBean("B","北京",false));
		cityBeanList.add(new CityBean("C","城市",true));
		cityBeanList.add(new CityBean("D","hello",true));
		cityBeanList.add(new CityBean("D","hello",false));
		cityBeanList.add(new CityBean("D","hello",false));
		cityBeanList.add(new CityBean("D","hello",false));
		cityBeanList.add(new CityBean("D","zzzhello",false));
		cityBeanList.add(new CityBean("D","hello",false));
		cityBeanList.add(new CityBean("E","world",true));
		return cityBeanList;
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

	/**
	 * 转换地点的形式
	 * @param isSingle
	 */
	private void changeSinglePlaceData(boolean isSingle){
		if (hotelData != null ) {
			hotelData.clear();
			placeAdapter.notifyDataSetChanged();
		}
		if (isSingle) {
			addSinglePlaceData();
		}else {
			addDefaultPlaceData();
		}
		placeAdapter.setSingleMode(isSingle);
		//刷新数据
		placeAdapter.notifyDataSetChanged();
	}

	/**
	 * 添加地点选择器的数据
	 * @return
	 */
	private void addSinglePlaceData(){
//		hotelData = new ArrayList<>(Arrays.asList(hotelPeopleData));
		hotelData.clear();
		hotelData.addAll(Arrays.asList(hotelPeopleData));
	}

	private void addDefaultPlaceData(){
		hotelData.clear();
//		hotelData = new ArrayList<>(Arrays.asList(hotelPlaceType));
		hotelData.addAll(Arrays.asList(hotelPlaceType));
	}

}
