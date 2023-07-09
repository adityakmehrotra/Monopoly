// Monopoly
// Created By: Aditya Mehrotra

import java.util.ArrayList;

import java.util.Scanner;
import java.util.Random;

import org.json.simple.parser.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.json.JSONException;

public class Game {
	private static String[] board;

	public Game() throws FileNotFoundException, JSONException, IOException, ParseException {

	}

	public static void main(String[] args)
			throws JSONException, FileNotFoundException, IOException, ParseException, java.text.ParseException {
		Board mainBoard = new Board();
		ArrayList<Player> playerList = new ArrayList<Player>();

		board = mainBoard.getBoard();

		Player playerOne = new Player(board);
		Player playerTwo = new Player(board);

		playerList.add(playerOne);
		playerList.add(playerTwo);

		startGame(playerList);
	}

	/**
	 * Starts the game. Generates a stack of Chance Cards and Community Chest Cards.
	 * Asks the user if they want to play, and if so, how many players are playing.
	 * Generates players for each player that the user wants to play. Rotates the
	 * players that are playing.
	 * 
	 * @param playerList ArrayList of Players that are playing
	 * @throws FileNotFoundException
	 * @throws JSONException
	 * @throws IOException
	 * @throws ParseException
	 * @throws java.text.ParseException
	 */
	public static void startGame(ArrayList<Player> playerList)
			throws FileNotFoundException, JSONException, IOException, ParseException, java.text.ParseException {
		Scanner scanner = new Scanner(System.in);
		Chance chanceCards = new Chance();
		CommunityChest communityChestCards = new CommunityChest();

		chanceCards.generateStack();
		communityChestCards.generateStack();

		System.out.println(
				"Welcome to Hasbro's Monopoly- Optimized by Aditya Mehrotra!\nDo you want to Play a Game? (Y/N): ");
		String playGameDecision = scanner.nextLine();

		if (playGameDecision.equals("Y")) {
			System.out.print("How many Players? (2, 3, 4): ");
			int numPlayers = scanner.nextInt();

			if (numPlayers >= 3) {
				Player playerThree = new Player(board);
				playerList.add(playerThree);
			}

			if (numPlayers == 4) {
				Player playerFour = new Player(board);
				playerList.add(playerFour);
			}

			rotatePlayers(chanceCards, communityChestCards, playerList, numPlayers);
		}
	}

	public static void rotatePlayers(Chance chanceCards, CommunityChest communityChestCards,
			ArrayList<Player> playerList, int numPlayers)
			throws FileNotFoundException, JSONException, IOException, ParseException, java.text.ParseException {
		while ((playerList != null) && (playerList.size() != 0)) {
			playerOneTurn(playerList.get(0), playerList, chanceCards, communityChestCards);
			playerTwoTurn(playerList.get(1), playerList, chanceCards, communityChestCards);

			if (numPlayers >= 3) {
				playerThreeTurn(playerList.get(2), playerList, chanceCards, communityChestCards);
			}

			if (numPlayers == 4) {
				playerFourTurn(playerList.get(3), playerList, chanceCards, communityChestCards);
			}
		}
	}

	public static void playerOneTurn(Player player, ArrayList<Player> playerList, Chance chanceCards,
			CommunityChest communityChestCards)
			throws FileNotFoundException, JSONException, IOException, ParseException, java.text.ParseException {
		Scanner scanner = new Scanner(System.in);

		if (player.getInJail() == true) {
			if (player.getJailCount() == 3) {
				System.out.println(
						"\nHi Player One. Would you like to: \n(R)oll the Dice\n(C)heck your Assets\n(M)ortgage your Assets\n(U)nmortgage your Assets");
				String initDecision = scanner.nextLine();

				playerOption(player, playerList, chanceCards, communityChestCards, initDecision);
			} else {
				System.out.println("\nHi Player One. You are in Jail. Do you want to (B)uy or (R)oll?: ");
				String decision = scanner.nextLine();
				if (decision.equals("B")) {
					player.setMoney("-", 50);
					player.setInJail(false);

					System.out.println(
							"\nHi Player One. Would you like to: \n(R)oll the Dice\n(C)heck your Assets\n(M)ortgage your Assets\n(U)nmortgage your Assets");
					String initDecision = scanner.nextLine();
					player.clearJailCount();

					playerOption(player, playerList, chanceCards, communityChestCards, initDecision);
				} else {
					Random random = new Random();
					int firstDiceVal = random.nextInt(6) + 1;
					int secondDiceVal = random.nextInt(6) + 1;

					if (firstDiceVal == secondDiceVal) {
						player.clearJailCount();
						int moveNumber = firstDiceVal + secondDiceVal;

						System.out.println("First Dice: " + firstDiceVal);
						System.out.println("Second Dice: " + secondDiceVal);

						player.setPosition("Forward", moveNumber);
						System.out.println(player.getPosition());
						checkSquare(player, playerList, board[player.getPosition()], chanceCards, communityChestCards);

						if (firstDiceVal == secondDiceVal) {
							int count = 1;
							rollDice(player, playerList, chanceCards, communityChestCards, count);
						}
					} else {

						player.setJailCount(1);
					}

				}

			}
		} else {
			System.out.println(
					"\nHi Player One. Would you like to: \n(R)oll the Dice\n(C)heck your Assets\n(M)ortgage your Assets\n(U)nmortgage your Assets");
			String initDecision = scanner.nextLine();

			playerOption(player, playerList, chanceCards, communityChestCards, initDecision);
		}

	}

