/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Customer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author benjaminbajrami
 */
public class CustomerFacade {
    private static EntityManagerFactory emf;
    private static CustomerFacade instance;
    
    private CustomerFacade() {}

    public static CustomerFacade getcustomerFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CustomerFacade();
        }
        return instance;
    }
    
    public Customer addCustomer(String firstName, String lastName){
        Customer customer = new Customer(firstName, lastName);
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(customer);
            em.getTransaction().commit();
            return customer;
        }finally {
            em.close();
        }
    }
    
    public Customer findCustomerById(int id){
         EntityManager em = emf.createEntityManager();
        try{
            Customer customer = em.find(Customer.class,id);
            return customer;
        }finally {
            em.close();
        }
    }
    public List<Customer> findCustomerByLastName(String lastName) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Customer> query = em.createQuery("SELECT b FROM Customer b WHERE b.lastName = :lastName",Customer.class)
                    .setParameter("lastName", lastName);
            return query.getResultList();
        } finally {
        em.close();
    }
}
    public List<Customer> getAllCustomers(){
         EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<Customer> query = 
                       em.createQuery("Select customer from Customer customer",Customer.class);
            return query.getResultList();
        }finally {
            em.close();
        }
    }
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");
        CustomerFacade facade = CustomerFacade.getcustomerFacade(emf);
        facade.addCustomer("Jørgen", "Ostemad");
        facade.addCustomer("Jørgen", "Ostemad1");
        facade.addCustomer("Jørgen", "Ostemad2");
        facade.addCustomer("Jørgen", "Ostemad3");
        facade.addCustomer("Jørgen", "Ostemad4");
        System.out.println(facade.findCustomerById(3).toString());
        System.out.println(facade.findCustomerByLastName("Ostemad4").toString());
        System.out.println(facade.getAllCustomers().toString());
    }
}
