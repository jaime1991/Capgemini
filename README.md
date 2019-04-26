csInstrucciones:
----------------
Desarrollar las APIs requeridas para el sistema del banco MyBank basados en el documento de especificacion MyBank Webservice API Specification.pdf

APIs:
---------------
 - Registro (sign-up) 
 - Autenticacion (login)
 - Consulta de saldo (get customer product)
 - Transacciones (retiro, deposito, compra en establecimiento (POS), online) -> save transaction
 - Consulta de ultimos 5 movimientos -> Get last transactions

Describir todos los supuestos necesarios.

La consulta de saldo se hace por producto y por cliente

Ejecutar los scripts de la base de datos mysql ubicados en la carpeta db

El usuario/contraseña de la base de datos debe ser: root/root

Herramientas (Prueba Java):
---------------
- Eclipse
- Docker
- MySql 5.7
- Gradle 4+
- SpringBoot 1.5.8
- JDK 1.8
- JaCoCo/EclEmma (plugin eclipse)

Herramientas (Prueba .NET):
---------------
- Visual Studio
- MySql 5.7
- NuGet
- Docker

Entregables:
---------------
- Codigo fuente de la solucion (Gradle project para Java)
- Pruebas de unidad (JUnit para Java)
- Cobertura de codigo minima: 50%.
- Especificacion de APIs con Swagger
- Dockerfile

Instrucciones para la entrega de la prueba:
--------------------
1. Subir el código fuente en la rama master

2. Crear el archivo Dockerfile con las instrucciones para construir y ejecutar el docker con la aplicación en ejecución, incluyendo la base de datos MySQL


Criterios de evaluación:
-----------------------
1. Completitud de la prueba: 25%
2. Estructuración del proyecto y prácticas de desarrollo: 20%
3. Pruebas de unidad y cobertura: 15%
4. Cumplimiento: 20%
5. Conocimientos: 20%

Happy coding!!