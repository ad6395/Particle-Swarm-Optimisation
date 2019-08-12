package neu.edu.info6205;

import java.io.IOException;
import java.util.*;

public class Algorithm {

    private int target;
    private int noOfInputs;
    private int noOfParticles;
    private int maxVelocity = 10; // Maximum velocity change allowed.
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
    private ArrayList<Particle> particles = new ArrayList<>();
    private TreeMap<Integer, Integer> particleTreeMap = new TreeMap<>();
    private ArrayList<Integer> particles2 = new ArrayList<>();
    private ArrayList<Integer> particles3 = new ArrayList<>();
    private ResultWriter rw;
    int optimalParticle;


    public Algorithm(int target, int noOfInputs, int noOfParticles, int upperLimit, int lowerLimit, int noOfIterations,
                     int goldP, int diamondP, int mutualfundsP, int gPeriod, int dPeriod, int mPeriod, int bPeriod) throws IOException {
        this.rw = new ResultWriter();
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
        this.optimalParticle = -1;

        initialize();
    }

    private void initialize() {
        for (int i = 0; i < noOfParticles; i++) {
            Particle newParticle = new Particle(this.noOfInputs);// create multiple with respect to the maximum allowed particles
            int total = 0;
            for (int j = 0; j < noOfInputs; j++) {
                newParticle.setData(j, getRandomNumber(lowerLimit, upperLimit));// add random numbers equal to
                // noOfInputs in the data array of the
                // particle.
                total += newParticle.getData(j);// get the random number allocated at every index in the above step and
                // add it to total which is set to 0.
                // Keep on adding the random numbers until all numbers of the array of one
                // particle are added. Thus total represents the total of all numbers of the
                // data array.
            } // j
            newParticle.setpBest(total);// Initially for every particle we set the pBest as the total of all the random
            // numbers in the data array.
            particles.add(newParticle);// we add the particle to the list
        } // i
        return;
    }



    public void PSOAlgorithm() throws IOException {// main algorithm
        System.out.println(" the thread running is "+Thread.currentThread().getName()+" : "+this.hashCode());
        rw.writeLine(" the worker running the thread is "+Thread.currentThread().getName()+"\n");
        int gBest = 0;
        int gBestTest = 0;
        Particle par = null;
        int Iteration = 0;
        boolean done = false;
        int dataSum = 0;
        long x = System.nanoTime();



        while (!done) {
            // Two conditions can end this loop:
            // if the maximum number of Iterations allowed has been reached, or,
            // if the Target value has been found.

            if (Iteration < noOfIterations) {
//this loop is used just to represent the data in the console in a format

                for (int i = 0; i < noOfParticles; i++) {
                    rw.writeLine("Addition of these values  ");
                    par = particles.get(i);// its is used to get the properties of each particle
                    for (int j = 0; j < noOfInputs; j++) {

                        if (j < noOfInputs - 1) {
                            dataSum += par.getData(j);

                            rw.writeLine(par.getData(j) + " + ");
                        } else {
                            dataSum += par.getData(j);// storing sum of 3 inputs
                            particleTreeMap.put(
                                    sum(i, goldP, diamondP, mutualfundsP, gPeriod, dPeriod, mPeriod, bPeriod), dataSum);
                            rw.writeLine(par.getData(j) + " = " + dataSum
                                    + " after making the investment as mentioned, will give amount $: ");
                            dataSum = 0;

                        }
                    }
                    particles2.add(sum(i, goldP, diamondP, mutualfundsP, gPeriod, dPeriod, mPeriod, bPeriod));
                    rw.writeLine(sum(i, goldP, diamondP, mutualfundsP, gPeriod, dPeriod, mPeriod, bPeriod) + "\n");
                    if (sum(i, goldP, diamondP, mutualfundsP, gPeriod, dPeriod, mPeriod, bPeriod) == target) {
                        done = true;
                        this.optimalParticle=i;
                        System.out.println(this.hashCode()+" Optimal solution testing Particle no : "+Thread.currentThread().getName()+":   "+i);
                        break;

                    }
                } // i

                gBestTest = Best();// we store the result of Best function in this variable. It is basically the
                // index of the particle.
                par = particles.get(gBest);// at the beginning it is 0.
                // if(any particle's pBest value is better than the gBest value, make it the new
                // gBest value.
                if (Math.abs(target
                        - sum(gBestTest, goldP, diamondP, mutualfundsP, gPeriod, dPeriod, mPeriod, bPeriod)) < Math
                        .abs(target - sum(gBest, goldP, diamondP, mutualfundsP, gPeriod, dPeriod, mPeriod,
                                bPeriod))) {// we compare the target-sum of
                    // the gBestTestth index
                    // particle and gBesth index
                    // particle which is 0 for the
                    // first iteration.Incase the If
                    // condition is satisfied we
                    // replace gBest with gBestTest
                    // and this process continues
                    // until particle at every
                    // indexes are compared. At the
                    // end we get the best gBest
                    // which is the index of the
                    // best particle.
                    gBest = gBestTest;
                }

                getVelocity(gBest);// to get the velocity of gBestth index particle

                updateparticles(gBest);// to update the gBestth index particle

                rw.writeLine("Iteration number: " + Iteration);

                Iteration += 1;

            } else {
                done = true;
            }

            long y = System.nanoTime();
            long z = y - x;
            rw.writeLine("Time Taken in Nano Seconds:" + z);

        }

    }

