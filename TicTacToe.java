import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.util.Random;

public class TicTacToe implements ActionListener {
    private JFrame frame = new JFrame("Tic-Tac-Toe");
    private JPanel panel = new JPanel(new GridLayout(3, 3));
    private JLabel textfield = new JLabel("Tic-Tac-Toe", SwingConstants.CENTER);
    private JButton[] buttons = new JButton[9];
    private boolean player1_turn;
    private Random random = new Random();

    private TicTacToe() {
        initializeFrame();
        initializeButtons();
        startGame();
    }

    private void initializeFrame() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.getContentPane().setBackground(new Color(50, 50, 50));
        frame.setLayout(new BorderLayout(0, 0));
        textfield.setBackground(new Color(25, 25, 25));
        textfield.setForeground(new Color(25, 255, 0));
        textfield.setFont(new Font("Ink Free", Font.BOLD, 75));
        textfield.setText("Tic-Tac-Toe");
        textfield.setHorizontalAlignment(JLabel.CENTER);
        panel.setBackground(new Color(150, 150, 150));
        frame.add(textfield, BorderLayout.NORTH);
        frame.add(panel);
        frame.setVisible(true);
    }

    private void initializeButtons() {
        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            buttons[i].setFont(new Font("Times New Roman", Font.BOLD, 120));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
            panel.add(buttons[i]);
        }
    }

    private void startGame() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        player1_turn = random.nextBoolean();
        textfield.setText(player1_turn ? "X turn" : "O turn");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 9; i++) {
            if (e.getSource() == buttons[i] && buttons[i].getText().equals("")) {
                buttons[i].setText(player1_turn ? "X" : "O");
                buttons[i].setForeground(player1_turn ? new Color(255, 0, 0) : new Color(0, 0, 255));
                player1_turn = !player1_turn;
                textfield.setText(player1_turn ? "X turn" : "O turn");
                check();
            }
        }
    }

    private void check() {
        int[][] winCombinations = {
                { 0, 1, 2 }, { 3, 4, 5 }, { 6, 7, 8 }, // rows
                { 0, 3, 6 }, { 1, 4, 7 }, { 2, 5, 8 }, // columns
                { 0, 4, 8 }, { 2, 4, 6 } // diagonals
        };

        for (int[] combination : winCombinations) {
            if (buttons[combination[0]].getText().equals("X") &&
                    buttons[combination[1]].getText().equals("X") &&
                    buttons[combination[2]].getText().equals("X")) {
                handleWin(combination[0], combination[1], combination[2], "X");
                return;
            } else if (buttons[combination[0]].getText().equals("O") &&
                    buttons[combination[1]].getText().equals("O") &&
                    buttons[combination[2]].getText().equals("O")) {
                handleWin(combination[0], combination[1], combination[2], "O");
                return;
            }
        }

        if (allButtonsFilled()) {
            handleDraw();
        }
    }

    private boolean allButtonsFilled() {
        for (JButton button : buttons) {
            if (button.getText().equals("")) {
                return false;
            }
        }
        return true;
    }

    private void handleWin(int a, int b, int c, String player) {
        buttons[a].setBackground(Color.GREEN);
        buttons[b].setBackground(Color.GREEN);
        buttons[c].setBackground(Color.GREEN);
        disableButtons();
        textfield.setText(player + " wins");
    }

    private void handleDraw() {
        textfield.setText("Draw");
        disableButtons();
    }

    private void disableButtons() {
        for (JButton button : buttons) {
            button.setEnabled(false);
        }
    }

    public static void main(String[] args) {
        new TicTacToe();
    }
}
