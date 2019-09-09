package com.example.abhishek.stock;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {

    Context context;

    ArrayList<String> nameList;
    ArrayList<String> brokerageList;
    ArrayList<String> intraList;
    ArrayList<String> accountList;
    ArrayList<String> annualList;


    class SearchViewHolder extends RecyclerView.ViewHolder {

        TextView na,br,ir,acc,ann;

        public SearchViewHolder(View itemView) {
            super(itemView);

            na = (TextView) itemView.findViewById(R.id.namee);
            br = (TextView) itemView.findViewById(R.id.brokeragee);
            ir = (TextView) itemView.findViewById(R.id.intrae);
            acc = (TextView) itemView.findViewById(R.id.accounte);
            ann = (TextView) itemView.findViewById(R.id.annuale);

        }
    }

    public SearchAdapter(Context context, ArrayList<String> nameList, ArrayList<String> brokerageList, ArrayList<String> intraList, ArrayList<String> accountList, ArrayList<String> annualList){
        this.context = context;
        this.nameList = nameList;
        this.brokerageList = brokerageList;
        this.intraList = intraList;
        this.accountList = accountList;
        this.annualList = annualList;

    }

    @Override
    public SearchAdapter.SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.search_list_items, parent, false);
        return new SearchAdapter.SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchViewHolder holder, int position) {
        holder.na.setText(nameList.get(position));
        holder.br.setText(brokerageList.get(position));
        holder.ir.setText(intraList.get(position));
        holder.acc.setText(accountList.get(position));
        holder.ann.setText(annualList.get(position));

    }


    @Override
    public int getItemCount() {
        return nameList.size();
    }
}
