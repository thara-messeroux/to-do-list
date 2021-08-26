package commandLineParser;

import java.util.Objects;

/**
 * class Option, represents a command option and its information
 */
public class Option {

  private String opt;
  private Boolean isRequired;
  private Boolean hasArgs;
  private String requiredOption;

  /**
   * Constructor for class Option
   * @param opt - String, representing the command option
   * @param isRequired - Boolean, representing if the option is required
   * @param hasArgs - Boolean, representing if the option expects arguments
   * @param requiredOption - String, representing the name of another option that is required for
   *                       it to be valid command
   */
  public Option(String opt, Boolean isRequired, Boolean hasArgs, String requiredOption) {
    this.opt = opt;
    this.isRequired = isRequired;
    this.hasArgs = hasArgs;
    this.requiredOption = requiredOption;
  }

  /**
   * Constructor for class
   * @param opt - String, representing the command option
   * @param isRequired - Boolean, representing if the option is required
   * @param hasArgs - Boolean, representing if the option expects arguments
   */
  public Option(String opt, Boolean isRequired, Boolean hasArgs) {
    this.opt = opt;
    this.isRequired = isRequired;
    this.hasArgs = hasArgs;
  }

  /**
   * Empty Constructor
   */
  public Option() {}


  /**
   * @return - String, representing the command option
   */
  public String getOpt() {
    return opt;
  }

  /**
   * @return - Boolean, representing if the option is required
   */
  public Boolean getRequired() {
    return isRequired;
  }

  /**
   * @return - Boolean, representing if the option expects arguments
   */
  public Boolean getHasArgs() {
    return hasArgs;
  }

  /**
   * @return - String, representing the requiredOption
   */
  public String getRequiredOption() {
    return requiredOption;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) { return true; }
    if (!(o instanceof Option)) { return false; }
    Option option = (Option) o;
    return getOpt().equals(option.getOpt()) && isRequired.equals(option.isRequired) && getHasArgs()
        .equals(option.getHasArgs()) && getRequiredOption().equals(option.getRequiredOption());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getOpt(), isRequired, getHasArgs(), getRequiredOption());
  }

  @Override
  public String toString() {
    return "Option{" +
        "optionName='" + opt + '\'' +
        ", isRequired=" + isRequired +
        ", hasArgs=" + hasArgs +
        ", requiredOption='" + requiredOption + '\'' +
        '}';
  }
}
