// Anthony Galczak - agalczak@cnm.edu
// CIS 2237 - Program 2

package mobiletipster.galczakp2.cis2237.com.galczakp2;

/**
 * Created by anthony on 9/21/2016.
 */
public class TipsterCalc {

    private double billAmount;
    private int numOfPeople;
    private double tipPercent;
    private double tipAmount;
    private double totalDue;
    private double amountPerDiner;

    // Default constructor
    public void TipsterCalc(){
        billAmount = 0.0;
        numOfPeople = 0;
        tipPercent = 0.0;
        tipAmount = 0.0;
        totalDue = 0.0;
        amountPerDiner = 0.0;
    }

    public void setInput(double billAmount, int numOfPeople, double tipPercent){
        this.setBillAmount(billAmount);
        this.setNumOfPeople(numOfPeople);
        this.setTipPercent(tipPercent);
        calculate();
    }

    private void calculate(){
        tipAmount = billAmount * (tipPercent / 100.0);
        totalDue = billAmount + tipAmount;
        amountPerDiner = totalDue / (double) numOfPeople;
    }

    /**
     * @param billAmount the billAmount to set
     */
    public void setBillAmount(double billAmount) {
        this.billAmount = billAmount;
    }

    public double getBillAmount(){
        return billAmount;
    }

    /**
     * @param numOfPeople the numOfPeople to set
     */
    public void setNumOfPeople(int numOfPeople) {
        this.numOfPeople = numOfPeople;
    }

    public int getNumOfPeople(){
        return numOfPeople;
    }

    /**
     * @return the tipPercent
     */
    public double getTipPercent() {
        return tipPercent;
    }

    /**
     * @param tipPercent the tipPercent to set
     */
    public void setTipPercent(double tipPercent) {
        this.tipPercent = tipPercent;
    }

    /**
     * @return the tipAmount
     */
    public double getTipAmount() {
        return tipAmount;
    }

    /**
     * @return the totalDue
     */
    public double getTotalDue() {
        return totalDue;
    }

    /**
     * @return the amountPerDiner
     */
    public double getAmountPerDiner() {
        return amountPerDiner;
    }

}