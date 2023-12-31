package one;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Set;

public class Creature {

	public String name;
	public int attack;
	public int defense;
	public double minDamage;
	public double maxDamage;
	public int health;
	public boolean isRanged;
	public boolean hasMeleePenalty;
	public Set<String> hatedCreatureNames;
	public Set<String> oppositeElementalTraits;
	public boolean isMindSpellImmune;
	public boolean isDoubleShooting;
	public int shotCount;

	public Creature(String name, int attack, int defense, double minDamage, double maxDamage, int health,
			boolean isRanged, boolean hasMeleePenalty, Set<String> hatedCreatureNames, Set<String> oppositeElementalTraits,
			boolean isMindSpellImmune, boolean isDoubleShooting, int shotCount) { // Update
		// constructor
		this.name = name;
		this.attack = attack;
		this.defense = defense;
		this.minDamage = minDamage;
		this.maxDamage = maxDamage;
		this.health = health;
		this.isRanged = isRanged;
		this.hasMeleePenalty = hasMeleePenalty;
		this.hatedCreatureNames = hatedCreatureNames;
		this.oppositeElementalTraits = oppositeElementalTraits;
		this.isMindSpellImmune = isMindSpellImmune;
		this.isDoubleShooting=isDoubleShooting;
		this.shotCount=shotCount;
	}

