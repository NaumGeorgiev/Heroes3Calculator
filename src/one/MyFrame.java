package one;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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

// class CreatureList {
// 	JComboBox<String> comboBox;
// 	JTextField searchField = UIUtilities.creatureSearch();
// 	String searchString;
// 	String[] creaturesNames = Creature.createNames();

// 	CreatureList() {
// 		comboBox.addKeyListener((KeyListener) new KeyAdapter() {
// 			public void keyTyped(KeyEvent e) {
// 				char c = e.getKeyChar();
// 				searchString = UIUtilities.characterPressed(searchString, c);
// 				searchField.setText(searchString);
// 				String[] filteredNames = UIUtilities.creatureNamesFilter(creaturesNames, searchString);
// 				comboBox.removeAllItems();
// 				for (String name : filteredNames) {
// 					comboBox.addItem(name);
// 				}
// 			}
// 		});
// 	}
// }

public class MyFrame extends JFrame implements ActionListener {
	boolean isBehemoth = false;
	boolean isNix = false;
	boolean isAncientBehemoth = false;
	boolean isNixWarrior = false;
	int attack;
	int defence;
	int health;
	int armorer;
	int offence;
	int archery;
	int offenseSpecialtyLevel;
	int armorerSpecialtyLevel;
	int archerySpecialtyLevel;
	boolean isRanged;
	double minDamage;
	double maxDamage;
	int creatureCount = 0;

