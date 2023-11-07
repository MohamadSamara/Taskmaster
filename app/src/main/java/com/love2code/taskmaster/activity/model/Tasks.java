package com.love2code.taskmaster.activity.model;

import com.love2code.taskmaster.activity.Enum.State;

//TODO: step: 2-1: make data class

public class Tasks {

    private Long id;
    private String title;
    private String body;
    private State state;

    public Tasks(String title, String body, State state) {
        this.title = title;
        this.body = body;
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
