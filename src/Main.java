import javax.swing.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> {
            Model model;
            if(args.length > 0){
                try {
                    model = new Model(args[0]);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                model = new Model();
            }
            View view = new View(model);
        });
    }
}
