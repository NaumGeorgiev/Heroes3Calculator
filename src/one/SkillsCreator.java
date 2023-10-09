package one;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class SkillsCreator {

	public static JRadioButton createSkillRadioButton(JFrame frame, String skillLevel) {
		JRadioButton skill = new JRadioButton(skillLevel);
		skill.setFocusable(false);
		return skill;
	}

	public static ButtonGroup createGroup(JRadioButton no, JRadioButton basic, JRadioButton advanced, JRadioButton expert) {
		ButtonGroup group = new ButtonGroup();
		group.add(no);
		group.add(basic);
		group.add(advanced);
		group.add(expert);
		return group;
	}

	public static JPanel createSkillPanel(String skillName, JRadioButton no, JRadioButton basic, JRadioButton advanced,
			JRadioButton expert, int x, int y, int weight, int height) {
		JPanel panel = new JPanel();
		JLabel skill = new JLabel(skillName);
		panel.add(skill);
		panel.add(no);
		panel.add(basic);
		panel.add(advanced);
		panel.add(expert);
		panel.setBounds(x, y, weight, height);

		return panel;
	}

	public static JTextField limitingJTextFieldsToNumbersAndSettingBounds(int x, int y, int weight, int height) {
		JTextField textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
					e.consume(); // ignore event
				}
			}
		});
		textField.setBounds(x, y, weight, height);
		textField.setText("0");
		return textField;
	}

	public static JButton createButton(JFrame frame) {
		JButton button = new JButton();
		button.setBounds(30, 270, 420, 60);
		button.addActionListener((ActionListener) frame);
		button.setText("Sumbit");
		button.setFocusable(false);
		return button;
	}

	public static Creature findCreatureFromComboBox(String creatureName, Creature[] creature) {
		for (int i = 0; i < 156; i++) {
			if (creatureName.equals(creature[i].name))
				return creature[i];
		}
		return null;
	}

	public static void damageDealth(JLabel label, Creature attackingCreature, Creature defendingCreature) {
		label.setFont(new Font("MV Boli", Font.PLAIN, 18));
		label.setBounds(30, 340, 420, 60);
		int[] totalDamage = Calculator.calculator();
		int minTotalDamage = totalDamage[0];
		int maxTotalDamage = totalDamage[1];
		if(minTotalDamage==0)
			minTotalDamage=1;
		if(maxTotalDamage==0)
			maxTotalDamage=1;
		if (minTotalDamage == maxTotalDamage)
			label.setText("Deals " + minTotalDamage + " damage  Kills " + minTotalDamage / defendingCreature.health + " + "
					+ minTotalDamage % defendingCreature.health + " HP");
		else {
			label.setText("<html>Deals " + minTotalDamage + "-" + maxTotalDamage + " damage ("
					+ (minTotalDamage + maxTotalDamage) / 2 + " on average)" + "<br/>"+"Kills (" + minTotalDamage / defendingCreature.health + "+" + minTotalDamage % defendingCreature.health
					+ " HP)" + "-(" + maxTotalDamage / defendingCreature.health + "+" + maxTotalDamage % defendingCreature.health
					+ " HP) ("+(minTotalDamage+maxTotalDamage)/2/defendingCreature.health+"+"+(minTotalDamage+maxTotalDamage)/2%defendingCreature.health+ " HP on average)</html>");
		}
		 
	}
	public static void main(String[] args) {
		new MyFrame();
	}

}