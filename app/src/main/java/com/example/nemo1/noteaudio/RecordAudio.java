package com.example.nemo1.noteaudio;

import android.content.Context;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class RecordAudio {
    private MediaRecorder mediaRecorder;
    private Context context;
    private File file;

    public RecordAudio(Context context) {
        this.context = context;
        mediaRecorder = new MediaRecorder();
        file = new File(Environment.getExternalStorageDirectory(),"/Record"); //Tao folder Record de luu file
    }

    public void recoding() {
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_2_TS);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
        if(!file.isDirectory()){ // kiem tra da co san folder Record chua ?
            file.mkdir();
            mediaRecorder.setOutputFile(file+"/"+UUID.randomUUID().toString()+".mp3");
        }
        mediaRecorder.setOutputFile(file+"/"+UUID.randomUUID().toString()+".mp3");
        try {
            mediaRecorder.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaRecorder.start();
        }

    public void save(){
        try{
            mediaRecorder.stop();
            mediaRecorder.release();
            Toast.makeText(context,file.getAbsolutePath(),Toast.LENGTH_SHORT).show();
            mediaRecorder = null;
        }catch (Exception e){
            Toast.makeText(context,e.toString(),Toast.LENGTH_SHORT).show();
        }
    }
}
