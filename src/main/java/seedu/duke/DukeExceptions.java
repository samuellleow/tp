package seedu.duke;

import java.util.HashMap;
public class DukeExceptions extends Exception {

    private HashMap<String,String> exceptionMessageList = new HashMap<>();
    private String messageType;

    DukeExceptions(String messageType) {
        initializeMap();
        this.messageType = messageType;
    }


    public void initializeMap() {
        exceptionMessageList.put("Project","Command incomplete!, You need to include a project description.");
        exceptionMessageList.put("Delete Project","Command incomplete! You need to mention the project Id");
        exceptionMessageList.put("Task Description","Command incomplete!, You need to include a task description.");
        exceptionMessageList.put("Member","Command incomplete! You need to mention Member's name");
        exceptionMessageList.put("Delete Task","Command incomplete! You need to mention the project Id");
        exceptionMessageList.put("Switch","You are already in project view");
        exceptionMessageList.put("Add Project","You cannot add tasks in project view");
        exceptionMessageList.put("Add Task","You cannot add projects in task view");
        //file handler exceptions
        exceptionMessageList.put("Create File","The file could not be created");
        exceptionMessageList.put("Open File","The file could not be opened");
        exceptionMessageList.put("default","☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public String toString() {
        return exceptionMessageList.get(messageType);
    }
}
