package aed.iteradores;


import es.upm.aedlib.positionlist.*;
import es.upm.aedlib.indexedlist.*;
import java.util.Iterator;


/**
 * Administra una coleccion de asignaturas.
 */
public class Secretaria {
    private Iterable<AsignaturaAdmin> asignaturas;

  /**
   * Empieza administrar una coleccion de asignaturas.
   */
    public Secretaria(Iterable<AsignaturaAdmin> asignaturas) {
    	this.asignaturas = asignaturas;
    }

    private AsignaturaAdmin findAsignatura(String asignatura) {
    	Iterator<AsignaturaAdmin> it = asignaturas.iterator();
    	AsignaturaAdmin res = null; 
    	while (it.hasNext() && res == null) {
    		AsignaturaAdmin admin = it.next();
    		if (admin.getNombreAsignatura().equals(asignatura)) {
    			res = admin;
    		}
    	}
    	return res;
    }

    private AsignaturaAdmin getAsignatura(String asignatura)
	throws InvalidAsignaturaException {
    	AsignaturaAdmin admin = findAsignatura(asignatura);
    	if (admin == null) throw new InvalidAsignaturaException();
    		else return admin;
    }

   
  /**
   * Matricula una coleccion de alumnos (representados por el
   * parametro matriculas) en una asignatura.
   * @return los números de matricula de los alumnos matriculados.
   * @throws InvalidAsignaturaException si la asignatura no
   * está siendo administrada por la secretaría.
   */
    public Iterable<String> matricular(String asignatura, Iterable<String> matriculas)
	throws InvalidAsignaturaException {
    	return getAsignatura(asignatura).matricular(matriculas);
    }

  /**
   * Desmatricula una coleccion de alumnos (representados por el
   * parametro matriculas) de una asignatura.
   * @return las matriculas desmatriculados (que debían estar
   * matriculados y no tener nota).
   * @throws InvalidAsignaturaException si la asignatura no está
   * siendo administrado por la secretaria.
   */
    public Iterable<String> desMatricular(String asignatura, Iterable<String> matriculas)
	throws InvalidAsignaturaException {
    	return getAsignatura(asignatura).desMatricular(matriculas);
    }

  /**
   * Calcula la nota media de un alumno (representado por su
   * identificador de matrícula) en todas asignaturas en las que está
   * matriculado.  Si el alumno no tiene ninguna nota en ninguna
   * asignatura, el metodo debe devolver 0.
   * @return la nota media del alumno.
   */
    public double notaMediaExpediente (String matricula) {
   // Completar este metodo
    	
    	double resultado = 0;
    	int numAsignatura = 0;
    	
    	Iterator<AsignaturaAdmin> t = asignaturas.iterator();
    	
    	while(t.hasNext()){
    		
    		AsignaturaAdmin asignatura = t.next();
    		
    		try{
    		if(asignatura.estaMatriculado(matricula) && asignatura.tieneNota(matricula)){
    			
    			resultado += asignatura.getNota(matricula);
    			numAsignatura++;
    		}	
    	}
    		
    		catch(InvalidMatriculaException e){
    			
    			return 0;
    		}
    	}
    	
    	if(resultado==0){
    		
    		return 0;
    	}

      return resultado/numAsignatura;
   
    }

  /**
   * Devuelve el nombre de la asignatura que tiene la mejor nota
   * media, calculada usando las notas de todos los alumnos que tienen
   * notas para esa asignatura.  Si la secretaria no esta
   * administrando ninguna asignatura, el metodo debe devolver
   * null. Similarmente, si ningún alumno tiene nota en ninguna
   * asignatura, el metodo tambien debe devolver null.
   * @return el nombre de la asignatura con la mejor nota media.
   */
    public String mejorNotaMedia() {
        // Completar este metodo
    	
    	double maximo = 0;
    	String asignatura = " ";
    	
    	Iterator<AsignaturaAdmin> t = asignaturas.iterator();
    	
    	while(t.hasNext()){
    		AsignaturaAdmin asignaturas = t.next();
    		if(asignaturas.notaMedia() >= maximo){
    			maximo = asignaturas.notaMedia();
    			asignatura = asignaturas.getNombreAsignatura();
    			
    		}
    	}
    	
      return asignatura;
    }

