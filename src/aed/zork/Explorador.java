package aed.zork;

import java.util.Iterator;

import aed.laberinto.Lugar;
import es.upm.aedlib.lifo.LIFO;
import es.upm.aedlib.lifo.LIFOList;
import es.upm.aedlib.positionlist.*;



public class Explorador {
  
	public static Pair<Object,PositionList<Lugar>> explora(Lugar inicialLugar) {
		Lugar current = inicialLugar;
		PositionList<Lugar> camino = new NodePositionList<Lugar>();
		camino.addLast(current);
		Pair<Object,PositionList<Lugar>> res = null;
		if (!current.sueloMarcadoConTiza()){
			if (current.tieneTesoro()){
				Object tesoro = current.getTesoro();
				res = new Pair<Object,PositionList<Lugar>>(tesoro, camino);
				return res;
		    }
			current.marcaSueloConTiza();
			Iterable<Lugar> ways = inicialLugar.caminos();
			Iterator<Lugar> it = ways.iterator();
			while (it.hasNext()){
				Lugar next = it.next();
				if (!next.sueloMarcadoConTiza()){
					res = explora(next);
				}
				if (res.getLeft() != null){
					
				}
				
			}
			
		}
		
	}
  

}

//LIFO<Lugar> faltaPorExplorar = new LIFOList<Lugar>();
//Lugar current = inicialLugar;
//faltaPorExplorar.push(current);
//while (!faltaPorExplorar.isEmpty()){
//	current = faltaPorExplorar.pop();
//	if (!current.sueloMarcadoConTiza()){
//		if (current.tieneTesoro()){
//			return current.getTesoro();
//	    }
//		current.marcaSueloConTiza();
//		Iterable<Lugar> ways = current.caminos();
//	    Iterator<Lugar> it = ways.iterator();
//	    while(it.hasNext()){
//	    	Lugar next = it.next();
//	    	if (!next.sueloMarcadoConTiza()){
//	    		faltaPorExplorar.push(next);
//	    	}
//	    }
//	}
//}
//return null;	
