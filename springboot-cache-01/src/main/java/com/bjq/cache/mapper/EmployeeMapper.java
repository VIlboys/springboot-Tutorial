package com.bjq.cache.mapper;

import com.bjq.cache.bean.Employee;
import org.apache.ibatis.annotations.*;

@Mapper
public interface EmployeeMapper {

    @Select("select * from employee where id=#{id}")
    public Employee getEmployee(Integer id);

    @Update("update employee set lastName=#{lastName},email=#{email},gender=#{gender},d_id=#{dId} where id=#{id}")
    public void updateEmployee(Employee employee);

    @Delete("delete from employee where id=#{id}")
    public void deleteEmployee(Integer id);

    @Insert("insert into employee(lastName,email,gender,d_id) values(#{lastName},#{email},#{gender},#{dId})")
    public void insertEmployee(Employee employee);

    @Select("select * from employee where lastName=#{lastName}")
    Employee getByLastName(String lastName);
}
