package com.bjq.cache.service;

import com.bjq.cache.bean.Employee;
import com.bjq.cache.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@CacheConfig(cacheNames = "emp")
@Service
@Transactional
public class EmployeeService {
    @Autowired
    EmployeeMapper employeeMapper;


    /* @Cacheable(cacheNames = {"emp"},key = "#root.methodName+'['+#id+']'",condition = "#a0>1",unless = "#a0==2")*/
   @Cacheable(cacheNames = {"emp"})
    public Employee getEmployee(Integer id)
    {
        System.out.println("查询到几"+id+"号员工");
        Employee employee = employeeMapper.getEmployee(id);
        return employee;
    }


    @CachePut(/*value = "emp",*/key = "#result.id")
    public Employee updateEmployee(Employee employee)
    {
        System.out.println("update了"+employee);

        employeeMapper.updateEmployee(employee);

        return employee;
    }

    /*@CacheEvict(value = "emp",key = "#id")*/
    @CacheEvict(/*value = "emp",*/beforeInvocation=true)
    public void deleteEmp(Integer id){

        System.out.println("delete："+id);

        int a =10/0;

    }

    @Caching(
            cacheable = {
                    @Cacheable(/*value = "emp",*/key = "#lastName")
            },
            put = {
                    @CachePut(/*value = "emp",*/key = "#result.id")
                    ,
                    @CachePut(/*value = "emp",*/key = "#result.email")
            }
    )
    public Employee getByLastName(String lastName)
    {
        return  employeeMapper.getByLastName(lastName);
    }
}
