package aed.indexedlist;
import es.upm.aedlib.indexedlist.*;

public class Utils {
  public static <E> IndexedList<E> deleteRepeated(IndexedList<E> l) {
      IndexedList<E> lista = new ArrayIndexedList<E>();
      for (int i = 0; i < l.size(); i++){
    	  lista.add(i, l.get(i));
      }
      for (int i = 0; i < lista.size(); i++){
    	  for(int j = i + 1; j < (lista.size());){
    		  if(lista.get(i).equals(lista.get(j))){
    			  lista.removeElementAt(j);
    		  }else{
    			  j++;
    		  }
    	  }
      }
    return lista;
  }
}
