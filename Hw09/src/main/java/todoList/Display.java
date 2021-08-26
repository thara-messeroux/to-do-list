package todoList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * class Display, displays a todolist in the terminal
 */
public class Display {

  private String[] header = {"ID", "Text", "Completed", "Due Date", "Priority", "Category"};
  private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy");


  /**
   * constructor for class Display
   * @param todoList - List, representing the todolist to display
   */
  public Display(List<Todo> todoList) {
    displayTodoList(todoList);
  }

  /**
   * Prints the todolist with space format, color and background
   * @param todoList - List, representing the todolist to display
   */
  public void displayTodoList(List<Todo> todoList) {
    if (todoList.isEmpty()) {
      System.out.println("\n\u001B[31mThe todolist requesting to be displayed has no results...\u001B[0m\n");
    } else {
      List<String[]> todosFormatted = formatTodoList(todoList);
      System.out.format("\u001B[40m\u001B[32m%-5s%-40s%-15s%-15s%-15s%-15s\u001B[0m\n",
          header[0], header[1], header[2], header[3], header[4], header[5]);
      for (String[] row : todosFormatted) {
        System.out.format("\u001B[42m\u001B[30m%-5s%-40s%-15s%-15s%-15s%-15s\u001B[0m\n",
            row[0], row[1], row[2], row[3], row[4], row[5]);
      }
    }
  }

  /**
   * Splits the fields of a Todo and replaces any null value with a "?"
   * @param todoList - List, representing the todolist to format
   * @return - List, representing the formatted todolist
   */
  public List<String[]> formatTodoList(List<Todo> todoList) {
    List<String[]> todosFormatted = new ArrayList<>();
    String[] line;
    for (Todo todo: todoList) {
      line = todo.toString().split("\",\"");
      for (int i=3; i<line.length; i++) {
        // Change all the null to "?"
        if (line[i].equals("null")) {
          line[i] = "?";
          i++;
        }
      }
      // Establish the date format to print with
      if (!line[3].equals("?")) {
        LocalDate date1 = LocalDate.parse(line[3], DateTimeFormatter.ofPattern("yyyy-M-d"));
        line[3] =  date1.format(DATE_FORMATTER);
      }
      todosFormatted.add(line);
    }
    return todosFormatted;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Display)) {
      return false;
    }
    Display display = (Display) o;
    return Arrays.equals(header, display.header);
  }

  @Override
  public int hashCode() {
    return Arrays.hashCode(header);
  }

  @Override
  public String toString() {
    return "Display{" +
        "header=" + Arrays.toString(header) +
        '}';
  }
}
