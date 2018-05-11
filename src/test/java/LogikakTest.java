/*-
 * #%L
 * Mastermind
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2018 Czibere Mirabella
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mycompany.mastermind.Logikak;
import com.mycompany.mastermind.RandomEsHasonlit;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author minori
 */
public class LogikakTest {
    Logikak logika;
    RandomEsHasonlit random;
    
    
    @Before
    public void setUp() {
        logika = new Logikak();
        random = new RandomEsHasonlit();
    }
    
    @Test
    public void getSetProbalkozasokSzamaTeszt(){
        logika.setProbalkozasokSzama(10);
        assertEquals(10,logika.getProbalkozasokSzama());
    }
    
    @Test
    public void ujJatekGeneratorTeszt(){
        logika.setProbalkozasokSzama(10);
        logika.kisegito = 5;
        logika.kisKorIndex = 6;
        logika.ujJatekGenerator();
        assertEquals(1, logika.getProbalkozasokSzama());
        assertEquals(logika.kisKorIndex, logika.kisegito, 0);
    }
    
    @Test
    public void indexKiSegitoTeszt(){
      logika.kisKorIndex = 1;
      logika. kisegito = 2;
      logika.indexKiSegito();
      assertEquals(4, logika.kisKorIndex);
      assertEquals(logika.kisegito, logika.kisKorIndex);
      logika.kisKorIndex = 0;
      logika. kisegito = 0;
      logika.indexKiSegito();
      assertEquals(4, logika.kisKorIndex);
      assertEquals(logika.kisegito, logika.kisKorIndex);
      logika.kisKorIndex = 4;
      logika. kisegito = 3;
      logika.indexKiSegito();
      assertEquals(logika.kisegito, logika.kisKorIndex);
       
    }
    
    @Test
    public void feherKKorFelismeroTeszt(){
        random.randomSzinGenerator();
        random.getSzinKodCopy().set(0, 5);
        random.getSzinKodCopy().set(1, 0);
        assertFalse(logika.feherKKorFelismero(random, 0));
        assertTrue(logika.feherKKorFelismero(random, 1));
    }
    
    @Test
    public void feketeKKorFelismeroTeszt(){
        random.randomSzinGenerator();
        random.getSzinKodCopy().set(0, 5);
        random.getSzinKodCopy().set(1, 9);
        assertFalse(logika.feketeKKorFelismero(random, 0));
        assertTrue(logika.feketeKKorFelismero(random, 1));
    }
    
    @Test
    public void jatekVegeVizsgaloTeszt(){
        random.setWinner(4);
        logika.setProbalkozasokSzama(9);
        assertTrue(logika.jatekVegeVizsgalo(random));
        random.setWinner(3);
        assertTrue(logika.jatekVegeVizsgalo(random));
        logika.setProbalkozasokSzama(8);
        assertFalse(logika.jatekVegeVizsgalo(random));
        
    }
    
        
}
