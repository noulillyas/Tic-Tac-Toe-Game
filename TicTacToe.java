import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToe extends JFrame implements ActionListener {

    private final char X = 'X';
    private final char O = 'O';
    private char currentPlayer;
    private JButton[] buttons = new JButton[9];
    private JButton resetButton; // New reset button

    public TicTacToe() {
        super("Tic-Tac-Toe");
        setLayout(new GridLayout(4, 3)); // Increased rows to accommodate the reset button
        currentPlayer = X;

        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton();
            buttons[i].setForeground(new Color(147,112,219));
            buttons[i].setBackground(new Color(0,0,0));
            buttons[i].setFont(new Font("Ariel", Font.BOLD, 200));
            buttons[i].addActionListener(this);
            add(buttons[i]);
        }

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Panel to hold the reset button
        resetButton = new JButton("New Game");
        resetButton.setFont(new Font("Times New Roman", Font.BOLD, 80));
        resetButton.setHorizontalAlignment(JLabel.CENTER);
        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetGame();
            }
        });
        panel.setBackground(Color.WHITE);
        panel.add(resetButton);

        panel.setBounds(50,600, 700,200);
        add(panel, BorderLayout.SOUTH); // Add panel with reset button to the frame

        setSize(700, 850); // Adjusted frame size to accommodate the reset button
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();
        if (clickedButton.getText().isEmpty()) {
            clickedButton.setText(String.valueOf(currentPlayer));
            checkWinner();
            currentPlayer = (currentPlayer == X) ? O : X;

            // Display whose turn it is

        }
    }

    private void checkWinner() {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (allEqual(buttons[i * 3], buttons[i * 3 + 1], buttons[i * 3 + 2])) {
                declareWinner(buttons[i * 3].getText());
                return;
            }
        }

        // Check columns
        for (int i = 0; i < 3; i++) {
            if (allEqual(buttons[i], buttons[i + 3], buttons[i + 6])) {
                declareWinner(buttons[i].getText());
                return;
            }
        }

        // Check diagonals
        if (allEqual(buttons[0], buttons[4], buttons[8])) {
            declareWinner(buttons[0].getText());
            return;
        }
        if (allEqual(buttons[2], buttons[4], buttons[6])) {
            declareWinner(buttons[2].getText());
            return;
        }

        // Check for tie
        boolean isTie = true;
        for (JButton button : buttons) {
            if (button.getText().isEmpty()) {
                isTie = false;
                break;
            }
        }
        if (isTie) {
            declareWinner("It's a Tie!");
        }
    }

    private boolean allEqual(JButton btn1, JButton btn2, JButton btn3) {
        String text = btn1.getText();
        return !text.isEmpty() && text.equals(btn2.getText()) && text.equals(btn3.getText());
    }

    private void declareWinner(String message) {
        if (message.equals("It's a Tie!")) {
            JOptionPane.showMessageDialog(this, message);
        } else {
            JOptionPane.showMessageDialog(this, message + " Wins!");
        }
        resetGame();
    }

    private void resetGame() {
        for (JButton button : buttons) {
            button.setText("");
        }
        currentPlayer = X;
    }

    public static void main(String[] args) {
        new TicTacToe();
    }
}
