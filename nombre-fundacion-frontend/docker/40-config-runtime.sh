#!/bin/sh
# Reemplaza los marcadores __API_URL__ y __NOMBRE_FUNDACION__ del build de Angular
# por los valores de las variables de entorno API_URL y NOMBRE_FUNDACION.
# La imagen de Nginx ejecuta automaticamente los scripts de /docker-entrypoint.d/.
set -e

HTML_DIR=/usr/share/nginx/html

if ! grep -rqlE "__API_URL__|__NOMBRE_FUNDACION__" "$HTML_DIR"; then
  echo "config-runtime: los valores ya fueron aplicados. Para cambiarlos, recrea el contenedor."
  exit 0
fi

# Sin barra final, porque el codigo agrega "/ruta"
CFG_API_URL="${API_URL%/}"
CFG_NOMBRE="$NOMBRE_FUNDACION"
export CFG_API_URL CFG_NOMBRE

# Reemplazo literal con awk (sin expresiones regulares). Los valores se escapan
# como texto de JavaScript, asi caracteres como & / | \ " ' no causan problemas.
# (\047 es la comilla simple y \140 la comilla invertida.)
for f in "$HTML_DIR"/*.js; do
  awk '
    function escapar_js(valor,    salida, c, i) {
      salida = ""
      for (i = 1; i <= length(valor); i++) {
        c = substr(valor, i, 1)
        if (c == "\\" || c == "\"" || c == "\047" || c == "\140") salida = salida "\\"
        salida = salida c
      }
      return salida
    }
    function reemplazar(texto, buscar, valor,    salida, i) {
      salida = ""
      while ((i = index(texto, buscar)) > 0) {
        salida = salida substr(texto, 1, i - 1) valor
        texto = substr(texto, i + length(buscar))
      }
      return salida texto
    }
    BEGIN {
      api = escapar_js(ENVIRON["CFG_API_URL"])
      nombre = escapar_js(ENVIRON["CFG_NOMBRE"])
    }
    {
      linea = reemplazar($0, "__API_URL__", api)
      print reemplazar(linea, "__NOMBRE_FUNDACION__", nombre)
    }
  ' "$f" > "$f.tmp"
  mv "$f.tmp" "$f"
done

echo "config-runtime: API_URL=$CFG_API_URL NOMBRE_FUNDACION=$CFG_NOMBRE"
