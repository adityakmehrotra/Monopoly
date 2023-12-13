// Monopoly
// Created By: Aditya Mehrotra
// Last Update: 12/12/2023

import java.util.HashMap;

import java.io.FileReader;

import org.json.simple.parser.*;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.json.JSONException;

public class Property {
	private String ID;
	private HashMap<String, Integer> colorList;

	public Property(String propertyID, HashMap<String, Integer> colorList)
			throws FileNotFoundException, IOException, ParseException, JSONException {
		this.ID = propertyID;
		this.colorList = colorList;
	}

	public JSONObject getJSONObject() throws JSONException, FileNotFoundException, IOException, ParseException {
		JSONParser parser = new JSONParser();

		Object obj = parser.parse(new FileReader("/Users/aditya/eclipse-workspace/Monopoly/src/PropertyList.json"));
		JSONObject jsonObject = (JSONObject) obj;

		return jsonObject;
	}

	/**
	 * Sets the ID of the Property given by the parameters.
	 * 
	 * @param board    Array of Strings, with each value being the ID of the square
	 * @param ID       ID before the change
	 * @param aspect   What part of the ID needs to be changed
	 * @param decision How to change the ID aspect
	 * @param amt      Amount aspect needs to be changed
	 */
	public String[] setID(String[] board, String ID, String aspect, String decision, int amt) {
		String[] IDArray = ID.split("-");

		int numColors = Integer.parseInt(IDArray[2]);
		int numHouses = Integer.parseInt(IDArray[3]);
		String deed = IDArray[4];

		if (aspect.equals("Color")) {
			if (decision.equals("+")) {
				numColors = amt;
			} else {
				numColors = amt;
			}
		} else if (aspect.equals("House")) {
			if (decision.equals("+")) {
				numHouses = amt;
			} else {
				numHouses = amt;
			}
		} else {
			deed = decision;
		}

		ID = IDArray[0] + "-" + IDArray[1] + "-" + numColors + "-" + numHouses + "-" + deed;
		board[Integer.parseInt(IDArray[1])] = ID;

		return board;
	}

	public String getID() {
		return ID;
	}

	public HashMap<String, Integer> getColorList() {
		return colorList;
	}

	public String getName(String ID) throws FileNotFoundException, JSONException, IOException, ParseException {
		JSONObject jsonObject = getJSONObject();
		String[] IDArray = ID.split("-");

		int squareNumber = Integer.valueOf(IDArray[1]);

		JSONObject propertyDetails = (JSONObject) ((JSONArray) jsonObject.get("Board")).get(squareNumber);

		return (String) ((JSONObject) propertyDetails).get("Name");
	}

	public String getAlternateName(String ID) throws FileNotFoundException, JSONException, IOException, ParseException {
		JSONObject jsonObject = getJSONObject();
		String[] IDArray = ID.split("-");

		int squareNumber = Integer.valueOf(IDArray[1]);

		JSONObject propertyDetails = (JSONObject) ((JSONArray) jsonObject.get("Board")).get(squareNumber);

		return (String) ((JSONObject) propertyDetails).get("Alternative Name");
	}

	public long getCost(String ID) throws FileNotFoundException, JSONException, IOException, ParseException {
		JSONObject jsonObject = getJSONObject();
		String[] IDArray = ID.split("-");

		int squareNumber = Integer.valueOf(IDArray[1]);

		JSONObject propertyDetails = (JSONObject) ((JSONArray) jsonObject.get("Board")).get(squareNumber);

		return (Long) ((JSONObject) propertyDetails).get("Cost");
	}

	public long getSquare(String ID) throws FileNotFoundException, JSONException, IOException, ParseException {
		JSONObject jsonObject = getJSONObject();
		String[] IDArray = ID.split("-");

		int squareNumber = Integer.valueOf(IDArray[1]);

		JSONObject propertyDetails = (JSONObject) ((JSONArray) jsonObject.get("Board")).get(squareNumber);

		return (Long) ((JSONObject) propertyDetails).get("Square");
	}

