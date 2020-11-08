package th.ac.su.cp.speedrecords.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import th.ac.su.cp.speedrecords.R;
import th.ac.su.cp.speedrecords.model.Record;

public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.MyViewHolder> {
    private Context mContext;
    private Record[] mRecords;


    public RecordAdapter(Context mContext, Record[] mRecords) {
        this.mContext = mContext;
        this.mRecords = mRecords;
    }


    @NonNull
    @Override
    public RecordAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_record, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecordAdapter.MyViewHolder holder, int position) {
        Record record = mRecords[position];

        holder.distanceTextView.setText(record.distance+" METERS,");
        holder.durationTextView.setText(record.duration+" SECONDS" );
        holder.speedTextView.setText(record.speed+" KM/H");

        double speed = Double.parseDouble(record.speed);
        if (speed >= 80){
            holder.overLimitImageView.setImageResource(R.drawable.red_cow);
        }else {
            holder.overLimitImageView.setImageResource(0);
        }
    }


    @Override
    public int getItemCount() {
        return mRecords.length;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView speedTextView;
        TextView distanceTextView;
        TextView durationTextView;
        ImageView overLimitImageView;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            this.speedTextView = itemView.findViewById(R.id.speed_text_view);
            this.distanceTextView = itemView.findViewById(R.id.distance_text_view);
            this.durationTextView = itemView.findViewById(R.id.duration_text_view);
            this.overLimitImageView = itemView.findViewById(R.id.over_limit_image_view);
        }
    }

}
