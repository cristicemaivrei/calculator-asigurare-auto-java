import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class database {
    private static database instance;
    private Connection con;
    private Statement stmt;

    private database() {
        try {
            String url = "jdbc:mysql://localhost:3306/ecar_insurance?serverTimezone=UTC";
            String username = "root";
            String password = ""; // Add your MySQL password if any
            con = DriverManager.getConnection(url, username, password);
            stmt = con.createStatement();
        } catch (SQLException e) {
            System.out.println("Connection error: " + e.getMessage());
        }
    }

    public Statement getStatement() {
        return stmt;
    }

    public void close() {
        try {
            if (stmt != null) stmt.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            System.out.println("Error closing the connection: " + e.getMessage());
        }
    }

    public static database getInstance() {
        if (instance == null) instance = new database();
        return instance;
    }

    public void addCar(String brand, String model, int year, String pollutionIndex, String fuelType, String bodyType, String name, int age, int accidentCount, int yearsDriving, String extras, String feeDue) {
        if (stmt == null) {
            System.out.println("Statement is not initialized.");
            return;
        }
        try {
            String insertQuery = "INSERT INTO `cars`(`brand`, `model`, `year`, `pollutionIndex`, `fuelType`, `bodyType`) VALUES ('"
                    + brand + "','" + model + "','" + year + "','" + pollutionIndex + "','" + fuelType + "','" + bodyType + "')";
            stmt.executeUpdate(insertQuery);
            System.out.println("Data inserted successfully.");
        } catch (SQLException ex) {
            System.out.println("Problem To Add Data: " + ex.getMessage());
        }
    }
    
    public void addCar(String brand, String model, int year, String pollutionIndex, String name, int age, int accidentCount, int yearsDriving, String extras, String feeDue, String bodyType, String insurancePrice) {
        if (stmt == null) {
            System.out.println("Statement is not initialized.");
            return;
        }
        try {
            String insertQuery = "INSERT INTO `carsandensurees`(`brand`, `model`, `year`, `pollutionIndex`, `name`, `age`, `accidentCount`, `yearsDriving`, `extras`, `feeDue`, `bodyType`, `insurancePrice`) VALUES ('"
                    + brand + "','" + model + "','" + year + "','" + pollutionIndex + "','" + name + "','" + age + "','" + accidentCount + "','" + yearsDriving + "','" + extras + "','" + feeDue + "','" + bodyType+ "','" + insurancePrice + "')";
            stmt.executeUpdate(insertQuery);
            System.out.println("Data inserted successfully.");
        } catch (SQLException ex) {
            System.out.println("Problem To Add Data: " + ex.getMessage());
        }
    }

    public List<Object[]> getAllRecords() {
        List<Object[]> data = new ArrayList<>();
        if (stmt == null) {
            System.out.println("Statement is not initialized.");
            return data;
        }
        try {
            String query = "SELECT * FROM carsandensurees";
            ResultSet rs = stmt.executeQuery(query);
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (rs.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    row[i - 1] = rs.getObject(i);
                }
                data.add(row);
            }
        } catch (SQLException e) {
            System.out.println("Problem To Fetch Data: " + e.getMessage());
        }
        return data;
    }
}
