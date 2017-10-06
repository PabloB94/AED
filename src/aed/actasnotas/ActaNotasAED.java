package aed.actasnotas;

import java.util.Comparator;

import es.upm.aedlib.indexedlist.*;

public class ActaNotasAED implements ActaNotas {
	
	IndexedList<Calificacion> calificaciones = new ArrayIndexedList<Calificacion>();
	int numNotas = 0;
	/**
	 * Adds a new calificacion to the database.
	 * @throws CalificacionAlreadyExistsException
	 * if a calificacion with the same matricula already existed
	 * in the database.
	 */
	public void addCalificacion(String nombre, String matricula, int nota)
			throws CalificacionAlreadyExistsException{
		
		Calificacion aux = new Calificacion(nombre, matricula, nota);
		for(int i = 0; i < calificaciones.size(); i++){
			if (!calificaciones.isEmpty() && matricula.equals(calificaciones.get(i).getMatricula())){
				throw new CalificacionAlreadyExistsException();				
			}
		}
		calificaciones.add(numNotas, aux);
		numNotas++;
				
	}

	/**
	 * Modifies the nota for a calificacion with the given matricula
	 * (there can never be more than one calificacion for a given matricula).
	 * @throws InvalidMatriculaExcepcion if there is no calificacion for
	 * the specified matricula.
	 */
	public void updateNota(String matricula, int nota)
			throws InvalidMatriculaException{
		int index = buscarMatricula(matricula);
		if (index == -1){
			throw new InvalidMatriculaException();
		}
		calificaciones.get(index).setNota(nota);
	}

	/**
	 * Deletes the calificacion for the student with the given matricula.
	 * @throws InvalidMatriculaExcepcion if there is no calificacion for
	 * the specified matricula.
	 */
	public void deleteCalificacion(String matricula)
			throws InvalidMatriculaException{
		int index = buscarMatricula(matricula);
		if (index == -1){
			throw new InvalidMatriculaException();
		}
		calificaciones.removeElementAt(index);
		numNotas--;
	}

	/**
	 * Returns the calificacion for the student with the given matricula.
	 * @return the calificacion for the student with the given matricula.
	 * @throws InvalidMatriculaExcepcion if there is no calificacion for
	 * the specified matricula.
	 */
	public Calificacion getCalificacion(String matricula)
			throws InvalidMatriculaException{
		int index = buscarMatricula(matricula);
		Calificacion ret = null;
		if (index == -1){
			throw new InvalidMatriculaException();
		}
		ret = calificaciones.get(index);
		return ret;		
	}

	/**
	 * Returns a SORTED indexed list with the calificaciones in the
	 * database, where the
	 * sorting order is given by the Comparator cmp. Note that calificaciones
	 * should be sorted from smaller to bigger values (using cmp).
	 * @return a sorted list of the calificacions in the database.
	 */
	public IndexedList<Calificacion> getCalificaciones(Comparator<Calificacion> cmp){
		IndexedList<Calificacion> calificacionesOrdenadas = new ArrayIndexedList<Calificacion>();
		if (!calificaciones.isEmpty()){
		calificacionesOrdenadas.add(0, calificaciones.get(0));
		boolean placed;
			for(int i = 1; i < calificaciones.size(); i++){
				placed = false;
				for(int j = 0; j < calificacionesOrdenadas.size() && !placed ; j++){
					if (cmp.compare(calificaciones.get(i), calificacionesOrdenadas.get(j)) < 0) {
						calificacionesOrdenadas.add(j, calificaciones.get(i));
						placed = true;
					}
				}
				if (!placed){
					calificacionesOrdenadas.add(calificacionesOrdenadas.size(), calificaciones.get(i));
					placed = true;
				}
			}
		}
		return calificacionesOrdenadas;		
	}

	/**
	 * Returns a list with the calificacions that have a nota greater
	 * or equal to the notaMinima argument.
	 * Note that there is no requirement that the returned
	 * list has to be sorted. This method should NOT remove calificaciones
	 * from the database.
	 */
	public IndexedList<Calificacion> getAprobados(int notaMinima){
		IndexedList<Calificacion> aprobados = new ArrayIndexedList<Calificacion>();
		int numAprobados = 0;
		for(int i = 0; i < calificaciones.size(); i++){
			if (calificaciones.get(i).getNota() >= notaMinima){
				aprobados.add(numAprobados, calificaciones.get(i));
				numAprobados ++;
			}
		}
		return aprobados;		
	}
	
	private int buscarMatricula(String matricula){
		int index = -1;
		for (int i = 0; i < calificaciones.size(); i++){
			if (calificaciones.get(i).getMatricula().equals(matricula)){
				index = i;				
			}
		}
		return index;
	}
	
}
