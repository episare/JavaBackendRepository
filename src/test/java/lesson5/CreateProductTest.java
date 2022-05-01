package lesson5;

import com.github.javafaker.Faker;
import lesson5.api.ProductService;
import lesson5.dto.Product;
import lesson5.utils.RetrofitUtils;
import okhttp3.ResponseBody;
import org.apache.commons.lang3.ObjectUtils;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.*;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CreateProductTest {
    static ProductService productService;
    Product product = null;
    Faker faker = new Faker();
    static int id;

    @BeforeAll
    static void beforeAll() {
        productService = RetrofitUtils.getRetrofit().create(ProductService.class);
    }

    @BeforeEach
    void setUp() {
        product = new Product()
                .withTitle(faker.food().ingredient())
                .withCategoryTitle("Food")
                .withPrice((int) (Math.random() * 10000));
    }

    @Test
    @Order(1)
    void createProductInFoodCategoryTest() throws IOException {
        Response<Product> response = productService.createProduct(product).execute();
        id = response.body().getId();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
        assertThat(response.body().getTitle(), equalTo(product.getTitle()));
        assertThat(response.body().getCategoryTitle(), equalTo("Food"));
        assertThat(response.body().getPrice(), equalTo(product.getPrice()));
    }

    @Test
    void createProductInCategoryNotExistsTest() throws IOException {
        product.setCategoryTitle("Not Found");
        Response<Product> response = productService.createProduct(product).execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(false));
     }

    @Test
    void getProductsListTest() throws IOException {
        Response<List<Product>> response = productService.getProducts().execute();
        assertThat("Response is successful", response.isSuccessful(), CoreMatchers.is(true));
        assertThat("Response contains the product list", response.body().size(), greaterThan(0));
    }

    @Test
    @Order(2)
    void getProductByIdTest() throws IOException {
        Response<Product> responseCreated = productService.createProduct(product).execute();
        id = responseCreated.body().getId();
        Response<Product> response = productService.getProductById(id).execute();
        assertThat("Response is successful", response.isSuccessful(), CoreMatchers.is(true));
        assertThat(response.body().getId(), equalTo(id));
        assertThat(response.body().getTitle(), equalTo(responseCreated.body().getTitle()));
        assertThat(response.body().getCategoryTitle(), equalTo(responseCreated.body().getCategoryTitle()));
        assertThat(response.body().getPrice(), equalTo(responseCreated.body().getPrice()));
    }

    @Test
    void getProductByNotExistsIdTest() throws IOException {
        Response<Product> response = productService.getProductById(0).execute();
        assertThat("Response is not successful", response.isSuccessful(), CoreMatchers.is(false));
        assertThat("Code 404 - Not found", response.code(), equalTo(404));
    }

    @Test
    @Order(3)
    void updateProductByIdTest() throws IOException {
        Response<Product> responseCreated = productService.createProduct(product).execute();
        id = responseCreated.body().getId();

        Product productToModify = new Product()
                .withId(id)
                .withTitle(responseCreated.body().getTitle())
                .withCategoryTitle("Electronic")
                .withPrice(responseCreated.body().getPrice() * 2);

        Response<Product> responseModified = productService.modifyProduct(productToModify).execute();
        assertThat(responseModified.isSuccessful(), CoreMatchers.is(true));

        assertThat(responseModified.body().getId(), equalTo(responseCreated.body().getId()));
        assertThat(responseModified.body().getTitle(), equalTo(responseCreated.body().getTitle()));
        assertThat(responseModified.body().getCategoryTitle(), equalTo("Electronic"));
        assertThat(responseModified.body().getPrice(), equalTo(responseCreated.body().getPrice() * 2));
    }

    @Test
    void updateProductIdNotSetTest() throws IOException {
        Product productToModify = new Product()
                .withTitle("Update without ID")
                .withCategoryTitle("Electronic")
                .withPrice(1);

        Response<Product> responseModified = productService.modifyProduct(productToModify).execute();

        assertThat(responseModified.isSuccessful(), CoreMatchers.is(false));
        assertThat(responseModified.code(), greaterThanOrEqualTo(400));
        assertThat(responseModified.code(), lessThan(500));
    }

    @Test
    void updateProductTitleNotSetTest() throws IOException {
        Response<Product> responseCreated = productService.createProduct(product).execute();
        id = responseCreated.body().getId();

        Product productToModify = new Product()
                .withId(id)
                .withCategoryTitle("Electronic")
                .withPrice(1);

        Response<Product> responseModified = productService.modifyProduct(productToModify).execute();
        System.out.println(responseModified);
        assertThat(responseModified.isSuccessful(), CoreMatchers.is(true));
        assertThat(responseModified.code(), equalTo(200));
        assertThat(responseModified.body().getTitle(), CoreMatchers.nullValue());

    }

    @Test
    void deleteProductsByNotExistsIdTest() throws IOException {
        Response<ResponseBody> response = productService.deleteProduct(0).execute();
        assertThat("Response is not successful", response.isSuccessful(), CoreMatchers.is(false));
    }

    void tearDown() throws IOException {
        if (id > 0) {
            Response<ResponseBody> response = productService.deleteProduct(id).execute();
            assertThat(response.isSuccessful(), CoreMatchers.is(true));
            id = 0;
        }
    }

}
