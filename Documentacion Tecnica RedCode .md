# REDCODE ERP – Documentación Técnica del Proyecto

---

# 1. Introducción del proyecto

## Nombre del proyecto

**RedCode ERP – Sistema de Gestión Empresarial**

---

## Equipo de desarrollo

Proyecto desarrollado por el grupo **RedCode** en el módulo de Proyecto Intermodular de 1º DAM.

Integrantes del equipo:

- Daniel
- Diego
- Alba

---

## Contexto del proyecto

La empresa seleccionada se encuentra en pleno proceso de expansión. Su objetivo a medio plazo es lanzar una plataforma de comercio electrónico, pero antes de eso necesita resolver un problema grave: su gestión interna es un caos.

La dirección ha encargado el diseño de la infraestructura y el desarrollo del **Software Central de Gestión Corporativa (ERP básico)** para organizarse internamente antes de escalar hacia el exterior.

---

## Objetivo de la aplicación

El objetivo es desarrollar un **Producto Mínimo Viable (MVP)** que permita gestionar la información interna de la empresa de forma centralizada, persistente y funcional.

El sistema gestiona, como mínimo:

- **Estructura organizativa:** departamentos con nombre, presupuesto y director asignado
- **Capital humano:** empleados, su departamento asignado, rol, salario y contacto
- **Inventario tecnológico:** dispositivos de hardware dados de alta en la red y su responsable
- **Gestión de incidencias:** registro y seguimiento de incidencias sobre el inventario tecnológico

La aplicación cuenta con una interfaz web que permite realizar operaciones CRUD sobre todos estos datos, conectada a una base de datos PostgreSQL para garantizar la persistencia.

---

## Problema que resuelve

Muchas empresas tienen la información dispersa en diferentes herramientas, lo que provoca:

- pérdida de datos
- desorganización
- duplicidad de información
- falta de control del inventario tecnológico
- mala gestión de incidencias técnicas

Este sistema centraliza toda esa información en una única aplicación estructurada.

---

## Público objetivo

Este proyecto está orientado a:

- el equipo interno de la empresa (RRHH, sistemas, dirección)
- administradores de sistemas
- estudiantes de DAM que estudien el proyecto
- profesores que evalúen el trabajo

---

# 2. Tecnologías utilizadas

## Frontend

- HTML5
- CSS3
- JavaScript vanilla

## Backend

- Java (JDK 23)
- Programación orientada a objetos

## Base de datos

- PostgreSQL

## Control de versiones y despliegue

- GitHub (control de versiones)
- Vercel (despliegue del frontend)

## Herramientas de desarrollo

- IntelliJ IDEA

---

# 3. Arquitectura del sistema

## Explicación general

El sistema sigue una arquitectura en capas:

- **Frontend:** interfaz web con HTML, CSS y JavaScript, desplegada en Vercel
- **Backend:** lógica del sistema desarrollada en Java, ejecutada localmente
- **Base de datos:** PostgreSQL para la persistencia de los datos

El frontend no se comunica directamente con la base de datos. Es el backend Java quien gestiona todas las operaciones sobre PostgreSQL y sirve los datos a la interfaz.

---

## Componentes principales

### Frontend

Interfaz web con 5 páginas: inicio, departamentos, empleados, dispositivos e incidencias. Desarrollada con HTML5, CSS3 y JavaScript vanilla. Diseño responsivo adaptado a móvil, tablet y escritorio.

### Backend

Aplicación Java organizada en capas: `model` (entidades), `dao` (acceso a datos), `db` (conexión) y `main` (punto de entrada).

### Base de datos

PostgreSQL con las tablas: `departamento`, `empleado`, `dispositivo` e `incidencias`.

---

## Comunicación entre servicios

```text
Usuario
  ↓
Frontend (HTML/CSS/JS) → desplegado en Vercel
  ↓
Backend (Java) → ejecutado localmente
  ↓
PostgreSQL → base de datos local
```

### Diagrama

```
+----------------------------+
| Frontend Web (Vercel)      |
+----------------------------+
             ↓
+----------------------------+
| Backend Java (local)       |
+----------------------------+
             ↓
+----------------------------+
| PostgreSQL (local)         |
+----------------------------+
```

---

