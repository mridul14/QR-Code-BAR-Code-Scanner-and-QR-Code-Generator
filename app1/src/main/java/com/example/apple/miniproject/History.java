package com.example.apple.miniproject;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class History extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView rvHistory;
    private Contract contract;
    private ArrayList<Model> historylist;
    private FloatingActionButton fab;
    private Button btn_delete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        rvHistory = (RecyclerView) findViewById(R.id.rvHistory);

        contract = new Contract(this);
        Cursor c= contract.list();
        historylist = new ArrayList<>();
        CursorToArraylist(c);
        HistoryAdapter adapter = new HistoryAdapter(this,historylist);
        LinearLayoutManager layout = new LinearLayoutManager(this);
        rvHistory.setLayoutManager(layout);
        rvHistory.setAdapter(adapter);


        btn_delete = (Button) findViewById(R.id.btn_delete);

        btn_delete.setOnClickListener(this);

    }

    private void CursorToArraylist(Cursor c) {
        if(c!=null)
        {
            if(c.getCount()>0)
            {


                while (c.moveToNext()){
                    long id = c.getLong(0);
                    String name = c.getString(1);
                    historylist.add(new Model(name,id));

                }


            }
            else{
                Toast.makeText(this, "NO HISTORY", Toast.LENGTH_SHORT).show();
            }}
        else
        {
            Toast.makeText(this, "there was no problem loading data", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onClick(View view) {

        dodelete();
        Intent resultIntent=new Intent(History.this,History.class);
        startActivity(resultIntent);


    }

    private void dodelete() {
        contract= new Contract(this);
        contract.delete();
        Toast.makeText(this, "History Deleted", Toast.LENGTH_SHORT).show();
    }
}
