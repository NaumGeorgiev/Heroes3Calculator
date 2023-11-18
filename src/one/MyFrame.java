package one;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class MyFrame extends JFrame implements ActionListener {
	public JRadioButton noOffense;
	JRadioButton advancedOffense;
	JRadioButton basicOffense;
	JRadioButton expertOffense;
	JRadioButton noArmorer;
	JRadioButton advancedArmorer;
	JRadioButton basicArmorer;
	JRadioButton expertArmorer;
	JRadioButton noArchery;
	JRadioButton advancedArchery;
	JRadioButton basicArchery;
	JRadioButton expertArchery ;

	JTextField offenseSpecialtyLevel;
	JTextField armorerSpecialtyLevel;
	JTextField archerySpecialtyLevel;

	JTextField heroAttackTextField;
	JTextField heroDefenceTextField;

	Creature[] creatures = Creature.createAll();
	JComboBox<String> creatureListAttacker;
	JComboBox<String> creatureListDefender;

	JTextField creaturesNumber;
	String inputString=new String();

	JTextField creatureSearchAttacker;
	JButton submit;

	JLabel damageDealth = new JLabel();

	MyFrame() {
		noOffense = ObjectCreator.secondarySkillRadioButton(this, "No");
		basicOffense = ObjectCreator.secondarySkillRadioButton(this, "Basic");
		advancedOffense = ObjectCreator.secondarySkillRadioButton(this, "Advanced");
		expertOffense = ObjectCreator.secondarySkillRadioButton(this, "Expert");
		noArmorer = ObjectCreator.secondarySkillRadioButton(this, "No");
		basicArmorer = ObjectCreator.secondarySkillRadioButton(this, "Basic");
		advancedArmorer = ObjectCreator.secondarySkillRadioButton(this, "Advanced");
		expertArmorer = ObjectCreator.secondarySkillRadioButton(this, "Expert");
		noArchery = ObjectCreator.secondarySkillRadioButton(this, "No");
		basicArchery = ObjectCreator.secondarySkillRadioButton(this, "Basic");
		advancedArchery = ObjectCreator.secondarySkillRadioButton(this, "Advanced");
		expertArchery = ObjectCreator.secondarySkillRadioButton(this, "Expert");
		noArchery.setSelected(true);
		noOffense.setSelected(true);
		noArmorer.setSelected(true);
		ObjectCreator.secondarySkillGroups(noOffense, basicOffense, advancedOffense, expertOffense, noArmorer, basicArmorer,
				advancedArmorer, expertArmorer, noArchery, basicArchery, advancedArchery, expertArchery);

		JPanel offensePanel = ObjectCreator.secondarySkillPanel("Offense:", noOffense, basicOffense, advancedOffense,
				expertOffense, 0, 0, 350, 30);
		JPanel armorerPanel = ObjectCreator.secondarySkillPanel("Armorer:", noArmorer, basicArmorer, advancedArmorer,
				expertArmorer, 0, 30, 350, 30);
		JPanel archeryPanel = ObjectCreator.secondarySkillPanel("Archery:", noArchery, basicArchery, advancedArchery,
				expertArchery, 0, 60, 350, 30);

		offenseSpecialtyLevel = ObjectCreator.limitingJTextFieldsToNumbersAndSettingBounds(350, 0, 40, 30);
		armorerSpecialtyLevel = ObjectCreator.limitingJTextFieldsToNumbersAndSettingBounds(350, 30, 40, 30);
		archerySpecialtyLevel = ObjectCreator.limitingJTextFieldsToNumbersAndSettingBounds(350, 60, 40, 30);

		JLabel heroAttackLabel = new JLabel("Hero's attack skill");
		heroAttackLabel.setBounds(30, 120, 130, 30);
		JLabel heroDefenseLabel = new JLabel("Hero's defense skill");
		heroDefenseLabel.setBounds(320, 120, 130, 30);

		heroAttackTextField = ObjectCreator.limitingJTextFieldsToNumbersAndSettingBounds(30, 150, 40, 30);
		heroDefenceTextField = ObjectCreator.limitingJTextFieldsToNumbersAndSettingBounds(320, 150, 40, 30);

		creaturesNumber = ObjectCreator.limitingJTextFieldsToNumbersAndSettingBounds(140, 30, 40, 30);

		creatureSearchAttacker=new JTextField();
		creatureSearchAttacker.setBounds(0, 0, 140, 30);


		creatureListAttacker = ObjectCreator.creatureList(0, 30, 140, 30, this);
		creatureListDefender = ObjectCreator.creatureList(320, 200, 130, 30, this);
		JPanel attackerPanel = ObjectCreator.attackerPanel(creatureListAttacker, creaturesNumber, creatureSearchAttacker);
	// defenderPanel

		// creatureListAttacker.addKeyListener(new KeyAdapter() {
		// 	public void keyTyped(KeyEvent e) {
		// 		Character input=e.getKeyChar();
		// 		ObjectCreator.creatureSearchAttackerSettingText(creatureSearchAttacker, input, inputString);
		// 	}
		// });

		submit = ObjectCreator.button(this);

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
		this.add(heroAttackLabel);
		this.add(heroDefenseLabel);
		this.add(heroAttackTextField);
		this.add(heroDefenceTextField);
		this.add(attackerPanel);
		this.add(creatureListDefender);
		this.add(submit);
		this.add(damageDealth);
		this.setVisible(true);
		this.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == submit) {
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

			if (armorerSpecialtyLevel.getText().isEmpty() == false)
				Calculator.amorerHeroLevel = Integer.valueOf(armorerSpecialtyLevel.getText());
			if (offenseSpecialtyLevel.getText().isEmpty() == false)
				Calculator.offenseHeroLevel = Integer.valueOf(offenseSpecialtyLevel.getText());
			if (archerySpecialtyLevel.getText().isEmpty() == false)
				Calculator.archeryHeroLevel = Integer.valueOf(archerySpecialtyLevel.getText());

			String creatureNameAttacker = String.valueOf(creatureListAttacker.getSelectedItem());
			Creature creatureAttacker = ObjectCreator.findCreatureFromList(creatureNameAttacker, creatures);
			Calculator.minDamage = creatureAttacker.minDamage;
			Calculator.maxDamage = creatureAttacker.maxDamage;
			Calculator.ranged = creatureAttacker.ranged;

			String creatureDefense = String.valueOf(creatureListDefender.getSelectedItem());
			Creature creatureDefender = ObjectCreator.findCreatureFromList(creatureDefense, creatures);
			Calculator.health = creatureDefender.health;

			if (heroAttackTextField.getText().isEmpty() == false)
				Calculator.attack = Integer.valueOf(heroAttackTextField.getText()) + creatureAttacker.attack;
			if (heroDefenceTextField.getText().isEmpty() == false)
				Calculator.defence = Integer.valueOf(heroDefenceTextField.getText()) + creatureDefender.defense;

			if (creatureNameAttacker.equals("Behemoth"))
				Calculator.behemoth = true;
			if (creatureNameAttacker.equals("AncientBehemoth"))
				Calculator.ancientBehemoth = true;
			if (creatureDefense.equals("Nix"))
				Calculator.nix = true;
			else if (creatureDefense.equals("NixWarrior"))
				Calculator.nixWarrior = true;

			if (creaturesNumber.getText().isEmpty()==false)
				Calculator.creaturesNumber = Integer.valueOf(creaturesNumber.getText());
			
			ObjectCreator.damageDealthLabel(damageDealth, creatureAttacker, creatureDefender);

			this.add(damageDealth);
		}

	}

	public static void main(String[] args) {
		new MyFrame();
	}
}
