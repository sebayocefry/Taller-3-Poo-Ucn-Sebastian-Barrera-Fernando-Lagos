import java.util.ArrayList;

public class OrdenarImpacto implements PrioridadStrategy{

	// le daremos valores simples de 1 a 3 para darle un nivel de prioridad, siendo 1 el mas importante
	
	public int impacto(String impacto) {
		if(impacto.equalsIgnoreCase("bug")) {
			return 1;
		}
		if(impacto.equalsIgnoreCase("feature")) {
			return 2;
		}
		
		if(impacto.equalsIgnoreCase("documento")) {
			return 3;
		}
		return 0;
	}
	
	
	
	@Override
	public void ordenarTareas(ArrayList<Tarea> listaTareas) {
		// TODO Auto-generated method stub
		listaTareas.sort((t1,t2)-> Integer.compare((impacto(t1.getTipoTarea())), impacto(t1.getTipoTarea())));
		System.out.println(listaTareas);
	}

	
	// igual pude hacer una lista nueva y hacer un foreach donde hacia un instanof a tareas
	// si instanciaba bug lo guardaba en la lista, luego otro foreach pero que instanciara feature
	// y asi agregabas los features siguien la lista nueva, lo mismo para doc
	// y despues hacerle un clear a la lista original y un addAll a la misma lista con la lista nueva
	// pero me resulto mas larga y tediosa 
	
}
