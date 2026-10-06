# ---------- Etapa 1: compilar Angular ----------
# Angular 9 necesita Node 14 o anterior
FROM node:14-alpine AS build
WORKDIR /build

COPY package.json package-lock.json ./
RUN npm ci --no-audit --no-fund

COPY . .
# Se compila con marcadores que se reemplazan al iniciar el contenedor,
# asi la misma imagen sirve para cualquier servidor u organizacion.
RUN sed -i \
      -e "s|apiUrl: '[^']*'|apiUrl: '__API_URL__'|" \
      -e "s|nombreFundacion: '[^']*'|nombreFundacion: '__NOMBRE_FUNDACION__'|" \
      src/environments/environment.prod.ts \
 && npx ng build --prod

# ---------- Etapa 2: servir con Nginx ----------
FROM nginx:1.25-alpine

COPY docker/nginx.conf /etc/nginx/conf.d/default.conf
COPY docker/40-config-runtime.sh /docker-entrypoint.d/40-config-runtime.sh
RUN chmod +x /docker-entrypoint.d/40-config-runtime.sh
COPY --from=build /build/dist/nombre-fundacion /usr/share/nginx/html

# Valores por defecto; se cambian con variables de entorno al crear el contenedor
ENV API_URL=http://localhost:3000 \
    NOMBRE_FUNDACION="Nombre Fundación"
EXPOSE 80
