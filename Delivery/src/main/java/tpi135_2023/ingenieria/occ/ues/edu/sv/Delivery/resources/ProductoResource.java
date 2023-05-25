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
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.boundary.RestResourcePattern.NULL_PARAMETER;
import static tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.boundary.RestResourcePattern.PARAMETROS_FALTANTES;
import static tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.boundary.RestResourcePattern.WRONG_PARAMETER;
import tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.control.ComercioBean;
import tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.control.ProductoBean;
import tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.control.ProductoComercioBean;
import tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.control.TipoComercioBean;
import tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.entity.Comercio;
import tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.entity.Producto;
import tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.entity.ProductoComercio;
import tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.entity.ProductoComercioPK;
import tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.entity.TipoComercio;

/**
 *
 * @author USER
 */
@Path("/producto")
public class ProductoResource implements Serializable {
    
    @Inject
    ProductoBean productoBean;
    
    @Inject
    ProductoComercioBean pcBean;
    
    @Inject
    ComercioBean comercioBean;
    
    
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response crearproducto(Producto producto, @Context UriInfo info) {

        if (producto != null) {
            try {
                productoBean.crear(producto);

                if (producto.getIdProducto() != null) {
                    UriBuilder uriBuilder = info.getAbsolutePathBuilder();
                    uriBuilder.path(producto.getIdProducto().toString());
                    return Response.created(uriBuilder.build()).build();
                }
            } catch (Exception ex) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
            }
            return Response.status(400).header(WRONG_PARAMETER, JsonbBuilder.create().toJson(producto)).build();
        }
        return Response.status(400)
                .header(NULL_PARAMETER, null).build();
    }

      
                        //METODO FINd ALL------------------------------------------------------------------------------
   @Path("/all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response encontrarproductos() {
        List<Producto> registros = productoBean.findAll();
        Long total = productoBean.contar();
        return Response.ok(registros)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS")
                .header("Access-Control-Allow-Headers", "Content-Type")
                .header("total registros", total)
                .build();
    }
    
    @Path("/{idProducto}/comercio/{idComercio}")
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response agregarproductosacomercios(@PathParam("idProducto")Integer idProducto,
                                         @PathParam("idComercio")Long idComercio){
    
        if(idProducto!=null && idComercio!=null){
            
            Producto p = productoBean.findById(idProducto);
            Comercio c = comercioBean.findById(idComercio);
            
            if( p!=null && c!=null){
            
            ProductoComercio pc = new ProductoComercio();
            ProductoComercioPK ctcPK = new ProductoComercioPK(idProducto.intValue(),idComercio.intValue());
            pc.setProducto(p);
            pc.setComercio(c);
            pc.setFechaCreacion(new Date());
            pc.setActivo(Boolean.TRUE);
            pc.setProductoComercioPK(ctcPK);
            pcBean.crear(pc);
            
            return Response.status(Response.Status.CREATED).build();
            
            }
            
            return Response.status(400).header(WRONG_PARAMETER, null).build();
        
        }
        
        return Response.status(404).header(PARAMETROS_FALTANTES, null).build();
    }

}