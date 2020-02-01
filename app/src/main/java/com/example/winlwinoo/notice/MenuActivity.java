package com.example.winlwinoo.notice;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        TextView contact = (TextView) findViewById(R.id.contact);

        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    MenuActivity.this.startActivity(new Intent(Intent.ACTION_VIEW , Uri.parse("fb://profile/100009323503559")));
                }catch (Exception e){
                    MenuActivity.this.startActivity(new Intent(Intent.ACTION_VIEW , Uri.parse("https://www.facebook.com/profile.php?id=100009323503559")));
                }
            }
        });
    }
}
