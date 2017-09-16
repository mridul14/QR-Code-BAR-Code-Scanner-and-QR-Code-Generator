package com.example.apple.miniproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ReaderActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvscan;


    private Button btnSave;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    public static final String info="com.example.apple.miniproject";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reader);
        tvscan = (TextView) findViewById(R.id.tvscan);
        btnSave = (Button) findViewById(R.id.btnSave);


        btnSave.setOnClickListener(this);


        if(getIntent()!=null){
            Bundle extras=getIntent().getExtras();
            if(extras!=null){
                updateUI(extras);
            }
        }
    }

    private void updateUI(Bundle extras) {
        tvscan.setText(extras.getString(MainActivity.PACKAGE +".key_result"));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnSave:
                saveHistory();

             Intent saveintent= new Intent(this,MainActivity.class);

                  startActivity(saveintent);



                return;



        }

    }

    private void saveHistory() {
        Contract contract = new Contract(this);
        String name = tvscan.getText().toString().trim();
        long response = contract.add(name);

        if (response != -1) {

            Toast.makeText(this, "History Saved", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "there has been a mistake", Toast.LENGTH_SHORT).show();


        }
    }
}
