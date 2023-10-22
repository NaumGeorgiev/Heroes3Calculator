package one;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Creature {
	int bruh;
	public String name;
	public int attack;	
	public int defense;
	public int minDamage;
	public int maxDamage;
	public int health;
	public boolean ranged;
	
	private Creature(String name, int attack, int defense, int minDamage, int maxDamage, int health, boolean ranged) {
		this.name=name;
		this.attack=attack;
		this.defense=defense;
		this.minDamage=minDamage;
		this.maxDamage=maxDamage;
		this.health=health;
		this.ranged=ranged;
			
	}
	
	public static Creature[] createCreatures() {
	File csvFile=new File("H3Units.csv");
		Creature[] creature=new Creature[156];
		try {
			BufferedReader reader = new BufferedReader(new FileReader(csvFile));
			String creatureInfo=reader.readLine();
			int i=0;
			while(true) {
				creatureInfo=reader.readLine();
				if(creatureInfo==null)
					break;
				boolean ranged=false;
				String[] stats=creatureInfo.split(",");
				if(stats[13].contains("Ranged"))
					ranged=true;
				creature[i]=new Creature(stats[0], Integer.valueOf(stats[3]), Integer.valueOf(stats[4]), Integer.valueOf(stats[5]), Integer.valueOf(stats[6]), Integer.valueOf(stats[7]), ranged);
				i++;
			}
			creature[i]=new Creature("Nymph", 5, 2, 1, 2, 4, false);
			creature[i+1]=new Creature("Oceanid", 6, 2, 1, 3, 4, false);
			creature[i+2]=new Creature("CrewMate", 7, 4, 2, 4, 15, false);
			creature[i+3]=new Creature("Seaman", 8, 6, 3, 4, 15, false);
			creature[i+4]=new Creature("Pirate", 8, 6, 3, 7, 15, true);
			creature[i+5]=new Creature("Corasair", 10, 8, 3, 7, 15, true);
			creature[i+6]=new Creature("SeaDog", 12, 11, 3, 7, 15, true);
			creature[i+7]=new Creature("Stormbird", 10, 8, 6, 9, 30, false);
			creature[i+8]=new Creature("Ayssid", 11, 8, 6, 10, 30, false);
			creature[i+9]=new Creature("SeaWitch", 12, 7, 10, 14, 35, true);
			creature[i+10]=new Creature("Sorceress", 12, 9, 10, 16, 35, true);
			creature[i+11]=new Creature("Nix", 13, 16, 18, 22, 80, false);
			creature[i+12]=new Creature("NixWarrior", 14, 17, 18, 22, 90, false);
			creature[i+13]=new Creature("SeaSerpent", 22, 16, 30, 55, 180, false);
			creature[i+14]=new Creature("Haspid", 29, 20, 30, 55, 300, false);
			sortByNames(creature);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return creature;
	}
	
	private static Creature[] sortByNames(Creature[] creature) {
		for(int i=0; i<156; i++) {
			for(int j=0 ;j<156-i-1; j++) {
			for(int n=0; n<creature[j].name.length(); n++) {
				if(creature[j].name.length()==n || creature[j+1].name.charAt(n)<creature[j].name.charAt(n)) {
					Creature temp=creature[j];
					creature[j]=creature[j+1];
					creature[j+1]=temp;
					break;
				}
				if(creature[j+1].name.charAt(n)==creature[j].name.charAt(n)) {
					continue;
				}
				break;
			}
		}
	}
		return creature;
	}
	
	public static String[] createCreatureNames() {
		Creature[] creatures=createCreatures();
		String[] creatureNames=new String[156];
		for(int i=0; i<156; i++) {
			creatureNames[i]=creatures[i].name;
		}
		return creatureNames;
	}
	
	public String toString() {
		return (this.name+" "+this.attack+" "+this.defense+" "+this.minDamage+" "+this.maxDamage+" "+this.health+" "+this.ranged);
	}
	
	
}
