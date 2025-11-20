============ Sistema de Gestión de Proyectos y Tareas ============

Proyecto desarrollado para el Taller de Programación Orientada a Objetos (POO – UCN)
Aplicación completa de consola escrita en Java, diseñada para demostrar un uso sólido de patrones de diseño, principios de POO, desacoplamiento, mantenibilidad, y extensibilidad.

📌 Índice:

  1.Introducción
  2.Características Principales
  3.Roles del Sistema
  4.Arquitectura del Sistema
  5.Patrones de Diseño Implementados
  6.Flujo General del Sistema
  7.Diagramas UML
  8.Requisitos
  9.Ejecución
  10.Estructura del Repositorio
  11.Documentación
  12.Mejoras Futuras
  13.Contribuciones
  14.Licencia

🟦 Introducción:

  Este sistema simula un entorno real de gestión de proyectos con usuarios, tareas y prioridades dinámicas.
  Todo el diseño está construido aplicando:

🎯 Principio Fundamental:

  1.Programación Orientada a Interfaces
  2.Principio de Responsabilidad Única
  3.Abierto/Cerrado (OCP)
  4.Inversión de Dependencia
  5.Patrones de diseño: Singleton, Factory, Strategy, Visitor
  6.La aplicación lee y escribe datos desde archivos .txt, permitiendo persistencia simple pero funcional.
  7.Siguiendo un diseño estricto, la capa de presentación (MaIn) no interactúa directamente con la clase Sistema, sino con la interfaz ISistema. Esto desacopla la lógica de negocio de la interfaz de usuario, permitiendo que la implementación del sistema pueda ser           reemplazada sin afectar al cliente (el MaIn).

👤 Funcionalidad General:

  1.Autenticación segura mediante usuario/contraseña
  2.Persistencia mediante archivos .txt
  3.Exportación de reportes (reporte.txt)
  4.Análisis de tareas mediante Visitor
  5.Lectura automática de usuarios, proyectos y tareas

👨‍💼 Rol Administrador:

  1.Gestión de Proyectos:
    Crear y eliminar proyectos en el sistema.

  2.Gestión de Tareas:
    Agregar y eliminar tareas (Bug, Feature, Documentacion) de cualquier proyecto.

  3.Asignación de Estrategias:
    Asignar dinámicamente la estrategia de priorización (ordenamiento) de tareas para un proyecto específico.

  4.Visibilidad Total:
    Visualización completa de todos los proyectos y todas las tareas.

👷 Rol Colaborador:

  1.Visibilidad Limitada:
    Acceso de solo lectura a los proyectos y tareas donde está asignado.

  2.Gestión de Estado:
    Capacidad de actualizar el estado de sus propias tareas (ej. de "Pendiente" a "En Proceso").

  3.Análisis de Tareas:
    Ejecutar operaciones de análisis (usando el patrón Visitor) sobre sus tareas.

🏗️ Arquitectura del Sistema:

  El objetivo de este proyecto es la implementación de patrones de diseño para resolver problemas comunes de la ingeniería de software.

  1. Sistema (Fachada + Singleton):

     Maneja autenticación, proyectos, tareas, usuarios
     Punto único de acceso
     Evita inconsistencias entre instancias

  2. Entidades del Dominio:

     Proyecto: 
     contiene lista de tareas y estrategia aplicada
     
     Tarea (abstracta):
     Bug
     Feature
     Documentacion
     
     Usuario:
     Admi
     Colaborador

  3. Fábricas (Factory Method):
     UsuarioFactory
     TareasFactory
     Permiten desacoplar creación de objetos, facilitando agregar nuevos tipos.

  4. Módulo de Estrategias (Strategy Pattern):
     EstrategiaFecha
     OrdenarComplejidad
     OrdenarImpacto
     El administrador puede cambiar dinámicamente la estrategia aplicada.

  5. Visitor Pattern:
     Permite agregar operaciones a Tarea sin modificar sus clases hijas.


🎯 Patrones de Diseño Implementados:

