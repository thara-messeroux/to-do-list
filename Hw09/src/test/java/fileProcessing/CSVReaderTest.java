package fileProcessing;
import static org.junit.Assert.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import org.junit.Before;
import org.junit.Test;
import todoList.Todo;
public class CSVReaderTest {
  private String fileName;
  private ArrayList<Todo> todoList; //[todo1[Finish HW 9, false,], todo2, todo3, .....]
  private String[] headerNames;
  private CSVReader testCSVReader;
  private CSVReader testCSVReader2;

  @Before
  public void setUp() throws Exception {
    fileName = "todos.csv";
    testCSVReader = new CSVReader(fileName);
    todoList = testCSVReader.getTodoLists();
  }
  @Test
  public void parseDueDate() {
    Todo todo1 = todoList.get(0);
    LocalDate actualDate = todo1.getDueDate();
    LocalDate expectedDate = LocalDate.parse("2021-08-02");
    assertEquals(expectedDate, actualDate);
  }
  @Test
  public void parseCategory() {
    Todo todo1 = todoList.get(1);
    String actualCategory = todo1.getCategory();
    assertNull(actualCategory);
    todo1 = todoList.get(3);
    actualCategory = todo1.getCategory();
    String expectedCategory = "home";
    assertEquals(expectedCategory, actualCategory);
  }
  @Test
  public void getToDoLists() {
    assertEquals(5, todoList.size());
  }
  @Test
  public void testEquals() {
    testCSVReader2 = new CSVReader(fileName);
    assertTrue(testCSVReader.equals(testCSVReader));
    assertTrue(testCSVReader.equals(testCSVReader2));
    assertFalse(testCSVReader.equals(testCSVReader.getTodoLists()));
  }
  @Test
  public void testHashCode() {
    int hash = Objects.hash(fileName, testCSVReader.getTodoLists());
    assertEquals(hash, testCSVReader.hashCode());
  }


  @Test //todo test failing, I am not sure why
  public void testToString() {
    String expectedString = "CSVReader{" +
        "fileName='" + fileName + '\'' +
        ", todoLists=" + todoList +
        ", headerNames=" + Arrays.toString(testCSVReader.getHeaderNames()) +
        '}';
    assertEquals(expectedString, testCSVReader.toString());
  }
}