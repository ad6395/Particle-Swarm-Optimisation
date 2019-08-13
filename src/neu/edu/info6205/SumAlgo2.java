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
public class SumAlgo2 implements ICompoundInterstAlgo {
     public int sum(int index, int goldP, int diamondP, int mutualfundsP, int gPeriod, int dPeriod, int mPeriod,
                           int bPeriod, ArrayList<Particle> particles, int noOfInputs) {

       int diamond = 0;
		int diamond1 = 0;
		int gold = 0;
		int gold1 = 0;
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

		par = particles.get(index);// null is now replaced with the particle object and its properties.

		for (int i = 0; i < noOfInputs; i++) {
			sum += par.getData(i);// get the random numbers allocated to every index of the data array of the
		}
// particle and add it to total. Do it continously until all numbers are added.
// Calculation for the target
		gold = (sum * goldP) / 100;// gold investment
		gold1 = gold + (((gold * 20) / 100) * gPeriod);// gold investment
		diamond = (sum * diamondP) / 100;// diamond investment
		diamond1 = diamond + (((diamond * 30) / 100) * dPeriod);// diamond investment
		mutualFunds = (sum * mutualfundsP) / 100;
		mutualFunds1 = mutualFunds + (((mutualFunds * 14) / 100) * mPeriod);
// sum = sum + ((sum * 11) / 100);

// total = (sum + gold + diamond);

// total = sum - ((sum * 20) / 100);
		rem = sum - (gold1 + diamond1 + mutualFunds1);
		bankInterest = (((rem * 11) / 100) * bPeriod);
		total1 = sum + gold1 + diamond1 + mutualFunds1 + bankInterest;
		tax = (total1 * 20) / 100;
		total = total1 - tax;

		return total;
     }
    
}
