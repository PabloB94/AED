package aed.zork;

import java.util.Iterator;

import aed.laberinto.Lugar;
import es.upm.aedlib.lifo.LIFO;
import es.upm.aedlib.lifo.LIFOList;
import es.upm.aedlib.positionlist.*;



public class Explorador {
  
	public static Pair<Object,PositionList<Lugar>> explora(Lugar inicialLugar) {
		PositionList<Lugar> camino  = new NodePositionList<Lugar>();
		camino.addFirst(inicialLugar);
		Pair<Object,PositionList<Lugar>> res = null;
		if (!inicialLugar.sueloMarcadoConTiza()){
			if (inicialLugar.tieneTesoro()){
				Object tesoro = inicialLugar.getTesoro();
				res = new Pair<Object,PositionList<Lugar>>(tesoro, camino);
				return res;
		    }
			inicialLugar.marcaSueloConTiza();
			Iterable<Lugar> ways = inicialLugar.caminos();
			Iterator<Lugar> it = ways.iterator();
			while (it.hasNext()){
				Lugar next = it.next();
				if (!next.sueloMarcadoConTiza()){
					res = explora(next);					
				}
				if (res != null){
					camino = res.getRight();
					camino.addFirst(inicialLugar);
					res = new Pair<Object,PositionList<Lugar>>(res.getLeft(), camino);	
					return res;
				}				
			}
		}
		return null;
	}
		
}
  