	public static void playerTwoTurn(Player player, ArrayList<Player> playerList, Chance chanceCards,
			CommunityChest communityChestCards)
			throws FileNotFoundException, JSONException, IOException, ParseException, java.text.ParseException {
		Scanner scanner = new Scanner(System.in);

		if (player.getInJail() == true) {
			if (player.getJailCount() == 3) {
				System.out.println(
						"\nHi Player Two. Would you like to: \n(R)oll the Dice\n(C)heck your Assets\n(M)ortgage your Assets\n(U)nmortgage your Assets");
				String initDecision = scanner.nextLine();

				playerOption(player, playerList, chanceCards, communityChestCards, initDecision);
			} else {
				System.out.println("\nHi Player Two. You are in Jail. Do you want to (B)uy or (R)oll?: ");
				String decision = scanner.nextLine();
				if (decision.equals("B")) {
					player.setMoney("-", 50);
					player.setInJail(false);

					System.out.println(
							"\nHi Player Two. Would you like to: \n(R)oll the Dice\n(C)heck your Assets\n(M)ortgage your Assets\n(U)nmortgage your Assets");
					String initDecision = scanner.nextLine();
					player.clearJailCount();

					playerOption(player, playerList, chanceCards, communityChestCards, initDecision);
				} else {
					Random random = new Random();
					int firstDiceVal = random.nextInt(6) + 1;
					int secondDiceVal = random.nextInt(6) + 1;

					if (firstDiceVal == secondDiceVal) {
						player.clearJailCount();
						int moveNumber = firstDiceVal + secondDiceVal;

						System.out.println("First Dice: " + firstDiceVal);
						System.out.println("Second Dice: " + secondDiceVal);

						player.setPosition("Forward", moveNumber);
						System.out.println(player.getPosition());
						checkSquare(player, playerList, board[player.getPosition()], chanceCards, communityChestCards);

						if (firstDiceVal == secondDiceVal) {
							int count = 1;
							rollDice(player, playerList, chanceCards, communityChestCards, count);
						}
					} else {

						player.setJailCount(1);
					}

				}

			}
		} else {
			System.out.println(
					"\nHi Player Two. Would you like to: \n(R)oll the Dice\n(C)heck your Assets\n(M)ortgage your Assets\n(U)nmortgage your Assets");
			String initDecision = scanner.nextLine();

			playerOption(player, playerList, chanceCards, communityChestCards, initDecision);
		}

	}

	public static void playerThreeTurn(Player player, ArrayList<Player> playerList, Chance chanceCards,
			CommunityChest communityChestCards)
			throws FileNotFoundException, JSONException, IOException, ParseException, java.text.ParseException {
		Scanner scanner = new Scanner(System.in);

		if (player.getInJail() == true) {
			if (player.getJailCount() == 3) {
				System.out.println(
						"\nHi Player Three. Would you like to: \n(R)oll the Dice\n(C)heck your Assets\n(M)ortgage your Assets\n(U)nmortgage your Assets");
				String initDecision = scanner.nextLine();

				playerOption(player, playerList, chanceCards, communityChestCards, initDecision);
			} else {
				System.out.println("\nHi Player Three. You are in Jail. Do you want to (B)uy or (R)oll?: ");
				String decision = scanner.nextLine();
				if (decision.equals("B")) {
					player.setMoney("-", 50);
					player.setInJail(false);

					System.out.println(
							"\nHi Player Three. Would you like to: \n(R)oll the Dice\n(C)heck your Assets\n(M)ortgage your Assets\n(U)nmortgage your Assets");
					String initDecision = scanner.nextLine();
					player.clearJailCount();

					playerOption(player, playerList, chanceCards, communityChestCards, initDecision);
				} else {
					Random random = new Random();
					int firstDiceVal = random.nextInt(6) + 1;
					int secondDiceVal = random.nextInt(6) + 1;

					if (firstDiceVal == secondDiceVal) {
						player.clearJailCount();
						int moveNumber = firstDiceVal + secondDiceVal;

						System.out.println("First Dice: " + firstDiceVal);
						System.out.println("Second Dice: " + secondDiceVal);

						player.setPosition("Forward", moveNumber);
						System.out.println(player.getPosition());
						checkSquare(player, playerList, board[player.getPosition()], chanceCards, communityChestCards);

						if (firstDiceVal == secondDiceVal) {
							int count = 1;
							rollDice(player, playerList, chanceCards, communityChestCards, count);
						}
					} else {

						player.setJailCount(1);
					}

				}

			}
		} else {
			System.out.println(
					"\nHi Player Three. Would you like to: \n(R)oll the Dice\n(C)heck your Assets\n(M)ortgage your Assets\n(U)nmortgage your Assets");
			String initDecision = scanner.nextLine();

			playerOption(player, playerList, chanceCards, communityChestCards, initDecision);
		}

	}

	public static void playerFourTurn(Player player, ArrayList<Player> playerList, Chance chanceCards,
			CommunityChest communityChestCards)
			throws FileNotFoundException, JSONException, IOException, ParseException, java.text.ParseException {
		Scanner scanner = new Scanner(System.in);

		if (player.getInJail() == true) {
			if (player.getJailCount() == 3) {
				System.out.println(
						"\nHi Player Four. Would you like to: \n(R)oll the Dice\n(C)heck your Assets\n(M)ortgage your Assets\n(U)nmortgage your Assets");
				String initDecision = scanner.nextLine();

				playerOption(player, playerList, chanceCards, communityChestCards, initDecision);
			} else {
				System.out.println("\nHi Player Four. You are in Jail. Do you want to (B)uy or (R)oll?: ");
				String decision = scanner.nextLine();
				if (decision.equals("B")) {
					player.setMoney("-", 50);
					player.setInJail(false);

					System.out.println(
							"\nHi Player Four. Would you like to: \n(R)oll the Dice\n(C)heck your Assets\n(M)ortgage your Assets\n(U)nmortgage your Assets");
					String initDecision = scanner.nextLine();
					player.clearJailCount();

					playerOption(player, playerList, chanceCards, communityChestCards, initDecision);
				} else {
					Random random = new Random();
					int firstDiceVal = random.nextInt(6) + 1;
					int secondDiceVal = random.nextInt(6) + 1;

					if (firstDiceVal == secondDiceVal) {
						player.clearJailCount();
						int moveNumber = firstDiceVal + secondDiceVal;

						System.out.println("First Dice: " + firstDiceVal);
						System.out.println("Second Dice: " + secondDiceVal);

						player.setPosition("Forward", moveNumber);
						System.out.println(player.getPosition());
						checkSquare(player, playerList, board[player.getPosition()], chanceCards, communityChestCards);

						if (firstDiceVal == secondDiceVal) {
							int count = 1;
							rollDice(player, playerList, chanceCards, communityChestCards, count);
						}
					} else {

						player.setJailCount(1);
					}

				}

			}
		} else {
			System.out.println(
					"\nHi Player Four. Would you like to: \n(R)oll the Dice\n(C)heck your Assets\n(M)ortgage your Assets\n(U)nmortgage your Assets");
			String initDecision = scanner.nextLine();

			playerOption(player, playerList, chanceCards, communityChestCards, initDecision);
		}

	}

