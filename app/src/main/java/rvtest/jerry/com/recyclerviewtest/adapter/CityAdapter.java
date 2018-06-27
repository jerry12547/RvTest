package rvtest.jerry.com.recyclerviewtest.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import rvtest.jerry.com.recyclerviewtest.R;
import rvtest.jerry.com.recyclerviewtest.beans.CityBean;

/**
 * @author Jerry on 2018/6/26.
 * @function
 */
public class CityAdapter  extends  RecyclerView.Adapter<CityAdapter.ViewHolder>{

	private List<CityBean> data;

	public CityAdapter(List<CityBean> data){
		this.data = data;
	}
	@NonNull
	@Override
	public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_basic_rv,parent,false);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
		holder.tvMain.setText(data.get(position).getMemberName());
	}

	@Override
	public int getItemCount() {
		return data.size();
	}

	class ViewHolder extends RecyclerView.ViewHolder{
		TextView tvMain;
		public ViewHolder(View itemView) {
			super(itemView);
			tvMain = itemView.findViewById(R.id.item_basic_tv);
		}
	}
}
