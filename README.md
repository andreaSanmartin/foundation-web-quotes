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
- [Despliegue en producción](#despliegue-en-producción)
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
├── nombre-fundacion-backend/      API REST (Spring Boot)
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

---

## Instalación y ejecución

### 1. Clonar el repositorio

```bash
git clone <url-del-repositorio>
cd <carpeta-del-repositorio>
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

## Despliegue en producción

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
