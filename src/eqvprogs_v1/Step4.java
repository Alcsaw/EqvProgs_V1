/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eqvprogs_v1;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author augus
 */
public class Step4 {
    
    private List<Line> irc1 = new ArrayList();
    private List<Line> irc2 = new ArrayList();
    
    private List<Line> comparisson = new ArrayList();
    private int lineNum = 1;

    public void setIrc1(List<Line> irc1) {
        this.irc1 = irc1;
    }

    public void setIrc2(List<Line> irc2) {
        this.irc2 = irc2;
    }
    
    //Removes repeated lines in the list
    public void updateIRC(List<Line> irc){
        Line line;
        /*for (Line l : irc){
            for (int i = irc.)
        }*/
        for (int i=0; i >= irc.size(); i++){
            line = irc.get(i);
            
            for (int j=i+1; j >= irc.size(); j++){
                if (line.equals(irc.get(j)))
                    irc.remove(j);
            }
        }
    }
    
    ////Checks if it's a stop on first part (of program 2)
    public boolean checkStop1(Line l1, Line l2, Line lB){
        if (l2.getOp1().equals("parada")){
            lB.setOp1(l1.getNextLabel1());
            lB.setNextLabel1(l2.getNextLabel1());
            return true;
        }
        return false;
    }
    
    //Checks if it's a stop on second part (of program 2)
    public boolean checkStop2(Line l1, Line l2, Line lB){
        if (l2.getOp2().equals("parada")){
            lB.setOp2(l1.getNextLabel2());
            lB.setNextLabel2(l2.getNextLabel2());
            return true;
        }
        return false;
    }
    
    //Checks if it's an infinite cicle on first part (of program 2)
    public boolean checkCicle1(Line l1, Line l2, Line lB){
        if (l1.getOp1().equals("ciclo")){
            if (l2.getOp1().equals("ciclo")){
                lB.setOp1(l1.getNextLabel1());
                lB.setNextLabel1(l2.getNextLabel1());
                return true;
            }
        }
        return false;
    }
    
    //Checks if it's an infinite cicle on second part (of program 2)
    public boolean checkCicle2(Line l1, Line l2, Line lB){
        if (l2.getOp2().equals("ciclo")){
            lB.setOp2(l1.getNextLabel2());
            lB.setNextLabel2(l2.getNextLabel2());
            return true;
        }
        return false;
    }
    
    //Checks if it's an operation on first part (of program 2)
    public boolean checkOP1(Line l1, Line l2, Line lB){
        if (l2.getOp1().equals("parada"))
            return false;
        if (l1.getOp1().equals("ciclo"))
            return false;
        lB.setOp1(l1.getNextLabel1());
        lB.setNextLabel1(l2.getNextLabel1());
        return true;
    }
    
    //Checks if it's an operation on second part (of program 2)
    public boolean checkOP2(Line l1, Line l2, Line lB){
        if (l2.getOp2().equals("parada"))
            return false;
        if (l2.getOp2().equals("ciclo"))
            return false;
        lB.setOp2(l1.getNextLabel2());
        lB.setNextLabel2(l2.getNextLabel2());
        return true;
    }
    
    public boolean checkPart2(Line lineP1, Line lineP2, Line lineB){
        if (lineP1.getOp2().equals("parada")){//For another stop
            if (checkStop2(lineP1, lineP2, lineB)){
                comparisson.add(lineB);
                return true;
            } 
        }
        else if (lineP1.getOp2().equals("ciclo")){//For infinite cicle
            if (checkCicle2(lineP1, lineP2, lineB)){
                comparisson.add(lineB);
                return true;
            }
        }
        else{//Second part of program 1 must be an OP then
            if (checkOP2(lineP1, lineP2, lineB)){
                comparisson.add(lineB);
                return true;
            }
            //
        }
        System.out.println("Stoped comparing at label: " + lineP1.getLabel().charAt(0));
        return false;
    }
    
    public boolean compare(){
        Line lineP1, lineP2;
        Line lineB = new Line();
        for (int i=0; i >= irc1.size() && i >= irc2.size(); i++){
            lineP1 = irc1.get(i);
            lineP2 = irc2.get(i);
            lineB.setLabel(Integer.toString(lineNum));
            
            /*if (lineP1.getOp1().equals("parada")){
                if (lineP2.getOp1().equals("parada")){
                    lineB.setOp1(lineP1.getNextLabel1());
                    lineB.setNextLabel1(lineP2.getNextLabel1());
                }
            }
            else if (lineP1.getOp1().equals("ciclo")){
                    if (lineP2.getOp1().equals("ciclo")){
                        lineB.setOp1(lineP1.getNextLabel1());
                        lineB.setNextLabel1(lineP2.getNextLabel1());
                    }
            
            }*/
            //if (checkStop1(lineP1, lineP2, lineB) || checkCicle1(lineB, lineB, lineB)){
            //In case first OP is Stop:
            if (lineP1.getOp1().equals("parada")){
                if (checkStop1(lineP1, lineP2, lineB)){//Finish first part
                    //Checking the second part:
                    /*if (lineP1.getOp2().equals("parada")){//For another stop
                        if (checkStop2(lineP1, lineP2, lineB))
                            comparisson.add(lineB);
                    }
                    else if (lineP1.getOp2().equals("ciclo")){//For infinite cicle
                        if (checkCicle2(lineP1, lineP2, lineB))
                            comparisson.add(lineB);
                    }
                    else{//Second part of program 1 must be an OP then
                        if (checkOP2(lineP1, lineP2, lineB))
                            comparisson.add(lineB);
                        else{
                            System.out.println("Stoped comparing at index: " + i);
                            return false;
                        }
                    }*/
                    if (!checkPart2(lineP1, lineP2, lineB))
                        return false;
                }
                else{
                    System.out.println("Stoped comparing at label: " + lineP1.getLabel().charAt(0));
                    return false;
                }
                    
            }
            //In case first OP is cicle:
            else if (lineP1.getOp1().equals("ciclo")){
                if (checkCicle1(lineP1, lineP2, lineB)){//Finish first part
                    if (!checkPart2(lineP1, lineP2, lineB))
                        return false;
                }
                else{
                    System.out.println("Stoped comparing at label: " + lineP1.getLabel().charAt(0));
                    return false;
                }
                    
            }
            //If it's neighter "parada" or "ciclo", there's just one last option:
            //In case part 1 is an OP
            else{
                if (checkOP1(lineP1, lineP2, lineB)){//Checks if it's also OP in program 2
                    if (!checkPart2(lineP1, lineP2, lineB))
                        return false;
                }
                else{
                    System.out.println("Stoped comparing at label: " + lineP1.getLabel().charAt(0));
                    return false;
                }
                    
            }
            lineNum += 1;
        }
        //Since all bad possibililities has passed, programs are strongly equivalents
        return true;
    }
    
    public void printComparisson(){
        String begin1 = Character.toString(irc1.get(0).getLabel().charAt(0));
        String begin2 = Character.toString(irc2.get(0).getLabel().charAt(0));
        
        System.out.println("B0 = {(" + begin1 + ", " + begin2 + ")}");
        
        for (Line l : comparisson){
            System.out.println(l.getVerifiedLine());
        }
    }
    
    public void doStep4(){
        updateIRC(irc1);
        updateIRC(irc2);
        if (compare())
            System.out.println("These programs are strongly equivalent!");
        else
            System.out.println("These programs are not strongly equivalent.");
        printComparisson();
    }
}
