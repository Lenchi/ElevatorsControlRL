/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package main;

/**
 *
 * @author Admin
 */
import java.io.*;
//class for printing all results into txt files
public class FileWriter {
    public String fileName;
    public FileWriter(String fn){this.fileName=fn;}
public void write(String text){
    try{
        PrintWriter out = new PrintWriter(new File(fileName).getAbsoluteFile());
        try{
            out.println(text);
        } finally {
            out.close();
        }
    }catch(IOException e){
        throw new RuntimeException(e);
    }
}
}
