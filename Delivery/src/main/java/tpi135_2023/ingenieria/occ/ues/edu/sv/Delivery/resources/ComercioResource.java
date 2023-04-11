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
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.core.UriInfo;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import static tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.boundary.RestResourcePattern.CONTAR_REGISTROS;
import static tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.boundary.RestResourcePattern.ID_NOT_FOUND;
import static tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.boundary.RestResourcePattern.NULL_PARAMETER;
import static tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.boundary.RestResourcePattern.WRONG_PARAMETER;
import tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.control.ComercioBean;
import tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.control.TipoComercioBean;
import tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.entity.Comercio;
import tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.entity.TipoComercio;

/**
 *
 * @author moimo98
 */
@Path("/comercio")
public class ComercioResource implements Serializable {
    @Inject
    ComercioBean comercioBean;
    //CREAR COMERCIO-----------------------------------------------------------------------------

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response crearTest(Comercio comercio, @Context UriInfo info) {

        if (comercio != null) {
            try {
                comercioBean.crear(comercio);

                if (comercio.getIdComercio() != null) {
                    UriBuilder uriBuilder = info.getAbsolutePathBuilder();
                    uriBuilder.path(comercio.getIdComercio().toString());
                    return Response.created(uriBuilder.build()).build();
                }
            } catch (Exception ex) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
            }
            return Response.status(400).header(WRONG_PARAMETER, JsonbBuilder.create().toJson(comercio)).build();
        }
        return Response.status(400)
                .header(NULL_PARAMETER, null).build();
    }
    
    @Path("/{id}/tipocomercio")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerTipocomercio(@PathParam("id") Long idComercio){
       
        if(idComercio!= null){
            Comercio c = comercioBean.findById(idComercio);
            
            if(c!=null){
            
                Long contarResgistros = comercioBean.contar();
                return Response.ok(c).header(CONTAR_REGISTROS,contarResgistros).build();
                
            }
            return Response.status(404)
                .header(ID_NOT_FOUND, idComercio).build();
        
        }
        
        
        return Response.status(400).header(NULL_PARAMETER, null).build();
        
        
    }
  
}
