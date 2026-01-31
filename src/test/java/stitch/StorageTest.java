package stitch;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

/**
 * Test non-trivial methods in Storage class just JUnit and Gradle
 */
public class StorageTest {
    private static final String PATH = "./data/stitch.txt";

    @Test
    public void saveAndLoad_multipleTasks_success() throws StitchException {
        Storage storage = new Storage();
        ArrayList<Task> tasks = new ArrayList<Task>();
        tasks.add(new ToDo("t1"));
        tasks.add(new Deadline("t2", "2025-11-25 16:01"));
        tasks.add(new Event("t3", "2025-12-1 2:00", "2025-12-3 15:00"));
        storage.save(tasks);
        ArrayList<Task> loadTasks = storage.load();

        assertEquals(3, loadTasks.size());
        assertTrue(loadTasks.get(0) instanceof ToDo);
        assertTrue(loadTasks.get(1) instanceof Deadline);
        assertTrue(loadTasks.get(2) instanceof Event);
    }

    @Test
    public void parseFormat_success() {
        Storage storage = new Storage();

        try {
            File file = new File("./data/stitch.txt");
            file.getParentFile().mkdirs();
            BufferedWriter write = new BufferedWriter(new FileWriter(file));
            write.write("T | 1 | write essay");
            write.close();
        } catch (Exception e) {
            fail();
        }
        ArrayList<Task> tasks = storage.load();
        assertEquals(1, tasks.size());
        assertTrue(tasks.get(0) instanceof ToDo);
        assertTrue(tasks.get(0).isDone);
        assertEquals("write essay", tasks.get(0).description);
    }

    @Test
    public void formatString_success() {
        Storage storage = new Storage();

        ArrayList<Task> tasks = new ArrayList<Task>();
        ToDo todo = new ToDo("write essay");
        tasks.add(todo);
        storage.save(tasks);

        try {
            BufferedReader read = new BufferedReader(new FileReader("./data/stitch.txt"));
            String text = read.readLine();
            read.close();
            assertEquals("T | 0 | write essay", text);
        } catch (Exception e) {
            fail();
        }
    }
}
