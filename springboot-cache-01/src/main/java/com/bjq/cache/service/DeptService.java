package com.bjq.cache.service;

import com.bjq.cache.bean.Department;
import com.bjq.cache.bean.Employee;
import com.bjq.cache.mapper.DepartmentMapper;
import com.bjq.cache.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.*;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeptService {
    @Autowired
    DepartmentMapper departmentMapper;

    //@Qualifier("deptRedisTemplate")
    @Autowired
    CacheManager deptRedisTemplate;

    /* @Cacheable(cacheNames = {"emp"},key = "#root.methodName+'['+#id+']'",condition = "#a0>1",unless = "#a0==2")*/
    /*@Cacheable(cacheNames = {"dept"})
    这是使用注解的形式
    public Department getDept(Integer id)
    {
        System.out.println("查询部门"+id);
        Department department = departmentMapper.getEmployee(id);
        //获取某个缓存
        Cache dept = deptRedisTemplate.getCache("dept");
        dept.put("dept:1",department);
        return department;
    }*/
    //使用缓存管理器得到缓存，进行api调用,使用编码的形式
    public Department getDept(Integer id)
    {
        System.out.println("查询部门"+id);
        Department department = departmentMapper.getEmployee(id);
        //获取某个缓存
        Cache dept = deptRedisTemplate.getCache("dept");
        dept.put("dept:1",department);
        return department;
    }

}
