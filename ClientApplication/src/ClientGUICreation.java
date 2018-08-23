import javax.swing.JFrame;

/**
 * Write a description of class GUI here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class ClientGUICreation {
    public static void main(String [] args) {
    	ClientGUI clientGUI = new ClientGUI();
    	clientGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	clientGUI.setSize(300,150);
    	clientGUI.setVisible(true);
    }
}