🟩 Patrón Singleton:

  1.Implemetación:
    Implementado en Sistema
    Garantiza única instancia
    Centraliza información
    Evita inconsistencia de datos

  2.¿Como?:
    La clase Sistema implementa el patrón Singleton. El constructor es privado y se accede a la única instancia global a través del método estático Sistema.getInstance().

  3.Justificación:
    El sistema necesita un Punto Único de Verdad (Single Source of Truth). Solo debe existir una instancia que gestione la lista de usuarios, la lista de proyectos y el estado del usuario logueado (uLogueado). Esto previene la desincronización de datos que ocurriría si     múltiples instancias del sistema coexistieran.


🟧 Patron Metodo Factory:

  1.Utilizado en:
    UsuarioFactory
    TareasFactory
    Encapsula creación de objetos según tipo.
    Permite agregar nuevos tipos sin modificar el sistema.

  2.Justificación:
    Este patrón encapsula la lógica de creación de objetos complejos.
    Cuando el Sistema carga usuarios desde un .txt, no debe saber cómo construir un Admi o un Colaborador. Simplemente le pide a UsuarioFactory.crearUsuario(...) que lo haga.
    De igual manera, TareasFactory abstrae la lógica de instanciar un Bug, Feature o Documentacion.

  3.Beneficio:
    Si en el futuro se añade un nuevo rol (ej. Invitado) o un nuevo tipo de tarea (ej. Mejora), solo se modifica la fábrica correspondiente. Las clases Sistema o MaIn no sufren ningún cambio, adhiriéndose al Principio de Abierto/Cerrado.

🟨 Patron Strategy:

  PrioridadStrategy define comportamiento.

  1.Implementación:
    Fecha
    Impacto
    Complejidad
    El proyecto actúa como Contexto.

  2.¿Como?:
    La interfaz PrioridadStrategy define el contrato ordenarTareas(). Las clases EstrategiaFechas, OrdenarComplejidad y OrdenarImpacto proveen implementaciones concretas. La clase Proyecto actúa como el Contexto, manteniendo una referencia a una estrategia                  (miEstrategia).

  3.Justificación:
    Permite definir una familia de algoritmos y hacerlos intercambiables. La priorización de tareas es un comportamiento que puede variar. El administrador puede decidir en tiempo de ejecución si las tareas de un proyecto deben ordenarse por fecha, impacto o                complejidad. El Proyecto delega la responsabilidad del "cómo" ordenar a la estrategia que tenga asignada, promoviendo la Inversión de Dependencia.

🟥 Patron Visitor:

  1.Implementaciones:
    TareasVisitor implementa varias operaciones:
    análisis de impacto
    mensajes personalizados
    Permite agregar futuras operaciones sin modificar tareas.

  1.Justificación:
    Este patrón permite agregar nuevas operaciones a una jerarquía de clases sin modificar esas clases. En este proyecto, TareasVisitor añade la capacidad de imprimir un análisis de impacto ("Afecta criticidad", "Impacta en estimación", etc.).

  2.Beneficio:
    Si en un futuro necesitamos una operación completamente nueva (ej. "CalcularCosteTarea"), podemos crear un CosteVisitor sin tener que añadir el método calcularCoste(). Esto mantiene las clases de tareas limpias, estables y centradas en su única responsabilidad.

📌 Flujo General de Uso del Sistema:

  1.Inicio de Sesión
    Validación de usuario y contraseña
    Se carga usuario logueado

  2.Gestión de Proyectos
    Administrador crea o elimina proyectos
    Se asignan responsables
    Se aplican estrategias dinámicas

  3.Gestión de Tareas
    Crear mediante fábrica
    Asignar responsable
    Actualizar estado
    Ordenar según estrategia

  4.Exportación
    Se genera reporte en .txt

📊 Diagrama UML:

  ![image_alt](https://github.com/sebayocefry/Taller-3-Poo-Ucn-Sebastian-Barrera-Fernando-Lagos/blob/main/taller%203UML-2025-11-17-210756.png)

📊 Diagrama de Dominio:

  ![image_alt](https://github.com/sebayocefry/Taller-3-Poo-Ucn-Sebastian-Barrera-Fernando-Lagos/blob/main/taller%203%20poo%20diagrama%20dominio-2025-11-17-213649.png)
