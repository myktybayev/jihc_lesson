package kz.jihc.registration;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tv_result, tv_zapis;
    Button btn7, btn8, btn9, btnX;
    Button btn4, btnMinus, btnPlus;
    Button btnCalc;
    String previousNumber, secondNumber;
    String operation = "";
    String allZapis = "";

    /*

    previousNumber = 700
    operation = +
    currentNumber = 99
    =
    799

     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator);

        initViews();
        initActions();

    }

    public void initViews() {
        tv_result = findViewById(R.id.tv_result);
        tv_zapis = findViewById(R.id.tv_zapis);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        btnX = findViewById(R.id.btnX);

        btn4 = findViewById(R.id.btn4);
        btnCalc = findViewById(R.id.btnCalc);
        btnMinus = findViewById(R.id.btnMinus);
        btnPlus = findViewById(R.id.btnPlus);

    }

    public void initActions() {

        onClickButton(btn7, "7");
        onClickButton(btn8, "8");
        onClickButton(btn9, "9");

        onClickOperationButton(btnPlus, "+");
        onClickOperationButton(btnMinus, "-");
        onClickButtonCalc();
    }

    public void onClickOperationButton(Button btn, final String curOperation) {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                previousNumber = tv_result.getText().toString();

                if(!previousNumber.equals("0")) {
                    operation = curOperation;
                    tv_result.setText("0");

                    allZapis += previousNumber + " " + operation;
                    tv_zapis.setText(allZapis);
                }
            }
        });
    }

    public void onClickButtonCalc() {
        btnCalc.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {

                secondNumber = tv_result.getText().toString();
                allZapis += secondNumber;
                int result = 0;

                switch (operation){
                    case "+":
                        result = Integer.parseInt(previousNumber) + Integer.parseInt(secondNumber);

                        break;

                    case "-":
                        result = Integer.parseInt(previousNumber) - Integer.parseInt(secondNumber);

                        break;
                }

                tv_zapis.setText(allZapis);
                tv_result.setText(Integer.toString(result));
            }
        });
    }

    public void onClickButton(Button btn, final String text) {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String res = tv_result.getText().toString(); // 0

                if (res.equals("0")) {
                    res = text; // 7
                } else {
                    res += text; // res = res + text; res = 78
                }
                tv_result.setText(res); // 78
            }
        });
    }

}

