package com.booksystem.mybatis.mapper;

import com.booksystem.mybatis.entities.BookUser;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BookUserMapper {
    int deleteByPrimaryKey(@Param("bookId") String bookId, @Param("userId") String userId);

    int insert(BookUser record);

    BookUser selectByPrimaryKey(@Param("bookId") String bookId, @Param("userId") String userId);
    
    //根据学生id号获取所有借阅记录
    public List<BookUser> getRecord(String userId);
    
    List<BookUser> selectAll();

    //更新指定记录
    int updateByPrimaryKey(BookUser record);
}