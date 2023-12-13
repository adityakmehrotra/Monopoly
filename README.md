# Monopoly
The Monopoly Project allows users to play Monopoly on their computer with 2-4 players.

## Rules
The rules of the Monopoly Project mimic the rules of the Monopoly game by Hasbros.

Run Main.java to start the game!

Download all files before running

### Files
#### `Board.java`:
- Java file that represents the board that the Monopoly game is played on
- The Board is built using an array of Strings
  - Each String is a unique ID for the square on the board
#### `Chance.java`:
- Java file that represents the Chance class that hold all of the Chance operations
- The Chance cards are randomly placed into a Stack data structure
  - Each value in the stack holds an ID representing the Chance card
#### `CommunityChest.java`:
- Java file that represents the Community Chest class that hold all of the Community Chest operations
- The Community Chest cards are randomly placed into a Stack data structure
  - Each value in the stack holds an ID representing the Community Chest card
#### `Game.java`:
- 
#### `Player.java`:
- This java file is the Player class that holds all of the Player operations including:
  - Setting all of the player's holdings (Propertys, Cash, Get Out of Jail Free Cards, etc.)
#### `Property.java`:
- This java file is the Property class that holds all of the Property operations
- This file has functions that return the rent of the property, given which other propertys and houses/hotels the player has
#### `PropertyList.json`:
- 
#### `Railroad.java`:
- 
#### `Utility.java`:
- 
