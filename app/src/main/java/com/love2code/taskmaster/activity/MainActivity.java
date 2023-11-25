package com.love2code.taskmaster.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.auth.AuthUser;
import com.amplifyframework.auth.AuthUserAttribute;
import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Task;
import com.love2code.taskmaster.R;
import com.love2code.taskmaster.activity.adapter.TaskListRecyclerViewAdapter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    SharedPreferences preferences;
    List<Task> tasks = null;
    TaskListRecyclerViewAdapter adapter;
    public static final String TASK_NAME_TAG = "taskName";
    public static final String TASK_BODY_TAG = "taskBody";
    public static final String TASK_STATE_TAG = "taskState";
    public static final String TAG = "TaskActivity";
    public static final String MAIN_ID_TAG = "Main ID Tag";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // first step, sign up
       /* Amplify.Auth.signUp("mohamadsamara1211@gmail.com",
                "mohamad123",
                AuthSignUpOptions.builder()
                        .userAttribute(AuthUserAttributeKey.email(), "mohamadsamara1211@gmail.com")
                        .userAttribute(AuthUserAttributeKey.nickname(), "Samara")
                        .build(),
                good ->
                {
                    Log.i(TAG, "Signup succeeded: "+ good.toString());
                },
                bad ->
                {
                    Log.i(TAG, "Signup failed with username: "+ "mohamadsamara1211@gmail.com"+ " with this message: "+ bad.toString());
                }
        );*/

        // next step, we need to verify the user
        /*Amplify.Auth.confirmSignUp("mohamadsamara1211@gmail.com",
                "676225",
                success ->
                {
                    Log.i(TAG,"verification succeeded: "+ success.toString());

                },
                failure ->
                {
                    Log.i(TAG,"verification failed: "+ failure.toString());
                }
        );*/

        // next, we want to log in to our system
/*        Amplify.Auth.signIn("mohamadsamara1211@gmail.com",
                "mohamad@123",
                success ->
                {
                    Log.i(TAG, "Login succeeded: "+success.toString());
                },
                failure ->
                {
                    Log.i(TAG, "Login failed: "+failure.toString());
                }
        );*/

        // next we want to log out from out system
/*        Amplify.Auth.signOut(
                () ->
                {
                    Log.i(TAG,"Logout succeeded");
                },
                failure ->
                {
                    Log.i(TAG, "Logout failed");
                }
        );*/

