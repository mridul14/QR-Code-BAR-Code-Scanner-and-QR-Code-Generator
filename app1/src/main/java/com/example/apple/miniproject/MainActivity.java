package com.example.apple.miniproject;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;

import static com.example.apple.miniproject.ReaderActivity.info;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String PACKAGE="com.example.apple.miniproject";
    private FloatingActionButton fab;
    private Contract contract;
    private Button btn_qr_scan;
    private Button btn_qr_generate;
    private Button btn_bar_scan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       btn_qr_scan = (Button) findViewById(R.id.btn_qr_scan);
        btn_qr_generate = (Button) findViewById(R.id.btn_qr_generate);
        btn_bar_scan = (Button) findViewById(R.id.btn_bar_scan);

        btn_qr_scan.setOnClickListener(this);
        btn_qr_generate.setOnClickListener(this);
        btn_bar_scan.setOnClickListener(this);





    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
         switch (id)
        {

            case  R.id.action_history:
                doactionHistory();
                return  true;
            case  R.id.action_delete:
                dodelete();
                return  true;

        }


        return super.onOptionsItemSelected(item);
    }

    private void dodelete() {
        contract= new Contract(this);
        contract.delete();


            Toast.makeText(this, "history deleted" , Toast.LENGTH_SHORT).show();



        }


    private void doactionHistory() {


       Intent intenthistory= new Intent(this,History.class);
        startActivity(intenthistory);


    }

    private void doactionScan() {
        final Activity activity = this;
        IntentIntegrator integrator = new IntentIntegrator(activity);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        integrator.setPrompt("Scan");
        integrator.setCameraId(0);
        integrator.setBeepEnabled(false);
        integrator.setBarcodeImageEnabled(false);
        integrator.initiateScan();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null){
            if(result.getContents()==null){
                Toast.makeText(this, "You cancelled the scanning", Toast.LENGTH_LONG).show();
            }
            else {

                String results=result.getContents();
                Intent resultintent=new Intent(this,ReaderActivity.class);
                resultintent.putExtra(PACKAGE +".key_result",results);
                startActivity(resultintent);
                //Toast.makeText(this, result.getContents(),Toast.LENGTH_LONG).show();
            }
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);

        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_qr_scan:
                doactionScan();
                return;
            case R.id.btn_qr_generate:
                Intent generate_intent=new Intent(MainActivity.this,QrCodeGenerateActivity.class);
                startActivity(generate_intent);
                return;
            case R.id.btn_bar_scan:
                Intent bar_intent=new Intent(MainActivity.this,BarCodeScanActivity.class);
                startActivity(bar_intent);
                return;
        }

    }
    }
