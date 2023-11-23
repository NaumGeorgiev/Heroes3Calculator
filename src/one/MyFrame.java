package one;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class MyFrame extends JFrame implements ActionListener {
	JRadioButton[] offense = UIUtilities.secondarySkillRadioButtons();
	JRadioButton[] armorer = UIUtilities.secondarySkillRadioButtons();
	JRadioButton[] archery = UIUtilities.secondarySkillRadioButtons();
	JTextField offenseSpecialtyLevel = UIUtilities.limitingJTextFieldsToNumbersAndSettingBounds(350, 0, 40, 30);
	JTextField armorerSpecialtyLevel = UIUtilities.limitingJTextFieldsToNumbersAndSettingBounds(350, 30, 40, 30);
	JTextField archerySpecialtyLevel = UIUtilities.limitingJTextFieldsToNumbersAndSettingBounds(350, 60, 40, 30);

	JTextField heroAttackTextField;
	JTextField heroDefenceTextField;

	Creature[] creatures = Creature.createAll();
	String[] creaturesNames = Creature.createNames();
	JComboBox<String> creatureListAttacker;
	JComboBox<String> creatureListDefender;

	JTextField creaturesNumber = UIUtilities.limitingJTextFieldsToNumbersAndSettingBounds(140, 30, 40, 30);;
	String inputAttacker = new String();
	String inputDefender = new String();

	JTextField creatureSearchAttacker=UIUtilities.creatureSearch();
	JTextField creatureSearchDefender=UIUtilities.creatureSearch();

	JButton submit;

	JLabel damageDealth = new JLabel();

	MyFrame() {
		JPanel offensePanel = UIUtilities.secondarySkillPanel("Offense:", offense, 0, 0, 350, 30);
		JPanel armorerPanel = UIUtilities.secondarySkillPanel("Armorer:", armorer, 0, 30, 350, 30);
		JPanel archeryPanel = UIUtilities.secondarySkillPanel("Archery:", archery, 0, 60, 350, 30);

		heroAttackTextField = UIUtilities.limitingJTextFieldsToNumbersAndSettingBounds(0, 30, 40, 30);
		heroDefenceTextField = UIUtilities.limitingJTextFieldsToNumbersAndSettingBounds(290, 30, 40, 30);
		JPanel statsPanel=UIUtilities.statsPanel(heroAttackTextField, heroDefenceTextField);

		creatureListAttacker = UIUtilities.creatureList(this, creaturesNames);
		creatureListDefender = UIUtilities.creatureList(this, creaturesNames);
		JPanel attackerPanel = UIUtilities.attackerPanel(creatureListAttacker, creaturesNumber, creatureSearchAttacker);
		JPanel defenderPanel = UIUtilities.defenderPanel(creatureListDefender, creatureSearchDefender);

		// combine these to unto one method
		creatureListAttacker.addKeyListener((KeyListener) new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				inputAttacker = UIUtilities.characterPressed(inputAttacker, c);
				creatureSearchAttacker.setText(inputAttacker);
				String[] filteredNames = UIUtilities.creatureNamesFilter(creaturesNames, inputAttacker);
				creatureListAttacker.removeAllItems();
				for (String name : filteredNames) {
					creatureListAttacker.addItem(name);
				}
			}
		});
		creatureListDefender.addKeyListener((KeyListener) new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				inputDefender = UIUtilities.characterPressed(inputDefender, c);
				creatureSearchDefender.setText(inputDefender);
				String[] filteredNames = UIUtilities.creatureNamesFilter(creaturesNames, inputDefender);
				creatureListDefender.removeAllItems();
				for (String name : filteredNames) {
					creatureListDefender.addItem(name);
				}
			}
		});

		submit = UIUtilities.button(this);

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(500, 600);
		this.setLayout(null);
		this.setLocation(500, 100);
		this.add(offensePanel);
		this.add(armorerPanel);
		this.add(archeryPanel);
		this.add(offenseSpecialtyLevel);
		this.add(armorerSpecialtyLevel);
		this.add(archerySpecialtyLevel);
		this.add(statsPanel);
		this.add(heroAttackTextField);
		this.add(heroDefenceTextField);
		this.add(attackerPanel);
		this.add(defenderPanel);
		this.add(submit);
		this.add(damageDealth);
		this.setVisible(true);
		this.repaint();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == submit) {
			for (int i = 0; i < 4; i++) {
				if (offense[i].isSelected())
					Calculator.offence = i;
				if (armorer[i].isSelected())
					Calculator.armorer = i;
				if (archery[i].isSelected())
					Calculator.archery = i;
			}

			if (armorerSpecialtyLevel.getText().isEmpty() == false)
				Calculator.amorerHeroLevel = Integer.valueOf(armorerSpecialtyLevel.getText());
			else
				Calculator.amorerHeroLevel = 0;
			if (offenseSpecialtyLevel.getText().isEmpty() == false)
				Calculator.offenseHeroLevel = Integer.valueOf(offenseSpecialtyLevel.getText());
			else
				Calculator.offenseHeroLevel = 0;
			if (archerySpecialtyLevel.getText().isEmpty() == false)
				Calculator.archeryHeroLevel = Integer.valueOf(archerySpecialtyLevel.getText());
			else
				Calculator.archeryHeroLevel = 0;

			String creatureNameAttacker = String.valueOf(creatureListAttacker.getSelectedItem());

			Creature creatureAttacker = UIUtilities.findCreatureFromList(creatureNameAttacker, creatures);
			Calculator.minDamage = creatureAttacker.minDamage;
			Calculator.maxDamage = creatureAttacker.maxDamage;
			Calculator.ranged = creatureAttacker.isRanged;

			String creatureNameDefender = String.valueOf(creatureListDefender.getSelectedItem());
			Creature creatureDefender = UIUtilities.findCreatureFromList(creatureNameDefender, creatures);
			Calculator.health = creatureDefender.health;

			if (heroAttackTextField.getText().isEmpty() == false)
				Calculator.attack = Integer.valueOf(heroAttackTextField.getText()) + creatureAttacker.attack;
			else
				Calculator.attack = 0;
			if (heroDefenceTextField.getText().isEmpty() == false)
				Calculator.defence = Integer.valueOf(heroDefenceTextField.getText()) + creatureDefender.defense;
			else
				Calculator.defence = 0;

			if (creatureNameAttacker.equals("Behemoth"))
				Calculator.behemoth = true;
			if (creatureNameAttacker.equals("AncientBehemoth"))
				Calculator.ancientBehemoth = true;
			if (creatureNameDefender.equals("Nix"))
				Calculator.nix = true;
			else if (creatureNameDefender.equals("NixWarrior"))
				Calculator.nixWarrior = true;

			if (creaturesNumber.getText().isEmpty() == false)
				Calculator.creaturesNumber = Integer.valueOf(creaturesNumber.getText());
			else
				Calculator.creaturesNumber = 0;

			UIUtilities.damageDealthLabel(damageDealth, creatureAttacker, creatureDefender);

			this.add(damageDealth);

			if (Calculator.creaturesNumber != 0) {
				creatureSearchAttacker.setText("");
				creatureListAttacker.removeAllItems();
				inputAttacker = "";
				creatureSearchDefender.setText("");
				creatureListDefender.removeAllItems();
				inputDefender = "";
				for (String name : creaturesNames) {
					creatureListDefender.addItem(name);
					creatureListAttacker.addItem(name);
				}
				creatureListAttacker.setSelectedItem(creatureNameAttacker);
				creatureListDefender.setSelectedItem(creatureNameDefender);

			}
		}

	}

	public static void main(String[] args) {
		new MyFrame();
	}
}
