import java.time.LocalDate;

public class Feature extends Tarea{

	public Feature(String idProyecto, String idTarea, String tipoTarea, String descripcionTarea, String estadoTarea,
			Usuario responsableTarea, String complejidadTarea, LocalDate fechaTarea) {
		super(idProyecto, idTarea, tipoTarea, descripcionTarea, estadoTarea, responsableTarea, complejidadTarea, fechaTarea);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getPrioridadImpacto() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void aceptar(Visitor v) {
		// TODO Auto-generated method stub
		v.visitar(this);
	}

}
