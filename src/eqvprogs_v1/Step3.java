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
public class Step3 {
    
    private List<Line> irc1Original = new ArrayList();
    private List<Line> irc2Original = new ArrayList();
    
    private List<Line> irc1left = new ArrayList();
    private List<Line> irc2left = new ArrayList();
    
    private boolean simplification = false;

    public List<Line> getIrc1Original() {
        return irc1Original;
    }

    public void setIrc1Original(List<Line> irc1Original) {
        this.irc1Original = irc1Original;
    }

    public List<Line> getIrc2Original() {
        return irc2Original;
    }

    public void setIrc2Original(List<Line> irc2Original) {
        this.irc2Original = irc2Original;
    }
    
    public void setIRC1left(List<Line> irc1){
        this.irc1left = irc1;
    }
    
    public void setIRC2left(List<Line> irc2){
        this.irc2left = irc2;
    }
    
    public void checkOutOfLimit(List<Line> ircLeft, List<Line> ircOriginal){
        for (Line l : ircLeft){
            String line = l.getLine();
            if (line.contains("w")){
                continue;
            }
            else{
                simplification = true;
                System.out.println("FORA DO LIMITE: " + line);
                System.out.println("Removing...");
                ircOriginal.remove(l);
            }
        }
    }
    
    public void printSimplification(List<Line> irc){
        String line;
        boolean cicle = false;
        
        if (simplification){
            System.out.println("Simplification:");
            
            for (Line l : irc){
                line = l.getLine();
                System.out.println(line);
                if (line.contains("w"))
                    cicle = true;
            }
            if (cicle == true)
                System.out.println("w: (ciclo, w), (ciclo, w)");
        }
        else
            System.out.println("Não há simplificação, pois não há rótulos fora do limite do programa.");
        
    }
    
    public void doStep3(){
        checkOutOfLimit(irc1left, irc1Original);
        checkOutOfLimit(irc2left, irc2Original);
        printSimplification(irc1Original);
        printSimplification(irc2Original);
    }
}
