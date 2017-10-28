package aed.recursion;

import es.upm.aedlib.Position;
import es.upm.aedlib.positionlist.*;


public class RecursiveUtils {


  /**
   * Return a^n. 
   * @return a^n.
   */
	public static int power(int a, int n) {
		if(n==0) return 1;
		if(n==1) return a;
		return a = a * power(a, n-1);
	}

  /**
   *  Returns true if the list parameter does not contain a null element.
   * @return true if the list does not contain a null element.
   */
	public static <E> boolean allNonNull(PositionList<E> l) {
		Position<E> pos = l.first();
		if (pos == null) return true;
		return recNullCheck(l, pos);
	}

  /**
   *  Returns the number of non-null elements in the parameter list.
   * @return the number of non-null elements in the parameter list.
   */
	public static <E> int countNonNull(PositionList<E> l) {
		int n = 0;
		Position<E> pos = l.first();
		if (pos == null) return 0;		
		return recNullCount(l, pos, n);
	}
  
	private static <E> boolean recNullCheck(PositionList<E> l, Position<E> pos){
		if (pos.element() == null) return false;
		if (l.next(pos) == null) return true;
		pos = l.next(pos);
		return recNullCheck(l, pos);
  	}
  
  	private static <E> int recNullCount(PositionList<E> l, Position<E> pos, int n){
  		if (pos.element() != null) n++;
  		if (l.next(pos) == null) return n;
  		pos = l.next(pos);
  		return recNullCount(l, pos, n);
	  
  	}
  
}
