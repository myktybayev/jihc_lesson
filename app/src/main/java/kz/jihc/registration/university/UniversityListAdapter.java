package kz.jihc.registration.university;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import kz.jihc.registration.R;

public class UniversityListAdapter extends RecyclerView.Adapter<UniversityListAdapter.MyTViewHolder> {

    private Context context;
    private List<University> dataList;

    public class MyTViewHolder extends RecyclerView.ViewHolder{

        ImageView photo;
        TextView name;
        TextView city;
        TextView phone;

        public MyTViewHolder(View view) {
            super(view);
            photo = view.findViewById(R.id.photo);
            name = view.findViewById(R.id.name);
            city = view.findViewById(R.id.city);
            phone = view.findViewById(R.id.phone);

        }

    }

    public UniversityListAdapter(Context context, List<University> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @Override
    public MyTViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_university, parent, false);

        return new MyTViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyTViewHolder holder, int position) {

        University item = dataList.get(position);

        Glide.with(context.getApplicationContext())
                .load(item.getPhoto())
                .placeholder(R.drawable.ic_univer)
                .into(holder.photo);

        holder.name.setText(item.getName());
        holder.city.setText(item.getCity());
        holder.phone.setText(item.getPhoneNumber());

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

}