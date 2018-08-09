package com.mostafa.root.ismartgp.Model;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.mostafa.root.ismartgp.R;
import java.util.List;

public class HomeActionAdapter extends RecyclerView.Adapter<HomeActionAdapter.ViewHolder>{
    private List<HomeAction> actionViewList;
    private Context context;


    public HomeActionAdapter(List<HomeAction> actionViewList , Context context){
        this.actionViewList = actionViewList ;
        this.context = context;
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView action_id ;
        public TextView action_current;
        public TextView action_next;
        public TextView action_count;

        public ViewHolder(View itemView) {
            super(itemView);
            action_id = (TextView) itemView.findViewById(R.id.txt_single_id);
            action_current =(TextView)  itemView.findViewById(R.id.txt_single_current);
            action_next =(TextView)  itemView.findViewById(R.id.txt_single_next);
            action_count =(TextView)  itemView.findViewById(R.id.txt_single_count);
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate( R.layout.single_action , parent ,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final HomeAction homeAction = actionViewList.get(position);
        holder.action_id.setText(homeAction.getAction_id());
        holder.action_current.setText(homeAction.getCurrent_action());
        holder.action_next.setText(homeAction.getNext_action());
        holder.action_count.setText(String.valueOf(homeAction.getAction_count()));


    }
    @Override
    public int getItemCount() {
        return actionViewList.size();

    }
}