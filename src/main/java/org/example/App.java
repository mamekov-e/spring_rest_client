package org.example;

import org.example.client.Communication;
import org.example.config.SpringConfig;
import org.example.entity.Employee;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(SpringConfig.class);
        Communication communication = context.getBean("communication", Communication.class);

        System.out.println("Get all employees");
        System.out.println(communication.getAllEmployees());

        int empId = 2;
        System.out.println("Get employee with id " + empId);
        System.out.println(communication.getEmployeeById(empId));

        Employee employee = new Employee("Sanzhar", "Aithanov", "Sales", 1500);
        System.out.println("Add employee: " + employee);
        communication.saveEmployee(employee);

        employee.setId(empId);
        employee.setDepartment("IT");
        employee.setSalary(2000);
        System.out.println("Update employee: " + employee);
        communication.saveEmployee(employee);

        System.out.println("Delete employee with id " + empId);
        communication.deleteEmployeeById(empId);
    }
}
