/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;
import entities.Employee;
/**
 *
 * @author benjaminbajrami
 */
public class EmployeeDTO {
    private int id;
    private String name;
    private String address;

    public EmployeeDTO(Employee emp) {
        this.id = emp.getId();
        this.name = emp.getAddress();
        this.address = emp.getAddress();
    }

    public EmployeeDTO() {
    }
    
    
    
}
