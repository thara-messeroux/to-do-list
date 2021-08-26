package todoList;

import static org.junit.Assert.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.junit.Before;
import org.junit.Test;

public class TodoListTest {
  TodoList testTodoList;
  TodoList testTodoList2;
  ArrayList<Todo> todoLists;
  ArrayList<Todo> todoLists2;
  Todo testTodo;
  Todo testTodo2;
  Todo testTodo3;
  String description;
  Boolean isCompleted;
  Boolean isCompleted2;
  LocalDate dueDate;
  LocalDate dueDate2;
  Integer priority;
  String category;
  Integer id;
  Integer id2;
  List<Integer> todosToBeCompleted;


  @Before
  public void setUp() throws Exception {
    description = "Make a phone call";
    isCompleted = false;
    isCompleted2 = true;
    dueDate = LocalDate.of(2021, 10, 05);
    dueDate2 = LocalDate.of(2022, 10, 05);
    priority = 3;
    category = "private";
    testTodo = new Todo(null, description, isCompleted, dueDate, priority, category);
    testTodo2 = new Todo();
    testTodo3 = new Todo(null, "Complete spreadsheet", isCompleted2, dueDate2, 1,
        category);
    todoLists = new ArrayList<Todo>();
    todoLists.add(testTodo);
    testTodoList = new TodoList(todoLists);
    todosToBeCompleted = new ArrayList<Integer>();
  }

  @Test
  public void getTodoLists() {
    assertEquals(todoLists, testTodoList.getTodoLists());
  }

  @Test
  public void testHashCode() {
    int hash = Objects.hash(todoLists);
    assertEquals(hash, testTodoList.hashCode());
  }

  @Test
  public void testToString() {
    String expectedString = "TodoList{" +
        "todoLists=" + todoLists +
        '}';
    assertEquals(expectedString, testTodoList.toString());
  }
}
