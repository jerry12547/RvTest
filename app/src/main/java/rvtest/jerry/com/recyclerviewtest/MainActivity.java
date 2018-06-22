package rvtest.jerry.com.recyclerviewtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import rvtest.jerry.com.recyclerviewtest.adapter.MainAdapter;
import rvtest.jerry.com.recyclerviewtest.beans.MainBean;

/**
 * 创建展示recyclerview的demo
 */
public class MainActivity extends AppCompatActivity {

	private RecyclerView mRecyclerView;
	private List<MainBean> mainData  = new ArrayList<>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mRecyclerView = findViewById(R.id.recycler_main);

		dealData();

		mRecyclerView.setAdapter(new MainAdapter(mainData));
		mRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
	}

	/**
	 * 添加数据
	 */
	private void dealData(){
		mainData.add(new MainBean(R.mipmap.ic_launcher,"基础recyclerView"));
		mainData.add(new MainBean(R.mipmap.ic_launcher,"瀑布流"));
		mainData.add(new MainBean(R.mipmap.ic_launcher,"多布局复杂recyclerView"));
		mainData.add(new MainBean(R.mipmap.ic_launcher,"时间选择空间recyclerView"));
	}
}
