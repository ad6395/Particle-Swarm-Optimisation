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
    public Metadata solution()  {// to get all the data of the best thread we call Metadata
        Work w = new Work(target,  noOfInputs,  noOfParticles,  upperLimit,  lowerLimit,  noOfIterations,
                goldP,  diamondP,  mutualfundsP,  gPeriod,  dPeriod,  mPeriod,  bPeriod);

        int numberOfWorkers = 2;
        Thread[] workerThreads = new Thread[numberOfWorkers];//Defining threads
        for(int i=0; i<numberOfWorkers; i++) {
            workerThreads[i] = new Thread(w);//assigning task of execution to every thread
        }


        for(int i=0; i<numberOfWorkers; i++) {
            workerThreads[i].start();//starting all threads
        }
        for(int i=0; i<numberOfWorkers; i++) {
            try {
                workerThreads[i].join();//To keep the main thread running 
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }

        ArrayList<Metadata> md =w.getMetadata();//created arraylist to store the metadata(String threadName,Integer solution,Integer returns,boolean isOptimal) of each thread.
        Collections.sort(md);//sorting the threads according to solution as mentions in compareTo method
        System.out.println(md);
        return md.get(0);//used to print the least solution given by the thread after sorting




    }

}
