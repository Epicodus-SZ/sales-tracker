import org.junit.*;
import static org.junit.Assert.*;

public class ProductTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void Product_instantiatesCorrectly_true() {
    Product testProduct = new Product ("Expresso", 425);
    assertEquals(true, testProduct instanceof Product);
  }

  @Test
  public void getPriceInDollars_returns2DigitDouble() {
    Product testProduct = new Product ("Expresso", 425);
    assertEquals(4.25, testProduct.getPriceInDollars(),0);
  }

  @Test
  public void save_SavesProductToDB_true() {
    Product testProduct = new Product ("Expresso", 425);
    testProduct.save();
    assertEquals(testProduct, Product.all().get(0));
  }

  @Test
  public void find_FindsProductByID_Product() {
    Product testProduct = new Product ("Expresso", 425);
    testProduct.save();
    assertEquals(testProduct, Product.find(testProduct.getId()));
  }

  // @Test
  // public void delete_DeletesTheProductFromTheDB_true() {
  //   Product testProduct = new Product ("Expresso", 425);
  //   testProduct.save();
  //   testProduct.delete();
  //   assertEquals(null, Product.find(testProduct.getId()));
  // }
}
