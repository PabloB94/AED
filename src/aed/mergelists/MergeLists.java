package aed.mergelists;

import es.upm.aedlib.Position;
import es.upm.aedlib.indexedlist.ArrayIndexedList;
import es.upm.aedlib.indexedlist.IndexedList;
import es.upm.aedlib.positionlist.NodePositionList;
import es.upm.aedlib.positionlist.PositionList;
import java.util.Comparator;


public class MergeLists {

  /**
   * Merges two lists ordered using the comparator cmp, returning
   * a new ordered list.
   * @returns a new list which is the ordered merge of the two argument lists
   */
  public static <E> PositionList<E> merge(final PositionList<E> l1, final PositionList<E> l2, final Comparator<E> comp) {
	  
      PositionList<E> ans = new NodePositionList<E>();
      int empty = 0;
      
      if(l1.isEmpty()) empty = empty+1;
      if(l2.isEmpty()) empty = empty+2;
      switch(empty){
      	case 0:  
      		Position<E> i = l1.first();
      		Position<E> j = l2.first();
      		Position<E> pointer = ans.first();
          
      		do{ 	  
      			if (ans.isEmpty()){          	  
      				if(comp.compare(i.element(), j.element()) < 0){
      					ans.addFirst(i.element());
      					i = l1.next(i);                      
      				}else{                	  
      					ans.addFirst(j.element());
      					j = l2.next(j);
      				}    		  
      				pointer = ans.first();
                  
      			}else{            	  
      				if(comp.compare(i.element(), j.element()) < 0){
      					ans.addAfter(pointer, i.element());
      					i = l1.next(i);                      
      				}else {                	  
      					ans.addAfter(pointer, j.element());
      					j = l2.next(j);
      				}           
      				pointer = ans.next(pointer);
      			}
      		}while (i != null && j != null);
          
      		while (i != null){        	  
      			ans.addAfter(pointer, i.element());
      			i = l1.next(i);
      			pointer = ans.next(pointer);
      		}
      
      		while (j != null){        	  
      			ans.addAfter(pointer, j.element());
      			j = l2.next(j);
      			pointer = ans.next(pointer);
      		}
      		break;
      	case 1:
      		ans = clone(l2);
      		break;
      	case 2:
      		ans = clone(l1);
      		break;      	
      	}
    return ans;
  }

  /**
   * Merges two lists ordered using the comparator cmp, returning
   * a new ordered list.
   * @returns a new list which is the ordered merge of the two argument lists
   */
  public static <E> IndexedList<E> merge(final IndexedList<E> l1, final IndexedList<E> l2, final Comparator<E> comp) {
      IndexedList<E> ans = new ArrayIndexedList<E>();
      int empty = 0;
      
      if(l1.isEmpty()) empty = empty+1;
      if(l2.isEmpty()) empty = empty+2;
      switch(empty){
       case 0:        	  
    	   int i = 0;
    	   int j = 0;
    	   int temp = 0;
        
    	   do{ 	  
    		   if (comp.compare(l1.get(i), l2.get(j)) < 0){
    			   ans.add(temp, l1.get(i));
    			   i++;                  
    		   }else{
    			   ans.add(temp, l2.get(j));
    			   j++;
    		   }    	  
    		   temp++;              
    	   }while (i < l1.size() && j < l2.size());
    	   
    	   for(;i < l1.size();i++){        	  
    		   ans.add(temp, l1.get(i));
    		   temp++;
    	   }
    	   
    	   for(; j < l2.size(); j++){
    		   ans.add(temp, l2.get(j));
    		   temp++;
    	   }
    	   break;
       case 1:
    	   ans = clone(l2);
    	   break;
       case 2:
    	   ans = clone(l1);
    	   break;
      }
      return ans;
  }
  
  
  	private static <E> PositionList<E> clone(final PositionList<E> list){
  		PositionList<E> ans = new NodePositionList<E>();
  		Position<E> i = list.first();
        while(i != null){
        	ans.addLast(i.element());
            i = list.next(i);
        }
        return ans;
  	}
  	
  	private static <E> IndexedList<E> clone(final IndexedList<E> list){
  		IndexedList<E> ans = new ArrayIndexedList<E>();
  		for(int i = 0; i < list.size(); i++){
  			ans.add(i, list.get(i));
  		}
  		return ans;
  	}
  }
