// Monopoly
// Created By: Aditya Mehrotra
// Last Update: 02/06/2024

import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.parser.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.json.JSONException;

public class Player {
	private int money;
	private int position;
	private int houses;
	private int hotels;
	private Boolean inJail;
	private int jailCount;
	private int getOutOfJailFreeCards;

	private HashMap<String, Integer> colorList;
	private ArrayList<Property> propertyList;
	private ArrayList<Railroad> railroadList;
	private ArrayList<Utility> utilityList;

	private ArrayList<Property> mortgagePropertyList;
	private ArrayList<Railroad> mortgageRailroadList;
	private ArrayList<Utility> mortgageUtilityList;

	public Player(String[] board) {
		this.money = 1500;
		this.position = 0;
		this.houses = 0;
		this.hotels = 0;
		this.inJail = false;
		this.getOutOfJailFreeCards = 0;

		this.colorList = new HashMap<String, Integer>();
		this.propertyList = new ArrayList<Property>();
		this.railroadList = new ArrayList<Railroad>();
		this.utilityList = new ArrayList<Utility>();

		this.mortgagePropertyList = new ArrayList<Property>();
		this.mortgageRailroadList = new ArrayList<Railroad>();
		this.mortgageUtilityList = new ArrayList<Utility>();
	}

	public static void main(String[] args) {

	}

	public void setMoney(String sign, long value) {
		if (sign.equals("+")) {
			money += value;
		} else {
			money -= value;
		}
	}

	public int getMoney() {
		return money;
	}

	/**
	 * Changes the position of the player to the square given the decision to (move
	 * forward, move backwards, or move to an exact square), and by the amt.
	 * 
	 * @param decision String on whether to move Forward, Backward, or to an Exact
	 *                 square
	 * @param amt      Amount of squares to move
	 */
	public void setPosition(String decision, int amt) {
		System.out.println("Old Square: " + position);
		if (decision.equals("Forward")) {
			position += amt;

			if (position > 39) {
				position -= 40;
				passGO();
			}
		} else if (decision.equals("Backwards")) {
			position -= amt;

			if (position < 0) {
				position += 40;
			}
		} else if (decision.equals("Exact")) {
			position = amt;
		}
		System.out.println("New Square: " + position + "\n");
	}

	public int getPosition() {
		return position;
	}

	public void setHouses(String sign, int amt) {
		if (sign.equals("+")) {
			houses += amt;
		} else {
			houses -= amt;
		}
	}

	public int getHouses() {
		return houses;
	}

	public void setHotels(String sign, int amt) {
		if (sign.equals("+")) {
			hotels += amt;
		} else {
			hotels -= amt;
		}
	}

	public int getHotels() {
		return hotels;
	}

	public void setInJail(Boolean jail) {
		inJail = jail;
	}

	public Boolean getInJail() {
		return inJail;
	}

	public void setJailCount(int increment) {
		jailCount += increment;
	}

	public int getJailCount() {
		return jailCount;
	}

	public void clearJailCount() {
		jailCount = 0;
	}

	public void setGetOutOfJailFreeCards(String sign, int num) {
		if (sign.equals("+")) {
			getOutOfJailFreeCards += num;
		} else {
			getOutOfJailFreeCards -= num;
		}
	}

	public int getOutOfJailFreeCards() {
		return getOutOfJailFreeCards;
	}

	public void setColorList(String color, String sign, int num) {
		if (sign.equals("+")) {
			if (colorList.containsKey(color)) {
				colorList.put(color, colorList.get(color) + num);
			} else {
				colorList.put(color, num);
			}
		} else {
			if ((colorList.get(color) - num) == 0) {
				colorList.remove(color);
			} else {
				colorList.put(color, colorList.get(color) - num);
			}
		}
	}

	public HashMap<String, Integer> getColorList() {
		return colorList;
	}

	public void setPropertyList(Property property, String sign) throws JSONException, FileNotFoundException,
			IOException, ParseException, org.json.simple.parser.ParseException {
		if (sign.equals("+")) {
			propertyList.add(property);
		} else {
			propertyList.remove(property);
		}
	}

	public ArrayList<Property> getPropertyList() {
		return propertyList;
	}

	public void setRailroadList(Railroad railroad, String sign) throws JSONException, FileNotFoundException,
			IOException, ParseException, org.json.simple.parser.ParseException {
		if (sign.equals("+")) {
			railroadList.add(railroad);
		} else {
			railroadList.remove(railroad);
		}
	}

	public ArrayList<Railroad> getRailroadList() {
		return railroadList;
	}

