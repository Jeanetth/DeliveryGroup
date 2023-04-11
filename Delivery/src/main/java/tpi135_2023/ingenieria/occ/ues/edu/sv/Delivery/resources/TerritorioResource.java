/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.resources;
import jakarta.inject.Inject;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.Consumes;
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
import java.math.BigInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import static tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.boundary.RestResourcePattern.NULL_PARAMETER;
import static tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.boundary.RestResourcePattern.WRONG_PARAMETER;
import tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.control.ComercioBean;
import tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.control.DireccionBean;
import tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.control.SucursalBean;
import tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.control.TerritorioBean;
import tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.entity.Comercio;
import tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.entity.Direccion;
import tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.entity.Sucursal;
import tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.entity.Territorio;
/**
 *
 * @author sussy
 */
@Path("/territorio")
public class TerritorioResource implements Serializable {
    
    @Inject
    SucursalBean sucursalbean;
    
    @Inject
    ComercioBean comerciobean;
    
    @Inject
    DireccionBean direccionbean;
    
    @Inject
    TerritorioBean territoriobean;
    //CREAR Sucursal-----------------------------------------------------------------------------
    
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response Sucursal(Territorio territorio, @Context UriInfo info) {

        if (territorio != null) {
            try {
               
                territoriobean.crear(territorio);

                if (territorio.getIdTerritorio()!= null) {
                    UriBuilder uriBuilder = info.getAbsolutePathBuilder();
                    uriBuilder.path(territorio.getIdTerritorio().toString());
                    return Response.created(uriBuilder.build()).build();
                }
            } catch (Exception ex) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
            }
            return Response.status(400).header(WRONG_PARAMETER, JsonbBuilder.create().toJson(territorio)).build();
        }
        return Response.status(400)
                .header(NULL_PARAMETER, null).build();
    }
    
    
    
}

