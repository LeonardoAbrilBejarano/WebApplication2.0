/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FileWR;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Leonardo
 */
public class FileWR {
    
    public static void Printrun(String namefile) throws IOException{
        ArrayList cont=Readrun(namefile);
        String txt;
        for(int i=0;i<cont.size();i++){
            txt=(String) cont.get(i);
            System.out.println(txt);
        }
    }
    public static ArrayList Readrun(String namefile) throws IOException {
	BufferedReader br = null;
        ArrayList contenido=new ArrayList();
        int k=0;
	String line = "";
		br = new BufferedReader(new FileReader(namefile));
		while ((line = br.readLine()) != null) {
                        contenido.add(line);
                }
        return contenido;
    }
    



  
    public static void Writerun(String namefile,String txt){
	BufferedWriter bw = null;
        try {

		bw = new BufferedWriter(new FileWriter(namefile));
		        bw.write(txt);
                        bw.newLine();
                        bw.flush();
                
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	
  
    public static void RandomPrintRun(String name) throws IOException{
        String txt=RandomReadRun(name);
        for(int i=1;i<txt.length();i++){
            System.out.print(txt.charAt(i));
            i++;
        }
    }
    public static String RandomReadRun(String name) throws FileNotFoundException, IOException{
        File f;
        RandomAccessFile rad;
        f=new File(name);
        rad=new RandomAccessFile(f,"rw");
        rad.seek(0);
        String txt;
        txt=rad.readLine();
        return txt;
    } 
    
    public static void RandomWriteRun(String name,String txt) throws FileNotFoundException, IOException{
        File f;
        RandomAccessFile rad;
        f=new File(name);
        rad=new RandomAccessFile(f,"rw");
        rad.seek(0);
        rad.writeChars(txt);
    }
}