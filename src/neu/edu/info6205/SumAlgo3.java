/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neu.edu.info6205;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Abhishek Dongare
 */
public class SumAlgo3 {
    public class SumAlgo1 implements ICompoundInterstAlgo {
      public int sum(int index, int goldP, int diamondP, int mutualfundsP, int gPeriod, int dPeriod, int mPeriod,
                           int bPeriod, ArrayList<Particle> particles, int noOfInputs) {

ArrayList<Integer> A =new ArrayList<>();
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
for(int i=2;i<13;i++){
    A.add(i);
}
		for (int i = 0; i < noOfInputs; i++) {
			sum += par.getData(i);// get the random numbers allocated to every index of the data array of the
		} // particle and add it to total. Do it continously until all numbers are added.

		// get the random numbers allocated to every index of the data array of the
		// particle and add it to total. Do it continously until all numbers are added.
		// Calculation for the target
		gold = (sum * goldP) / 100;// gold investment
		for (int i = 0; i < gPeriod; i++) {
			int r =A.get(i);
			// System.out.println("GOLD ROI for month " + i + " is " + randomInt);
			gold1 = gold + (((gold * r) / 100));
			gold += gold1;
		}
		diamond = (sum * diamondP) / 100;// gold investment
		for (int i = 0; i < dPeriod; i++) {
			int r =A.get(i);
			// System.out.println("Diamond ROI for month " + i + " is " + randomInt);
			diamond1 = diamond + (((diamond * r) / 100));
			diamond += diamond1;
		}
		mutualFunds = (sum * mutualfundsP) / 100;// gold investment
		for (int i = 0; i < dPeriod; i++) {
			int r =A.get(i);
			// System.out.println("MF ROI for month " + i + " is " + randomInt);
			mutualFunds1 = mutualFunds + (((mutualFunds * r) / 100));
			mutualFunds += mutualFunds1;
		}

		total1 = sum + gold + diamond + mutualFunds;
		tax = (total1 * 20) / 100;
		total = total1 - tax;

		return total;

	}}
}
