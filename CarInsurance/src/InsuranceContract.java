import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class InsuranceContract {
    private Car car;
    private Insuree insuree;
    private boolean fullCoverage;
    private String feeDue;
    private double basePrice;

    public InsuranceContract(Car car, Insuree insuree, boolean fullCoverage, String feeDue) {
        this.car = car;
        this.insuree = insuree;
        this.fullCoverage = fullCoverage;
        this.feeDue = feeDue;
        calculateBasePrice();
    }

    private void calculateBasePrice() {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int carAge = currentYear - car.getYear();

        if (car.getPollutionIndex().equals("Euro 6")) {
            basePrice = 600 * 0.9; // cut the the insurance cost by 10% for Euro 6
        } else {
            basePrice = 750;

            if (car.getYear() < 1991) {
                basePrice += 0.2;
            }
            if (car.getYear() > 1990) {
                basePrice += (1990 - car.getYear()) * 10;
            }
        }

        if (insuree.getAge() > 30) {
            basePrice *= 0.8; // cut the insurance cost by 20% for insuree older than 30
        }

        // Lambda function 
        Function<String, Double> adjustForPollutionIndex = (pollutionIndex) -> {
            Map<String, Double> pollutionMultipliers = new HashMap<>();
            pollutionMultipliers.put("Euro 1", 1.8);
            pollutionMultipliers.put("Euro 2", 1.6);
            pollutionMultipliers.put("Euro 3", 1.4);
            pollutionMultipliers.put("Euro 4", 1.2);
            pollutionMultipliers.put("Euro 5", 1.0);
            pollutionMultipliers.put("Euro 6", 0.9);

            return pollutionMultipliers.getOrDefault(pollutionIndex, 1.0);
        };
        basePrice *= adjustForPollutionIndex.apply(car.getPollutionIndex());

        // price for historic vehicles
        if (car.getYear() < 1990) {
            basePrice *= 0.9;
        }

       
        switch (feeDue) {
            case "One Month":
                basePrice *= 1.5 / 12; 
                break;
            case "Six Months":
                basePrice *= 1.2 / 2; 
                break;
            case "Twelve Months":
              
                break;
        }

       
        if (fullCoverage) {
            basePrice *= 1.3;
        }

     
        if (car.getYear() < 1991 && car.getYear() >= 2024 - carAge) {
            basePrice -= basePrice * 0.01 * (1991 - car.getYear());
        }
    }

    public double calculateTotalSum() {
        double totalSum = basePrice;

        // Lambda function 2
        Function<Integer, Double> accidentMultiplier = (accidents) -> {
            if (accidents > 0 && accidents <= 4) {
                return Math.pow(1.2, accidents); 
            } else if (accidents > 4) {
                return Math.pow(1.2, 4); 
            } else {
                return 1.0;
            }
        };

        totalSum *= accidentMultiplier.apply(insuree.getAccidentCount());
        return totalSum;
    }
}
