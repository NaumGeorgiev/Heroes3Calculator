package one;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class MyFrame extends JFrame implements ActionListener {
	JRadioButton noOffense = ObjectCreator.secondarySkillRadioButton(this, "No");
	JRadioButton advancedOffense = ObjectCreator.secondarySkillRadioButton(this, "Advanced");
	JRadioButton basicOffense = ObjectCreator.secondarySkillRadioButton(this, "Basic");
	JRadioButton expertOffense = ObjectCreator.secondarySkillRadioButton(this, "Expert");

	JRadioButton noArmorer = ObjectCreator.secondarySkillRadioButton(this, "No");
	JRadioButton advancedArmorer = ObjectCreator.secondarySkillRadioButton(this, "Advanced");
	JRadioButton basicArmorer = ObjectCreator.secondarySkillRadioButton(this, "Basic");
	JRadioButton expertArmorer = ObjectCreator.secondarySkillRadioButton(this, "Expert");

	JRadioButton noArchery = ObjectCreator.secondarySkillRadioButton(this, "No");
	JRadioButton advancedArchery = ObjectCreator.secondarySkillRadioButton(this, "Advanced");
	JRadioButton basicArchery = ObjectCreator.secondarySkillRadioButton(this, "Basic");
	JRadioButton expertArchery = ObjectCreator.secondarySkillRadioButton(this, "Expert");

	JTextField specialtyLevelOffense;
	JTextField specialtyLevelArmorer;
	JTextField specialtyLevelArchery;

	JTextField heroAttackTextField;
	JTextField heroDefenceTextField;

	Creature[] creatures = Creature.createCreatures();
	JComboBox creatureListAttacker;
	JComboBox creatureListDefender;
	int letterNumber=0;

	JTextField numberOfAttackingCreatures;

	JButton button;

	JLabel damageDealth = new JLabel();
	
	

	MyFrame() {
		noArchery.setSelected(true);
		noOffense.setSelected(true);
		noArmorer.setSelected(true);
		
		ButtonGroup groupOffense = ObjectCreator.secondarySkillGroup(noOffense, basicOffense, advancedOffense, expertOffense);
		ButtonGroup groupArmorer = ObjectCreator.secondarySkillGroup(noArmorer, basicArmorer, advancedArmorer, expertArmorer);
		ButtonGroup groupArchery = ObjectCreator.secondarySkillGroup(noArchery, basicArchery, advancedArchery, expertArchery);
		
		JPanel panelOffense = ObjectCreator.secondarySkillPanel("Offense:", noOffense, basicOffense, advancedOffense,
				expertOffense, 0, 0, 350, 30);
		JPanel panelArmorer = ObjectCreator.secondarySkillPanel("Armorer:", noArmorer, basicArmorer, advancedArmorer,
				expertArmorer, 0, 30, 350, 30);
		JPanel panelArchery = ObjectCreator.secondarySkillPanel("Archery:", noArchery, basicArchery, advancedArchery,
				expertArchery, 0, 60, 350, 30);
		
		specialtyLevelOffense = ObjectCreator.limitingJTextFieldsToNumbersAndSettingBounds(350, 0, 40, 30);
		specialtyLevelArmorer = ObjectCreator.limitingJTextFieldsToNumbersAndSettingBounds(350, 30, 40, 30);
		specialtyLevelArchery = ObjectCreator.limitingJTextFieldsToNumbersAndSettingBounds(350, 60, 40, 30);

		
		
		
		JLabel heroAttackLabel = new JLabel("Hero's attack skill");
		heroAttackLabel.setBounds(30, 120, 130, 30);
		JLabel heroDefenseLabel = new JLabel("Hero's defense skill");
		heroDefenseLabel.setBounds(320, 120, 130, 30);
		
		heroAttackTextField = ObjectCreator.limitingJTextFieldsToNumbersAndSettingBounds(30, 150, 40, 30);
		heroDefenceTextField = ObjectCreator.limitingJTextFieldsToNumbersAndSettingBounds(320, 150, 40, 30);

		creatureListAttacker = ObjectCreator.creatureList(30, 200, 130, 30, this);
		creatureListDefender = ObjectCreator.creatureList(320, 200, 130, 30, this);
		creatureListAttacker.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				Character input=e.getKeyChar();
				if(letterNumber==0)
					input=Character.toUpperCase(input);
				creatureListAttacker=ObjectCreator.removingCreaturesFromList(creatureListAttacker, input, letterNumber);
				letterNumber++;
			}
		});
		numberOfAttackingCreatures = ObjectCreator.limitingJTextFieldsToNumbersAndSettingBounds(160, 200, 40, 30);

		
		button = ObjectCreator.button(this);
		
		
		

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
		this.add(heroAttackTextField);
		this.add(heroDefenceTextField);
		this.add(creatureListAttacker);
		this.add(creatureListDefender);
		this.add(numberOfAttackingCreatures);
		this.add(button);
		this.add(damageDealth);
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

			Creature attackingCreature = ObjectCreator.findCreatureFromCreatureList(creatureAttack, creatures);
			Calculator.minDamage = attackingCreature.minDamage;
			Calculator.maxDamage = attackingCreature.maxDamage;
			Calculator.ranged = attackingCreature.ranged;

			Creature defendingCreature = ObjectCreator.findCreatureFromCreatureList(creatureDefense, creatures);
			Calculator.health = defendingCreature.health;

			if (heroAttackTextField.getText().isEmpty() == false)
				Calculator.attack = Integer.valueOf(heroAttackTextField.getText()) + attackingCreature.attack;
			if (heroDefenceTextField.getText().isEmpty() == false)
				Calculator.defence = Integer.valueOf(heroDefenceTextField.getText()) + defendingCreature.defense;

			if (creatureAttack.equals("Behemoth"))
				Calculator.behemoth = true;
			if (creatureAttack.equals("AncientBehemoth"))
				Calculator.ancientBehemoth = true;
			if (creatureDefense.equals("Nix"))
				Calculator.nix = true;
			else if (creatureDefense.equals("NixWarrior"))
				Calculator.nixWarrior = true;

			if (numberOfAttackingCreatures.getText().isEmpty())
				Calculator.numberOfAttackingCreatures = 0;
			else
				Calculator.numberOfAttackingCreatures = Integer.valueOf(numberOfAttackingCreatures.getText());				

			ObjectCreator.damageDealthLabel(damageDealth, attackingCreature, defendingCreature);

			this.add(damageDealth);
		}

	}
	
	public static void main(String[] args) {
		new MyFrame();
	}
}
