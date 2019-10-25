package com.bjq.cache.controller;

import com.bjq.cache.bean.Department;
import com.bjq.cache.bean.Employee;
import com.bjq.cache.mapper.DepartmentMapper;
import com.bjq.cache.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;


    @GetMapping("/emp/{id}")
    public Employee getEmployee(@PathVariable("id") Integer id) {

        Employee employee = employeeService.getEmployee(id);

        return  employee;

    }

    @GetMapping("/emp")
    public Employee updateEmployee(Employee employee){

        Employee emp = employeeService.updateEmployee(employee);

        return emp;

    }

    @GetMapping("/delEmp")
    public String delEmp(Integer id) {

        employeeService.deleteEmp(id);

        return "success";
    }


    @GetMapping("/emp/lastName/{lastName}")
    public Employee getByLastName(@PathVariable("lastName") String lastName)
    {
        return employeeService.getByLastName(lastName);
    }

}
