package one;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class MyFrameTest extends JFrame implements ActionListener, KeyListener {
	String creatureName;
MyFrameTest(){
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setSize(100, 100);
	this.setLayout(null);
	this.addKeyListener(this);
	this.setVisible(true);
}
	@Override
	public void keyTyped(KeyEvent e) {
		System.out.println(e.getKeyChar());
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	public static void main(String[] args) {
		new MyFrameTest();
	}

}
