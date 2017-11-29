/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Updaters;

import Readers.ReaderLastSnapshot;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Compuj
 */
public class ImagesDelete {
    
    public static void clean(ArrayList<String> images){
        int count = 0;
       
        while(count < images.size()){
            try{
                String cmd = "rm "+images.get(count); 
                Runtime.getRuntime().exec(cmd).waitFor(); 
            }
            catch (IOException ex) {
                Logger.getLogger(ReaderLastSnapshot.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(ImagesDelete.class.getName()).log(Level.SEVERE, null, ex);
            }
            count++;
        }
    }
}
