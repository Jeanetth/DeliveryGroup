FROM payara/server-full:6.2023.3-jdk17
# Instalar curl
USER root
RUN apt-get update && apt-get install -y curl
#Descarga el driver
RUN curl -o /opt/payara/appserver/glassfish/domains/domain1/lib/postgresql-42.6.0.jar https://jdbc.postgresql.org/download/postgresql-42.6.0.jar
# Cambiar el usuario a payara
USER payara


