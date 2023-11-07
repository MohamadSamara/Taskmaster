package com.love2code.taskmaster.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import com.love2code.taskmaster.R;
import com.love2code.taskmaster.activity.Enum.State;
import com.love2code.taskmaster.activity.model.Tasks;

public class AddTaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);



        Spinner taskCategorySpinner = (Spinner) findViewById(R.id.addTaskCategorySpinner);
        taskCategorySpinner.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                State.values()));



        Button addTasksButton = (Button) findViewById(R.id.addTaskToTotalbtn);
        Button back = (Button) findViewById(R.id.backbtn2);

        addTasksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Tasks newTasks = new Tasks(
                        ((EditText) findViewById(R.id.taskTitleEdtTxt)).getText().toString(),
                        ((EditText) findViewById(R.id.taskBodyEdtTxt)).getText().toString(),
                        State.fromString(taskCategorySpinner.getSelectedItem().toString())
                        );
                // appDatabase.taskDao().insertATask(newTasks);

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToMainIntent = new Intent(AddTaskActivity.this, MainActivity.class);
                startActivity(goToMainIntent);
            }
        });
    }

}