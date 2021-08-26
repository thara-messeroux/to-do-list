package commandLineParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * class CommandLine, provides parsing for command line options
 */
public class CommandLine {

  private HashMap<String, String> todoArguments = new HashMap<>();
  private HashMap<String, String> inputCommands = new HashMap<>();
  private List<Integer> todoToComplete = new ArrayList<>();
  private List<String> requiredCommands;
  private String csvFile = "";
  public String GUIDE = "\nUsage:\n"
      + "NOTE: some options are required and some need to be given with another command to be valid\n"
      + "--csv-file <path/to/file> is The CSV file containing the todos. "
      + "This command is required along with its file!\n "
      + "--add-todo is given when a new todo wants to be added If this option is provided, then "
      + "--todo-text must also be provided.\n"
      + "--todo-text <description of todo> is a description of the todo\n"
      + "--completed (Optional) Sets the completed status of a new todo to true\n"
      + "--due <due date> (Optional) Sets the due date of a new todo, "
      + "due date must be given in M/d/yyyy format.\n"
      + "--priority <1,2 or 3> (Optional) Sets the priority of a new todo. "
      + "The value can be 1, 2, or 3.\n"
      + "--category <a category name> (Optional) Sets the category of a new todo."
      + "--complete-todo <id> Mark the Todo with the provided ID as complete.\n"
      + "--display Display todos. \n"
      + "--show-incomplete (Optional) If --display is provided, only incomplete todos should be "
      + "displayed.\n"
      + "--show-category <category> (Optional) If --display is provided, only todos with the given "
      + "category should be displayed.\n"
      + "--sort-by-date (Optional) If --display is provided, sort the list of todos by date order "
      + "(ascending). Cannot be combined with --sort-by- priority.\n"
      + "--sort-by-priority (Optional) If --display is provided, sort the list of todos by priority "
      + "(ascending). Cannot be combined with --sort-by-date.\n\n"
      + "Examples:\n"
      + "The following example adds a new todo and display the todolist by incomplete todo and "
      + "sorted by date\n"
      + "--csv-file todos.csv --add-todo --todo-text Clean kitchen  --completed true "
      + "--due 07/04/2021 --priority 2 --category home --complete-todo 3 --display --show-incomplete"
      + " --sort-by-date\n";


  /**
   * Constructor for class CommandLine
   */
  public CommandLine() { }

  /**
   * Parse the arguments according to the specified options.
   * @param validOptions - Options, representing the valid command options
   * @param args - String[] -
   */
  public void parser(Options validOptions, String[] args) {
    List<String> commands =  validOptions.optCommands();
    this.requiredCommands = validOptions.requiredOptions();

    for (int i = 0; i< args.length; i++){
      if (commands.contains(args[i])) { // If this command is an option
        Option command = validOptions.getCommandOption(args[i]); // get that command option

        // Check command was not already given, except for --complete-todo
//        if (inputCommands.containsKey(args[i]) && (!args[i].equals("--complete-todo"))) {
//          throw new IllegalArgumentException("\nERROR: ERROR: command " + args[i] +
//              " can only be given once" + GUIDE);
//        }

        // If arguments are expected with that command, retrieve and store arguments
        if (command.getHasArgs()) {
          String argument = extractArguments(args, i+1);

          // Add TodoList ID to complete later
          if (command.getOpt().equals("--complete-todo")) {
            todoToComplete.add(Integer.parseInt(argument)); }


          // Store argument value
          if (command.getOpt().equals("--csv-file")) {
            this.csvFile = argument;
          } else {
            todoArguments.put(command.getOpt(), argument);
          }
        }
        //Save command to validate later in case this command needs another command to be valid
        inputCommands.put(command.getOpt(), command.getRequiredOption());
      }
    }
    // Perform validations
    verifyArgumentProvided();
    verifyValidCommands();
    verifyRequiredCommands();
    verifyOneSortOnly();
  }


  /**
   * Helper method that returns the argument(s) value of a command option
   *
   * Since the value arguments of an option could have more than one word then it retrieves all the
   * input words followed by that command until another command is found or the end of the command
   * line args is reached
   *
   * @param args - String[], representing the command line args
   * @param index - Integer, representing the index to start getting arguments values
   * @return
   */
  private String extractArguments(String[] args, Integer index) {
    String arguments = "";
    if (!index.equals(args.length)) {
      while (!args[index].startsWith("--")) {
        arguments += args[index] + " ";
        index++;
        if (index.equals(args.length)) { break; }
      }
    }
    if (!arguments.equals("")) {
      arguments = arguments.substring(0, arguments.length()-1);
    }
    return arguments;
  }


