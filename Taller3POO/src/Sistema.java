
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;



public class Sistema implements ISistema {
	//atributo estatico para que este presente en toda la clase
	private static Sistema instancia;
	
	//listas de usuario y la de los proyectos que dentro contiene otra con las tareas
	private ArrayList<Usuario> listaUsuarios = new ArrayList<>();
	private ArrayList<Proyecto> listaProyectos = new ArrayList<>();
	
	// lo uso para poder acceder a las tareas del usuario logueado
	
	private Usuario uLogueado;
	
	//constructor privado para no poder instanciar desde afuera
	
	private Sistema() {}
	
	public static Sistema getInstance() {
		if(instancia == null) {
			instancia = new Sistema();
		}
		return instancia;
	}
	
	
	public Usuario getuLogueado() {
		return uLogueado;
	}

	public void setuLogueado(Usuario uLogueado) {
		this.uLogueado = uLogueado;
	}

	@Override
	public void cargarUsuarios(String[] partes) {
		// TODO Auto-generated method stub
		Usuario nuevoUsuario = UsuarioFactory.crearUsuario(partes[0], partes[1], partes[2]);
		if(nuevoUsuario!=null) {
			this.listaUsuarios.add(nuevoUsuario);
		}

	}

	@Override
	public void cargarProyectos(String[] partes) {
		// TODO Auto-generated method stub
		listaProyectos.add(new Proyecto(partes[0],partes[1], partes[2]));
		
	}

	@Override
	public void cargarTareas(String[] partes) {
		// TODO Auto-generated method stub
		String idProyecto = partes[0];
		Usuario nUsario = buscarUsuario(partes[5]);
		LocalDate fecha = LocalDate.parse(partes[7]);
		for (Proyecto p : listaProyectos) {
			if(p.getIdProyecto().equalsIgnoreCase(idProyecto)) {
				Tarea nuevaTarea = TareasFactory.crearTarea(idProyecto, partes[1], partes[2],partes[3], partes[4], nUsario, partes[6],fecha);
				p.agregarTarea(nuevaTarea);
			}
		}

	}

	@Override
	public void verTodo() {
		// TODO Auto-generated method stub
		for (Proyecto proyecto : listaProyectos) {
			System.out.println(proyecto.toString());
		}

	}
	
	@Override
	public void verLimitado() {
		// TODO Auto-generated method stub
		for (Proyecto proyecto : listaProyectos) {
			System.out.println("ID del proyecto:"+proyecto.getNombreProyecto());
			System.out.println("Responsable: "+ proyecto.getResponsableProyecto());
			for (Tarea t : proyecto.getListaTareas()) {
				System.out.println(t.getTipoTarea());
				System.out.println(t.getEstadoTarea());
			}
			
		}

	}
	//  metodo de prueba
	public void verU() {
		for (Usuario u : listaUsuarios) {
			System.out.println(u.toString());
		}
	}
	

	@Override
	public void agregarProyecto(String idProyecto, String nombreProyecto, String responsable) {
		// TODO Auto-generated method stub
		Proyecto proyectoNuevo = new Proyecto(idProyecto, nombreProyecto, responsable);
		Usuario usuario = buscarUsuario(responsable);
		if(proyectoNuevo!=null && usuario!=null) {
			listaProyectos.add(proyectoNuevo);
			return;
		}
		System.out.println("error");
		
		
	}

	@Override
	public void eliminarProyecto(String idProyecto) {
		// TODO Auto-generated method stub
		Proyecto proyectoDelete = buscarProyecto(idProyecto);
		listaProyectos.remove(proyectoDelete);
	}

	@Override
	public void agregarTarea(String idProyecto, String idTarea, String tipoTarea, String descripcionTarea,
			String estadoTarea, String responsableTarea, String complejidadTarea, String fechaTarea) {
		// TODO Auto-generated method stub
		LocalDate date = convertirFecha(fechaTarea);
		Usuario responsable = buscarUsuario(responsableTarea);
		
		Tarea nuevaTarea = TareasFactory.crearTarea(idProyecto, idTarea, tipoTarea, descripcionTarea, estadoTarea, responsable, complejidadTarea, date);
		Proyecto proyecto = buscarProyecto(idProyecto);
		if(proyecto!=null && nuevaTarea != null) {
			proyecto.getListaTareas().add(nuevaTarea);
		}
		
		

	}
	
	@Override
	public void eliminarTarea(String idProyecto, String idTarea) {
		// TODO Auto-generated method stub
		Tarea tarea = buscarTarea(idTarea);
		Proyecto proyecto = buscarProyecto(idProyecto);
		if(tarea!=null && proyecto!=null) {
			proyecto.getListaTareas().remove(tarea);
		}
	}
	@Override
	public PrioridadStrategy buscarEstrategia(int op) {
		if(op==1) {
			return new EstrategiaFechas();
		}
		if(op==2) {
			return new OrdenarImpacto();
		}
		if(op==3) {
			return new OrdenarComplejidad();
		}
		System.out.println("Estrategia no encontrada");
		return null;
	}
	
	
	@Override
	public void asignarPrioridades(String idProyecto, int op) {
		// TODO Auto-generated method stub
		Proyecto p = buscarProyecto(idProyecto);
		PrioridadStrategy nuevaEstrategia = buscarEstrategia(op);
		if(p!=null && nuevaEstrategia!=null) {
			p.setMiEstrategia(nuevaEstrategia);
			p.aplicarEstrategia();
		}
	}

