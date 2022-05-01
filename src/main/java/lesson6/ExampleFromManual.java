package lesson6;

import lesson6.db.dao.CategoriesMapper;
import lesson6.db.model.CategoriesExample;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class ExampleFromManual {

        public static void main(String[] args) throws IOException {
            SqlSessionFactory sqlSessionFactory;
            //NyBatis Configuration file
            String resource = "mybatisConfig.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        }

    public static Integer countNumberOfAllCategories(String resource) throws IOException {
        CategoriesMapper categoriesMapper = getCategoriesMapper(resource);
        CategoriesExample example = new CategoriesExample();
        return Math.toIntExact(categoriesMapper.countByExample(example));
    }
    private static CategoriesMapper getCategoriesMapper(String resource) throws IOException {
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new
                SqlSessionFactoryBuilder().build(inputStream);
        sqlSessionFactory.openSession();
        SqlSession session = sqlSessionFactory.openSession();
        return session.getMapper(CategoriesMapper.class);
    }

}
