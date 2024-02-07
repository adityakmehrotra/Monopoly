// Monopoly
// Created By: Aditya Mehrotra
// Last Update: 02/06/2024

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

import java.util.Random;
import java.io.FileReader;

import org.json.simple.parser.*;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.json.JSONException;

public class Chance {
	private Stack<Integer> chanceCardStack;

	public Chance() throws FileNotFoundException, IOException, ParseException, JSONException {
		this.chanceCardStack = generateStack();
	}

	public static void main(String[] args) {

	}

	public JSONObject getJSONObject() throws JSONException, FileNotFoundException, IOException, ParseException {
		JSONParser parser = new JSONParser();

		Object obj = parser.parse(new FileReader("/Users/aditya/eclipse-workspace/Monopoly/src/PropertyList.json"));
		JSONObject jsonObject = (JSONObject) obj;

		return jsonObject;
	}

	/**
	 * Generates a Stack of Integers (0-14) inclusive, and randomize the order. This
	 * stack is the index of all Chance cards.
	 * 
	 * @return Stack of Integers that randomizes 0-14 (inclusive)
	 */
	public Stack<Integer> generateStack() {
		Random random = new Random();
		Stack<Integer> chanceOrder = new Stack<Integer>();
		ArrayList<Integer> cards = new ArrayList<Integer>();

		Collections.addAll(cards, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14);

		while (cards.size() != 0) {
			int choice = random.nextInt(cards.size());
			chanceOrder.push(cards.get(choice));
			cards.remove(choice);
		}

		return chanceOrder;
	}

	public Stack<Integer> getChanceCardStack() {
		return chanceCardStack;
	}

	public int getChanceIndex() {
		int chanceIndex = chanceCardStack.pop();

		if (chanceCardStack.size() == 0) {
			chanceCardStack = generateStack();
		}

		return chanceIndex;
	}

	public String getID(String ID) {
		return ID;
	}

	public String getTitle(Integer index) throws FileNotFoundException, JSONException, IOException, ParseException {
		JSONObject jsonObject = getJSONObject();

		JSONObject propertyDetails = (JSONObject) ((JSONArray) jsonObject.get("Chance Cards")).get(index);

		return (String) ((JSONObject) propertyDetails).get("Title");
	}

	public String getDescription(Integer index)
			throws FileNotFoundException, JSONException, IOException, ParseException {
		JSONObject jsonObject = getJSONObject();

		JSONObject propertyDetails = (JSONObject) ((JSONArray) jsonObject.get("Chance Cards")).get(index);

		return (String) ((JSONObject) propertyDetails).get("Description");
	}

	public String getAlternateTitle(Integer index)
			throws FileNotFoundException, JSONException, IOException, ParseException {
		JSONObject jsonObject = getJSONObject();

		JSONObject propertyDetails = (JSONObject) ((JSONArray) jsonObject.get("Chance Cards")).get(index);

		return (String) ((JSONObject) propertyDetails).get("Alternate Title");
	}

	public String getAlternateDescription(Integer index)
			throws FileNotFoundException, JSONException, IOException, ParseException {
		JSONObject jsonObject = getJSONObject();

		JSONObject propertyDetails = (JSONObject) ((JSONArray) jsonObject.get("Chance Cards")).get(index);

		return (String) ((JSONObject) propertyDetails).get("Alternate Description");
	}
}
