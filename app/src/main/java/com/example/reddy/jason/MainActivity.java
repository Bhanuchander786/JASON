package com.example.reddy.jason;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
        EditText edTxt;
        TextView tView;
        Button rd,wr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edTxt=findViewById(R.id.et);
        tView=findViewById(R.id.textview);
        rd=findViewById(R.id.read);
        wr=findViewById(R.id.write);
        rd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileInputStream fileInputStream = openFileInput("myText.txt");
                    InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                    BufferedReader bufferedReader =new BufferedReader(inputStreamReader);
                    StringBuffer stringBuffer = new StringBuffer();
                    String lines;
                    while (lines=bufferedReader.readLine()!=null){
                        stringBuffer.append(lines+"");
                    }
                    tView.setText(stringBuffer.toString());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        wr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mytxt = edTxt.getText().toString();
                try (FileOutputStream fs = openFileOutput("myText.txt", MODE_PRIVATE)) {
                    fs.write(mytxt.getBytes());
                    fs.close();
                    Toast.makeText(getApplicationContext(),"Text saved",Toast.LENGTH_SHORT).show();
                    edTxt.setText("");
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }


}