	public void setUtilityList(Utility utility, String sign) throws JSONException, FileNotFoundException, IOException,
			ParseException, org.json.simple.parser.ParseException {
		if (sign.equals("+")) {
			utilityList.add(utility);
		} else {
			utilityList.remove(utility);
		}
	}

	public ArrayList<Utility> getUtilityList() {
		return utilityList;
	}

	public void setMortgagePropertyList(Property property, String decision) {
		if (decision.equals("Mortgage")) {
			propertyList.remove(property);
			mortgagePropertyList.add(property);
		} else {
			propertyList.add(property);
			mortgagePropertyList.remove(property);
		}
	}

	public ArrayList<Property> getMortgagePropertyList() {
		return mortgagePropertyList;
	}

	public void setMortgageRailroadList(Railroad railroad, String decision) {
		if (decision.equals("Mortgage")) {
			railroadList.remove(railroad);
			mortgageRailroadList.add(railroad);
		} else {
			railroadList.add(railroad);
			mortgageRailroadList.remove(railroad);
		}
	}

	public ArrayList<Railroad> getMortgageRailroadList() {
		return mortgageRailroadList;
	}

	public void setMortgageUtilityList(Utility utility, String decision) {
		if (decision.equals("Mortgage")) {
			utilityList.remove(utility);
			mortgageUtilityList.add(utility);
		} else {
			utilityList.add(utility);
			mortgageUtilityList.remove(utility);
		}
	}

	public ArrayList<Utility> getMortgageUtilityList() {
		return mortgageUtilityList;
	}

	public String[] checkColors(Property property, String[] board, int index) {
		if ((index == 1) || (index == 3)) {
			board = property.setID(board, board[1], "Color", "+", getColorList().get("Brown"));
			board = property.setID(board, board[3], "Color", "+", getColorList().get("Brown"));
		} else if ((index == 5) || (index == 15) || (index == 25) || (index == 35)) {
			board = property.setID(board, board[5], "Color", "+", getColorList().get("Railroad"));
			board = property.setID(board, board[15], "Color", "+", getColorList().get("Railroad"));
			board = property.setID(board, board[25], "Color", "+", getColorList().get("Railroad"));
			board = property.setID(board, board[35], "Color", "+", getColorList().get("Railroad"));
		} else if ((index == 6) || (index == 8) || (index == 9)) {
			board = property.setID(board, board[6], "Color", "+", getColorList().get("Light Blue"));
			board = property.setID(board, board[8], "Color", "+", getColorList().get("Light Blue"));
			board = property.setID(board, board[9], "Color", "+", getColorList().get("Light Blue"));
		} else if ((index == 12) || (index == 28)) {
			board = property.setID(board, board[12], "Color", "+", getColorList().get("Utility"));
			board = property.setID(board, board[28], "Color", "+", getColorList().get("Utility"));
		} else if ((index == 11) || (index == 13) || (index == 14)) {
			board = property.setID(board, board[11], "Color", "+", getColorList().get("Pink"));
			board = property.setID(board, board[13], "Color", "+", getColorList().get("Pink"));
			board = property.setID(board, board[14], "Color", "+", getColorList().get("Pink"));
		} else if ((index == 16) || (index == 18) || (index == 19)) {
			board = property.setID(board, board[16], "Color", "+", getColorList().get("Orange"));
			board = property.setID(board, board[18], "Color", "+", getColorList().get("Orange"));
			board = property.setID(board, board[19], "Color", "+", getColorList().get("Orange"));
		} else if ((index == 21) || (index == 23) || (index == 24)) {
			board = property.setID(board, board[21], "Color", "+", getColorList().get("Red"));
			board = property.setID(board, board[23], "Color", "+", getColorList().get("Red"));
			board = property.setID(board, board[24], "Color", "+", getColorList().get("Red"));
		} else if ((index == 26) || (index == 27) || (index == 29)) {
			board = property.setID(board, board[26], "Color", "+", getColorList().get("Yellow"));
			board = property.setID(board, board[27], "Color", "+", getColorList().get("Yellow"));
			board = property.setID(board, board[29], "Color", "+", getColorList().get("Yellow"));
		} else if ((index == 31) || (index == 32) || (index == 34)) {
			board = property.setID(board, board[31], "Color", "+", getColorList().get("Yellow"));
			board = property.setID(board, board[32], "Color", "+", getColorList().get("Yellow"));
			board = property.setID(board, board[34], "Color", "+", getColorList().get("Yellow"));
		} else if ((index == 37) || (index == 39)) {
			board = property.setID(board, board[37], "Color", "+", getColorList().get("Dark Blue"));
			board = property.setID(board, board[39], "Color", "+", getColorList().get("Dark Blue"));
		}

		return board;
	}

	public void passGO() {
		setMoney("+", 200);
	}
}
