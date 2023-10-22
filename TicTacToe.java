import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.util.Random;

public class TicTacToe implements ActionListener {
    // Creating the main components of the game.
    JFrame frame = new JFrame(); // The main window.
    JPanel panel = new JPanel(); // A container to hold buttons in a grid layout.
    JLabel textfield = new JLabel(); // A label to display game messages.
    JButton[] buttons = new JButton[9]; // An array to hold the 9 buttons of the game.
    boolean player1_turn; // Keeps track of which player's turn it is.
    Random random = new Random(); // Random number generator for first turn.

    // Constructor for the TicTacToe class.
    TicTacToe() {
        // Setting up the main window.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.getContentPane().setBackground(new Color(50, 50, 50));
        frame.setLayout(new BorderLayout(0, 0));
        frame.setVisible(true);

        // Setting up the title label.
        textfield.setBackground(new Color(25, 25, 25));
        textfield.setForeground(new Color(25, 255, 0));
        textfield.setFont(new Font("Ink Free", Font.BOLD, 75));
        textfield.setHorizontalAlignment(JLabel.CENTER);
        textfield.setText("Tic-Tac-Toe");

        // Setting up the panel to hold the buttons in a 3x3 grid.
        panel.setLayout(new GridLayout(3, 3));
        panel.setBackground(new Color(150, 150, 150));

        // Creating and configuring the buttons.
        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            panel.add(buttons[i]);
            buttons[i].setFont(new Font("Times New Roman", Font.BOLD, 120));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
        }

        // Adding components to the main window.
        frame.add(textfield, BorderLayout.NORTH);
        frame.add(panel);

        // Starting the game with the first player's turn.
        firstTurn();
    }

    // ActionListener interface method to handle button clicks.
    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 9; i++) {
            if (e.getSource() == buttons[i]) {
                if (player1_turn) {
                    if (buttons[i].getText().equals("")) {
                        buttons[i].setForeground(new Color(255, 0, 0));
                        buttons[i].setText("X");
                        player1_turn = false;
                        textfield.setText("O turn");
                        check(); // Check if the game has been won.
                    }
                } else {
                    if (buttons[i].getText().equals("")) {
                        buttons[i].setForeground(new Color(0, 0, 255));
                        buttons[i].setText("O");
                        player1_turn = true;
                        textfield.setText("X turn");
                        check(); // Check if the game has been won.
                    }
                }
            }
        }
    }

    // Method to determine which player goes first.
    public void firstTurn() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        player1_turn = random.nextBoolean();
        textfield.setText(player1_turn ? "X turn" : "O turn");
    }

    // Method to check if a player has won.
    public void check() {
        int[][] winCombinations = {
                { 0, 1, 2 }, { 3, 4, 5 }, { 6, 7, 8 }, // rows
                { 0, 3, 6 }, { 1, 4, 7 }, { 2, 5, 8 }, // columns
                { 0, 4, 8 }, { 2, 4, 6 } // diagonals
        };

        for (int[] combination : winCombinations) {
            if (buttons[combination[0]].getText().equals("X") &&
                    buttons[combination[1]].getText().equals("X") &&
                    buttons[combination[2]].getText().equals("X")) {
                wins(combination[0], combination[1], combination[2], "X");
                return;
            } else if (buttons[combination[0]].getText().equals("O") &&
                    buttons[combination[1]].getText().equals("O") &&
                    buttons[combination[2]].getText().equals("O")) {
                wins(combination[0], combination[1], combination[2], "O");
                return;
            }
        }
    }

    // Method to handle what happens when a player wins.
    public void wins(int a, int b, int c, String player) {
        buttons[a].setBackground(Color.GREEN);
        buttons[b].setBackground(Color.GREEN);
        buttons[c].setBackground(Color.GREEN);
        for (int i = 0; i < 9; i++) {
            buttons[i].setEnabled(false);
        }
        textfield.setText(player + " wins");
    }

    // Main method to start the game.
    public static void main(String[] args) {
        new TicTacToe();
    }
}
