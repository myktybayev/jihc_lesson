package kz.jihc.registration.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import kz.jihc.registration.R;
import kz.jihc.registration.adapters.BookListAdapter;
import kz.jihc.registration.models.Book;
import kz.jihc.registration.models.User;

public class LibraryActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager linearLayoutManager;
    Book book;
    ArrayList<Book> bookList;
    BookListAdapter bookListAdapter;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);
        recyclerView = findViewById(R.id.recyclerView);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        bookList = new ArrayList<>();

        bookListAdapter = new BookListAdapter(this, bookList);
        recyclerView.setAdapter(bookListAdapter);

        collectList();
    }

    public void collectList(){
//        bookList.add(new Book("test","test",100,"test"));
//        bookList.add(new Book("test2","test2",100,"test"));
//        bookList.add(new Book("test3","test3",100,"test"));
//        bookList.add(new Book("test4","test4",100,"test"));

        databaseReference.child("library").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    bookList.clear();

                    for (DataSnapshot userInfo : snapshot.getChildren()) {
                        Book book = userInfo.getValue(Book.class);
                        bookList.add(book);
                    }

                    bookListAdapter.notifyDataSetChanged();

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

}