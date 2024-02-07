// Monopoly
// Created By: Aditya Mehrotra
// Last Update: 02/06/2024

import java.util.HashMap;
import java.io.FileReader;

import org.json.simple.parser.*;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.json.JSONException;

public class Utility {
	private String ID;
	private HashMap<String, Integer> colorList;

	public Utility(String ID, HashMap<String, Integer> colorList)
			throws FileNotFoundException, IOException, ParseException, JSONException {
		this.ID = ID;
		this.colorList = colorList;
	}

	public JSONObject getJSONObject() throws JSONException, FileNotFoundException, IOException, ParseException {
		JSONParser parser = new JSONParser();

		Object obj = parser.parse(new FileReader("/Users/aditya/eclipse-workspace/Monopoly/src/PropertyList.json"));
		JSONObject jsonObject = (JSONObject) obj;

		return jsonObject;
	}

	/**
	 * Sets the ID of the Utility given by the parameters.
	 * 
	 * @param board    Array of Strings, with each value being the ID of the square
	 * @param ID       ID before the change
	 * @param aspect   What part of the ID needs to be changed
	 * @param decision How to change the ID aspect
	 * @param amt      Amount aspect needs to be changed
	 */
	public void setID(String ID, String aspect, String decision) {
		String[] IDArray = ID.split("-");
		int numColors = Integer.parseInt(IDArray[2]);
		int numHouses = Integer.parseInt(IDArray[3]);
		String deed = IDArray[4];

		if (aspect.equals("Color")) {
			if (decision.equals("+")) {
				numColors += 1;
			} else {
				numColors -= 1;
			}
		} else if (aspect.equals("House")) {
			if (decision.equals("+")) {
				numHouses += 1;
			} else {
				numHouses -= 1;
			}
		} else {
			deed = decision;
		}

		ID = IDArray[0] + "-" + IDArray[1] + "-" + numColors + "-" + numHouses + "-" + deed;
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

	/**
	 * Gets the rent of the Utility given the player's properties.
	 * 
	 * @param player    Player
	 * @param ID        ID of the Utility
	 * @param colorList HashMap mapping Strings to Integers representing the colors
	 *                  and how much of them are owned by the player (includes
	 *                  amount of utilities owned by the player)
	 * @throws FileNotFounddException
	 * @throws JSONException
	 * @throws IOException
	 * @throws ParseException
	 */
	public long getRent(Player player, String ID, HashMap<String, Integer> colorList)
			throws FileNotFoundException, JSONException, IOException, ParseException {
		JSONObject jsonObject = getJSONObject();
		String[] IDArray = ID.split("-");

		int squareNumber = Integer.valueOf(IDArray[1]);
		int numberOfColors = 0;
		String rentType = "";

		JSONObject propertyDetails = (JSONObject) ((JSONArray) jsonObject.get("Board")).get(squareNumber);
		JSONObject rentDetails = (JSONObject) ((JSONArray) propertyDetails.get("Rent")).get(0);

		if (colorList.keySet().contains("Utility")) {
			numberOfColors = colorList.get("Utility");
		}

		if ((numberOfColors == 0) || (numberOfColors == 1)) {
			rentType = "Solo";
		} else {
			rentType = "Duo";
		}

		return (Long) ((JSONObject) rentDetails).get(rentType);
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
