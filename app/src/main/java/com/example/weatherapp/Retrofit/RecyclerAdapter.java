package com.example.weatherapp.Retrofit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapp.R;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyviewHolder> {
    Context context;
    List<Example> example;

    public RecyclerAdapter(Context context, List<Example> movieList) {
        this.context = context;
        this.example = example;
    }

    @NonNull
    @Override
    public RecyclerAdapter.MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_adapter, parent, false);
        return new MyviewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.MyviewHolder holder, int position) {

        holder.tempText.setText(example.get(position).toString());
        holder.descText.setText(example.get(position).toString());
        holder.humidityText.setText(example.get(position).toString());


    }

    @Override
    public int getItemCount() {
        if (example != null) {
            return example.size();
        }
        return 0;
    }

    public void setMovieList(List<Example> example)
    {
        this.example = example;
        notifyDataSetChanged();
    }


    public class MyviewHolder extends RecyclerView.ViewHolder {
        TextView tempText, descText, humidityText;
        //ImageView image;

        public MyviewHolder(View itemView) {
            super(itemView);
            tempText = (TextView) itemView.findViewById(R.id.tempText);
            descText = (TextView) itemView.findViewById(R.id.descText);
            humidityText = (TextView) itemView.findViewById(R.id.humidityText);
        }
    }
}
