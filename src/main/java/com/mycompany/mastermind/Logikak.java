/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mastermind;


import adatbazis.Lekerdezesek;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A logika osztályban jön létre maga a játéklogika. Itt bírálódik el minden eldöntendő kérdés, 
 * és itt dől el, hogy megnyered vagy elveszted a játékot.
 */
public class Logikak {
    /**
     * Logger példány a naplózáshoz.
     */
    private Logger logger = LoggerFactory.getLogger(MainApp.class);
    
    /**
     * A szinkód eltalálására vonatkozó próbálkozás száma.
     * A probalkozasokSzama változóban tárolódik, hogy a játékban hanyadik próbálkozásnál jársz.
     */
    public int probalkozasokSzama = 1;

    /**
     * A kisKorIndex változóban tárolódik, hogy épp hanyadik kört kell majd kiszíneznie a kontrollernek.
     */
    public int kisKorIndex = 0;
    
    /**
     * A kisegito változó fogja helyrerakni a kisKorIndexet, ha az esetleg elcsúszna.
     */
    public int kisegito = 0;
   
    
    /**
     * A jatekVegeVizsgalo metódus megnézi, hogy vége van-e a játéknak.
     * Ha vége a játéknak, akkor elmenti a végeredményt az adatbázisba.
     * @param random osztály
     * @return true, ha vége a játéknak, egyébként hamis.
     */
    public boolean jatekVegeVizsgalo(RandomEsHasonlit random){
            if(random.getWinner() == 4 || probalkozasokSzama >= 9){
                logger.info("Vége a játéknak!");
                return true;
            }
            else return false;
    } 
    
    /**
     * Meghatározza, hogy nyertél, vagy vesztettél.
     * Ezután akár nyertél, akár vesztettél, elmenti az adatbázisba.
     * @param lekerdezesek adatbázis
     * @param random osztály
     * @return igaz, ha nyertél, hamis ha vesztettél
     */
    public boolean jatekKiertekelo(Lekerdezesek lekerdezesek, RandomEsHasonlit random) {
        if (random.getWinner() == 4) {
            lekerdezesek.gyoztemSorBetol();
            logger.trace("A játékos nyert.");
            return true;
        } else {
            lekerdezesek.vesztekSorBetol();
            logger.trace("A játékos vesztett.");
            return false;
        }
    }
    
    /**
     * Megvizsgálja, hogy eltaláltad-e, az adott színt a megfelelő helyen.
     * @param random egy osztály
     * @param index egy indexérték
     * @return igaz, ha a random lista indexedik eleme 9-es, azaz 
     * (a RandomEsHasonlit osztály JoSzinRosszHelyen metódusa alapján) 
     * a próbálkozásaink indexedik eleme megegyezik a szinkóddal,
     * egyébként hamis
     */
    public boolean feketeKKorFelismero(RandomEsHasonlit random, int index){
        return random.getSzinKodCopy().get(index) == 9;
    }
    
    /**
     * Megvizsgálja, hogy eltaláltad-e a színt, csak rossz helyen, vagy sem.
     * @param random egy osztály
     * @param index egy indexérték
     * @return igaz, ha a random lista indexedik eleme 0-ás, azaz 
     * (a RandomEsHasonlit osztály JoSzinRosszHelyen metódusa alapján) 
     * a próbálkozásaink indexedik eleme megegyezik a szinkóddal,
     * egyébként hamis
     */
    public boolean feherKKorFelismero(RandomEsHasonlit random, int index){
        return random.getSzinKodCopy().get(index) == 0;
    }
    
    /**
     * Az eredményjelő körök indexének értékét korrigálja.
     * Az {@code indexKiSegito} metódus fogja nekünk helyrepakolni az eredményjelző köreink
     * indexeit abban az esetben, ha azok el akarnának csúszni.
     */
    public void indexKiSegito(){
        if(kisKorIndex%4 != 0 || kisegito==kisKorIndex)
           kisKorIndex += 4 - (kisKorIndex%4);
        kisegito = kisKorIndex;
    }
    
    /**
     * Alaphelyzetbe állítja a változókat.
     * Az ujJatekGenerator metódus visszaállítja a segédváltozóinkat az 
     * alaphelyzetbe, hogy újra kedvünkre növelhessük/csökkenthessük őket.
     */
    public void ujJatekGenerator(){
        probalkozasokSzama = 1;
        kisKorIndex = 0;
        kisegito = 0;
        logger.info("Az új játék hamarosan elkezdődik!");
    }

    /**
     * A probalkozasokSzama változó getter metódusa.
     * @return probalkozasokSzama
     */
    public int getProbalkozasokSzama() {
        return probalkozasokSzama;
    }

    /**
     * A probalkozasokSzama változó setter metódusa.
     * @param probalkozasokSzama 
     */
    public void setProbalkozasokSzama(int probalkozasokSzama) {
        this.probalkozasokSzama = probalkozasokSzama;
    }
}
