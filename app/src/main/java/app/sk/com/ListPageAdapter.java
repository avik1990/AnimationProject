package app.sk.com;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ListPageAdapter extends RecyclerView.Adapter<ListPageAdapter.ViewHolder> {

    private int itemLayout;
    List<String> items;
    Context context;

    public ListPageAdapter(List<String> items, Context context, int itemLayout) {
        this.items = items;
        this.itemLayout = itemLayout;
        this.context=context;
    }

    @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        return new ViewHolder(v);
    }

    @Override public void onBindViewHolder(ViewHolder holder, int position) {
        String item = items.get(position);
        holder.text.setText(item.toString());
       // context.startAnimation();
    }

    @Override public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
       // public ImageView image;
        public TextView text;

        public ViewHolder(View itemView) {
            super(itemView);
           // image = (ImageView) itemView.findViewById(R.id.image);
            text = (TextView) itemView.findViewById(R.id.text);
        }
    }
}