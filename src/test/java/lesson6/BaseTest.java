package lesson6;

import lesson6.db.dao.CategoriesMapper;
import lesson6.db.dao.ProductsMapper;
import lesson6.db.model.Categories;
import lesson6.db.model.CategoriesExample;
import lesson6.db.model.Products;
import lesson6.db.model.ProductsExample;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.BeforeAll;

import java.io.IOException;
import java.io.InputStream;

public abstract class BaseTest {
    static SqlSessionFactory sqlSessionFactory;
    static SqlSession sqlSession;
    static CategoriesExample categoriesExample;
    static CategoriesMapper categoriesMapper;

    static ProductsExample productsExample;
    static ProductsMapper productsMapper;

    @BeforeAll
    static void BeforeAll() throws IOException {
        String resource = "mybatisConfig.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);

        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try {
            sqlSession = sqlSessionFactory.openSession();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        categoriesExample = new CategoriesExample();
        productsExample = new ProductsExample();

    }

}
