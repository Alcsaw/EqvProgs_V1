/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eqvprogs_v1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFileChooser;

/**
 *
 * @author augus
 */
public class Step1 {
    // Reading files:
    
    //private String arq1 = "Program1.txt", arq2 = "Program2.txt";
    private String file1, file2;    // Strings for files' paths
    
    JButton jb = new JButton();
    JFileChooser jf = new JFileChooser("");

    private List<String> prog1 = new ArrayList(); // For reading files
    private List<String> prog2 = new ArrayList();
    
    private List<Line> ircP1 = new ArrayList(); // A list of Line objects is going to contain all 'IRC' for Program 1
    private List<Line> ircP2 = new ArrayList();
    
    public void setFile1() {
        jf.showOpenDialog(jb);
        this.file1 = jf.getSelectedFile().getAbsolutePath();
    }

    public void setFile2() {
        jf.showOpenDialog(jb);
        this.file2 = jf.getSelectedFile().getAbsolutePath();
    }

    public String getFile1Path() {
        return this.file1;
    }

    public String getFile2Path() {
        return this.file2;
    }

    public List<Line> getIrcP1() {
        return ircP1;
    }

    public List<Line> getIrcP2() {
        return ircP2;
    }
    
    public void readFile() throws FileNotFoundException, IOException{
        String line;
    
        FileInputStream fis = new FileInputStream(this.file1);
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader bf = new BufferedReader(isr);
        
        line = bf.readLine();
        while (line != null){
            this.prog1.add(line);
            line = bf.readLine();
        }
        fis.close();
        
        FileInputStream fis2 = new FileInputStream(this.file2);
        InputStreamReader isr2 = new InputStreamReader(fis2);
        BufferedReader bf2 = new BufferedReader(isr2);
        
        line = bf2.readLine();
        while (line != null){
            this.prog2.add(line);
            line = bf2.readLine();
        }
        fis2.close();
        
    }
    
    public void printFile1(String file){
        File f1 = new File(file);
        System.out.println(f1.getName());
        
        for (int line = 0; line < prog1.size(); line++) {
            System.out.println(prog1.get(line));
        }
    }
    
    public void printFile2(String file){
        File f1 = new File(file);
        System.out.println(f1.getName());
        
        for (int line = 0; line < prog2.size(); line++) {
            System.out.println(prog2.get(line));
        }
    }
    
    private String getLabel (String line){
        return line.substring(0, line.indexOf(":") + 1);
    }
    
    private String getOp1(String line){
        //System.out.println(line.charAt(line.indexOf(":") + 2));
        if (Character.toString(line.charAt(line.indexOf(":") + 2)).equals("f")){
            return Character.toString(line.charAt(line.indexOf("faca ") + 5));
        }
        else{
            if (line.indexOf("faca") < line.indexOf("senao")){// Checks if there's OP 1
            //System.out.println("TEM OP 1!   " + line.charAt(line.indexOf("faca ") + 5));
            return Character.toString(line.charAt(line.indexOf("faca ") + 5));// Returns OP1 if True
            }
            else{
                //System.out.println("Não tem OP1!! Tem: " + line.charAt(line.indexOf("va_para ") + 8));

                String noOP = Character.toString(line.charAt(line.indexOf("va_para ") + 8));
                String label = Character.toString(line.charAt(0));
                if (noOP.equals("0")){
                    return "parada";
                }

                else if (noOP.equals(label)){
                    return "ciclo";
                }
                return ("ERROR - Program has encountered an error at label " + label);
            }
        }
        
        
    }
    
    private String getNextLabel1 (String line){
        //System.out.println("Next label 1: " + Character.toString(line.charAt(line.indexOf("va_para ") + 8)));
        return Character.toString(line.charAt(line.indexOf("va_para ") + 8));
    }
    
    private String getOp2 (String line){
        if (line.lastIndexOf("faca") > line.indexOf("senao")){// Checks if there's OP 2
            //System.out.println("TEM OP 2!   " + line.charAt(line.lastIndexOf("faca ") + 5));
            return Character.toString(line.charAt(line.lastIndexOf("faca ") + 5));// Returns OP2 if True
        }
        else{
            //System.out.println("Não tem OP 2!! Tem: " + line.charAt(line.lastIndexOf("va_para ") + 8));
        
            String noOP = Character.toString(line.charAt(line.lastIndexOf("va_para ") + 8));
            String label = Character.toString(line.charAt(0));
            if (noOP.equals("0")){
                return "parada";
            }
            
            else if (noOP.equals(label)){
                return "ciclo";
            }
            return ("ERROR - Program has encountered an error at label " + label);
        }
        
        
    }
    
    private String getNextLabel2 (String line){
        return Character.toString(line.charAt(line.lastIndexOf("va_para ") + 8));
    }
    
    public void toIRC(List<String> prog, List<Line> IRC){    // Convert file to IRC
        
        for (String line : prog){// Cicles throught each line of the program
            line = line.toLowerCase();// makes the line all lower case for better usage
            Line l = new Line();
            
            //set current label:
            l.setLabel(this.getLabel(line));
            
            //set the first operation (for True case or if it's the only one)
            l.setOp1(this.getOp1(line));

            //Checks if it's just an operation or a test:
            if (line.contains("se")){
                // if it has no OP1 ("ciclo" or "parada")
                if (l.getOp1().length() > 2){
                    if (l.getOp1().equals("parada")){
                        l.setNextLabel1("&");
                    }
                    else if (l.getOp1().equals("ciclo")){
                        l.setNextLabel1("w");
                    }
                }
                else{
                    l.setNextLabel1(this.getNextLabel1(line));
                }
                
                // second part:
                l.setOp2(this.getOp2(line));
                
                // if it has no OP2 ("ciclo" or "parada")
                if (l.getOp2().length() > 2){
                    if (l.getOp2().equals("parada")){
                        l.setNextLabel2("&");
                    }
                    else if (l.getOp2().equals("ciclo")){
                        l.setNextLabel2("w");
                    }
                }
                else{
                    l.setNextLabel2(this.getNextLabel2(line));
                }                
            }
            else{
                // Set next label for first part:
                l.setNextLabel1(this.getNextLabel1(line));
                // Since there's only one operation, it's the same information for second part
                l.setOp2(l.getOp1());
                l.setNextLabel2(l.getNextLabel1());
            }
            
            // After definying the entire IRC, append it to the List
            IRC.add(l);
        }
        
        /*for (int line = 0; line < prog.size(); line ++){// Da 1ª até última linha do programa
            
            /*
            Get label;
            Get Op1
            if (OP1 == parada || ciclo)
                NextLabel1 = parada || ciclo
            else
                GetNextlabel1
            GetOP2;
            if (OP2 == parada || ciclo)
                NextLabel2 = parada || ciclo
            else
                GetNextlabel2
            
        }*/
          
    }
    
    public void printIRC(List<Line> IRC){
        Boolean cicle = false;
        String line;
        
        System.out.println("IRC:");
        for (Line l : IRC){
            line = l.getLine();
            System.out.println(line);
            if (line.contains("w"))
                cicle = true;
        }
        if (cicle == true)
            System.out.println("w: (ciclo, w), (ciclo, w)");
    }
    
    public void doStep1(){

        try {
            this.readFile();
        } catch (IOException ex) {
            Logger.getLogger(Step1.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.printFile1(file1);
        this.toIRC(prog1, ircP1);
        this.printIRC(ircP1);
        
        this.printFile2(file2);
        this.toIRC(prog2, ircP2);
        this.printIRC(ircP2);
        
    }
    
    
}
