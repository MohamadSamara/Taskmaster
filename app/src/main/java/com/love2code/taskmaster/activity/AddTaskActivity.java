package com.love2code.taskmaster.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.State;
import com.amplifyframework.datastore.generated.model.Tasks;
import com.love2code.taskmaster.R;

public class AddTaskActivity extends AppCompatActivity {

    public static final String TAG = "AddTaskActivity";

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

//                Tasks newTasks = new Tasks(
//                        ((EditText) findViewById(R.id.taskTitleEdtTxt)).getText().toString(),
//                        ((EditText) findViewById(R.id.taskBodyEdtTxt)).getText().toString(),
//                        State.fromString(taskCategorySpinner.getSelectedItem().toString())
//                        );
                // appDatabase.taskDao().insertATask(newTasks);

                String title = ((EditText)findViewById(R.id.taskTitleEdtTxt)).getText().toString();
                String body = ((EditText)findViewById(R.id.taskBodyEdtTxt)).getText().toString();
                Tasks newTask = Tasks.builder()
                        .title(title)
                        .body(body)
                        .state((State) taskCategorySpinner.getSelectedItem()).build();

                Amplify.API.mutate(
                        ModelMutation.create(newTask),
                        successResponse -> Log.i(TAG, "AddTaskActivity.onCreate(): made a Task successfully"),//success response
                        failureResponse -> Log.e(TAG, "AddTaskActivity.onCreate(): failed with this response" + failureResponse)// in case we have a failed response
                );

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