package com.example.currency_convertor;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    Button btn, resetBtn;
    TextView outputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Link UI elements
        editText = findViewById(R.id.edittxt);
        btn = findViewById(R.id.btn);
        resetBtn = findViewById(R.id.resetBtn);
        outputText = findViewById(R.id.outputText);

        // Convert button logic
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = editText.getText().toString().trim();

                if (message.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter an amount!", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    float valInDollar = Float.parseFloat(message);
                    float valInRupees = valInDollar * 85;
                    String finalVal = String.format("%.2f INR", valInRupees);
                    outputText.setText("Converted: " + finalVal);
                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "Invalid input!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Reset button logic
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("");
                outputText.setText("After Converting Amount");
            }
        });
    }
}
