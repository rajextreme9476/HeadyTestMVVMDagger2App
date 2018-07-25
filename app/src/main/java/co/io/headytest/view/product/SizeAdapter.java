package co.io.headytest.view.product;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import co.io.headytest.R;
import co.io.headytest.data.model.ProductResponse;

public class SizeAdapter extends RecyclerView.Adapter<SizeAdapter.ViewHolder> {

    Context applicationContext;
    List<ProductResponse.Variant> list;

    public SizeAdapter(Context applicationContext, List<ProductResponse.Variant> list) {
        this.applicationContext = applicationContext;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.inflate_size, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    public void onBindViewHolder( ViewHolder holder, int position) {
        holder.tvSize.setText(""+list.get(position).getSize());
        holder.tvColor.setText(""+list.get(position).getColor());
      }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {

        public final TextView tvSize;
        public final TextView tvColor;

        public ViewHolder(View view)
        {
            super(view);
            tvSize =  view.findViewById(R.id.tvSize);
            tvColor =  view.findViewById(R.id.tvColor);
        }

    }
}