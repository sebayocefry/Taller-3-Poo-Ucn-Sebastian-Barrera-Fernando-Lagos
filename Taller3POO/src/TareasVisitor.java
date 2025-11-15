
public class TareasVisitor implements Visitor{

	@Override
	public void visitar(Bug bug) {
		// TODO Auto-generated method stub
		System.out.println("La tarea: "+bug.getIdTarea() + " de tipo: "+bug.getTipoTarea()+" Afecta criticidad del proyecto");
	}

	@Override
	public void visitar(Feature feature) {
		// TODO Auto-generated method stub
		System.out.println("La tarea: "+ feature.getIdTarea()+ " de tipo: "+feature.getTipoTarea()+" Impacta en la estimación de tiempo");
	}

	@Override
	public void visitar(Documentacion documentacion) {
		// TODO Auto-generated method stub
		System.out.println("La tarea: "+documentacion.getIdTarea()+" de tipo: "+ documentacion.getTipoTarea()+ " Mejora la calidad del proyecto.");
	}

}
