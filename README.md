# Monopoly
The Monopoly Project allows users to play Monopoly on their computer with 2-4 players.

## Rules
The rules of the Monopoly Project mimic the rules of the Monopoly game by Hasbros.

## Run the Game
- Download the required files:
  - `Board.java`
  - `Chance.java`
  - `CommunityChest.java`
  - `Game.java`
  - `Player.java`
  - `Property.java`
  - `PropertyList.json`
  - `Railroad.java`
  - `Utility.java`
- You can run the `Game.java` file through your IDE/Console.

## Files
### `Board.java`:
- Java file that represents the board that the Monopoly game is played on
- The Board is built using an array of Strings
  - Each String is a unique ID for the square on the board
### `Chance.java`:
- Java file that represents the Chance class that hold all of the Chance operations
- The Chance cards are randomly placed into a Stack data structure
  - Each value in the stack holds an ID representing the Chance card
### `CommunityChest.java`:
- Java file that represents the Community Chest class that hold all of the Community Chest operations
- The Community Chest cards are randomly placed into a Stack data structure
  - Each value in the stack holds an ID representing the Community Chest card
### `Game.java`:
- This is the main Java file that gets information from the user
  - How many players are playing? (2-4), etc.
- This file creates an instance of the Player class for each player
- This file also holds all of the interplayer functions
  - Auctioning properties, trading, etc.
### `Player.java`:
- This java file is the Player class that holds all of the Player operations including:
  - Setting all of the player's holdings (Propertys, Cash, Get Out of Jail Free Cards, etc.)
### `Property.java`:
- This java file is the Property class that holds all of the Property operations
- This file has functions that return the rent of the property, given which other propertys and houses/hotels the player has
### `PropertyList.json`:
- This is a JSON file holding all of the squares on the board
  - Each square has all of its attributes
- This JSON file also holds all of the Chance and Community Chest Cards
  - Each card has the title and description of the card
### `Railroad.java`:
- This java file is the Railroad class that holds all of the Railroad operations
- This file has functions that return the rent of the railroad, given which other railroads the player has
### `Utility.java`:
- This java file is the Utility class that holds all of the Utility operations
- This file has functions that return the rent of the utility, given which other utilities the player has, and what the roll of the dice was (as this changes the value of rent)

## Last Updated
03/02/2024
