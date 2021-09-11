# TerminalToDoApp
Designed a program that would accept input from a command line regarding to-dos a user might want to add, complete, edit or view (including filtering options)

![ToDo](https://media.github.ccs.neu.edu/user/8933/files/bd06e880-1246-11ec-8a50-c1b3e44948f8)


Short Description of the TODO Application:
This program is building a command line TODO application. The system will allow the user to add and track the status of their TODOs by due date, category, priority, and status(complete/incomplete). The application stores all TODOs in a CSV file. The CSV has 6 columns named id, text, completed, due, priority, and category.

Todo data structure
A Todo consists of the following information:
 text - a description of the task to be done. This field is required.
completed - indicates whether the task is completed or incomplete. If not specified, this field should be false by default. However, it should be possible to create a new Todo with ‘completed’ set to true.
due - a due date. This field is optional.
priority - an integer indicating the priority of the todo. This field is optional. If the user chooses to specify a priority, it must be between 1 and 3, with 1 being the highest priority. If no priority is specified, the todo can be treated as lowest priority (i.e., 3).
category - a user-specified String that can be used to group related todos, e.g., “school”, “work”, “home”. This field is optional.

The TODO App was programmed this way:

The commandLineParser package is responsible to read in the options:
It contains 5 different classes:
CommandLine
Main
Option
OptionAddControl
Options

CommandLine Class:

Because the TODO app can take in a lot of options, doing the command line with a switch would have been very long and inefficient, so I decided to use an option class to program the command line, using the following structure.
Created a method called ‘parser’, which will receive 2 fields:
The validOptions, which will hold the commands of the TODO application l30
From these options I extracted all of the commands that are required, which is the CSV file l32
Checking if the arguments are valid command before proceeding l38

Main Class:

Get the list of all the possible options, and parses in the commands that the user provided | l17-18
Read in whatever options the user provided and Parse the commandLine arguments  | l21-22
Pass in a CSV File, then Create a brand new toDoList, based on whatever CSV file the user passes, by calling the getTodoList method. | l25-26
Update toDoList if needed  | l29-35

Option Class:

It contains each individual option

OptionAddControl class:

Adds and create the different options 
Stores all the command options

Options Class:

Stores the list of options
We have the option to provide which CSV file you want to use
The options can be controlled in “Edit Run Configuration” where we can add the appropriate CSV file options, such as --csv-file <path/to/file> (todos.csv) due date, priority of the task, complete, or incomplete.

The fileProcessing package is responsible for processing the files.
It contains 2 different classes:
CSVReader
CSVWriter
1. CSVReader class
The CSVReader class reads and process a CSV file to extract its data
2. CSVWriter class
CSVWriter Class  write a file out of a template by including the information of a todo

The todoList package is responsible for creating the todoList.
It contains 4 different classes and 1 interface:
Display
DisplayController
ITodoList
Todo
TodoList
1. Display class
Class Display, displays a todolist in the terminal

2. DisplayController class
Class DisplayController, display the todolist based of the filter and sorting requirements given
3. ITodoList  Interface
Interface ITodoList contains methods that should be represented in the todolist class
4. Todo class
Class Todo, representing an individual task todo including its information
It has a collection of attributes such as:
Description
isCompleted
dueDate
priority
category
Id
Priorities
Date Time Formatter
5. TodoList class
Class TodoList represents a list of todos.  It’s an array list of todos, todo objects
From there you can:
Create a todoList
Get a todoList
Add a todo item
Complete a todo item
Sort the todo item by date
Sort by priority
Filter out Incomplete ones
Filter the category


