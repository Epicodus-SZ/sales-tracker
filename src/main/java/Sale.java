import org.sql2o.*;
import java.util.List;
//import java.util.ArrayList;
import java.sql.Timestamp;

public class Sale {
  private int id;
  private int customer_id;
  private Timestamp sale_date;
  private List<Product> productsPurchased;

  public static final int THIRTYDAYREVGOAL = 8000000;
  public static final int SIXTYFIVEDAYREVGOAL = 16000000;

  public Sale(int customer_id, List<Product> productsPurchased){
    this.customer_id = customer_id;
    this.productsPurchased = productsPurchased;
  }

  /// Getters ///////////////////////////
  public int getId(){
    return id;
  }

  @Override
  public boolean equals(Object otherSale){
    if (!(otherSale instanceof Sale)) {
      return false;
    } else {
      Sale newSale = (Sale) otherSale;
      return this.getId() == newSale.getId();
    }
  }

  // public static List<Sale> all() {
  //   String sql = "SELECT id, name FROM sales";
  //   try(Connection con = DB.sql2o.open()) {
  //    return con.createQuery(sql).executeAndFetch(Sale.class);
  //   }
  // }
  //
  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO sales (customer_id, sale_date) VALUES (:customer_id, :sale_date)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("customer_id", this.customer_id)
        .addParameter("sale_date", this.sale_date)
        .executeUpdate()
        .getKey();

      for (Product productPurchased : this.productsPurchased) {
        String sql2 = "INSERT INTO sold_products (product_id, sale_id, price) VALUES (:product_id, :sale_id, :price)";
        con.createQuery(sql2)
          .addParameter("product_id", productPurchased.getId())
          .addParameter("sale_id", this.getId())
          .addParameter("price", productPurchased.getPrice())
          .executeUpdate();
      } //end of for loop
    } //end of try
} //end of save method

  // public static Sale find(int id) {
  //   try(Connection con = DB.sql2o.open()) {
  //     String sql = "SELECT * FROM sales where id=:id";
  //     Sale customer = con.createQuery(sql)
  //       .addParameter("id", id)
  //       .executeAndFetchFirst(Sale.class);
  //     return customer;
  //   }
  // }
  //
  // public void delete() {
  //   try(Connection con = DB.sql2o.open()) {
  //   String sql = "DELETE FROM sales WHERE id = :id";
  //   con.createQuery(sql)
  //     .addParameter("id", id)
  //     .executeUpdate();
  //   }
  // }
  //
  // public void update(String name, int price) {
  //   try(Connection con = DB.sql2o.open()) {
  //     String sql = "UPDATE sales SET name = :name, price = :price WHERE id = :id";
  //     con.createQuery(sql)
  //       .addParameter("name", name)
  //       .addParameter("price", price)
  //       .addParameter("id", id)
  //       .executeUpdate();
  //   }
  // }
}  //end of class
