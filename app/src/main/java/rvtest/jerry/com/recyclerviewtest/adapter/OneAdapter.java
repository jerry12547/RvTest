package rvtest.jerry.com.recyclerviewtest.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import rvtest.jerry.com.recyclerviewtest.R;

/**
 * @author Jerry on 2018/6/22.
 * @function
 */
public class OneAdapter extends RecyclerView.Adapter<OneAdapter.ViewHolder> {

	private List<String>  hotelName;



	public OneAdapter(List<String> hotelName){
		this.hotelName = hotelName;
	}
	@NonNull
	@Override
	public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_one_tv,parent,false);
		ViewHolder vh = new ViewHolder(view);

		return vh;
	}

	@Override
	public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
		holder.tvName.setText(hotelName.get(position));
	}

	@Override
	public int getItemCount() {
		return hotelName.size();
	}

	class ViewHolder extends RecyclerView.ViewHolder{
		TextView tvName;
		public ViewHolder(View itemView) {
			super(itemView);
			tvName = itemView.findViewById(R.id.item_one_tv);
		}
	}
}
