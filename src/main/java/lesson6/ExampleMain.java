package lesson6;

import lesson6.db.dao.CategoriesMapper;
import lesson6.db.model.Categories;
import lesson6.db.model.CategoriesExample;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ExampleMain {
    static SqlSession sqlSession;
    public static void main(String[] args) throws IOException {
        String resource = "mybatisConfig.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try {
            sqlSession = sqlSessionFactory.openSession();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        CategoriesMapper categoriesMapper = sqlSession.getMapper(CategoriesMapper.class);
        CategoriesExample categoriesExample = new CategoriesExample();

        categoriesExample.createCriteria().andIdEqualTo(1);

        List<Categories> list = categoriesMapper.selectByExample(categoriesExample);
        System.out.println(categoriesMapper.countByExample(categoriesExample));

        Categories selected = categoriesMapper.selectByPrimaryKey(2);
        System.out.println("ID: " + selected.getId() + "\ntitle: " + selected.getTitle());

        Categories categories = new Categories();
        categories.setTitle("Test");
        categoriesMapper.insert(categories);
        sqlSession.commit();


    }

}
