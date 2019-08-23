    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.BankCustomer;
import javax.persistence.Entity;

/**
 *
 * @author ndupo
 */

public class CustomerDTO {
    
    private long customerID;
    private String firstName;
    private String LastName;
    private String accountNumber;
    private double balance;

    public CustomerDTO() {
    }

    public CustomerDTO(BankCustomer cust) {
        this.customerID = cust.getId();
        this.firstName = cust.getFirstName();
        this.LastName = cust.getLastName();
        this.accountNumber = cust.getAccountNumber();
        this.balance = cust.getBalance();
    }
    
    

    
}
