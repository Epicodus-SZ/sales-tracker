import org.junit.rules.ExternalResource;
import org.sql2o.*;

public class DatabaseRule extends ExternalResource {

  @Override
  protected void before() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/sales_tracker_test", null, null);
  }

  @Override
  protected void after() {
    try(Connection con = DB.sql2o.open()) {
      String deleteCustomersQuery = "DELETE FROM customers *;";
      con.createQuery(deleteCustomersQuery).executeUpdate();

      String deleteProductsQuery = "DELETE FROM products *;";
      con.createQuery(deleteProductsQuery).executeUpdate();

      String deleteSalesQuery = "DELETE FROM sales *;";
      con.createQuery(deleteSalesQuery).executeUpdate();

      String deleteSoldProductQuery = "DELETE FROM sold_products *;";
      con.createQuery(deleteSoldProductQuery).executeUpdate();

    }
  }

}
