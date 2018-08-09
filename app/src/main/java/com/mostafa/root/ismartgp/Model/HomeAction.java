package com.mostafa.root.ismartgp.Model;

public class HomeAction {
    String  action_id;
    String current_action;
    String next_action;
    int action_count;

    public HomeAction(){}

    public HomeAction(int action_count) {
        this.action_count = action_count;
    }

    public HomeAction(String current_action, String next_action, int action_count) {
        this.current_action = current_action;
        this.next_action = next_action;
        this.action_count = action_count;
    }

    public HomeAction(String current_action, String next_action) {
        this.current_action = current_action;
        this.next_action = next_action;
    }

    public HomeAction(String action_id, String current_action, String next_action, int action_count) {
        this.action_id = action_id;
        this.current_action = current_action;
        this.next_action = next_action;
        this.action_count = action_count;
    }

    public HomeAction(String next_action) {
        this.next_action = next_action;
    }

    public int getAction_count() {
        return action_count;
    }

    public void setAction_count(int action_count) {
        this.action_count = action_count;
    }

    public String getAction_id() {
        return action_id;
    }

    public void setAction_id(String action_id) {
        this.action_id = action_id;
    }

    public String getCurrent_action() {
        return current_action;
    }

    public void setCurrent_action(String current_action) {
        this.current_action = current_action;
    }

    public String getNext_action() {
        return next_action;
    }

    public void setNext_action(String next_action) {
        this.next_action = next_action;
    }
}
