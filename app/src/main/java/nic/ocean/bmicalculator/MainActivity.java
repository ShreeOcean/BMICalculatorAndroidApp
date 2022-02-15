package nic.ocean.bmicalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import nic.ocean.bmicalculator.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    TextView tvGender,tvResultBmi,tvSuggestion, tvAboutApp, tvAboutBmiChart;
    ImageButton cross;
    ImageView imageViewAppIcon, imageViewBmiChart;
    EditText etHeight,etWeight;
    Button btnCalculate,btnClear, btnGotIt;
    RadioGroup rg_gender;
    RadioButton rb_gender;
    Context context;

    private AlertDialog.Builder dialogBuilder;
    private AlertDialog alertDialog;
    private ActivityMainBinding bindingMain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        etHeight = findViewById(R.id.etHeight);
        etWeight = findViewById(R.id.etWeight);

        tvGender = findViewById(R.id.tvGender);
        tvResultBmi = findViewById(R.id.tvResultBmi);
        tvSuggestion = findViewById(R.id.tvSuggestion);
        btnClear = findViewById(R.id.btnClear);
        btnCalculate = findViewById(R.id.btnCalculate);

        rg_gender = findViewById(R.id.rg_Gender);
        rg_gender.setOnCheckedChangeListener(this);
        btnCalculate.setOnClickListener(this);
        btnClear.setOnClickListener(this);

    }

    //adding menu cade
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.option_menu,menu);
        return true;
    }
    //on option item selected from menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId){
            case R.id.aboutAppMenuItem:
                aboutAppMenuOnClick();
                break;
            case R.id.menuAboutDev:
                aboutDeveloperMenu();
                break;
            case R.id.bmiChartMenuItem:
                aboutBmiChartMenu();
                break;
            case R.id.exitMenuItem:
                exitMenuOption();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void exitMenuOption() {

        dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("Exit Application?");
        dialogBuilder
                .setMessage("Click yes to exit!")
                .setCancelable(false)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                moveTaskToBack(true);
                                android.os.Process.killProcess(android.os.Process.myPid());
                                System.exit(1);
                            }
                        })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

    }

    private void aboutBmiChartMenu() {

        dialogBuilder = new AlertDialog.Builder(this);
        final View  aboutBMIChartMenu = getLayoutInflater().inflate(R.layout.about_bmi_chart, null);
        cross = aboutBMIChartMenu.findViewById(R.id.imageBtnClose);
        btnGotIt = aboutBMIChartMenu.findViewById(R.id.btnGotItBmiChart);
        dialogBuilder.setView(aboutBMIChartMenu);
        alertDialog = dialogBuilder.create();
        alertDialog.show();

        cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
        btnGotIt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
    }

    private void aboutDeveloperMenu() {

        Intent intent = new Intent(this, AboutDev.class);
        startActivity(intent);
//        dialogBuilder = new AlertDialog.Builder(this);
//        final View  aboutDevMenu = getLayoutInflater().inflate(R.layout.about_developer_menu, null);
//        dialogBuilder.setView(aboutDevMenu);
//        alertDialog = dialogBuilder.create();
//        alertDialog.show();
//
//        cross = aboutDevMenu.findViewById(R.id.imageBtnClose);
//
//        cross.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                alertDialog.dismiss();
//            }
//        });

    }

    private void aboutAppMenuOnClick() {

        Intent intent = new Intent(this, AboutApp.class);
        startActivity(intent);
//        dialogBuilder = new AlertDialog.Builder(this);
//        final View  aboutAppMenu = getLayoutInflater().inflate(R.layout.about_app_popup, null);
//        dialogBuilder.setView(aboutAppMenu);
//        alertDialog = dialogBuilder.create();
//        alertDialog.show();
//
//        cross = aboutAppMenu.findViewById(R.id.imageBtnClose);
//        btnGotIt = aboutAppMenu.findViewById(R.id.btnGotIt);
//
//        cross.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                alertDialog.dismiss();
//            }
//        });
//        btnGotIt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                alertDialog.dismiss();
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
                tvGender.setText(" Gender : " + rb_gender.getText().toString());
                tvResultBmi.setText(String.valueOf( " Your BMI value is "+ bmiValue ));
                tvSuggestion.setText(bmiInterpretation);
                break;

            //button clear click event
            case R.id.btnClear:
                tvGender.setText("");
                tvResultBmi.setText("");
                tvSuggestion.setText("");
                etWeight.setText("");
                etHeight.setText("");
                rg_gender.clearCheck();
                btnClear.setVisibility(View.GONE);

                break;
        }
    }
}