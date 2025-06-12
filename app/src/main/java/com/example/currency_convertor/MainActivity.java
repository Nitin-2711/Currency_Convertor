package com.example.currency_convertor;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    Button btn, resetBtn;
    TextView outputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        editText = findViewById(R.id.edittxt);
        btn = findViewById(R.id.btn);
        resetBtn = findViewById(R.id.resetBtn);
        outputText = findViewById(R.id.outputText);

        // Convert button logic
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = editText.getText().toString().trim();
                if (input.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter an amount!", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    float amount = Float.parseFloat(input);
                    fetchExchangeRate(amount);
                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "Invalid number format!", Toast.LENGTH_SHORT).show();
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

    private void fetchExchangeRate(float dollarAmount) {
        String apiKey = "061f11a63427e4a8cea79e19"; // Replace with your real API key
        String url = "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/USD";

        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        JSONObject rates = response.getJSONObject("conversion_rates");
                        double inrRate = rates.getDouble("INR");
                        double converted = dollarAmount * inrRate;
                        outputText.setText("Converted: " + String.format("%.2f INR", converted));
                    } catch (JSONException e) {
                        Toast.makeText(MainActivity.this, "Parsing error!", Toast.LENGTH_SHORT).show();
                    }
                },
                error -> Toast.makeText(MainActivity.this, "API Error: " + error.getMessage(), Toast.LENGTH_SHORT).show()
        );

        queue.add(request);
    }
}
