package com.labeeb.funprimetechnology;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.onesignal.OneSignal;

import java.util.ArrayList;
import java.util.Arrays;

public class Home_Screen extends AppCompatActivity {

    private static final String ONESIGNAL_APP_ID = "8d088bc5-64a8-4aec-a5de-12eae4e8e1b1";


    TextView headertextview;

    FirebaseFirestore firebaseFirestore;
    Button addMoreImagesButton;



    private InterstitialAd interstitialAds;




    ArrayList arrayList = new ArrayList<>(Arrays.asList(R.drawable.funpime_logo, R.drawable.flutter1,
            R.drawable.flutter2, R.drawable.flutter3,
            R.drawable.funpime_logo, R.drawable.ic_launcher_background,
            R.drawable.funpime_logo, R.drawable.ic_launcher_background,
            R.drawable.funpime_logo, R.drawable.ic_launcher_background));


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home__screen);


        // Enable verbose OneSignal logging to debug issues if needed.
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);
        // OneSignal Initialization
        OneSignal.initWithContext(this);
        OneSignal.setAppId(ONESIGNAL_APP_ID);




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
                interstitialAds.show(Home_Screen.this);
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





        headertextview = findViewById(R.id.headertextview);
        addMoreImagesButton = findViewById(R.id.addMoreImagesButton);



        FirebaseFirestore db = FirebaseFirestore.getInstance();
db.collection("name").document("eq5Yk7shGLypZerg1m5T").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
    @Override
    public void onSuccess(DocumentSnapshot documentSnapshot) {
        headertextview.setText(documentSnapshot.getString("name"));
    }
}).addOnFailureListener(new OnFailureListener() {
    @Override
    public void onFailure(@NonNull Exception e) {
        Toast.makeText(Home_Screen.this, e.getMessage(), Toast.LENGTH_SHORT).show();
    }
});



addMoreImagesButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getApplicationContext(), glide_images_recyclerView.class);
        startActivity(intent);
    }
});








        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.homerecyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),2);
        recyclerView.setLayoutManager(gridLayoutManager);

        Home_Adapter home_adapter = new Home_Adapter(Home_Screen.this,arrayList);
        recyclerView.setAdapter(home_adapter);


    }
}