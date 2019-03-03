package com.example.nemo1.noteaudio;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private RecordAudio recordAudio;
    private Button record, save, open;
    private AlertDialog.Builder builder;
    private Intent intent;
    private static final int OPEN_REQUEST_CODE = 41;
    private String[] permissions = {"android.permission.WRITE_EXTERNAL_STORAGE","android.permission.READ_EXTERNAL_STORAGE","android.permission.RECORD_AUDIO"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initEvent();
        setEvent();
        checkPermissions();
    }

    @Override
    protected void onStart() {
        super.onStart();
        builder = new AlertDialog.Builder(this)
        .setTitle("Record Note")
        .setMessage("Wellcome User")
        .setNegativeButton("OK",null);
        builder.create().show();
    }

    private void initEvent() {
        record = findViewById(R.id.record);
        save = findViewById(R.id.save);
        open = findViewById(R.id.open);
        recordAudio = new RecordAudio(this);
    }

    private void setEvent() {
        record.setOnClickListener(this);
        save.setOnClickListener(this);
        open.setOnClickListener(this);
    }

    public void checkPermissions(){
        ActivityCompat.requestPermissions(this,permissions,200);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 200:
            {
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){

                }
                else if (grantResults[1] == PackageManager.PERMISSION_GRANTED){

                }
                else if (grantResults[2] == PackageManager.PERMISSION_GRANTED){

                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == record.getId()){
            Toast.makeText(this,"Record start",Toast.LENGTH_SHORT).show();
            recordAudio.recoding();
        }
        if(v.getId() == save.getId()){
            Toast.makeText(this,"Record stop",Toast.LENGTH_SHORT).show();
            recordAudio.save();
        }
        if(v.getId() == open.getId()){
            intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("audio/*");
            startActivityForResult(intent,OPEN_REQUEST_CODE);
        }
    }

    @Override
    public void onBackPressed() {
        builder.setTitle("Bye Bye")
                .setMessage("Are you exit")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("No",null)
                .create()
                .show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK){
            if(requestCode == OPEN_REQUEST_CODE){
                if(data != null){
                    Toast.makeText(this,data.getDataString(),Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
