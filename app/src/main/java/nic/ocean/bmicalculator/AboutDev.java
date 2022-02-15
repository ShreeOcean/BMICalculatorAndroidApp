package nic.ocean.bmicalculator;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import nic.ocean.bmicalculator.databinding.AboutAppPopupBinding;
import nic.ocean.bmicalculator.databinding.AboutDeveloperMenuBinding;

public class AboutDev extends AppCompatActivity {

    private AboutDeveloperMenuBinding bindingAboutDev;
    Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bindingAboutDev = AboutDeveloperMenuBinding.inflate(getLayoutInflater());
        setContentView(bindingAboutDev.getRoot());
        context = this;


    }
}
