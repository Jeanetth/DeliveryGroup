# Delivery App Custom Image Docker

_Imagen de docker basada en payara/server-full:6.2023.3-jdk17_

## Comenzando 🚀

_Estas instrucciones te permitirán obtener una copia del proyecto en funcionamiento en tu máquina local el servicio de docker debe estar en 
arrancado para desarrollar._

### Verificar

Verificar si tiene la imagen payara/server-full:6.2023.3-jdk17


### Descargar la carpeta en formato zip

ir a la ruta C:\\{Su path donde lo descargaron}\imageCustom_DeliveryApp\payara_pg_full

Verificar si tiene dos archivos en ese path los cuales son: 

### postgresql-42.6.0.jar
### Dockerfile

No modificar el docker file ya que puede afectar la construccion de la 
imagen-custom

### Realizar el siguiente comando 

docker build -t delivery_payara/full:6.2023.3-jdk17 .

### Verificar en su repositorio si la imagen se creo

docker images
