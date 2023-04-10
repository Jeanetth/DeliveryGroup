/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.boundary;

/**
 *
 * @author Mariana
 */
public class Constantes {
    public static final String NOMBRE_PU="deliveryPU";
    public static final String DB_USER="postgres";
    public static final String DB_PASSWORD="abc123";
    public static final String DB_NAME="delivery";
    public static final String DB_URL="jdbc:tc:postgresql:9.6.8:///delivery";
    public static final String PATH_WAR="target/Delivery-1.0.0-SNAPSHOT.war";
    public static final String SCRIT_INIT_DB="iniciarDelivery.sql";
    public static final String IMAGE_POSTGRES="postgres:13-alpine";
    public static final String IMAGE_PAYARA="payara/server-full:6.2023.3-jdk17";
    public static final String PAYARA_SERVER_FULL_LOG=".*JMXStartupService has started JMXConnector on JMXService.*";
    public static final String PAYARA_MICRO_LOG=".* Payara Micro .* ready in .*\\s";
    public static final String PATH_TO_PAYARA_SERVER_WAR="/opt/payara/deployments/aplicacion.war";
    public static final String IMAGE_DELIVERY_SERVER="delivery_payara/full:6.2023.3-jdk17";
    
    
    
}
