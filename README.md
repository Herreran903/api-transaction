# api-transaction

Microservicio de ventas y abastecimiento construido con Java 17 y Spring Boot, siguiendo
arquitectura hexagonal. Registra las compras de los clientes y las entradas de mercancía
de bodega, coordinándose con el catálogo mediante **OpenFeign**.

Forma parte del reto **Emazon**, una tienda virtual dividida en microservicios
independientes, desarrollado durante el **Bootcamp Power Up de Pragma** (2024).

## Arquitectura

Puertos y adaptadores, con el dominio aislado de la infraestructura:

```
domain/     modelos, reglas de negocio, casos de uso y puertos
            (supply, role, error)
            spi/  puerto de salida hacia stock
app/        handlers de aplicación, DTOs y mappers de MapStruct
infra/      adaptadores de entrada (REST), salida (JPA y Feign),
            seguridad, manejo de excepciones y OpenAPI
```

## Endpoints

| Método | Ruta | Rol requerido | Descripción |
|---|---|---|---|
| `POST` | `/sale/add` | `CLIENT` | Registrar una venta |
| `POST` | `/supply/add` | `WAREHOUSE_ASSISTANT` | Registrar entrada de mercancía |
| `POST` | `/supply/restock-date` | `CLIENT` | Consultar fecha estimada de reabastecimiento |

Documentación interactiva en `/swagger-ui.html` una vez levantado el servicio.

## Comunicación entre servicios

```
api-cart ──Feign──> api-transaction ──Feign──> api-stock
```

`StockFeignClient` actualiza las existencias en `api-stock` al registrar una venta o un
abastecimiento. `FeignClientInterceptor` propaga el JWT del usuario en cada llamada
saliente, de modo que la autorización se evalúa sobre el usuario que inició la petición.

## Reglas de negocio implementadas

- Una venta descuenta existencias del catálogo; un abastecimiento las incrementa
- Reversión de los cambios ante error, para no dejar inventario inconsistente
- Registro del reporte de compra: comprador, fecha, costo total y artículos
- Fecha de reabastecimiento consultable por el cliente cuando un artículo se agota
- Cada operación restringida al rol que corresponde

## Stack

- **Java 17**, **Spring Boot 3.3**
- Spring Web, Spring Data JPA, Spring Security, Spring Validation
- **Spring Cloud OpenFeign** para las llamadas entre servicios
- **MySQL** como motor de persistencia
- **MapStruct 1.5.5**, **JJWT 0.12.6**, **springdoc-openapi 2.6.0**
- **JUnit 5** y **Mockito** para pruebas

## Ejecución local

Requiere Java 17, MySQL, y que `api-stock` y `api-user` estén levantados.

```bash
git clone https://github.com/Herreran903/api-transaction.git
cd api-transaction
./gradlew bootRun
```

Configura en `src/main/resources/application.properties` la conexión a la base de datos,
la clave de firma del JWT —debe coincidir con la de `api-user`— y la URL de `api-stock`.

```bash
./gradlew test
```

## Servicios relacionados

- [`api-user`](https://github.com/Herreran903/api-user) — autenticación y usuarios
- [`api-stock`](https://github.com/Herreran903/api-stock) — catálogo e inventario
- [`api-cart`](https://github.com/Herreran903/api-cart) — carrito de compras
