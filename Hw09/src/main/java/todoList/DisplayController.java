package todoList;

import java.util.Objects;

/**
 * class DisplayController, display the todolist based of the filter and sorting requirements given
 */
public class DisplayController {

  private Boolean showIncomplete;
  private Boolean showCategory;
  private String category;
  private Boolean sortByDate;
  private Boolean sortByPriority;
  private TodoList todoList;

  /**
   * Constructor for class DisplayController
   * @param showIncomplete - Boolean, representing to  only display incomplete todos
   * @param showCategory - Boolean, representing to only display todo by a given category
   * @param sortByDate - Boolean,  representing to display the list sorted by date
   * @param sortByPriority - Boolean,  representing to display the list sorted by priority
   * @param category -String, representing the todolist category to display by
   * @param todoList - Todolist, representing the todolist to display
   */
  public DisplayController(Boolean showIncomplete, Boolean showCategory, Boolean sortByDate,
      Boolean sortByPriority, String category, TodoList todoList) {
    this.showIncomplete = showIncomplete;
    this.showCategory = showCategory;
    this.category = category;
    this.sortByDate = sortByDate;
    this.sortByPriority = sortByPriority;
    this.todoList = todoList;
    displayTodos();
  }

  /**
   * Checks if a sort of filter is required and applies it to the todolist
   * Then display the todolist
   */
  private void displayTodos() {
    if (showCategory) {
      System.out.println("\n\u001B[31mFiltering by Category...\u001B[0m\n");
      if (category != null) {
        todoList = todoList.filterCategory(category);
      }
    }

    if (showIncomplete) {
      System.out.println("\n\u001B[31mFiltering by Incomplete...\u001B[0m\n");
      todoList = todoList.filterIncomplete();
    }

    if (sortByDate) {
      System.out.println("\n\u001B[31mSorting by Date...\u001B[0m\n");
      todoList = todoList.sortByDate();
    }

    if (sortByPriority) {
      System.out.println("\n\u001B[31mSorting by Priority...\u001B[0m\n");
      todoList = todoList.sortByPriority();
    }

    Display display = new Display(todoList.getTodoLists());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof DisplayController)) {
      return false;
    }
    DisplayController that = (DisplayController) o;
    return showIncomplete.equals(that.showIncomplete) && showCategory.equals(that.showCategory)
        && category.equals(that.category) && sortByDate.equals(that.sortByDate) && sortByPriority
        .equals(that.sortByPriority) && todoList.equals(that.todoList);
  }

  @Override
  public int hashCode() {
    return Objects
        .hash(showIncomplete, showCategory, category, sortByDate, sortByPriority, todoList);
  }

  @Override
  public String toString() {
    return "DisplayController{" +
        "showIncomplete=" + showIncomplete +
        ", showCategory=" + showCategory +
        ", category='" + category + '\'' +
        ", sortByDate=" + sortByDate +
        ", sortByPriority=" + sortByPriority +
        ", todoList=" + todoList +
        '}';
  }
}
