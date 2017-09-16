package com.example.apple.miniproject;

import android.graphics.Bitmap;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.R.attr.bitmap;

public class QrCodeGenerateActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etCode;
    private Button btnGenerator;
    private ImageView ivQr;
    private Button btnDownloader;
    private String Textvalue;
    private Bitmap bitmap;
    public final static int QRcodeWidth = 500;
    private EditText etSaveImage;
    private boolean success=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_code_generate);

        etCode = (EditText) findViewById(R.id.etCode);
        btnGenerator = (Button) findViewById(R.id.btnGernerator);
        ivQr = (ImageView) findViewById(R.id.ivQr);
        btnDownloader = (Button) findViewById(R.id.btnDownloader);

        etSaveImage = (EditText) findViewById(R.id.etSaveImage);

        btnDownloader = (Button)findViewById(R.id.btnDownloader);
        btnGenerator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Textvalue = etCode.getText().toString();

                try {
                    bitmap = TextToImageEncode(Textvalue);
                } catch (WriterException e) {
                    e.printStackTrace();
                }
                ivQr.setImageBitmap(bitmap);
            }
        });
        btnDownloader.setOnClickListener(this);
    }

    Bitmap TextToImageEncode(String value)throws WriterException {
        BitMatrix bitMatrix;
        try{
            bitMatrix=new MultiFormatWriter().encode(
                    value,
                    BarcodeFormat.DATA_MATRIX.QR_CODE,
                    QRcodeWidth,QRcodeWidth,null
            );
        }
        catch (IllegalArgumentException Illegalargumentexception){
            return null;
        }
        int bitMatrixWidth=bitMatrix.getWidth();
        int bitMatrixHeight=bitMatrix.getHeight();
        int[] pixels=new int[bitMatrixWidth*bitMatrixHeight];
        for(int y=0;y<bitMatrixHeight;y++){
            int offset=y*bitMatrixWidth;
            for (int x=0;x<bitMatrixWidth;x++){
                pixels[offset+x]=bitMatrix.get(x,y)?
                        getResources().getColor(R.color.QrBlackColor):getResources().getColor(R.color.QrWhiteColor);
            }
        }
        Bitmap bitmap=Bitmap.createBitmap(bitMatrixWidth,bitMatrixHeight, Bitmap.Config.ARGB_4444);
        bitmap.setPixels(pixels,0,500,0,0,bitMatrixWidth,bitMatrixHeight);
        return bitmap;
    }



    @Override
    public void onClick(View view) {
        if (!Textvalue.equals("")) {


            String name = etSaveImage.getText().toString().trim();


            //create a file to write bitmap data
            {
                File f = new File(getBaseContext().getExternalCacheDir(), name + ".jpeg");

                try {
                    f.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                //Convert bitmap to byte array
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
                byte[] bitmapdata = bos.toByteArray();

                //write the bytes in file
                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream(f);
                    fos.write(bitmapdata);
                    fos.flush();
                    fos.close();
                    success = true;
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (success) {
                    if (!name.equals("")) {
                        Toast.makeText(this, "Image Saved", Toast.LENGTH_SHORT).show();
                        etCode.setText("");
                        etSaveImage.setText("");

                    } else {
                        Toast.makeText(this, "Give Image Name", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "Error during saving Image", Toast.LENGTH_SHORT).show();
                }
            }


        } else {
            Toast.makeText(this, "Generate Code First", Toast.LENGTH_SHORT).show();

        }
    }


}

