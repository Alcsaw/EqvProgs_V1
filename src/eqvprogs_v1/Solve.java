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
public class Solve {
    // This class calls all Steps on order to do the comparison between the 2 files chosen
    
    Step1 s1 = new Step1();
    Step2 s2 = new Step2();
    Step3 s3 = new Step3();
    Step4 s4 = new Step4();
    
    public void solve(){
        s1.doStep1();
        s2.setIRC(s1.getIrcP1(), s1.getIrcP2());
        s2.doStep2();
        //s1.printIRC(s1.getIrcP1());
        //s1.printIRC(s1.getIrcP2());
        s3.setIrc1Original(s1.getIrcP1());
        s3.setIrc2Original(s1.getIrcP2());
        s3.setIRC1left(s2.getIRC1left());
        s3.setIRC2left(s2.getIRC2left());
        s3.doStep3();
        
        s4.setIrc1(s3.getIrc1Original());
        s4.setIrc2(s3.getIrc2Original());
        s4.doStep4();
    }
}
