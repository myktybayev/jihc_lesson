package kz.jihc.registration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static kz.jihc.registration.StoreDatabase.COLUMN_INFO;
import static kz.jihc.registration.StoreDatabase.COLUMN_EMAIL;
import static kz.jihc.registration.StoreDatabase.COLUMN_PASSWORD;
import static kz.jihc.registration.StoreDatabase.TABLE_USER;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etFullname, etEmail, etPassword;
    Button btnCreateAccount, btnLogin;

    StoreDatabase storeDatabase;
    SQLiteDatabase sqLiteDatabase;

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

        btnCreateAccount.setOnClickListener(this);
        btnLogin.setOnClickListener(this);

        storeDatabase = new StoreDatabase(this);
        sqLiteDatabase = storeDatabase.getWritableDatabase();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnCreateAccount:

                boolean createAccount = true;

                if(etFullname.getText().toString().isEmpty()){
                    etFullname.setError("Try again");
                    createAccount = false;
                }

                if(etEmail.getText().toString().isEmpty()){
                    etEmail.setError("Try again");
                    createAccount = false;
                }

                if(etPassword.getText().toString().isEmpty()){
                    etPassword.setError("Try again");
                    createAccount = false;
                }

                if(createAccount){

                    // database insert

                    ContentValues userValue = new ContentValues();
                    userValue.put(COLUMN_INFO, etFullname.getText().toString());
                    userValue.put(COLUMN_EMAIL, etEmail.getText().toString());
                    userValue.put(COLUMN_PASSWORD, etPassword.getText().toString());

                    sqLiteDatabase.insert(TABLE_USER, null, userValue);

                    Toast.makeText(this, "Create account success!", Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(this, "Fill all info, try again!", Toast.LENGTH_SHORT).show();
                }

                break;

            case R.id.btnLogin:

                break;
        }
    }
}