package aed.secretaria;


import es.upm.aedlib.Position;
import es.upm.aedlib.positionlist.PositionList;
import es.upm.aedlib.positionlist.NodePositionList;


/**
 * Organizes the administration for an asignatura.
 * An asignatura has a name (e.g., "AED"), and the class
 * keeps track of matriculated alumnos, 
 * and of assigned notas for alumnos.
 */
public class AsignaturaAdmin {
    // Name of asignatura
    private String nombreAsignatura;

    // A list of pairs of matricula (String) and notas (integer)
    // Note that the nota should be null if no nota has
    // been assigned yet.
    private PositionList<Pair<String,Integer>> notas;

    /**
     * Creates an asignatura administration object, 
     * where the asignatura has a name (e.g. "AED"),
     * and which keeps tracks of matriculated alumnos (their matriculas), 
     * and assigned notas.
     */
    public AsignaturaAdmin(String nombreAsignatura) {
    	this.nombreAsignatura = nombreAsignatura;
    	this.notas = new NodePositionList<Pair<String,Integer>>();
    }
 
    /**
     * Returns the asignatura name.
     * @return the asignatura name.
     */
    public String getNombreAsignatura() {
	// Modificado
    	return nombreAsignatura;
    }
    
    /**
     * Adds a number of matriculas
     * to the asignatura. Returns a list of the matriculas
     * that were added,
     * i.e., the list of matriculas which had not previously been added.
     * @return a list with the matriculas added.
     */
    public PositionList<String> matricular(PositionList<String> matriculas) {
	// Hay que modificar este metodo
    	PositionList<String> aux = new NodePositionList<String>();

    	for(String alumno : matriculas){
    		if (!estaMatriculado(alumno)){
    			Pair<String, Integer> a = new Pair<String, Integer>(alumno, null);
    			notas.addLast(a);
    			aux.addLast(alumno);
    		}
    	}
    	
    	return aux;
    }

    /**
     * Removes a list of matriculas from the asignatura.
     * Returns a list with the matriculas which were successfully
     * removed. A matricula can be removed IF:
     * i) the matricula was previously added and has not been removed since, AND
     * ii) there is NO nota associated with the matricula.
     * @return a list with the matriculas that were removed.
     */
    public PositionList<String> desMatricular(PositionList<String> matriculas) {
	// Hay que modificar este metodo
    	PositionList<String> aux = new NodePositionList<String>();

    	for(String alumno : matriculas){
    		try{
    			if(tieneNota(alumno)){
    				notas.remove(positionOf(alumno));
        			aux.addLast(alumno);
    			}
    		}catch(InvalidMatriculaException e){} 		
    	}
    	
    	return aux;
    }
	
    /**
     * Checks whether a matricula has been added to the asignatura.
     * @return true if the matricula has been added, false otherwise.
     */
    public boolean estaMatriculado(String matricula) {
    	boolean matriculado = false;
        for (Pair<String, Integer> aux : notas) {
            if (matricula.equals(aux.getLeft())){
                matriculado = true;
            }
        }

        return matriculado;
    }

    /**
     * Checks whether a matricula has received a nota.
     * @return true if the matricula has a nota, and false otherwise.
     * @throws InvalidMatriculaException if the matricula 
     * has not been added to the asignatura (or was removed)
     */
    public boolean tieneNota(String matricula) throws InvalidMatriculaException {
    	if(!estaMatriculado(matricula)){
    		throw new InvalidMatriculaException();
    	}
    	boolean tieneNota = false;
        for (Pair<String, Integer> aux : notas) {
            if (!aux.getRight().equals(null)){
                tieneNota = true;
            }
        }
        return tieneNota;
    }

    /**
     * Returns the nota of a matricula.
     * @return the nota of an matrciula.
     * @throws InvalidMatriculaException if the matricula has no nota assigned,
     * or the matricula has not been added to the asignatura (or was removed).
     */
    public int getNota(String matricula) throws InvalidMatriculaException {
	// Hay que modificar este metodo
	return -1;
    }

    /**
     * Assigns a nota for a matricula.
     * @throws InvalidMatriculaException if the matricula has not
     * been added to the asignatura (or was removed).
     */
    public void setNota(String matricula, int nota) throws InvalidMatriculaException {
	// Hay que modificar este metodo
    }

    /**
    * Returns a list with the matriculas who has a nota between 
    * the minimum nota minNota (including it) and the maximum nota maxNota
    * (including it).
    * @return a list with the matriculas
    * with notas between (including) minNota...maxNota.
    */
    public PositionList<String> alumnosEnRango(int minNota, int maxNota) {
	// Hay que modificar este metodo
	return null;
    }

    /**
     * Calculates the average grade of the notas in the asignatura.
     * NOTE. Does not count alumnos (matriculas) that have not been assigned
     * a nota.
     * NOTE. The average grade for an empty set of notas is defined to be 0.
     * @return the average grade of the asignatura.
     */
    public double notaMedia() {
	return 10.0;
    }
    
    private Position<Pair<String, Integer>> positionOf(String matricula){
    	Position<Pair<String, Integer>> position = notas.first();
        for (Pair<String, Integer> aux : notas) {
            if (matricula.equals(aux.getLeft())){
               break;
            }
            position = notas.next(position);            
        }
        return position;    	
    }
}

