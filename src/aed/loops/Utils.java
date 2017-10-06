package aed.loops;

public class Utils {
  public static int maxNumRepeated(Integer[] l, Integer elem)  {
      int count = 0;
      int maxCount = 0;
	  for (int i = 0; i < l.length; i++){
    	  if (l[i].equals(elem)){
    		  count++;
    		  if(count > maxCount){
    			  maxCount = count;
    		  }
    	  }else {
    		  count = 0;
    	  }
      }
    return maxCount;  
  }
}
