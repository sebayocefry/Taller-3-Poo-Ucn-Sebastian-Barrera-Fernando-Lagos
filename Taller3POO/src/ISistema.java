import java.time.LocalDate;

public interface ISistema {
	
	public void cargarUsuarios(String[] partes);
	public void cargarProyectos(String[] partes);
	public void cargarTareas(String[] partes);
	public void verTodo();
	public void agregarProyecto(String idProyecto, String nombreProyecto, String responsable);
	public void eliminarProyecto(String idProyecto);
	public void agregarTarea(String idProyecto,String idTarea,String tipoTarea, String descripcionTarea, String estadoTarea,
			Usuario responsableTarea, String complejidadTarea, LocalDate fechaTarea);
	public void eliminarTarea(String idProyecto, String idTarea);
	public PrioridadStrategy buscarEstrategia(int op);
	public void asignarPrioridades(String idProyecto, int op);
	
	//esto lo hago normal y sin visitor porque piden guardar proyectos y no tareas
	public void generarTxt();
	
	public Proyecto buscarProyecto(String idProyecto);
	public Tarea buscarTarea(String idTarea);
	
	
	public void verProyectos();
	public void verTareas();
	public void actualizarEstadoTarea( String idTarea,String estadoActualizar);
	public void aplicarVisitorEnTareas();
	
	
	public Usuario buscarUsuario(String name);
	public boolean login(String user, String pass);
	
}
