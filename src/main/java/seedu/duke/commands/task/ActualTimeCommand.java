package seedu.duke.commands.task;

import seedu.duke.DukeExceptions;
import seedu.duke.commands.Command;
import seedu.duke.member.TeamMember;
import seedu.duke.project.Project;
import seedu.duke.task.Task;
import seedu.duke.ui.Ui;

import java.util.ArrayList;
import java.util.HashMap;

import static seedu.duke.Parser.getHashValue;

public class ActualTimeCommand extends Command {

    private int taskIndex;
    private int durationInMinutes;
    private int projectIndex;
    HashMap<String, String> params;

    public ActualTimeCommand(HashMap<String, String> params, int projectIndex)
            throws DukeExceptions {
        assert projectIndex >= 0: projectIndex;
        this.params = params;
        this.projectIndex = projectIndex;
        this.parse();
    }

    public void parse() throws DukeExceptions {
        try {
            taskIndex = Integer.parseInt(getHashValue(params, "t")) - 1;
            int hours = Integer.parseInt(getHashValue(params, "h"));
            int minutes = Integer.parseInt(getHashValue(params, "m"));
            durationInMinutes = hours * 60 + minutes;
        } catch (NumberFormatException e) {
            throw new DukeExceptions("invalidTaskID");
        }
    }

    public String executeCommand(ArrayList<Project> projects, ArrayList<TeamMember> members) throws DukeExceptions {
        try {
            Project project = projects.get(projectIndex);
            Task task = project.getTask(taskIndex);
            if (!task.isDone()) {
                throw new DukeExceptions("taskNotDone");
            }
            task.addActual(durationInMinutes);
            int hours = task.getActual() / 60;
            int minutes = task.getActual() % 60;
            return Ui.printActualDurationAddedMessage(task.getDescription(), hours, minutes);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeExceptions("invalidTaskID");
        }
    }

    public Boolean isExit() {
        return false;
    }

}