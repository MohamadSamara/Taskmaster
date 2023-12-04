package com.love2code.taskmaster.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.amplifyframework.core.Amplify;
import com.love2code.taskmaster.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class TaskDetailActivity extends AppCompatActivity {

    private final String TAG = "TEST";
    private final MediaPlayer mp = new MediaPlayer();
//    Button TextToSpeechButton = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        Intent callingIntent = getIntent();
        String taskNameString = null;
        String taskBodyString = null;
        String taskStateEnum = null;

        if (callingIntent != null) {
            taskNameString = callingIntent.getStringExtra(MainActivity.TASK_NAME_TAG);
            taskBodyString = callingIntent.getStringExtra(MainActivity.TASK_BODY_TAG);
            taskStateEnum = callingIntent.getStringExtra(MainActivity.TASK_STATE_TAG);

            TextView taskNameTextView = (TextView) findViewById(R.id.taskNameTxt);
            TextView taskBodyTextView = (TextView) findViewById(R.id.taskBodyTxt);
            TextView taskStateTextView = (TextView) findViewById(R.id.taskStateTxt);

            if (taskNameString != null) {
                taskNameTextView.setText(taskNameString);
            } else {
                taskNameTextView.setText("Task Unknown");
            }

            if (taskBodyString != null) {
                taskBodyTextView.setText(taskBodyString);
            } else {
                taskBodyTextView.setText("Body Unknown");
            }

            if (taskStateEnum != null) {
//                taskStateTextView.setText(taskStateEnum);
                taskStateTextView.setText(taskStateEnum.toString()); // You can use toString() to display the enum as a String.

            } else {
                taskStateTextView.setText("State Unknown");
            }

        }

        setUpSpeakButton();
    }

    private void setUpSpeakButton(){
        Button speakButton = (Button) findViewById(R.id.TextToSpeechButton);
        speakButton.setOnClickListener(b ->
        {
            String taskBody= ((TextView) findViewById(R.id.taskBodyTxt)).getText().toString();

            Amplify.Predictions.convertTextToSpeech(
                    taskBody,
                    result -> playAudio(result.getAudioData()),
                    error -> Log.e(TAG,"conversion failed ", error)
            );
        });
    }

    private void playAudio(InputStream data) {
        File mp3File = new File(getCacheDir(), "audio.mp3");

        try (OutputStream out = new FileOutputStream(mp3File)) {
            byte[] buffer = new byte[8 * 1_024];
            int bytesRead;
            while ((bytesRead = data.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
            mp.reset();
            mp.setOnPreparedListener(MediaPlayer::start);
            mp.setDataSource(new FileInputStream(mp3File).getFD());
            mp.prepareAsync();
        } catch (IOException error) {
            Log.e("MyAmplifyApp", "Error writing audio file", error);
        }
    }
}