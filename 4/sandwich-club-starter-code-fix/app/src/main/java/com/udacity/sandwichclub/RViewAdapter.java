package com.udacity.sandwichclub;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RViewAdapter extends RecyclerView.Adapter<RViewAdapter.ViewHolder> {
    private final String[] items;
    public static final String EXTRA_POSITION = "extra_position";


    RViewAdapter(String[] items) {
        this.items = items;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.idView.setText(items[position]);
        holder.itemView.setId(position);
        holder.itemView.setOnClickListener(onClickListener);
    }
    @Override
    public int getItemCount() { return items.length; }

    class ViewHolder extends RecyclerView.ViewHolder {
        final TextView idView;


        ViewHolder(View view) {
            super(view);
            idView = view.findViewById(R.id.id_text);

        }
    }

    final private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Integer item = (Integer) view.getId();
            Context context = view.getContext();
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra(EXTRA_POSITION, item);
            context.startActivity(intent);
        }
    };
}

