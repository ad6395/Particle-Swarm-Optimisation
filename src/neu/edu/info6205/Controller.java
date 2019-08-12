package neu.edu.info6205;

import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller {
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
    public Controller(int target, int noOfInputs, int noOfParticles, int upperLimit, int lowerLimit, int noOfIterations,
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
    public int solution()  {
        Work w = new Work(target,  noOfInputs,  noOfParticles,  upperLimit,  lowerLimit,  noOfIterations,
                goldP,  diamondP,  mutualfundsP,  gPeriod,  dPeriod,  mPeriod,  bPeriod);

        int numberOfWorkers = 2;
        Thread[] workerThreads = new Thread[numberOfWorkers];
        for(int i=0; i<numberOfWorkers; i++) {
            workerThreads[i] = new Thread(w);
        }


        for(int i=0; i<numberOfWorkers; i++) {
            workerThreads[i].start();
        }
        for(int i=0; i<numberOfWorkers; i++) {
            try {
                workerThreads[i].join();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }

        ArrayList<Metadata> md =w.getMetadata();
        Collections.sort(md);
        System.out.println(md);
        return md.get(0).getSolution();//used to print the least solution given by the thread after sorting




    }

}
