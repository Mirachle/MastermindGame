/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adatbazis;


import com.mycompany.mastermind.MainApp;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Az adatbázisműveleteket tartalmazó osztály.
 * @author minori
 */
public class Lekerdezesek {
    /**
     * Logger példány a naplózáshoz.
     */
    private Logger logger = LoggerFactory.getLogger(MainApp.class);
    
    /**
     * EntityManagerFactory példányosítása.
     */
    protected EntityManagerFactory ef;
    
    /**
     * EntityManager példányosítása.
     */
    protected EntityManager em;
    
    /**
     * A feltöltött sor id-ját adja meg.
     */
    public int idErtek = 0;
    
    /**
     * Adatbáziskapcsolat létrehozása JPA-val.
     */
    public Lekerdezesek(){
        ef = Persistence.createEntityManagerFactory("kapcsolatom");
        em = ef.createEntityManager();
        logger.trace("Az adatbáziskapcsolat létrejött.");
    }
    
    /**
     * Lezárja a megnyitott adatbáziskapcsolatot.
     */
    public void kapcsolatZaro(){
        if(ef.isOpen()){
        	delete();
        	em.close();
        	ef.close();
            logger.trace("Az adatbáziskapcsolat lezárva.");
        }
    }
    
    /**
     * Megszámolja a paraméterként kapott kifejezés előfordulását.
     * Az adatbázis végeredmény oszlopában lévő stringek közül listázza ki, majd adja vissza hánynál talált egyezést.
     * @param kifejezes egy sztring
     * @return a kifejezés darabszáma a listában
     */
    public int szamlalo(String kifejezes){
        return getAll().stream().filter(e -> e.getVégeredmény().equals(kifejezes)).collect(Collectors.toList()).size();
    }
    
    /**
     * Az adatbázist visszaadja listaként.
     * @return lista
     */
    public List<Mastermind> getAll(){
        Query query = em.createQuery("SELECT e FROM Mastermind e");
        return (List<Mastermind>) query.getResultList();
    }
    
    /**
     * Feltölti a kapott adatokat az adatbázisba.
     * @param eredmeny osztály
     */
    public void insert(Mastermind eredmeny){
        em.getTransaction().begin();
        em.persist(eredmeny);
        em.getTransaction().commit();
        logger.info("Beszúrásra került egy sor.");
    }
    
    /**
     * Kiüríti az adatbázist.
     */
    public void delete(){
       List<Mastermind> torlendok = getAll();
        em.getTransaction().begin();
        torlendok.forEach(e -> em.remove(e));
        em.getTransaction().commit();
        logger.info("Az adatbázis tartalmát töröltük.");
    }
    
    /**
     * Feltölt egy sort az adatbázisba "gyoztem" végeredménnyel.
     */
    public void gyoztemSorBetol(){
                Mastermind sor = new Mastermind();
                sor.setVégeredmény("gyoztem");
                sor.setId(idErtek);
                idErtek++;
                insert(sor);
    }
    
    /**
     * Feltölt egy sort az adatbázisba "vesztettem" végeredménnyel.
     */
    public void vesztekSorBetol(){
                Mastermind sor = new Mastermind();
                sor.setVégeredmény("vesztettem");
                sor.setId(idErtek);
                idErtek++;
                insert(sor);
    }
}
