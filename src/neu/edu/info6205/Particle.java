package neu.edu.info6205;
public class Particle {
    private int Data[];
    private int pBest = 0;
    private double Velocity = 0.0;

    public Particle(int noOfInputs) {
        Data = new int[noOfInputs];
        this.pBest = 0;
        this.Velocity = 0.0;
    }

    public int getData(int index) {
        return this.Data[index];
    }

    public void setData(int index, int value) {
        this.Data[index] = value;
        return;
    }

    public int getpBest() {
        return this.pBest;
    }

    public void setpBest(int value) {
        this.pBest = value;
        return;
    }

    public double getVelocity() {
        return this.Velocity;
    }

    public void setVelocity(double velocityScore) {
        this.Velocity = velocityScore;
        return;
    }
} // Particle