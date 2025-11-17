import java.io.File;
import java.io.FileNotFoundException;
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
		
		while(login==null) {
			
		}
	}
	
	
	
	
}
