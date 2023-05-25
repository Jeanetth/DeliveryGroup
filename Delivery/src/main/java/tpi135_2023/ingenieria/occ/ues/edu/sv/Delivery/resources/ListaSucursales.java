/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.io.Serializable;
import java.util.List;
import tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.control.ProductoComercioBean;
import tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.control.SucursalBean;
import tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.entity.ProductoComercio;
import tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.entity.Sucursal;

/**
 *
 * @author USER
 */
@Path("/comercio_sucursales")
public class ListaSucursales implements Serializable {
      @Inject
    SucursalBean sucursalBean;
    
    @Path("/all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response eocntrartodos() {
        List<Sucursal> registros = sucursalBean.findAll();
        Long total = sucursalBean.contar();
        return Response.ok(registros)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS")
                .header("Access-Control-Allow-Headers", "Content-Type")
                .header("total registros", total)
                .build();
    }


}
    
