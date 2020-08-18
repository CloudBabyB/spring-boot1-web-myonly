package com.cloud.springboot.controller;

import com.cloud.springboot.bean.Department;
import com.cloud.springboot.bean.Employee;
import com.cloud.springboot.dao.DepartmentDao;
import com.cloud.springboot.dao.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

/**
 * @author myBin
 * @Title:
 * @Package
 * @Description:
 * @date 2020/8/1219:16
 */
@Controller
public class EmployeeController {

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private DepartmentDao departmentDao;

    /**
     * 查询所有员工，返回列表页面
     *
     * @return
     */
    @GetMapping("/emps")
    public String listEmp(Model model) {

        Collection<Employee> employees = employeeDao.getAll();

        model.addAttribute("employees", employees);
        return "emp/list";
    }

    /**
     * 跳转到添加页面
     *
     * @param request
     * @return
     */
    @GetMapping("/addemp")
    public String addEmp(HttpServletRequest request) {
        Collection<Department> depts = departmentDao.getDepartments();

        request.setAttribute("depts", depts);
        System.out.println("dds");
        return "emp/addemp";
    }

    /**
     * 提交添加的数据
     */
    @PostMapping("/addemps")
    public String addPostEmp(Employee employee) {

        employeeDao.save(employee);
        System.out.println("asdsadsdsa");
        return "redirect:/emps";
    }

    /**
     * 跳转修改页面
     */

    @GetMapping("/editemp/{id}")
    public String editemp(@PathVariable("id") Integer id, Model model) {
// 通过id查找要修改员工信息，回显
        Employee employee = employeeDao.get(id);
        model.addAttribute("emp",employee);
// 查询部门，回显到修改页面
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts", departments);

        return "emp/addemp";
    }

    /**
     * 提交修改数据
     */
    @PutMapping("/addemps")
    public String updateemp(Employee employee){
        // 提交修改
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    /**
     *
     * 删除指定数据
     * */
    @DeleteMapping("/deleteemp/{id}")
    public String deleteEmp(@PathVariable("id") Integer id){

        employeeDao.delete(id);

        return "redirect:/emps";
    }

}
