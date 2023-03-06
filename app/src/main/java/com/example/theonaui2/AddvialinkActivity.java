package com.example.theonaui2;

import static android.content.ClipDescription.MIMETYPE_TEXT_PLAIN;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.theonaui2.ui.MainActivity;

public class AddvialinkActivity extends AppCompatActivity {
    TextView myLink;
    Button copy;
    Button share;
    ImageButton goBack;
    Button ok;
    EditText url;
    Button paste;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addvialink);

        bindViews();

        goBack.setOnClickListener(view -> {
            Intent intent = new Intent(AddvialinkActivity.this, MainActivity.class);
            startActivity(intent);
        });


        copy.setOnClickListener(view -> {
            String text = myLink.getText().toString();
            ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("text", text);
            clipboard.setPrimaryClip(clip);
            Toast.makeText(AddvialinkActivity.this, "Copied to clipboard", Toast.LENGTH_SHORT).show();
        });

        paste.setOnClickListener(view -> {
            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            String pasteData = "";

            // If it does contain data, decide if you can handle the data.
            if (!(clipboard.hasPrimaryClip())) {

            } else if (!(clipboard.getPrimaryClipDescription().hasMimeType(MIMETYPE_TEXT_PLAIN))) {

                // since the clipboard has data but it is not plain text

            } else {
                //since the clipboard contains plain text.
                ClipData.Item item = clipboard.getPrimaryClip().getItemAt(0);

                // Gets the clipboard as text.
                pasteData = item.getText().toString();
            }

            url.setText(pasteData);
        });


        ok.setOnClickListener(view -> {
            if (url.getText().toString().isEmpty()) {
                Intent intent = new Intent(AddvialinkActivity.this, MainActivity.class);
                startActivity(intent);
            } else {
                Intent intent = new Intent(AddvialinkActivity.this, MainActivity.class);
                intent.putExtra("url", url.getText().toString());
                startActivity(intent);
            }
        });
    }

    void bindViews() {
        myLink = findViewById(R.id.myLink);
        copy = findViewById(R.id.copyButton);
        share = findViewById(R.id.shareButton);
        goBack = findViewById(R.id.goBackUrl);
        ok = findViewById(R.id.okUrl);
        url = findViewById(R.id.editTextText);
        paste = findViewById(R.id.pasteButton);
    }
}