package commandLineParser;

import org.junit.Before;
import org.junit.Test;

public class MainTest {

  private String[] args1 = {"--csv-file", "todosForMainTest.csv", "--add-todo", "--todo-text", "Clean kitchen",
      "--completed", "true", "--due", "07/04/2021", "--priority", "2", "--category", "home",
      "--complete-todo", "3", "--display", "--show-incomplete", "--sort-by-date"};

  @Before
  public void setUp() throws Exception {
  }

  @Test
  public void main() {
    Main.main(args1);

  }
}