package miu.edu.bank;

import static org.hamcrest.CoreMatchers.equalTo;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import miu.edu.bank.Model.Account;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BankApplicationTests {

  @BeforeAll
  public static void setup() {
    RestAssured.port = 8080;
    RestAssured.baseURI = "http://localhost";
    RestAssured.basePath = "/accounts";
  }

  @Test
  public void testGetAccount() {
    Account newAccount = new Account("1111222233", "Shah Rasel", 22);
    RestAssured
      .given()
      .contentType("application/json")
      .body(newAccount)
      .when()
      .post("")
      .then()
      .statusCode(201);

    RestAssured
      .given()
      .when()
      .get("/1111222233")
      .then()
      .contentType(ContentType.JSON)
      .and()
      .statusCode(200)
      .and()
      .body("accountNo", equalTo("1111222233"))
      .body("accountHolder", equalTo("Shah Rasel"))
      .body("balance", equalTo(22));
  }

  @Test
  public void testCreateAccount() {
    Account newAccount = new Account("1111222233", "Shah Rasel", 22);
    RestAssured
      .given()
      .contentType("application/json")
      .body(newAccount)
      .when()
      .post("")
      .then()
      .statusCode(201)
      .and()
      .body("accountHolder", equalTo(newAccount.getAccountHolder()))
      .body("balance", equalTo(22))
      .body("accountNumber", equalTo(newAccount.getAccountNumber()));
  }

  @Test
  public void testRemoveAccount() {
    Account newAccount = new Account("1111222233", "Shah Rasel", 22);
    RestAssured
      .given()
      .contentType("application/json")
      .body(newAccount)
      .when()
      .post("")
      .then()
      .statusCode(201);
    RestAssured
      .given()
      .when()
      .delete("/" + newAccount.getAccountNumber())
      .then()
      .statusCode(204);
  }

  @Test
  public void testAccountDeposit() {
    // Add new account
    Account newAccount = new Account("1111222233", "Shah Rasel", 22);
    RestAssured
      .given()
      .contentType("application/json")
      .body(newAccount)
      .when()
      .post("")
      .then()
      .statusCode(201);

    // Deposit money to account
    RestAssured
      .given()
      .contentType("application/json")
      .post(
        "/deposit?amount=199.99&accountNumber=" + newAccount.getAccountNumber()
      )
      .then()
      .statusCode(200);

    // Check if Account balance is updated
    RestAssured
      .given()
      .when()
      .get("/" + newAccount.getAccountNumber())
      .then()
      .statusCode(200)
      .and()
      .body("balance", equalTo(199.99F));
  }

  @Test
  public void testAccountWithdraw() {
    // Add new book
    Account newAccount = new Account("1111222233", "Shah Rasel", 22);
    RestAssured
      .given()
      .contentType("application/json")
      .body(newAccount)
      .when()
      .post("")
      .then()
      .statusCode(201);

    // Withdraw money from account
    RestAssured
      .given()
      .contentType("application/json")
      .post(
        "/withdraw?amount=22.5&accountNumber=" + newAccount.getAccountNumber()
      )
      .then()
      .statusCode(200);

    // Check if Account balance is updated
    RestAssured
      .given()
      .when()
      .get("/" + newAccount.getAccountNumber())
      .then()
      .statusCode(200)
      .and()
      .body("balance", equalTo(22));
  }
}
