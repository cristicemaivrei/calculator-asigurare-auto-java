import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class MainFrame extends JFrame {
    private JTextField brandTextField;
    private JTextField modelTextField;
    private JTextField yearTextField;
    private JComboBox<String> pollutionIndexComboBox;
    private JComboBox<String> fuelTypeComboBox;
    private JComboBox<String> bodyTypeComboBox;
    private JTextField extrasTextField;
    private JComboBox<String> feeDueComboBox;
    private JTextField nameTextField;
    private JTextField ageTextField;
    private JTextField accidentCountTextField;
    private JTextField yearsDrivingTextField;
    private JCheckBox fullCoverageCheckBox;
    private JButton calculateButton;
    private JButton helpButton;
    private JButton addToDatabaseButton;
    private JButton viewDatabaseButton;
    private JTextArea resultArea;
    private database db = database.getInstance();

    public MainFrame() {
        setTitle("E-Car Insurance");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(16, 2));

        panel.add(new JLabel("Brand:"));
        brandTextField = new JTextField();
        panel.add(brandTextField);

        panel.add(new JLabel("Model:"));
        modelTextField = new JTextField();
        panel.add(modelTextField);

        panel.add(new JLabel("Year:"));
        yearTextField = new JTextField();
        panel.add(yearTextField);

        panel.add(new JLabel("Pollution Index:"));
        pollutionIndexComboBox = new JComboBox<>(new String[]{"Euro 1", "Euro 2", "Euro 3", "Euro 4", "Euro 5", "Euro 6"});
        panel.add(pollutionIndexComboBox);

        panel.add(new JLabel("Fuel Type:"));
        fuelTypeComboBox = new JComboBox<>(new String[]{"Petrol", "Diesel"});
        panel.add(fuelTypeComboBox);

        panel.add(new JLabel("Body Type:"));
        bodyTypeComboBox = new JComboBox<>(new String[]{"Sedan", "Hatchback", "SUV", "Coupe", "Convertible", "Wagon", "Van"});
        panel.add(bodyTypeComboBox);

        panel.add(new JLabel("Extras:"));
        extrasTextField = new JTextField();
        panel.add(extrasTextField);

        panel.add(new JLabel("Fee Due:"));
        feeDueComboBox = new JComboBox<>(new String[]{"One Month", "Six Months", "Twelve Months"});
        panel.add(feeDueComboBox);

        panel.add(new JLabel("Insuree Name:"));
        nameTextField = new JTextField();
        panel.add(nameTextField);

        panel.add(new JLabel("Age:"));
        ageTextField = new JTextField();
        panel.add(ageTextField);

        panel.add(new JLabel("Accident Count:"));
        accidentCountTextField = new JTextField();
        panel.add(accidentCountTextField);

        panel.add(new JLabel("Years Driving:"));
        yearsDrivingTextField = new JTextField();
        panel.add(yearsDrivingTextField);

        panel.add(new JLabel("Full Coverage:"));
        fullCoverageCheckBox = new JCheckBox();
        panel.add(fullCoverageCheckBox);

        calculateButton = new JButton("Calculate Insurance");
        panel.add(calculateButton);

        helpButton = new JButton("Help");
        panel.add(helpButton);

        addToDatabaseButton = new JButton("Add to Database");
        panel.add(addToDatabaseButton);

        viewDatabaseButton = new JButton("View Database");
        panel.add(viewDatabaseButton);

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        panel.add(new JScrollPane(resultArea));

        add(panel);

        calculateButton.addActionListener(this::calculateInsurance);
        helpButton.addActionListener(this::openHelpWindow);
        addToDatabaseButton.addActionListener(this::addToDatabase);
        viewDatabaseButton.addActionListener(this::viewDatabase);

        setVisible(true);
    }

    private void calculateInsurance(ActionEvent e) {
        try {
            String brand = brandTextField.getText();
            String model = modelTextField.getText();
            int year = Integer.parseInt(yearTextField.getText());
            String pollutionIndex = (String) pollutionIndexComboBox.getSelectedItem();
            String fuelType = (String) fuelTypeComboBox.getSelectedItem();
            String bodyType = (String) bodyTypeComboBox.getSelectedItem();
            String extras = extrasTextField.getText();
            String feeDue = (String) feeDueComboBox.getSelectedItem();
            String name = nameTextField.getText();
            int age = Integer.parseInt(ageTextField.getText());
            int accidentCount = Integer.parseInt(accidentCountTextField.getText());
            int yearsDriving = Integer.parseInt(yearsDrivingTextField.getText());
            boolean fullCoverage = fullCoverageCheckBox.isSelected();

            Car car = new Car(brand, model, year, pollutionIndex, fuelType, bodyType);
            Insuree insuree = new Insuree(name, age, accidentCount, yearsDriving);
            InsuranceContract contract = new InsuranceContract(car, insuree, fullCoverage, feeDue);

            double totalSum = contract.calculateTotalSum();

            resultArea.setText("Insurance Cost for " + brand + " " + model + " (" + year + "): $" + totalSum);
        } catch (Exception ex) {
            resultArea.setText("Error in input: " + ex.getMessage());
        }
    }

    private void openHelpWindow(ActionEvent e) {
    HelpWindow helpWindow = new HelpWindow();
    helpWindow.setVisible(true);
}


    private void addToDatabase(ActionEvent e) {
        try {
            String brand = brandTextField.getText();
            String model = modelTextField.getText();
            int year = Integer.parseInt(yearTextField.getText());
            String pollutionIndex = (String) pollutionIndexComboBox.getSelectedItem();
            String fuelType = (String) fuelTypeComboBox.getSelectedItem();
            String bodyType = (String) bodyTypeComboBox.getSelectedItem();
            String extras = extrasTextField.getText();
            String feeDue = (String) feeDueComboBox.getSelectedItem();
            String name = nameTextField.getText();
            int age = Integer.parseInt(ageTextField.getText());
            int accidentCount = Integer.parseInt(accidentCountTextField.getText());
            int yearsDriving = Integer.parseInt(yearsDrivingTextField.getText());
            boolean fullCoverage = fullCoverageCheckBox.isSelected();

            // Calculate insurance price as needed
            double insurancePrice = calculateInsurancePrice(); // Implement this method as needed

            db.addCar(brand, model, year, pollutionIndex, name, age, accidentCount, yearsDriving, extras, feeDue, bodyType, String.valueOf(insurancePrice));
            resultArea.setText("Data added to database.");
        } catch (Exception ex) {
            resultArea.setText("Error adding to database: " + ex.getMessage());
        }
    }

    private void viewDatabase(ActionEvent e) {
        List<Object[]> records = db.getAllRecords();
        DatabaseViewFrame databaseViewFrame = new DatabaseViewFrame(records);
        databaseViewFrame.setVisible(true);
    }

    private double calculateInsurancePrice() {
        // Implement insurance price calculation
        return 0.0;
    }

    private class DatabaseViewFrame extends JFrame {
        public DatabaseViewFrame(List<Object[]> records) {
            setTitle("Database Records");
            setSize(800, 600);
            setLocationRelativeTo(null);

            String[] columnNames = {"id", "brand", "model", "year", "pollution norm", "name", "age", "accidents", "driving years", "extras", "fee due", "body type", "insurance price"};
            Object[][] data = records.toArray(new Object[0][]);

            JTable table = new JTable(data, columnNames);
            JScrollPane scrollPane = new JScrollPane(table);
            add(scrollPane, BorderLayout.CENTER);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::new);
    }
}