  /**
   * Devuelve todas las notas de un alumno (representado por su
   * identificador de matrícula) como una coleccion de objetos
   * Pair(NombreAsignatura, Nota).
   * @return una coleccion de pares de las notas de la matricula en
   * todas las asignaturas.
   */
    public Iterable<Pair<String,Integer>> expediente(String matricula) {
      // Completar este metodo
    	Iterator<AsignaturaAdmin> it = asignaturas.iterator();
    	IndexedList<Pair<String,Integer>> aux = new ArrayIndexedList<Pair<String,Integer>>();
    	AsignaturaAdmin res = null; 
    	int i = 0;
    	while (it.hasNext() && res == null) {
    		AsignaturaAdmin admin = it.next();
    		try{
    			int nota = admin.getNota(matricula);
    			Pair<String,Integer> subject = new Pair<String,Integer>(admin.getNombreAsignatura(),nota);
    			aux.add(i, subject);
    			i++;
    		}
    		catch (InvalidMatriculaException e){}
    	}
    	return aux;
    }

  /**
   * Devuelve una coleccion con todas los pares de asignaturas --
   * representados como Pair(NombreAsignatura1, NombreAsignatura2) --
   * que no tienen alumnos en comun.  El metodo NO debe devolver nunca
   * un par Pair(NombreAsignatura,NombreAsignatura), es decir, con
   * nombres iguales de asignaturas.  Si dos asignaturas A1 y A2 no
   * tienen ningún alumno en común, para ellas se puede devolver: (i)
   * Pair(A1,A2), o (ii) Pair(A1,A2), Pair(A2,A1), o (iii)
   * Pair(A2,A1).
   * @return una coleccion que contiene todos los pares de asignaturas
   * que no tienen ningún alumno en comun.
   */
    public Iterable<Pair<String,String>> asignaturasNoConflictivas () {
    	IndexedList<Pair<String,String>> res = new ArrayIndexedList<Pair<String,String>>();
    	Iterator<AsignaturaAdmin> it1 = asignaturas.iterator();
    	
    	int i = 0;
    	while (it1.hasNext()) {
    		Iterator<AsignaturaAdmin> it2 = asignaturas.iterator();
    		AsignaturaAdmin admin1 = it1.next();
    		AsignaturaAdmin admin2;
    		i++;
    		int j = 0;
    		while(it2.hasNext() && j<i){
    			admin2 = it2.next();
    		}    			
    		while (it2.hasNext()){
    			admin2 = it2.next();
    			if(compartenAlumnos(admin1, admin2)){
    				Pair<String, String> subjects = new Pair<String, String>(admin1.getNombreAsignatura(), admin2.getNombreAsignatura());
    				res.add(res.size(), subjects);
    			}
    		}
    	}
    	return res;
    }

  /**
   * Devuelve true si dos asignaturas a1 y a2 no tienen ningun alumno en
   * comun.
   * @return true si las dos asignaturas no tienen ningún alumno en comun.
   */
  private boolean compartenAlumnos (AsignaturaAdmin a1, AsignaturaAdmin a2) {
	  Iterable<String> students1 = a1.matriculados();
	  Iterable<String> students2 = a2.matriculados();
	  
	  Iterator<String> it1 = students1.iterator();
	  Iterator<String> it2 = students2.iterator();
	  
	  boolean notFound = true;
	  
	  while(it1.hasNext() && notFound){
		  String auxs1 = it1.next();
		  while(it2.hasNext() && notFound){
			  String auxs2 = it2.next();
			  if(auxs2.equals(auxs1)) notFound = false;			  
		  }
	  }
	  return notFound;
  }
}
