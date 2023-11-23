package one;

public class Calculator {
	public static boolean behemoth = false;
	public static boolean nix = false;
	public static boolean ancientBehemoth = false;
	public static boolean nixWarrior = false;
	public static int attack;
	public static int defence;
	public static int health;
	public static int armorer;
	public static int offence;
	public static int archery;
	public static int offenseHeroLevel;
	public static int archeryHeroLevel;
	public static int amorerHeroLevel;
	public static boolean isRanged;
	public static double minDamage;
	public static double maxDamage;
	public static int creaturesNumber=0;
	

	public static int[] calculate() {

		if (behemoth == true) {
			if ((defence * 3) % 5 > 0)
				defence = defence * 3 / 5;
			else
				defence = defence * 3 / 5 - 1;
		} else if (ancientBehemoth == true) {
			if (defence % 5 > 0)
				defence = defence / 5;
			else
				defence = defence / 5 - 1;
		}
		if (nix == true)
			attack = attack * 7 / 10 + 2;
		else if (nixWarrior == true)
			attack = attack * 4 / 10 + 2;

		double attackDefenceDifferenceBonus = 1;
		if (attack >= defence) {
			if (attack - defence >= 60)
				attackDefenceDifferenceBonus += 3;
			else
				attackDefenceDifferenceBonus += (attack - defence) * 0.05;
		} else {
			if (defence - attack >= 28)
				attackDefenceDifferenceBonus -= 0.7;
			else
				attackDefenceDifferenceBonus -= (defence - attack) * 0.025;
		}

		double offenceBonus = 1;
		double archeryBonus = 1;
		if (isRanged == false) {
			switch (offence) {
			case 0:
				break;
			case 1:
				offenceBonus = 1.1;
				break;
			case 2:
				offenceBonus = 1.2;
				break;
			case 3:
				offenceBonus = 1.3;
			}
			offenceBonus += offenseHeroLevel * (offenceBonus-1) * 0.05;
		} else {
			switch (archery) {
			case 0:
				break;
			case 1:
				archeryBonus = 1.1;
				break;
			case 2:
				archeryBonus = 1.25;
				break;
			case 3:
				archeryBonus = 1.5;
			}
			archeryBonus += archeryHeroLevel * (archeryBonus-1) * 0.05;
		}

		double armorerBonus = 1;
		switch (armorer) {
		case 0:
			break;
		case 1:
			armorerBonus = 0.95;
			break;
		case 2:
			armorerBonus = 0.9;
			break;
		case 3:
			armorerBonus = 0.85;
		}
		armorerBonus -= amorerHeroLevel * (1-armorerBonus) * 0.05;
		minDamage = (minDamage * attackDefenceDifferenceBonus * offenceBonus * archeryBonus * armorerBonus*creaturesNumber);
		maxDamage = (maxDamage * attackDefenceDifferenceBonus * offenceBonus * archeryBonus * armorerBonus*creaturesNumber);
		return new int[] {(int) minDamage, (int) maxDamage };
	}
	// public static int[] calculate(Creature attack, Creature defend){
		

	// }
	public static int[] calculateThroughMeleePenalty(Creature creature){
		Calculator.isRanged=false;
		int[] damage=calculate();
		damage[0]/=2;
		damage[1]/=2;
		return damage;
	}
}