    private int Best() {
        // Returns an array index.
        int temp = 0;
        boolean newtemp = false;//
        boolean done = false;// condition for the while loop

        while (!done) {
            newtemp = false;
            for (int i = 0; i < noOfParticles; i++) {
                if (i != temp) { // Avoid self-comparison.
                    // The Best has to be in relation to the Target.
                    if (Math.abs(
                            target - sum(i, goldP, diamondP, mutualfundsP, gPeriod, dPeriod, mPeriod, bPeriod)) < Math
                            .abs(target - sum(temp, goldP, diamondP, mutualfundsP, gPeriod, dPeriod, mPeriod,
                                    bPeriod))) {// in
                        // the
                        // first
                        // iteration,
                        // target
                        // -
                        // sum of first particle is compared
                        // with the sum of the tempth
                        // particle which is initially set
                        // to 0. In case the if condition is
                        // satisfied, the tempth particle is
                        // replaced by the first particle.
                        // This is done to compare the sum
                        // of each particle with all other
                        // particles and the best particle
                        // is found out if its target-sum is
                        // least of all

                        temp = i;
                        newtemp = true;
                    }
                }
            }

            if (newtemp == false) {
                done = true;
            }
        }

        return temp;
    }


    private void getVelocity(int gBestindex) {// it is used to return the index of the particle with best
        // velocity after compairing with all particles. VERIFY THIS
        // POINT! ASK ANIMESH
        // from Kennedy & Eberhart(1995).
        // vx[][] = vx[][] + 2 * rand() * (pbestx[][] - presentx[][]) +
        // 2 * rand() * (pbestx[][gbest] - presentx[][])

        int testResults = 0;
        int bestResults = 0;
        double vValue = 0.0;
        Particle par = null;

        bestResults = sum(gBestindex, goldP, diamondP, mutualfundsP, gPeriod, dPeriod, mPeriod, bPeriod);// we calculate
        // the sum
        // of all
        // random
        // numbers
        // in the
        // data
        // array
        // of the
        // gBestindexth index particle

        for (int i = 0; i < noOfParticles; i++) {
            testResults = sum(i, goldP, diamondP, mutualfundsP, gPeriod, dPeriod, mPeriod, bPeriod);// we store the sum
            // of all random
            // numbers in the
            // data array of the
            // gBestindexth
            // index particle of every particle
            par = particles.get(i);// retrieve particle from the list.
            vValue = par.getVelocity() + 2 * new Random().nextDouble() * (par.getpBest() - testResults)
                    + 2 * new Random().nextDouble() * (bestResults - testResults);// calculation of the velocity

            if (vValue > maxVelocity) {
                par.setVelocity(maxVelocity);
            } else if (vValue < -maxVelocity) {
                par.setVelocity(-maxVelocity);
            } else {
                par.setVelocity(vValue);
            }
        }
        return;
    }

