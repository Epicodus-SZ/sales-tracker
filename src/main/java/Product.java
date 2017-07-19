import java.util.List;
import org.sql2o.*;

public class Product {
  private int id;
  private String name;
  private int price;

  public Product(String name, int price){
    this.name = name;
    this.price = price;
  }

  /// Getters ///////////////////////////
  public String getName(){
    return this.name;
  }

  public int getId(){
    return id;
  }

  public int getPrice() {
    return this.price;
  }

  public double getPriceInDollars() {
     return Math.round(((double)this.price/100) * 100.0) / 100.0;
  }

  @Override
  public boolean equals(Object otherProduct){
    if (!(otherProduct instanceof Product)) {
      return false;
    } else {
      Product newProduct = (Product) otherProduct;
      return this.getName().equals(newProduct.getName()) &&
             this.getId() == newProduct.getId();
    }
  }

  public static List<Product> all() {
    String sql = "SELECT id, name FROM products";
    try(Connection con = DB.sql2o.open()) {
     return con.createQuery(sql).executeAndFetch(Product.class);
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO products (name, price) VALUES (:name, :price)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("price", this.price)
        .executeUpdate()
        .getKey();
    }
  }

  public static Product find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM products where id=:id";
      Product customer = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Product.class);
      return customer;
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
    String sql = "DELETE FROM products WHERE id = :id";
    con.createQuery(sql)
      .addParameter("id", id)
      .executeUpdate();
    }
  }

  public void update(String name, int price) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE products SET name = :name, price = :price WHERE id = :id";
      con.createQuery(sql)
        .addParameter("name", name)
        .addParameter("price", price)
        .addParameter("id", id)
        .executeUpdate();
    }
  }
}  //end of class
