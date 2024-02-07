// Monopoly
// Created By: Aditya Mehrotra
// Last Update: 02/06/2024

import java.util.ArrayList;
import java.util.Collections;

import org.json.simple.parser.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.json.JSONException;

public class Board {
	private String[] board;

	public Board() throws FileNotFoundException, JSONException, IOException, ParseException {
		this.board = createBoard();
	}

	public static void main(String[] args) {

	}

	public String[] getBoard() {
		return board;
	}

	public void setBoard(String ID, int index) {
		board[index] = ID;
	}

	/**
	 * Categorizes square numbers into what type of card they are. Then, based off
	 * of that categorization, it generates an ID that is stored in an array with
	 * the position being the index of the square on the board.
	 * 
	 * @return Array with 40 values, each value being the ID of the squares on the
	 *         board.
	 * @throws FileNotFoundException
	 * @throws JSONException
	 * @throws IOException
	 * @throws ParseException
	 */
	public String[] createBoard() {
		String[] IDList = new String[40];

		ArrayList<Integer> specialCardPos = new ArrayList<Integer>();
		Integer[] specialCardList = new Integer[] { 0, 4, 10, 20, 30, 38 };
		Collections.addAll(specialCardPos, specialCardList);

		ArrayList<Integer> railroadCardPos = new ArrayList<Integer>();
		Integer[] railroadCardList = new Integer[] { 5, 15, 25, 35 };
		Collections.addAll(railroadCardPos, railroadCardList);

		ArrayList<Integer> utilityCardPos = new ArrayList<Integer>();
		Integer[] utilityCardList = new Integer[] { 12, 28 };
		Collections.addAll(utilityCardPos, utilityCardList);

		ArrayList<Integer> chanceCardPos = new ArrayList<Integer>();
		Integer[] chanceCardList = new Integer[] { 7, 22, 36 };
		Collections.addAll(chanceCardPos, chanceCardList);

		ArrayList<Integer> communityChestCardPos = new ArrayList<Integer>();
		Integer[] communityChestList = new Integer[] { 2, 17, 33 };
		Collections.addAll(communityChestCardPos, communityChestList);

		for (int i = 0; i < 40; i++) {
			if (specialCardPos.contains(i)) {
				IDList[i] = generateID("S", i);
			} else if (railroadCardPos.contains(i)) {
				IDList[i] = generateID("R", i);
			} else if (utilityCardPos.contains(i)) {
				IDList[i] = generateID("U", i);
			} else if (chanceCardPos.contains(i)) {
				IDList[i] = generateID("C", i);
			} else if (communityChestCardPos.contains(i)) {
				IDList[i] = generateID("CC", i);
			} else {
				IDList[i] = generateID("P", i);
			}
		}
		return IDList;
	}

	/**
	 * Generates an ID template specifically for the type of square. Sets each value
	 * in the ID to the starter value.
	 * 
	 * @param typeOfCard   Single-letter representing type of square.
	 * @param squareNumber Index of the square on the board (0-39 inclusive).
	 * @return Base-Standard ID.
	 */
	public String generateID(String typeOfCard, int squareNumber) {
		String finalProp = "";
		if (typeOfCard.equals("P") || typeOfCard.equals("R") || typeOfCard.equals("U")) {
			finalProp = "F";
		} else if (typeOfCard.equals("C") || typeOfCard.equals("CC")) {
			finalProp = "R";
		} else {
			finalProp = "S";
		}
		return typeOfCard + "-" + squareNumber + "-00-00-" + finalProp;
	}
}
