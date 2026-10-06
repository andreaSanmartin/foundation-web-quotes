# nombre-fundacion — frontend

Aplicación web en Angular 9 del sistema de gestión. La descripción completa, la instalación y la configuración están en el [README principal](../README.md).

## Comandos

Requiere **Node.js 12 o 14** (npm 6).

| Comando | Descripción |
|---|---|
| `npm install` | Instala las dependencias |
| `npm start` | Servidor de desarrollo en `http://localhost:4200` |
| `npm run build -- --prod` | Compilación de producción en `dist/nombre-fundacion/` |
| `npm test` | Pruebas unitarias (Karma) |

## Configuración

`src/environments/environment.ts` (desarrollo) y `src/environments/environment.prod.ts` (producción):

| Propiedad | Descripción |
|---|---|
| `apiUrl` | URL base del backend |
| `nombreFundacion` | Nombre de la organización que aparece en correos y fichas PDF |
