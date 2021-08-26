package todoList;

import static org.junit.Assert.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Objects;
import org.junit.Before;
import org.junit.Test;
import todoList.Todo.TodoGenerator;


public class TodoTest {
  Todo testTodo;
  Todo testTodo2;
  Todo testTodo3;
  Todo.TodoGenerator testTodo4;
  Todo.TodoGenerator testTodo5;
  String description;
  Boolean isCompleted;
  LocalDate dueDate;
  Integer priority;
  String category;
  Integer id;
  HashMap<String, String> todoArguments;
  HashMap<String, String> todoArguments2;


  @Before
  public void setUp() throws Exception {
    description = "Make a phone call";
    isCompleted = false;
    dueDate = LocalDate.of(2021, 10, 05);
    priority = 3;
    category = "private";
    id = 75;
    testTodo = new Todo(id, description, isCompleted, dueDate, priority, category);
    testTodo2 = new Todo();
    todoArguments = new HashMap<>();
    todoArguments.put("--todo-text", "To buy grocery");
    testTodo4 = new TodoGenerator(todoArguments, 5);
    todoArguments2 = new HashMap<>();
    todoArguments2.put("--todo-text", "To submit hw");
    todoArguments2.put("--category", "school");
    todoArguments2.put("--is-completed", "true");
    todoArguments2.put("--priority", "1");
    testTodo5 = new TodoGenerator(todoArguments2, 7);
  }


  @Test (expected = IllegalArgumentException.class)
  public void getPriority2() {
    testTodo3 = new Todo(id, description, isCompleted, dueDate, 5, category);
  }


  @Test
  public void getDescription() {
    assertEquals(description, testTodo.getDescription());
    assertEquals("To buy grocery", testTodo4.getTodo().getDescription());
  }

  @Test
  public void getCompleted() {
    assertEquals(isCompleted, testTodo.getCompleted());
  }


  @Test
  public void getDueDate() {
    assertEquals(dueDate, testTodo.getDueDate());
  }


  @Test
  public void getPriority() {
    assertEquals(priority, testTodo.getPriority());
    assertEquals(priority, testTodo2.getPriority());
    Integer one = 1;
    assertEquals(one, testTodo5.getTodo().getPriority());
  }

  @Test
  public void getCategory() {
    assertEquals(category, testTodo.getCategory());
  }

  @Test
  public void getId() {
    assertEquals(id, testTodo.getId());
  }

  @Test
  public void setCompleted() {
    testTodo.setCompleted(true);
    assertTrue(testTodo.getCompleted());
  }

  @Test
  public void testToString() {
    String expectedString = id +
        "\",\"" + description  +
        "\",\"" + isCompleted +
        "\",\"" + dueDate +
        "\",\"" + priority +
        "\",\"" + category;
    assertEquals(expectedString, testTodo.toString());
  }

  @Test
  public void testEquals() {
    assertTrue(testTodo.equals(testTodo));
    assertFalse(testTodo.equals(testTodo.getDueDate()));
    Todo todo2 = new Todo(id, description, isCompleted, dueDate, priority, category);
    assertTrue(testTodo.equals(todo2));
    todo2.setCompleted(true);
    Todo todo3 = new Todo(25, "different description", isCompleted, dueDate, priority,
        category);
    assertFalse(testTodo.equals(todo3));
  }

  @Test
  public void testHashCode() {
    int hash = Objects.hash(description, isCompleted, dueDate, priority, category, id);
    assertEquals(hash, testTodo.hashCode());
  }


}

