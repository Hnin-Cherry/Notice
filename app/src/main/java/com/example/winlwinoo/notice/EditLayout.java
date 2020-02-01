package com.example.winlwinoo.notice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.winlwinoo.notice.winlwinoo.notice.db.DatabaseAccess;

public class EditLayout extends AppCompatActivity {

    private EditText etText;
    private Button btnSave;
    private Button btnCancel;
    private Memo memo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_layout);

        this.etText = (EditText) findViewById(R.id.etText);
        this.btnSave = (Button) findViewById(R.id.btnSave);
        this.btnCancel = (Button) findViewById(R.id.btnCalcel);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            memo = (Memo) bundle.get("MEMO");
            if (memo != null){
                this.etText.setText(memo.getText());
            }
        }

        this.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSaveClicked();
            }
        });

        this.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCancelClicked();
            }
        });

       /* this.btnAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditLayout.this , alarm_clock.class);
                startActivity(intent);
            }
        });*/


    }

    public void onSaveClicked(){
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();

        if (memo == null){
            //Add new memo
            Memo temp = new Memo();
            temp.setText(etText.getText().toString());
            databaseAccess.save(temp);
        }

        else {
            //update the memo
            memo.setText(etText.getText().toString());
            databaseAccess.update(memo);
        }

        databaseAccess.close();
        this.finish();
    }

    public void onCancelClicked(){
        this.finish();
    }
}