	public static void playerOption(Player player, ArrayList<Player> playerList, Chance chanceCards,
			CommunityChest communityChestCards,
			String initDecision)
			throws FileNotFoundException, JSONException, IOException, ParseException, java.text.ParseException {
		Scanner scanner = new Scanner(System.in);
		if (initDecision.equals("R")) {
			rollDice(player, playerList, chanceCards, communityChestCards, 0);
		} else if (initDecision.equals("C")) {
			checkAssets(player);

			System.out.println(
					"\nAfter Checking your Assets, what would you like to do?: \n(R)oll the Dice\n(M)ortgage your Assets\n(U)nmortgage your Assets\n");
			String secondDecision = scanner.nextLine();

			if (secondDecision.equals("R")) {
				rollDice(player, playerList, chanceCards, communityChestCards, 0);
			} else if (secondDecision.equals("M")) {
				System.out.println("Money: " + player.getMoney());
				System.out.print("Property: ");

				for (int i = 0; i < player.getPropertyList().size(); i++) {
					String thisID = player.getPropertyList().get(i).getID();
					if (i == (player.getPropertyList().size() - 1)) {
						System.out.println(player.getPropertyList().get(i).getName(thisID) + " ("
								+ player.getPropertyList().get(i).getColor(thisID) + ")");
					} else if (i == (player.getPropertyList().size() - 2)) {
						System.out.print(player.getPropertyList().get(i).getName(thisID) + " ("
								+ player.getPropertyList().get(i).getColor(thisID) + "), and ");
					} else {
						System.out.print(player.getPropertyList().get(i).getName(thisID) + " ("
								+ player.getPropertyList().get(i).getColor(thisID) + "), ");
					}
				}

				for (String color : player.getColorList().keySet()) {
					System.out.println(color + ": " + player.getColorList().get(color));
				}

				System.out.println("Which Property will you like to Mortgage?: ");
				String mortgageProp = scanner.nextLine();

				mortgagePropertyPre(player, mortgageProp);
			}
		} else if (initDecision.equals("M")) {
			System.out.println("Money: " + player.getMoney());
			System.out.print("Property: ");

			for (int i = 0; i < player.getPropertyList().size(); i++) {
				String thisID = player.getPropertyList().get(i).getID();

				if (i == (player.getPropertyList().size() - 1)) {
					System.out.println(player.getPropertyList().get(i).getName(thisID) + " ("
							+ player.getPropertyList().get(i).getColor(thisID) + ")");
				} else if (i == (player.getPropertyList().size() - 2)) {
					System.out.print(player.getPropertyList().get(i).getName(thisID) + " ("
							+ player.getPropertyList().get(i).getColor(thisID) + "), and ");
				} else {
					System.out.print(player.getPropertyList().get(i).getName(thisID) + " ("
							+ player.getPropertyList().get(i).getColor(thisID) + "), ");
				}
			}

			for (String color : player.getColorList().keySet()) {
				System.out.println(color + ": " + player.getColorList().get(color));
			}

			System.out.println("Which Property will you like to Mortgage?: ");
			String mortgageProp = scanner.nextLine();

			mortgagePropertyPre(player, mortgageProp);
		}
	}

	/**
	 * Rolls the dice, and places the player at the new position, given the new dice
	 * roll. Checks if a double is rolled.
	 * 
	 * @param player
	 * @param playerList
	 * @param chanceCards
	 * @param communityChestCards
	 * @param count
	 * @throws FileNotFoundException
	 * @throws JSONException
	 * @throws IOException
	 * @throws ParseException
	 * @throws java.text.ParseException
	 */
	public static void rollDice(Player player, ArrayList<Player> playerList, Chance chanceCards,
			CommunityChest communityChestCards, int count)
			throws FileNotFoundException, JSONException, IOException, ParseException, java.text.ParseException {
		if (count == 3) {
			player.setInJail(true);
		}

		Random random = new Random();
		int firstDiceVal = random.nextInt(6) + 1;
		int secondDiceVal = random.nextInt(6) + 1;
		int moveNumber = firstDiceVal + secondDiceVal;

		System.out.println("\nFirst Dice: " + firstDiceVal);
		System.out.println("Second Dice: " + secondDiceVal);

		player.setPosition("Forward", moveNumber);
		checkSquare(player, playerList, board[player.getPosition()], chanceCards, communityChestCards);

		if (firstDiceVal == secondDiceVal) {
			count += 1;
			rollDice(player, playerList, chanceCards, communityChestCards, count);
		}
	}

