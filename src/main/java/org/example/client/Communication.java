package org.example.client;

import org.example.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class Communication {
    private final String URL = "http://localhost:8080/spring_rest/api/employees";
    @Autowired
    RestTemplate restTemplate;

    public List<Employee> getAllEmployees() {
        ResponseEntity<List<Employee>> responseEntity =
                restTemplate.exchange(URL, HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<Employee>>() {
                        });

        return responseEntity.getBody();
    }

    public Employee getEmployeeById(int id) {
        return restTemplate.getForObject(URL + "/" + id, Employee.class);
    }

    public void saveEmployee(Employee employee) {
        int id = employee.getId();
        if (id == 0) {
            ResponseEntity<String> responseEntity =
                    restTemplate.postForEntity(URL, employee, String.class);
            System.out.println("Employee was added.");
            System.out.println(responseEntity.getBody());
        } else {
            restTemplate.put(URL, employee);
            System.out.printf("Employee with id %d was updated.\n", employee.getId());
        }
    }

    public void deleteEmployeeById(int id) {
        restTemplate.delete(URL + "/" + id);
        System.out.printf("Employee with id %d was deleted.\n", id);
    }
}
