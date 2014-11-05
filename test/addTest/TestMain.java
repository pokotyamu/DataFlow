/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package addTest;

import DBCreater.DBController;
import DBCreater.FileList;
import java.io.File;

/**
 *
 * @author pokotyamu
 */
public class TestMain {
    
    public static void main(String[] args) {
        

        FileList fl = new FileList("/Users/pokotyamu/Desktop/Kyutech");
        

        
        
//        System.out.println(TestMain.class.getResource("TestMain.class"));
//FileList fc = new FileList("");
        DBController dbcon = new DBController();
        for(File f : fl.getFiles()){
            dbcon.setDB(f);
            dbcon.createAllTable();
            dbcon.registallDB(dbcon.getST_ID(f), dbcon.getClass_ID(f),dbcon.getSUBMITION(f));
        }
        
        
        
    }
    
}
