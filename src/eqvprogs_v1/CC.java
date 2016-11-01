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
public class CC {//Cadeia de Conjuntos
    private int group;
    private String lines = "";

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public String getLines() {
        return lines;
    }

    public void setLines(String lines) {
        this.lines += lines;
    }
    
    public String getChain(){
        return ("A" + group + " = {" + lines + "}");
    }
    
}
