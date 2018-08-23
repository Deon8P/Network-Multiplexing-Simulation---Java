import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ClientGUI extends JFrame {
	
	// Components variable names
	private JLabel lblNum;
	private JSlider numChannels;
	private JButton btnSend;
	
	// Other variables
	private ArrayList<String> messages = new ArrayList<String>();
	private int channels =1;
	private Timer timer;
	private int timeValue = 0;
	
	//private JTextField txtMessage;
	
	// Constructor for creating components and adding listeners
	public ClientGUI(){
		super("Client");
		setLayout(null);
		
		numChannels = new JSlider();
		numChannels.setBounds(10,20,100,20);
		numChannels.setValue(1);
		numChannels.setMinimum(1);
		numChannels.setMaximum(10);
		add(numChannels);
		
		btnSend = new JButton("Set channels");
		btnSend.setBounds(10, 50, 120, 20);
		add(btnSend);
		
		lblNum = new JLabel();
		lblNum.setBounds(115, 17, 120, 20);
		add(lblNum);
		
		// Legacy text field for entering a single test value
		/*
		txtMessage = new JTextField();
		txtMessage.setBounds(10, 300, 100, 20);
		add(txtMessage);*/
		
		// Creating handler object
		Handler handler = new Handler();
		
		// Adding components to the different listeners
		btnSend.addActionListener(handler);
		numChannels.addChangeListener(handler);
		lblNum.setText("Channels: "+String.valueOf(numChannels.getValue()));
	}
	
	
	private class Handler implements ActionListener, ChangeListener{
		
		// Sets the amount of channels that will be sent, the amount of messages or users.
		public void stateChanged(ChangeEvent event) {
			if(event.getSource() == numChannels) {
				channels = numChannels.getValue();
				lblNum.setText("Channels: " + String.valueOf(channels));
			}
		}
		
		// The button is pressed and the message is sent to the server application.
		public void actionPerformed(ActionEvent event) {
			if(event.getSource() == btnSend) {
				try{
					for(int i = 0; i < channels; i++) {
						messages.add(JOptionPane.showInputDialog(null, "User #"+ (i+1) + " message."));
					}
					timer = new Timer();
					timer.schedule(new SendMessage(), 0, 1000);
				}catch(Exception exc){System.out.println(exc);}
			}
		}
		
		private class SendMessage extends TimerTask{

			public void run() {
				try {
					String str = "";
					for (String msg : messages) {
						try {
						if ((msg.charAt(timeValue) != ' ') && (msg.length() > 0) && (msg != null) && (msg != "")) {
							str += msg.charAt(timeValue);
						} 
						else if (String.valueOf(msg.charAt(timeValue)) == null || msg.length() > timeValue) {
							str += '_';
						} else {
							str += '_';
						}
						} catch (Exception exc) {str += '_';}
					}
					System.out.println(str);
					Socket s=new Socket("localhost",6666);
					DataOutputStream dout=new DataOutputStream(s.getOutputStream());
					dout.writeUTF(str);
					dout.flush();  
					dout.close();  
					s.close();
					timeValue++;
				}catch(Exception exc) {System.out.println(exc);}
				
			}
			
		}
	}
	
}
