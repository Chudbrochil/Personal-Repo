// Anthony Galczak - agalczak@cnm.edu
// Java I - Program 4 GUI by NetBeans
// GalczakP4.java - Main


package galczakp4;


import javax.swing.JFrame;



public class GalczakP4 {

    
    JFrameGUI frame;

    public static void main(String[] args) {
        GalczakP4 app = new GalczakP4();
    }
    
    public GalczakP4(){
        frame = new JFrameGUI();
        frame.setTitle("Computer Store");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
}
