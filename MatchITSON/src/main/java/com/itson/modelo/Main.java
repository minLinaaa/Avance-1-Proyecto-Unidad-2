/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.itson.modelo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.time.LocalDateTime;

/**
 *
 * @author anapa
 */
public class Main {

   
    
    public static void main(String[] args) {
        // TODO code application logic here
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            System.out.println("Conexión exitosa");

     
            Estudiante e1 = new Estudiante();
            e1.setNombre("Ana");
            e1.setCorreo("ana@itson.edu.mx");
            e1.setPassword("1234");
            e1.setCarrera("Software");

            Estudiante e2 = new Estudiante();
            e2.setNombre("Luis");
            e2.setCorreo("luis@itson.edu.mx");
            e2.setPassword("1234");
            e2.setCarrera("Software");

            em.persist(e1);
            em.persist(e2);

            
            Hobby h1 = new Hobby("Fútbol");
            Hobby h2 = new Hobby("Programación");

            em.persist(h1);
            em.persist(h2);

       
            Like like = new Like(e1, e2, "LIKE");
            em.persist(like);


            Match match = new Match(e1, e2, LocalDateTime.now());
            em.persist(match);

        
            Mensaje mensaje = new Mensaje(match, e1, "Hola, ¿cómo estás?", LocalDateTime.now());
            em.persist(mensaje);

            em.getTransaction().commit();

            System.out.println("Datos insertados correctamente");

        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    
    }
    
}
