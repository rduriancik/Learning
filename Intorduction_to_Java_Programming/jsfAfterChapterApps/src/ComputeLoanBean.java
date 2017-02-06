import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * Created by robert on 6.2.2017.
 */

@ManagedBean
@SessionScoped
public class ComputeLoanBean {
    private double loanAmount;
    private double annualInterestRate;
    private int numberOfYears;

    public ComputeLoanBean() {
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public void setAnnualInterestRate(double annualInterestRate) {
        this.annualInterestRate = annualInterestRate;
    }

    public void setNumberOfYears(int numberOfYears) {
        this.numberOfYears = numberOfYears;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public double getAnnualInterestRate() {
        return annualInterestRate;
    }

    public int getNumberOfYears() {
        return numberOfYears;
    }

    /**
     * Find monthly payment
     */
    private double getMonthlyPayment() {
        double monthlyInterestRate = annualInterestRate / 1200;
        return loanAmount * monthlyInterestRate / (1 -
                (1 / Math.pow(1 + monthlyInterestRate, numberOfYears * 12)));
    }

    /**
     * Find total payment
     */
    private double getTotalPayment() {
        return getMonthlyPayment() * numberOfYears * 12;
    }

    public String getLoanResult() {
        StringBuilder result = new StringBuilder("<p>");
        result.append(String.format("Loan Amount: %.2f<br/>", loanAmount));
        result.append(String.format("Annual Interest Rate: %.2f<br/>", annualInterestRate));
        result.append(String.format("Number of Years: %d<br/>", numberOfYears));
        result.append(String.format("Monthly payment: %.2f<br/>", getMonthlyPayment()));
        result.append(String.format("Total Payment: %.2f</p>", getTotalPayment()));
        return result.toString();
    }

    public void clear() {
        loanAmount = 0;
        annualInterestRate = 0;
        numberOfYears = 0;
    }
}