	public static void checkAssets(Player player)
			throws FileNotFoundException, JSONException, IOException, ParseException {
		System.out.println("\nMoney: " + player.getMoney());

		if (player.getPropertyList() != null) {
			if (player.getPropertyList().size() > 1) {
				System.out.print("Properties: ");
			} else if (player.getPropertyList().size() > 0) {
				System.out.print("Property: ");
			}
		}

		for (int i = 0; i < player.getPropertyList().size(); i++) {
			String thisID = player.getPropertyList().get(i).getID();

			if (i == (player.getPropertyList().size() - 1)) {
				System.out.print(player.getPropertyList().get(i).getName(thisID) + " ("
						+ player.getPropertyList().get(i).getColor(thisID) + ")\n");
			} else if (i == (player.getPropertyList().size() - 2)) {
				System.out.print(player.getPropertyList().get(i).getName(thisID) + " ("
						+ player.getPropertyList().get(i).getColor(thisID) + "), and ");
			} else {
				System.out.print(player.getPropertyList().get(i).getName(thisID) + " ("
						+ player.getPropertyList().get(i).getColor(thisID) + "), ");
			}
		}

		if (player.getRailroadList() != null) {
			if (player.getRailroadList().size() > 1) {
				System.out.print("Railroads: ");
			} else if (player.getRailroadList().size() > 0) {
				System.out.print("Railroad: ");
			}
		}

		for (int i = 0; i < player.getRailroadList().size(); i++) {
			String thisID = player.getRailroadList().get(i).getID();

			if (i == (player.getRailroadList().size() - 1)) {
				System.out.print(player.getRailroadList().get(i).getName(thisID) + "\n");
			} else if (i == (player.getRailroadList().size() - 2)) {
				System.out.print(player.getRailroadList().get(i).getName(thisID) + ", and ");
			} else {
				System.out.print(player.getRailroadList().get(i).getName(thisID) + ", ");
			}
		}

		if (player.getUtilityList() != null) {
			if (player.getUtilityList().size() > 1) {
				System.out.print("Utilities: ");
			} else if (player.getUtilityList().size() > 0) {
				System.out.print("Utility: ");
			}
		}

		for (int i = 0; i < player.getUtilityList().size(); i++) {
			String thisID = player.getUtilityList().get(i).getID();

			if (i == (player.getUtilityList().size() - 1)) {
				System.out.print(player.getUtilityList().get(i).getName(thisID) + "\n");
			} else if (i == (player.getUtilityList().size() - 2)) {
				System.out.print(player.getUtilityList().get(i).getName(thisID) + ", and ");
			} else {
				System.out.print(player.getUtilityList().get(i).getName(thisID) + ", ");
			}
		}

		for (String color : player.getColorList().keySet()) {
			System.out.println(color + ": " + player.getColorList().get(color));
		}

		if (player.getMortgagePropertyList() != null) {
			if (player.getMortgagePropertyList().size() > 1) {
				System.out.print("Mortgaged Properties: ");
			} else if (player.getMortgagePropertyList().size() > 0) {
				System.out.print("Mortgaged Property: ");
			}
		}

		for (int i = 0; i < player.getMortgagePropertyList().size(); i++) {
			String thisID = player.getMortgagePropertyList().get(i).getID();

			if (i == (player.getMortgagePropertyList().size() - 1)) {
				System.out.print(player.getMortgagePropertyList().get(i).getName(thisID) + " ("
						+ player.getMortgagePropertyList().get(i).getColor(thisID) + ")\n");
			} else if (i == (player.getMortgagePropertyList().size() - 2)) {
				System.out.print(player.getMortgagePropertyList().get(i).getName(thisID) + " ("
						+ player.getMortgagePropertyList().get(i).getColor(thisID) + "), and ");
			} else {
				System.out.print(player.getMortgagePropertyList().get(i).getName(thisID) + " ("
						+ player.getMortgagePropertyList().get(i).getColor(thisID) + "), ");
			}
		}

		if (player.getMortgageRailroadList() != null) {
			if (player.getMortgageRailroadList().size() > 1) {
				System.out.print("Mortgaged Railroads: ");
			} else if (player.getMortgageRailroadList().size() > 0) {
				System.out.print("Mortgaged Railroad: ");
			}
		}

		for (int i = 0; i < player.getMortgageRailroadList().size(); i++) {
			String thisID = player.getMortgageRailroadList().get(i).getID();

			if (i == (player.getMortgageRailroadList().size() - 1)) {
				System.out.print(player.getMortgageRailroadList().get(i).getName(thisID) + "\n");
			} else if (i == (player.getMortgageRailroadList().size() - 2)) {
				System.out.print(player.getMortgageRailroadList().get(i).getName(thisID) + ", and ");
			} else {
				System.out.print(player.getMortgageRailroadList().get(i).getName(thisID) + ", ");
			}
		}

		if (player.getMortgageUtilityList() != null) {
			if (player.getMortgageUtilityList().size() > 1) {
				System.out.print("Mortgaged Utilities: ");
			} else if (player.getMortgageUtilityList().size() > 0) {
				System.out.print("Mortgaged Utility: ");
			}
		}

		for (int i = 0; i < player.getMortgageUtilityList().size(); i++) {
			String thisID = player.getMortgageUtilityList().get(i).getID();

			if (i == (player.getMortgageUtilityList().size() - 1)) {
				System.out.print(player.getMortgageUtilityList().get(i).getName(thisID) + "\n");
			} else if (i == (player.getMortgageUtilityList().size() - 2)) {
				System.out.print(player.getMortgageUtilityList().get(i).getName(thisID) + ", and ");
			} else {
				System.out.print(player.getMortgageUtilityList().get(i).getName(thisID) + ", ");
			}
		}
	}

	public static void mortgagePropertyPre(Player player, String propName)
			throws FileNotFoundException, JSONException, IOException, ParseException, java.text.ParseException {
		Scanner scanner = new Scanner(System.in);
		for (int i = 0; i < player.getPropertyList().size(); i++) {
			if ((player.getPropertyList().get(i)).equals(propName)) {
				String ID = player.getPropertyList().get(i).getID();
				Property property = new Property(ID, player.getColorList());

				mortgageProperty(ID, player, property);
			} else if ((player.getRailroadList().get(i)).equals(propName)) {
				String ID = player.getRailroadList().get(i).getID();
				Railroad railroad = new Railroad(ID, player.getColorList());

				mortgageRailroad(ID, player, railroad);
			} else if ((player.getUtilityList().get(i)).equals(propName)) {
				String ID = player.getUtilityList().get(i).getID();
				Utility utility = new Utility(ID, player.getColorList());

				mortgageUtility(ID, player, utility);
			} else {
				System.out.println(
						"This Property cannot be Mortgaged by you at this time.\nWhich Property will you like to Mortgage?: ");
				String mortgageProp = scanner.nextLine();

				mortgagePropertyPre(player, mortgageProp);
			}
		}
	}

