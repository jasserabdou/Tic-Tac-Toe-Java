# Tic-Tac-Toe Game

This is a Java program that implements a two-player Tic-Tac-Toe game using Swing GUI components.

## Getting Started

### Prerequisites

To run this program, you need to have Java installed on your system.

### Running the Program

1. Compile the Java file `TicTacToe.java`.
   ```
   javac TicTacToe.java
   ```

2. Run the compiled program.
   ```
   java TicTacToe
   ```

## How to Play

- The game is played on a 3x3 grid.
- Players take turns to place their marker (X or O) on an empty cell.
- The first player to get three of their markers in a row (horizontally, vertically, or diagonally) wins the game.
- If all cells are filled and no player has won, the game is declared a draw.

## Controls

- Click on an empty cell to place your marker.

## Features

- Randomly determines which player goes first.
- Provides feedback on whose turn it is.
- Detects and displays the winner or a draw.
- Allows players to start a new game after completion.

## Code Structure

- The program is organized using classes and methods for better readability and maintainability.
- GUI components like JFrame, JPanel, and JButton are used to create the game interface.
- Event handling is done using ActionListener interface for button clicks.
