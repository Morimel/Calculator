package com.example.calculator2;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private Double a, b;
    private String operation;
    private boolean isOperation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        textView = findViewById(R.id.text_view);
    }

    public void onClickButton(View view) {
        String text = ((MaterialButton) view).getText().toString();
        String currentText = textView.getText().toString();
        if (text.equals("AC")) {
            textView.setText("0");
            a = 0.0;
            b = 0.0;
        } else if(text.equals(".")) {
            if (!currentText.contains(".")) {
                textView.append(text);
            }
        } else if (textView.getText().toString().equals("0") || isOperation){
            textView.setText(text);
        } else {
            textView.append((text));
        }
        isOperation = false;
    }

    public void onOperationClick(View view) {
        String text = ((MaterialButton) view).getText().toString();

        if (text.equals("+") || text.equals("-") || text.equals("x") || text.equals("/") || text.equals("%")) {
            a = Double.valueOf(textView.getText().toString());
            operation = text;
            isOperation = true;
        }  else if (view.getId() == R.id.equalButton) {
            b = Double.valueOf(textView.getText().toString());
            double result = 0;
            if (operation != null) {
                switch (operation) {
                    case "+":
                        result = a + b;
                        break;
                    case "-":
                        result = a - b;
                        break;
                    case "x":
                        result = a * b;
                        break;
                    case "%":
                        if (b == null) {
                            result = a / 100.0; // деление на 100.0 для получения дробного результата
                        } else {
                            result = (a / 100.0) * b; // деление на 100.0 и умножение на b
                        }
                        break;

                    case "/":
                        if (b != 0) {
                            result = a / b;
                        } else {
                            textView.setText("Error");
                            return;
                        }
                        break;
                }
                textView.setText(String.valueOf(result));
            }
        }
    }
}