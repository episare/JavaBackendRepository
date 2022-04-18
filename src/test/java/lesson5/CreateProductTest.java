package lesson5;

import com.github.javafaker.Faker;
import lesson5.api.ProductService;
import lesson5.dto.Product;
import lesson5.utils.RetrofitUtils;
import okhttp3.ResponseBody;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.*;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

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
    void CreateProductInFoodCategoryTest() throws IOException {
        Response<Product> response = productService.createProduct(product).execute();
        id = response.body().getId();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
        assertThat(response.body().getTitle(), equalTo(product.getTitle()));
        assertThat(response.body().getCategoryTitle(), equalTo("Food"));
        assertThat(response.body().getPrice(), equalTo(product.getPrice()));
    }

    @Test
    void getProductsListTest() throws IOException {
        Response<List<Product>> response = productService.getProducts().execute();
        assertThat("Response is successful", response.isSuccessful(), CoreMatchers.is(true));
        assertThat("Response contains the product list", response.body().size(), greaterThan(0));
    }

    @Test
    @Order(2)
    void getProductsByIdTest() throws IOException {
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

    @AfterEach
    void tearDown() throws IOException {
        if (id > 0) {
            Response<ResponseBody> response = productService.deleteProduct(id).execute();
            assertThat(response.isSuccessful(), CoreMatchers.is(true));
            id = 0;
        }
    }

}
