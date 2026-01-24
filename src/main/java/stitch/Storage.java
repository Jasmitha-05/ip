package stitch;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import java.util.ArrayList;

/**
 * Storage class handles the loading and saving of tasks to a file in order to
 * retrieve the pre-existing list whenever the application is running.
 */
public class Storage {
    private String path = "./data/stitch.txt";
    String text;

    /**
     * Returns an ArrayList of Tasks loaded from the stitch file that was previously
     * saved.
     * 
     * @return ArrayList<Task> tasks containing all tasks loaded from stitch file.
     */
    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<Task>();

        try {
            File file = new File(path);
            file.getParentFile().mkdirs();
            file.createNewFile();
            BufferedReader read = new BufferedReader(new FileReader(file));

            while ((text = read.readLine()) != null) {
                Task task = parse(text);
                tasks.add(task);
            }

            read.close();
        } catch (Exception e) {
            System.out.println("Error in loading file");
        }
        return tasks;
    }

    /**
     * Saves the current list of tasks to the stitch file.
     * 
     * @param tasks ArrayList<Task> containing all tasks in the current list to be
     *              saved locally on disk.
     */
    public void save(ArrayList<Task> tasks) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));

            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                writer.write(format(task));
                writer.newLine();
            }

            writer.close();
        } catch (Exception e) {
            System.out.println("Error in saving file");
        }
    }

    /**
     * Returns a Task object parsed from the given text line from the stitch file.
     *
     * @param text The text line to parse.
     * @return Task object parsed from the given text line.
     * @throws IllegalArgumentException for unknown/invalid task types.
     * @throws StitchException
     */
    private Task parse(String text) throws IllegalArgumentException, StitchException {
        String[] split = text.split(" \\| ");
        String symbolString = split[0];
        String isDone = split[1];
        String description = split[2];

        if (symbolString.equals("T")) {
            Task todo = new ToDo(description);
            if (isDone.equals("1")) {
                todo.markAsDone();
            }
            return todo;
        } else if (symbolString.equals("D")) {
            String by = split[3];
            Task deadline = new Deadline(description, by);
            if (isDone.equals("1")) {
                deadline.markAsDone();
            }
            return deadline;
        } else if (symbolString.equals("E")) {
            String from = split[3];
            String to = split[4];
            Task event = new Event(description, from, to);
            if (isDone.equals("1")) {
                event.markAsDone();
            }
            return event;
        } else {
            throw new IllegalArgumentException("Unknown task");
        }
    }

    /**
     * Returns a string representation of each Task in the list to be saved in the
     * stitch file.
     * 
     * @param task The Task object to format as a string representation.
     * @return A string representation of each Task in the list.
     * @throws IllegalArgumentException for unknown/invalid task types.
     */
    private String format(Task task) throws IllegalArgumentException {
        String isDone = task.isDone ? "1" : "0";
        if (task instanceof ToDo) {
            return "T | " + isDone + " | " + task.description;
        } else if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            return "D | " + isDone + " | " + task.description + " | " + deadline.by.format(Task.INPUT);
        } else if (task instanceof Event) {
            Event event = (Event) task;
            return "E | " + isDone + " | " + task.description + " | " + event.from.format(Task.INPUT) + " | "
                    + event.to.format(Task.INPUT);
        } else {
            throw new IllegalArgumentException("Unknown task");
        }
    }
}
