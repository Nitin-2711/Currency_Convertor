package com.example.currency_convertor;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Button btn = findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText editText=findViewById(R.id.edittxt);


                String message=editText.getText().toString();

                float valInDollar=Float.parseFloat(message);

                Float valInRupees= (float) (valInDollar*85);

                String finalVal=String.valueOf(valInRupees);

                Toast.makeText(MainActivity.this, finalVal, Toast.LENGTH_SHORT).show();


            }
        });


    }
}