package com.labeeb.funprimetechnology.Splash_Screen;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.formats.MediaView;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.gms.ads.formats.UnifiedNativeAdView;


//
//import com.google.android.ads.nativetemplates.NativeTemplateStyle;
//import com.google.android.ads.nativetemplates.TemplateView;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.nativead.NativeAd;
import com.labeeb.funprimetechnology.Home_Screen;
import com.labeeb.funprimetechnology.R;
import com.labeeb.funprimetechnology.privacy_policy;

public class Splash_Screen extends AppCompatActivity {

    SharedPreferences sharedPreferences,getSharedPreferences;
    SharedPreferences.Editor sharedPreferencesEditor;

    public static final String MY_PREFS_NAME = "MyPrefsFile";
    AlertDialog.Builder builder;
    Context context;



    /**Duration of Splash_Screen**/
    public static int SPLASH_DISPLAY_LENGTH = 4000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash__screen);


        MobileAds.initialize(this);

        AdLoader adLoader = new AdLoader.Builder(this, "ca-app-pub-3940256099942544/2247696110")
                .forUnifiedNativeAd(new UnifiedNativeAd.OnUnifiedNativeAdLoadedListener() {
                    @Override
                    public void onUnifiedNativeAdLoaded(UnifiedNativeAd unifiedNativeAd) {
                        //the native ad will be available inside this method  (unifiedNativeAd)

                        UnifiedNativeAdView unifiedNativeAdView = (UnifiedNativeAdView) getLayoutInflater().inflate(R.layout.native_add, null);
                        mapUnifiedNativeAdToLayout(unifiedNativeAd, unifiedNativeAdView);

                        FrameLayout nativeAdLayout = findViewById(R.id.id_native_ads);
                        nativeAdLayout.removeAllViews();
                        nativeAdLayout.addView(unifiedNativeAdView);
                    }
                })
                .build();
        adLoader.loadAd(new AdRequest.Builder().build());



        getSharedPreferences = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        sharedPreferencesEditor = getSharedPreferences.edit();

        builder = new AlertDialog.Builder(this);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (getSharedPreferences.getBoolean("privacyPolicyCh", false)) {

                    Intent intent = new Intent(getApplicationContext(), Home_Screen.class);
                    Splash_Screen.this.startActivity(intent);
                    finish();

                } else {

                    Intent intent = new Intent(getApplicationContext(), privacy_policy.class);
                    Splash_Screen.this.startActivity(intent);
                    finish();

                }

            }


        }, SPLASH_DISPLAY_LENGTH);


        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });


    }


    public void mapUnifiedNativeAdToLayout(UnifiedNativeAd adFromGoogle, UnifiedNativeAdView myAdView) {
        MediaView mediaView = myAdView.findViewById(R.id.ad_media);
        myAdView.setMediaView(mediaView);

        myAdView.setHeadlineView(myAdView.findViewById(R.id.ad_headline));
        myAdView.setBodyView(myAdView.findViewById(R.id.ad_body));
        myAdView.setCallToActionView(myAdView.findViewById(R.id.ad_call_to_action));
        myAdView.setIconView(myAdView.findViewById(R.id.ad_icon));
        myAdView.setPriceView(myAdView.findViewById(R.id.ad_price));
        myAdView.setStarRatingView(myAdView.findViewById(R.id.ad_rating));
        myAdView.setStoreView(myAdView.findViewById(R.id.ad_store));
        myAdView.setAdvertiserView(myAdView.findViewById(R.id.ad_advertiser));

        ((TextView) myAdView.getHeadlineView()).setText(adFromGoogle.getHeadline());

        if (adFromGoogle.getBody() == null) {
            myAdView.getBodyView().setVisibility(View.GONE);
        } else {
            ((TextView) myAdView.getBodyView()).setText(adFromGoogle.getBody());
        }

        if (adFromGoogle.getCallToAction() == null) {
            myAdView.getCallToActionView().setVisibility(View.GONE);
        } else {
            ((Button) myAdView.getCallToActionView()).setText(adFromGoogle.getCallToAction());
        }

        if (adFromGoogle.getIcon() == null) {
            myAdView.getIconView().setVisibility(View.GONE);
        } else {
            ((ImageView) myAdView.getIconView()).setImageDrawable(adFromGoogle.getIcon().getDrawable());
        }

        if (adFromGoogle.getPrice() == null) {
            myAdView.getPriceView().setVisibility(View.GONE);
        } else {
            ((TextView) myAdView.getPriceView()).setText(adFromGoogle.getPrice());
        }

        if (adFromGoogle.getStarRating() == null) {
            myAdView.getStarRatingView().setVisibility(View.GONE);
        } else {
            ((RatingBar) myAdView.getStarRatingView()).setRating(adFromGoogle.getStarRating().floatValue());
        }

        if (adFromGoogle.getStore() == null) {
            myAdView.getStoreView().setVisibility(View.GONE);
        } else {
            ((TextView) myAdView.getStoreView()).setText(adFromGoogle.getStore());
        }

        if (adFromGoogle.getAdvertiser() == null) {
            myAdView.getAdvertiserView().setVisibility(View.GONE);
        } else {
            ((TextView) myAdView.getAdvertiserView()).setText(adFromGoogle.getAdvertiser());
        }

        myAdView.setNativeAd(adFromGoogle);
    }

}