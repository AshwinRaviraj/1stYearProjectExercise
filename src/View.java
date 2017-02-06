import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class View implements Observer {
    Model model;
    JFrame window;
    JTextField userInput;
    JTextArea userOutput;

    public View(Model model){
        this.model = model;
        model.addObserver(this);
        window = new JFrame("Awesome Address GUI thingy!!!");
        window.setLayout(new BorderLayout());
        userInput = new JTextField();
        userInput.setPreferredSize(new Dimension(500,30));
        userInput.addActionListener(a -> {
            model.add(Address.parse(userInput.getText()));
            userInput.setText("");
        });
        window.add(userInput, BorderLayout.NORTH);
        userOutput = new JTextArea();
        userOutput.setEditable(false);
        userOutput.setPreferredSize(new Dimension(500,500));
        window.add(userOutput, BorderLayout.CENTER);
        window.pack();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        update(null, null);
    }

    @Override
    public void update(Observable o, Object arg) {
        StringBuilder sb = new StringBuilder();
        for (Address address : model) {
            sb.append(address).append("\n\n");
        }
        userOutput.setText(sb.toString());
        window.repaint();
    }
}
