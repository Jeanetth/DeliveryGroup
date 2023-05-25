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
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.boundary.RestResourcePattern;
import static tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.boundary.RestResourcePattern.CONTAR_REGISTROS;
import static tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.boundary.RestResourcePattern.ID_NOT_FOUND;
import static tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.boundary.RestResourcePattern.NULL_PARAMETER;
import static tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.boundary.RestResourcePattern.PARAMETROS_FALTANTES;
import static tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.boundary.RestResourcePattern.WRONG_PARAMETER;
import tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.control.ComercioBean;
import tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.control.ComercioTipoComercioBean;
import tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.control.SucursalBean;
import tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.control.TipoComercioBean;
import tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.entity.Comercio;
import tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.entity.ComercioTipoComercio;
import tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.entity.ComercioTipoComercioPK;
import tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.entity.Sucursal;
import tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.entity.TipoComercio;

/**
 *
 * @author moimo98
 */
@Path("/comercio")
public class ComercioResource implements Serializable {
    @Inject
    ComercioBean comercioBean;
    
    @Inject
    SucursalBean sucursalBean;
    
    @Inject
    ComercioTipoComercioBean ctcBean;
    
    @Inject
    TipoComercioBean tipoComerciobean;
    
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
    
    
                        //METODO FINd ALL------------------------------------------------------------------------------
   @Path("/all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response eocntrartodos() {
        List<Comercio> registros = comercioBean.findAll();
        Long total = comercioBean.contar();
        return Response.ok(registros)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS")
                .header("Access-Control-Allow-Headers", "Content-Type")
                .header("total registros", total)
                .build();
    }
    
                    //METODO FINBYID------------------------------------------------------------------------------
    @Path("/{id}")
    @GET   
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") Long id) {
        Comercio comercio = comercioBean.findById(id);//buscar el comercio por su ID en la base de datos o en alguna otra fuente de datos
        if (comercio == null) {
            return Response.status(Response.Status.NOT_FOUND)
                  .header(RestResourcePattern.ID_NOT_FOUND, "true")
                  .build();
        } else {
            return Response.ok(comercio).build();
        }
    }
    
    //SE VALIDA TIPO VACIO Y TIPO LLENO - MISMO METODO PARA AMBAS PRUEBAS
    @Path("/{id}/tipocomercio")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerTipocomercio(@PathParam("id") Long idComercio){
       
        if(idComercio!= null){
            Comercio c = comercioBean.findById(idComercio);
            
            if(c!=null){
            
                Long contarResgistros = ctcBean.contar();
                return Response.ok(c).header(CONTAR_REGISTROS,contarResgistros).build();
                
            }
            return Response.status(404)
                .header(ID_NOT_FOUND, idComercio).build();
        
        }
        
        
        return Response.status(400).header(NULL_PARAMETER, null).build();
        
        
    }
    
    @Path("/{idComercio}/tipocomercio/{idTipoComercio}")
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response agregarTipoAcomercio(@PathParam("idComercio")Long idComercio,
                                         @PathParam("idTipoComercio")Integer idTipoComercio){
    
        if(idComercio!=null && idTipoComercio!=null){
            
            Comercio c = comercioBean.findById(idComercio);
            TipoComercio tp = tipoComerciobean.findById(idTipoComercio);
            
            if(c!=null && tp!=null){
            
            ComercioTipoComercio ctc = new ComercioTipoComercio();
            ComercioTipoComercioPK ctcPK = new ComercioTipoComercioPK(idComercio.intValue(),idTipoComercio.intValue());
            
            
            
            ctc.setComercio(c);
            ctc.setTipoComercio(tp);
            ctc.setFechaCreacion(new Date());
            ctc.setActivo(Boolean.TRUE);
            ctc.setComercioTipoComercioPK(ctcPK);
            ctcBean.crear(ctc);
            
            return Response.status(Response.Status.CREATED).build();
            
            }
            
            return Response.status(400).header(WRONG_PARAMETER, null).build();
        
        }
        
        return Response.status(404).header(PARAMETROS_FALTANTES, null).build();
    }
    //RESOURCE CREARSUCURSAL 
    //PREVIAMENTE YA CREADO EL RESOURCE DE  TERRITORIOS Y DIRECCION)
    
    @Path("/{idComercio}/sucursal")
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response crearnuevaSucursal(@PathParam(value="idComercio")Long idcomercio, Sucursal sucursal, @Context UriInfo info) {

        if (sucursal != null) {
            try {
               
                sucursal.setIdComercio(comercioBean.findById(idcomercio));
                     
         
               sucursal.setIdDireccion( BigInteger.valueOf(idcomercio));
          
                sucursalBean.crear(sucursal);

                if (sucursal.getIdSucursal() != null && comercioBean.findById(idcomercio)!=null) {
                    UriBuilder uriBuilder = info.getAbsolutePathBuilder();
                    uriBuilder.path(sucursal.getIdSucursal().toString());
                    return Response.created(uriBuilder.build()).build();
                }
            } catch (Exception ex) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
            }
            return Response.status(400).header(WRONG_PARAMETER, JsonbBuilder.create().toJson(sucursal)).build();
        }
        
        
       return Response.status(400)
               .header(NULL_PARAMETER, null).build();
    }
  
}
