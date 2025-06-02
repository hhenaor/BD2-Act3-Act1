# Actividad 1 - BD2 Actvidad 3

Control de películas con JPA/Hibernate con MySQL

## REQUISITOS

- Java JDK 11+
- MySQL 8.0+
- NetBeans IDE
- Maven

## ESTRUCTURA DEL PROYECTO

```
peliculas-jpa/
├── pom.xml
├── src/main/java/
│   ├── com/ejemplo/entidades/
│   │   └── Pelicula.java
│   ├── com/ejemplo/util/
│   │   ├── JPAUtil.java
│   │   └── Utils.java
│   └── com/ejemplo/programas/
│       ├── ListarPeliculas.java
│       ├── NuevaPeliculaConRollback.java
│       └── ListarPeliculasReadUncommitted.java
├── src/main/resources/
│   └── META-INF/
│       └── persistence.xml
└── schema.sql
```

## INSTALACIÓN

### 1. Proyecto
```bash
# NetBeans: File -> New Project -> Maven -> Java Application
# Nombre: peliculas-jpa
# Group ID: com.ejemplo
```

### 2. Base de Datos
```bash
# Abrir MySQL como root
mysql -u root -p

# Ejecutar schema.sql
source schema.sql
```

### 3. Configurar Proyecto
1. Copiar todos los archivos de GitHub e insertar en el proyecto creado en NetBeans

### 4. Ejecutar los archivos

- ListarPeliculas.java
- NuevaPeliculaConRollback.java
- ListarPeliculasReadUncommitted.java

Usando ``Shift + F5``

#### Desde Terminal
```bash
mvn exec:java -Dexec.mainClass="com.ejemplo.programas.ListarPeliculas"
mvn exec:java -Dexec.mainClass="com.ejemplo.programas.NuevaPeliculaConRollback"
mvn exec:java -Dexec.mainClass="com.ejemplo.programas.ListarPeliculasReadUncommitted"
```

## PROGRAMAS DISPONIBLES

### 1. ListarPeliculas
- Muestra todas las películas de la BD

### 2. NuevaPeliculaConRollback
- Inserta película con flush y control de errores

### 3. ListarPeliculasReadUncommitted
- Lee datos de nivel READ_UNCOMMITTED

## PRUEBAS ESPECIFICAS

#### Test 1: Inserción exitosa
```bash
mvn exec:java -Dexec.mainClass="com.ejemplo.programas.NuevaPeliculaConRollback"
```
- Ingresar datos de pelicula
- Responder ``n`` cuando pregunte por error
- Verificar que se guarde correctamente

#### Test 2: Rollback provocado  
```bash
mvn exec:java -Dexec.mainClass="com.ejemplo.programas.NuevaPeliculaConRollback"
```
- Ingresar datos de película
- Responder ``s`` para provocar error
- Verificar que se ejecute rollback

#### Test 3: Concurrencia (DOS TERMINALES)
**Terminal 1**:
```bash
mvn exec:java -Dexec.mainClass="com.ejemplo.programas.NuevaPeliculaConRollback"
# Ingresar datos pero no responder la pregunta aún
```

**Terminal 2** (mientras Terminal 1 espera):
```bash
mvn exec:java -Dexec.mainClass="com.ejemplo.programas.ListarPeliculas"
# En esta parte debe estar la película nueva
```

## ARCHIVOS CLAVE

### pom.xml
- Dependencias: Hibernate, MySQL Connector, JPA API
- Java version: 11
- Encoding: UTF-8

### persistence.xml
- Unidad de persistencia: `PeliculasJPA`
- Driver: MySQL 8
- Hibernate configurado para mostrar SQL

### schema.sql
- Crea BD, usuario y tabla
- Datos iniciales de ejemplo
- Indices para optimizacion

## LICENCIA
Este proyecto está licenciado bajo los terminos de la GNU General Public License version 3.0 (GPLv3). Ver el archivo LICENSE para más información.
