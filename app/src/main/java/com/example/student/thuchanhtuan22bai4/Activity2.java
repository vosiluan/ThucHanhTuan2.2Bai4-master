package com.example.student.thuchanhtuan22bai4;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class Activity2 extends AppCompatActivity  {
    private ListView dsnuoc;
    private Button btnketthuc;
    ArrayList<String> dschon = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        connectView();
        final ArrayList<String> dsnuocuong = new ArrayList<>();
        dsnuocuong.add("COCA");dsnuocuong.add("COFFEE");dsnuocuong.add("MILK TEA");dsnuocuong.add("NUMBER ONE");
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_multiple_choice, dsnuocuong);
        dsnuoc.setAdapter(arrayAdapter);
        btnketthuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putStringArrayListExtra("dsnuocchon",dschon);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
        dsnuoc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Object nuocuong = adapterView.getAdapter().getItem(i);
                dschon.add(nuocuong.toString());
            }
        });
    }

    private void connectView() {
        btnketthuc = (Button) findViewById(R.id.btnketthuc);
        dsnuoc = (ListView)findViewById(R.id.dsnuocuong);
    }

}
