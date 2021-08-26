package todoList;

import java.util.List;

/**
 * Methods that should be represented in the todolist class
 */
public interface ITodoList {

  /**
   * Method that adds a new to do to the list
   * @param todoToBeAdded - Todo, the todo to be added to the list
   */
  public void addToDo(Todo todoToBeAdded);


  /**
   * Method that changes the status of the todo from incomplete to complete
   * @param todosToBeCompleted - List - list of todos that should be set completed
   */
  public void completeToDo(List<Integer> todosToBeCompleted);


  /**
   * Sorts the todos by date in ascending order
   * @return TodoList, sorted by date
   */
  public TodoList sortByDate();


  /**
   * Sorts the todos by priority in ascending order
   * @return TodoList, sorted by priority
   */
  public TodoList sortByPriority();


  /**
   * Filters the todo list to only include incomplete Todos
   * @return TodoList, filtered incomplete
   */
  public TodoList filterIncomplete();


  /**
   * Filters the list to only include Todos with a particular category
   * @param category - String, the category of the todo
   * @return TodoList filtered by category
   */
  public TodoList filterCategory(String category);
}
