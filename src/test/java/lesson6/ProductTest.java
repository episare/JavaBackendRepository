package lesson6;

import com.github.javafaker.Faker;

import lesson6.db.dao.ProductsMapper;
import lesson6.db.model.Products;
import org.apache.ibatis.exceptions.PersistenceException;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductTest extends BaseTest {
    Products products;
    Faker faker = new Faker();
    static String newProductTitle;
    static Integer productId = 0;

    @Test
    @Order(1)
    void createProductInFoodCategoryTest() {
        int id = productsMapper.insert(products);
        assert (id == 1);
        sqlSession.commit();

        productsExample.createCriteria().andTitleEqualTo(newProductTitle);
        List<Products> productsList = productsMapper.selectByExample(productsExample);
        assertThat(productsList.size(), equalTo(1));
        Products createdProduct = productsList.get(0);
        productId = createdProduct.getId();

        assertThat(createdProduct.getTitle(), equalTo(products.getTitle()));
        assertThat(createdProduct.getPrice(), equalTo(products.getPrice()));
        assertThat(createdProduct.getCategory_id(), equalTo(products.getCategory_id()));
    }

    @Test
    @Order(2)
    void createProductInCategoryNotExistsTest() {
        products.setCategory_id(999);
        String errMessage = "";
        try {
            productsMapper.insert(products);
        } catch (PersistenceException e) {
            errMessage = e.getMessage();
            sqlSession.rollback();
        }
        assertThat(errMessage, containsString("violates foreign key constraint"));
    }

    @Test
    @Order(3)
    void updateProductById() {
        Integer idToUpdate;

        idToUpdate = (productId == 0 ? 1 : productId);

        Products productsToUpdate = productsMapper.selectByPrimaryKey(idToUpdate);
        String originalProductTitle = productsToUpdate.getTitle();
        Integer originalProductPrice = productsToUpdate.getPrice();

        String updatedProductTitle = faker.food().fruit();
        Integer updatedProductPrice = (int) (Math.random() * 100 + 100);

        productsToUpdate.setTitle(updatedProductTitle);
        productsToUpdate.setPrice(updatedProductPrice);

        int updated = productsMapper.updateByPrimaryKey(productsToUpdate);
        assert (updated == 1);
        sqlSession.commit();
        Products updatedProduct = productsMapper.selectByPrimaryKey(idToUpdate);
        assertThat(updatedProduct.getId(), equalTo(idToUpdate));
        assertThat(updatedProduct.getTitle(), equalTo(updatedProductTitle));
        assertThat(updatedProduct.getPrice(), equalTo(updatedProductPrice));

        // rollback
        updatedProduct.setTitle(originalProductTitle);
        updatedProduct.setPrice(originalProductPrice);
        productsMapper.updateByPrimaryKey(updatedProduct);
        sqlSession.commit();

    }

    @BeforeEach
    void setUp() {
        productsExample.clear();
        productsMapper = sqlSession.getMapper(ProductsMapper.class);

        products = getNewProduct();
    }

    Products getNewProduct() {

        Products newProduct = new Products();
        newProductTitle = "Test " + faker.food().ingredient();
        newProduct.setTitle(newProductTitle);
        newProduct.setCategory_id(1);
        newProduct.setPrice((int) (Math.random() * 10000));

        return newProduct;
    }

    @AfterAll
    static void AfterAll() {
        productsExample.clear();
        productsExample.createCriteria().andTitleLike("Test%");
        productsMapper.deleteByExample(productsExample);
        sqlSession.commit();

        sqlSession.close();
    }
}
