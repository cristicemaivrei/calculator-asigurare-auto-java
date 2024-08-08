import javax.swing.*;
import java.awt.*;

public class HelpWindow extends JFrame {
    public HelpWindow() {
        setTitle("Insurance Calculation Help");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setText("Here's how the insurance is calculated:\n\n" +
                          "1. Base price is calculated based on the car's age, type.\n" +
                          "2. Adjustments are made based on the driver's age, driving history, and coverage type, in other words the price is adjusted by the risk index.\n" +
                          "3. Pollution index affects the insurance cost: higher index leads to higher premiums.\n\n" +
                          "For detailed information, please refer to our insurance policy. Phone No. 0773123123");

        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);
    }
}
