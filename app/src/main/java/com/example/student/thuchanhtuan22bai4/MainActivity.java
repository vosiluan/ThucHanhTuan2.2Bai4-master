package com.example.student.thuchanhtuan22bai4;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.Console;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private TextView ketqua;
    private Button btnchonnuoc, btnluu;
    private EditText soban;
    private TextView txtnuoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connectView();
        final ArrayList<String> dsban = new ArrayList<>();
        dsban.add("001");
        final Spinner danhsachcacban = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,dsban);
        danhsachcacban.setAdapter(arrayAdapter);
        btnchonnuoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Activity2.class);
                startActivityForResult(intent,9999);
            }
        });
        btnluu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String maban = soban.getText().toString();
                dsban.add(maban);
                try {
                    FileOutputStream fout = openFileOutput(maban, Context.MODE_PRIVATE);
                    fout.write(ketqua.getText().toString().getBytes());
                    fout.close();
                }
                catch (Exception e){
                    Toast.makeText(MainActivity.this,"Lỗi lưu file",Toast.LENGTH_LONG).show();
                }
            }
        });

        danhsachcacban.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                soban.setText(dsban.get(i).toString());
                String name = soban.getText().toString();
                StringBuffer buffer = new StringBuffer();
                String line = null;
                try{
                    FileInputStream fin = openFileInput(name);
                    BufferedReader bread = new BufferedReader(new InputStreamReader(fin));
                    while ((line = bread.readLine())!=null)
                        buffer.append(line).append("\n");
                    txtnuoc.setText(buffer.toString());
                }
                catch (Exception e){

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 9999 && resultCode == RESULT_OK){
           ArrayList<String> dsitem= data.getStringArrayListExtra("dsnuocchon");
           String ketquaa ="";
            for(int i =0;i<dsitem.size();i++){
               ketquaa+= "\t" + dsitem.get(i);

            }
            ketqua.setText(ketquaa);

        }
    }

    private void connectView() {
        ketqua = (TextView)findViewById(R.id.ketqua);
        btnchonnuoc = (Button) findViewById(R.id.btnchonnuoc);
        btnluu = (Button)findViewById(R.id.btnluu);
        soban = (EditText)findViewById(R.id.soban);
        txtnuoc = (TextView)findViewById(R.id.txtnuoc);
    }

}
