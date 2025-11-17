import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class MaIn {

	public static void main(String[] args)throws FileNotFoundException {
		// TODO Auto-generated method stub
		 Scanner sc = new Scanner(System.in);
		 Sistema sis = Sistema.getInstance();
		 Usuario login = null;
		 File arch = new File("usuarios.txt");
		 
			Scanner lector = new Scanner(arch);
			while(lector.hasNextLine()) {
				String line = lector.nextLine();
				String[] partes = line.split("\\|");
				sis.cargarUsuarios(partes);				
			}
			lector.close();
		File arch1 = new File("proyectos.txt");
		Scanner lector1 = new Scanner(arch1);
		while(lector1.hasNextLine()) {
			String line1 = lector1.nextLine();
			String[] partes1 = line1.split("\\|");
			sis.cargarProyectos(partes1);		
		}
		lector1.close();
		
		File arch2 = new File("tareas.txt");
		Scanner lector2 = new Scanner(arch2);
		while(lector2.hasNextLine()) {
			String line2 = lector2.nextLine();
			String[] partes2 = line2.split("\\|");
			sis.cargarTareas(partes2);				
		}
		lector2.close();
		
		
		//Menu base
		
		
		while(login==null) {
			System.out.println("Menu");
			System.out.println("Ingrese su usuario");
			String usuario = sc.nextLine();
			System.out.println("Ingrese su Contraseña");
			String pass = sc.nextLine();
			
			login = sis.login(usuario,pass);
			
			if(login==null) {
				System.out.println("Usuario no encontrado");
			}
			
		}
		
		System.out.println("Bienvenido "+login.getNombreUsuario());
		if(login.getRolUsuario().equalsIgnoreCase("administrador")) {
			menuAdmi(sis, sc, login);
		}else if(login.getRolUsuario().equalsIgnoreCase("colaborador")) {
			menuUsuario(sis, sc, login);
		}
	}
	
	public static void menuUsuario(Sistema sis,Scanner sc, Usuario user) {
		int op;
		
		do {
			System.out.println("\n=== MENU COLABORADOR ===");
	        System.out.println("1. Ver proyectos disponibles");
	        System.out.println("2. Ver tareas asignadas");
	        System.out.println("3. Cambiar estado de una tarea");
	        System.out.println("4. Aplicar visitor a las tareas");
	        System.out.println("0. Salir");
	        
	        op = sc.nextInt();
	        sc.nextLine();
	        switch (op) {
			case 1:
				System.out.println("1)Ver info todos proyectos (vista usuario)");
				System.out.println("2)Ver proyectos asignados(vista total");
				int op1 = sc.nextInt();
				sc.nextLine();
				if(op1 == 1) {
					sis.verLimitado();
				}else if(op1 == 2) {
					sis.verProyectos();
				}
				break;
			case 2:
				sis.verTareas();
				break;
			case 3:
				System.out.println("Ingrese el id de la tarea a modificar su estado");
				String idTarea= sc.nextLine();
				Tarea t = sis.buscarTarea(idTarea);
				System.out.println("La tarea "+t.getIdTarea() +" del proyecto "+t.getIdProyecto()+" con un estado actual de "+t.getEstadoTarea());
				System.out.println();
				System.out.println("Ingrese el nuevo estado()");
				String estadoNuevo= sc.nextLine(); 
				
				sis.actualizarEstadoTarea(idTarea, estadoNuevo);
				break;
				
			case 4:
				System.out.println("Aplicando visitor...");
				System.out.println("...");
				System.out.println("..");
				sis.aplicarVisitorEnTareas();
				break;
			
			}
		} while (op!=0);

        
	}
	
	public static void menuAdmi(Sistema sis,Scanner sc, Usuario user) {
int op;
		
		do {
			System.out.println("\n=== MENU ADMINISTRADOR ===");
	        System.out.println("1. Ver proyectos");
	        System.out.println("2. Agregar o eliminar un proyecto");
	        System.out.println("3. Agregar o eliminar una tarea");
	        System.out.println("4. Aplicar estrategia de prioridad");
	        System.out.println("5. Generar txt global de proyectos");
	        System.out.println("0. Salir");
	        
	        op = sc.nextInt();
	        sc.nextLine();
	        switch (op) {
			case 1:
				sis.verTodo();
				break;
			case 2:
				System.out.println("1)Eliminar\n2)Agregar");
				op = sc.nextInt(); 
				sc.nextLine();
				if(op==1) {
					System.out.println("Ingrese el id del proyecto");
					String idProyecto = sc.nextLine();
					sis.eliminarProyecto(idProyecto);
				}else if(op == 2) {
					System.out.println("Ingrese el id del proyecto");
					String idProyecto = sc.nextLine();
					System.out.println("Ingrese el nombre del proyecto");
					String nombreProyecto = sc.nextLine();
					System.out.println("Ingrese el nombre del responsable");
					String responsable = sc.nextLine(); 
					sis.agregarProyecto(idProyecto, nombreProyecto, responsable);
				}
				break;
			case 3:
				System.out.println("1)Eliminar\n2)Agregar");
				op = sc.nextInt(); 
				if(op==1) {
					System.out.println("Ingrese el id del proyecto");
					String idProyecto = sc.nextLine();
					System.out.println("ingrese el id de la tarea");
					String idTarea = sc.nextLine();
					sis.eliminarTarea(idProyecto, idTarea);
				}else if(op == 2) {
					System.out.println("Ingrese el id del proyecto");
					String idProyecto = sc.nextLine();
					System.out.println("ingrese el id de la tarea");
					String idTarea = sc.nextLine();
					System.out.println("ingrese el tipo de tarea");
					String tipoTarea = sc.nextLine();
					System.out.println("ingrese la descripcion de la tarea");
					String descripTarea = sc.nextLine();
					System.out.println("ingrese el estado de la tarea");
					String estadoTarea = sc.nextLine();
					System.out.println("ingrese el responsable de la tarea");
					String responsableTarea = sc.nextLine();
					System.out.println("ingrese la complejidad de la tarea");
					String complejidadTarea = sc.nextLine();
					System.out.println("ingrese la fecha de la tarea en formato(yyyy-MM-dd)");
					String fecha= sc.nextLine();
					
					
					sis.agregarTarea(idProyecto, idTarea, tipoTarea, descripTarea, estadoTarea, responsableTarea, complejidadTarea, fecha);
					
				}
				break;
				
			case 4:
				System.out.println("Ingrese el id del proyecto");
				String idProyecto = sc.nextLine();
				System.out.println("Seleccione estrategia");
                System.out.println("1. Fechas");
                System.out.println("2. Impacto");
                System.out.println("3. Complejidad");
                int opEstrategia = sc.nextInt();
                sc.nextLine();
                sis.asignarPrioridades(idProyecto, opEstrategia);
				break;
			case 5:
				System.out.println("Generando TXT");
				System.out.println("...");
				System.out.println("..");
				sis.generarTxt();
				
			
			}
		} while (op!=0);
	}
	
}
