package todoList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * class Todolist, representing a list of todo tasks
 */
public class TodoList implements ITodoList{
  private ArrayList<Todo> todoLists;

  /**
   * Constructor for class Todolist
   * @param todoLists - ArrayList, represents the list of todos
   */
  public TodoList(ArrayList<Todo> todoLists) {
    this.todoLists = todoLists;
  }

  /**
   * @return - ArrayList, represents the list of todos
   */
  public ArrayList<Todo> getTodoLists() {
    return this.todoLists;
  }


  /**
   * checks that the id of the Todo to add doesn't already exists and adds new todo to the list
   * @param todoToBeAdded - Todo, representing the new todo to add
   */
  public void addToDo(Todo todoToBeAdded) {
    if (!(todoToBeAdded.getId() < todoLists.size())) {
      todoLists.add(todoToBeAdded);
    }

  }

  /**
   * Verify that the ID of the todo exist, the ids of the todos are define in chronological order
   * meaning that the size of the todolist represent the id of the last todo in the list
   * Changes the isComplete value to true of a todo(s)
   * @param todosToBeCompleted - List, representing the id's of the todos to complete
   */
  public void completeToDo(List<Integer> todosToBeCompleted) {
    for (Integer ID: todosToBeCompleted) {
      if (ID <= todoLists.size()) {
        //System.out.println("Completing Todo #" + ID);
        Todo newTodo = this.todoLists.get(ID-1);
        newTodo.setCompleted(true);
        this.todoLists.set(ID-1, newTodo); //create constants later
      } else {
        throw new IllegalArgumentException("\nERROR: ERROR: Todo with the ID" + ID + "does not exist");
      }
    }
  }


  /**
   * Sorts the todo list by date  and leaving the null date fields at the end
   * Defines a comparator to compare to the dates
   * @return Todolist - representing the list sorted by date
   */
  public TodoList sortByDate() {
    ArrayList<Todo> sortedTodoList = this.todoLists;
    Collections.sort(sortedTodoList, new Comparator<Todo>() {
      public int compare(Todo o1, Todo o2) {
        if (o1.getDueDate() == null & o2.getDueDate() == null)
          return 0;
        else if (o1.getDueDate() == null)
          return 1;
        else if (o2.getDueDate() == null)
          return -1;
        return o1.getDueDate().compareTo(o2.getDueDate());
      }
    });
    return new TodoList(sortedTodoList);
  }

  /**
   * Sorts the todo list by priority from 1 to 3
   * Defines a comparator to compare to the priorities
   * @return - Todolist - representing to the list sorted by priority
   */
  public TodoList sortByPriority() {
    ArrayList<Todo> sortedTodoList = this.todoLists;
    Collections.sort(sortedTodoList, new Comparator<Todo>() {
      public int compare(Todo o1, Todo o2) {
        return o1.getPriority().compareTo(o2.getPriority());
      }
    });
    return new TodoList(sortedTodoList);
  }

  /**
   * Iterates through the list to filter trough only the incomplete lists
   * @return - Todolist, representing the todolist filter by incomplete list
   */
  public TodoList filterIncomplete() {
    ArrayList<Todo> filteredToDoList = new ArrayList<>();
    for (Todo toDo : this.todoLists)
      if (toDo.getCompleted().equals(false))
        filteredToDoList.add(toDo);
    return new TodoList(filteredToDoList);
  }

  /**
   * Iterates through the list to filter by the given category
   * @param category - String, representing the category to filter by
   * @return - Todolist, representing the todolist filter by the given category
   */
  public TodoList filterCategory(String category) {
    ArrayList<Todo> filteredToDoList = new ArrayList<>();
    for (Todo toDo : this.todoLists)
      if (toDo.getCategory() != null)
        if (toDo.getCategory().equals(category))
          filteredToDoList.add(toDo);
    return new TodoList(filteredToDoList);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof TodoList)) {
      return false;
    }
    TodoList todoList = (TodoList) o;
    return getTodoLists().equals(todoList.getTodoLists());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getTodoLists());
  }

  @Override
  public String toString() {
    return "TodoList{" +
        "todoLists=" + todoLists +
        '}';
  }
}

