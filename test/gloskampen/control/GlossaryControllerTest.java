/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gloskampen.control;

import gloskampen.view.MainView;
import gloskampen.model.WordList;
import gloskampen.model.Word;
import java.io.IOException;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.matchers.JUnitMatchers.*;
/**
 *
 * @author lotta
 */
public class GlossaryControllerTest {
    gloskampen.model.WordList testListSvEn;
    gloskampen.view.MainView mainView;
    
    public GlossaryControllerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        mainView = new gloskampen.view.MainView();
        mainView.generateFrames();
        testListSvEn = new WordList("svenska", "engelska");
        testListSvEn.initiateTestWords();
    }
    
    @After
    public void tearDown() {
        testListSvEn.tearDownTestWords();
    }

     /**
     * Test of getNewGlossary method, of class GlossaryController.
     */
    @Test
    public void testGetNewGlossary() {
        System.out.println("getNewGlossary");
        GlossaryController instance;
        instance = new gloskampen.control.GlossaryController(null, (int)10);
        instance.intiateGlossaryControlerGame(mainView);
        instance.setWordList(testListSvEn);
        String expResult = "bil;car husvagn;caravan olycka;accident spel;game näsa;nose axel;shoulder, ansikte;face gurka;cucumber jordgubbe;strawberry sommar;summer jul;Christmas";
        String result = instance.getNewGlossary();
        assertThat(expResult, containsString(result));
    }


//    /**
//     * Test of intiateGlossaryControlerGame method, of class GlossaryController.
//     */
//    @Test
//    public void testIntiateGlossaryControlerGame() {
//        System.out.println("intiateGlossaryControlerGame");
//        MainView mainView = null;
//        GlossaryController instance = null;
//        instance.intiateGlossaryControlerGame(mainView);
//        // TODO review the generated test code and remove the default call to fail.
//        //fail("The test case is a prototype.");
//    }
//    
//    /**
//     * Test of checkEndOfTest method, of class GlossaryController.
//     */
//    @Test
//    public void testCheckEndOfTest() {
//        System.out.println("checkEndOfTest");
//        GlossaryController instance = null;
//        Boolean expResult = null;
//        Boolean result = instance.checkEndOfTest();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        // fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of validateGlossary method, of class GlossaryController.
//     */
//    @Test
//    public void testValidateGlossary() {
//        System.out.println("validateGlossary");
//        String triedGlossary = "";
//        GlossaryController instance = null;
//        String expResult = "";
//        String result = instance.validateGlossary(triedGlossary);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        // fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getPercentDone method, of class GlossaryController.
//     */
//    @Test
//    public void testGetPercentDone() {
//        System.out.println("getPercentDone");
//        GlossaryController instance = null;
//        int expResult = 0;
//        int result = instance.getPercentDone();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        // fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getNumberOfFailedGlossaries method, of class GlossaryController.
//     */
//    @Test
//    public void testGetNumberOfFailedGlossaries() {
//        System.out.println("getNumberOfFailedGlossaries");
//        GlossaryController instance = null;
//        int expResult = 0;
//        int result = instance.getNumberOfFailedGlossaries();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        // fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getTotNumberOfGlossaries method, of class GlossaryController.
//     */
//    @Test
//    public void testGetTotNumberOfGlossaries() {
//        System.out.println("getTotNumberOfGlossaries");
//        GlossaryController instance = null;
//        int expResult = 0;
//        int result = instance.getTotNumberOfGlossaries();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        // fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setLanguagesAndLevelTrials method, of class GlossaryController.
//     */
//    @Test
//    public void testSetLanguagesAndLevelTrials() {
//        System.out.println("setLanguagesAndLevelTrials");
//        int fromLanguage = 0;
//        int toLanguage = 0;
//        int level = 0;
//        int numberOfTrials = 0;
//        GlossaryController instance = null;
//        instance.setLanguagesAndLevelTrials(fromLanguage, toLanguage, level, numberOfTrials);
//        // TODO review the generated test code and remove the default call to fail.
//        // fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of validateUserName method, of class GlossaryController.
//     */
//    @Test
//    public void testValidateUserName() {
//        System.out.println("validateUserName");
//        GlossaryController instance = null;
//        Boolean expResult = null;
//        Boolean result = instance.validateUserName();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        // fail("The test case is a prototype.");
//    }
    
}