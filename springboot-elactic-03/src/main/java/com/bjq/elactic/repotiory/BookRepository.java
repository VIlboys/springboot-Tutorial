package com.bjq.elactic.repotiory;

import com.bjq.elactic.bean.Book;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

import java.util.List;

public interface BookRepository extends ElasticsearchRepository<Book,Long> {

    public List<Book> findByBookNameLike(String bookName);

}
