package com.shirongbao.rest.controller;

import com.shirongbao.rest.bean.Employee;
import com.shirongbao.rest.dao.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: ShiRongbao
 * Date: 2024/2/6
 * Time: 15:25
 */
@Controller
public class EmployeeController {

    @Autowired
    private EmployeeDao employeeDao;

    // 当使用GET请求访问/employee地址时会执行这个控制器方法
    @RequestMapping(value = "/employee", method = RequestMethod.GET)
    public String getAllEmployee(Model model) {
        // 调用dao层方法查询所有员工信息
        Collection<Employee> employeeList = employeeDao.getAll();
        // 将查询到的结果放入到域对象中
        model.addAttribute("employeeList", employeeList);
        // 返回结果交给视图解析器
        return "employee_list";
    }

    @DeleteMapping("/employee/{id}")
    public String deleteEmployeeById(@PathVariable("id") Integer id) {
        employeeDao.delete(id);
        return "success";
    }

    @RequestMapping(value = "/employee",method = RequestMethod.POST)
    public String addEmployee(Employee employee) {
        employeeDao.save(employee);
        return "redirect:/employee";
    }

    @RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
    public String selectById(@PathVariable("id") Integer id, Model model){
        Employee employee = employeeDao.get(id);
        model.addAttribute("employee", employee);
        return "employee_update";
    }

    @PutMapping("/employee")
    public String updateEmployee(Employee employee){
        employeeDao.save(employee);
        return "redirect:/employee";
    }
}
