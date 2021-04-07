package kz.jihc.registration.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import kz.jihc.registration.R;

public class  MenuActivity extends AppCompatActivity {

    // TODO 2 start
    TextView textViewAndroid;
    TextView textViewiOs;
    // TODO 3 start

    Button submit;

    RadioButton bankRadio;
    RadioButton akshaRadio;

    CheckBox silykCheckBox;
    CheckBox dostavkaCheckBox;

    String androidPhone, iOsPhone, tolemTyri, silyk, dostavka;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        textViewAndroid = findViewById(R.id.textViewAndroid);
        textViewiOs = findViewById(R.id.textViewiOs);
        submit = findViewById(R.id.submit);

        bankRadio = findViewById(R.id.bankRadio);
        akshaRadio = findViewById(R.id.akshaRadio);

        silykCheckBox = findViewById(R.id.silykCheckBox);
        dostavkaCheckBox = findViewById(R.id.dostavkaCheckBox);

        registerForContextMenu(textViewAndroid);
        registerForContextMenu(textViewiOs);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(bankRadio.isChecked()){
                    tolemTyri = "Банк карточкасы";
                }else if(akshaRadio.isChecked()){
                    tolemTyri = "Қолма қол ақшамен";
                }

                if(silykCheckBox.isChecked()){
                    silyk = "Сыйлық корабшасына орау";
                }else{
                    silyk = "қажет емес";
                }

                if(dostavkaCheckBox.isChecked()){
                    dostavka = "керек";
                }else{
                    dostavka = "қажет емес";
                }

                String report = "Android телефон:"+androidPhone+"\n"+
                                "iOs телефон:"+iOsPhone+"\n"+
                                "Төлем: "+tolemTyri+"\n"+
                                "Безендеу: "+silyk+"\n"+
                                "Жеткізу: "+dostavka;

                Toast.makeText(MenuActivity.this, report, Toast.LENGTH_LONG).show();
            }
        });
    }

//    TODO 1
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.bantau:
                Toast.makeText(this, "bantau basildi", Toast.LENGTH_SHORT).show();

                break;

            case R.id.shigu:
                Toast.makeText(this, "shigu basildi", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }


    // TODO 2
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if(v == textViewAndroid) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.context_menu_android, menu);
            menu.setHeaderTitle("Телефон модель таңдаңыз");

        }else if(v == textViewiOs) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.context_menu_ios, menu);
            menu.setHeaderTitle("Телефон модель таңдаңыз");
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item){
            switch (item.getItemId()) {
                case R.id.samsung:
                    androidPhone = "samsung";
                    textViewAndroid.setText("samsung таңдалды");
                    break;

                case R.id.huawei:
                    androidPhone = "huawei";
                    textViewAndroid.setText("huawei таңдалды");
                    break;

                case R.id.mi9:
                    androidPhone = "mi9";
                    textViewAndroid.setText("mi9 таңдалды");
                    break;

                case R.id.iphone12Max:
                    iOsPhone = "iphone12Max";
                    textViewiOs.setText("iphone12Max таңдалды");
                    break;
                case R.id.iphone12:
                    iOsPhone = "iphone12";
                    textViewiOs.setText("iphone12 таңдалды");
                    break;
                case R.id.iphone11Max:
                    iOsPhone = "iphone11Max";
                    textViewiOs.setText("iphone11Max таңдалды");
                    break;
                case R.id.iphone11:
                    iOsPhone = "iphone11";
                    textViewiOs.setText("iphone11 таңдалды");
                    break;
            }
            return true;
    }


}