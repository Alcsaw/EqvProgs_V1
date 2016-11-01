/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eqvprogs_v1;

/**
 *
 * @author augus
 */
public class Line {
    private String label, op1, op2, nextLabel1, nextLabel2;

    public String getOp1() {
        return op1;
    }

    public void setOp1(String op1) {
        this.op1 = op1;
    }

    public String getOp2() {
        return op2;
    }

    public void setOp2(String op2) {
        this.op2 = op2;
    }

    public String getNextLabel1() {
        return nextLabel1;
    }

    public void setNextLabel1(String nextLabel1) {
        this.nextLabel1 = nextLabel1;
    }

    public String getNextLabel2() {
        return nextLabel2;
    }

    public void setNextLabel2(String nextLabel2) {
        this.nextLabel2 = nextLabel2;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
    
    public String getLine(){
        return (this.label + "(" + this.op1 + ", " + this.nextLabel1 + "), (" + this.op2 + ", " + this.nextLabel2 + ")");
    }
    
    public String getVerifiedLine(){
        return ("B" + label + " = {(" + op1 + ", " + nextLabel1 + "), (" + op2 + ", " + nextLabel2 + ")}");
    }
    
}
