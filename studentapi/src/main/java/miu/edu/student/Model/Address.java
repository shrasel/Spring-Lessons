package miu.edu.student.Model;

public class Address {

  private String street;
  private String city;
  private String state;
  private String postCode;

  public Address(String street, String city, String state, String postCode) {
    this.street = street;
    this.city = city;
    this.state = state;
    this.postCode = postCode;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getPostCode() {
    return postCode;
  }

  public void setPostCode(String postCode) {
    this.postCode = postCode;
  }

  @Override
  public String toString() {
    return (
      "Address [street=" +
      street +
      ", city=" +
      city +
      ", state=" +
      state +
      ", postCode=" +
      postCode +
      "]"
    );
  }
}
