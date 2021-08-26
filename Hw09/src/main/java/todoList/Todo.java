package todoList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Objects;

/**
 * class Todo, representing a task todo including its information
 */
public class Todo implements Comparable<Todo>{

  private String description;
  private Boolean isCompleted;
  private LocalDate dueDate;
  private Integer priority;
  private String category;
  private Integer id;
  private static final Integer PRIORITY_MIN = 1;
  private static final Integer PRIORITY_MAX = 3;
  private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("M/d/yyyy");


  /**
   * Constructor for class Todo
   * @param id - Integer, representing the identification of the todo
   * @param description - String, representing the todos descriptions
   * @param isCompleted - Boolean, representing if the todo is completed or not
   * @param dueDate - LocalDate, representing the due date of the todo
   * @param priority - Integer, representing the the priority of the todo
   * @param category - String, representing the todo category
   */
  public Todo(Integer id,String description, Boolean isCompleted, LocalDate dueDate, Integer priority,
      String category) {
    this.description = description;
    this.isCompleted = isCompleted;
    this.dueDate = dueDate;
    this.priority = this.validatePriority(priority); //need to validate
    this.category = category;
    this.id = id;
  }

  /**
   * Constructor for class Todo
   * Initialized with the only two fields that have defaults values
   */
  public Todo() {
    this.isCompleted = false;
    this.priority = PRIORITY_MAX;
  }

  /**
   * Validates that the priority given is in the range of 1 to 3
   * @param priority - Integer, priority to validate
   * @return - returns the priority if if valid
   */
  private Integer validatePriority(Integer priority) {
    if (priority < PRIORITY_MIN || priority > PRIORITY_MAX)
      throw new IllegalArgumentException("Priority must be between 1 and 3");
    else
      return priority;
  }

  /**
   * @return - String, representing the todos descriptions
   */
  public String getDescription() {
    return this.description;
  }

  /**
   * @return - Boolean, representing if the todo is completed or not
   */
  public Boolean getCompleted() {
    return this.isCompleted;
  }

  /**
   * @return - LocalDate, representing the due date of the todo
   */
  public LocalDate getDueDate() {
    return this.dueDate;
  }

  /**
   * @return  - Integer, representing the the priority of the todo
   */
  public Integer getPriority() {
    return this.priority;
  }

  /**
   * @return  - String, representing the todo category
   */
  public String getCategory() {
    return this.category;
  }

  /**
   * @return - String, representing the todo id
   */
  public Integer getId() {
    return this.id;
  }

  /**
   * @param completed - the value to set the completed
   */
  public void setCompleted(Boolean completed) {
    this.isCompleted = completed;
  }


  /**
   * Compares two Todo object
   * @param o - Todo, other Todo to compare
   * @return - Integer, representing resulting value of comparing both object
   */
  public int compareTo(Todo o) {
    return this.compareTo(o);
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) { return true; }
    if (!(o instanceof Todo)) { return false; }
    Todo todo = (Todo) o;
    return getDescription().equals(todo.getDescription()) && isCompleted.equals(todo.isCompleted)
        && getDueDate().equals(todo.getDueDate()) && getPriority().equals(todo.getPriority())
        && getCategory().equals(todo.getCategory()) && getId().equals(todo.getId());
  }

  @Override
  public int hashCode() {
    return Objects
        .hash(getDescription(), isCompleted, getDueDate(), getPriority(), getCategory(), getId());
  }

  @Override
  public String toString() {
    return id +
        "\",\"" + description  +
        "\",\"" + isCompleted +
        "\",\"" + dueDate +
        "\",\"" + priority +
        "\",\"" + category;
  }


  /**
   * nested class TodoGenerator, generates a Todo object
   */
  public static class TodoGenerator {
    private HashMap<String, String> todoArguments;
    private Todo todo = new Todo();
    private Integer idNum;

    /**
     * Constructor for nested class TodoGenerator
     * @param todoArguments - HashMap, representing the argument sof the todo
     * @param id - Representing the value of the last id in the current todolist
     */
    public TodoGenerator(HashMap<String, String> todoArguments, Integer id) {
      this.todoArguments = todoArguments;
      this.todo.id = id + 1;
      generateTodo();
    }

    /**
     * Generates a new Todo  Object based on the todo arguments given
     */
    private void generateTodo() {
      if (todoArguments.containsKey("--todo-text")) {
        todo.description = todoArguments.get("--todo-text");
      }
      if (todoArguments.containsKey("--is-completed")) {
        todo.isCompleted = Boolean.parseBoolean(todoArguments.get("--is.completed"));
      }
      if (todoArguments.containsKey("--due")) {
        todo.dueDate =  LocalDate.parse(todoArguments.get("--due"), DATE_FORMATTER);
      }
      if (todoArguments.containsKey("--priority")) {
        todo.priority = todo.validatePriority(Integer.parseInt(todoArguments.get("--priority")));
      }
      if(todoArguments.containsKey("--category")) {
        todo.category = todoArguments.get("--category");
      }
    }

    /**
     * @return - representing the todo object generated
     */
    public Todo getTodo() {
      return todo;
    }


    @Override
    public boolean equals(Object o) {
      if (this == o) { return true; }
      if (!(o instanceof TodoGenerator)) { return false; }
      TodoGenerator that = (TodoGenerator) o;
      return todoArguments.equals(that.todoArguments) && getTodo().equals(that.getTodo()) && idNum
          .equals(that.idNum);
    }

    @Override
    public int hashCode() {
      return Objects.hash(todoArguments, getTodo(), idNum);
    }

    @Override
    public String toString() {
      return "TodoGenerator{" +
          "todoArguments=" + todoArguments +
          ", todo=" + todo +
          ", idNum=" + idNum +
          '}';
    }
  }
}