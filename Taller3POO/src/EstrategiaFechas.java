import java.util.ArrayList;

public class EstrategiaFechas implements PrioridadStrategy {

	// al principio iba hacer un ord burbuja pero haciendo la clase del martes 11 el profesor paolini enseno el .sort y las funciones lambda
	// asi que aprovechando lo aplicare, igual hay autorizacion del profesor y 
	@Override
	public void ordenarTareas(ArrayList<Tarea> listaTareas) {
		// TODO Auto-generated method stub
		// se que no poner una funcion Integer, Float etc podria provocarme algun error de overflock pero como es una
		//variable LocalDate no deberia tener mayores problemas por el tamano de numeros
		listaTareas.sort((t1,t2)->t1.getFechaTarea().compareTo(t2.getFechaTarea()));
	}

}