	public static void checkSquare(Player player, ArrayList<Player> playerList, String squareID, Chance chanceCards,
			CommunityChest communityChestCards)
			throws FileNotFoundException, JSONException, IOException, ParseException, java.text.ParseException {
		String[] IDArray = squareID.split("-");
		String type = IDArray[0];

		if (type.equals("P")) {
			landOnProperty(player, playerList, squareID);
		} else if (type.equals("R")) {
			landOnRailroad(player, playerList, squareID);
		} else if (type.equals("U")) {
			landOnUtility(player, playerList, squareID);
		} else if (type.equals("C")) {
			landOnChance(player, playerList, chanceCards);
		} else if (type.equals("CC")) {
			landOnCommunityChest(player, playerList, communityChestCards);
		} else {
			landOnSpecialSquare(player, squareID);
		}
	}

	public static void landOnProperty(Player player, ArrayList<Player> playerList, String ID)
			throws FileNotFoundException, JSONException, IOException, ParseException, java.text.ParseException {
		Property property = new Property(ID, player.getColorList());
		System.out.println("You have landed on " + property.getName(ID));

		String[] IDArray = ID.split("-");
		String propType = IDArray[4];

		Scanner scanner = new Scanner(System.in);
		if (propType.equals("OU")) {
			System.out.println("New" + board[Integer.parseInt(IDArray[1])]);
			System.out.println("New" + player.getColorList());
			System.out.println("This Property is already Owned. The owed Rent is "
					+ property.getRent(player, board[Integer.parseInt(IDArray[1])], player.getColorList()));

			player.setMoney("-",
					property.getRent(player, board[Integer.parseInt(IDArray[1])], player.getColorList()));
		} else if (propType.equals("F")) {
			System.out.println("This Property is Unowned. Do you want to (A)uction or (B)uy it?: ");
			String freeDecision = scanner.nextLine();

			if (freeDecision.equals("A")) {
				auctionProperty(ID, player, playerList, property);
			} else if (freeDecision.equals("B")) {
				buyProperty(ID, player, property, 0);
			}
		} else {
			System.out.println("This Property is already Owned, however, it is Mortgaged.");
		}
	}

	public static void landOnRailroad(Player player, ArrayList<Player> playerList, String ID)
			throws JSONException, FileNotFoundException, IOException, ParseException, java.text.ParseException {
		Railroad railroad = new Railroad(ID, player.getColorList());
		System.out.println("You have landed on " + railroad.getName(ID));

		String[] IDArray = ID.split("-");
		String propType = IDArray[4];

		Scanner scanner = new Scanner(System.in);
		if (propType.equals("OU")) {
			System.out.println("New" + board[Integer.parseInt(IDArray[1])]);
			System.out.println("New" + player.getColorList());
			System.out.println("This Railroad is already Owned. The owed Rent is "
					+ railroad.getRent(player, board[Integer.parseInt(IDArray[1])], player.getColorList()));

			player.setMoney("-",
					railroad.getRent(player, board[Integer.parseInt(IDArray[1])], player.getColorList()));
		} else if (propType.equals("F")) {
			System.out.println("This Property is Unowned. Do you want to (A)uction or (B)uy it?: ");
			String freeDecision = scanner.nextLine();

			if (freeDecision.equals("A")) {
				auctionRailroad(ID, player, playerList, railroad);
			} else if (freeDecision.equals("B")) {
				buyRailroad(ID, player, railroad, 0);
			}
		} else {
			System.out.println("This Property is already Owned, however, it is Mortgaged.");
		}
	}

	public static void landOnUtility(Player player, ArrayList<Player> playerList, String ID)
			throws NumberFormatException, FileNotFoundException,
			JSONException, IOException, ParseException, java.text.ParseException {
		Utility utility = new Utility(ID, player.getColorList());
		System.out.println("You have landed on " + utility.getName(ID));

		String[] IDArray = ID.split("-");
		String propType = IDArray[4];

		Scanner scanner = new Scanner(System.in);
		if (propType.equals("OU")) {
			System.out.println("New" + board[Integer.parseInt(IDArray[1])]);
			System.out.println("New" + player.getColorList());
			System.out.println("This Utility is already Owned. The owed Rent is "
					+ utility.getRent(player, board[Integer.parseInt(IDArray[1])], player.getColorList()));

			player.setMoney("-",
					utility.getRent(player, board[Integer.parseInt(IDArray[1])], player.getColorList()));
		} else if (propType.equals("F")) {
			System.out.println("This Utility is Unowned. Do you want to (A)uction or (B)uy it?: ");
			String freeDecision = scanner.nextLine();

			if (freeDecision.equals("A")) {
				auctionUtility(ID, player, playerList, utility);
			} else if (freeDecision.equals("B")) {
				buyUtility(ID, player, utility, 0);
			}
		} else {
			System.out.println("This Property is already Owned, however, it is Mortgaged.");
		}
	}

	public static void landOnChance(Player player, ArrayList<Player> playerList, Chance chanceCards)
			throws FileNotFoundException, JSONException, IOException, ParseException, java.text.ParseException {
		System.out.println("You landed on Chance!");

		chanceOperations(player, playerList, chanceCards.getChanceIndex());
	}

	public static void landOnCommunityChest(Player player, ArrayList<Player> playerList,
			CommunityChest communityChestCards)
			throws FileNotFoundException, JSONException, IOException, ParseException {
		System.out.println("You landed on Community Chest!");

		communityChestOperations(player, playerList, communityChestCards.getCommunityChestIndex());
	}

	public static void landOnSpecialSquare(Player player, String ID) {
		String[] IDArray = ID.split("-");
		int square = Integer.parseInt(IDArray[1]);

		if (square == 0) {
			System.out.println("You landed on Go!");
			player.setMoney("+", 200);
		} else if (square == 4) {
			System.out.println("You landed on Income Tax.");
			player.setMoney("-", 200);
		} else if (square == 10) {
			System.out.println("You are just visiting jail.");
		} else if (square == 20) {
			System.out.println("You landed on Free Parking.");
		} else if (square == 30) {
			System.out.println("Go to Jail!");
			player.setInJail(true);
		} else if (square == 38) {
			System.out.println("You have landed on Super Tax");
			player.setMoney("-", 100);
		}
	}

