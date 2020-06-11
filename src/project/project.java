package project;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Random;
import java.util.Scanner;



public class project { 
	Scanner myScanner = new Scanner(System.in);
	Scanner enterScanner = new Scanner(System.in); 
	int playerHP;  
	String playerName;
	String playerWeapon;
	int choice;
	int monsterHP;
	int monster2HP;
	Scanner input = new Scanner(System.in);
	String playerEquipment;
	int playerPower;
	int[] saveInformation = { playerHP, choice, monsterHP, monster2HP, playerPower }; //my attempts at making a save file

	int pHP = 0;
	int pC = 1;
	int mHP = 2;
	int mHP2 = 3;
	int pP = 4;
	
	public project() {
		readPlayer("Player Save.txt");
		updatePlayerStats();
		savePlayer("Player Save.txt");
		
	}

	public static void main(String[] args) {

		project3 Pirate;
		Pirate = new project3();

		Pirate.info();
		Pirate.rooms();
	}

	public void info() { 
		monsterHP = 75; //shows info for the starting stats
		monster2HP = 250;
		playerHP = 100;
		playerWeapon = "fists"; //currently you only have your fists
		playerEquipment = "nothing"; // no items 
		System.out.println("Your starting HP is:" + " " + playerHP);
		System.out.println("All you have to defend yourself is your:" + " " + playerWeapon);

		System.out.println("\nARRRRRGH State your name Landlubber!");

		playerName = myScanner.nextLine();

		System.out.println("Ahoy" + " " + playerName + " " + "YARR better be prepared to take on WhiteBeard!");

	}

	public void rooms() { //the point is to reach the last cabin and fight WhiteBeard and take over
		System.out.println("\n------------------------------------------------------------------\n");
		System.out.println(
				"Ye be currently too weak t' scuttle WhiteBeard, ye 'ave t' gather starb'rd equipment t' do so.");
		System.out.println("Ye 'ave cabins t' choose from afore yer 'n final cabin which contains WhiteBeard. ");
		System.out.println("Which cabin do ye wants t' go in first?");
		System.out.println("\n1: Cabin 1");
		System.out.println("2: Cabin 2");//gives user choice of first or 2nd cabin

		choice = myScanner.nextInt();

		if (choice == 1) {
			cabin1();
		}
		if (choice == 2) {
			cabin2();
		}
	}

	public void cabin1() { //if user enters the first cabin this goes
		String answer;
		System.out.println("\n------------------------------------------------------------------\n");
		System.out.println("cabin#1");
		System.out.println(
				"Ye see a cutlass on th' ground but thar be a snake on top o' it, do ye wants t' try 'n grab it?");
		System.out.println("Please enter: yes or no");
		System.out.println("if Yes, You risk getting bit by the snake");
		System.out.println("if no, you'll continue without a weapon");
		answer = input.nextLine();

		if (answer.equals("yes")) { 
			playerHP = playerHP - 25;
			System.out.println("\n------------------------------------------------------------------\n");
			System.out
					.println("ye got bit by th' snake 'n loot 25 damage, although ye now 'ave a nice cutlass t' use!");
			playerWeapon = "cutlass";
			System.out.println("\n------------------------------------------------------------------\n");
			playerPower = 20;
			rooms2();
		} else {
			rooms2();
		}

	}

	public void cabin2() { //if user enters 2nd cabin they find a pair of gloves which will help them in the first cabin
		String answer;
		System.out.println("\n------------------------------------------------------------------\n");
		System.out.println("cabin#2");
		System.out.println(
				"\nYe 'ave found leather gloves, although they do nah offer physical strength, they will offer protection o' some sorts!");
		playerEquipment = "gloves";
		System.out.println("do ye wants t' go back tot he first cabin?");
		answer = input.nextLine();
		if (answer.equals("yes")) {
			cabinA();
		} else {
			rooms2();
		}

	}

	public void cabinA() { 
		System.out.println("\n------------------------------------------------------------------\n");
		System.out.println("Th' snake bites ye but th' fangs can nah go through th' gloves, ye obtained th' cutlass!");
		playerWeapon = "cutlass";
		System.out.println("\n------------------------------------------------------------------\n");
		playerPower = 20;
		playerHP = 100;
		rooms2();
	}

