package rvtest.jerry.com.recyclerviewtest.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import rvtest.jerry.com.recyclerviewtest.R;
import rvtest.jerry.com.recyclerviewtest.util.ToastUtil;

public class BasicAdapter extends RecyclerView.Adapter<BasicAdapter.ViewHolder> {

    private List<String> data ;
    private Context mContext;
    public BasicAdapter(Context context, List<String> data){
        this.data = data;
        mContext = context;
    }


    public void addData(boolean head){
        if (head){
            data.add(0,"head");
            notifyItemInserted(0);
        }else{
            data.add(data.size(),"bottom");
            notifyItemInserted(data.size());
        }


    }

    public void removeData(boolean head){
        if (head){
            data.remove(0);
            notifyItemRemoved(0);
        }else{
            data.remove(data.size());
            notifyItemRemoved(data.size());
        }
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_basic_rv,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showShort(mContext,"点击了："+data.get(position));
            }
        });
        holder.tvMain.setText(data.get(position));
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
