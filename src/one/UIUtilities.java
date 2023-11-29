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
import javax.swing.JToggleButton;

public class UIUtilities {
	public static JRadioButton[] secondarySkillRadioButtons() {
		JRadioButton[] toBeReturned = new JRadioButton[4];
		toBeReturned[0] = new JRadioButton("No");
		toBeReturned[0].setSelected(true);
		toBeReturned[1] = new JRadioButton("Basic");
		toBeReturned[2] = new JRadioButton("Advanced");
		toBeReturned[3] = new JRadioButton("Expert");
		for (int i = 0; i < 4; i++) {
			toBeReturned[i].setFocusable(false);
			toBeReturned[i].setSize(10, 10);
		}
		group(toBeReturned);
		return toBeReturned;
	}

	private static void group(JRadioButton[] skills) {
		ButtonGroup skillGroup = new ButtonGroup();
		for (int i = 0; i < 4; i++) {
			skillGroup.add(skills[i]);
		}
	}

	public static JPanel secondarySkillPanel(String skillName, JRadioButton[] skills, int x, int y, int weight,
			int height) {
		JPanel panel = new JPanel();
		JLabel skillLabel = new JLabel(skillName);
		panel.add(skillLabel);
		panel.add(skills[0]);
		panel.add(skills[1]);
		panel.add(skills[2]);
		panel.add(skills[3]);
		panel.setBounds(x, y, weight, height);
		return panel;
	}

	public static JTextField limitingFieldsToNumbersSettingBounds(int x, int y, int weight, int height, Object newParam, Object newParam2) {
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
		return textField;
	}

	public static JPanel statsPanel(JTextField attack, JTextField defence) {
		JLabel heroAttackLabel = new JLabel("Hero's attack skill");
		heroAttackLabel.setBounds(0, 0, 130, 30);

		JLabel heroDefenseLabel = new JLabel("Hero's defense skill");
		heroDefenseLabel.setBounds(290, 0, 130, 30);
		JPanel statsPanel = new JPanel();
		statsPanel.setLayout(null);
		statsPanel.add(heroAttackLabel);
		statsPanel.add(attack);
		statsPanel.add(heroDefenseLabel);
		statsPanel.add(defence);
		statsPanel.setBounds(30, 120, 420, 60);
		return statsPanel;
	}

	public static JTextField[] SkillSpecialtyLevel() {
		JTextField[] toBeReturned = new JTextField[3];
		toBeReturned[0] = limitingFieldsToNumbersSettingBounds(350, 0, 40, 30, null, null);
		toBeReturned[1] = limitingFieldsToNumbersSettingBounds(350, 30, 40, 30, null, null);
		toBeReturned[2] = limitingFieldsToNumbersSettingBounds(350, 60, 40, 30, null, null);
		return toBeReturned;
	}

	public static JComboBox<String> creatureList(JFrame frame, String[] creaturesNames) {
		JComboBox<String> list = new JComboBox<String>(creaturesNames);
		list.setBounds(0, 30, 140, 30);
		list.addActionListener((ActionListener) frame);
		return list;
	}

	public static JButton submitButton(JFrame frame) {
		JButton button = new JButton();
		button.setBounds(30, 300, 420, 60);
		button.addActionListener((ActionListener) frame);
		button.setText("Sumbit");
		button.setFocusable(false);
		return button;
	}
	public static JButton swapButton(JFrame frame) {
		JButton button = new JButton();
		button.setBounds(223, 220, 80, 30);
		button.addActionListener((ActionListener) frame);
		button.setText("Swap");
		button.setFocusable(false);
		return button;
	}
		public static JToggleButton meleeButton(JFrame frame) {
		JToggleButton button = new JToggleButton();
		button.setBounds(30, 250, 140, 30);
		button.addActionListener((ActionListener) frame);
		button.setText("Melee");
		button.setFocusable(false);
		button.setVisible(false);
		return button;
	}
	// public static void showOrHideMelee(JFrame frame ,JComboBox<String> creatureListAttacker, JToggleButton button, Creature[] creature) {
	// 	creatureListAttacker.addItemListener((ItemListener) new ItemListener() {
    //         public void itemStateChanged(ItemEvent ie) {
	// 			if(findCreatureFromList((String)creatureListAttacker.getSelectedItem(), creature).isRanged)
    //             button.setVisible(true);
	// 			else
	// 			button.setVisible(false);
    //         }
    //     });
	// }
	// broken method
	// public static void showOrHideMelee(JComboBox<String> creatureListAttacker, Creature[] creatures,
	// 		JToggleButton melee) {
	// 	creatureListAttacker.addActionListener(new ActionListener() {
	// 		@Override
	// 		public void actionPerformed(ActionEvent e) {
	// 			String creatureNameAttacker = String.valueOf(creatureListAttacker.getSelectedItem());
	// 			if (findCreatureFromList(creatureNameAttacker, creatures).isRanged)
	// 				melee.setVisible(true);
	// 			else
	// 				melee.setVisible(false);
	// 			melee.setSelected(false);
	// 		}
	// 	});
	// }