# 4. Funcionalidades principales

El sistema ERP desarrollado permite la gestión completa de los principales recursos internos de una empresa. Todas las operaciones se realizan sobre la base de datos PostgreSQL a través del backend en Java.

---

## 4.1 Gestión de departamentos

El sistema permite la administración completa de los departamentos de la empresa.

### Funcionalidades

- Listar todos los departamentos
- Buscar departamento por código
- Crear nuevo departamento
- Actualizar datos de un departamento
- Eliminar departamento

### Datos gestionados

- Código de departamento (`cod_dep`)
- Nombre del departamento (`nom_dep`)
- Presupuesto asignado (`presupuesto`)
- Código del director (`cod_director`)

---

## 4.2 Gestión de empleados

Permite el control del personal de la empresa y su relación con los departamentos.

### Funcionalidades

- Listar todos los empleados
- Buscar empleado por código
- Listar empleados por departamento
- Registrar nuevo empleado
- Actualizar información de un empleado
- Eliminar empleado

### Datos gestionados

- Código de empleado (`cod_empleado`)
- DNI (`dni`)
- Nombre (`nombre`)
- Apellidos (`apellido_1`, `apellido_2`)
- Rol o cargo (`rol`)
- Fecha de contratación (`fecha_contratacion`)
- Salario (`salario`)
- Departamento asignado (`cod_dep`)
- Correo electrónico (`mail`)

---

## 4.3 Gestión de dispositivos

Control del inventario tecnológico de la empresa.

### Funcionalidades

- Listar todos los dispositivos
- Buscar dispositivo por código
- Registrar nuevo dispositivo
- Actualizar información de un dispositivo
- Eliminar dispositivo

### Datos gestionados

- Código de dispositivo (`cod_dispositivo`)
- Dirección MAC (`mac`)
- Número de serie (`num_serie`)
- Sistema operativo (`sistema_operativo`)
- Dirección IP (`ip`)
- Tipo de dispositivo (`tipo`)
- Empleado responsable (`cod_empleado`)

---

## 4.4 Gestión de incidencias

Permite registrar y gestionar problemas técnicos relacionados con el inventario tecnológico.

### Funcionalidades

- Listar todas las incidencias
- Filtrar incidencias por estado
- Crear nueva incidencia
- Actualizar estado y responsable de una incidencia
- Cerrar incidencia
- Eliminar incidencia

### Datos gestionados

- Código de incidencia (`cod_incidencia`)
- Observaciones (`observaciones`)
- Estado (`estado`)
- Fecha de alta (`fecha_alta`)
- Fecha de cierre (`fecha_cierre`, opcional)
- Dispositivo afectado (`cod_dispositivo`)
- Responsable de resolución (`cod_responsable`)
- Creador de la incidencia (`cod_creador`)

---

# 5. Instalación y ejecución

## 5.1 Requisitos previos

Para ejecutar el proyecto es necesario disponer de:

- Java JDK 23
- PostgreSQL instalado y en ejecución
- IntelliJ IDEA
- Git instalado
- Navegador web moderno (para el frontend)

---

## 5.2 Configuración de la conexión a la base de datos

La conexión a PostgreSQL se configura en la clase `ConexionBD.java`:

```java
private static final String HOST = "localhost:5432";
private static final String DB_NAME = "red-code";
private static final String USUARIO = "postgres";
private static final String PASSWORD = "qwerty";

private static final String URL = "jdbc:postgresql://" + HOST + "/" + DB_NAME;
```

---

## 5.3 Instalación y puesta en marcha

1. Clonar el repositorio desde GitHub
2. Instalar Java JDK 23 y PostgreSQL
3. Crear la base de datos `red-code` en PostgreSQL
4. Ejecutar los scripts SQL de creación de tablas
5. Importar el proyecto en IntelliJ IDEA
6. Añadir el driver de PostgreSQL al proyecto
7. Verificar los datos de conexión en `ConexionBD.java`
8. Ejecutar `Main.java` para arrancar el backend

---

## 5.4 Acceso al frontend

El frontend está desplegado en Vercel y puede abrirse directamente desde el navegador. También puede ejecutarse en local abriendo el archivo `index.html`.

---

## 5.5 Ejecución en producción

