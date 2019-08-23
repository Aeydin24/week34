/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author ndupo
 */
public class MakeTestData {
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        try {
            BankCustomer bc1 = new BankCustomer("Jenni", "Hansen", "100-1234", 10092, 3, "Medlem siden 1997-10-05");
            BankCustomer bc2 = new BankCustomer("Sten","Frederiksen","100-7362",6857, 8, "Medlem siden 1985-01-09");
            BankCustomer bc3 = new BankCustomer("Tom","Mikkelsen","100-8271", 983, 10, "Medlem siden 1992-03-23");
            BankCustomer bc4 = new BankCustomer("Dorte","Jørgensen","100-8251", 10, 2, "Medlem siden 2003-11-30");
            em.getTransaction().begin(); //begin transaction
            em.persist(bc1);
            em.persist(bc2);
            em.persist(bc3);
            em.persist(bc4);
            em.getTransaction().commit(); //commit transactions
        } finally {
            em.close();
        }
    }
    
}
