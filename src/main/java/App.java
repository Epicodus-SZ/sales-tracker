import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/products/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/products-new.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/products", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String productName = request.queryParams("name");
      int productPrice = Integer.parseInt(request.queryParams("price"));
      Product newProduct = new Product (productName, productPrice);
      try {
        newProduct.save();
      }
      catch (IllegalArgumentException exception){
        System.out.println(exception.getMessage());
        // response.redirect("/error");
      }
      response.redirect("/products");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/products", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("products", Product.all());
      model.put("template", "templates/products.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/products/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      int ProdNum = Integer.parseInt(request.params(":id"));
      Product sentProduct = Product.find(ProdNum);
      model.put("product", sentProduct);
      model.put("template", "templates/products-details.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/products/:id/update", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      int ProdNum = Integer.parseInt(request.params(":id"));
      Product updateProduct = Product.find(ProdNum);
      model.put("product", updateProduct);
      model.put("template", "templates/products-update.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

// Response to update post
    post("/products/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String productName = request.queryParams("name");
      int productPrice = Integer.parseInt(request.queryParams("price"));
      int prodId = Integer.parseInt(request.params(":id"));
      Product updateProduct = Product.find(prodId);
      updateProduct.update(productName, productPrice);
      response.redirect("/products");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/products/:id/delete", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Product.find(Integer.parseInt(request.params(":id"))).delete();
      response.redirect("/products");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());


    //Sales Routes
    get("/reports/sales/all", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      int ProdNum = Integer.parseInt(request.params(":id"));
      Product updateProduct = Product.find(ProdNum);
      model.put("sales", Sale.all());
      model.put("template", "templates/products-update.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

  } //end of main
} //end of class
