# Actividad 1 - BD2 Actvidad 3

Control de películas con JPA/Hibernate con MySQL

## REQUISITOS

- Java JDK 11+
- MySQL 8.0+
- NetBeans IDE
- Maven

## ESTRUCTURA DEL PROYECTO

```
PeliculasJPA
│   LICENSE
│   pom.xml
│   README.md
│   schema.sql
│   
└───src
    └───main
        ├───java
        │   └───com
        │       └───ejemplo
        │           ├───entidades
        │           │       Pelicula.java
        │           │       
        │           ├───peliculasjpa
        │           │       PeliculasJPA.java
        │           │       
        │           ├───programas
        │           │       ListarPeliculas.java
        │           │       ListarPeliculasReadUncommitted.java
        │           │       NuevaPeliculaConRollback.java
        │           │       
        │           └───util
        │                   JPAUtil.java
        │                   Utils.java
        │                   
        └───resources
            └───META-INF
                    persistence.xml
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

## LICENCIA
Este proyecto está licenciado bajo los terminos de la GNU General Public License version 3.0 (GPLv3). Ver el archivo LICENSE para más información.