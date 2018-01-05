package com.booksystem.mybatis.mapper;

import com.booksystem.mybatis.entities.Book;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface BookMapper {
    int deleteByPrimaryKey(String bookId);

    int insert(Book record);

    Book selectByPrimaryKey(String bookId);
    
    //根据类型排序书籍
    List<Book> selectAllByOrder(@Param(value="order") String order);
     
    //根据指定条件获取书籍集合
    List<Book> selectByBookTypes(@Param(value="type") String type,@Param(value="value") String value);
    //模糊查询
    List<Book> selectBookBySearch(@Param(value="type") String type,@Param(value="value") String value);
    
    int updateByPrimaryKey(Book record);
    
    int updateBook(@Param(value="sql") String sql);
    
}