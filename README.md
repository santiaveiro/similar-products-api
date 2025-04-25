#  Similar Products API

Este proyecto es una **API REST desarrollada con Spring Boot. El objetivo fue crear un servicio que devuelva el detalle de productos similares a un producto dado, cumpliendo con el contrato.

---

##  Stack Tecnológico

- Java 17
- Spring Boot 3.4.5
- Maven
- Lombok
- Docker & Docker Compose
- Postman (testing manual)
- Git + GitHub Desktop (flujo GitFlow)

---

##  Arquitectura del Proyecto

El proyecto se desarrolló bajo el patrón **MVC (Model-View-Controller)** y siguiendo principios de diseño SOLID para facilitar escalabilidad y mantenibilidad.

### Estructura de Carpetas

src/
├── main/
│   ├── java/
│   │   └── com.backendtest.similarproducts/
│   │       ├── controller/       --> Controlador REST
│   │       ├── client/           --> Comunicación con servicios externos (mock)
│   │       ├── service/          --> Lógica de negocio
│   │       │   └── impl/
│   │       ├── model/            --> Representación de entidades
│   │       └── exception/        --> Manejo global de errores
│   └── resources/
│       └── application.properties

## Instrucciones para ejecutar la aplicación

Requisitos previos:

- Java 17
- Maven
- Docker Desktop (activo y corriendo)
- Puerto `5000` libre

### 1. Configuración inicial

Verifique que el archivo `src/main/resources/application.properties` contenga lo siguiente:

```properties
server.port=5000
spring.application.name=similarproducts

### 2. Compilación y ejecución de la API

Desde la raíz del proyecto, ejecutar:

#### Con Maven Wrapper (recomendado):

```bash
./mvnw spring-boot:run       # Unix/Linux/macOS
.\mvnw.cmd spring-boot:run   # Windows (PowerShell)

### 3. Preparar entorno simulado (Mock)

Para ejecutar correctamente la aplicación, es necesario levantar el entorno simulado provisto en el repositorio `backend-dev-test`.

#### Pasos:

1. Clonar o ubicar el repositorio `backend-dev-test` en local.
2. Acceder a la carpeta `backendDevTest`:

```bash
cd path/al/repositorio/backend-dev-test/backendDevTest

3. Ejecutar los contenedores necesarios:

docker-compose up -d simulado influxdb grafana


### 4. Probar el endpoint principal

Una vez que la aplicación (`similar-products-api`) y el entorno simulado (`simulado`) estén en ejecución, se puede realizar la prueba funcional del servicio.

#### Request:

```http
GET http://localhost:5000/product/1/similar

Al consumir exitosamente el endpoint `/product/{productId}/similar`, se espera recibir un array JSON con el detalle de los productos similares, respetando la estructura definida en el contrato OpenAPI:

```json
[
  {
    "id": "2",
    "name": "Dress",
    "price": 19.99,
    "availability": true
  },
  {
    "id": "3",
    "name": "Blazer",
    "price": 29.99,
    "availability": false
  },
  {
    "id": "4",
    "name": "Boots",
    "price": 39.99,
    "availability": true
  }
]



### 5. Test de rendimiento (opcional)

Una vez que la aplicación esté funcionando y el entorno simulado esté activo, se puede ejecutar una prueba de carga para validar el rendimiento del servicio.

#### Pasos para ejecutar el test:

1. Asegurarse de que los contenedores de Docker (`simulado`, `grafana`, `influxdb`) estén corriendo.

2. Desde la carpeta del entorno de pruebas, ejecutar el siguiente comando:

```bash
docker-compose run --rm k6 run scripts/test.js
