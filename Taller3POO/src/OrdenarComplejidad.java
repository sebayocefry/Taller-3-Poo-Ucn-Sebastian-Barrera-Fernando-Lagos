import java.util.ArrayList;

public class OrdenarComplejidad implements PrioridadStrategy{

	public int complejidad(String complejidad) {
		if(complejidad.equalsIgnoreCase("baja")) {
			return 1;
		}
		if(complejidad.equalsIgnoreCase("media")) {
			return 2;
		}
		if(complejidad.equalsIgnoreCase("alta")) {
			return 3;
		}
		return -1;//el viejo null
	}
	@Override
	public void ordenarTareas(ArrayList<Tarea> listaTareas) {
		// TODO Auto-generated method stub
		listaTareas.sort((t1,t2)-> Integer.compare(complejidad(t1.getComplejidadTarea()),complejidad(t2.getComplejidadTarea())));
	}

}