  /**
   * Verifies that arguments that are required were provided
   * If they are not provided an exception is thrown
   */
  public void verifyArgumentProvided() {
    // Check TodoText is given an argument
    if (inputCommands.containsKey("--todo-text") && todoArguments.get("--todo-text").equals("")) {
      throw new IllegalArgumentException("\nERROR: ERROR: "
          + "--todo-text provided but no description given" + GUIDE);
    }

    // Check csvFile is given an argument
    if (csvFile.equals("")) {
      throw new IllegalArgumentException("\nERROR: ERROR: "
          + "--csv-file provided but no file path given" + GUIDE);
    }
  }


  /**
   * Validates if a given given command is valid or not
   *
   * In case a command needs another command along with it to be valid check that that other
   * command was given as well, If it wasn't given it's counted as an InvalidCommand and an
   * exception is thrown
   *
   * For example for "--show-incomplete" the command "--display" needs to be given as well etc.
   * Throws an exception if command is invalid
   */
  public void verifyValidCommands() {
    for (Map.Entry<String,String> entry: inputCommands.entrySet()) {
      if (entry.getValue() != null) { // if the command need to be validated
        if (!inputCommands.containsKey(entry.getValue())) {
          throw new IllegalArgumentException("\nERROR: ERROR: " + entry.getKey() +
              " is provided, but not " +  entry.getValue() + GUIDE);
        }
      }
    }
  }


  /**
   * Verifies that required command options were given
   *
   * In this case the "--csv-file" is the only required command
   */
  public void verifyRequiredCommands() {
    for (String command: requiredCommands) {
      if (!inputCommands.containsKey(command)) {
        throw new IllegalArgumentException("\nERROR: ERROR: command \"" + command +
            "\" is required but was not provided" + GUIDE);
      }
    }
  }


  /**
   * Verifies that only one sort option if given at a time
   * An exception is thrown if both sorts are provided
   */
  public void verifyOneSortOnly() {
    if (inputCommands.containsKey("--sort-by-date") &
        inputCommands.containsKey("--sort-by-priority")) {
      throw new IllegalArgumentException("\nERROR: ERROR: "
          + "Only one sort can be be applied at a time" + GUIDE);
    }
  }


  /**
   * @return Boolean, true if --add-todo command was given
   */
  public boolean hasAddTodo() { return (this.inputCommands.containsKey("--add-todo"));}

  /**
   * @return Boolean, true if --complete-todo command was given
   */
  public boolean hasCompleteTodo() { return (this.inputCommands.containsKey("--complete-todo")); }

  /**
   * @return Boolean, true if --display command was given
   */
  public boolean hasDisplay() { return (this.inputCommands.containsKey("--display")); }

  /**
   * @return Boolean, true if --show-incomplete command was given
   */
  public boolean hasShowIncomplete() { return (this.inputCommands.containsKey("--show-incomplete")); }

  /**
   * @return Boolean, true if --show-category command was given
   */
  public boolean hasShowCategory() { return (this.inputCommands.containsKey("--show-category")); }

  /**
   * @return Boolean, true if --sort-by-date command was given
   */
  public boolean hasSortByDate() { return (this.inputCommands.containsKey("--sort-by-date")); }

  /**
   * @return Boolean, true if --sort-by-priority command was given
   */
  public boolean hasSortByPriority() { return (this.inputCommands.containsKey("--sort-by-priority")); }


  /**
   * @return - String, representing the category to show
   */
  public String getCategory() { return todoArguments.get("--show-category"); }

  /**
   * @return - HashMap, representing the argument values
   */
  public HashMap<String, String> getTodoArguments() { return todoArguments; }


  /**
   * @return - List, representing the ID of todolist to complete
   */
  public List<Integer> getTodoToComplete() { return todoToComplete; }


  /**
   * @return - String, representing the csvFile name
   */
  public String getCsvFile() { return csvFile; }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof CommandLine)) {
      return false;
    }
    CommandLine that = (CommandLine) o;
    return getTodoArguments().equals(that.getTodoArguments()) && inputCommands
        .equals(that.inputCommands) && getTodoToComplete().equals(that.getTodoToComplete())
        && requiredCommands.equals(that.requiredCommands) && getCsvFile().equals(that.getCsvFile());
  }

  @Override
  public int hashCode() {
    return Objects
        .hash(getTodoArguments(), inputCommands, getTodoToComplete(), requiredCommands,
            getCsvFile());
  }

  @Override
  public String toString() {
    return "CommandLine{" +
        "todoArguments=" + todoArguments +
        ", todoToComplete=" + todoToComplete +
        ", csvFile='" + csvFile + '\'' +
        '}';
  }
}
