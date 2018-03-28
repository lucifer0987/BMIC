package com.example.gaurav.bmic;

        import android.content.Intent;
        import android.os.Bundle;
        import android.os.Handler;
        import android.support.v7.app.AppCompatActivity;
        import android.view.View;

        import com.podcopic.animationlib.library.AnimationType;
        import com.podcopic.animationlib.library.StartSmartAnimation;

public class MainActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        new Handler().postDelayed(new Runnable() {
            public void run() {
                findViewById(R.id.logo).setVisibility(View.VISIBLE);
            }
        }, 500);

        //It adds animation to the logo.
        StartSmartAnimation.startAnimation( findViewById(R.id.logo) , AnimationType.DropOut , 2000 , 100 , true );

        //it makes the i visible after 2 seconds.

        new Handler().postDelayed(new Runnable() {
            public void run() {
                findViewById(R.id.i).setVisibility(View.VISIBLE);
            }
        }, 1500);

        //It adds animation to the i text.
        StartSmartAnimation.startAnimation( findViewById(R.id.i) , AnimationType.Tada , 2000 , 1000 , true );


        new Handler().postDelayed(new Runnable() {
            public void run() {
                findViewById(R.id.app).setVisibility(View.VISIBLE);
            }
        }, 1300);

        // it adds animation to the app text.
        StartSmartAnimation.startAnimation( findViewById(R.id.app) , AnimationType.SlideInLeft , 2000 , 800 , false );


        new Handler().postDelayed(new Runnable() {
            public void run() {
                findViewById(R.id.holic).setVisibility(View.VISIBLE);
            }
        }, 1300);

        //It adds animation to the holic text.
        StartSmartAnimation.startAnimation( findViewById(R.id.holic) , AnimationType.SlideInRight , 2000 , 800 , false );

        //For splash screen.

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                Intent homeIntent = new Intent(MainActivity.this, onboarding.class);
                startActivity(homeIntent);
                finish();
            }
        },SPLASH_TIME_OUT);

    }

}

