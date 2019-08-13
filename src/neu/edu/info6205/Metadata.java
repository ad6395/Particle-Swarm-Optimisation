package neu.edu.info6205;

public class Metadata implements Comparable {
   private String threadName;
   private Integer solution;
   private Integer returns;
   private boolean isOptimal;
   public Metadata(String threadName,Integer solution,Integer returns,boolean isOptimal){
      this.threadName = threadName;
      this.solution = solution;
      this.returns=returns;
      this.isOptimal = isOptimal;
   }
   public int compareTo(Object o){//compairing the threads w.r.t soluton i.e. the amount to be invested
      Metadata m = (Metadata)o;
      if (this.solution == null ||m.solution == null) {
      return 0;
    }
      return this.solution.compareTo(m.solution);
   }

   public Integer getSolution(){
       return this.solution;
   }
   public Integer getReturns(){
       return this.returns;
   }
    public boolean getisOptimal(){
       return this.isOptimal;
   }
   public String toString(){
      return "metadata: [ "+this.threadName+", "+this.solution+", "+this.returns+" ]";
   }

}