	@Override
	public void generarTxt() {
		// TODO Auto-generated method stub
		 try {
		        FileWriter fw = new FileWriter("reporte.txt");
		        PrintWriter pw = new PrintWriter(fw);

		        for (Proyecto p : listaProyectos) {

		            pw.println("===== Proyecto " + p.getIdProyecto() + " =====");
		            pw.println("Nombre: " + p.getNombreProyecto());
		            pw.println("Responsable: " + p.getResponsableProyecto());
		            pw.println();
		            pw.println("--- Tareas ---");

		            for (Tarea t : p.getListaTareas()) {
		                pw.println("ID: " + t.getIdTarea());
		                pw.println("Tipo: " + t.getTipoTarea());
		                pw.println("Estado: " + t.getEstadoTarea());
		                pw.println("Responsable: " + 
		                    (t.getResponsableTarea() != null ? 
		                    t.getResponsableTarea().getNombreUsuario() : "Sin asignar"));
		                pw.println("Fecha: " + t.getFechaTarea());
		                pw.println("Complejidad: " + t.getComplejidadTarea());
		                pw.println();
		            }

		            pw.println("------------------------");
		            pw.println();
		        }

		        pw.close();
		        System.out.println("Reporte generado con éxito.");

		    } catch (Exception e) {
		        System.out.println("Error al generar el archivo: " + e.getMessage());
		    }

	}

	
	@Override
	public void verProyectos() {
		// TODO Auto-generated method stub

		Usuario Ulogueado = this.uLogueado;
		 String NombreU = Ulogueado.getNombreUsuario();
		if(uLogueado==null) {
			System.out.println("No hay usuario logueado");
			return;
		}
		System.out.println("Proyectos asignados a "+ uLogueado.getNombreUsuario());
		for (Proyecto proyecto : listaProyectos) {
			if(proyecto.getResponsableProyecto().equals(NombreU)) {
				System.out.println(proyecto);
			}
		}
	}

	@Override
	public void verTareas() {
		// TODO Auto-generated method stub
		Usuario uLogueado = this.uLogueado;
		if(uLogueado==null) {
			System.out.println("No hay usuario logueado");
			return;
		}
		System.out.println("Tareas asignas a "+ uLogueado.getNombreUsuario());
		for (Proyecto proyecto : listaProyectos) {
			for (Tarea tarea : proyecto.getListaTareas()) {
				if(tarea.getResponsableTarea()!=null && tarea.getResponsableTarea().equals(uLogueado)) {
					System.out.println(tarea);
				}
				
			}
		}
		
	}

	@Override
	public void actualizarEstadoTarea(String idTarea,String estadoActualizar) {
		// TODO Auto-generated method stub
		Tarea t = buscarTarea(idTarea);
		
		if(t == null) {
			return;
		}
		
		if(estadoActualizar.equalsIgnoreCase("pendiente")) {
			System.out.println("Las tareas van en progreso, no en retroceso");
			return;
		}
		
		if(t.getEstadoTarea().equalsIgnoreCase(estadoActualizar)) {
			System.out.println("Las tareas van en progreso, no en empate");
			return;
		}
		t.setEstadoTarea(estadoActualizar);
		System.out.println("La tarea "+t.getIdTarea() +" del proyecto "+t.getIdProyecto()+" actualizo su estado actual a "+t.getEstadoTarea());
		

	}

	@Override
	public void aplicarVisitorEnTareas() {
		// TODO Auto-generated method stub
		Visitor v = new TareasVisitor();
		for (Proyecto proyecto : listaProyectos) {
			for(Tarea t : proyecto.getListaTareas()) {
				t.aceptar(v);
			}
		}
	}

	@Override
	public Usuario buscarUsuario(String name) {
		// TODO Auto-generated method stub
		for (Usuario u : listaUsuarios) {
			if(u.getNombreUsuario().equals(name)) {
				return u;
			}
		}
		return null;
	}

	@Override
	public Proyecto buscarProyecto(String idProyecto) {
		// TODO Auto-generated method stub
		for (Proyecto proyecto : listaProyectos) {
			if(proyecto.getIdProyecto().equalsIgnoreCase(idProyecto)) {
				return proyecto;
			}
		}
		return null;
	}

	@Override
	public Tarea buscarTarea(String idTarea) {
		// TODO Auto-generated method stub
		for (Proyecto proyecto : listaProyectos) {
			for(Tarea tarea:proyecto.getListaTareas()) {
				if(tarea.getIdTarea().equalsIgnoreCase(idTarea)) {
					return tarea;
				}
			}
		}
		return null;
	}

	@Override
	public Usuario login(String user, String pass) {
		// TODO Auto-generated method stub
		for (Usuario u : listaUsuarios) {
			if(u.getNombreUsuario().equals(user)&&u.getPassword().equals(pass)) {
				this.uLogueado = u;
				return u;
			}
		}
		return null;
	}

	@Override
	public LocalDate convertirFecha(String fecha) {
		// TODO Auto-generated method stub
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate date = LocalDate.parse(fecha,formato);
		
		return date;
	}

	
	
}
