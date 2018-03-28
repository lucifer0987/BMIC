package com.example.gaurav.bmic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class main extends AppCompatActivity {

    Spinner weightSpinner;
    Spinner lengthSpinner;
    int wup;
    int lup;
    Button button2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //creating a custom toolbar.
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.my_Title);
        setSupportActionBar(toolbar);



        /**
         * creating the units spinner for weight.
         */
        weightSpinner = (Spinner) findViewById(R.id.weight_units_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterweight = ArrayAdapter.createFromResource(this,
                R.array.weight_units, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapterweight.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        weightSpinner.setAdapter(adapterweight);

        //on item selection listener.
        weightSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                wup = weightSpinner.getSelectedItemPosition() + 1 ;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        /**
         * creating units spinner for length.
         */
        lengthSpinner = (Spinner) findViewById(R.id.length_units_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterlength = ArrayAdapter.createFromResource(this,
                R.array.length_units, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapterlength.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        lengthSpinner.setAdapter(adapterlength);

        //on item selection listener.
        lengthSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                lup = lengthSpinner.getSelectedItemPosition() + 1 ;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        /**
         * starting next activity on button click.
         */
        // Locate the button in activity_main.xml
        button2 = (Button) findViewById(R.id.BMI);
        // Capture button clicks
        button2.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {

                //get name value.
                EditText edit = (EditText) findViewById(R.id.name);
                String name = edit.getText().toString();

                //get age value.
                EditText edit1 = (EditText)findViewById(R.id.age);
                int age = Integer.parseInt(edit1.getText().toString());

                //get gender value.
                EditText edit2 = (EditText)findViewById(R.id.gender);
                String gender = edit2.getText().toString();

                //get weight value.
                EditText edit3 = (EditText)findViewById(R.id.weight1);
                double weight = Double.parseDouble(edit3.getText().toString());

                //get height value.
                EditText edit4 = (EditText)findViewById(R.id.height1);
                double height = Double.parseDouble(edit4.getText().toString());

                //get BMI value.
                double BMI = calculateBMI(weight,height);

                //get BMR value.
                double BMR = calculateBMR(weight, height, age,gender);

                // Start NewActivity.class
                if(age<=25){
                    Intent myIntent = new Intent(main.this, result1525.class);
                    myIntent.putExtra("getName", name);
                    myIntent.putExtra("getAge",age);
                    myIntent.putExtra("getGender", gender);
                    myIntent.putExtra("getWeight", weight);
                    myIntent.putExtra("getHeight", height);
                    myIntent.putExtra("getBMI", BMI);
                    myIntent.putExtra("getBMR", BMR);
                    myIntent.putExtra("wup", wup);
                    myIntent.putExtra("lup",lup);
                    startActivity(myIntent);
                }else if(age>25 && age<=40){
                    Intent myIntent = new Intent(main.this, result2540.class);
                    myIntent.putExtra("getName", name);
                    myIntent.putExtra("getAge",age);
                    myIntent.putExtra("getGender", gender);
                    myIntent.putExtra("getWeight", weight);
                    myIntent.putExtra("getHeight", height);
                    myIntent.putExtra("getBMI", BMI);
                    myIntent.putExtra("getBMR", BMR);
                    myIntent.putExtra("wup", wup);
                    myIntent.putExtra("lup",lup);
                    startActivity(myIntent);
                }else{
                    Intent myIntent = new Intent(main.this, result4050.class);
                    myIntent.putExtra("getName", name);
                    myIntent.putExtra("getAge",age);
                    myIntent.putExtra("getGender", gender);
                    myIntent.putExtra("getWeight", weight);
                    myIntent.putExtra("getHeight", height);
                    myIntent.putExtra("getBMI", BMI);
                    myIntent.putExtra("getBMR", BMR);
                    myIntent.putExtra("wup", wup);
                    myIntent.putExtra("lup",lup);
                    startActivity(myIntent);
                }

            }
        });
    }

    //It calculates the BMI.
    public double calculateBMI(double weight, double height){
        if (wup==1 & lup==1){
           double first = weight / height;
           double second = first / height;
           double rounded = (double) Math.round(second * 10) / 10;
           return (rounded);
        }else if(wup==1 & lup==2){
            double first = height / 100;
            double second = weight / first;
            double third = second / first;
            double rounded = (double) Math.round(third * 10) / 10;
            return (rounded);
        }else if(wup==1 & lup==3) {
            double first = height * 0.0254;
            double second = weight / first;
            double third = second / first;
            double rounded = (double) Math.round(third * 10) / 10;
            return (rounded);
        }else if(wup==2 & lup==1){
            double first = weight * 0.453592;
            double second = first / height;
            double third = second / height;
            double rounded = (double) Math.round(third * 10) / 10;
            return (rounded);
        }else if(wup==2 & lup==2){
            double first = weight * 0.453592;
            double first2 = height / 100;
            double second = first / first2;
            double third = second / first2;
            double rounded = (double) Math.round(third * 10) / 10;
            return (rounded);
        }else if(wup==2 & lup==3){
            double first = weight * 0.453592;
            double first2 = height *0.0254;
            double second = first / first2;
            double third = second / first2;
            double rounded = (double) Math.round(third * 10) / 10;
            return (rounded);
        }else{
            return weight;
        }
    }


    //It resets all the fields in form.
    public void Reset (View view){
        ((EditText) findViewById(R.id.name)).getText().clear();
        ((EditText) findViewById(R.id.age)).getText().clear();
        ((EditText) findViewById(R.id.gender)).getText().clear();
        ((EditText) findViewById(R.id.weight1)).getText().clear();
        ((EditText) findViewById(R.id.height1)).getText().clear();
    }

    //BMR calculator.
    //It calculates the BMI.
    public double calculateBMR(double weight, double height, int age, String gender){
        if (gender.contains("M")){
            if (wup==1 & lup==1){
                double first = height*100;
                double second = (10*weight) + (6.25*first)-(5*age) + 5;
                double rounded = (double) Math.round(second * 10) / 10;
                return (rounded);
            }else if(wup==1 & lup==2){
                double first = height;
                double second = (10*weight) + (6.25*first)-(5*age) + 5;
                double rounded = (double) Math.round(second * 10) / 10;
                return (rounded);
            }else if(wup==1 & lup==3) {
                double first = (height * 0.0254)*100;
                double second = (10*weight) + (6.25*first)-(5*age) + 5;
                double rounded = (double) Math.round(second * 10) / 10;
                return (rounded);
            }else if(wup==2 & lup==1){
                double first = weight * 0.453592;
                double second = height*100;
                double third = (10*first) + (6.25*second)-(5*age) + 5;
                double rounded = (double) Math.round(third * 10) / 10;
                return (rounded);
            }else if(wup==2 & lup==2){
                double first = weight * 0.453592;
                double first2 = height;
                double third = (10*first) + (6.25*first2)-(5*age) + 5;
                double rounded = (double) Math.round(third * 10) / 10;
                return (rounded);
            }else if(wup==2 & lup==3){
                double first = weight * 0.453592;
                double first2 = (height *0.0254)*100;
                double third = (10*first) + (6.25*first2)-(5*age) + 5;
                double rounded = (double) Math.round(third * 10) / 10;
                return (rounded);
            }else{
                return weight;
            }

        }else{
            if (wup==1 & lup==1){
                double first = height*100;
                double second = (10*weight) + (6.25*first)-(5*age) -161;
                double rounded = (double) Math.round(second * 10) / 10;
                return (rounded);
            }else if(wup==1 & lup==2){
                double first = height;
                double second = (10*weight) + (6.25*first)-(5*age) -161;
                double rounded = (double) Math.round(second * 10) / 10;
                return (rounded);
            }else if(wup==1 & lup==3) {
                double first = (height * 0.0254)*100;
                double second = (10*weight) + (6.25*first)-(5*age) -161;
                double rounded = (double) Math.round(second * 10) / 10;
                return (rounded);
            }else if(wup==2 & lup==1){
                double first = weight * 0.453592;
                double second = height*100;
                double third = (10*first) + (6.25*second)-(5*age) -161;
                double rounded = (double) Math.round(third * 10) / 10;
                return (rounded);
            }else if(wup==2 & lup==2){
                double first = weight * 0.453592;
                double first2 = height;
                double third = (10*first) + (6.25*first2)-(5*age) -161;
                double rounded = (double) Math.round(third * 10) / 10;
                return (rounded);
            }else if(wup==2 & lup==3){
                double first = weight * 0.453592;
                double first2 = (height *0.0254)*100;
                double third = (10*first) + (6.25*first2)-(5*age) -161;
                double rounded = (double) Math.round(third * 10) / 10;
                return (rounded);
            }else{
                return weight;
            }
        }

    }

}
