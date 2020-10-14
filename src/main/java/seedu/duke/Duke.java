package seedu.duke;

import seedu.duke.commands.Command;
import seedu.duke.project.ProjectList;
import seedu.duke.task.TaskList;
import seedu.duke.ui.Ui;

import java.util.Scanner;

public class Duke {

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) throws DukeExceptions {

        ProjectList projects = new ProjectList();
        Ui ui = new Ui();

        ui.printWelcome();
        Scanner in = new Scanner(System.in);

        boolean isExit = false;
        while (!isExit) {
            try {
                Command commandInput = Parser.parse(in.nextLine());
                ui.printLine();
                String output = commandInput.executeCommand(projects);
                ui.printOutput(output);
                isExit = commandInput.isExit();
            } catch (NullPointerException | StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
                System.out.println(e);
            }
            ui.printLine();
        }

    }

}