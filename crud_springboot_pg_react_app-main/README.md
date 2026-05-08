# Clase: CRUD con Spring Boot y PostgreSQL

## 🎯 Objetivo

Construir una aplicación CRUD (Create, Read, Update, Delete) utilizando **Spring Boot** conectada a una base de datos **PostgreSQL**.

---

## ✅ Requisitos Previos

Antes de comenzar, asegúrate de tener instalado:

- Un IDE como Visual Studio Code o IntelliJ IDEA
- PostgreSQL
- pgAdmin
- Java (JDK)
- Maven

---

## 🚀 Paso 1: Crear el Proyecto con Spring Initializr

Usaremos Spring Initializr para generar la estructura base del proyecto.

1. Ir a https://start.spring.io/
2. Configurar:

   - **Project:** Maven  
   - **Language:** Java  
   - **Spring Boot:** Versión por defecto  
   - **Group:** com.ejemplo  
   - **Artifact:** crud-demo  

3. Agregar las siguientes dependencias:

   ### 📦 Dependencias necesarias

   - **Spring Web**  
     Permite crear APIs REST, controladores y manejar peticiones HTTP (GET, POST, PUT, DELETE).

   - **Spring Data JPA**  
     Facilita el acceso a la base de datos usando repositorios e integración con Hibernate. Evita escribir SQL manual.

   - **PostgreSQL Driver**  
     Permite que la aplicación se conecte a PostgreSQL.

   - **Lombok**  
     Reduce código repetitivo (boilerplate) generando automáticamente getters, setters, constructores, etc.

4. Hacer clic en **Generate**

Se descargará un archivo `.zip`.

---

## 📂 Paso 2: Abrir el Proyecto

1. Descomprimir el `.zip`
2. Abrir la carpeta en el IDE
3. Esperar a que Maven descargue dependencias

---

## 🛢️ Paso 3: Crear Base de Datos en PostgreSQL

Desde pgAdmin:

1. Crear base de datos:

2. Guardar(tener a la mano los valores segun la cofig de postgress para ponerlos en application.properties):

- Usuario
- Password
- Puerto (por defecto 5432)

---

## ⚙️ Paso 4: Configurar application.properties

Editar:

src/main/resources/application.properties


Ejemplo:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/crud_db
spring.datasource.username=postgres
spring.datasource.password=tu_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```

## ⚙️ Paso 5: Crear los Java Packages

Dentro de src/main/java/com/ejemplo/demo/ crear los siguientes packages:

model → Contendrá las entidades (clases @Entity)

repository → Contendrá los repositorios (interfaces JpaRepository)

controller → Contendrá los controladores REST

exception → Contendrá manejo de errores personalizados

Estructura esperada:

com.ejemplo.cruddemo
 ├── controller
 ├── model
 ├── repository
 ├── services
 └── exception
 

## Paso 6 Crear la primera entidad (User)

Ahora crearemos nuestra primera clase de modelo dentro del package **model**.  
Esta clase representará una tabla en la base de datos.
### ✅ Crear la clase en Visual Studio Code

1. Navegar a la carpeta: src/main/java/com/example/demo/model
2. crear el archivo: User.java


### 📘 ¿Qué es una Entidad en Spring Boot / JPA?

Una **entidad** es una clase Java que representa una **tabla en la base de datos**.

Cada objeto de esa clase equivale a **una fila (registro)**, y cada atributo de la clase
equivale a **una columna**.

@Entity

Indica que esta clase será gestionada por JPA y que se convertirá en una tabla
dentro de la base de datos.

En otras palabras:

✔ Clase Java → Tabla en PostgreSQL

@Id

Define la clave primaria de la entidad.

Cada registro en la tabla debe tener un identificador único.

@GeneratedValue

Indica que el valor del id será generado automáticamente por la base de datos.

No necesitamos asignarlo manualmente.

Atributos de la clase
```
private Long id;
private String name;
private String username;
private String email;
```
Cada atributo se convertirá en una columna de la tabla.
Resultado esperado en la base de datos:

| id | name | username | email |
| -- | ---- | -------- | ----- |
Getters y Setters

Permiten acceder y modificar los valores de los atributos.

Spring Boot y JPA los utilizan para:

✔ Leer datos de la BD
✔ Escribir datos en la BD
✔ Serializar objetos JSON

### Resultado

Con esta clase ya tenemos nuestra primera entidad persistente.

## 🗂️ Paso 7: Crear el Repository

Después de definir una **entidad**, el siguiente paso es crear el **Repository**.

## 📘 ¿Qué es un Repository?

Un **Repository** es un componente de **Spring Data JPA** que nos permite interactuar
con la base de datos **sin escribir SQL manualmente**.

Actúa como una capa de acceso a datos (DAO).

Gracias a `JpaRepository`, obtenemos automáticamente métodos como:

✔ Guardar registros → `save()`  
✔ Listar registros → `findAll()`  
✔ Buscar por id → `findById()`  
✔ Eliminar registros → `deleteById()`

Todo esto **sin implementar lógica adicional**.

---

## ✅ Crear el package repository

Dentro de: src/main/java/com/example/demo/repository

## ✅ Luego crear la interfaz:

Click derecho sobre repository, new java file, interface



## ✅ Código del Repository

```
package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}

