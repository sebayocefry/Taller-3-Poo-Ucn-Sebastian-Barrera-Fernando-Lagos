Sistema de Gestión de Proyectos - Taller de POO
1. Introducción
Este proyecto es una aplicación de consola desarrollada en Java para el Taller de Programación Orientada a Objetos. El objetivo principal no es solo crear un sistema funcional de gestión de tareas, sino hacerlo aplicando un conjunto de principios de diseño y patrones arquitectónicos para garantizar un código desacoplado, mantenible y extensible.

El sistema simula un entorno de gestión de proyectos donde existen dos tipos de usuarios (Administradores y Colaboradores), quienes interactúan con Proyectos y Tareas. La persistencia de los datos se maneja a través de la lectura y escritura de archivos de texto (.txt).

2. Características Principales
El sistema ofrece una funcionalidad diferenciada basada en el rol del usuario:

👤 Funcionalidad General
Autenticación: Sistema de inicio de sesión que valida usuario y contraseña.

Persistencia de Datos: Carga inicial de usuarios, proyectos y tareas desde archivos .txt.

Generación de Reportes: Capacidad de exportar un estado completo de todos los proyectos y sus tareas a un archivo reporte.txt.

👨‍💼 Rol: Administrador
Gestión de Proyectos: Crear y eliminar proyectos en el sistema.

Gestión de Tareas: Agregar y eliminar tareas (Bug, Feature, Documentacion) de cualquier proyecto.

Asignación de Estrategias: Asignar dinámicamente la estrategia de priorización (ordenamiento) de tareas para un proyecto específico.

Visibilidad Total: Visualización completa de todos los proyectos y todas las tareas.

👷 Rol: Colaborador
Visibilidad Limitada: Acceso de solo lectura a los proyectos y tareas donde está asignado.

Gestión de Estado: Capacidad de actualizar el estado de sus propias tareas (ej. de "Pendiente" a "En Proceso").

Análisis de Tareas: Ejecutar operaciones de análisis (usando el patrón Visitor) sobre sus tareas.

3. Arquitectura y Patrones de Diseño
El núcleo de este proyecto es la implementación de patrones de diseño para resolver problemas comunes de la ingeniería de software.

Principio Fundamental: Programación Orientada a Interfaces
Siguiendo un diseño estricto, la capa de presentación (MaIn) no interactúa directamente con la clase Sistema, sino con la interfaz ISistema. Esto desacopla la lógica de negocio de la interfaz de usuario, permitiendo que la implementación del sistema pueda ser reemplazada sin afectar al cliente (el MaIn).

🏛️ Patrón Singleton
Implementación: La clase Sistema implementa el patrón Singleton. El constructor es privado y se accede a la única instancia global a través del método estático Sistema.getInstance().

Justificación: El sistema necesita un Punto Único de Verdad (Single Source of Truth). Solo debe existir una instancia que gestione la lista de usuarios, la lista de proyectos y el estado del usuario logueado (uLogueado). Esto previene la desincronización de datos que ocurriría si múltiples instancias del sistema coexistieran.

🏭 Patrón Factory Method
Implementación: Se utilizan dos fábricas: UsuarioFactory y TareasFactory.

Justificación: Este patrón encapsula la lógica de creación de objetos complejos.

Cuando el Sistema carga usuarios desde un .txt, no debe saber cómo construir un Admi o un Colaborador. Simplemente le pide a UsuarioFactory.crearUsuario(...) que lo haga.

De igual manera, TareasFactory abstrae la lógica de instanciar un Bug, Feature o Documentacion.

Beneficio: Si en el futuro se añade un nuevo rol (ej. Invitado) o un nuevo tipo de tarea (ej. Mejora), solo se modifica la fábrica correspondiente. Las clases Sistema o MaIn no sufren ningún cambio, adhiriéndose al Principio de Abierto/Cerrado.

🎯 Patrón Strategy
Implementación: La interfaz PrioridadStrategy define el contrato ordenarTareas(). Las clases EstrategiaFechas, OrdenarComplejidad y OrdenarImpacto proveen implementaciones concretas. La clase Proyecto actúa como el Contexto, manteniendo una referencia a una estrategia (miEstrategia).

Justificación: Permite definir una familia de algoritmos y hacerlos intercambiables. La priorización de tareas es un comportamiento que puede variar. El administrador puede decidir en tiempo de ejecución si las tareas de un proyecto deben ordenarse por fecha, impacto o complejidad. El Proyecto delega la responsabilidad del "cómo" ordenar a la estrategia que tenga asignada, promoviendo la Inversión de Dependencia.

🕵️ Patrón Visitor
Implementación: La interfaz Visitor define los métodos visitar() para cada tipo de tarea concreta (Bug, Feature, Documentacion). La clase TareasVisitor implementa estas operaciones. La jerarquía de Tarea (los Elementos) implementa el método aceptar(Visitor).

Justificación: Este patrón permite agregar nuevas operaciones a una jerarquía de clases sin modificar esas clases. En este proyecto, TareasVisitor añade la capacidad de imprimir un análisis de impacto ("Afecta criticidad", "Impacta en estimación", etc.).

Beneficio: Si mañana necesitamos una operación completamente nueva (ej. "CalcularCosteTarea"), podemos crear un CosteVisitor sin tener que añadir el método calcularCoste() a la clase Tarea y todas sus hijas. Esto mantiene las clases de tareas limpias, estables y centradas en su única responsabilidad.

Diagrama UML:
![image_alt](https://github.com/sebayocefry/Taller-3-Poo-Ucn-Sebastian-Barrera-Fernando-Lagos/blob/main/taller%203UML-2025-11-17-210756.png)

Diagrama de dominio:
![image_alt](https://github.com/sebayocefry/Taller-3-Poo-Ucn-Sebastian-Barrera-Fernando-Lagos/blob/main/taller%203%20poo%20diagrama%20dominio-2025-11-17-213649.png)
