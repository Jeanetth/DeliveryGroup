/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.resources;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

/**
 *
 * @author Mariana
 */
@Path("/tipocomercio")
public class TipoComercioResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public HelloRecord hello(){
        return new HelloRecord("Hello MARIANA si funciona");
    }
    
}
