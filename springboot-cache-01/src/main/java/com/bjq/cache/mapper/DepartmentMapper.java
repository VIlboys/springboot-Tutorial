package com.bjq.cache.mapper;

import com.bjq.cache.bean.Department;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DepartmentMapper {

    @Select("SELECT * from department WHERE id=#{id}")
    Department getEmployee(Integer id);
}
