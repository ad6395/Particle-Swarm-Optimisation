package neu.edu.info6205;

public class Investment {
    private int principle;
    private int period;

    public Investment(int principle, int period){
        this.principle = principle;
        this.period = period;
    }

    public double compoundInterest() {

        int currentPrinciple=principle;
        for (int i = 0; i < period; i++) {
           int rate=10;
           currentPrinciple=currentPrinciple+((currentPrinciple*rate)/100);

        }
        return currentPrinciple;
    }


    public static int getPrinciple(int amount, int percentage){
        return (amount * percentage)/100;
    }
}
