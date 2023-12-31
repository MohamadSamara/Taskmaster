package com.love2code.taskmaster.activity.Enum;

import androidx.annotation.NonNull;

public enum State {
    NEW("New"),
    ASSIGNED("Assigned"),
    IN_PROGRESS("In Progress"),
    COMPLETE("Complete");


    private final String taskText;

    State(String productText) {
        this.taskText = productText;
    }

    public static State fromString(String possibleTaskText){
        for(State task : State.values()){
            if (task.taskText.equals(possibleTaskText)){
                return task;
            }
        }
        return null;
    }

    @NonNull
    @Override
    public String toString(){
        if(taskText == null){
            return "";
        }
        return taskText;
    }


}