	public static void buyProperty(String ID, Player player, Property property, int cost)
			throws JSONException, FileNotFoundException, IOException, ParseException, java.text.ParseException {
		String[] IDArray = ID.split("-");
		int square = Integer.parseInt(IDArray[1]);
		board = property.setID(board, board[square], "", "OU", 0);

		if (cost != 0) {
			player.setMoney("-", cost);
			player.setPropertyList(property, "+");
			player.setColorList(property.getColor(ID), "+", 1);
			board = player.checkColors(property, board, square);
		} else {
			player.setMoney("-", property.getCost(ID));
			player.setPropertyList(property, "+");
			player.setColorList(property.getColor(ID), "+", 1);
			board = player.checkColors(property, board, square);
		}

	}

	public static void auctionProperty(String ID, Player player, ArrayList<Player> playerList, Property property)
			throws FileNotFoundException, JSONException, IOException, ParseException, java.text.ParseException {
		String propName = property.getName(ID);
		Long originalValue = property.getCost(ID);
		Boolean[] inAuction = new Boolean[playerList.size()];
		int playerCount = playerList.size();
		int bidPrice = 10;

		for (int i = 0; i < inAuction.length; i++) {
			inAuction[i] = true;
		}

		while (playerCount > 1) {
			for (int i = 0; i < playerList.size(); i++) {
				Player currentPlayer = playerList.get(i);
				System.out.println("\nIt is Player " + getPlayerFull(i) + "'s Turn\nThe Original Cost of " + propName
						+ " is " + originalValue + "\nThe Current Bid Value is " + bidPrice);
				Scanner scanner = new Scanner(System.in);
				int tempBidPrice = bidPrice;
				System.out.println(
						"\nDo you want to: \n(S)top Bidding\nBid (O)ne Dollar\nBid (T)en Dollars\nBid One (H)undred Dollars");
				String decision = scanner.nextLine();

				if (decision.equals("O")) {
					tempBidPrice += 1;
				} else if (decision.equals("T")) {
					tempBidPrice += 10;
				} else if (decision.equals("H")) {
					tempBidPrice += 100;
				} else {
					inAuction[i] = false;
					playerCount -= 1;
				}

				if (!(currentPlayer.getMoney() >= tempBidPrice)) {
					System.out.println("You have overbid. You automatically drop out of the auction of " + propName);
					inAuction[i] = false;
					playerCount -= 1;
				} else {
					bidPrice = tempBidPrice;
				}
			}
		}

		int chosenPlayer = 0;
		for (int i = 0; i < inAuction.length; i++) {
			if (inAuction[i] == true) {
				chosenPlayer = i;
			}
		}

		System.out.println(
				"\nCongratulations Player " + getPlayerFull(chosenPlayer) + ". You get " + propName + " for "
						+ bidPrice);
		buyProperty(ID, playerList.get(chosenPlayer), property, bidPrice);
	}

	public static String getPlayerFull(int playerVal) {
		if (playerVal == 0) {
			return "One";
		} else if (playerVal == 1) {
			return "Two";
		} else if (playerVal == 2) {
			return "Three";
		} else {
			return "Four";
		}
	}

	public static void mortgageProperty(String ID, Player player, Property property)
			throws JSONException, FileNotFoundException, IOException, ParseException, java.text.ParseException {
		player.setMoney("+", property.getMortgage(ID));
		player.setPropertyList(property, "-");
		player.setMortgagePropertyList(property, "Mortgage");
		player.setColorList(property.getColor(ID), "-", 1);
	}

	public static void unMortgageProperty(String ID, Player player, Property property)
			throws JSONException, FileNotFoundException, IOException, ParseException, java.text.ParseException {
		player.setMoney("-", property.getUnMortgage(ID));
		player.setPropertyList(property, "+");
		player.setMortgagePropertyList(property, "UnMortgage");
		player.setColorList(property.getColor(ID), "+", 1);
	}

	public static void buyRailroad(String ID, Player player, Railroad railroad, int cost)
			throws JSONException, FileNotFoundException, IOException, ParseException, java.text.ParseException {
		if (cost != 0) {
			player.setMoney("-", cost);
			player.setRailroadList(railroad, "+");
			player.setColorList("Railroad", "+", 1);
		} else {
			player.setMoney("-", railroad.getCost(ID));
			player.setRailroadList(railroad, "+");
			player.setColorList("Railroad", "+", 1);
		}

	}

	public static void auctionRailroad(String ID, Player player, ArrayList<Player> playerList, Railroad railroad)
			throws FileNotFoundException, JSONException, IOException, ParseException, java.text.ParseException {
		String propName = railroad.getName(ID);
		Long originalValue = railroad.getCost(ID);
		Boolean[] inAuction = new Boolean[playerList.size()];
		int playerCount = playerList.size();
		int bidPrice = 10;

		for (int i = 0; i < inAuction.length; i++) {
			inAuction[i] = true;
		}

		while (playerCount > 1) {
			for (int i = 0; i < playerList.size(); i++) {
				Player currentPlayer = playerList.get(i);
				System.out.println("\nIt is Player " + getPlayerFull(i) + "'s Turn\nThe Original Cost of " + propName
						+ " is " + originalValue + "\nThe Current Bid Value is " + bidPrice);
				Scanner scanner = new Scanner(System.in);
				int tempBidPrice = bidPrice;
				System.out.println(
						"\nDo you want to: \n(S)top Bidding\nBid (O)ne Dollar\nBid (T)en Dollars\nBid One (H)undred Dollars");
				String decision = scanner.nextLine();

				if (decision.equals("O")) {
					tempBidPrice += 1;
				} else if (decision.equals("T")) {
					tempBidPrice += 10;
				} else if (decision.equals("H")) {
					tempBidPrice += 100;
				} else {
					inAuction[i] = false;
					playerCount -= 1;
				}

				if (!(currentPlayer.getMoney() >= tempBidPrice)) {
					System.out.println("You have overbid. You automatically drop out of the auction of " + propName);
					inAuction[i] = false;
					playerCount -= 1;
				} else {
					bidPrice = tempBidPrice;
				}
			}
		}

		int chosenPlayer = 0;
		for (int i = 0; i < inAuction.length; i++) {
			if (inAuction[i] == true) {
				chosenPlayer = i;
			}
		}

		System.out.println(
				"\nCongratulations Player " + getPlayerFull(chosenPlayer) + ". You get " + propName + " for "
						+ bidPrice);
		buyRailroad(ID, playerList.get(chosenPlayer), railroad, bidPrice);
	}