```

## ✅ Explicación del código

JpaRepository<User, Long>

Indica dos cosas importantes:

User → La entidad que gestionará el repository

Long → El tipo de la clave primaria (@Id)

Spring generará automáticamente la implementación en tiempo de ejecución.

No necesitamos escribir código.

## 🗂️ Paso 8: Ejecutar la aplicación

Una vez creada la entidad y el repository, podemos iniciar la aplicación.
esto creara la tabla con unestro modelo Usuarios en la base de datos

### En tu IDE (VsCode o IJ)

Ubicar la clase principal:

en nuestro caso CrudSpringbootPgReactApplication.java Ejecutar la aplicación usando:
1. Botón Run
o
2. Terminal: mvn spring-boot:run

### ¿Qué ocurre al iniciar Spring Boot?
Cuando la aplicación arranca:

✔ Spring analiza las clases anotadas con @Entity
✔ Hibernate crea o actualiza la estructura en PostgreSQL
✔ Se genera automáticamente la tabla correspondiente

Esto es posible gracias a: spring.jpa.hibernate.ddl-auto=update


### Resultado esperado en PostgreSQL

Se creará la tabla asociada a la entidad User. revisar en PGadmin que se haya creado correctamente

## 🌐 Paso 9: Crear el Controllers 


Después de definir la **Entidad** y el **Repository**, el siguiente componente clave es el **Controller**.

---

## 📘 ¿Qué es un Controller en Spring Boot?

Un **Controller** es la capa encargada de manejar las **peticiones HTTP** que llegan a la aplicación.

Su función es:

✔ Recibir solicitudes del cliente (navegador, Postman, frontend, etc.)  
✔ Procesar la lógica necesaria  
✔ Devolver respuestas (JSON, status, datos, etc.)

En aplicaciones REST, los controladores exponen **endpoints**.

Ejemplo:

1. GET /users
2. POST /user
3. PUT /user/{id}
4. DELETE /user/{id}

## ✅ Crear el controller

Dentro de: src/main/java/com/example/demo/controller

crear la clase: UserController.java

codigo:

```

---

## ✅ Código del Controllers (explicar Create y get all users ya que para los otros hay que explicar el manejo de excepciones)

```java
package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // CREATE
    @PostMapping("/user")
    User newUser(@RequestBody User newUser) {
        return userRepository.save(newUser);
    }

    // READ (ALL)
    @GetMapping("/users")
    List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // READ (BY ID)
    @GetMapping("/user/{id}")
    User getUserById(@PathVariable Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    // UPDATE
    @PutMapping("/user/{id}")
    User updateUser(@RequestBody User updatedUser, @PathVariable Long id) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setName(updatedUser.getName());
                    user.setEmail(updatedUser.getEmail());
                    // agrega aquí otros campos
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    // DELETE
    @DeleteMapping("/user/{id}")
    void deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
    }
}


```

### Explicación del Controller

@RestController

Indica que la clase manejará peticiones REST y devolverá respuestas en formato JSON.

@Autowired

Permite que Spring inyecte automáticamente el UserRepository.

No creamos el objeto manualmente.

### Implementación del CRUD

El controlador implementa las operaciones básicas:

| Operación | Método HTTP | Endpoint     | Descripción        |
| --------- | ----------- | ------------ | ------------------ |
| CREATE    | POST        | `/user`      | Crear usuario      |
| READ ALL  | GET         | `/users`     | Listar usuarios    |
| READ ONE  | GET         | `/user/{id}` | Buscar usuario     |
| UPDATE    | PUT         | `/user/{id}` | Actualizar usuario |
| DELETE    | DELETE      | `/user/{id}` | Eliminar usuario   |


### Probar la API con Postman
Usaremos Postman para enviar solicitudes HTTP en tiempo real.

#### Pasos

1. Ejecutar la aplicación: mvn spring-boot:run
2. Abrir Postman
3. Probar endpoints uno por uno

##### CREATE (POST)
URL http://localhost:8080/user

Body → raw → JSON

```
{
  "name": "Juan Perez",
  "username": "juanp",
  "email": "juan@email.com"
}

```

##### READ ALL (GET)

URL http://localhost:8080/users

Devuelve todos los usuarios.

##### READ BY ID (GET)

URL http://localhost:8080/user/1

Devuelve usuarios por id.

##### UPDATE (PUT)

URL http://localhost:8080/user/1

```
{
  "name": "Juan Actualizado",
  "email": "nuevo@email.com"
}

```

##### DELETE (DELETE)

URL http://localhost:8080/user/1

### Conclusión de este paso

Ahora nuestra aplicación:

✔ Expone endpoints REST
✔ Permite operaciones CRUD completas
✔ Puede probarse en tiempo real con Postman 🚀

## ⚠️ Paso10: Manejo de Excepciones

