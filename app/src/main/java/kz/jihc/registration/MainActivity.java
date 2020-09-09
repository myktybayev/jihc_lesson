package kz.jihc.registration;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tv_result;
    Button btn7, btn8, btn9, btnX;
    Button btn4, btn5, btn6, btnMinus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator);

        initViews();
        initActions();

    }

    public void initViews(){
        tv_result = findViewById(R.id.tv_result);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        btnX = findViewById(R.id.btnX);

        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btnMinus = findViewById(R.id.btnMinus);

    }

    public void initActions(){

        onClickButton(btn7, "7");
        onClickButton(btn8, "8");
        onClickButton(btn9, "9");
        onClickButton(btn5, "5");

    }

    public void onClickButton(Button btn, final String text){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String res = tv_result.getText().toString(); // 0

                if(res.equals("0")){
                    res = text; // 7
                }else {
                    res += text; // res = res + text; res = 78
                }
                tv_result.setText(res); // 78
            }
        });
    }

}

