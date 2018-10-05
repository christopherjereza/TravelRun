package com.nowrunning.travelrun.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;

import com.nowrunning.travelrun.R;
import com.nowrunning.travelrun.util.Chats;

import java.util.ArrayList;

public class RecyclerViewRequests  extends RecyclerView.Adapter<RecyclerViewRequests.RecycHolderRequests> implements Filterable {

    private ArrayList<Chats> requests;
    private ArrayList<Chats> requestsFillter;
    //private

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString();
                if(charString.isEmpty()){
                    requestsFillter = requests;
                } else{
                    ArrayList<Chats> filteredList = new ArrayList<>();
                    for (Chats row: requests){
                        if(row.getPerson().getName().contains(charString.toLowerCase()) || row.getPerson().geteMail().contains(charString.toLowerCase())){
                            filteredList.add(row);
                        }
                    }
                    requestsFillter = filteredList;
                }
                FilterResults filterResult = new FilterResults();
                filterResult.values = requestsFillter;
                return filterResult;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                requestsFillter = (ArrayList<Chats>) results.values;
                notifyDataSetChanged();
            }
        };
    }

/*    public RecyclerViewRequests(ArrayList<Chats> profiles) {
    }

    public RecyclerViewRequests(ArrayList<Chats> chats) {
    }*/

    public class RecycHolderRequests extends RecyclerView.ViewHolder{
        protected ImageView imageViewRequests;

        public RecycHolderRequests(View itemView) {
            super(itemView);
            this.imageViewRequests = (ImageView) itemView.findViewById(R.id.imageViewRequests);
        }

    }

    public RecyclerViewRequests(ArrayList<Chats> listProf){
        this.requests = listProf;
        this.requestsFillter = new ArrayList<>();
    }


    @Override
    public RecyclerViewRequests.RecycHolderRequests onCreateViewHolder(ViewGroup parent, int viewType) {
        //numCreated++;
        //Log.d("RV", "OncreateViewHolder ["+numCreated+"]");
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.content_layout_request_images, null);
        RecycHolderRequests mh = new RecycHolderRequests(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(RecycHolderRequests holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.imageViewRequests.setImageResource(requests.get(position).getPerson().getMainImage());
    }


    @Override
    public int getItemCount() {
        return requestsFillter.size();
    }


    /*public class RecycHolderRequests  {
        public RecycHolderRequests(View itemView) {
            super(itemView);
        }
    }*/
}
