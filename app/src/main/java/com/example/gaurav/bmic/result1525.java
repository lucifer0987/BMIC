package com.example.gaurav.bmic;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class result1525 extends AppCompatActivity {

    private int PICK_IMAGE_REQUEST = 1;
    Button button2;
    Button button3;
    Button button4;
    String getName;
    double getWeight;
    double getHeight;
    double getBMR;
    double getBMI;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result1525);


        //sharing option.
        button3 = (Button) findViewById(R.id.share);
        // Capture button clicks
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Name :- "+ getName + "\nHeight :- " + getHeight +"\nWeight :- " + getWeight + "\nBMI :- " +getBMI+"\nBMR :- " +getBMR);
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
        });


        //new activity BMI.
        button2 = (Button) findViewById(R.id.tv6);
        // Capture button clicks
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent myIntent = new Intent(result1525.this, BMI.class);
                startActivity(myIntent);
            }
        });

        //new activity BMR.
        button4 = (Button) findViewById(R.id.tv12);
        // Capture button clicks
        button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent myIntent = new Intent(result1525.this, FAT.class);
                startActivity(myIntent);
            }
        });



        //getting the image.
        ImageButton buttonLoadImage = (ImageButton) findViewById(R.id.camera);
        buttonLoadImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, PICK_IMAGE_REQUEST);
            }
        });


        //imports different datas from previous activity.
        getName = getIntent().getExtras().getString("getName");
        int getAge = getIntent().getExtras().getInt("getAge");
        String getGender = getIntent().getExtras().getString("getGender");
        getWeight = getIntent().getExtras().getDouble("getWeight");
        getHeight = getIntent().getExtras().getDouble("getHeight");
        getBMI = getIntent().getExtras().getDouble("getBMI");
        getBMR = getIntent().getExtras().getDouble("getBMR");
        int wup = getIntent().getExtras().getInt("wup");
        int lup = getIntent().getExtras().getInt("lup");

        //Assigns BMR.
        TextView tv11 = (TextView)findViewById(R.id.tv11);
        tv11.setText(getBMR + "");


        //Assigns Body Fat percentage and colors to it.
        TextView tv13 = (TextView)findViewById(R.id.tv13);
        if(getGender.matches("M")){
            double bf2 = bf(getAge, getBMI, 1);
            double rounded = (double) Math.round(bf2 * 10) / 10;
            tv13.setText(rounded + "");
            if (rounded<=13){
                tv13.setTextColor(Color.parseColor("#11ff00"));
            }else if(rounded>13 && rounded<=24){
                tv13.setTextColor(Color.parseColor("#ffff00"));
            }else{
                tv13.setTextColor(Color.parseColor("#ff0000"));
            }
        }else{
            double bf2 = bf(getAge, getBMI,0);
            double rounded = (double) Math.round(bf2 * 10) / 10;
            tv13.setText(rounded + "");
            if (rounded<=13){
                tv13.setTextColor(Color.parseColor("#11ff00"));
            }else if(rounded>13 && rounded<=24){
                tv13.setTextColor(Color.parseColor("#ffff00"));
            }else{
                tv13.setTextColor(Color.parseColor("#ff0000"));
            }
        }



        // displays different datas to different textviews.
        TextView tv1 = (TextView)findViewById(R.id.tv1);
        tv1.setText(getName + "");

        TextView tv2 = (TextView)findViewById(R.id.tv2);
        tv2.setText(getAge +"");

        TextView tv3 = (TextView)findViewById(R.id.tv3);
        tv3.setText(getGender + "");

        TextView tv7 = (TextView)findViewById(R.id.tv7);
        tv7.setText(getBMI + "");
        if(getBMI<=15 || getBMI>=30){
            tv7.setTextColor(Color.parseColor("#ff0000"));
        }else if(getBMI>15 && getBMI<18.5){
            tv7.setTextColor(Color.parseColor("#ffff00"));
        }else if(getBMI>=25 && getBMI<30){
            tv7.setTextColor(Color.parseColor("#ffff00"));
        }else{
            tv7.setTextColor(Color.parseColor("#11ff00"));
        }

        //assigning idealweight values.
        TextView tv9 =(TextView)findViewById(R.id.tv9);
        double heightm;
        //converting height to metres.
        if (lup==1){
            heightm = getHeight;
        }else if(lup==2){
            heightm = getHeight/100;
        }else{
            heightm = getHeight * 0.0254;
        }
        //displaying idealweight.
        double idealweight = iw(heightm);
        if (wup == 1){
            double rounded = (double) Math.round(idealweight * 10) / 10;
            tv9.setText(rounded + "kg");
        }else{
            double rounded = (double) Math.round(idealweight * 10) / 10;
            tv9.setText(rounded + "lbs");
        }
    }

    //getting the image.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                // Log.d(TAG, String.valueOf(bitmap));

                ImageView imageView = (ImageView) findViewById(R.id.image);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //calculates idealweight.
    private double iw (double height2){
        double first = 25 * height2 * height2;
        return first;
    }

    //calculates body fat %
    private double bf(int age2, double bmi2, int gender2){
        double bf2 = (1.20 * bmi2) + (0.23 * age2);
        double bf3 = (10.8 * gender2);
        double bf4= (bf2 - bf3) - 5.4;
        return bf4;
    }

}
