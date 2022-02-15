package nic.ocean.bmicalculator;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import nic.ocean.bmicalculator.databinding.AboutAppPopupBinding;

public class AboutApp extends AppCompatActivity {

    private AboutAppPopupBinding bindingAboutApp;
    Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bindingAboutApp = AboutAppPopupBinding.inflate(getLayoutInflater());
        setContentView(bindingAboutApp.getRoot());
        context = this;


    }
}
