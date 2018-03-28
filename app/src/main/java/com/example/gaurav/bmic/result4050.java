package com.example.gaurav.bmic;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.icu.text.DecimalFormat;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

public class result4050 extends AppCompatActivity {

    private int PICK_IMAGE_REQUEST = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result4050);

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
        String getName = getIntent().getExtras().getString("getName");
        int getAge = getIntent().getExtras().getInt("getAge");
        String getGender = getIntent().getExtras().getString("getGender");
        double getWeight = getIntent().getExtras().getDouble("getWeight");
        double getHeight = getIntent().getExtras().getDouble("getHeight");
        double getBMI = getIntent().getExtras().getDouble("getBMI");
        int wup = getIntent().getExtras().getInt("wup");
        int lup = getIntent().getExtras().getInt("lup");




        // displays different datas to different textviews.
        TextView tv1 = (TextView)findViewById(R.id.tv1);
        tv1.setText(getName + "");

        TextView tv2 = (TextView)findViewById(R.id.tv2);
        tv2.setText(getAge +"");

        TextView tv3 = (TextView)findViewById(R.id.tv3);
        tv3.setText(getGender + "");

        TextView tv7 = (TextView)findViewById(R.id.tv7);
        tv7.setText(getBMI + "");
        if(getBMI<=17 || getBMI>=29){
            tv7.setTextColor(Color.parseColor("#ff0000"));
        }else if(getBMI>17 && getBMI<=20){
            tv7.setTextColor(Color.parseColor("#ffff00"));
        }else if(getBMI>25 && getBMI<29){
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
            DecimalFormat df = new DecimalFormat("###.#");
            tv9.setText(df.format(idealweight) + "kg");
        }else{
            DecimalFormat df = new DecimalFormat("###.#");
            tv9.setText(df.format(idealweight/0.453592) + "lbs");
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
        double first = 24 * height2 * height2;
        return first;
    }

}

