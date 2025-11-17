# Taller-3-Poo-Ucn-Sebastian-Barrera-Fernando-Lagos
Taller 3 ITI – POO: TaskForge Ltda

1. Introducción
Este proyecto es una aplicación de consola desarrollada en Java para el Taller de Programación Orientada a Objetos. El objetivo principal no es solo crear un sistema funcional de gestión de tareas, sino que aplica un conjunto de principios de enfermedad y patrones arquitectónicos para garantizar un código desacoplado, mantenible y extensible.

El sistema simula un entorno de gestión de proyectos donde existen dos tipos de usuarios (Administradores y Colaboradores), quienes interactúan con Proyectos y Tareas. La persistencia de los datos se maneja a viajes de la lectura y escritura de archivos de texto (.txt).

2. Características Principales
El sistema de recepción una funcionalidad diferenciada basada en el rol del usuario:

👤 Funcionalidad General
.Autenticación: Sistema de inicio de sesión que valida usuario y contraseña.
.Persistencia de Datos: Carga inicial de usuarios, proyectos y tareas desde archivos .txt.
.Generación de Informes: Capacidad de exportar un estado completo de todos los proyectos y sus tareas a un archivo reporte.txt.

👨‍💼 Rol: Administrador
.Gestión de Proyectos: Crear y eliminar proyectos en el sistema.
.Gestión de Tareas: Agregar y eliminar tareas (Error, Reportaje, Documentación) de cual proyecto.
.Asignación de Estrategias: Asignar dinamamente la estrategia de priorización (ordenamiento) de tareas para un proyecto específico.
.Visibilidad Total: Visualización completa de todos los proyectos y todas las tareas.

👷 Rol: Colaborador
.Visibilidad Limitada: Acceso de solo lectura a los proyectos y tareas donde está asignado.
.Gestión de Estado: Capacidad de actualizar el estado de sus propias tareas (ej. de "Pendiente" a "En Proceso").
.Análisis de Tareas: Ejecutar operaciones de análisis (usando el patrón Visitante) sobre sus tareas.

3. Arquitectura y Patrones de Diseño
El núcleo de este proyecto es la implementación de patrones de enfermedad para resolver problemas comunes de la ingeniería de software.
Principio Fundamental: Programación Orientada a Interfaces
Sigo un enferma estricta, la capa de presentación (MaIn) no interactúa directamente con la clase Sistema, sino con la interfaz ISistema. Esto desacopla la lógica de negocio de la interfaz de usuario, permiso que la implementación del sistema puede ser reemplazada sin afectar al cliente (el MaIn).

