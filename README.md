# nombre-fundacion

Sistema web de gestión para fundaciones y organizaciones sociales. Permite llevar el registro de beneficiarios y sus fichas socioeconómicas, agendar citas médicas con recordatorio por correo, gestionar las actividades de los voluntarios y controlar las donaciones recibidas y entregadas.

Se desarrolló como proyecto de vinculación con la sociedad para una fundación. Esta versión es genérica: el nombre, el logo, la base de datos y las credenciales se configuran para cada organización, sin cambiar el código.

---

## Contenido

- [Funcionalidades](#funcionalidades)
- [Tecnologías](#tecnologías)
- [Estructura del proyecto](#estructura-del-proyecto)
- [Requisitos](#requisitos)
- [Instalación y ejecución](#instalación-y-ejecución)
- [Variables de entorno del backend](#variables-de-entorno-del-backend)
- [Personalización para una organización](#personalización-para-una-organización)
- [Docker](#docker): construir, subir imágenes a Docker Hub y desplegar
- [Despliegue en producción (sin Docker)](#despliegue-en-producción-sin-docker)
- [Problemas comunes](#problemas-comunes)
- [Limitaciones conocidas](#limitaciones-conocidas)
- [Autores](#autores)

---

## Funcionalidades

| Módulo | Qué permite hacer |
|---|---|
| **Beneficiarios** | Registrar personas, su ficha socioeconómica, sus familiares, documentos adjuntos y observaciones. Ver el historial completo de cada beneficiario y exportarlo a PDF. |
| **Citas médicas** | Agendar citas para beneficiarios con un trabajador acompañante, centro médico y especialidad. Verlas en un calendario y enviar recordatorios por correo electrónico. |
| **Centros médicos y especialidades** | Mantener el catálogo de centros médicos y especialidades disponibles para las citas. |
| **Actividades** | Registrar las actividades de los voluntarios, sus tipos y la asistencia, y generar reportes de actividades. |
| **Donaciones** | Registrar los productos donados y su entrega a los beneficiarios. |
| **Usuarios** | Crear, editar y deshabilitar usuarios del sistema. Cada usuario puede cambiar su contraseña desde "Mi Perfil". |
| **Reportes** | Exportar listados a PDF y Excel (beneficiarios, usuarios, citas, donaciones, actividades). |

### Roles de usuario

| Rol | Uso típico |
|---|---|
| **SuperAdministrador** | Acceso completo, incluida la gestión de usuarios. |
| **Administrador** | Gestión de beneficiarios, citas, donaciones y reportes. |
| **Voluntario Interno** | Registro de sus actividades. |
| **Voluntario Externo** | Registro de sus actividades. |

Al iniciar sesión se elige el tipo de usuario junto con la cédula y la contraseña.

---

## Tecnologías

| Parte | Tecnología |
|---|---|
| Backend | Java 8, Spring Boot 2.4, Spring Data MongoDB, Spring Mail, Maven (incluido mediante `mvnw`) |
| Base de datos | MongoDB (local o MongoDB Atlas) |
| Frontend | Angular 9, PrimeNG, Bootstrap 4, pdfmake / jsPDF, angular-calendar |

---

## Estructura del proyecto

```
.
├── docker-compose.yml             MongoDB + backend + frontend con Docker
├── .env.docker.example            Variables para docker compose
│
├── nombre-fundacion-backend/      API REST (Spring Boot)
│   ├── Dockerfile
│   ├── .env.example               Plantilla de configuración
│   ├── mvnw / mvnw.cmd            Maven incluido (Linux-macOS / Windows)
│   └── src/main/java/com/app/ista/
│       ├── config/                CORS y creación del administrador inicial
│       ├── controller/            Endpoints REST
│       ├── model/                 Documentos de MongoDB
│       ├── repository/            Acceso a datos
│       └── service/               Lógica de negocio y envío de correos
│
└── nombre-fundacion-frontend/     Aplicación web (Angular)
    ├── Dockerfile
    ├── docker/                    Configuración de Nginx para la imagen
    └── src/
        ├── environments/          URL del backend y nombre de la organización
        ├── assets/img/            Logos e imágenes
        └── app/
            ├── components/        Pantallas
            ├── models/            Modelos de datos
            └── services/          Comunicación con la API
```

---

## Requisitos

| Herramienta | Versión | Notas |
|---|---|---|
| **JDK** | 8 | Necesario para el backend. Maven no hace falta instalarlo: se usa `mvnw`. |
| **Node.js** | 12 o 14 (con npm 6) | Angular 9 **no compila** con versiones más nuevas de Node. Si ya tienes otra versión, usa [nvm](https://github.com/nvm-sh/nvm) o [nvm-windows](https://github.com/coreybutler/nvm-windows) para instalar Node 14 junto a ella. |
| **MongoDB** | 4.x o superior | Una instalación local, un contenedor Docker o una base gratuita en [MongoDB Atlas](https://www.mongodb.com/atlas). |
| **Cuenta de correo SMTP** | — | Opcional. Solo es necesaria para enviar los recordatorios de citas. |

Si usas [Docker](#docker), solo necesitas Docker: no hace falta instalar JDK, Node ni MongoDB.

---

## Instalación y ejecución

### 1. Clonar el repositorio

```bash
git clone https://github.com/andreaSanmartin/foundation-web-quotes.git
cd foundation-web-quotes
```

### 2. Preparar la base de datos

Usa **una** de estas opciones:

- **MongoDB local:** instálalo y déjalo corriendo en el puerto 27017. No hace falta crear la base, se crea sola.
- **Docker:**
  ```bash
  docker run -d --name mongo -p 27017:27017 mongo:4.4
  ```
- **MongoDB Atlas:** crea un cluster, un usuario de base de datos y permite el acceso desde tu IP. Copia la cadena de conexión, que tiene la forma `mongodb+srv://<usuario>:<contraseña>@<cluster>.mongodb.net/<base>?retryWrites=true`.

### 3. Configurar el backend

```bash
cd nombre-fundacion-backend
cp .env.example .env
```

En Windows (PowerShell) se usa `copy .env.example .env`.

Edita `.env` y completa como mínimo:

```properties
MONGODB_URI=mongodb://localhost:27017/nombre_fundacion
ADMIN_CEDULA=<cédula del primer administrador>
ADMIN_PASSWORD=<contraseña del primer administrador>
```

El archivo `.env` contiene datos sensibles y **no se sube al repositorio** (ya está en `.gitignore`). En la sección de [variables de entorno](#variables-de-entorno-del-backend) están todas las opciones.

### 4. Ejecutar el backend

```bash
# Linux / macOS
./mvnw spring-boot:run

# Windows
mvnw.cmd spring-boot:run
```

La primera vez Maven descarga las dependencias, así que tarda unos minutos. El backend está listo cuando aparece:

```
Tomcat started on port(s): 3000
```

Si la base no tenía usuarios, también verás:

```
Usuario SuperAdministrador inicial creado con cedula ...
```

### 5. Configurar el frontend

En `nombre-fundacion-frontend/src/environments/environment.ts`:

```ts
export const environment = {
  production: false,
  apiUrl: 'http://localhost:3000',          // URL del backend
  nombreFundacion: 'Nombre Fundación'       // Nombre de la organización
};
```

Si no cambiaste el puerto del backend, los valores por defecto funcionan tal cual.

### 6. Instalar y ejecutar el frontend

En otra terminal:

```bash
cd nombre-fundacion-frontend
npm install
npm start
```

Cuando aparezca `Compiled successfully`, abre **http://localhost:4200**.

### 7. Primer inicio de sesión

1. Ingresa la cédula y contraseña definidas en `ADMIN_CEDULA` y `ADMIN_PASSWORD`.
2. Selecciona el tipo de usuario **SuperAdministrador**.
3. Crea los demás usuarios en **Personas → Usuarios → Registrar Usuario**.
4. Cambia la contraseña inicial desde **Mi Perfil**.

El administrador inicial se crea **solo una vez**, cuando la colección de usuarios está vacía. Los reinicios posteriores no lo duplican ni lo modifican.

---

## Variables de entorno del backend

Se definen en `nombre-fundacion-backend/.env` o como variables de entorno del sistema o del servidor. Las del sistema tienen prioridad sobre el archivo.

| Variable | Descripción | Valor por defecto |
|---|---|---|
| `PORT` | Puerto del backend | `3000` |
| `MONGODB_URI` | Cadena de conexión de MongoDB | `mongodb://localhost:27017/nombre_fundacion` |
| `CORS_ALLOWED_ORIGINS` | URLs del frontend que pueden llamar a la API, separadas por coma | `http://localhost:4200` |
| `ADMIN_CEDULA` | Cédula del SuperAdministrador inicial | vacío (no se crea) |
| `ADMIN_PASSWORD` | Contraseña del SuperAdministrador inicial | vacío (no se crea) |
| `ADMIN_NOMBRE` | Nombre del SuperAdministrador inicial | `Administrador` |
| `MAIL_HOST` | Servidor SMTP | `smtp.gmail.com` |
| `MAIL_PORT` | Puerto SMTP | `587` |
| `MAIL_USERNAME` | Usuario de la cuenta de correo | vacío |
| `MAIL_PASSWORD` | Contraseña SMTP. En Gmail debe ser una [contraseña de aplicación](https://support.google.com/accounts/answer/185833), no la contraseña normal. | vacío |
| `MAIL_FROM` | Remitente de los correos | igual a `MAIL_USERNAME` |
| `MAX_FILE_SIZE` | Tamaño máximo por archivo subido | `20MB` |
| `MAX_REQUEST_SIZE` | Tamaño máximo por petición | `20MB` |

Si el correo no está configurado el sistema funciona igual, pero los recordatorios de citas no se envían.

---

## Personalización para una organización

| Qué | Dónde |
|---|---|
| **Nombre de la organización** (correos, fichas PDF) | `nombreFundacion` en `src/environments/environment.ts` y `environment.prod.ts` |
| **Título de la pestaña del navegador** | `<title>` en `src/index.html` |
| **Logo de la barra de navegación y de los PDF** | `src/assets/img/logo.png` |
| **Logo de la pantalla de inicio de sesión** | `src/assets/img/descargar.png` |
| **Ícono del navegador** | `src/favicon.ico` |
| **Logo de algunos reportes PDF** (citas y actividades) | Propiedad `img` (imagen en base64) en `src/app/services/actividades.service.ts` |
| **Imagen de la pantalla de inicio** | `src/app/components/inicio-super-admin/inicio-super-admin.component.html` |

Todas las rutas son relativas a `nombre-fundacion-frontend/`. Para el logo en base64 puedes convertir tu imagen en un sitio como [base64-image.de](https://www.base64-image.de/) y pegar el resultado completo (`data:image/png;base64,...`).

---

## Docker

Con Docker no hace falta instalar Java, Node ni MongoDB: todo corre en contenedores. Solo necesitas [Docker Desktop](https://www.docker.com/products/docker-desktop/) (Windows / macOS) o Docker Engine con el plugin `compose` (Linux).

### Archivos de Docker

| Archivo | Qué hace |
|---|---|
| `nombre-fundacion-backend/Dockerfile` | Compila el `.jar` con Maven y lo ejecuta con Java 8. |
| `nombre-fundacion-frontend/Dockerfile` | Compila Angular con Node 14 y lo sirve con Nginx. |
| `nombre-fundacion-frontend/docker/nginx.conf` | Configuración de Nginx para la aplicación. |
| `nombre-fundacion-frontend/docker/40-config-runtime.sh` | Al iniciar el contenedor, coloca la URL del backend y el nombre de la organización. Así **la misma imagen sirve para cualquier servidor**, sin recompilar. |
| `docker-compose.yml` | Levanta MongoDB, backend y frontend juntos. |
| `.env.docker.example` | Plantilla de variables para `docker compose`. |

### 1. Configurar

Desde la raíz del repositorio:

```bash
cp nombre-fundacion-backend/.env.example nombre-fundacion-backend/.env
cp .env.docker.example .env
```

En Windows (PowerShell) usa `copy` en lugar de `cp`.

- En **`nombre-fundacion-backend/.env`** completa `ADMIN_CEDULA`, `ADMIN_PASSWORD` y, si quieres recordatorios, los datos de correo. `MONGODB_URI` no importa aquí: Docker usa su propio MongoDB.
- En **`.env`** (raíz) pon tu usuario de Docker Hub en `DOCKERHUB_USER`, la versión en `TAG` y, si cambian, los puertos y las URLs.

Los dos archivos `.env` están en `.gitignore` y no se suben al repositorio.

### 2. Construir y ejecutar en tu computadora

```bash
docker compose up -d --build
```

La primera vez tarda varios minutos (descarga las dependencias de Maven y npm). Después:

| Servicio | Dirección |
|---|---|
| Aplicación web | http://localhost:8080 |
| API (backend) | http://localhost:3000 |

Comandos útiles:

```bash
docker compose ps                  # ver el estado de los contenedores
docker compose logs -f backend     # ver los logs del backend
docker compose down                # detener todo (los datos se conservan)
docker compose down -v             # detener y BORRAR la base de datos y los documentos
```

Los datos de MongoDB y los documentos subidos de los beneficiarios se guardan en los volúmenes `mongo-data` y `documentos`, así que no se pierden al reiniciar o actualizar los contenedores.

### 3. Subir las imágenes a Docker Hub

1. Crea una cuenta gratuita en [hub.docker.com](https://hub.docker.com).
2. Inicia sesión desde la terminal (te pedirá tu usuario y contraseña o un *access token*):
   ```bash
   docker login
   ```
3. Revisa que `DOCKERHUB_USER` y `TAG` del archivo `.env` de la raíz tengan tu usuario y la versión, por ejemplo `TAG=1.0.0`.
4. Construye y sube las imágenes:
   ```bash
   docker compose build
   docker compose push backend frontend
   ```

Esto publica `tu-usuario/nombre-fundacion-backend:1.0.0` y `tu-usuario/nombre-fundacion-frontend:1.0.0`. Por defecto los repositorios nuevos de Docker Hub son **públicos**. Las imágenes no contienen contraseñas, porque el archivo `.env` no se copia dentro de ellas, pero si prefieres que sean privadas cámbialo en Docker Hub.

Si no usas `docker compose`, el equivalente con `docker` es:

```bash
docker build -t tu-usuario/nombre-fundacion-backend:1.0.0 ./nombre-fundacion-backend
docker build -t tu-usuario/nombre-fundacion-frontend:1.0.0 ./nombre-fundacion-frontend
docker push tu-usuario/nombre-fundacion-backend:1.0.0
docker push tu-usuario/nombre-fundacion-frontend:1.0.0
```

> **Mac con chip Apple (M1/M2/M3):** la mayoría de los servidores son Intel/AMD. Agrega `--platform linux/amd64` a `docker build`, o antes de `docker compose build` ejecuta `export DOCKER_DEFAULT_PLATFORM=linux/amd64`.

### 4. Desplegar en un servidor

En el servidor (por ejemplo un VPS con Ubuntu) solo hace falta Docker. No necesitas el código fuente, solo estos archivos:

```
docker-compose.yml
.env                              (copiado de .env.docker.example)
nombre-fundacion-backend/.env     (copiado de .env.example)
```

1. Instala Docker:
   ```bash
   curl -fsSL https://get.docker.com | sh
   ```
2. Copia los tres archivos al servidor, respetando las carpetas, o clona el repositorio.
3. En el `.env` de la raíz pon las direcciones **públicas**. Por ejemplo, si la IP del servidor es `203.0.113.10`:
   ```properties
   DOCKERHUB_USER=tu-usuario
   TAG=1.0.0
   API_URL=http://203.0.113.10:3000
   CORS_ALLOWED_ORIGINS=http://203.0.113.10:8080
   NOMBRE_FUNDACION=Fundación Ejemplo
   ```
   `API_URL` es la dirección con la que **el navegador del usuario** llega al backend, por eso debe ser pública.
4. Descarga las imágenes y arranca:
   ```bash
   docker compose pull
   docker compose up -d
   ```
5. Abre `http://203.0.113.10:8080`.

Para usar **MongoDB Atlas** en lugar del MongoDB del contenedor, define `MONGODB_URI` en el `.env` de la raíz. El servicio `mongo` puede quedarse, solo que no se usará.

Para usar un **dominio con HTTPS** (`https://fundacion.org`), pon delante un proxy inverso como [Caddy](https://caddyserver.com/) o Nginx con Let's Encrypt, que reenvíe al puerto 8080 (frontend) y al 3000 (backend). Después actualiza `API_URL` y `CORS_ALLOWED_ORIGINS` con las URLs `https://`.

### 5. Actualizar a una nueva versión

En tu computadora, después de hacer cambios en el código:

```bash
# cambia TAG en .env, por ejemplo TAG=1.0.1
docker compose build
docker compose push backend frontend
```

En el servidor:

```bash
# cambia TAG en .env al mismo valor
docker compose pull
docker compose up -d
```

Los datos se conservan. Si solo cambias `API_URL` o `NOMBRE_FUNDACION`, no hace falta reconstruir: basta con `docker compose up -d`, que recrea el contenedor con los valores nuevos.

---

## Despliegue en producción (sin Docker)

### Backend

```bash
cd nombre-fundacion-backend
./mvnw clean package -DskipTests
java -jar target/nombre-fundacion-backend-0.0.1-SNAPSHOT.jar
```

Define las variables de entorno en el servidor (`MONGODB_URI`, `CORS_ALLOWED_ORIGINS` con el dominio real del frontend, credenciales de correo, etc.).

### Frontend

1. Pon la URL pública del backend en `apiUrl` de `src/environments/environment.prod.ts`.
2. Compila:
   ```bash
   cd nombre-fundacion-frontend
   npm run build -- --prod
   ```
3. Publica el contenido de `dist/nombre-fundacion/` en cualquier servidor de archivos estáticos (Nginx, Apache, Netlify, etc.). La aplicación usa rutas con `#`, así que no necesita configuración especial de redirecciones.

---

## Problemas comunes

| Problema | Solución |
|---|---|
| `npm install` o `npm start` falla con errores de OpenSSL, `node-sass` o `node-gyp` | Estás usando una versión de Node demasiado nueva. Cambia a Node 14 (`nvm use 14`). |
| El puerto 4200 está ocupado | Ejecuta `npm start -- --port 4300` y agrega `http://localhost:4300` a `CORS_ALLOWED_ORIGINS`. |
| El navegador muestra errores de **CORS** | La URL del frontend no está en `CORS_ALLOWED_ORIGINS`. Agrégala y reinicia el backend. |
| "Datos erróneos" al iniciar sesión | Revisa la cédula, la contraseña **y el tipo de usuario**: los tres deben coincidir. |
| No se creó el administrador inicial | Ya existían usuarios en la base, o faltan `ADMIN_CEDULA` / `ADMIN_PASSWORD` en `.env`. |
| `./mvnw: Permission denied` (Linux/macOS) | Ejecuta `chmod +x mvnw`. |
| El backend no conecta a MongoDB Atlas | Verifica la cadena de conexión y que tu IP esté permitida en *Network Access* de Atlas. |
| Los correos no se envían | Revisa `MAIL_USERNAME` y `MAIL_PASSWORD`. En Gmail usa una contraseña de aplicación. |

---

## Limitaciones conocidas

Antes de usar el sistema con datos reales en producción, conviene tener en cuenta y, si es posible, resolver lo siguiente:

- Las contraseñas se guardan **en texto plano** en la base de datos. Se recomienda usar hashing, por ejemplo con BCrypt.
- El inicio de sesión envía la contraseña como parámetro de la URL (`GET`). Se recomienda cambiarlo a `POST`.
- La API **no tiene autenticación**: los permisos por rol solo se controlan en el frontend. Se recomienda agregar Spring Security con JWT.
- Las versiones de Angular (9), Spring Boot (2.4) y Java (8) ya no tienen soporte oficial.

El sistema guarda datos personales y socioeconómicos sensibles. Cumple con la normativa de protección de datos que aplique en tu país.

---

## Autores

Proyecto desarrollado por estudiantes del Instituto Superior Tecnológico del Azuay como parte del programa de vinculación con la sociedad.
