# Monopoly

## Overview

The Monopoly Project is a fully playable cloud-based Monopoly game hosted on an AWS EC2 instance. The frontend is developed using React and TypeScript, offering an interactive and responsive user experience. The backend, engineered with Go, supports gameplay mechanics, property management, and multiplayer functionalities, ensuring an engaging platform for the Monopoly game, created by Hasbro.

## Features

- **Multiplayer Support**: Play with 2 to 4 players.
- **Game Components**: Includes classes representing different components of the game such as properties, players, utilities, railroads, community chest, and chance cards.
- **Interactive Gameplay**: Supports auctioning properties, trading among players, and other interactive elements of Monopoly.

## Rules

The game mimics the standard rules of Monopoly by Hasbro. Players move around the board, buying or trading properties, collecting rent, drawing cards, and building houses, while trying to bankrupt their opponents.

## Getting Started

### Prerequisites

You need to have Java installed on your computer to run this project. Visit [Java's official website](https://www.java.com/en/download/) to download and install it if you haven't already.

### Installation

1. **Clone the repository**:
   ```bash
   git clone https://github.com/yourusername/Monopoly.git
   cd Monopoly
   ```
2. **Compile the Java files**:
   
   You can compile the Java files using any Java-compatible IDE or directly from the command line:
   
   ```bash
   javac Game.java
   ```

3. **Run the game**:
   
   ```bash
   java Game
   ```

## Usage
After starting the game, follow the on-screen prompts to choose the number of players and start the game. Players will make choices through the console about their moves, purchases, and other actions during their turn.

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


## Contributing
Contributions to this project are welcome! Please fork the repository, make your changes, and submit a pull request for review.

### Last Updated
04/21/2024