	public static Creature[] createAll() {
		File csvFile = new File("H3Units.csv");
		Creature[] creatures = new Creature[156];
		try {
			BufferedReader reader = new BufferedReader(new FileReader(csvFile));
			String creatureInfo = reader.readLine();
			int i = 0;
			while (true) {
				creatureInfo = reader.readLine();
				if (creatureInfo == null) {
					reader.close();
					break;
				}
				int shotCount=0;
				boolean isRanged = false;
				boolean isDoubleShooting = false;
				boolean hasMeleePenalty = false;
				String[] stats = creatureInfo.split(",");
				Set<String> hatedCreatureNames = hatedCreatureNames(stats[0]);
				Set<String> oppositeElemental = oppositeElemental(stats[0]);
				if (stats[13].contains("Ranged")) {
					shotCount=shotCount(stats[13]);
					isRanged = true;
					if (creatureInfo.contains("Nomeleepenalty") == false)
						hasMeleePenalty = true;
					if (creatureInfo.contains("Doubleattack"))
						isDoubleShooting = true;
				}
				boolean isMindSpellImmune = (creatureInfo.contains("Undead") || creatureInfo.contains("Elemental")
						|| creatureInfo.contains("Unliving") || creatureInfo.contains("Mind")
						|| creatureInfo.contains("Resistallspells"));
				creatures[i] = new Creature(stats[0], Integer.valueOf(stats[3]), Integer.valueOf(stats[4]),
						Integer.valueOf(stats[5]), Integer.valueOf(stats[6]), Integer.valueOf(stats[7]), isRanged,
						hasMeleePenalty, hatedCreatureNames, oppositeElemental, isMindSpellImmune, isDoubleShooting, shotCount);
				i++;
			}
			creatures[i] = new Creature("Nymph", 5, 2, 1, 2, 4, false, false, null, null, false, false, 0);
			creatures[i + 1] = new Creature("Oceanid", 6, 2, 1, 3, 4, false, false, null, null, false, false, 0);
			creatures[i + 2] = new Creature("CrewMate", 7, 4, 2, 4, 15, false, false, null, null, false, false, 0);
			creatures[i + 3] = new Creature("Seaman", 8, 6, 3, 4, 15, false, false, null, null, false, false, 0);
			creatures[i + 4] = new Creature("Pirate", 8, 6, 3, 7, 15, true, false, null, null, false, false, 4);
			creatures[i + 5] = new Creature("Corasair", 10, 8, 3, 7, 15, true, false, null, null, false, false, 4);
			creatures[i + 6] = new Creature("SeaDog", 12, 11, 3, 7, 15, true, false, null, null, false, false, 12);
			creatures[i + 7] = new Creature("Stormbird", 10, 8, 6, 9, 30, false, false, null, null, false, false, 0);
			creatures[i + 8] = new Creature("Ayssid", 11, 8, 6, 10, 30, false, false, null, null, false, false, 0);
			creatures[i + 9] = new Creature("SeaWitch", 12, 7, 10, 14, 35, true, true, null, null, false, false, 0);
			creatures[i + 10] = new Creature("Sorceress", 12, 9, 10, 16, 35, true, true, null, null, false, false, 0);
			creatures[i + 11] = new Creature("Nix", 13, 16, 18, 22, 80, false, false, null, null, false, false, 0);
			creatures[i + 12] = new Creature("NixWarrior", 14, 17, 18, 22, 90, false, false, null, null, false, false, 0);
			creatures[i + 13] = new Creature("SeaSerpent", 22, 16, 30, 55, 180, false, false, null, null, false, false, 0);
			creatures[i + 14] = new Creature("Haspid", 29, 20, 30, 55, 300, false, false, null, null, false, false, 0);
			sortByNames(creatures);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return creatures;
	}

	private static Creature[] sortByNames(Creature[] creature) {
		for (int i = 0; i < 156; i++) {
			for (int j = 0; j < 156 - i - 1; j++) {
				for (int n = 0; n < creature[j].name.length(); n++) {
					if (creature[j].name.length() == n || creature[j + 1].name.charAt(n) < creature[j].name.charAt(n)) {
						Creature temp = creature[j];
						creature[j] = creature[j + 1];
						creature[j + 1] = temp;
						break;
					}
					if (creature[j + 1].name.charAt(n) == creature[j].name.charAt(n)) {
						continue;
					}
					break;
				}
			}
		}
		return creature;
	}

	private static Set<String> hatedCreatureNames(String name) {
		Set<String> toBeReturned = new HashSet<>();
		switch (name) {
			case "Angel":
			case "ArchAngel":
				toBeReturned.add("Devil");
				toBeReturned.add("ArchDevil");
				break;
			case "Devil":
			case "ArchDevil":
				toBeReturned.add("Angel");
				toBeReturned.add("ArchAngel");
				break;
			case "Genie":
			case "MasterGenie":
				toBeReturned.add("Efreeti");
				toBeReturned.add("EfreetSultan");
				break;
			case "Efreeti":
			case "EfreetSultan":
				toBeReturned.add("Genie");
				toBeReturned.add("MasterGenie");
				break;
			case "BlackDragon":
				toBeReturned.add("Titan");
				break;
			case "Titan":
				toBeReturned.add("BlackDragon");
				break;
		}
		return toBeReturned;
	}

	private static Set<String> oppositeElemental(String name) {
		Set<String> toBeReturned = new HashSet<>();
		switch (name) {
			case "AirElemental":
			case "StormElemental":
				toBeReturned.add("EarthElemental");
				toBeReturned.add("MagmaElemental");
				break;
			case "EarthElemental":
			case "MagmaElemental":
				toBeReturned.add("AirElemental");
				toBeReturned.add("StormElemental");
				break;
			case "WaterElemental":
			case "IceElemental":
				toBeReturned.add("FireElemental");
				toBeReturned.add("EnergyElemental");
				break;
			case "FireElemental":
			case "EnergyElemental":
				toBeReturned.add("WaterElemental");
				toBeReturned.add("IceElemental");
				break;
		}
		return toBeReturned;
	}
	public static int shotCount(String source){
		String[] parts=source.split("\\(");
		String[] toBeReturned=parts[1].split("s");
		return Integer.parseInt(toBeReturned[0]);
	}

	public boolean hates(Creature defender) {
		if (this.hatedCreatureNames != null) {
			if (this.hatedCreatureNames.contains(defender.name))
				return true;
		}
		return false;
	}

	public boolean isOppositeElemental(Creature defender) {
		if (this.oppositeElementalTraits != null) {
			if (this.oppositeElementalTraits.contains(defender.name))
				return true;
		}
		return false;
	}

	public static String[] createNames() {
		Creature[] creatures = createAll();
		String[] creaturesNames = new String[156];
		for (int i = 0; i < 156; i++) {
			creaturesNames[i] = creatures[i].name;
		}
		return creaturesNames;
	}

	public String toString() {
		return (this.name + " " + this.attack + " " + this.defense + " " + this.minDamage + " " + this.maxDamage + " "
				+ this.health + " " + this.isRanged);
	}

}