	private void rooms2() { //the user has to decide which room they want to go into next
		System.out.println("\n------------------------------------------------------------------\n");
		System.out.println("Ye 'ave came t' a crossroads, which cabin would ye like t' loot?");
		System.out.println("3: Cabin 3");
		System.out.println("4: Cabin 4");
		System.out.println("5: Cabin 5");
		choice = myScanner.nextInt();

		if (choice == 3) {
			cabin3();
		}
		if (choice == 4) {
			cabin4();
		}
		if (choice == 5) {
			cabin5();
		}

	}

	public void cabin3() { //if user enters 3 they run into their first fight
		System.out.println("\n------------------------------------------------------------------\n");
		System.out
				.println("ye've encountered a crew member do ye wants t' attack 'im 'n plunder his gear or run away?");
		System.out.println("1: Fight!");
		System.out.println("2: Run!");
		System.out.println("\n------------------------------------------------------------------\n");
		choice = myScanner.nextInt();

		if (choice == 1) {
			Fight();
		} else if (choice == 2) {
			rooms2();
		}

	}

	public void cabin4() {//if user enters 4 they find food and heal 20 hp (stackable)
		System.out.println("\n------------------------------------------------------------------\n");
		System.out.println("Ye 'ave found a barrel o' fine rum 'n fruits");
		System.out.println("Your HP is recovered.");
		playerHP = playerHP + 20;
		System.out.println("Your HP: " + playerHP);
		System.out.println("\n\n1: To go back to the Crossroads");
		System.out.println("\n------------------------------------------------------------------\n");

		choice = myScanner.nextInt();

		if (choice == 1) {
			rooms2();
		} else {
			cabin4();
		}
	}

	public void cabin5() { //if users enter 5 they fall into a trap and lose 10 hp
		System.out.println("\n------------------------------------------------------------------\n");
		System.out.println("This cabin be filled wit' rats, 'n one o' them jus' bit ye!");
		playerHP = playerHP - 10;
		System.out.println("\nYou have lost 10HP");
		System.out.println("Your HP: " + playerHP);

		if (playerHP < 1) {
			dead();
		} else if (playerHP > 0) {
			rooms2();
		}

	}

	public void Fight() { //
		System.out.println("\n------------------------------------------------------------------\n");
		System.out.println("Your HP: " + playerHP);
		System.out.println("Enemy HP: " + monsterHP);
		System.out.println("\n1: Attack");
		System.out.println("2: Run");
		System.out.println("\n------------------------------------------------------------------\n");

		choice = myScanner.nextInt();

		if (choice == 1) {
			attack();
		} else if (choice == 2) {
			rooms2();
		} else {
			Fight();
		}

	}

	public void Fight1() { //White Beard fight 
		System.out.println("\n------------------------------------------------------------------\n");
		System.out.println("Your HP: " + playerHP);
		System.out.println("WhiteBeards HP: " + monster2HP);
		System.out.println("\n1: Attack");
		System.out.println("2: Run");
		System.out.println("\n------------------------------------------------------------------\n");

		choice = myScanner.nextInt();

		if (choice == 1) {
			attack1();
		} else if (choice == 2) {
			System.out.println("Thar be no runnin' now yellow-bellied cur, stay 'n fight!"); //useless trying to run away
			Fight1();
		} else {
			Fight1();
		}
	}

	public void attack() {
		int playerDamage = 0;//the RNG of player hitting

		if (playerWeapon.equals("cutlass")) {
			playerDamage = new java.util.Random().nextInt(20); //hitting up to a 20 if player has a cutlass
		} else if (playerWeapon.equals("fists")) {
			playerDamage = new java.util.Random().nextInt(5); //hitting up to a 5 if player only has their fists
		}

		System.out.println("You did " + playerDamage + " damage!");

		monsterHP = monsterHP - playerDamage;

		System.out.println("Enemy HP: " + monsterHP);

		if (monsterHP < 1) {
			win();
		} else if (monsterHP > 0) {
			int monsterDamage = 0;

			monsterDamage = new java.util.Random().nextInt(15);

			System.out.println("The enemy did " + monsterDamage + " damage!");

			playerHP = playerHP - monsterDamage;

			System.out.println("Player HP: " + playerHP);

			if (playerHP < 1) {
				dead();
			} else if (playerHP > 0) {
				Fight();
			}
		}

	}

