package rvtest.jerry.com.recyclerviewtest.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import rvtest.jerry.com.recyclerviewtest.R;
import rvtest.jerry.com.recyclerviewtest.interfaces.OnRvItemClickListener;

/**
 * @author Jerry on 2018/6/22.
 * @function
 */
public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.ViewHolder> {

	private List<String>  hotelName;



	public PlaceAdapter(List<String> hotelName){
		this.hotelName = hotelName;
	}
	@NonNull
	@Override
	public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_place_rv,parent,false);
		ViewHolder vh = new ViewHolder(view);

		return vh;
	}

	@Override
	public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
		holder.tvName.setText(hotelName.get(position));
		if(listener !=null){
			holder.itemView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					if (v.isSelected()){
						v.setSelected(false);
					}else {
						v.setSelected(true);
					}
					listener.onItemClick(v);


				}
			});
		}
	}

	@Override
	public int getItemCount() {
		return hotelName.size();
	}

	class ViewHolder extends RecyclerView.ViewHolder{
		TextView tvName;
		public ViewHolder(View itemView) {
			super(itemView);
			tvName = itemView.findViewById(R.id.item_place_tv);
		}
	}

	private OnRvItemClickListener listener;

	public void setOnRvItemClickListener(OnRvItemClickListener listener){
		this.listener = listener;
	}
}
