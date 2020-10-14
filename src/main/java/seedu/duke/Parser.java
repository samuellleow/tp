package seedu.duke;


import seedu.duke.commands.Command;
import seedu.duke.commands.ExitCommand;
import seedu.duke.commands.member.AddTeamMemberCommand;
import seedu.duke.commands.member.DeleteTeamMemberCommand;
import seedu.duke.commands.member.ListTeamMembersCommand;
import seedu.duke.commands.project.ProjectCommand;
import seedu.duke.commands.project.ProjectListCommand;
import seedu.duke.commands.project.ProjectSelectCommand;
import seedu.duke.commands.task.TaskCommand;
import seedu.duke.commands.task.TaskDeleteCommand;
import seedu.duke.commands.task.TaskListCommand;
import seedu.duke.commands.task.TaskSelectCommand;

public class Parser {

    private static final String INPUT_COMMAND_BYE = "bye";
    private static final String INPUT_COMMAND_LIST = "list";
    private static int projectIndex = -1;
    private static int taskIndex = -1;

    /**
     * Parses user input into project command for execution.
     *
     * @param inputCommand Full user input command string
     * @return Command object corresponding to the input command of the user
     */
    public static Command parse(String inputCommand) throws DukeExceptions {
        Command commandType;
        if (inputCommand.equals(INPUT_COMMAND_BYE)) {
            commandType = new ExitCommand();
        } else {
            commandType = checkAction(inputCommand); //----------ADD TRY CATCH (EXCEPTION)
        }
        return commandType;
    }

    static void setProjectIndex(int newIndex) {
        projectIndex = newIndex;
    }

    /**
     * Parses user input related to tasks into command for execution.
     *
     * @param inputCommand Full user input command string
     * @return Command object corresponding to the input command of the user
     */
    public static Command checkAction(String inputCommand) throws DukeExceptions {
        Command commandType = null;
        String[] inputs = inputCommand.split("\\s+");
        String taskType = inputs[0];
        String projectDescription = "";
        boolean isProjectListView = (projectIndex == -1); //In main project list view

        switch (taskType) {
        case "list":
            if (isProjectListView) {
                commandType = new ProjectListCommand();
            } else {
                commandType = new TaskListCommand(projectIndex);
            }
            break;
        case "select":
            if (isProjectListView) {
                projectIndex = Integer.parseInt(inputs[1]) - 1;
                commandType = new ProjectSelectCommand(projectIndex);
            } else {
                taskIndex = Integer.parseInt(inputs[1]) - 1;
                commandType = new TaskSelectCommand(taskIndex, projectIndex);
            }
            break;
        case "project":
            if (isProjectListView) {
                for (int i = 1; i < inputs.length; i++) {
                    projectDescription += inputs[i];
                }
                commandType = new ProjectCommand(projectDescription);
            } else {
                throw new DukeExceptions("Add Task"); // REPLACED WITH EXCEPTION
            }
            break;
        case "task":
            if (isProjectListView) {
                throw new DukeExceptions("Add Project"); //REPLACED WITH EXCEPTION
            } else {
                for (int i = 1; i < inputs.length; i++) {
                    projectDescription += inputs[i];
                }
                commandType = new TaskCommand(projectDescription, projectIndex);
            }
            break;
        case "delete":
            if (isProjectListView) {
                //Implement delete project here - Small Sam
            } else {
                taskIndex = Integer.parseInt(inputs[1]) - 1;
                commandType = new TaskDeleteCommand(taskIndex, projectIndex);
            }
            break;
        case "switch":
            if (!isProjectListView) {
                System.out.println("Switched to Project View!");
                projectIndex = -1;
            } else {
                throw new DukeExceptions("Switch"); // REPLACED WITH EXCEPTION
            }
            break;
        case "member":
            String memberName = inputCommand.split(" ")[1];
            commandType = new AddTeamMemberCommand(memberName);
            break;
        case "members":
            commandType = new ListTeamMembersCommand();
            break;
        case "remove":      
            commandType = new DeleteTeamMemberCommand(inputCommand.split(" ")[1]);
            break;
        default:
            break;
        }
        return commandType;
    }

}