	ItemListener itemListener = new ItemListener() {
		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				if (UIUtilities.findCreatureFromList((String) attackerCreatureList.getSelectedItem(),
						allCreatures).isRanged)
					melee.setVisible(true);
				else
					melee.setVisible(false);

			}
		}
	};

	JRadioButton[] offenceButton = UIUtilities.secondarySkillRadioButtons();
	JRadioButton[] armorerButton = UIUtilities.secondarySkillRadioButtons();
	JRadioButton[] archeryButton = UIUtilities.secondarySkillRadioButtons();
	JTextField offenseSpecialtyLevelField = UIUtilities.limitingFieldsToNumbersSettingBounds(350, 0, 40, 30, null,
			null);
	JTextField armorerSpecialtyLevelField = UIUtilities.limitingFieldsToNumbersSettingBounds(350, 30, 40, 30, null,
			null);
	JTextField archerySpecialtyLevelField = UIUtilities.limitingFieldsToNumbersSettingBounds(350, 60, 40, 30, null,
			null);

	JTextField heroAttackField;
	JTextField heroDefenceField;

	Creature[] allCreatures = Creature.createAll();
	String[] creaturesNames = Creature.createNames();
	JComboBox<String> attackerCreatureList;
	JComboBox<String> defenderCreatureList;
	// CreatureList attackerCreatureList;
	// CreatureList defenderCreatureList;

	JTextField creatureCountField = UIUtilities.limitingFieldsToNumbersSettingBounds(140, 30, 40, 30, null, null);;
	String attackerSearchString = new String();
	String defenderSearchString = new String();

	JTextField attackerSearchField = UIUtilities.creatureSearch();
	JTextField defenderSearchField = UIUtilities.creatureSearch();

	JLabel damageDealth = new JLabel();

	JButton submit = UIUtilities.submitButton(this);;
	JToggleButton melee = UIUtilities.meleeButton(this);
	JButton swap = UIUtilities.swapButton(this);

	MyFrame() {
		JPanel offensePanel = UIUtilities.secondarySkillPanel("Offense:", offenceButton, 0, 0, 350, 30);
		JPanel armorerPanel = UIUtilities.secondarySkillPanel("Armorer:", armorerButton, 0, 30, 350, 30);
		JPanel archeryPanel = UIUtilities.secondarySkillPanel("Archery:", archeryButton, 0, 60, 350, 30);

		heroAttackField = UIUtilities.limitingFieldsToNumbersSettingBounds(0, 30, 40, 30, null, null);
		heroDefenceField = UIUtilities.limitingFieldsToNumbersSettingBounds(290, 30, 40, 30, null, null);
		JPanel statsPanel = UIUtilities.statsPanel(heroAttackField, heroDefenceField);

		attackerCreatureList = UIUtilities.creatureList(this, creaturesNames);
		// UIUtilities.showOrHideMelee(this, creatureListAttacker, melee, creatures);
		defenderCreatureList = UIUtilities.creatureList(this, creaturesNames);
		JPanel attackerPanel = UIUtilities.attackerPanel(attackerCreatureList, creatureCountField,
				attackerSearchField);
		JPanel defenderPanel = UIUtilities.defenderPanel(defenderCreatureList, defenderSearchField);

		attackerCreatureList.addItemListener(itemListener);

		attackerCreatureList.addKeyListener((KeyListener) new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				attackerSearchString = UIUtilities.characterPressed(attackerSearchString, c);
				attackerSearchField.setText(attackerSearchString);
				String[] filteredNames = UIUtilities.creatureNamesFilter(creaturesNames, attackerSearchString);
				attackerCreatureList.removeAllItems();
				for (String name : filteredNames) {
					attackerCreatureList.addItem(name);
				}
			}
		});
		defenderCreatureList.addKeyListener((KeyListener) new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				defenderSearchString = UIUtilities.characterPressed(defenderSearchString, c);
				defenderSearchField.setText(defenderSearchString);
				String[] filteredNames = UIUtilities.creatureNamesFilter(creaturesNames, defenderSearchString);
				defenderCreatureList.removeAllItems();
				for (String name : filteredNames) {
					defenderCreatureList.addItem(name);
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
		this.add(offenseSpecialtyLevelField);
		this.add(armorerSpecialtyLevelField);
		this.add(archerySpecialtyLevelField);
		this.add(statsPanel);
		this.add(attackerPanel);
		this.add(defenderPanel);
		this.add(submit);
		this.add(melee);
		this.add(swap);
		this.add(damageDealth);
		this.setVisible(true);
		this.repaint();

	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==swap){
			String creatureNameAttacker = String.valueOf(attackerCreatureList.getSelectedItem());
			String creatureNameDefender = String.valueOf(defenderCreatureList.getSelectedItem());
			attackerCreatureList.setSelectedItem(creatureNameDefender);
			defenderCreatureList.setSelectedItem(creatureNameAttacker);

		}
		if (e.getSource() == submit) {
			// Integer.valueOf(creatureCountField.getText());
			
			for (int i = 0; i < 4; i++) {
				if (offenceButton[i].isSelected())
					offence = i;
				if (armorerButton[i].isSelected())
					armorer = i;
				if (archeryButton[i].isSelected())
					archery = i;
			}

			if (armorerSpecialtyLevelField.getText().isEmpty())
				armorerSpecialtyLevel = 0;
			else
				armorerSpecialtyLevel = Integer.valueOf(armorerSpecialtyLevelField.getText());

			if (offenseSpecialtyLevelField.getText().isEmpty())
				offenseSpecialtyLevel = 0;
			else
				offenseSpecialtyLevel = Integer.valueOf(offenseSpecialtyLevelField.getText());

			if (archerySpecialtyLevelField.getText().isEmpty())
				archerySpecialtyLevel = 0;
			else
				archerySpecialtyLevel = Integer.valueOf(archerySpecialtyLevelField.getText());

			String creatureNameAttacker = String.valueOf(attackerCreatureList.getSelectedItem());
			Creature creatureAttacker = UIUtilities.findCreatureFromList(creatureNameAttacker, allCreatures);
			String creatureNameDefender = String.valueOf(defenderCreatureList.getSelectedItem());
			Creature creatureDefender = UIUtilities.findCreatureFromList(creatureNameDefender, allCreatures);
			minDamage = creatureAttacker.minDamage;
			maxDamage = creatureAttacker.maxDamage;
			isRanged = creatureAttacker.isRanged;
			health = creatureDefender.health;

			if (heroAttackField.getText().isEmpty() == false)
				attack = Integer.valueOf(heroAttackField.getText()) + creatureAttacker.attack;
			else
				attack = creatureAttacker.attack;
			if (heroDefenceField.getText().isEmpty() == false)
				defence = Integer.valueOf(heroDefenceField.getText()) + creatureDefender.defense;
			else
				defence = creatureDefender.defense;

			isBehemoth = creatureNameAttacker.equals("Behemoth");
			isAncientBehemoth = creatureNameAttacker.equals("AncientBehemoth");
			isNix = creatureNameDefender.equals("Nix");
			isNixWarrior = creatureNameDefender.equals("NixWarrior");

			if (creatureCountField.getText().isEmpty() == false)
				creatureCount = Integer.valueOf(creatureCountField.getText());
			else
				creatureCount = 0;

			Calculator calculator = new Calculator(isBehemoth, isNix, isAncientBehemoth, isNixWarrior, attack, defence,
					health,
					armorer, offence, archery, offenseSpecialtyLevel, archerySpecialtyLevel, archerySpecialtyLevel,
					isRanged, minDamage,
					maxDamage, creatureCount);
			int[] totalDamage = calculator.calculate(melee.isSelected(), creatureAttacker, creatureDefender,
					creaturesNames);

			UIUtilities.damageDealthLabel(damageDealth, health, totalDamage[0], totalDamage[1], creatureCount);

			attackerCreatureList.removeItemListener(itemListener);
			UIUtilities.clearInputs(attackerCreatureList, attackerSearchField, attackerSearchString, creaturesNames);
			UIUtilities.clearInputs(defenderCreatureList, defenderSearchField, defenderSearchString, creaturesNames);
			attackerCreatureList.addItemListener(itemListener);

		}

	}
}