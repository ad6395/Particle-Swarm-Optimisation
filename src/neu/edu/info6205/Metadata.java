package neu.edu.info6205;

public class Metadata implements Comparable {
   private String threadName;
   private Integer solution;

   public Metadata(String threadName,Integer solution){
      this.threadName = threadName;
      this.solution = solution;
   }
   public int compareTo(Object o){
      Metadata m = (Metadata)o;
      return this.solution.compareTo(m.solution);
   }

   public Integer getSolution(){
       return this.solution;
   }
   public String toString(){
      return "metadata: [ "+this.threadName+", "+this.solution+" ]";
   }

}
