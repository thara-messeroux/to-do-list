package commandLineParser;

import java.util.Objects;

/**
 * class OptionAddControl, add all the command options in an Options object
 */
public class OptionAddControl {

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

  /**
   * Constructor for class OptionAddControl
   */
  public OptionAddControl() {
  }

  /**
   * Adds command options
   * @return - Options, representing all the command options
   */
  public Options addAllCommandOptions() {
    Options options = new Options();

    options.addOption(csvFile);
    options.addOption(addTodo);
    options.addOption(todoText);
    options.addOption(completed);
    options.addOption(due);
    options.addOption(priority);
    options.addOption(category);
    options.addOption(completeTodo);
    options.addOption(display);
    options.addOption(showIncomplete);
    options.addOption(showCategory);
    options.addOption(sortByDate);
    options.addOption(sortByPriority);

    return options;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof OptionAddControl)) {
      return false;
    }
    OptionAddControl that = (OptionAddControl) o;
    return csvFile.equals(that.csvFile) && addTodo.equals(that.addTodo) && todoText
        .equals(that.todoText) && completed.equals(that.completed) && due.equals(that.due)
        && priority
        .equals(that.priority) && category.equals(that.category) && completeTodo
        .equals(that.completeTodo) && display.equals(that.display) && showIncomplete
        .equals(that.showIncomplete) && showCategory.equals(that.showCategory) && sortByDate
        .equals(that.sortByDate) && sortByPriority.equals(that.sortByPriority);
  }

  @Override
  public int hashCode() {
    return Objects
        .hash(csvFile, addTodo, todoText, completed, due, priority, category, completeTodo, display,
            showIncomplete, showCategory, sortByDate, sortByPriority);
  }

  @Override
  public String toString() {
    return "OptionAddControl{" +
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
  }
}