	public static void mortgageRailroad(String ID, Player player, Railroad railroad)
			throws JSONException, FileNotFoundException, IOException, ParseException, java.text.ParseException {
		player.setMoney("+", railroad.getMortgage(ID));
		player.setRailroadList(railroad, "-");
		player.setMortgageRailroadList(railroad, "Mortgage");
		player.setColorList("Railroad", "-", 1);
	}

	public static void unMortgageRailroad(String ID, Player player, Railroad railroad)
			throws JSONException, FileNotFoundException, IOException, ParseException, java.text.ParseException {
		player.setMoney("-", railroad.getUnMortgage(ID));
		player.setRailroadList(railroad, "+");
		player.setMortgageRailroadList(railroad, "UnMortgage");
		player.setColorList("Railroad", "+", 1);
	}

	public static void buyUtility(String ID, Player player, Utility utility, int cost)
			throws JSONException, FileNotFoundException, IOException, ParseException, java.text.ParseException {
		if (cost != 0) {
			player.setMoney("-", cost);
			player.setUtilityList(utility, "+");
			player.setColorList("Utility", "+", 1);
		} else {
			player.setMoney("-", utility.getCost(ID));
			player.setUtilityList(utility, "+");
			player.setColorList("Utility", "+", 1);
		}

	}

	public static void auctionUtility(String ID, Player player, ArrayList<Player> playerList, Utility utility)
			throws FileNotFoundException, JSONException, IOException, ParseException, java.text.ParseException {
		String propName = utility.getName(ID);
		Long originalValue = utility.getCost(ID);
		Boolean[] inAuction = new Boolean[playerList.size()];
		int playerCount = playerList.size();
		int bidPrice = 10;

		for (int i = 0; i < inAuction.length; i++) {
			inAuction[i] = true;
		}

		while (playerCount > 1) {
			for (int i = 0; i < playerList.size(); i++) {
				Player currentPlayer = playerList.get(i);
				System.out.println("\nIt is Player " + getPlayerFull(i) + "'s Turn\nThe Original Cost of " + propName
						+ " is " + originalValue + "\nThe Current Bid Value is " + bidPrice);
				Scanner scanner = new Scanner(System.in);
				int tempBidPrice = bidPrice;
				System.out.println(
						"\nDo you want to: \n(S)top Bidding\nBid (O)ne Dollar\nBid (T)en Dollars\nBid One (H)undred Dollars");
				String decision = scanner.nextLine();

				if (decision.equals("O")) {
					tempBidPrice += 1;
				} else if (decision.equals("T")) {
					tempBidPrice += 10;
				} else if (decision.equals("H")) {
					tempBidPrice += 100;
				} else {
					inAuction[i] = false;
					playerCount -= 1;
				}

				if (!(currentPlayer.getMoney() >= tempBidPrice)) {
					System.out.println("You have overbid. You automatically drop out of the auction of " + propName);
					inAuction[i] = false;
					playerCount -= 1;
				} else {
					bidPrice = tempBidPrice;
				}
			}
		}

		int chosenPlayer = 0;
		for (int i = 0; i < inAuction.length; i++) {
			if (inAuction[i] == true) {
				chosenPlayer = i;
			}
		}

		System.out.println(
				"\nCongratulations Player " + getPlayerFull(chosenPlayer) + ". You get " + propName + " for "
						+ bidPrice);
		buyUtility(ID, playerList.get(chosenPlayer), utility, bidPrice);
	}

	public static void mortgageUtility(String ID, Player player, Utility utility)
			throws JSONException, FileNotFoundException, IOException, ParseException, java.text.ParseException {
		player.setMoney("+", utility.getMortgage(ID));
		player.setUtilityList(utility, "-");
		player.setMortgageUtilityList(utility, "Mortgage");
		player.setColorList("Utility", "-", 1);
	}

	public static void unMortgageUtility(String ID, Player player, Utility utility)
			throws JSONException, FileNotFoundException, IOException, ParseException, java.text.ParseException {
		player.setMoney("-", utility.getUnMortgage(ID));
		player.setUtilityList(utility, "+");
		player.setMortgageUtilityList(utility, "UnMortgage");
		player.setColorList("Utility", "+", 1);
	}

	public static void communityChestOperations(Player player, ArrayList<Player> playerList, int communityChestCard)
			throws FileNotFoundException, JSONException, IOException, ParseException {

		CommunityChest commChest = new CommunityChest();

		if (communityChestCard == 0) {
			System.out.println(commChest.getTitle(communityChestCard));
			System.out.println(commChest.getDescription(communityChestCard));
			player.setPosition("Exact", 0);
			player.passGO();
		} else if (communityChestCard == 1) {
			System.out.println(commChest.getTitle(communityChestCard));
			System.out.println(commChest.getDescription(communityChestCard));
			player.setMoney("+", 200);
		} else if (communityChestCard == 2) {
			System.out.println(commChest.getTitle(communityChestCard));
			System.out.println(commChest.getDescription(communityChestCard));
			player.setMoney("-", 50);
		} else if (communityChestCard == 3) {
			System.out.println(commChest.getTitle(communityChestCard));
			player.setMoney("+", 50);
		} else if (communityChestCard == 4) {
			System.out.println(commChest.getTitle(communityChestCard));
			System.out.println(commChest.getDescription(communityChestCard));
			for (Player playerPlaying : playerList) {
				playerPlaying.setMoney("-", 50);
			}

			player.setMoney("+", (playerList.size() - 1) * 50);
		} else if (communityChestCard == 5) {
			System.out.println(commChest.getTitle(communityChestCard));
			System.out.println(commChest.getDescription(communityChestCard));
			player.setMoney("+", 100);
		} else if (communityChestCard == 6) {
			System.out.println(commChest.getTitle(communityChestCard));
			System.out.println(commChest.getDescription(communityChestCard));
			player.setMoney("+", 20);
		} else if (communityChestCard == 7) {
			System.out.println(commChest.getTitle(communityChestCard));
			System.out.println(commChest.getDescription(communityChestCard));
			for (Player playerPlaying : playerList) {
				playerPlaying.setMoney("-", 10);
			}

			player.setMoney("+", (playerList.size() - 1) * 10);
		} else if (communityChestCard == 8) {
			System.out.println(commChest.getTitle(communityChestCard));
			System.out.println(commChest.getDescription(communityChestCard));
			player.setMoney("+", 100);
		} else if ((communityChestCard == 9) || (communityChestCard == 10)) {
			System.out.println(commChest.getTitle(communityChestCard));
			System.out.println(commChest.getDescription(communityChestCard));
			player.setMoney("-", 50);
		} else if (communityChestCard == 11) {
			System.out.println(commChest.getTitle(communityChestCard));
			player.setMoney("+", 25);
		} else if (communityChestCard == 12) {
			System.out.println(commChest.getTitle(communityChestCard));
			System.out.println(commChest.getDescription(communityChestCard));
			int totalHousesVal = 40 * player.getHouses();
			int totalHotelsVal = 115 * player.getHotels();

			player.setMoney("-", totalHousesVal + totalHotelsVal);
		} else if (communityChestCard == 13) {
			System.out.println(commChest.getTitle(communityChestCard));
			System.out.println(commChest.getDescription(communityChestCard));
			player.setMoney("+", 10);
		} else {
			System.out.println(commChest.getTitle(communityChestCard));
			player.setMoney("+", 100);
		}
	}