En aplicaciones reales, es una **mala práctica** devolver errores genéricos como: RuntimeException

Lo correcto es manejar errores de forma controlada y devolver **respuestas HTTP adecuadas**.

Por ejemplo:

✔ 404 → Recurso no encontrado  
✔ 400 → Petición inválida  
✔ 500 → Error interno  

---

## 📘 ¿Qué es el manejo de excepciones?

El manejo de excepciones nos permite:

✔ Detectar errores de negocio  
✔ Devolver mensajes claros al cliente  
✔ Usar códigos HTTP correctos  
✔ Evitar respuestas ambiguas  

En este caso, crearemos una excepción personalizada cuando un usuario no exista.

---

## ✅ Paso 1: Crear la excepción personalizada 

Dentro del package:exception

Crear la clase:UserNotFoundException.java


---

### ✅ Código de la excepción

```
package com.example.demo.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(Long id) {
        super("Usuario no encontrado con id: " + id);
    }
}
```

¿Qué hace esta clase?

Extiende RuntimeException

Permite lanzar un error específico del dominio

Genera un mensaje claro y reutilizable

Paso 2: Usar la excepción en el Controller

Ahora modificamos el controlador para lanzar nuestra excepción personalizada.

```
@GetMapping("/user/{id}")
User getUserById(@PathVariable Long id) {
    return userRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException(id));
}
```

✔ Si el usuario existe → se devuelve
✔ Si no existe → se lanza excepción

Si solo lanzamos la excepción, Spring devolverá un error genérico 500.

Necesitamos interceptarla.

### Paso 3: Crear Controller Advice

Spring Boot permite manejar excepciones globalmente usando: @ControllerAdvice

Crear la clase:UserNotFoundAdvice.java

```
package com.example.demo.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class UserNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> exceptionHandler(UserNotFoundException exception) {

        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("errorMessage", exception.getMessage());

        return errorMap;
    }
}

```

Explicación del Advice
@ControllerAdvice

Indica que esta clase manejará excepciones de toda la aplicación.
@ExceptionHandler

Define qué excepción capturar.

@ResponseStatus(HttpStatus.NOT_FOUND)

Devuelve automáticamente: HTTP 404

@ResponseBody

Permite devolver un JSON en lugar de una vista.

#### Conclusión de este paso

Ahora la API:

✔ Maneja errores correctamente
✔ Devuelve códigos HTTP adecuados
✔ Proporciona mensajes claros al cliente
✔ Sigue buenas prácticas REST 🚀


## 📦 Paso: Uso de DTOs (Data Transfer Objects)

En aplicaciones reales **no es una buena práctica exponer directamente las entidades**.

Aunque funcione, genera varios problemas:

❌ Acoplamiento entre API y base de datos  
❌ Riesgos de seguridad  
❌ Dificultad para evolucionar el modelo  
❌ Exposición de campos sensibles 

La solución es usar **DTOs**.

## 📘 ¿Qué es un DTO?

Un **DTO (Data Transfer Object)** es un objeto diseñado únicamente para
**transportar datos entre capas o hacia el cliente**.

No representa una tabla.

No contiene lógica de negocio.

Solo define **qué datos se envían o reciben**.

## 🎯 Ventajas de usar DTOs

✔ Ocultar campos innecesarios  
✔ Evitar exponer la estructura interna de la entidad  
✔ Controlar la API de forma independiente  
✔ Mejorar seguridad y mantenibilidad 

### ✅ Código del DTO

```
package com.example.demo.dto;

public class UserDTO {

    private String name;
    private String username;
    private String email;

    // Getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
```
### Diferencia clave con la Entidad

Entidad (User):

✔ Tiene @Entity
✔ Se guarda en la base de datos
✔ Contiene id

DTO (UserDTO):

✔ No tiene anotaciones JPA
✔ No representa tabla
✔ Solo transporta datos

### Usar DTO en el Controller (para este paso tienes que modificar tu controlador)

CREATE usando DTO
```
@PostMapping("/user")
User newUser(@RequestBody UserDTO newUserDTO) {

    User user = new User();
    user.setName(newUserDTO.getName());
    user.setUsername(newUserDTO.getUsername());
    user.setEmail(newUserDTO.getEmail());

    return userRepository.save(user);
}
```
✔ El cliente envía un DTO
✔ La entidad se construye internamente
✔ La BD sigue usando User

READ ALL usando DTO

```
@GetMapping("/users")
List<UserDTO> getAllUsers() {

    return userRepository.findAll()
            .stream()
            .map(user -> {
                UserDTO dto = new UserDTO();
                dto.setName(user.getName());
                dto.setUsername(user.getUsername());
                dto.setEmail(user.getEmail());
                return dto;
            })
            .toList();
}

```

✔ La API no expone la entidad
✔ Solo se envían datos necesarios

Conclusión de este paso

Ahora la API:

✔ Está desacoplada del modelo de persistencia
✔ Es más segura
✔ Sigue buenas prácticas REST
✔ Permite evolucionar la entidad sin romper clientes 🚀