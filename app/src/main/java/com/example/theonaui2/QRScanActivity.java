package com.example.theonaui2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.theonaui2.ui.MainActivity;
import com.example.theonaui2.utils.CaptureActivityPortrait;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class QRScanActivity extends AppCompatActivity {
    ImageButton goBackButton;
    Button openCameraButton;
    Button okButton;
    ImageView qrImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrscan);

        bindViews();

        goBackButton.setOnClickListener(view -> {
            Intent intent = new Intent(QRScanActivity.this, MainActivity.class);
            startActivity(intent);
        });
        okButton.setOnClickListener(view -> {
            Intent intent = new Intent(QRScanActivity.this, MainActivity.class);
            startActivity(intent);
        });
        openCameraButton.setOnClickListener(view -> {
            IntentIntegrator integrator = new IntentIntegrator(QRScanActivity.this);
            integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
            integrator.setPrompt("Scan a QR code");
            integrator.setCameraId(0);
            integrator.setBeepEnabled(false);
            integrator.setBarcodeImageEnabled(true);
            integrator.setCaptureActivity(CaptureActivityPortrait.class);
            integrator.initiateScan();
        });

        //get UUID from Intent and use it to generate QR code
        Intent intent = getIntent();
        String uuid = intent.getStringExtra("uuid");

        Bitmap bitmap = generateQR(uuid);

        qrImage.setImageBitmap(bitmap);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                Log.e("QR", result.getContents().toString());
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


    private void bindViews() {
        goBackButton = findViewById(R.id.qr_goback);
        openCameraButton = findViewById(R.id.open_camera_button);
        okButton = findViewById(R.id.qr_ok);

        qrImage = findViewById(R.id.qr_image);
    }

    Bitmap generateQR(String text) {
        Bitmap qrBitmap = Bitmap.createBitmap(1000, 1000, Bitmap.Config.ARGB_8888);
        WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);

        // initializing a variable for default display.
        Display display = manager.getDefaultDisplay();

        // creating a variable for point which
        // is to be displayed in QR Code.
        Point point = new Point();
        display.getSize(point);

        // getting width and
        // height of a point
        int width = point.x;
        int height = point.y;

        // generating dimension from width and height.
        int dimen = width < height ? width : height;
        dimen = dimen * 3 / 4;

        try {
            QRGEncoder encoder = new QRGEncoder(text, null, QRGContents.Type.TEXT, dimen);
            qrBitmap = encoder.getBitmap();

            qrBitmap = doInvert(qrBitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return qrBitmap;
    }

    public static Bitmap doInvert(Bitmap src) {
        Bitmap bmOut = Bitmap.createBitmap(src.getWidth(), src.getHeight(),
                src.getConfig());
        // color info
        int A, R, G, B;
        int pixelColor;
        // image size
        int height = src.getHeight();
        int width = src.getWidth();
        // scan through every pixel
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // get one pixel
                pixelColor = src.getPixel(x, y);
                // saving alpha channel
                A = Color.alpha(pixelColor);
                R = 255 - Color.red(pixelColor);
                G = 255 - Color.green(pixelColor);
                B = 255 - Color.blue(pixelColor);
                bmOut.setPixel(x, y, Color.argb(A, R, G, B));
            }
        }
        // return final bitmap
        return bmOut;
    }

    void scanQR() {
        IntentIntegrator integrator = new IntentIntegrator(QRScanActivity.this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
        integrator.setPrompt("Scan a QR code");
        integrator.setCameraId(0);
        integrator.setBeepEnabled(false);
        integrator.setBarcodeImageEnabled(true);
        integrator.initiateScan();
    }
}