	public static void chanceOperations(Player player, ArrayList<Player> playerList, int chanceCard)
			throws FileNotFoundException, JSONException, IOException, ParseException, java.text.ParseException {
		Chance chanCards = new Chance();

		if (chanceCard == 0) {
			System.out.println(chanCards.getTitle(chanceCard));
			System.out.println(chanCards.getDescription(chanceCard));

			player.setPosition("Exact", 0);
			player.passGO();
		} else if (chanceCard == 1) {
			System.out.println(chanCards.getTitle(chanceCard));
			System.out.println(chanCards.getDescription(chanceCard));

			if (player.getPosition() > 23) {
				player.passGO();
			}

			player.setPosition("Exact", 23);
			landOnProperty(player, playerList, board[23]);
		} else if (chanceCard == 2) {
			System.out.println(chanCards.getTitle(chanceCard));
			System.out.println(chanCards.getDescription(chanceCard));

			if (player.getPosition() > 16) {
				player.passGO();
			}

			player.setPosition("Exact", 16);
			landOnProperty(player, playerList, board[16]);
		} else if (chanceCard == 3) {
			System.out.println(chanCards.getTitle(chanceCard));
			System.out.println(chanCards.getDescription(chanceCard));

			int currentPos = player.getPosition();

			if (currentPos > 28) {
				player.passGO();
				player.setPosition("Exact", 12);
				landOnUtility(player, playerList, board[12]);
			} else if (currentPos > 12) {
				player.setPosition("Exact", 28);
				landOnUtility(player, playerList, board[28]);
			} else {
				player.setPosition("Exact", 12);
				landOnUtility(player, playerList, board[12]);
			}
		} else if ((chanceCard == 4) || (chanceCard == 5)) {
			System.out.println(chanCards.getTitle(chanceCard));
			System.out.println(chanCards.getDescription(chanceCard));
			int currentPos = player.getPosition();

			if (currentPos > 35) {
				player.passGO();
				player.setPosition("Exact", 5);
				landOnRailroad(player, playerList, board[5]);
			} else if (currentPos > 25) {
				player.setPosition("Exact", 35);
				landOnRailroad(player, playerList, board[35]);
			} else if (currentPos > 15) {
				player.setPosition("Exact", 25);
				landOnRailroad(player, playerList, board[25]);
			} else if (currentPos > 5) {
				player.setPosition("Exact", 15);
				landOnRailroad(player, playerList, board[15]);
			} else {
				player.setPosition("Exact", 5);
				landOnRailroad(player, playerList, board[5]);
			}
		} else if (chanceCard == 6) {
			System.out.println(chanCards.getTitle(chanceCard));
			player.setMoney("+", 50);
		} else if (chanceCard == 7) {
			System.out.println(chanCards.getTitle(chanceCard));
			System.out.println(chanCards.getDescription(chanceCard));
			player.setGetOutOfJailFreeCards("+", 1);
		} else if (chanceCard == 8) {
			System.out.println(chanCards.getTitle(chanceCard));
			player.setPosition("Backwards", 3);
		} else if (chanceCard == 9) {
			System.out.println(chanCards.getTitle(chanceCard));
			System.out.println(chanCards.getDescription(chanceCard));
			player.setPosition("Exact", 30);
			player.setInJail(true);
		} else if (chanceCard == 10) {
			System.out.println(chanCards.getTitle(chanceCard));
			System.out.println(chanCards.getDescription(chanceCard));
			int totalHousesVal = 25 * player.getHouses();
			int totalHotelsVal = 100 * player.getHotels();

			player.setMoney("-", totalHousesVal + totalHotelsVal);
		} else if (chanceCard == 11) {
			System.out.println(chanCards.getTitle(chanceCard));
			System.out.println(chanCards.getDescription(chanceCard));
			if (player.getPosition() > 5) {
				player.passGO();
			}
			player.setPosition("Exact", 5);
		} else if (chanceCard == 12) {
			System.out.println(chanCards.getTitle(chanceCard));
			System.out.println(chanCards.getDescription(chanceCard));
			player.setPosition("Exact", 39);
		} else if (chanceCard == 13) {
			System.out.println(chanCards.getTitle(chanceCard));
			System.out.println(chanCards.getDescription(chanceCard));
			for (Player playerPlaying : playerList) {
				playerPlaying.setMoney("+", 50);
			}

			player.setMoney("-", (playerList.size() - 1) * 50);
		} else {
			System.out.println(chanCards.getTitle(chanceCard));
			System.out.println(chanCards.getDescription(chanceCard));
			player.setMoney("+", 150);
		}

	}
}