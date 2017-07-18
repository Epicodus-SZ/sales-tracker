import java.util.List;
import org.sql2o.*;

public class Customer {
  private int id;
  private String name;

  public Customer(String name){
    this.name = name;
  }

  /// Getters ///////////////////////////
  public String getName(){
    return this.name;
  }

  @Override
  public boolean equals(Object otherCustomer){
    if (!(otherCustomer instanceof Customer)) {
      return false;
    } else {
      Customer newCustomer = (Customer) otherCustomer;
      return this.getName().equals(newCustomer.getName());
    }
  }

  public static List<Customer> all() {
    String sql = "SELECT id, name FROM customers";
    try(Connection con = DB.sql2o.open()) {
     return con.createQuery(sql).executeAndFetch(Customer.class);
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO customers (name) VALUES (:name)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .executeUpdate()
        .getKey();
    }
  }

}
