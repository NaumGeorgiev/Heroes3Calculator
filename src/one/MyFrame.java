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
import javax.swing.JToggleButton;

class CreatureList {
	JComboBox<String> comboBox;
	JTextField searchField = UIUtilities.creatureSearch();
	String searchString;
	String[] creaturesNames = Creature.createNames();

	CreatureList() {
		comboBox.addKeyListener((KeyListener) new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				searchString = UIUtilities.characterPressed(searchString, c);
				searchField.setText(searchString);
				String[] filteredNames = UIUtilities.creatureNamesFilter(creaturesNames, searchString);
				comboBox.removeAllItems();
				for (String name : filteredNames) {
					comboBox.addItem(name);
				}
			}
		});
	}
}

public class MyFrame extends JFrame implements ActionListener {
	boolean behemoth = false;
	boolean nix = false;
	boolean ancientBehemoth = false;
	boolean nixWarrior = false;
	int attack;
	int defence;
	int health;
	int armorer;
	int offence;
	int archery;
	int offenseHeroLevel;
	int archeryHeroLevel;
	int amorerHeroLevel;
	boolean isRanged;
	double minDamage;
	double maxDamage;
	int creaturesNumber = 0;

	JRadioButton[] offenceButton = UIUtilities.secondarySkillRadioButtons();
	JRadioButton[] armorerButton = UIUtilities.secondarySkillRadioButtons();
	JRadioButton[] archeryButton = UIUtilities.secondarySkillRadioButtons();
	JTextField offenseSpecialtyLevel = UIUtilities.limitingJTextFieldsToNumbersAndSettingBounds(350, 0, 40, 30);
	JTextField armorerSpecialtyLevel = UIUtilities.limitingJTextFieldsToNumbersAndSettingBounds(350, 30, 40, 30);
	JTextField archerySpecialtyLevel = UIUtilities.limitingJTextFieldsToNumbersAndSettingBounds(350, 60, 40, 30);

	JTextField heroAttackTextField;
	JTextField heroDefenceTextField;

	Creature[] creatures = Creature.createAll();
	String[] creaturesNames = Creature.createNames();
	JComboBox<String> creatureListAttacker;
	JComboBox<String> creatureListDefender;
	//CreatureList attackerCreatureList;
	//CreatureList defenderCreatureList;

	JTextField creaturesNumberTexitField = UIUtilities.limitingJTextFieldsToNumbersAndSettingBounds(140, 30, 40, 30);;
	String inputAttacker = new String();
	String inputDefender = new String();

	JTextField creatureSearchAttacker = UIUtilities.creatureSearch();
	JTextField creatureSearchDefender = UIUtilities.creatureSearch();

	JButton submit = UIUtilities.button(this);;
	JToggleButton melee = UIUtilities.toggleButton(this);
	JLabel damageDealth = new JLabel();

	MyFrame() {
		JPanel offensePanel = UIUtilities.secondarySkillPanel("Offense:", offenceButton, 0, 0, 350, 30);
		JPanel armorerPanel = UIUtilities.secondarySkillPanel("Armorer:", armorerButton, 0, 30, 350, 30);
		JPanel archeryPanel = UIUtilities.secondarySkillPanel("Archery:", archeryButton, 0, 60, 350, 30);

		heroAttackTextField = UIUtilities.limitingJTextFieldsToNumbersAndSettingBounds(0, 30, 40, 30);
		heroDefenceTextField = UIUtilities.limitingJTextFieldsToNumbersAndSettingBounds(290, 30, 40, 30);
		JPanel statsPanel = UIUtilities.statsPanel(heroAttackTextField, heroDefenceTextField);

		creatureListAttacker = UIUtilities.creatureList(this, creaturesNames);
		// UIUtilities.showOrHideMelee(this, creatureListAttacker, melee, creatures);
		creatureListDefender = UIUtilities.creatureList(this, creaturesNames);
		JPanel attackerPanel = UIUtilities.attackerPanel(creatureListAttacker, creaturesNumberTexitField,
				creatureSearchAttacker);
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
		this.add(attackerPanel);
		this.add(defenderPanel);
		this.add(submit);
		this.add(melee);
		this.add(damageDealth);
		this.setVisible(true);
		this.repaint();

	}

	public void actionPerformed(ActionEvent e) {
		// if (creatureCount == 0) { setText(whatever); return; }
		if (e.getSource() == submit) {
			for (int i = 0; i < 4; i++) {
				if (offenceButton[i].isSelected())
					offence = i;
				if (armorerButton[i].isSelected())
					armorer = i;
				if (archeryButton[i].isSelected())
					archery = i;
			}

			if (armorerSpecialtyLevel.getText().isEmpty() == false)
				amorerHeroLevel = Integer.valueOf(armorerSpecialtyLevel.getText());
			else
				amorerHeroLevel = 0;
			if (offenseSpecialtyLevel.getText().isEmpty() == false)
				offenseHeroLevel = Integer.valueOf(offenseSpecialtyLevel.getText());
			else
				offenseHeroLevel = 0;
			if (archerySpecialtyLevel.getText().isEmpty() == false)
				archeryHeroLevel = Integer.valueOf(archerySpecialtyLevel.getText());
			else
				archeryHeroLevel = 0;

			String creatureNameAttacker = String.valueOf(creatureListAttacker.getSelectedItem());
			Creature creatureAttacker = UIUtilities.findCreatureFromList(creatureNameAttacker, creatures);
			//Creature creatureAttacker = attackerCreatureList.getSelectedCreature();
			String creatureNameDefender = String.valueOf(creatureListDefender.getSelectedItem());
			Creature creatureDefender = UIUtilities.findCreatureFromList(creatureNameDefender, creatures);
			minDamage = creatureAttacker.minDamage;
			maxDamage = creatureAttacker.maxDamage;
			isRanged = creatureAttacker.isRanged;
			health = creatureDefender.health;

			if (heroAttackTextField.getText().isEmpty() == false)
				attack = Integer.valueOf(heroAttackTextField.getText()) + creatureAttacker.attack;
			else
				attack = creatureAttacker.attack;
			if (heroDefenceTextField.getText().isEmpty() == false)
				defence = Integer.valueOf(heroDefenceTextField.getText()) + creatureDefender.defense;
			else
				defence = creatureDefender.defense;

			if (creatureNameAttacker.equals("Behemoth"))
				behemoth = true;
			else
				behemoth = false;
			if (creatureNameAttacker.equals("AncientBehemoth"))
				ancientBehemoth = true;
			else
				ancientBehemoth = false;
			if (creatureNameDefender.equals("Nix"))
				nix = true;
			else
				nix = false;
			if (creatureNameDefender.equals("NixWarrior"))
				nixWarrior = true;
			else
				nixWarrior = false;

			if (creaturesNumberTexitField.getText().isEmpty() == false)
				creaturesNumber = Integer.valueOf(creaturesNumberTexitField.getText());
			else
				creaturesNumber = 0;

			Calculator calculator = new Calculator(behemoth, nix, ancientBehemoth, nixWarrior, attack, defence, health,
					armorer, offence, archery, offenseHeroLevel, archeryHeroLevel, amorerHeroLevel, isRanged, minDamage,
					maxDamage, creaturesNumber);
			int[] totalDamage = calculator.calculate(melee.isSelected(), creatureAttacker, creatureDefender);

			UIUtilities.damageDealthLabel(damageDealth, health, totalDamage[0], totalDamage[1], creaturesNumber);

			if (creaturesNumber != 0) {
				//attackerCreatureList.clearSearch();
				//defenderCreatureList.clearSearch();
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
}
