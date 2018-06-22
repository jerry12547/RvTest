package rvtest.jerry.com.recyclerviewtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import rvtest.jerry.com.recyclerviewtest.adapter.MainAdapter;
import rvtest.jerry.com.recyclerviewtest.beans.MainBean;
import rvtest.jerry.com.recyclerviewtest.fragment.OneActivity;
import rvtest.jerry.com.recyclerviewtest.util.ToastUtil;

/**
 * 创建展示recyclerview的demo
 */
public class MainActivity extends AppCompatActivity {

	private RecyclerView mRecyclerView;
	private List<MainBean> mainData  = new ArrayList<>();
	private MainAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mRecyclerView = findViewById(R.id.recycler_main);

		dealData();
		adapter = new MainAdapter(mainData);
		adapter.setItemClickListener(new MainAdapter.OnRvItemClickListener() {
			@Override
			public void onItemClick(View view) {
				int position = mRecyclerView.getChildAdapterPosition(view);
				ToastUtil.showShort(MainActivity.this,"点击第"+position+"个");
				startIntent(position);
			}
		});
		mRecyclerView.setAdapter(adapter);
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
		mainData.add(new MainBean(R.mipmap.ic_launcher,"地点选择recyclerView"));
	}

	private void startIntent(int tag){
		Intent intent = new Intent(MainActivity.this,OneActivity.class);
		intent.putExtra("TAG",tag);
		startActivity(intent);
	}
}
