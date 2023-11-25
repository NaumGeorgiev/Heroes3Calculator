package one;

import javax.swing.JToggleButton;

public class Calculator {
	public boolean behemoth = false;
	public boolean nix = false;
	public boolean ancientBehemoth = false;
	public boolean nixWarrior = false;
	public int attack;
	public int defence;
	public int health;
	public int armorer;
	public int offence;
	public int archery;
	public int offenseHeroLevel;
	public int archeryHeroLevel;
	public int armorerHeroLevel;
	public boolean isRanged;
	public double minDamage;
	public double maxDamage;
	public int creaturesNumber;

	public Calculator(boolean behemoth, boolean nix, boolean ancientBehemoth, boolean nixWarrior, int attack,
			int defence, int health, int armorer, int offence, int archery, int offenseHeroLevel, int archeryHeroLevel,
			int armorerHeroLevel, boolean isRanged, double minDamage, double maxDamage,
			int creaturesNumber) {
		this.behemoth = behemoth;
		this.nix = nix;
		this.ancientBehemoth = ancientBehemoth;
		this.nixWarrior = nixWarrior;
		this.attack = attack;
		this.defence = defence;
		this.health = health;
		this.armorer = armorer;
		this.offence = offence;
		this.archery = archery;
		this.offenseHeroLevel = offenseHeroLevel;
		this.archeryHeroLevel = archeryHeroLevel;
		this.armorerHeroLevel = armorerHeroLevel;
		this.isRanged = isRanged;
		this.minDamage = minDamage;
		this.maxDamage = maxDamage;
		this.creaturesNumber = creaturesNumber;
	}

	public int[] fianlCalculate() {

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
			offenceBonus += offenseHeroLevel * (offenceBonus - 1) * 0.05;
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
			archeryBonus += archeryHeroLevel * (archeryBonus - 1) * 0.05;
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
		armorerBonus -= armorerHeroLevel * (1 - armorerBonus) * 0.05;
		minDamage = (minDamage * attackDefenceDifferenceBonus * offenceBonus * archeryBonus * armorerBonus
				* creaturesNumber);
		maxDamage = (maxDamage * attackDefenceDifferenceBonus * offenceBonus * archeryBonus * armorerBonus
				* creaturesNumber);
		return new int[] { (int) minDamage, (int) maxDamage };
	}

	public void calculateMelee(Creature attacker) {
		this.isRanged = false;
		if (attacker.hasMeleePenalty) {
			this.minDamage /= 2;
			this.maxDamage /= 2;
		}
	}

	public void calculateHatred() {
		this.minDamage = this.minDamage * 3 / 2;
		this.maxDamage = this.maxDamage * 3 / 2;
	}

	public void calculateOppositeElements() {
		this.minDamage = this.minDamage * 2;
		this.maxDamage = this.maxDamage * 2;
	}

	public void bullshitElemental(Creature defender) {
		switch (defender.name) {
			case "Sekeleton":
			case "SekeletonWarrior":
			case "WalkingDead":
			case "Zombie":
			case "Wight":
			case "Wraith":
			case "Vampire":
			case "VampireLord":
			case "Lich":
			case "PowerLich":
			case "BlackKnight":
			case "DreadKnight":
			case "BoneDragon":
			case "GhostDragon":
			case "BlackDragon":
			case "Giant":
			case "Titan":
				this.minDamage /= 2;
				this.maxDamage /= 2;
				break;
		}
	}

	public int[] calculate(boolean isMelee, Creature attacker, Creature defender) {
		if (isMelee)
			calculateMelee(attacker);
		else if (attacker.hates(defender))
			calculateHatred();
		else if (attacker.isOppositeElemental(defender))
			calculateOppositeElements();
		else if (attacker.name.equals("PsychicElemental") || attacker.name.equals("MagicElemental"))
			bullshitElemental(defender);
			return this.fianlCalculate();
	}
}