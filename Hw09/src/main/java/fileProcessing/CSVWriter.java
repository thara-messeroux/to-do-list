package fileProcessing;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;
import todoList.Todo;
import todoList.TodoList;

/**
 * CSVWriter Class  write a file out of a template by including the information
 * of a todo
 */
public class CSVWriter {

  private BufferedWriter writer;
  private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy");

  public CSVWriter(String outputString) {
    try {
      this.writer = new BufferedWriter(new FileWriter(outputString, false));
      System.out.println(writer.toString());
    } catch (
        FileNotFoundException fnfe) {
      System.out.println("*** OOPS! A file was not found : " + fnfe.getMessage());
      fnfe.printStackTrace();
    } catch (IOException ioe) {
      System.out.println("Something went wrong! : " + ioe.getMessage());
      ioe.printStackTrace();
    }
  }


  /**
   * Updates the CSVFile
   * @param todos - a list of todos
   */
  public void updateCSVFile(TodoList todos) {
    String write = "\"\"\"id\"\"\",\"\"\"text\"\"\",\"\"\"completed\"\"\",\"\"\"due\"\"\","
        + "\"\"\"priority\"\"\",\"\"\"category\"\"\"\n";
    ArrayList<todoList.Todo> arrayListOfTodos = todos.getTodoLists();
    for(Todo eachTodo : arrayListOfTodos){
      write += eachTodo.getId() + ",\"\"\"";
      write += eachTodo.getDescription() + "\"\"\",\"\"\"";
      write += eachTodo.getCompleted() + "\"\"\",\"\"\"";
      write += parseDate(eachTodo.getDueDate()) + "\"\"\",\"\"\"";
      write += eachTodo.getPriority() + "\"\"\",\"\"\"";
      write += parseCategory(eachTodo.getCategory()) + "\"\"\"\n";
    }
    try {
      // Write to the buffer first, when buffer's full, write in the file.
      writer.write(write);
      // Flush the data from the buffer into the file. Empty the buffer first
      writer.flush();
    } catch (IOException ioe) {
      System.out.println("Something went wrong! : " + ioe.getMessage());
      ioe.printStackTrace();
    }
  }

  public BufferedWriter getWriter() {
    return writer;
  }

  public String parseDate(LocalDate dateToParse) {
    if (dateToParse == null) {
      return "?";
    } else {
      String date = dateToParse.toString();
      LocalDate date1 = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-M-d"));
      date = date1.format(DATE_FORMATTER);

      return date;
    }
  }

  public String parseCategory(String category) {
    if (category == null) {
      return "?";
    }
    return category;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CSVWriter csvWriter = (CSVWriter) o;
    return writer.equals(csvWriter.writer);
  }
  @Override
  public int hashCode() {
    return Objects.hash(writer);
  }


  @Override
  public String toString() {
    return "CSVWriter{" +
        "writer=" + writer +
        '}';
  }
}

