package lesson6;

import lesson6.db.dao.CategoriesMapper;
import lesson6.db.model.Categories;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CategoryTest extends BaseTest {
    static Long listNumberBeforeTest;

    @BeforeEach
    void setUp() {
        categoriesMapper = sqlSession.getMapper(CategoriesMapper.class);
        listNumberBeforeTest = categoriesMapper.countByExample(categoriesExample);
    }

    @Test
    @Order(1)
    void insertNewCategoryTest() {
//        System.out.println("insert listNumberBeforeTest: " + listNumberBeforeTest);

        Categories categories = new Categories();
        categories.setTitle("Test");
        for (int i = 0; i < 5; i++) {
            categoriesMapper.insert(categories);
        }
        sqlSession.commit();

        long listNumberAfterInsert = categoriesMapper.countByExample(categoriesExample);
        assertThat(listNumberBeforeTest, Matchers.lessThan(listNumberAfterInsert));
    }

    @Test
    @Order(2)
    void deleteCategoryByIdTest() {

        categoriesExample.createCriteria().andTitleEqualTo("Test");
        List<Categories> list = categoriesMapper.selectByExample(categoriesExample);

        if (list.size() > 0) {
            int id = list.get(0).getId();
            categoriesExample.clear();
            categoriesExample.createCriteria().andIdEqualTo(id);
            int deleted = categoriesMapper.deleteByExample(categoriesExample);
            sqlSession.commit();
            assert (deleted == 1);
        }
    }

    @Test
    @Order(3)
    void deleteCategoryByTitle() {

        categoriesExample.createCriteria().andTitleEqualTo("Test");
        List<Categories> list = categoriesMapper.selectByExample(categoriesExample);

        if (list.size() > 0) {
            int deleted = categoriesMapper.deleteByExample(categoriesExample);
//            System.out.println("found to delete: " + deleted);
            sqlSession.commit();
            assert (deleted > 0);
        }
    }

    @AfterAll
    static void AfterAll() {

        categoriesExample.clear();
        categoriesExample.createCriteria().andTitleEqualTo("Test");
        categoriesMapper.deleteByExample(categoriesExample);
        sqlSession.commit();

        sqlSession.close();

    }

}
