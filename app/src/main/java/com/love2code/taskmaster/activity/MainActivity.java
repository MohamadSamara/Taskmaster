package com.love2code.taskmaster.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.love2code.taskmaster.R;
import com.love2code.taskmaster.activity.Enum.State;
import com.love2code.taskmaster.activity.adapter.TaskListRecyclerViewAdapter;
import com.love2code.taskmaster.activity.model.Tasks;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    SharedPreferences preferences;

    public static final String TASK_NAME_TAG ="taskName";
    public static final String TASK_BODY_TAG ="taskBody";
    public static final String TASK_STATE_TAG ="taskState";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);

//        setUpTaskTwoButton();
//        setUpTaskOneButton();

        setUpTaskListRecyclerView();


        //step1: get a UI element By id
        Button addTaskButton = findViewById(R.id.addTaskbtn);
        Button allTasksButton = findViewById(R.id.allTasksbtn);
        Button settingButton = findViewById(R.id.btnSetting);

        addTaskButton.setOnClickListener(new View.OnClickListener() {
            //step 3: Attach a callback function with onClick() method
            @Override
            public void onClick(View view) {
                // step 4: Do stuff in the callback
                Intent goToAddTaskIntent = new Intent(MainActivity.this, AddTaskActivity.class);
                startActivity(goToAddTaskIntent);
            }
        });


        allTasksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToAllTaskIntent = new Intent(MainActivity.this, AllTasksActivity.class);
                startActivity(goToAllTaskIntent);
            }
        });


        settingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToSettingIntent = new Intent(MainActivity.this, SettingActivity.class);
                startActivity(goToSettingIntent);
            }
        });
}

    @Override
    protected void onResume() {
        super.onResume();

        String username = preferences.getString(SettingActivity.USERNAME_TAG, "No Username");

        ((TextView)findViewById(R.id.usernameTxt)).setText(getString(R.string.username_with_input, username));
    }

//    private void setUpTaskOneButton() {
//        Button taskOneButton = findViewById(R.id.btnTaskOne);
//        taskOneButton.setOnClickListener(view -> {
//            String taskOneName = taskOneButton.getText().toString();
//            Intent goToDetailIntent = new Intent(MainActivity.this, TaskDetailActivity.class);
//            goToDetailIntent.putExtra(MainActivity.TASK_NAME_TAG, taskOneName);
//            startActivity(goToDetailIntent);
//        });
//    }

//    private void setUpTaskTwoButton() {
//        Button taskTwoButton = findViewById(R.id.btnTaskTow);
//        taskTwoButton.setOnClickListener(view -> {
//            String taskTwoName = taskTwoButton.getText().toString();
//            Intent goToDetailIntent = new Intent(MainActivity.this, TaskDetailActivity.class);
//            goToDetailIntent.putExtra(MainActivity.TASK_NAME_TAG, taskTwoName);
//            startActivity(goToDetailIntent);
//        });
//    }

    private void setUpTaskListRecyclerView(){
        //TODO: step 1-1: Add a RecyclerView to the Activity in the WSYIWYG editor
        //TODO: step 1-2: grab the RecyclerView
        RecyclerView taskListRecyclerView = (RecyclerView) findViewById(R.id.taskListRecyclerView);

        //TODO: step 1-3: set the layout manager of the RecyclerView to a LinerLayoutManager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        taskListRecyclerView.setLayoutManager(layoutManager);

        //TODO: step 2-2: Make some data items
        List<Tasks> tasks = new ArrayList<>();

        tasks.add(new Tasks("title1" , "body 1" , State.NEW));
        tasks.add(new Tasks("title2" , "body 2" , State.COMPLETE));
        tasks.add(new Tasks("title3" , "body 3" , State.ASSIGNED));
        tasks.add(new Tasks("title4" , "body 4" , State.COMPLETE));
        tasks.add(new Tasks("title5" , "body 5" , State.IN_PROGRESS));
        tasks.add(new Tasks("title6" , "body 6" , State.IN_PROGRESS));
        tasks.add(new Tasks("title7" , "body 7" , State.NEW));
        tasks.add(new Tasks("title8" , "body 8" , State.IN_PROGRESS));
        tasks.add(new Tasks("title9" , "body 9" , State.COMPLETE));


        //TODO: step 1-5: create and attach the RecyclerView.Adapter
        TaskListRecyclerViewAdapter adapter = new TaskListRecyclerViewAdapter(tasks, this);
        taskListRecyclerView.setAdapter(adapter);

    }




}