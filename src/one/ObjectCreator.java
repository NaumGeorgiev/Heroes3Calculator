package one;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class ObjectCreator {
	public static JRadioButton secondarySkillRadioButton(JFrame frame, String skillLevel) {
		JRadioButton skill = new JRadioButton(skillLevel);
		skill.setFocusable(false);
		skill.setSize(10, 10);
		return skill;
	}

	public static void secondarySkillGroups(JRadioButton noOffense, JRadioButton basicOffense, JRadioButton advancedOffense,
			JRadioButton expertOffense, JRadioButton noArmorer, JRadioButton basicArmorer, JRadioButton advancedArmorer,
			JRadioButton expertArmorer, JRadioButton noArchery, JRadioButton basicArchery, JRadioButton advancedArchery,
			JRadioButton expertArchery) {
		ButtonGroup offenseGroup = new ButtonGroup();
		ButtonGroup armorerGroup = new ButtonGroup();
		ButtonGroup archeryGroup = new ButtonGroup();
		offenseGroup.add(noOffense);
		offenseGroup.add(basicOffense);
		offenseGroup.add(advancedOffense);
		offenseGroup.add(expertOffense);
		armorerGroup.add(noArmorer);
		armorerGroup.add(basicArmorer);
		armorerGroup.add(advancedArmorer);
		armorerGroup.add(expertArmorer);
		archeryGroup.add(noArchery);
		archeryGroup.add(basicArchery);
		archeryGroup.add(advancedArchery);
		archeryGroup.add(expertArchery);
	}

	public static JPanel secondarySkillPanel(String skillName, JRadioButton no, JRadioButton basic, JRadioButton advanced,
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

	public static JComboBox<String> creatureList(int x, int y, int weight, int height, JFrame frame, String[] creaturesNames) {
		JComboBox<String> list = new JComboBox<String>(creaturesNames);
		list.setBounds(x, y, weight, height);
		list.addActionListener((ActionListener) frame);
		return list;
	}

	public static JButton button(JFrame frame) {
		JButton button = new JButton();
		button.setBounds(30, 270, 420, 60);
		button.addActionListener((ActionListener) frame);
		button.setText("Sumbit");
		button.setFocusable(false);
		return button;
	}

	public static Creature findCreatureFromList(String creatureName, Creature[] creature) {
		for (int i = 0; i < 156; i++) {
			if (creatureName.equals(creature[i].name))
				return creature[i];
		}
		return null;
	}
	public static JPanel attackerPanel(JComboBox<String> creatureListAttacker, JTextField numberOfAttackingCreatures, JTextField attackingCreatureTextField){

		JPanel creatureListAttackerPanel = new JPanel();
		creatureListAttackerPanel.setLayout(null);
		creatureListAttackerPanel.add(creatureListAttacker);
		creatureListAttackerPanel.add(numberOfAttackingCreatures);
		creatureListAttackerPanel.add(attackingCreatureTextField);
		creatureListAttackerPanel.setBounds(30, 190, 180, 60);
		return creatureListAttackerPanel;
	}

	public static String characterPressed(String input, char e){
		if(e==(char)8){
			input=deleteLastChar(input);
			return input;
		}
		if(e<65 || e>122){
			return input;
		}
		input+=e;
		if(input.length()==1)
		input=input.toUpperCase();
		return input;
	}
	private static String deleteLastChar(String s){
		if(s.length()==0)
		return s;
		String toBeReturned=new String("");
		for(int i=0; i<s.length()-1; i++){
			toBeReturned+=s.charAt(i);
		}
		return toBeReturned;
	}
	
	public static String[] creatureNamesPick(String[] creatureNames, String input){
		ArrayList<String> picked=new ArrayList<>();
		for(int i=0; i<creatureNames.length; i++){
			if(creatureNames[i].startsWith(input))
			picked.add(creatureNames[i]);
		}
		return arraysListToString(picked);
		
	}
	private static String[] arraysListToString(ArrayList<String> al){
		String[] toBeReturned =new String[al.size()];
		for(int i=0; i<al.size(); i++){
			toBeReturned[i]=al.get(i);
		}
		return toBeReturned;
	}

	public static void damageDealthLabel(JLabel label, Creature attackingCreature, Creature defendingCreature) {
		label.setFont(new Font("", Font.PLAIN, 22));
		label.setBounds(30, 340, 420, 85);
		int[] totalDamage = Calculator.calculate();
		int minTotalDamage = totalDamage[0];
		int maxTotalDamage = totalDamage[1];
		if (minTotalDamage == 0)
			minTotalDamage = 1;
		if (maxTotalDamage == 0)
			maxTotalDamage = 1;
		if (Calculator.creaturesNumber != 0) {
			if (minTotalDamage == maxTotalDamage)
				label.setText("Deals " + minTotalDamage + " damage  Kills " + minTotalDamage / defendingCreature.health + " + "
						+ minTotalDamage % defendingCreature.health + " HP");
			else {
				label.setText("<html>" + minTotalDamage + "-" + maxTotalDamage + " damage: "
						+ (minTotalDamage + maxTotalDamage) / 2 + " on average" + "<br/>" + "<html>"
						+ minTotalDamage / defendingCreature.health + "+" + minTotalDamage % defendingCreature.health + " HP"
						+ " - " + maxTotalDamage / defendingCreature.health + "+" + maxTotalDamage % defendingCreature.health
						+ " HP" + "<br/>"  + (minTotalDamage + maxTotalDamage) / 2 / defendingCreature.health + "+"
						+  (minTotalDamage + maxTotalDamage) / 2 % defendingCreature.health + " HP on average</html>");
			}
		} else {
			label.setText("Insert number of attacking creatures");
		}
	}

	public static void main(String[] args) {
		new MyFrame();
	}

}