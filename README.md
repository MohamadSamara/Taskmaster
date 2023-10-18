# Taskmaster

Taskmaster is a simple to-do list app that helps you stay organized and on track.

## Features

* Add new tasks with titles and bodies.
* View all of your tasks in a list.
* Mark tasks as complete.

## Getting started

To use Taskmaster, simply clone the repo, open it and you will see the main page.

On the main page, you can see an overview of all of your tasks. You can also add new tasks or view all of your tasks by clicking on the corresponding buttons.

To add a new task, click on the "Add Task" button. This will open a new page where you can enter a title and body for the task. Once you have entered the task information, click on the "Submit" button.

To view all of your tasks, click on the "All Tasks" button. This will open a new page that lists all of your tasks. You can mark tasks as complete by clicking on the checkbox next to the task.

## image

![image](screenshots/homeScreen.png)
![image](screenshots/addTaskScreen.png)
![image](screenshots/allTasksScreen.png)

## For next Lab (Lab 27)

After editing the code, I have made the following changes:

### MainActivity

In the `MainActivity` class, I've updated the code to allow users to click on task buttons (Task 1 and Task 2) and navigate to the Task Detail page with the respective task names and settings page to enter his username and save it.

![image](screenshots/NewHomeScreen.png)

### Settings

To access settings, click on the "Settings" button. In the settings page, you can enter and save your username. This username will be used in various parts of the app.

### SettingActivity

In the `SettingActivity` class, I've added the ability for users to enter and save their username. The username is stored in shared preferences and can be accessed throughout the app.

![image](screenshots/settingPage.png)


### TaskDetailActivity

The `TaskDetailActivity` class remains unchanged and is responsible for displaying task details based on the selected task.

![image](screenshots/TaskDetailsPage.png)
![image](screenshots/TaskDetailsPage.png)
