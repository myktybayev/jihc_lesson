package kz.jihc.registration.authen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import kz.jihc.registration.R;
import kz.jihc.registration.database.StoreDatabase;
import kz.jihc.registration.models.User;
import kz.jihc.registration.university.UniversityListActivity;

import static kz.jihc.registration.database.StoreDatabase.COLUMN_EMAIL;
import static kz.jihc.registration.database.StoreDatabase.COLUMN_INFO;
import static kz.jihc.registration.database.StoreDatabase.COLUMN_PASSWORD;
import static kz.jihc.registration.database.StoreDatabase.TABLE_STUDENTS;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    EditText etEmailLogin, etPasswordLogin;
    Button btnLogin;

    StoreDatabase storeDatabase;
    SQLiteDatabase sqLiteDatabase;
    FirebaseAuth mAuth;
    DatabaseReference databaseReference;
    TextView tvText;
    User user;
    String emailFormat;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        user = new User("Tesla", "Nikola", "tesla123@gmail.com", "87471199092");
        emailFormat = user.getEmail().replace(".", "-");
    }

    public void initViews(){
        etEmailLogin = findViewById(R.id.etEmailLogin);
        etPasswordLogin = findViewById(R.id.etPasswordLogin);

        storeDatabase = new StoreDatabase(this);
        sqLiteDatabase = storeDatabase.getWritableDatabase();

        tvText = findViewById(R.id.tvText);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();

//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        if(currentUser != null){
//            Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
//            startActivity(intent);
//        }


    }

    public void addDataToFirebase(){
        databaseReference.child("users").child(emailFormat).setValue(user);
    }

    public void getUserFromFirebase(){
        databaseReference.child("users").child(emailFormat).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    User fUser = snapshot.getValue(User.class);
                    tvText.setText(fUser.getName());
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnLogin:
                String userEmail = etEmailLogin.getText().toString();
                String userPassword = etPasswordLogin.getText().toString();

                if(userEmail.isEmpty()){
                    etEmailLogin.setError("Try again");
                    return;
                }

                if(userPassword.isEmpty()){
                    etPasswordLogin.setError("Try again");
                    return;
                }

                logIn(userEmail, userPassword);

                break;
        }
    }

    public void logIn(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Қош келдің!", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(LoginActivity.this, "Error: "+task.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void dbLogin(){

        String userEmail = etEmailLogin.getText().toString();
        String userPassword = etPasswordLogin.getText().toString();
            Cursor loginCursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_STUDENTS + " WHERE " +
                    COLUMN_EMAIL+ "=? AND "+COLUMN_PASSWORD+ "=? ", new String[]{userEmail, userPassword});

            if(loginCursor!=null & loginCursor.getCount() > 0){
                loginCursor.moveToFirst();

                String fullName = loginCursor.getString(loginCursor.getColumnIndex(COLUMN_INFO));
                Toast.makeText(this, "Welcome: "+fullName, Toast.LENGTH_SHORT).show();

                startActivity(new Intent(LoginActivity.this, UniversityListActivity.class));

            }else{
                Toast.makeText(this, "User not found", Toast.LENGTH_SHORT).show();
            }
    }
}