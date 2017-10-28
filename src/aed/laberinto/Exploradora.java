package aed.laberinto;

import es.upm.aedlib.lifo.*;

import java.util.Iterator;



public class Exploradora {

/**
 * Busca un tesoro en el laberinto, empezando en el lugar
 * inicial: inicialLugar. 
 * @return un Objeto tesoro encontrado, o null, si ningun
 * tesoro existe en la parte del laberinto que es alcanzable
 * desde la posicion inicial.
 */	 
	public static Object explora(Lugar inicialLugar) {
		LIFO<Lugar> faltaPorExplorar = new LIFOList<Lugar>();
		Lugar current = inicialLugar;
		faltaPorExplorar.push(current);
		while (!faltaPorExplorar.isEmpty()){
			current = faltaPorExplorar.pop();
			if (!current.sueloMarcadoConTiza()){
				if (current.tieneTesoro()){
					return current.getTesoro();
			    }
				current.marcaSueloConTiza();
				Iterable<Lugar> ways = current.caminos();
			    Iterator<Lugar> it = ways.iterator();
			    while(it.hasNext()){
			    	Lugar next = it.next();
			    	if (!next.sueloMarcadoConTiza()){
			    		faltaPorExplorar.push(next);
			    	}
			    }
			}
		}
		return null;
	}
}

