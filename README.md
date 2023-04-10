# txt2json

V0.1.
Lenguaje : java
Convertidor de un archivo txt a un txt en formato JSON: version de prueba de una lista de postres con un archivo que cuenta con tres campos diferentes : titulo, ingredientes y preparacion. se tiene como indicador para saber cuando es un nuevo elemento de la lista cuando se recibe un "--" seguido del titulo.

### Consideraciones:
* el titulo debe ser precedido de --
* los ingredientes deben ser precedidos por "Ingredientes"
* la preparacion debe ser precedido por "Preparación"

### Para probarlo:
- al ejecutar el archivo se debe seleccionar el txt que se desea convertir (debe tener el formato arriba descrito)
- luego de procesarse se genera un archivo en la raiz del proyecto con el json correctamente formateado.

### Mejoras a futuro:
- poder definir de una manera mas generica los separadores de los elementos del json.
- que no sean solo 3 elementos por objeto de la lista
- que se pueda seleccionar la ruta de descarga del archivo

***
Autor: Juan Sebastian Alzate Leon
