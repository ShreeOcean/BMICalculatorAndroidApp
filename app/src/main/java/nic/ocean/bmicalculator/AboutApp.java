package nic.ocean.bmicalculator;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import nic.ocean.bmicalculator.databinding.AboutAppPopupBinding;

public class AboutApp extends AppCompatActivity {

    private AboutAppPopupBinding bindingAboutApp;
    Context context;
    boolean doubleBackToExitPressedOnce = false;
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog alertDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bindingAboutApp = AboutAppPopupBinding.inflate(getLayoutInflater());
        setContentView(bindingAboutApp.getRoot());
        context = this;


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
                Intent intentHome = new Intent(AboutApp.this, MainActivity.class);
                startActivity(intentHome);
                break;
            case R.id.aboutAppMenuItem:
                Toast.makeText(context, "This is About App", Toast.LENGTH_SHORT).show();
                bindingAboutApp.btnGotIt.setOnClickListener(view -> {Intent intent = new Intent(AboutApp.this,MainActivity.class);});
                break;
            case R.id.menuAboutDev:
                Intent intentAboutDev = new Intent(AboutApp.this, AboutDev.class);
                startActivity(intentAboutDev);
                break;
            case R.id.bmiChartMenuItem:
                Intent intentbmichart = new Intent(AboutApp.this, AboutBMIChart.class);
                startActivity(intentbmichart);
                break;
            case R.id.menuContactUs:
                Intent intentConUs = new Intent(AboutApp.this, MenuContactUs.class);
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

}
