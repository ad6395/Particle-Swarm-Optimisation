package neu.edu.info6205;

import java.io.IOException;
import java.util.ArrayList;

public class Work implements Runnable {
    private int target;
    private int noOfInputs;
    private int noOfParticles;
    //private int maxVelocity = 10; // Maximum velocity change allowed.
    private int upperLimit;
    private int lowerLimit;
    private int noOfIterations;
    private int goldP;
    private int diamondP;
    private int mutualfundsP;
    private int gPeriod;
    private int dPeriod;
    private int mPeriod;
    private int bPeriod;
    

    public ArrayList<Metadata> getMetadata() {
        return metadata;
    }

    private ArrayList<Metadata> metadata = new ArrayList<>();

    public Work(int target, int noOfInputs, int noOfParticles, int upperLimit, int lowerLimit, int noOfIterations,
                int goldP, int diamondP, int mutualfundsP, int gPeriod, int dPeriod, int mPeriod, int bPeriod){
        this.target = target;
        this.noOfInputs = noOfInputs;
        this.noOfParticles = noOfParticles;
        this.upperLimit = upperLimit;
        this.lowerLimit = lowerLimit;
        this.noOfIterations = noOfIterations;
        this.goldP = goldP;
        this.diamondP = diamondP;
        this.mutualfundsP = mutualfundsP;
        this.gPeriod = gPeriod;
        this.dPeriod = dPeriod;
        this.mPeriod = mPeriod;
        this.bPeriod = bPeriod;

    }
    @Override
    public void run() {
        runAlgo();
    }

    private void runAlgo(){
        Algorithm a = null;
        try {
            System.out.println(Thread.currentThread().getName());
            a = new Algorithm( target,  noOfInputs,  noOfParticles,  upperLimit,  lowerLimit,  noOfIterations,
             goldP,  diamondP,  mutualfundsP,  gPeriod,  dPeriod,  mPeriod,  bPeriod);
            a.PSOAlgorithm();
            Solution opt = a.getOptimalSol();
            int x =opt.getReturnsIfNotOptimal();//storing getRetunsIfNotOptimal in x
            int y = opt.getSolution();//same as above but in y
            boolean z = opt.isOptimalSolution();//same as above but in z
            //System.out.println(opt);
            metadata.add(new Metadata(Thread.currentThread().getName(),y,x,z));//Since we created arraylist of type metadata and metadata constructor has 4 parameters i.eString threadName,Integer solution,Integer returns,boolean isOptimal, we need to add 4 paramters to the list
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
