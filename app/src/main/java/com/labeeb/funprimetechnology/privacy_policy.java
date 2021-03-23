package com.labeeb.funprimetechnology;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;

public class privacy_policy extends AppCompatActivity {


    Button confrimButton, cancelButton;
    CheckBox checkBox;


    SharedPreferences sharedPreferences,getSharedPreferences;
    SharedPreferences.Editor sharedPreferencesEditor;

    public static final String MY_PREFS_NAME = "MyPrefsFile";
    protected String checkboxString;
    private InterstitialAd interstitialAds;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy);


        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {}
        });


        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(this,"ca-app-pub-3940256099942544/1033173712", adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                // The mInterstitialAd reference will be null until
                // an ad is loaded.
                interstitialAds = interstitialAd;
                Log.i("AD Mob FunPrime", "onAdLoaded");
                interstitialAds.show(privacy_policy.this);
                interstitialAds.setFullScreenContentCallback(new FullScreenContentCallback(){
                    @Override
                    public void onAdDismissedFullScreenContent() {
                        // Called when fullscreen content is dismissed.
                        Log.d("AD mob by FunPrime", "The ad was dismissed.");
                    }

                    @Override
                    public void onAdFailedToShowFullScreenContent(AdError adError) {
                        // Called when fullscreen content failed to show.
                        Log.d("AdMob by Funprime", "The ad failed to show.");
                    }

                    @Override
                    public void onAdShowedFullScreenContent() {
                        // Called when fullscreen content is shown.
                        // Make sure to set your reference to null so you don't
                        // show it a second time.
                        interstitialAds = null;
                        Log.d("AdMob by Funprime", "The ad was shown.");
                    }
                });
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                // Handle the error
                Log.i("AD Mob FunPrime", loadAdError.getMessage());
                interstitialAds = null;
            }
        });



        confrimButton = findViewById(R.id.confrimButton);
        cancelButton = findViewById(R.id.cancelButton);
        checkBox = findViewById(R.id.checkBox);


        getSharedPreferences=getSharedPreferences(MY_PREFS_NAME,MODE_PRIVATE);
        sharedPreferencesEditor=getSharedPreferences.edit();



        confrimButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkboxString = checkBox.getText().toString();

                if (checkBox.isChecked()) {
sharedPreferencesEditor.putBoolean("privacyPolicyCh",true).apply();
                    Intent intent = new Intent(getApplicationContext(), Home_Screen.class);
                    startActivity(intent);
                    finish();
                }

                else {
                    Toast.makeText(privacy_policy.this, "Please fill the checkbox", Toast.LENGTH_SHORT).show();


                }




            }
        });




        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishActivity(0);
                System.exit(0);
            }
        });





    }
}