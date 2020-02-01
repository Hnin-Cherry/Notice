package com.example.winlwinoo.notice;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.winlwinoo.notice.winlwinoo.notice.db.DatabaseAccess;
import com.getbase.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    private ListView listView;
    private DatabaseAccess databaseAccess;
    private List<Memo> memos;
    private FloatingActionButton newNote , setAlarm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.databaseAccess = DatabaseAccess.getInstance(this);
        this.listView = (ListView) findViewById(R.id.listView);



        newNote = (FloatingActionButton) findViewById(R.id.newNote);
        setAlarm = (FloatingActionButton) findViewById(R.id.setAlarm);

        newNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this , EditLayout.class);
                startActivity(intent);
            }
        });

        setAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this , SetAlarmLayout.class);
                startActivity(intent);
            }
        });


            this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Memo memo = memos.get(position);

                TextView txtMemo = (TextView) view.findViewById(R.id.textMemo);

                if (memo.isFullDisplayed()){
                    txtMemo.setText(memo.getShortText());
                    memo.setFullDisplayed(false);
                }
                else {
                    txtMemo.setText(memo.getText());
                    memo.setFullDisplayed(true);
                }

            }
        });
    }
    //////
    @Override
    protected void onResume() {
        super.onResume();

        databaseAccess.open();
        this.memos = databaseAccess.getAllMemos();
        databaseAccess.close();

        MemoAdapter adapter = new MemoAdapter(this,memos);
        this.listView.setAdapter(adapter);

    }

    public void onDeleteClicked(Memo memo){

        databaseAccess.open();
        databaseAccess.delete(memo);
        databaseAccess.close();

        ArrayAdapter<Memo> adapter = (ArrayAdapter <Memo>) listView.getAdapter();
        adapter.remove(memo);
        adapter.notifyDataSetChanged();
    }

    public void onEditClicked(Memo memo){
        Intent intent = new Intent(MainActivity.this , EditLayout.class);
        intent.putExtra("MEMO" , memo);
        startActivity(intent);
    }

    private class MemoAdapter extends ArrayAdapter<Memo> {

        public MemoAdapter(Context context , List <Memo> objects){
            super(context , 0 , objects);
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null){
                convertView = getLayoutInflater().inflate(R.layout.layout_list_item , parent , false);

            }

            ImageView btnEdit = (ImageView) convertView.findViewById(R.id.btnEdit);
            ImageView btnDelete = (ImageView) convertView.findViewById(R.id.btnDelete);
            TextView txtDate = (TextView) convertView.findViewById(R.id.textDate);
            TextView txtMemo = (TextView) convertView.findViewById(R.id.textMemo);

            final Memo memo = memos.get(position);
            memo.setFullDisplayed(false);
            txtDate.setText(memo.getDate());
            txtMemo.setText(memo.getShortText());

            btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onEditClicked(memo);
                }
            });

            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onDeleteClicked(memo);
                }
            });

            return convertView;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_layout , menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        String about = item.getTitle().toString();

        if (about.equalsIgnoreCase("About")){

            Intent intent = new Intent(MainActivity.this , MenuActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