	public static Creature findCreatureFromList(String creatureName, Creature[] allCreatures) {
		for (int i = 0; i < allCreatures.length; i++) {
			if (creatureName.equals(allCreatures[i].name))
				return allCreatures[i];
		}
		return null;
	}

	public static JPanel defenderPanel(JComboBox<String> defenderCreatureList, JTextField defenderCreatureSearch) {
		JPanel creatureListAttackerPanel = new JPanel();
		creatureListAttackerPanel.setLayout(null);
		creatureListAttackerPanel.add(defenderCreatureList);
		creatureListAttackerPanel.add(defenderCreatureSearch);
		creatureListAttackerPanel.setBounds(320, 190, 180, 60);
		return creatureListAttackerPanel;
	}

	public static JPanel attackerPanel(JComboBox<String> attackerCreatureList, JTextField creatureCount,
			JTextField creatureSearchAttacker) {
		JPanel creatureListAttackerPanel = new JPanel();
		creatureListAttackerPanel.setLayout(null);
		creatureListAttackerPanel.add(attackerCreatureList);
		creatureListAttackerPanel.add(creatureCount);
		creatureListAttackerPanel.add(creatureSearchAttacker);
		creatureListAttackerPanel.setBounds(30, 190, 180, 60);
		return creatureListAttackerPanel;
	}

	public static JTextField creatureSearch() {
		JTextField toBeReturned = new JTextField();
		toBeReturned.setBounds(0, 0, 140, 30);
		toBeReturned.setEditable(false);
		return toBeReturned;
	}

	public static String characterPressed(String input, char e) {
		if (e == (char) 8) {
			input = deleteLastChar(input);
			return input;
		}
		if (e == (char) 27)
			return "";
		if (e < 65 || e > 122)
			return input;
		return input += e;
	}

	private static String deleteLastChar(String s) {
		if (s.length() == 0)
			return s;
		String toBeReturned = new String("");
		for (int i = 0; i < s.length() - 1; i++) {
			toBeReturned += s.charAt(i);
		}
		return toBeReturned;
	}

	public static String[] creatureNamesFilter(String[] creatureNames, String input) {
		ArrayList<String> picked = new ArrayList<>();
		for (int i = 0; i < creatureNames.length; i++) {
			if (creatureNames[i].toLowerCase().contains(input))
				picked.add(creatureNames[i]);
		}
		return arraysListToString(picked);

	}

	private static String[] arraysListToString(ArrayList<String> al) {
		String[] toBeReturned = new String[al.size()];
		for (int i = 0; i < al.size(); i++) {
			toBeReturned[i] = al.get(i);
		}
		return toBeReturned;
	}

	public static void damageDealthLabel(JLabel label ,int health, int minDamage, int maxDamage, int creaturesNumber) {
		label.setFont(new Font("", Font.PLAIN, 22));
		label.setBounds(30, 370, 420, 85);

	
			if (minDamage == maxDamage)
				label.setText("Deals " + minDamage + " damage  Kills " + minDamage / health
						+ " + "
						+ minDamage % health + " HP");
			else {
				label.setText("<html>" + minDamage + "-" + maxDamage + " damage: "
						+ (minDamage + maxDamage) / 2 + " on average" + "<br/>" + "<html>"
						+ minDamage / health + "+" + minDamage % health
						+ " HP"
						+ " - " + maxDamage / health + "+"
						+ maxDamage % health
						+ " HP" + "<br/>" + (minDamage + maxDamage) / 2 / health + "+"
						+ ((minDamage + maxDamage)/2) % health + " HP on average</html>");
			} 
	}
	public static void clearInputs(JComboBox<String>creatureList, JTextField creatureSearchField, String searchString, String[] creaturesNames){
		creatureSearchField.setText("");
		searchString="";
		creatureList.removeAllItems();
		for (String name : creaturesNames) {
			creatureList.addItem(name);
		}
	}
	public static void main(String[] args) {
		new MyFrame();
	}
	
}