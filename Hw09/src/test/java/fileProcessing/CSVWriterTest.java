package fileProcessing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import org.junit.Before;
import org.junit.Test;
import todoList.Todo;
import todoList.TodoList;

public class CSVWriterTest {
  private String outputString;
  private TodoList toDoList;
  private Todo todo2;
  private BufferedWriter newWriter;
  private String expectedString;
  private CSVWriter testCSVWriter;
  private Integer id1;
  private String description1;
  private Boolean isCompleted1;
  private LocalDate dueDate1;
  private Integer priority1;
  private String category1;
  private Todo todo1;
  private Integer id2;
  private String description2;
  private Boolean isCompleted2;
  private LocalDate dueDate2;
  private Integer priority2;
  private String category2;

  @Before
  public void setUp() throws Exception {
    outputString = "file.csv";
    testCSVWriter = new CSVWriter(outputString);
    id1 = 2;
    description1 = "Do my hair";
    isCompleted1 = false;
    dueDate1 = LocalDate.now();
    priority1 = 2;
    category1 = "house";
    todo1 = new Todo(id1, description1, isCompleted1, dueDate1, priority1, category1);
    id2 = 6;
    description2 = "Exercise";
    isCompleted2 = false;
    dueDate2 = LocalDate.of(2019, 12, 17);
    priority2 = 2;
    category2 = "health";
    todo2 = new Todo(id2, description2, isCompleted2, dueDate2, priority2, category2);

    newWriter = new BufferedWriter((new FileWriter(outputString, false)));
    ArrayList<Todo> toDoLists = new ArrayList<>();
    toDoLists.add(todo1);
    toDoLists.add(todo2);
    toDoList = new TodoList(toDoLists);

    System.out.println(newWriter + " =/= " + testCSVWriter.getWriter());
  }
  @Test
  public void updateCSVFile() {
  }
  @Test
  public void testEquals() {
    assertTrue(testCSVWriter.equals(testCSVWriter));
    assertFalse(testCSVWriter.equals(outputString));
  }

  @Test
  public void testHashCode() {
    int hash = Objects.hash(testCSVWriter.getWriter());
    assertEquals(hash, testCSVWriter.hashCode());
  }

  @Test
  public void testToString() {
    expectedString = "CSVWriter{" +
        "writer=" + testCSVWriter.getWriter() +
        '}';
    assertEquals(expectedString, testCSVWriter.toString());
  }

}