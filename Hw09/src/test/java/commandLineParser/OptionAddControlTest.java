package commandLineParser;

import static org.junit.Assert.*;

import java.util.Objects;
import org.junit.Before;
import org.junit.Test;

public class OptionAddControlTest {

  private OptionAddControl optionAddControl;
  private String expectedString;
  private Option csvFile = new Option("--csv-file",  true, true);
  private Option addTodo = new Option("--add-todo", false, false, "--todo-text");
  private Option todoText = new Option("--todo-text", false, true, "--add-todo");
  private Option completed = new Option("--completed", false, false, "--add-todo");
  private Option due = new Option("--due", false, true, "--add-todo");
  private Option priority = new Option("--priority", false, true, "--add-todo");
  private Option category = new Option("--category", false, true, "--add-todo");
  private Option completeTodo = new Option("--complete-todo", false, true);
  private Option display = new Option("--display", false, false);
  private Option showIncomplete = new Option("--show-incomplete", false, false, "--display");
  private Option showCategory = new Option("--show-category", false, true, "--display");
  private Option sortByDate = new Option("--sort-by-date", false, false, "--display");
  private Option sortByPriority = new Option("--sort-by-priority", false, false, "--display");

  @Before
  public void setUp() throws Exception {
    optionAddControl = new OptionAddControl();
  }

  @Test
  public void testEquals() {
    assertTrue(optionAddControl.equals(optionAddControl));
    assertFalse(optionAddControl.equals(csvFile));
  }

  @Test
  public void testHashCode() {
    int hash = Objects
        .hash(csvFile, addTodo, todoText, completed, due, priority, category, completeTodo, display,
            showIncomplete, showCategory, sortByDate, sortByPriority);

    assertEquals(optionAddControl.hashCode(), hash);
  }

  @Test
  public void testToString() {
    expectedString = "OptionAddControl{" +
        "csvFile=" + csvFile +
        ", addTodo=" + addTodo +
        ", todoText=" + todoText +
        ", completed=" + completed +
        ", due=" + due +
        ", priority=" + priority +
        ", category=" + category +
        ", completeTodo=" + completeTodo +
        ", display=" + display +
        ", showIncomplete=" + showIncomplete +
        ", showCategory=" + showCategory +
        ", sortByDate=" + sortByDate +
        ", sortByPriority=" + sortByPriority +
        '}';
    assertEquals(expectedString, optionAddControl.toString());
  }
}