package fileProcessing;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import todoList.Todo;

/**
 * The CSVReader class reads and process a CVS file to extract its data
 */
public class CSVReader {

  private String fileName;
  private ArrayList<Todo> todoLists;
  private String[] headerNames;
  private static final Integer PRIORITY_DEFAULT = 3;
  private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("M/d/yyyy");


  /**
   * Constructor for ProcessFileCSV class
   * @param fileName - String, representing the name of the csv file
   */
  public CSVReader(String fileName) {
    this.fileName = fileName;
    this.todoLists = this.processInput(fileName);
  }

  /**
   * Reads the csv file
   * For each line in the file implements regex to split the todoLists data of that line
   * @param fileName - String, representing the name of the csv file
   * @return - List, representing the todoLists data of each line
   */
  private List<String[]> readFile(String fileName) {
    List<String[]> lines = new ArrayList<>();
    try (BufferedReader inputFile = new BufferedReader(new FileReader(fileName))) {
      String line;
      while ((line = inputFile.readLine()) != null) {
        //Add quotes around id values to be consistent with rest of values
        line = line.replaceFirst(",", "\"\"\",");
        line = "\"\"\"" + line;
        lines.add(line.substring(3, line.length() - 3).split("\"\"\",\"\"\""));
      }
    } catch (FileNotFoundException fnfe) {
      System.out.println("*** OOPS! A file was not found : " + fnfe.getMessage());
      fnfe.printStackTrace();
    } catch (IOException ioe) {
      System.out.println("Something went wrong! : " + ioe.getMessage());
      ioe.printStackTrace();
    }

    headerNames = lines.remove(0);
    return lines;
  }
  /**
   * Calls the readFile method
   * Assigns the key and value of each todoList in a Map
   * @param fileName - String, representing the csv file
   * @return - ArrayList, containing all the maps with its key and value of each todoList
   */
  private ArrayList<Todo> processInput(String fileName) {
    List<String[]> lines = this.readFile(fileName);
    ArrayList<Todo> todoList = new ArrayList<>();
    for (String[] line: lines) {

      Integer id = Integer.parseInt(line[0]);
      String text = line[1];
      Boolean completed = Boolean.parseBoolean(line[2]);
      LocalDate dueDate = parseDueDate(line[3]);
      Integer priority = parsePriority(line[4]);
      String category = parseCategory(line[5]);

      //creating a to do object to add in the todoList in order to save what's in the file
      Todo todo = new Todo(id, text, completed, dueDate, priority, category);
      //System.out.println(todo.toString());
      todoList.add(todo);
    }
    return todoList;
  }

  /**
   *
   * @param date - representing the due date
   * @return LocalDate - representing the due date
   */
  private LocalDate parseDueDate(String date) {
    if(date.equals("?")) {
      return null;
    }
    //LocalDate date1 = LocalDate.parse(date, DATE_FORMATTER);
    //System.out.println(date1 + "\n");
    return LocalDate.parse(date, DATE_FORMATTER);
  }

  /**
   *
   * @param category - representing the category
   * @return Category - representing the category
   */
  private String parseCategory(String category) {
    if(category.equals("?")) {
      return null;
    }
    return category;
  }

  /**
   *
   * @param priority - representing the priority
   * @return Priority - representing the priority
   */
  public Integer parsePriority (String priority){
    if (priority.equals("?")){
      return PRIORITY_DEFAULT;
    }
    return Integer.parseInt(priority);
  }

  /**
   * @return - String[], representing the keys of the csv file
   */
  public String[] getHeaderNames() {
    return this.headerNames;
  }

  /**
   * @return  - Arraylist, representing the key and value of all todoLists
   */
  public ArrayList<Todo> getTodoLists() {
    return todoLists;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) { return true; }
    if (o == null || getClass() != o.getClass()) { return false; }
    CSVReader csvReader = (CSVReader) o;
    return fileName.equals(csvReader.fileName);// && todoList.equals(csvReader.todoList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(fileName, todoLists);
  }


  @Override
  public String toString() {
    return "CSVReader{" +
        "fileName='" + fileName + '\'' +
        ", todoLists=" + todoLists +
        ", headerNames=" + Arrays.toString(headerNames) +
        '}';
  }
}
