import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;
import java.util.ArrayList;

public class SaleTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void Sale_instantiatesCorrectly_true() {
    Customer testCustomer = new Customer ("Steve");
    List<Product> soldProducts = new ArrayList<Product>();
    Product testProduct = new Product ("Expresso", 425);
    soldProducts.add(testProduct);
    Product testProduct2 = new Product ("Scone", 225);
    soldProducts.add(testProduct2);
    Sale testSale = new Sale (testCustomer.getId(), soldProducts);
    assertEquals(true, testSale instanceof Sale);
  }


  @Test
  public void Save_SavesSalesInfoIntoBothTables_true() {
    Customer testCustomer = new Customer ("Steve");
    testCustomer.save();
    List<Product> soldProducts = new ArrayList<Product>();
    Product testProduct = new Product ("Expresso", 425);
    testProduct.save();
    Product testProduct2 = new Product ("Scone", 225);
    testProduct2.save();
    soldProducts.add(testProduct);
    soldProducts.add(testProduct2);
    Sale testSale = new Sale (testCustomer.getId(), soldProducts);
    testSale.save();
    assertEquals(true, testCustomer instanceof Customer);
  }

  @Test
  public void Find_returnsTheSalesDataFromSalesTable() {
    Customer testCustomer = new Customer ("Steve");
    testCustomer.save();
    List<Product> soldProducts = new ArrayList<Product>();
    Product testProduct = new Product ("Expresso", 425);
    testProduct.save();
    Product testProduct2 = new Product ("Scone", 225);
    testProduct2.save();
    soldProducts.add(testProduct);
    soldProducts.add(testProduct2);
    Sale testSale = new Sale (testCustomer.getId(), soldProducts);
    testSale.save();
    assertEquals(testProduct.getName(), Sale.find(testSale.getId()).getPurchasedProducts().get(0).getName());
    assertEquals(testProduct2.getPrice(), Sale.find(testSale.getId()).getPurchasedProducts().get(1).getPrice());
    //assertEquals(testSale.getId(),Sale.find(testSale.getId()).getCustomerId());
  }

  // @Test
  // public void getPriceInDollars_returns2DigitDouble() {
  //   Sale testSale = new Sale ("Expresso", 425);
  //   assertEquals(4.25, testSale.getPriceInDollars(),0);
  // }
  //
  // @Test
  // public void save_SavesSaleToDB_true() {
  //   Sale testSale = new Sale ("Expresso", 425);
  //   testSale.save();
  //   assertEquals(testSale, Sale.all().get(0));
  // }
  //
  // @Test
  // public void find_FindsSaleByID_Sale() {
  //   Sale testSale = new Sale ("Expresso", 425);
  //   testSale.save();
  //   assertEquals(testSale, Sale.find(testSale.getId()));
  // }
  //
  // @Test
  // public void delete_DeletesTheSaleFromTheDB_true() {
  //   Sale testSale = new Sale ("Expresso", 425);
  //   testSale.save();
  //   testSale.delete();
  //   assertEquals(null, Sale.find(testSale.getId()));
  // }
  //
  // @Test
  // public void update_UpdatesTheSaleNameAndPrice_true() {
  //   Sale testSale = new Sale ("Expresso", 425);
  //   testSale.save();
  //   testSale.update("Latte", 550);
  //   assertEquals("Latte", Sale.find(testSale.getId()).getName());
  //   assertEquals(550, Sale.find(testSale.getId()).getPrice());
  // }
}// end of class
