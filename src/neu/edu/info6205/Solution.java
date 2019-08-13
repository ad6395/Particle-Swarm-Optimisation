package neu.edu.info6205;

public class Solution {
    private boolean isOptimalSolution;
    private int solution;
    private int returnsIfNotOptimal;

    public Solution(int solution, int returnsIfNotOptimal,boolean isOptimalSolution) {
        this.isOptimalSolution = isOptimalSolution;
        this.solution = solution;
        this.returnsIfNotOptimal = returnsIfNotOptimal;
    }

    

    public boolean isOptimalSolution() {
        return isOptimalSolution;
    }

    public int getSolution() {
        return solution;
    }

    public int getReturnsIfNotOptimal() {
        return returnsIfNotOptimal;
    }

}
