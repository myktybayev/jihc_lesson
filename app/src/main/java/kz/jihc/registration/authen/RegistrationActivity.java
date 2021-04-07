package kz.jihc.registration.authen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

import kz.jihc.registration.R;
import kz.jihc.registration.database.StoreDatabase;

import static kz.jihc.registration.database.StoreDatabase.COLUMN_GINFO;
import static kz.jihc.registration.database.StoreDatabase.COLUMN_GROUP_ID;
import static kz.jihc.registration.database.StoreDatabase.COLUMN_INFO;
import static kz.jihc.registration.database.StoreDatabase.COLUMN_EMAIL;
import static kz.jihc.registration.database.StoreDatabase.COLUMN_PASSWORD;
import static kz.jihc.registration.database.StoreDatabase.COLUMN_SUM;
import static kz.jihc.registration.database.StoreDatabase.TABLE_GROUPS;
import static kz.jihc.registration.database.StoreDatabase.TABLE_STUDENTS;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {

    List<String> list = new ArrayList<String>();
    EditText etFullname, etEmail, etPassword;
    Button btnCreateAccount, btnLogin;
    Spinner groupSpinner;

    StoreDatabase storeDatabase;
    SQLiteDatabase sqLiteDatabase;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        initViews();

    }

    public void initViews(){
        etFullname = findViewById(R.id.etFullname);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);

        btnCreateAccount = findViewById(R.id.btnCreateAccount);
        btnLogin = findViewById(R.id.btnLogin);
        groupSpinner = findViewById(R.id.groupSpinner);

        storeDatabase = new StoreDatabase(this);
        sqLiteDatabase = storeDatabase.getWritableDatabase();

        mAuth = FirebaseAuth.getInstance();

        btnCreateAccount.setOnClickListener(this);
        btnLogin.setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnCreateAccount:
                if(TextUtils.isEmpty(etFullname.getText().toString())){
                    etFullname.setError("Try again");
                    return;
                }

                if(TextUtils.isEmpty(etEmail.getText().toString())){
                    etEmail.setError("Try again");
                    return;
                }

                if(TextUtils.isEmpty(etPassword.getText().toString())){
                    etPassword.setError("Try again");
                    return;
                }

                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();

                createAccount(email, password);

                break;

            case R.id.btnLogin:
                Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(intent);
                break;
        }
    }

    public void createAccount(String email, String password){

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(RegistrationActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(RegistrationActivity.this, "Welcome Home!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(RegistrationActivity.this, "Error: "+task.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void addToDatabase(){
            ContentValues userValue = new ContentValues();
            userValue.put(COLUMN_INFO, etFullname.getText().toString());
            userValue.put(COLUMN_EMAIL, etEmail.getText().toString());
            userValue.put(COLUMN_PASSWORD, etPassword.getText().toString());

            sqLiteDatabase.insert(TABLE_STUDENTS, null, userValue);

            Toast.makeText(this, "Create account success!", Toast.LENGTH_SHORT).show();
            showDatabaseData();

    }

    //        initSpinner();
    public void initSpinner(){
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_GROUPS, null);

        if ((cursor != null && cursor.getCount() > 0)) {
            while (cursor.moveToNext()) {
                String gInfo = cursor.getString(cursor.getColumnIndex(COLUMN_GINFO));
                String gSum = cursor.getString(cursor.getColumnIndex(COLUMN_SUM));
                String gId = cursor.getString(cursor.getColumnIndex(COLUMN_GROUP_ID));

                Log.i("Database", "fullName: "+gInfo);
                Log.i("Database", "email: "+gSum);
                Log.i("Database", "password: "+gId);

                list.add(gId);
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.item_group, list);
        groupSpinner.setAdapter(adapter);
    }

    public void showDatabaseData(){
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_STUDENTS, null);

        if ((cursor != null && cursor.getCount() > 0)) {
            while (cursor.moveToNext()) {
                String fName = cursor.getString(cursor.getColumnIndex(COLUMN_INFO));
                String email = cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL));
                String password = cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD));

                Log.i("Database", "fullName: "+fName);
                Log.i("Database", "email: "+email);
                Log.i("Database", "password: "+password);

            }
        }
    }
}