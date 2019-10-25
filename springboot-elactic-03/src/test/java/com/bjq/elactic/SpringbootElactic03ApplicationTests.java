package com.bjq.elactic;

import com.bjq.elactic.bean.Article;
import com.bjq.elactic.bean.Book;
import com.bjq.elactic.repotiory.BookRepository;
import io.searchbox.client.JestClient;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootElactic03ApplicationTests {

    @Autowired
    JestClient jestClient;

    @Autowired
    BookRepository bookRepository;


    @Test
    public void test02()
    {
       /* Book book= new Book();
        book.setId(1);
        book.setBookName("天才在左");
        book.setAuthor("高铭");
        bookRepository.index(book);*/
        List<Book> byBookNameLike = bookRepository.findByBookNameLike("天");
        System.out.println(byBookNameLike);
    }

    @Test
    public void contextLoads() {

        //1,给Es中索引（保存）一个文档
        Article article = new Article();
        article.setId(1);
        article.setTitle("天才在左");
        article.setAuthor("高明");
        article.setContent("这世界不对");
        //构建一个索引功能
        Index index = new Index.Builder(article).index("bjq").type("news").build();

        try {
            //执行
            jestClient.execute(index);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //测试搜索
    @Test
    public void search(){
        //构建查询表达式
        String json = "{\n" +
                "    \"query\" : {\n" +
                "        \"match\" : {\n" +
                "            \"content\" : \"hello\"\n" +
                "        }\n" +
                "    }\n" +
                "}";
        Search search = new Search.Builder(json).addIndex("bjq").addType("news").build();

        try {
            SearchResult result = jestClient.execute(search);
            System.out.println(result.getJsonString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
