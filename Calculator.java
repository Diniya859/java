import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame implements ActionListener {
    private JTextField displayField;
    private double firstNumber = 0;
    private double secondNumber = 0;
    private String operator = "";
    private boolean isOperatorClicked = false;

    public Calculator() {
        setTitle("Calculator");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        displayField = new JTextField();
        displayField.setFont(new Font("Arial", Font.BOLD, 24));
        displayField.setHorizontalAlignment(JTextField.RIGHT);
        displayField.setEditable(false);
        add(displayField, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4, 10, 10));

        String[] buttonLabels = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", "C", "=", "+"
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setFont(new Font("Arial", Font.BOLD, 20));
            button.addActionListener(this);
            buttonPanel.add(button);
        }

        add(buttonPanel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.charAt(0) >= '0' && command.charAt(0) <= '9') {
            if (isOperatorClicked) {
                displayField.setText(command);
                isOperatorClicked = false;
            } else {
                displayField.setText(displayField.getText() + command);
            }
        } else if (command.equals("C")) {
            displayField.setText("");
            firstNumber = 0;
            secondNumber = 0;
            operator = "";
            isOperatorClicked = false;
        } else if (command.equals("=")) {
            secondNumber = Double.parseDouble(displayField.getText());
            switch (operator) {
                case "+":
                    displayField.setText(String.valueOf(firstNumber + secondNumber));
                    break;
                case "-":
                    displayField.setText(String.valueOf(firstNumber - secondNumber));
                    break;
                case "*":
                    displayField.setText(String.valueOf(firstNumber * secondNumber));
                    break;
                case "/":
                    if (secondNumber != 0) {
                        displayField.setText(String.valueOf(firstNumber / secondNumber));
                    } else {
                        displayField.setText("Error");
                    }
                    break;
            }
            operator = "";
            isOperatorClicked = true;
        } else {
            if (!displayField.getText().isEmpty()) {
                firstNumber = Double.parseDouble(displayField.getText());
                operator = command;
                isOperatorClicked = true;
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Calculator calculator = new Calculator();
            calculator.setVisible(true);
        });
    }
}
