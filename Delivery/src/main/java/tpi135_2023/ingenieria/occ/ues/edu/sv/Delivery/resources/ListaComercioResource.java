/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.boundary.RestResourcePattern;
import tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.control.ComercioBean;
import tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.entity.Comercio;

/**
 *
 * @author Mariana
 */
@Path("/all")
public class ListaComercioResource {
    @Inject
    ComercioBean comercioBean;
            
    
    @GET   
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        List<Comercio> usuarios = comercioBean.findAll();// Obtén los usuarios de alguna manera

    // Devuelve la lista de usuarios en la respuesta
    return Response.ok(usuarios).build();
    }
}
