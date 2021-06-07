package com.example.imageclassification;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HistoryDataAdapter extends RecyclerView.Adapter<HistoryDataAdapter.HistoryViewHolder> {
    private Context mctx;
    private List<HistoryDataModel> historyDataModelList;

    public HistoryDataAdapter(Context mCtx, List<HistoryDataModel> historyDataModelList){
        this.mctx = mCtx;
        this.historyDataModelList = historyDataModelList;
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(mctx);
        View view = layoutInflater.inflate(R.layout.history_data_card,null);
        return new HistoryViewHolder(view);
    }


    @Override
    public void onBindViewHolder(HistoryDataAdapter.HistoryViewHolder holder, int position) {
        HistoryDataModel historyDataModel = historyDataModelList.get(position);

        holder.timestamp.setText("Timestamp : "+historyDataModel.getTimestamp());
        holder.output.setText("output : "+historyDataModel.getPrediction());
        holder.validation.setText("validation : "+historyDataModel.getFlag());
        holder.correct_val.setText("correct output : "+historyDataModel.getCorrect_value());
    }

    @Override
    public int getItemCount() {
        return historyDataModelList.size();
    }

    public class HistoryViewHolder extends RecyclerView.ViewHolder {

        TextView timestamp,output,validation,correct_val;

        public HistoryViewHolder(View itemView) {
            super(itemView);

            timestamp = itemView.findViewById(R.id.history_card_timestamp);
            output = itemView.findViewById(R.id.history_card_output);
            validation = itemView.findViewById(R.id.history_card_flag);
            correct_val = itemView.findViewById(R.id.history_card_correct_value);

        }
    }

}
