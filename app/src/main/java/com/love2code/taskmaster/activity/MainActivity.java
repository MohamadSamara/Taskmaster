package com.love2code.taskmaster.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.love2code.taskmaster.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //step1: get a UI element By id
        Button addTaskButton = (Button) findViewById(R.id.addTaskbtn);
        Button allTasksButton = (Button) findViewById(R.id.allTasksbtn);
        Toast toast = Toast.makeText(this, "Hello from Toast", Toast.LENGTH_SHORT);

        addTaskButton.setOnClickListener(new View.OnClickListener() {
            //step 3: Attach a callback function with onClick() method
            @Override
            public void onClick(View view) {
                // step 4: Do stuff in the callback
                toast.show();
                Intent goToAddTaskIntent = new Intent(MainActivity.this, AddTaskActivity.class);
                startActivity(goToAddTaskIntent);
            }
        });


        allTasksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // step 4: Do stuff in the callback
                toast.show();
                Intent goToAllTaskIntent = new Intent(MainActivity.this, AllTasksActivity.class);
                startActivity(goToAllTaskIntent);
            }
        });


}
}