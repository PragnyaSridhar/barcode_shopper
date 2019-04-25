package com.example.p7;

import android.content.Context;
import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.widget.TextView;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ImageView imageView1;
    TextView textView1;
    BarcodeDetector detector;
    ArrayList<String> codes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        codes= new ArrayList<String>();

        Button button1=(Button) findViewById(R.id.button1);
        Button button2=(Button) findViewById(R.id.button2);
        Button button3=(Button) findViewById(R.id.button3);
        imageView1=(ImageView) findViewById(R.id.imageView1);
        textView1= (TextView) findViewById(R.id.textView1);

        Context context = getApplicationContext();
        detector = new BarcodeDetector.Builder(context).build();

        boolean op = detector.isOperational();
        if(!op )

        {
            textView1.setText("Could not set up the detector!");
            return;
        }

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,0);

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this,bill.class);
                //Bundle bundle = new Bundle();
                intent.putExtra("barc", codes);
                //intent.putExtras();
                startActivity(intent);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!textView1.getText().toString().equals("")) {
                    codes.add(textView1.getText().toString());
                }

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        Bitmap bitmap=(Bitmap)data.getExtras().get("data");
        imageView1.setImageBitmap(bitmap);
        Frame frame = new Frame.Builder().setBitmap(bitmap).build();
        SparseArray<Barcode> barcodes = detector.detect(frame);

        Barcode thisCode = barcodes.valueAt(0);
        textView1.setText(thisCode.rawValue);
    }
}
