package rvtest.jerry.com.recyclerviewtest.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import rvtest.jerry.com.recyclerviewtest.R;
import rvtest.jerry.com.recyclerviewtest.beans.MainBean;
import rvtest.jerry.com.recyclerviewtest.interfaces.OnRvItemClickListener;

/**
 * @author Jerry on 2018/6/22.
 * @function
 */
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

	private List<MainBean> data;
	public MainAdapter(List<MainBean> data){
		this.data = data;
	}

//	public  interface OnRvItemClickListener{
//		void onItemClick(View view);
//	}
	public void setItemClickListener(OnRvItemClickListener onItemClickListener){
		this.onItemClickListener = onItemClickListener;
	}
	private   OnRvItemClickListener onItemClickListener;

	@NonNull
	@Override
	public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_rv,parent,false);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

		holder.ivIcon.setImageResource(data.get(position).getIvId());
		holder.tvName.setText(data.get(position).getName());
		if (onItemClickListener !=null){
			holder.itemView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					onItemClickListener.onItemClick(holder.itemView);
				}
			});

		}
	}



	@Override
	public int getItemCount() {
		return data.size();
	}



	class ViewHolder extends RecyclerView.ViewHolder{
		TextView tvName;
		ImageView ivIcon;

		public ViewHolder(View itemView) {
			super(itemView);
			tvName = itemView.findViewById(R.id.item_main_tv);
			ivIcon  = itemView.findViewById(R.id.item_main_iv);

		}
	}
}
