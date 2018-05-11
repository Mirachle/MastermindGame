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

import com.mycompany.mastermind.SajatSzinek;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author minori
 */
public class SajatSzinekTest {
    
    SajatSzinek szinek;
    private List<Integer> lista;
 
    
    @Before
    public void setUp() {
        lista = new ArrayList();
        szinek = new SajatSzinek();
        lista.add(1);
    }
    
    @Test
    public void getSetTeszt(){
        szinek.setProbalkozasaim(lista);
        assertEquals(lista,szinek.getProbalkozasaim());
    }
    
    @Test
    public void szintAdHozzaTeszt(){
        szinek.szintAdHozza(1);
        assertEquals(szinek.getProbalkozasaim(), lista);
    }
    
    @Test
    public void probalkozasaimTorleseTeszt(){
        szinek.getProbalkozasaim().add(1);
        szinek.probalkozasaimTorlese();
        szinek.getProbalkozasaim().isEmpty();
    }
    

}