//        Team team1 = Team.builder()
//                .name("Mohamad Samara")
//                .build();
//
//        Team team2 = Team.builder()
//                .name("Team Name 2")
//                .build();
//
//        Team team3 = Team.builder()
//                .name("Team Name 3")
//                .build();
//
//
//               Amplify.API.mutate(
//               ModelMutation.create(team1),
//               successResponse -> Log.i(TAG, "MainActivity.onCreate(): made a Team successfully"),
//               failureResponse -> Log.i(TAG, "MainActivity.onCreate(): Team failed with this response: "+failureResponse)
//       );
//        Amplify.API.mutate(
//                ModelMutation.create(team2),
//                successResponse -> Log.i(TAG, "MainActivity.onCreate(): made a Team successfully"),
//                failureResponse -> Log.i(TAG, "MainActivity.onCreate(): Team failed with this response: "+failureResponse)
//        );
//        Amplify.API.mutate(
//                ModelMutation.create(team3),
//                successResponse -> Log.i(TAG, "MainActivity.onCreate(): made a Team successfully"),
//                failureResponse -> Log.i(TAG, "MainActivity.onCreate(): Team failed with this response: "+failureResponse)
//        );



        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        tasks = new ArrayList<>();

        Amplify.API.query(
                ModelQuery.list(Task.class),
                success -> {
                    Log.i(TAG, "Updated Tasks Successfully!");
                    tasks.clear();
                    for (Task databaseTask : success.getData()) {
                        tasks.add(databaseTask);
                    }
                    runOnUiThread(() -> {
                        adapter.notifyDataSetChanged();
                    });
                },


                failure -> Log.i(TAG, "failed with this response: ")
        );


        setUpTaskListRecyclerView();

        //step1: get a UI element By id
        Button addTaskButton = findViewById(R.id.addTaskBtn);
        Button allTasksButton = findViewById(R.id.allTasksBtn);
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


        String emptyFilename= "emptyTestFileName";
        File emptyFile = new File(getApplicationContext().getFilesDir(), emptyFilename);

        try {
            BufferedWriter emptyFileBufferedWriter= new BufferedWriter(new FileWriter(emptyFile));

            emptyFileBufferedWriter.append("Some text here from Mohamad\nAnother lib from Mohamad");

            emptyFileBufferedWriter.close();
        }catch (IOException ioe){
            Log.i(TAG, "could not write locally with filename: "+ emptyFilename);
        }

        String emptyFileS3Key = "someFileOnS3.txt";
        Amplify.Storage.uploadFile(
                emptyFileS3Key,
                emptyFile,
                success ->
                {
                    Log.i(TAG, "S3 upload succeeded and the Key is: " + success.getKey());
                },
                failure ->
                {
                    Log.i(TAG, "S3 upload failed! " + failure.getMessage());
                }
        );


        setUpLoginAndLogoutButton();
    }

    @Override
    protected void onResume() {
        super.onResume();

//        String username = preferences.getString(SettingActivity.USERNAME_TAG, "No Username");
//        ((TextView) findViewById(R.id.usernameTxt)).setText(getString(R.string.username_with_input, username));


        AuthUser authUser = Amplify.Auth.getCurrentUser();
        String username="";
        if (authUser == null){
            Button loginButton = (Button) findViewById(R.id.taskListLoginButton);
            loginButton.setVisibility(View.VISIBLE);
            Button logoutButton = (Button) findViewById(R.id.taskListLogoutButton);
            logoutButton.setVisibility(View.INVISIBLE);
        }else{
            username = authUser.getUsername();
            Log.i(TAG, "Username is: "+ username);
            Button loginButton = (Button) findViewById(R.id.taskListLoginButton);
            loginButton.setVisibility(View.INVISIBLE);
            Button logoutButton = (Button) findViewById(R.id.taskListLogoutButton);
            logoutButton.setVisibility(View.VISIBLE);

            String username2 = username; // ugly way for lambda hack
            Amplify.Auth.fetchUserAttributes(
                    success ->
                    {
                        Log.i(TAG, "Fetch user attributes succeeded for username: "+username2);
                        for (AuthUserAttribute userAttribute: success){
                            if(userAttribute.getKey().getKeyString().equals("email")){
                                String userEmail = userAttribute.getValue();
                                runOnUiThread(() ->
                                {
                                    ((TextView)findViewById(R.id.usernameTxt)).setText(userEmail);
                                });
                            }
                        }
                    },
                    failure ->
                    {
                        Log.i(TAG, "Fetch user attributes failed: "+failure.toString());
                    }
            );
        }

        String userTeamName = preferences.getString(SettingActivity.USER_TEAM_TAG, "No Team");

        ((TextView) findViewById(R.id.teamNameTxt)).setText(getString(R.string.team_with_input, userTeamName));

        Amplify.API.query(
                ModelQuery.list(Task.class),
                success -> {
                    Log.i(TAG, "Updated Tasks Successfully!");
                    tasks.clear();
                    for(Task databaseTask : success.getData()){
                        if (userTeamName.equals("No Team")){
                            tasks.add(databaseTask);
                        }
                        else if (databaseTask.getTeamName().getName().equals(userTeamName)) {
                            tasks.add(databaseTask);
                        }
                    }
                    runOnUiThread(() -> {
                        adapter.notifyDataSetChanged();
                    });
                },

                failure -> Log.i(TAG, "failed with this response: ")
        );

    }


    private void setUpTaskListRecyclerView() {
        //TODO: step 1-1: Add a RecyclerView to the Activity in the WSYIWYG editor
        //TODO: step 1-2: grab the RecyclerView
        RecyclerView taskListRecyclerView = (RecyclerView) findViewById(R.id.taskListRecyclerView);

        //TODO: step 1-3: set the layout manager of the RecyclerView to a LinerLayoutManager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        taskListRecyclerView.setLayoutManager(layoutManager);


        //TODO: step 1-5: create and attach the RecyclerView.Adapter
        adapter = new TaskListRecyclerViewAdapter(tasks, this);
        taskListRecyclerView.setAdapter(adapter);

    }


    private void setUpLoginAndLogoutButton(){
        Button loginButton = (Button) findViewById(R.id.taskListLoginButton);
        loginButton.setOnClickListener(v ->
        {
            Intent goToLogInIntent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(goToLogInIntent);
        });

        Button logoutButton = (Button) findViewById(R.id.taskListLogoutButton);
        logoutButton.setOnClickListener(v->
        {
            Amplify.Auth.signOut(
                    () ->
                    {
                        Log.i(TAG,"Logout succeeded");
                        runOnUiThread(() ->
                        {
                            ((TextView)findViewById(R.id.usernameTxt)).setText("");
                        });
                        Intent goToLogInIntent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(goToLogInIntent);
                    },
                    failure ->
                    {
                        Log.i(TAG, "Logout failed");
                        runOnUiThread(() ->
                        {
                            Toast.makeText(MainActivity.this, "Log out failed", Toast.LENGTH_LONG);
                        });
                    }
            );
        });
    }

}