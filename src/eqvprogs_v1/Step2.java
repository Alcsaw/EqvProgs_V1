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
public class Step2 {
    
    private List<CC> cc1 = new ArrayList();// List containing on each line, a group and a list of lines;
    private List<CC> cc2 = new ArrayList();
    private List<Line> irc1 = new ArrayList();// IRCs that is going to be put as CC
    private List<Line> irc2 = new ArrayList();
    
    public void setIRC(List<Line> irc1, List<Line> irc2) {
        //Creating a new list not to modify the original IRC 
        this.irc1.addAll(irc1);
        this.irc2.addAll(irc2);
    }
    
    public List<Line> getIRC1left(){
        return this.irc1;
    }
    
    public List<Line> getIRC2left(){
        return this.irc2;
    }
    
    public void toCC(List<Line> irc, List<CC> cc){
        String line;
        int lastStopIndex = 0;
        int group = 0;
        String lines = "";

        //Verify last stop
        for (Line l : irc){
            //line = l.getLine();

            // if "parada" is on first part:
            if (l.getOp1().equals("parada") || l.getOp2().equals("parada")){
                lastStopIndex = irc.indexOf(l);

            }
        }
        //System.out.println("ULTIMA PARADA EM: " + lastStopIndex);
        //get what's with stop if there's something
        //come back in list comparing lines searching for equals
        
        //Add "parada" to CC list:
        CC x = new CC();
        x.setGroup(group);
        group += 1;
        lines += "&";
        x.setLines(lines);
        cc.add(x);
        
        //from last stop to beggining of the List:
        for (int i = lastStopIndex; i >= 0; i--){
            x = new CC();
            //Get the line at index i and set it to String line
            line = irc.get(i).getLine();
            //removes this Line from List
            irc.remove(i);
            //and compares it with the rest of the list
            for (Line l : irc){
                String currentLine = l.getLine();
                //If it has an equal line, then add to lines List
                //if (l.getLine().equals(line))
                int tamanhoLinha = line.length();
                int tamanhoLinhaAtual = currentLine.length();
                //Trying to find out what the freak is that
                
                currentLine = currentLine.substring(1);
                for(int z = 0; z < currentLine.length() ; z ++){
                    if(line.charAt(z) == currentLine.charAt(z))
                        System.out.print(currentLine.charAt(z));
	        else
	            System.out.println(currentLine.charAt(z) + "aqui ta o problema");
                }
                
                
                if (currentLine.substring(1).equals(line.substring(1)))
                    //x.setLines(Character.toString(line.charAt(0)));
                    lines += ", " + Character.toString(currentLine.charAt(0));
                
            }
            //Add new group and its lines to CC list
            x.setGroup(group);
            group += 1;
            //x.setLines(Character.toString(line.charAt(0)));
            lines += ", " + Character.toString(line.charAt(0));
            x.setLines(lines);
            cc.add(x);
        }
    }
    
    public void printCC(List<CC> cc){
        String group = "";
        for (CC ancestors : cc){
            group = ancestors.getChain();
            System.out.println(group);
        }
        int lastGroup = Character.getNumericValue(group.charAt(1));
        lastGroup += 1;
        String replacement = Integer.toString(lastGroup);
        
        System.out.println(group.replace(group.substring(0, 1), replacement));
    }
    
    public void doStep2(){
        toCC(irc1, cc1);
        System.out.println("CC 1:");
        printCC(cc1);
        
        toCC(irc2, cc2);
        System.out.println("CC 2:");
        printCC(cc2);
        
    }
    
}
