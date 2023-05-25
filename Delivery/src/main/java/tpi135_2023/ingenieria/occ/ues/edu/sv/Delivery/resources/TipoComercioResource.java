/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.resources;

import jakarta.inject.Inject;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.core.UriInfo;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.boundary.RestResourcePattern.NULL_PARAMETER;
import static tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.boundary.RestResourcePattern.WRONG_PARAMETER;
import tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.control.TipoComercioBean;
import tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.entity.TipoComercio;

/**
 *
 * @author moimo98
 */
@Path("/tipocomercio")
public class TipoComercioResource {
    
    @Inject
    TipoComercioBean tipoComerciobean;
    
    
    
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response crearTipoComercio(TipoComercio tipoComercio, @Context UriInfo info) {

        if (tipoComercio != null) {
            try {
                tipoComerciobean.crear(tipoComercio);

                if (tipoComercio.getIdTipoComercio() != null) {
                    UriBuilder uriBuilder = info.getAbsolutePathBuilder();
                    uriBuilder.path(tipoComercio.getIdTipoComercio().toString());
                    return Response.created(uriBuilder.build()).build();
                }
            } catch (Exception ex) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
            }
            return Response.status(400).header(WRONG_PARAMETER, JsonbBuilder.create().toJson(tipoComercio)).build();
        }
        return Response.status(400)
                .header(NULL_PARAMETER, null).build();
    }
    
    
   
                    //METODO FINd ALL------------------------------------------------------------------------------
   @Path("/all")
     @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response encontrartodos() {
        List<TipoComercio> registros = tipoComerciobean.findAll();
        Long total = tipoComerciobean.contar();
        return Response.ok(registros)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS")
                .header("Access-Control-Allow-Headers", "Content-Type")
                .header("total registros", total)
                .build();
    }


}
