package stitch;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

/**
 * Uses Storage class to handle the loading and saving of tasks to a file in order to
 * retrieve the pre-existing list whenever the application is running.
 */
public class Storage {
    private String path = "./data/stitch.txt";
    private String text;

    /**
     * Returns an ArrayList of Tasks loaded from the stitch file that was previously
     * saved.
     *
     * @return list of tasks containing all tasks loaded from stitch file.
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
                assert task != null : "task is null";
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
     * @param tasks list containing all tasks in the current list to be
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
     * @throws StitchException if the task details are invalid.
     */
    private Task parse(String text) throws StitchException { 
        String[] split = text.split(" \\| ");
        Task task = createTask(split);

        if (split[1].equals("1")) {
            task.markAsDone();
        }
        return task;
    }

    /**
     * Creates a Task object based on the type specified in the split array.
     * 
     * @param split The array containing task type and details.
     * @return Task object created based on the type specified.
     * @throws IllegalArgumentException for unknown task types.
     * @throws StitchException if the task details are invalid.
     */
    private Task createTask(String[] split) throws StitchException {
        /* Use ChatGPT to improve the previous parse method by extracting 
         * the method createTask to help adhere to SLAP.
         */                                 
        String taskType = split[0];
        String description = split[2];

        switch (taskType) {
        case "T":
            assert split.length >= 3 : "Missing information for todo task";
            return new ToDo(description);
        case "D":
            assert split.length >= 4 : "Missing information for deadline task";
            return new Deadline(description, split[3]);
        case "E":
            assert split.length >= 5 : "Missing information for event task";
            return new Event(description, split[3], split[4]);
        default:
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
