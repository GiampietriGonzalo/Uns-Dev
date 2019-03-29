package ayds.tp2.ej1;

public class User {

  private String name;
  private String lastName;

  public User(String name, String lastName) {
    this.name = name;
    this.lastName = lastName;
  }

  public String getName() {
    return name;
  }

  public String getLastName() {
    return lastName;
  }
}
