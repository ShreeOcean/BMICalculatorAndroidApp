package nic.ocean.bmicalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import nic.ocean.bmicalculator.databinding.AboutAppPopupBinding;
import nic.ocean.bmicalculator.databinding.ActivityMenuContactUsBinding;
import nic.ocean.bmicalculator.databinding.CustomSuggestionDialogBinding;

public class MenuContactUs extends AppCompatActivity implements View.OnClickListener{

    private ActivityMenuContactUsBinding menuContactUsBinding;
    private CustomSuggestionDialogBinding suggestionDialogBinding;
    Context context;
    boolean doubleBackToExitPressedOnce = false;
    private AlertDialog.Builder dialogBuilder;
    private String[] PERMISSION = new String[]{Manifest.permission.CALL_PHONE};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        menuContactUsBinding = ActivityMenuContactUsBinding.inflate(getLayoutInflater());
        setContentView(menuContactUsBinding.getRoot());
        context = this;

        menuContactUsBinding.btnCall.setOnClickListener(this);
        menuContactUsBinding.btnDial.setOnClickListener(this);
        menuContactUsBinding.btnEmail.setOnClickListener(this);
        menuContactUsBinding.btnSuggestion.setOnClickListener(this);
//        suggestionDialogBinding.btnCancelSuggest.setOnClickListener(this);
//        suggestionDialogBinding.btnSubmitSuggest.setOnClickListener(this);
    }

    //double back press to exit
//    @Override
//    public void onBackPressed() {
//        if (doubleBackToExitPressedOnce) {
//            super.onBackPressed();
//            return;
//        }
//
//        this.doubleBackToExitPressedOnce = true;
//        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
//
//        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
//
//            @Override
//            public void run() {
//                doubleBackToExitPressedOnce=false;
//            }
//        }, 2000);
//    }

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
            case R.id.homeMenu:
                Intent intentHome = new Intent(MenuContactUs.this, MainActivity.class);
                startActivity(intentHome);
                break;
            case R.id.aboutAppMenuItem:
                Intent intentAboutApp = new Intent(MenuContactUs.this, AboutApp.class);
                startActivity(intentAboutApp);
                break;
            case R.id.menuAboutDev:
                Intent intentAboutDev = new Intent(MenuContactUs.this, AboutDev.class);
                startActivity(intentAboutDev);
                break;
            case R.id.bmiChartMenuItem:
                Intent intentbmichart = new Intent(MenuContactUs.this, AboutBMIChart.class);
                startActivity(intentbmichart);
                break;
            case R.id.menuContactUs:
                Intent intentConUs = new Intent(MenuContactUs.this,MenuContactUs.class);
                startActivity(intentConUs);
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnDial:
                Intent intentDial = new Intent(Intent.ACTION_DIAL);
                intentDial.setData(Uri.parse("tel:8093478746"));
                startActivity(intentDial);
                break;
            case R.id.btnCall:
                if (ContextCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                    doCall();

                } else {
                    ActivityCompat.requestPermissions(MenuContactUs.this, PERMISSION, 1);
                }
                break;
            case R.id.btnEmail:
                Intent intentEmail = new Intent(Intent.ACTION_SEND);
                String[] emails_in_to={"pragyan9423@gmail.com"};
                intentEmail.putExtra(Intent.EXTRA_EMAIL, emails_in_to );
                intentEmail.putExtra(Intent.EXTRA_SUBJECT,"Todo Email's subject");
                intentEmail.putExtra(Intent.EXTRA_TEXT, "Todo Email's predefined Body");
                intentEmail.putExtra(Intent.EXTRA_CC,"shree.pragyan23@gmail.com");
                intentEmail.setType("text/html");
                intentEmail.setPackage("com.google.android.gm");
                startActivity(intentEmail);
                break;
            case R.id.btnSuggestion:
                AlertDialog.Builder builder = new AlertDialog.Builder(MenuContactUs.this);
                ViewGroup viewGroup = findViewById(android.R.id.content);
                View dialogView = LayoutInflater.from(view.getContext()).inflate(R.layout.custom_suggestion_dialog, viewGroup, false);
                builder.setView(dialogView);
                AlertDialog  alertDialog = builder.create();
                alertDialog.show();
                break;

        }
    }
    private void doCall() {
        Intent intentCall = new Intent(Intent.ACTION_CALL, Uri.parse("tel:8093478746"));
        startActivity(intentCall);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                doCall();

            }else {
                Toast.makeText(context, "Permission Denied!!!", Toast.LENGTH_LONG).show();
            }
        }
    }

}
