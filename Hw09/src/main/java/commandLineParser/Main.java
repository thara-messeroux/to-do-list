package commandLineParser;

import fileProcessing.CSVReader;
import fileProcessing.CSVWriter;
import todoList.DisplayController;
import todoList.Todo;
import todoList.Todo.TodoGenerator;
import todoList.TodoList;

public class Main {

  public Main(String[] args1) {
  }

  public static void main(String[] args) {
    // Add all command options
    OptionAddControl optionAddControl = new OptionAddControl();
    Options options = optionAddControl.addAllCommandOptions();

    // Parse command line arguments
    CommandLine cmd = new CommandLine();
    cmd.parser(options, args);

    // Read todolist file
    CSVReader csvFile = new CSVReader(cmd.getCsvFile());
    TodoList initialTodoList = new TodoList(csvFile.getTodoLists());

    // Update todolist if needed
    if (cmd.hasAddTodo() || cmd.hasCompleteTodo()) {
      if (cmd.hasAddTodo()) {
        System.out.println("\n\u001B[31mAdding New Todo...\u001B[0m");
        Todo.TodoGenerator newTodo = new TodoGenerator(cmd.getTodoArguments(),
            csvFile.getTodoLists().size());
        initialTodoList.addToDo(newTodo.getTodo());
      }
      if (cmd.hasCompleteTodo()) {
        System.out.println("\n\u001B[31mCompleting a Todo...\u001B[0m");
        initialTodoList.completeToDo(cmd.getTodoToComplete());
      }
      // Call csv writer to update the file
      CSVWriter csvWriter = new CSVWriter(cmd.getCsvFile());
      csvWriter.updateCSVFile(initialTodoList);
    }

    // Display todolist if specified
    if (cmd.hasDisplay()) {
      TodoList todoListToDisplay =  new TodoList(initialTodoList.getTodoLists());
      DisplayController displayController = new DisplayController(cmd.hasShowIncomplete(),
          cmd.hasShowCategory(), cmd.hasSortByDate(), cmd.hasSortByPriority(), cmd.getCategory(),
          todoListToDisplay);
    }
  }

}
