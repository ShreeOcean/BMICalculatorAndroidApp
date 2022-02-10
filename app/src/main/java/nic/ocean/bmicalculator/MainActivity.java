package nic.ocean.bmicalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import nic.ocean.bmicalculator.databinding.AboutAppPopupBinding;
import nic.ocean.bmicalculator.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    TextView tvResult,tvSuggestion;
    EditText etHeight,etWeight;
    Button btnCalculate,btnClear, btnGotIt, btnGotItBmiChart;
    RadioGroup rg_gender;
    Context context;
    private String gender = "";
    ImageButton btnCross;

    private AlertDialog.Builder dialogBuilder;
    private  AlertDialog dialog;

//    private ActivityMainBinding activityMainBinding;
//    private AboutAppPopupBinding aboutAppPopupBinding;


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

//        aboutAppPopupBinding = AboutAppPopupBinding.inflate(getLayoutInflater());
//        setContentView(aboutAppPopupBinding.getRoot());

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

    //adding menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
        return true;
    }

    //option menu item selected code
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.aboutAppMenuItem:
                createNewAboutAppDialog();
                break;
            case R.id.bmiChartMenuItem:
                createAboutBMIChart();
                break;
            case R.id.exitMenuItem:
                finish();
                break;
            case R.id.menuAboutDev:

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void createAboutBMIChart() {

        dialogBuilder = new AlertDialog.Builder(this);
        final View aboutBMIChart = getLayoutInflater().inflate(R.layout.about_bmi_chart, null);

        dialogBuilder.setView(aboutBMIChart);
        dialog = dialogBuilder.create();
        dialog.show();
        btnCross = aboutBMIChart.findViewById(R.id.imageBtnClose);

        btnGotItBmiChart = aboutBMIChart.findViewById(R.id.btnGotItBmiChart);
        btnGotItBmiChart.setOnClickListener(view -> dialog.dismiss());
        btnCross.setOnClickListener(view -> dialog.dismiss());

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

//    @Override
//    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
////        if (radioGroup.getCheckedRadioButtonId() == -1){
////            Toast.makeText(context, "Select your Gender !!!", Toast.LENGTH_SHORT).show();
////        }
//
//        RadioButton radioButton = findViewById(checkedId);
//            //rb_gender = findViewById(i);
//            gender = radioButton.getText().toString();
//            Toast.makeText(context, gender, Toast.LENGTH_SHORT).show();
//
//    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {

        Log.d("onCheckedChanged(RG)", "onCheckedChanged: " + checkedId);
        RadioButton radioButton = findViewById(checkedId);
        if(radioButton == null)return;
        //Toast.makeText(context, radioButton.getText().toString(), Toast.LENGTH_SHORT).show();
        gender = radioButton.getText().toString();

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
                else if (TextUtils.isEmpty(gender)){
                    Toast.makeText(context, "Select your gender !!!", Toast.LENGTH_SHORT).show();
                    return;
                }


                //get the user value from the widget reference
                float heightF = Float.parseFloat(height)/100;
                float weightF = Float.parseFloat(weight);

                //calculate BMI value
                float bmiValue = calculateBMI(weightF,heightF);

                //Define the meaning of the bmi value
                String bmiInterpretation = interpretBMI(bmiValue);

                tvResult.setText(String.valueOf(" Gender : " + gender + " Your BMI value is "+ bmiValue + "-" + bmiInterpretation ));

                break;

            //button clear click event
            case R.id.btnClear:
                tvResult.setText(null);
                tvSuggestion.setText(null);
                etWeight.setText(null);
                etHeight.setText(null);

//                if (rb_gender.isChecked()) {
//                    // If the button was already checked, uncheck them all
//                    rg_gender.clearCheck();
//                    // Prevent the system from re-checking it
//                    return ;
//                }

                rg_gender.clearCheck();
                btnClear.setVisibility(View.GONE);

                break;
        }
    }

    public void createNewAboutAppDialog(){

        dialogBuilder = new AlertDialog.Builder(this);
        final View aboutAppPopupView = getLayoutInflater().inflate(R.layout.about_app_popup, null);

        btnGotIt = aboutAppPopupView.findViewById(R.id.btnGotIt);
        btnCross = aboutAppPopupView.findViewById(R.id.imageBtnClose);

        dialogBuilder.setView(aboutAppPopupView);
        dialog = dialogBuilder.create();
        dialog.show();

        btnGotIt.setOnClickListener(view -> dialog.dismiss());
        btnCross.setOnClickListener(view -> dialog.dismiss());
    }
}