- **Frontend:** desplegado en Vercel mediante `vercel.json`
- **Backend:** ejecución local con IntelliJ IDEA
- **Base de datos:** PostgreSQL local

---

# 6. Estructura del proyecto

## 6.1 Organización de carpetas

```
RedCode-ERP/
│
├── frontend/
│   ├── index.html
│   ├── departamentos.html
│   ├── empleados.html
│   ├── dispositivos.html
│   ├── incidencias.html
│   ├── style.css
│   └── vercel.json
│
├── backend/
│   ├── dao/
│   │   ├── DepartamentoDAO.java
│   │   ├── DispositivoDAO.java
│   │   ├── EmpleadoDAO.java
│   │   └── IncidenciaDAO.java
│   │
│   ├── db/
│   │   └── ConexionBD.java
│   │
│   ├── main/
│   │   └── Main.java
│   │
│   └── model/
│       ├── Departamento.java
│       ├── Dispositivo.java
│       ├── Empleado.java
│       └── Incidencia.java
│
├── sql/
├── docs/
└── README.md
```

---

## 6.2 Descripción de módulos

**`frontend/`**
Interfaz web desarrollada con HTML5, CSS3 y JavaScript vanilla. Incluye una página por cada módulo del sistema, una hoja de estilos global (`style.css`) y el archivo de configuración de despliegue en Vercel (`vercel.json`). El diseño es responsivo y se adapta a móvil, tablet y escritorio.

**`dao/`**
Clases de acceso a datos. Cada clase gestiona las operaciones CRUD de una entidad: `DepartamentoDAO`, `DispositivoDAO`, `EmpleadoDAO` e `IncidenciaDAO`. Usan `PreparedStatement` para evitar inyección SQL.

**`db/`**
Contiene `ConexionBD.java`, encargada de establecer la conexión con PostgreSQL.

**`main/`**
Contiene `Main.java`, punto de entrada de la aplicación backend.

**`model/`**
Clases entidad del sistema: `Departamento`, `Dispositivo`, `Empleado` e `Incidencia`. Representan las tablas de la base de datos.

**`sql/`**
Scripts SQL de creación de tablas y datos de prueba.

**`docs/`**
Documentación del proyecto.

---

# 7. APIs o endpoints

Este proyecto no utiliza APIs REST ni endpoints HTTP.

El backend Java se comunica directamente con PostgreSQL. El frontend no realiza llamadas a ningún servidor: es una interfaz estática desplegada en Vercel.

Por tanto:

- No existen endpoints
- No se utilizan métodos HTTP (GET, POST, PUT, DELETE)
- No se intercambian respuestas JSON

---

# 8. Problemas encontrados y soluciones aplicadas

## 8.1 Problemas de conexión a la base de datos

**Problema:** errores de conexión con PostgreSQL al arrancar el backend.

**Causa:** credenciales incorrectas o servicio de PostgreSQL detenido.

**Solución:** verificar usuario, contraseña y que el servicio esté activo antes de ejecutar la aplicación.

---

## 8.2 Problemas con el driver de PostgreSQL

**Problema:** el proyecto no reconocía las clases de conexión a PostgreSQL.

**Causa:** falta del driver de PostgreSQL en el classpath del proyecto.

**Solución:** añadir el archivo `.jar` del driver de PostgreSQL a las dependencias del proyecto en IntelliJ IDEA.

---

## 8.3 Problemas en la organización del backend

**Problema:** mezcla de lógica de negocio y acceso a datos en las mismas clases.

**Causa:** falta de estructura clara al inicio del desarrollo.

**Solución:** separación del proyecto en capas (`dao`, `db`, `model`, `main`).

---

## 8.4 Problemas en el frontend

**Problema:** errores en la interacción entre JavaScript y el HTML.

**Causa:** elementos del DOM mal referenciados o eventos mal asignados.

**Solución:** corrección de selectores y revisión de los eventos en cada página.

---

## 8.5 Problemas en el despliegue en Vercel

**Problema:** rutas rotas al navegar entre páginas tras desplegar en Vercel.

**Causa:** Vercel no resolvía correctamente las rutas de los archivos HTML.

**Solución:** configuración del archivo `vercel.json` con las reglas de reescritura de rutas.
