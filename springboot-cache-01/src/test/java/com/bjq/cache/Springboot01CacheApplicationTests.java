package com.bjq.cache;

import com.bjq.cache.bean.Department;
import com.bjq.cache.bean.Employee;
import com.bjq.cache.mapper.DepartmentMapper;
import com.bjq.cache.mapper.EmployeeMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot01CacheApplicationTests {

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    StringRedisTemplate stringRedisTemplate;//操作字符串

    @Autowired
    RedisTemplate redisTemplate;//k-v都是对象的

    @Autowired
    RedisTemplate<Object,Employee> empRedisTemplate;



    @Autowired
    RedisTemplate<Object,Department> deptRedisTemplate;

    @Autowired
    DepartmentMapper departmentMapper;

    @Test
    public void test01(){
        stringRedisTemplate.opsForValue().append("msg","hello");
    }

    @Test
    public void test02()
    {
        Employee empById = employeeMapper.getEmployee(1);
        //redisTemplate.opsForVale().set("emp-01",empById)
        //将数据以json的方式保存
        //(1)自己将对象转为json
        //(2)redisTemplate默认的序列化规则
        empRedisTemplate.opsForValue().set("emp-01",empById);
    }




    @Test
    public void contextLoads() {
        Employee employee= employeeMapper.getEmployee(1);
        System.out.println(employee);
    }



}
