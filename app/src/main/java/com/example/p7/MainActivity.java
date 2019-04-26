package com.example.p7;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    ImageView imageView1;
    TextView textView1;
    BarcodeDetector detector;
    ArrayList<String> codes;
    DatabaseHelper helper;
    String s;
    public static int INS = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        codes= new ArrayList<String>();


        helper= new DatabaseHelper(MainActivity.this);
        SharedPreferences sp = this.getSharedPreferences("com.example.p7", MODE_PRIVATE);
        if(sp.getInt(s,INS)==0){
            helper.insertNote("4960371004426",
                    "Marie Light Gold",
                    20.00);
            helper.insertNote("060123456788",
                    "Dettol handwash",
                    120.00);
            helper.insertNote("5014016150821",
                    "Basmati Rice(1kg)",
                    200.00);
            helper.insertNote("640509040147",
                    "Pepsodent Gumcare",
                    100.00);
            helper.insertNote("036000291452",
                    "Sunfeast snacky biscuit",
                    20.00);
            helper.insertNote("1234567890128",
                    "Surfexcel",
                    90.00);
            helper.insertNote("712345678911",
                    "Bodywash",
                    180.00);
            helper.insertNote("987654321098",
                    "Coffee powder",
                    60.00);
            helper.insertNote("042000062008",
                    "Nutties chocolate",
                    40.00);
            helper.insertNote("3800065711135",
                    "Pedigree-Dog food",
                    400.00);
            SharedPreferences.Editor editor=sp.edit();
            editor.putInt(s,1);
            editor.commit();
            editor.apply();
            //editor.close();
        }



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
