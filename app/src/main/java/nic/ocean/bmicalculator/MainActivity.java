package nic.ocean.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    TextView tvResult,tvSuggestion;
    EditText etHeight,etWeight;
    Button btnCalculate,btnClear;
    RadioGroup rg_gender;
    RadioButton rb_gender;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        etHeight = findViewById(R.id.etHeight);
        etWeight = findViewById(R.id.etWeight);

        tvResult = findViewById(R.id.tvResult);
        tvSuggestion = findViewById(R.id.tvSuggestion);
        btnClear = findViewById(R.id.btnClear);
        btnCalculate = findViewById(R.id.btnCalculate);

        rg_gender = findViewById(R.id.rg_Gender);
        rg_gender.setOnCheckedChangeListener(this);
        btnCalculate.setOnClickListener(this);
        btnClear.setOnClickListener(this);

//        findViewById(R.id.btnCalculate).setOnClickListener(new View.OnClickListener() {
//
//            //logic for validation, input can't be empty
//            @Override
//            public void onClick(View view) {
//
//                btnClear.setVisibility(View.VISIBLE);
//
//                String height = etHeight.getText().toString();
//                String weight = etWeight.getText().toString();
//
//                //error warning for empty input in height edit text
//                if(TextUtils.isEmpty(height)){
//                    etHeight.setError("PLEASE ENTER YOUR HEIGHT");
//                    etHeight.requestFocus();
//                    return;
//                }
//                //error warning for empty input in weight edit text
//                else if(TextUtils.isEmpty(weight)){
//                    etWeight.setError("PLEASE ENTER YOUR WEIGHT");
//                    etWeight.requestFocus();
//                    return;
//                }
//
//
//                //get the user value from the widget reference
//                float heightF = Float.parseFloat(height)/100;
//                float weightF = Float.parseFloat(weight);
//
//                //calculate BMI value
//                float bmiValue = calculateBMI(weightF,heightF);
//
//                //Define the meaning of the bmi value
//                String bmiInterpretation = interpretBMI(bmiValue);
//
//                tvResult.setText(String.valueOf(" Gender :" + rb_gender.getText().toString() + " Your BMI value is "+ bmiValue + "-" + bmiInterpretation ));
//            }
//        });

//        btnClear.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                tvResult.setText(null);
//                tvSuggestion.setText(null);
//                etWeight.setText(null);
//                etHeight.setText(null);
//                rg_gender.clearCheck();
//
//                btnClear.setVisibility(View.GONE);
//            }
//        });

    }

    //method to calculate bmi value
    private float calculateBMI(float weightF, float heightF) {

        return (float) (weightF / (heightF * heightF));
    }

    //method to show interpret bmi msg
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

    //
    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {

        if (radioGroup.getCheckedRadioButtonId() == -1){
            Toast.makeText(context, "Select your Gender !!!", Toast.LENGTH_SHORT).show();
        }
        else {
            rb_gender = findViewById(i);
            Toast.makeText(context, rb_gender.getText().toString(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            //button calculate bmi click event
            case R.id.btnCalculate:

                btnClear.setVisibility(View.VISIBLE);

                String height = etHeight.getText().toString();
                String weight = etWeight.getText().toString();

                //error warning for empty input in height edit text
                if(TextUtils.isEmpty(height)){
                    etHeight.setError("PLEASE ENTER YOUR HEIGHT");
                    etHeight.requestFocus();
                    return;
                }
                //error warning for empty input in weight edit text
                else if(TextUtils.isEmpty(weight)){
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

                tvResult.setText(String.valueOf(" Gender : " + rb_gender.getText().toString() + " Your BMI value is "+ bmiValue + "-" + bmiInterpretation ));

                break;

            //button clear click event
            case R.id.btnClear:
                tvResult.setText(null);
                tvSuggestion.setText(null);
                etWeight.setText(null);
                etHeight.setText(null);
                rg_gender.clearCheck();
                btnClear.setVisibility(View.GONE);

                break;
        }
    }
}