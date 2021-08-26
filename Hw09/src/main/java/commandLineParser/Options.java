package commandLineParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * class Options, stores a groups of command options
 */
public class Options {

  private List<Option> optionsList;

  /**
   * Constructor for class Options
   */
  public Options() {
    this.optionsList = new ArrayList<>();
  }

  /**
   * Add a new command option to the Options list
   * @param option - Option, representing the option to add
   */
  public void addOption(Option option) {
    if (!optionsList.contains(option)) {
      this.optionsList.add(option);
    }
  }


  /**
   * Returns a list of the valid commands options
   * @return - List, representing the valid command options
   */
  public List<String> optCommands () {
    List<String> optCommands = new ArrayList<>();
    for (Option optName: this.getOptionsList()) {
      optCommands.add(optName.getOpt());
    }
    return optCommands;
  }

  /**
   * Returns an option based on the command
   * @param optCommand - String, representing the command
   * @return Option, representing the option containing that command
   */
  public Option getCommandOption(String optCommand) {
    Option optionCopy = new Option();
    for (Option option: optionsList) {
      if (option.getOpt().equals(optCommand)) {
        optionCopy = option;
      }
    }
    return optionCopy;
  }

  /**
   * @return List, representing a list of all command options that are required
   */
  public List<String> requiredOptions() {
    List<String> requiredOptions = new ArrayList<>();
    for (Option option: optionsList) {
      if (option.getRequired()) {
        requiredOptions.add(option.getOpt());
      }
    }
    return requiredOptions;
  }

  /**
   * @return - List, representing the list of options
   */
  public List<Option> getOptionsList() {
    return optionsList;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) { return true; }
    if (!(o instanceof Options)) { return false; }
    Options options = (Options) o;
    return getOptionsList().equals(options.getOptionsList());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getOptionsList());
  }

  @Override
  public String toString() {
    return "Options{" +
        "options=" + optionsList +
        '}';
  }
}
