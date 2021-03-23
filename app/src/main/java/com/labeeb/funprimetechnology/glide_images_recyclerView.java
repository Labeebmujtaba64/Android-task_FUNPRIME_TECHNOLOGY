package com.labeeb.funprimetechnology;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.transition.Transition;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static android.bluetooth.BluetoothGattCharacteristic.PERMISSION_WRITE;

public class glide_images_recyclerView extends AppCompatActivity {


    Date currentTime;
    ImageView glide1stImage,glide2ndImage;
    ProgressDialog ProgressDialog,progressDialog;
    Bitmap bitmap;

    private InterstitialAd interstitialAds;


    public static final int PERMISSION_WRITE = 0;
    String url = "https://storage.googleapis.com/twg-content/original_images/2005-Google_Think_Privacy-and-Trust.gif";

    public static final int WRITE_EXTERNAL_STORAGE_CODE=1;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide_images_recycler_view);


        currentTime = Calendar.getInstance().getTime();

        String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());



        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {}
        });




        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)==
            PackageManager.PERMISSION_DENIED){

                String[] permission = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                 requestPermissions(permission,WRITE_EXTERNAL_STORAGE_CODE);
            }


        }





        glide1stImage = findViewById(R.id.glide1stImage);
        glide2ndImage = findViewById(R.id.glide2ndImage);


        Glide.with(this)
                .load("https://media.istockphoto.com/photos/pakistan-monument-islamabad-picture-id535695503?k=6&m=535695503&s=612x612&w=0&h=uP8aDK4xlfjk3kEiyr9wwUiuh80UwAiICweFpiBDosk=")
                .into(glide1stImage);
        checkPermission();


        Glide.with(this)
                .load("https://lh3.googleusercontent.com/X11OiHcMTa4FRDa2haLN3x4X5FCH-UOAUEq8c6PQKzzhald2XwWmBtww41FQVpp-oa73yicBJ49yaqA8d2cHNW4tG6o2Wp_XMVn3aw")
                .into(glide2ndImage);




        glide1stImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadImage("https://media.istockphoto.com/photos/pakistan-monument-islamabad-picture-id535695503?k=6&m=535695503&s=612x612&w=0&h=uP8aDK4xlfjk3kEiyr9wwUiuh80UwAiICweFpiBDosk=");
                //Show Ads
                AdRequest adRequest = new AdRequest.Builder().build();

                InterstitialAd.load(glide_images_recyclerView.this,"ca-app-pub-3940256099942544/1033173712", adRequest, new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        interstitialAds = interstitialAd;
                        Log.i("AD Mob FunPrime", "onAdLoaded");
                        interstitialAds.show(glide_images_recyclerView.this);
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













            }
        });
        glide2ndImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadImage("https://lh3.googleusercontent.com/X11OiHcMTa4FRDa2haLN3x4X5FCH-UOAUEq8c6PQKzzhald2XwWmBtww41FQVpp-oa73yicBJ49yaqA8d2cHNW4tG6o2Wp_XMVn3aw");
                //Show Ads
                AdRequest adRequest = new AdRequest.Builder().build();

                InterstitialAd.load(glide_images_recyclerView.this,"ca-app-pub-3940256099942544/1033173712", adRequest, new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        interstitialAds = interstitialAd;
                        Log.i("AD Mob FunPrime", "onAdLoaded");
                        interstitialAds.show(glide_images_recyclerView.this);
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










            }
        });


    }


    public class Downloading extends AsyncTask<String, Integer, String> {

        @Override
        public void onPreExecute() {
            super .onPreExecute();
            progressDialog.setMessage("Please wait");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        protected String doInBackground(String... url) {
            File mydir = new File(Environment.getExternalStorageDirectory() + "/11zon");
            if (!mydir.exists()) {
                mydir.mkdirs();
            }

            DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
            Uri downloadUri = Uri.parse(url[0]);
            DownloadManager.Request request = new DownloadManager.Request(downloadUri);

            SimpleDateFormat dateFormat = new SimpleDateFormat("mmddyyyyhhmmss");
            String date = dateFormat.format(new Date());

            request.setAllowedNetworkTypes(
                    DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE)
                    .setAllowedOverRoaming(false)
                    .setTitle("Downloading")
                    .setDestinationInExternalPublicDir("/11zon", date + ".jpg");

            manager.enqueue(request);
            return mydir.getAbsolutePath() + File.separator + date + ".jpg";
        }

        @Override
        public void onPostExecute(String s) {
            super .onPostExecute(s);
            progressDialog.dismiss();
            Toast.makeText(getApplicationContext(), "Image Saved", Toast.LENGTH_SHORT).show();
        }
    }

    //runtime storage permission
    public boolean checkPermission() {
        int READ_EXTERNAL_PERMISSION = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        if((READ_EXTERNAL_PERMISSION != PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_WRITE);
            return false;
        }
        return true;
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==PERMISSION_WRITE && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            //do somethings
        }
    }


    private void checkselfpermission (String writeexternalstorage){
    }

    void downloadImage(String imageURL){

        if (!verifyPermissions()) {
            return;
        }

        String dirPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + getString(R.string.app_name) + "/";

        final File dir = new File(dirPath);

        final String fileName = imageURL.substring(imageURL.lastIndexOf('/') + 1);

        Glide.with(this)
                .load(imageURL)
                .into(new CustomTarget<Drawable>() {


                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {

                    }

                    @Override
                    public void onLoadFailed(@Nullable Drawable errorDrawable) {
                        super.onLoadFailed(errorDrawable);

                        Toast.makeText(glide_images_recyclerView.this, "Failed to Download Image! Please try again later.", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable com.bumptech.glide.request.transition.Transition<? super Drawable> transition) {
                        Bitmap bitmap = ((BitmapDrawable)resource).getBitmap();
                        Toast.makeText(glide_images_recyclerView.this, "Saving Image...", Toast.LENGTH_SHORT).show();
                        saveImage(bitmap, dir, fileName);
                    }
                });

    }
    public Boolean verifyPermissions() {

        // This will return the current Status
        int permissionExternalMemory = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permissionExternalMemory != PackageManager.PERMISSION_GRANTED) {

            String[] STORAGE_PERMISSIONS = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
            // If permission not granted then ask for permission real time.
            ActivityCompat.requestPermissions(this, STORAGE_PERMISSIONS, 1);
            return false;
        }

        return true;

    }
    private void saveImage(Bitmap image, File storageDir, String imageFileName) {

        boolean successDirCreated = false;
        if (!storageDir.exists()) {
            successDirCreated = storageDir.mkdir();
        }
        if (successDirCreated) {
            File imageFile = new File(storageDir, imageFileName);
            String savedImagePath = imageFile.getAbsolutePath();
            try {
                OutputStream fOut = new FileOutputStream(imageFile);
                image.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
                fOut.close();
                Toast.makeText(glide_images_recyclerView.this, "Image Saved!", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(glide_images_recyclerView.this, "Error while saving image!", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }

        }else{
            Toast.makeText(this, "Failed to make folder!", Toast.LENGTH_SHORT).show();
        }
    }

}