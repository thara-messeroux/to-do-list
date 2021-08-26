package commandLineParser;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CommandLineTest {

  private CommandLine commandLine;
  private CommandLine commandLine2;
  private OptionAddControl optionAddControl;
  private Options options;
  private String expectedString;
  private String[] args1 = {"--csv-file", "todos.csv", "--add-todo", "--todo-text", "Clean kitchen",
      "--completed", "true", "--due", "07/04/2021", "priority", "2", "--category", "home",
      "--complete-todo", "3", "--display", "--show-incomplete", "--sort-by-date"};

  private String[] args2 = {"--csv-file", "todos.csv", "--add-todo", "--todo-text",
      "--due", "07/04/2021", "--category", "school",
      "--complete-todo", "4", "--display", "--show-by-category", "school", "--sort-by-priority"};

  private String[] args3 = {"--csv-file", "todos.csv", "--todo-text", "text1"};

  private String[] args4 = {"--complete-todo", "4"};
  private String[] args5 = {"--csv-file", "todos.csv", "--add-todo", "--todo-text", "Clean kitchen",
      "--completed", "true", "--due", "07/04/2021", "priority", "2", "--category", "home",
      "--complete-todo", "3", "--display", "--show-incomplete", "--sort-by-date"};

  private String[] args6 = {"--csv-file", "todos.csv", "--display", "--sort-by-date", "--sort-by-priority"};

  @Before
  public void setUp() throws Exception {
    optionAddControl = new OptionAddControl();
    options = optionAddControl.addAllCommandOptions();
    commandLine = new CommandLine();
    commandLine2 = new CommandLine();
  }

  @Test
  public void parser() {
    commandLine.parser(options, args1);
  }

  @Test (expected = IllegalArgumentException.class)
  public void verifyArgumentProvided() {
    // todo text is not given
    commandLine.parser(options, args2);
  }

  @Test (expected = IllegalArgumentException.class)
  public void verifyValidCommands() {
    // --todo-text is given but not --add-todo
    commandLine.parser(options, args3);
  }

  @Test (expected = IllegalArgumentException.class)
  public void verifyRequiredCommands() {
    // --csv-file which is required is not given
    commandLine.parser(options, args4);
  }

  @Test (expected = IllegalArgumentException.class)
  public void verifyOneSortOnly() {
    // two sort options were given
    commandLine.parser(options, args6);
  }

  @Test
  public void hasAddTodo() {
    commandLine.parser(options, args1);
    assertTrue(commandLine.hasAddTodo());
  }

  @Test
  public void hasCompleteTodo() {
    commandLine.parser(options, args1);
    assertTrue(commandLine.hasCompleteTodo());
  }

  @Test
  public void hasDisplay() {
    commandLine.parser(options, args1);
    assertTrue(commandLine.hasDisplay());
  }

  @Test
  public void hasShowIncomplete() {
    commandLine.parser(options, args1);
    assertTrue(commandLine.hasShowIncomplete());
  }

  @Test
  public void hasShowCategory() {
    commandLine.parser(options, args1);
    assertFalse(commandLine.hasShowCategory());
  }

  @Test
  public void hasSortByDate() {
    commandLine.parser(options, args1);
    assertTrue(commandLine.hasSortByDate());
  }

  @Test
  public void hasSortByPriority() {
    commandLine.parser(options, args1);
    assertTrue(commandLine.hasAddTodo());
  }


  @Test
  public void testEquals() {
    commandLine.parser(options, args1);
    commandLine2.parser(options, args1);
    assertTrue(commandLine.equals(commandLine));
    assertTrue(commandLine.equals(commandLine2));
    assertFalse(commandLine.equals(args1));
  }


  @Test
  public void testToString() {
    commandLine.parser(options, args1);

    expectedString = "CommandLine{" +
        "todoArguments=" + commandLine.getTodoArguments() +
        ", todoToComplete=" + commandLine.getTodoToComplete()  +
        ", csvFile='" + commandLine.getCsvFile() + '\'' +
        '}';
    assertEquals(expectedString, commandLine.toString());
  }
}