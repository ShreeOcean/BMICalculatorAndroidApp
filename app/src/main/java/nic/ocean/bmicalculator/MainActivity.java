package nic.ocean.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tvResult,tvSuggestion;
    EditText etHeight,etWeight;
    Button btnCalculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etHeight = findViewById(R.id.etHeight);
        etWeight = findViewById(R.id.etWeight);

        tvResult = findViewById(R.id.tvResult);
        tvSuggestion = findViewById(R.id.tvSuggestion);

        findViewById(R.id.btnCalculate).setOnClickListener(new View.OnClickListener() {

            //logic for validation, input can't be empty
            @Override
            public void onClick(View view) {

                String height = etHeight.getText().toString();
                String weight = etWeight.getText().toString();

                if(TextUtils.isEmpty(height)){
                    etHeight.setError("PLEASE ENTER YOUR HEIGHT");
                    etHeight.requestFocus();
                    return;
                }

                if(TextUtils.isEmpty(weight)){
                    etWeight.setError("PLEASE ENTER YOUR WEIGHT");
                    etWeight.requestFocus();
                    return;
                }

                //get the user value from the widget reference
                float heightF = Float.parseFloat(height)/100;
                float weightF = Float.parseFloat(weight);

                //calculate BMI value
                float bmiValue = calculateBMI(weightF,heightF);

                //Define the meaning of the bmi value
                String bmiInterpretation = interpretBMI(bmiValue);

                tvResult.setText(String.valueOf(bmiValue + "-" + bmiInterpretation));
            }
        });

    }

    private float calculateBMI(float weightF, float heightF) {

        return (float) (weightF / (heightF * heightF));
    }

    private String interpretBMI(float bmiValue) {

        if(bmiValue < 16){
            tvSuggestion.setText("Eat well nutritional, visit Doctor to be more healthy");
            return "Severly underweight";
        }
        else if (bmiValue < 18.5){
            tvSuggestion.setText("Eat well nutritional, be more healthy");
            return "Underweight";
        }
        else if(bmiValue < 25){
            tvSuggestion.setText("Good, be healthy");
            return "Normal";
        }
        else if(bmiValue < 30){
            tvSuggestion.setText("Eat well nutritional, visit Doctor to be more healthy and do regular exercise");
            return "Overweight";
        }
        else {
            return "OBESE !!! Be healthy, do visit Doctor";
        }

    }
}