package one;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;

public class MyFrameTest extends JFrame implements ActionListener {
	String creatureName;
MyFrameTest(){
	JComboBox test=ObjectCreator.creatureList(1, 1, 1, 1, this);
	
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setSize(100, 100);
	this.add(test);
	this.pack();
	this.setLayout(null);
	this.setVisible(true);
}

	public static void main(String[] args) {
		// new MyFrameTest();
		System.out.println((char)(8));
		String a="abc";
		char[] b=a.toCharArray(); 
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

}
