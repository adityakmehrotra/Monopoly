# Monopoly

## Overview

The Monopoly Project is a fully playable cloud-based Monopoly game hosted on an AWS EC2 instance. The frontend is developed using React and TypeScript, offering an interactive user experience. The backend, engineered with Go, supports gameplay mechanics, property management, and multiplayer functionalities, ensuring an engaging platform for the Monopoly game, created by Hasbro.

## Features

- **Interactive Frontend**: Developed with React and TypeScript for a responsive and dynamic user interface.
- **Advanced Backend**: Engineered in Go, implementing features such as property management, sophisticated gameplay rules, and multiplayer mechanics.
- **Cloud Hosting**: Deployed on an AWS EC2 instance for reliable and scalable access.

## Tech Stack

- **Frontend**: React and TypeScript
- **Backend**: Go
- **Hosting**: AWS EC2

## Getting Started

### Prerequisites
- Node.js and npm
- Go
- AWS account and AWS CLI configured

### Installation

#### Clone the Repository

```bash
git clone https://github.com/yourusername/monopoly-web-app.git
cd monopoly-web-app
```

#### Set Up the Frontend

Navigate to the frontend directory and install dependencies:
```bash
cd frontend
npm install
```

#### Set Up the Backend

Ensure Go is installed and set up, then navigate to the backend directory:
```bash
cd ../backend
go build
```

### Running the Application

#### Start the Backend Server
```bash
./backend
```

#### Start the Frontend

Open another terminal and start the frontend:
```bash
cd frontend
npm start
```

The frontend should now be accessible on http://localhost:3000.

## Rules

The game mimics the standard rules of Monopoly by Hasbro. Players move around the board, buying or trading properties, collecting rent, drawing cards, and building houses, while trying to bankrupt their opponents.

## Usage
Navigate to the deployed URL or local address to start playing Monopoly with friends or AI opponents, utilizing full gameplay features and real-time updates.

## Contributing
Contributions to this project are welcome! Please fork the repository, make your changes, and submit a pull request for review.

## Acknowledgments
Thanks to all the contributors who have invested their time and effort in improving this project.
Special thanks to AWS for hosting services.

## Contact
For any queries, you can reach out at `adi1.mehrotra@gmail.com`.

### Last Updated
06/24/2024
