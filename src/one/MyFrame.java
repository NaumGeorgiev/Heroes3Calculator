package one;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class MyFrame extends JFrame implements ActionListener {
	JRadioButton noOffense = SkillsCreator.createSkillRadioButton(this, "No");
	JRadioButton advancedOffense = SkillsCreator.createSkillRadioButton(this, "Advanced");
	JRadioButton basicOffense = SkillsCreator.createSkillRadioButton(this, "Basic");
	JRadioButton expertOffense = SkillsCreator.createSkillRadioButton(this, "Expert");

	JRadioButton noArmorer = SkillsCreator.createSkillRadioButton(this, "No");
	JRadioButton advancedArmorer = SkillsCreator.createSkillRadioButton(this, "Advanced");
	JRadioButton basicArmorer = SkillsCreator.createSkillRadioButton(this, "Basic");
	JRadioButton expertArmorer = SkillsCreator.createSkillRadioButton(this, "Expert");

	JRadioButton noArchery = SkillsCreator.createSkillRadioButton(this, "No");
	JRadioButton advancedArchery = SkillsCreator.createSkillRadioButton(this, "Advanced");
	JRadioButton basicArchery = SkillsCreator.createSkillRadioButton(this, "Basic");
	JRadioButton expertArchery = SkillsCreator.createSkillRadioButton(this, "Expert");

	JTextField specialtyLevelOffense;
	JTextField specialtyLevelArmorer;
	JTextField specialtyLevelArchery;

	JTextField heroAttack;
	JTextField heroDefence;

	Creature[] creatures = Creature.createCreatures();
	JComboBox creatureListAttacker;
	JComboBox creatureListDefender;

	JTextField numberOfAttackingCreatures;

	JButton button;

	JLabel damageDealth = new JLabel();

	MyFrame() {
		noArchery.setSelected(true);
		noOffense.setSelected(true);
		noArmorer.setSelected(true);
		ButtonGroup groupOffense = SkillsCreator.createGroup(noOffense, basicOffense, advancedOffense, expertOffense);
		ButtonGroup groupArmorer = SkillsCreator.createGroup(noArmorer, basicArmorer, advancedArmorer, expertArmorer);
		ButtonGroup groupArchery = SkillsCreator.createGroup(noArchery, basicArchery, advancedArchery, expertArchery);

		JPanel panelOffense = SkillsCreator.createSkillPanel("Offense:", noOffense, basicOffense, advancedOffense,
				expertOffense, 0, 0, 350, 30);
		JPanel panelArmorer = SkillsCreator.createSkillPanel("Armorer:", noArmorer, basicArmorer, advancedArmorer,
				expertArmorer, 0, 30, 350, 30);
		JPanel panelArchery = SkillsCreator.createSkillPanel("Archery:", noArchery, basicArchery, advancedArchery,
				expertArchery, 0, 60, 350, 30);

		specialtyLevelOffense = SkillsCreator.limitingJTextFieldsToNumbersAndSettingBounds(350, 0, 40, 30);
		specialtyLevelArmorer = SkillsCreator.limitingJTextFieldsToNumbersAndSettingBounds(350, 30, 40, 30);
		specialtyLevelArchery = SkillsCreator.limitingJTextFieldsToNumbersAndSettingBounds(350, 60, 40, 30);

		JLabel heroAttackLabel = new JLabel("Hero's attack skill");
		heroAttackLabel.setBounds(30, 120, 130, 30);
		JLabel heroDefenseLabel = new JLabel("Hero's defense skill");
		heroDefenseLabel.setBounds(320, 120, 130, 30);

		heroAttack = SkillsCreator.limitingJTextFieldsToNumbersAndSettingBounds(30, 150, 40, 30);
		heroDefence = SkillsCreator.limitingJTextFieldsToNumbersAndSettingBounds(320, 150, 40, 30);

		creatureListAttacker = new JComboBox(Creature.createCreatureNames());
		creatureListAttacker.setBounds(30, 200, 130, 30);
		creatureListAttacker.addActionListener(this);
		creatureListDefender = new JComboBox(Creature.createCreatureNames());
		creatureListDefender.setBounds(320, 200, 130, 30);

		numberOfAttackingCreatures = SkillsCreator.limitingJTextFieldsToNumbersAndSettingBounds(160, 200, 40, 30);

		button = SkillsCreator.createButton(this);

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(500, 500);
		this.setLayout(null);
		this.setLocation(500, 100);
		this.add(panelOffense);
		this.add(panelArmorer);
		this.add(panelArchery);
		this.add(specialtyLevelOffense);
		this.add(specialtyLevelArmorer);
		this.add(specialtyLevelArchery);
		this.add(heroAttackLabel);
		this.add(heroDefenseLabel);
		this.add(heroAttack);
		this.add(heroDefence);
		this.add(creatureListAttacker);
		this.add(creatureListDefender);
		this.add(numberOfAttackingCreatures);
		this.add(button);
		this.add(damageDealth);

//		this.getContentPane().setBackground(Color.black);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button) {
			if (noOffense.isSelected())
				Calculator.offence = 0;
			else if (basicOffense.isSelected())
				Calculator.offence = 1;
			else if (advancedOffense.isSelected())
				Calculator.offence = 2;
			else if (expertOffense.isSelected())
				Calculator.offence = 3;

			if (noArmorer.isSelected())
				Calculator.armorer = 0;
			else if (basicArmorer.isSelected())
				Calculator.armorer = 1;
			else if (advancedArmorer.isSelected())
				Calculator.armorer = 2;
			else if (expertArmorer.isSelected())
				Calculator.armorer = 3;

			if (noArchery.isSelected())
				Calculator.archery = 0;
			else if (basicArchery.isSelected())
				Calculator.archery = 1;
			else if (advancedArchery.isSelected())
				Calculator.archery = 2;
			else if (expertArchery.isSelected())
				Calculator.archery = 3;

			if (specialtyLevelArmorer.getText().isEmpty() == false)
				Calculator.amorerHeroLevel = Integer.valueOf(specialtyLevelArmorer.getText());
			if (specialtyLevelOffense.getText().isEmpty() == false)
				Calculator.offenseHeroLevel = Integer.valueOf(specialtyLevelOffense.getText());
			if (specialtyLevelArchery.getText().isEmpty() == false)
				Calculator.archeryHeroLevel = Integer.valueOf(specialtyLevelArchery.getText());

			String creatureAttack = String.valueOf(creatureListAttacker.getSelectedItem());
			String creatureDefense = String.valueOf(creatureListDefender.getSelectedItem());

			Creature attackingCreature = SkillsCreator.findCreatureFromComboBox(creatureAttack, creatures);
			Calculator.minDamage = attackingCreature.minDamage;
			Calculator.maxDamage = attackingCreature.maxDamage;
			Calculator.ranged = attackingCreature.ranged;

			Creature defendingCreature = SkillsCreator.findCreatureFromComboBox(creatureDefense, creatures);
			Calculator.health = defendingCreature.health;

			if (heroAttack.getText().isEmpty() == false)
				Calculator.attack = Integer.valueOf(heroAttack.getText()) + attackingCreature.attack;
			if (heroDefence.getText().isEmpty() == false)
				Calculator.defence = Integer.valueOf(heroDefence.getText()) + defendingCreature.defense;

			if (creatureAttack.equals("Behemoth"))
				Calculator.behemoth = true;
			if (creatureAttack.equals("AncientBehemoth"))
				Calculator.ancientBehemoth = true;
			if (creatureDefense.equals("Nix"))
				Calculator.nix = true;
			else if (creatureDefense.equals("NixWarrior"))
				Calculator.nixWarrior = true;

			Calculator.numberOfAttackingCreatures = Integer.valueOf(numberOfAttackingCreatures.getText());

			SkillsCreator.damageDealth(damageDealth, attackingCreature, defendingCreature);

			this.add(damageDealth);
		}

	}
	public static void main(String[] args) {
		new MyFrame();
	}

}
