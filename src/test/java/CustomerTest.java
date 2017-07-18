import org.junit.*;
import static org.junit.Assert.*;

public class CustomerTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void Customer_instantiatesCorrectly_true() {
    Customer testCustomer = new Customer ("Steve");
    assertEquals(true, testCustomer instanceof Customer);
  }

  @Test
  public void save_SavesCustomerToDB_true() {
    Customer testCustomer = new Customer ("Steve");
    testCustomer.save();
    assertEquals(true, Customer.all().get(0).equals(testCustomer));
  }

  @Test
  public void find_FindsCustomerByID_Customer() {
    Customer testCustomer = new Customer ("Steve");
    testCustomer.save();
    assertEquals(testCustomer, Customer.find(testCustomer.getId()));
  }
}