	public void dead() { //if you die this is what you see
		System.out.println("\n------------------------------------------------------------------\n");
		System.out.println("ye be dead!");
		System.out.println("\n\nGAME OVER");
		System.out.println("\n------------------------------------------------------------------\n");

	}

	public void win() { //if you win the first battle you get a reward
		System.out.println("\n------------------------------------------------------------------\n");
		System.out.println("You killed the enemy!");
		System.out.println(
				"Ye 'ave scuttled th' enemy, ye 'ave obtained yourself a Scimitar that far outshines yer cutlass!");
		System.out.println("New weapon: Scimitar");
		rooms3();

	}

	public void rooms3() { //the last choice before the fight
		playerWeapon = "Scimitar";
		playerPower = 40;
		System.out.println("\n------------------------------------------------------------------\n");
		System.out.println(
				"Thar are two cabins t' choose from, one o' them be WhiteBeards 'n th' other one be filled wit' untold riches.");
		System.out.println("Do ye wants t' fight WhiteBeard or do ye wants t' bath in doubloons?");
		System.out.println("\n1: Atack WhiteBeard");
		System.out.println("2: Steal gold **It is probably a trap**");

		choice = myScanner.nextInt();

		if (choice == 1) {
			attack1();
		} else if (choice == 2) {
			dead2();
		}
	}

	public void attack1() {
		int playerDamage = 0;

		if (playerWeapon.equals("cutlass")) {
			playerDamage = new java.util.Random().nextInt(20); 
		} else if (playerWeapon.equals("Scimitar")) {
			playerDamage = new java.util.Random().nextInt(50);
		}

		System.out.println("You did " + playerDamage + " damage!");

		monster2HP = monster2HP - playerDamage;

		System.out.println("WhiteBeards HP: " + monster2HP);

		if (monster2HP < 1) {
			end();
		} else if (monster2HP > 0) {
			int monsterDamage = 0;

			monsterDamage = new java.util.Random().nextInt(15);

			System.out.println("WhiteBeard did " + monsterDamage + " damage!");

			playerHP = playerHP - monsterDamage;

			System.out.println("Player HP: " + playerHP);

			if (playerHP < 1) {
				dead();
			} else if (playerHP > 0) {
				Fight1();

			}
		}
	}

	public void end() { //if you beat WhiteBeard
		System.out.println("\n------------------------------------------------------------------\n");
		System.out.println(
				"\n\n\nYe 'ave scuttled WhiteBeard, ye be now th' leader o' th' crew 'n soon will control th' sea!");
		System.out.println("\n\n           THE END                    ");
		System.out.println("\n------------------------------------------------------------------\n");

	}

	public void dead2() { //if you decide to fall for the trap 
		System.out.println("\n------------------------------------------------------------------\n");
		System.out.println("As ye board th' door closes 'n locks behind ye. ");
		System.out.println("Thar be another greedy adventurer here named Carmine Belmonte. ");
		System.out.println(
				"Now th' two o' ye 'ave all th' doubloons in th' world, but are unable t' buy grub nor water wit' it.");
		System.out.println("Ye both Starve together.....");
		System.out.println("\n\nGAME OVER");
		System.out.println("\n------------------------------------------------------------------\n");
		System.exit(0);
	}
	
	private void updatePlayerStats() { //my attempts to create a save file are under this line
		System.out.println(playerHP);
		playerHP = saveInformation[pHP];
		choice = saveInformation[pC];
		monsterHP = saveInformation[mHP];
		monsterHP = saveInformation[mHP2];
		playerPower = saveInformation[pP];
		
		System.out.println(playerHP);
	}
	private void readPlayer(String filepath) {
		File inputFile;
		BufferedReader inputReader;
		
		try { 
			inputFile = new File(filepath);
			inputReader = new BufferedReader(new FileReader(inputFile));

			
			for(int i = 0; i < saveInformation.length; i++) {
				saveInformation[i] = Integer.parseInt(inputReader.readLine());
			}
			inputReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void savePlayer(String filepath) {
		File outputFile;
		BufferedWriter outputWriter;
		
		try {
			outputFile = new File(filepath);
			outputWriter = new BufferedWriter(new FileWriter(outputFile));
			
			
			for(int i = 0; i < saveInformation.length; i++) {
				outputWriter.write(Integer.toString(saveInformation[i]) + "\n");
			}
			
		
			outputWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}}
	