	public String getColor(String ID) throws JSONException, FileNotFoundException, IOException, ParseException {
		JSONObject jsonObject = getJSONObject();
		String[] IDArray = ID.split("-");

		int squareNumber = Integer.valueOf(IDArray[1]);

		JSONObject propertyDetails = (JSONObject) ((JSONArray) jsonObject.get("Board")).get(squareNumber);

		return (String) ((JSONObject) propertyDetails).get("Color");
	}

	public long getBuildingCost(String ID) throws JSONException, FileNotFoundException, IOException, ParseException {
		JSONObject jsonObject = getJSONObject();
		String[] IDArray = ID.split("-");

		int squareNumber = Integer.valueOf(IDArray[1]);

		JSONObject propertyDetails = (JSONObject) ((JSONArray) jsonObject.get("Board")).get(squareNumber);

		return (Long) propertyDetails.get("Building Cost");
	}

	/**
	 * Gets the rent of the Property given the player's specific details.
	 * 
	 * @param player    Player
	 * @param ID        ID of the Utility
	 * @param colorList HashMap mapping Strings to Integers representing the colors
	 *                  and how much of them are owned by the player
	 * @throws FileNotFounddException
	 * @throws JSONException
	 * @throws IOException
	 * @throws ParseException
	 */
	public long getRent(Player player, String ID, HashMap<String, Integer> colorList)
			throws JSONException, FileNotFoundException, IOException, ParseException {
		JSONObject jsonObject = getJSONObject();
		String[] IDArray = ID.split("-");

		int squareNumber = Integer.valueOf(IDArray[1]);
		int numberOfHouses = Integer.valueOf(IDArray[2]);
		int numberOfColors = 0;
		String rentType = "";
		Boolean colorSet = false;

		JSONObject propertyDetails = (JSONObject) ((JSONArray) jsonObject.get("Board")).get(squareNumber);
		JSONObject rentDetails = (JSONObject) ((JSONArray) propertyDetails.get("Rent")).get(0);

		if (colorList.containsKey(getColor(ID))) {
			numberOfColors = colorList.get(getColor(ID));
		}

		if (numberOfColors == 0) {
			rentType = "Solo";
		} else if ((getColor(ID).equals("Brown")) || (getColor(ID).equals("Dark Blue"))) {
			if (numberOfColors == 2) {
				colorSet = true;
			} else {
				rentType = "Solo";
			}
		} else {
			if (numberOfColors == 3) {
				colorSet = true;
			} else {
				rentType = "Solo";
			}
		}

		if (colorSet == true) {
			if (numberOfHouses == 1) {
				rentType = "1 House";
			} else if (numberOfHouses == 2) {
				rentType = "2 Houses";
			} else if (numberOfHouses == 3) {
				rentType = "3 Houses";
			} else if (numberOfHouses == 4) {
				rentType = "4 Houses";
			} else if (numberOfHouses == 5) {
				rentType = "Hotel";
			} else {
				rentType = "Color Set";
			}
		}

		return (Long) rentDetails.get(rentType);

	}

	public long getMortgage(String ID) throws FileNotFoundException, JSONException, IOException, ParseException {
		JSONObject jsonObject = getJSONObject();
		String[] IDArray = ID.split("-");

		int squareNumber = Integer.valueOf(IDArray[1]);

		JSONObject propertyDetails = (JSONObject) ((JSONArray) jsonObject.get("Board")).get(squareNumber);

		return (Long) ((JSONObject) propertyDetails).get("Mortgage");
	}

	public long getUnMortgage(String ID) throws FileNotFoundException, JSONException, IOException, ParseException {
		JSONObject jsonObject = getJSONObject();
		String[] IDArray = ID.split("-");

		int squareNumber = Integer.valueOf(IDArray[1]);

		JSONObject propertyDetails = (JSONObject) ((JSONArray) jsonObject.get("Board")).get(squareNumber);

		return (Long) ((JSONObject) propertyDetails).get("UnMortgage");
	}
}
