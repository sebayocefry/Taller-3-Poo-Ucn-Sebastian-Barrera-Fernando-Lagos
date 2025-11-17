import java.util.ArrayList;

public class Proyecto {
	private String idProyecto;
	private String nombreProyecto;
	private String responsableProyecto;
	private ArrayList<Tarea> listaTareas;
	
	// atributo para hacer de contexto la clase proyecto y no la sistema, 
	//debido que queremos aplicar por proyecto y no todos los proyectos
	private PrioridadStrategy miEstrategia;
	
	public Proyecto(String idProyecto, String nombreProyecto, String responsableProyecto) {
		super();
		this.idProyecto = idProyecto;
		this.nombreProyecto = nombreProyecto;
		this.responsableProyecto = responsableProyecto;
		this.listaTareas = new ArrayList<>();
	}
	
	
	public void aplicarEstrategia() {
		if(miEstrategia!=null) {
			miEstrategia.ordenarTareas(listaTareas);
		}
	}
	
	
	
	public void setMiEstrategia(PrioridadStrategy miEstrategia) {
		this.miEstrategia = miEstrategia;
	}


	public void agregarTarea(Tarea t) {
		listaTareas.add(t);
	}
	public String getIdProyecto() {
		return idProyecto;
	}
	public void setIdProyecto(String idProyecto) {
		this.idProyecto = idProyecto;
	}
	public String getNombreProyecto() {
		return nombreProyecto;
	}
	public void setNombreProyecto(String nombreProyecto) {
		this.nombreProyecto = nombreProyecto;
	}
	public String getResponsableProyecto() {
		return responsableProyecto;
	}
	public void setResponsableProyecto(String responsableProyecto) {
		this.responsableProyecto = responsableProyecto;
	}
	public ArrayList<Tarea> getListaTareas() {
		return listaTareas;
	}
	public void setListaTareas(ArrayList<Tarea> listaTareas) {
		this.listaTareas = listaTareas;
	}
	@Override
	public String toString() {
		return "Proyecto\n id Proyecto: " + idProyecto + " Nombre Proyecto: " + nombreProyecto + " responsable Proyecto: "
				+ responsableProyecto + "";
	}
	
	
}