    private void updateparticles(int gBestindex) {
        Particle gBParticle = particles.get(gBestindex);// it is used to get the properties of the particle at
        // gBestindexth index

        for (int i = 0; i < noOfParticles; i++) {
            for (int j = 0; j < noOfInputs; j++) {
                if (particles.get(i).getData(j) != gBParticle.getData(j)) {// This condition is used to compare the
                    // values at every index in the data array
                    // of every particle with the values at
                    // every index in the data array of the
                    // particle at gBestindexth index
                    particles.get(i).setData(j,
                            particles.get(i).getData(j) + (int) Math.round(particles.get(i).getVelocity()));// if they
                    // are not
                    // equal,
                    // then this
                    // formula
                    // is used
                    // to update
                    // the
                    // values
                }
            } // j

            // Check pBest value.
            int total = sum(i, goldP, diamondP, mutualfundsP, gPeriod, dPeriod, mPeriod, bPeriod);
            if (Math.abs(target - total) < particles.get(i).getpBest()) {
                particles.get(i).setpBest(total);
            }

        } // i
        return;
    }


    private static int getRandomNumber(int low, int high) {
        int randomInteger = 0;
        Random random = new Random();

        randomInteger = random.nextInt((high - low) + 1) + low;

        // System.out.println("Random Integer in Java: " + randomInteger);

        return randomInteger;

        // return (int) ((high - low) * new Random().nextDouble() + low);
    }

    private  int sum(int index, int goldP, int diamondP, int mutualfundsP, int gPeriod, int dPeriod, int mPeriod,
			int bPeriod) {

		int diamond = 0;
		int diamond1 = 0;
		int gold = 0;
		int gold1 = 0;
		int gold2 = 0;
		int mutualFunds = 0;
		int mutualFunds1 = 0;
		int sum = 0;
		int sum1 = 0;
		int total1 = 0;
		int tax = 0;
		int total = 0;
		int bankInterest = 0;
		int rem = 0;
		double interest = 0;
		Particle par = null;// create a new instance of particle and set to null
		Random randomGenerator = new Random();
		par = particles.get(index);// null is now replaced with the particle object and its properties.

		for (int i = 0; i < noOfInputs; i++) {
			sum += par.getData(i);// get the random numbers allocated to every index of the data array of the
		} // particle and add it to total. Do it continously until all numbers are added.
//Calculation for the target
		gold = (sum * goldP) / 100;// gold investment
		for (int i = 0; i < gPeriod; i++) {
			int randomInt = randomGenerator.nextInt(50) + 1;
			System.out.println("GOLD ROI for month " + i + " is " + randomInt);
			gold1 = gold + (((gold * 10) / 100));
			gold += gold1;
		}
		diamond = (sum * diamondP) / 100;// gold investment
		for (int i = 0; i < dPeriod; i++) {
			int randomInt = randomGenerator.nextInt(50) + 1;
			System.out.println("Diamond ROI for month " + i + " is " + randomInt);
			diamond1 = diamond + (((diamond * 10) / 100));
			diamond += diamond1;
		}
		mutualFunds = (sum * mutualfundsP) / 100;// gold investment
		for (int i = 0; i < dPeriod; i++) {
			int randomInt = randomGenerator.nextInt(50) + 1;
			System.out.println("MF ROI for month " + i + " is " + randomInt);
			mutualFunds1 = mutualFunds + (((mutualFunds * 10) / 100));
			mutualFunds += mutualFunds1;
		}
		//rem = sum - (sum + gold + diamond + mutualFunds);
//gold1 = gold + (((gold * 20) / 100) * gPeriod);// gold investment
//diamond = (sum * diamondP) / 100;// diamond investment
//diamond1 = diamond + (((diamond * 30) / 100) * dPeriod);// diamond investment
//mutualFunds = (sum * mutualfundsP) / 100;
//mutualFunds1 = mutualFunds + (((mutualFunds * 14) / 100) * mPeriod);
////sum = sum + ((sum * 11) / 100);
//
////total = (sum + gold + diamond);
//
////total = sum - ((sum * 20) / 100);
//

//total1 = sum + gold1 + diamond1 + mutualFunds1 + bankInterest;
		total1 = sum + gold + diamond + mutualFunds;
		tax = (total1 * 20) / 100;
		total = total1 - tax;

		return total;

	


       /* int diamond = 0;
        int diamond1 = 0;
        int gold = 0;
        int gold1 = 0;
        int mutualFunds = 0;
        int mutualFunds1 = 0;
        int totalAmountInAParticle = 0;
        int total1 = 0;
        int tax = 0;
        double total = 0;
        int bankInterest = 0;
        int rem = 0;
        Particle par = null;// create a new instance of particle and set to null

        par = particles.get(index);// null is now replaced with the particle object and its properties.

        for (int i = 0; i < noOfInputs; i++) {
            totalAmountInAParticle += par.getData(i);// get the random numbers allocated to every index of the data array of the
        }

        int goldPrinciple = Investment.getPrinciple(totalAmountInAParticle,goldP);
        int diamondPrinciple = Investment.getPrinciple(totalAmountInAParticle,diamondP);
        int mutualfundsPrinciple = Investment.getPrinciple(totalAmountInAParticle,mutualfundsP);
        int bankPrinciple = totalAmountInAParticle-(goldPrinciple+diamondPrinciple+mutualfundsPrinciple);

        Investment goldInvestment = new Investment(goldPrinciple, gPeriod);
        Investment diamondInvestment = new Investment(diamondPrinciple, dPeriod);
        Investment mutualFundsInvestment = new Investment(mutualfundsPrinciple, mPeriod);
        Investment bankInvestment = new Investment(bankPrinciple,bPeriod);
        double totalIncome = goldInvestment.compoundInterest()+diamondInvestment.compoundInterest()+
                mutualFundsInvestment.compoundInterest()+bankInvestment.compoundInterest();
        total = totalIncome-((totalIncome*20)/100);

        return (int)total;
*/
    }


