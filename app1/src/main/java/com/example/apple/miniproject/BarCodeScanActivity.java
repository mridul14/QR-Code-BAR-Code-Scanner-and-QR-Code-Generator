package com.example.apple.miniproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

/**
 * Created by dell on 06-08-2017.
 */

public class BarCodeScanActivity extends AppCompatActivity {
    public static final String PACKAGE = "com.example.apple.miniproject";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IntentIntegrator scanIntegrator = new IntentIntegrator(this);
        scanIntegrator.initiateScan();

    }



    public void onActivityResult(int requestCode, int resultCode, Intent intent) {


        //retrieve scan result
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {
            if (scanningResult.getContents() == null) {
                Toast.makeText(this, "You cancelled the scanning", Toast.LENGTH_LONG).show();
                Intent scanintent = new Intent(BarCodeScanActivity.this, MainActivity.class);
                startActivity(scanintent);
            } else {
                //we have a result
                String scanContent = scanningResult.getContents().concat(scanningResult.getFormatName());
                //String scanFormat = scanningResult.getFormatName();

                Intent resultintent = new Intent(this, ReaderActivity.class);
                resultintent.putExtra(PACKAGE + ".key_result", scanContent);
                // resultintent.putExtra(PACKAGE +".key_result1",scanFormat);
                startActivity(resultintent);

            }

        } else {


        }
    }
}

