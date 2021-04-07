package kz.jihc.registration.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import kz.jihc.registration.R;
import kz.jihc.registration.models.Book;

public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.MyTViewHolder>{
    private Context context;
    private List<Book> bookList;

    public class MyTViewHolder extends RecyclerView.ViewHolder{
        public ImageView book_photo;
        public TextView info, author, pageNumber;


        public MyTViewHolder(View view) {
            super(view);
            book_photo = view.findViewById(R.id.book_photo);
            info = view.findViewById(R.id.info);
            author = view.findViewById(R.id.author);
            pageNumber = view.findViewById(R.id.pageNumber);

        }
    }

    public BookListAdapter(Context context, List<Book> bookList) {
        this.context = context;
        this.bookList = bookList;
    }

    @Override
    public MyTViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book, parent, false);

        return new MyTViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyTViewHolder holder, int position) {

        Book item = bookList.get(position);
        Glide.with(context.getApplicationContext())
                .load(item.getPhoto())
                .placeholder(R.drawable.ic_baseline_book_24)
                .into(holder.book_photo);

        holder.info.setText(item.getName());
        holder.author.setText(item.getAuthor());
        holder.pageNumber.setText(""+item.getPageNumber());

    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

}