    public int getOptimalSol() throws IOException {
        System.out.println("**************************************************");
        int sol;
        if(optimalParticle != -1){
            rw.writeLine("\nParticle Number:" + optimalParticle+"\n");
            int sum = 0;
            for (int j = 0; j < noOfInputs; j++) {
                sum += particles.get(optimalParticle).getData(j);
                if (j < noOfInputs - 1) {
                    rw.writeLine(particles.get(optimalParticle).getData(j) + " + ");
                } else {
                    rw.writeLine(particles.get(optimalParticle).getData(j) + " = " + sum);
                }
            }
            sol = sum;
        }
        else {
            System.out.println("optimal solution not found.");
            int a = 0;
            int b = 0;
            // logic to get the next closest value
            int[] store = new int[particles2.size()];

            for (int i = 0; i < particles2.size(); i++) {
                store[i] = particles2.get(i);
            }
            Arrays.sort(store);
            rw.writeLine("Store 1:" + Arrays.toString(store));
            int x = target + 5;
            int y = target - 5;
            for (int i = 0; i < store.length; i++) {

                if (y < store[i] && store[i] < x) {
                    particles3.add(store[i]);
                }
            }
            rw.writeLine("LIST 3" + particles3);
            int[] store2 = new int[particles3.size()];
            for (int i = 0; i < particles3.size(); i++) {
                store2[i] = particles3.get(i);
            }
            Arrays.sort(store2);
            for (int i = 0; i < store2.length; i++) {
                int l = store2.length - 1;
                a = store2[l];
            }
            for (Map.Entry<Integer, Integer> entry : particleTreeMap.entrySet()) {
                int key = entry.getKey();
                int value = entry.getValue();
                if (a == key) {
                    b = value;
                }
                // System.out.println(key + " => " + value);
            }
            // System.out.println("Store 2" + Arrays.toString(store2));
            rw.writeLine("\nSolution cannot be found!Please enter different value!");
            rw.writeLine("\nInstead of " + target + " you can set target as:" + a + " and invest $:" + b);
            // System.out.println("HashMap:" + particleMap);
            sol = b;

        }

        rw.closeStream();
        return sol;

    }



}
