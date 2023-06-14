package tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.resources;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.control.ComercioBean;
import tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.control.ComercioTipoComercioBean;
import tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.control.SucursalBean;
import tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.control.TipoComercioBean;
import tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.entity.Comercio;
import tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.entity.ComercioTipoComercio;
import tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.entity.Sucursal;
import tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.entity.TipoComercio;

public class ComercioResourceTest {
    
    @Mock
    private TipoComercioBean tipoComerciobean;

    @Mock
    private ComercioBean comercioBean;

    @Mock
    private UriInfo uriInfo;

    @InjectMocks
    private ComercioResource comercioResource;
    
    @Mock
    private ComercioTipoComercioBean ctcBean;
    
    @Mock
    private SucursalBean sucursalBean;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void crearUnComercio() {
        Comercio comercio = new Comercio();
        comercio.setIdComercio(1L);
        when(uriInfo.getAbsolutePathBuilder()).thenReturn(UriBuilder.fromUri(URI.create("http://deliveryApp.com")));

        Response response = comercioResource.crearTest(comercio, uriInfo);

        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
        assertEquals("http://deliveryApp.com/1", response.getLocation().toString());
        response = comercioResource.crearTest(null, null);
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
        verify(comercioBean, times(1)).crear(comercio);
    }
    
    @Test
    public void findById_valido() {
        // Arrange
        Long comercioId = 1L;
        Comercio comercio = new Comercio();
        comercio.setIdComercio(comercioId);
        when(comercioBean.findById(comercioId)).thenReturn(comercio);

        // Act
        Response response = comercioResource.findById(comercioId);

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(comercio, response.getEntity());
        verify(comercioBean, times(1)).findById(comercioId);
    }
    
     @Test
    public void findById_noValido() {
        // Arrange
        Long comercioId = 1L;
        when(comercioBean.findById(comercioId)).thenReturn(null);

        // Act
        Response response = comercioResource.findById(comercioId);

        // Assert
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
        verify(comercioBean, times(1)).findById(comercioId);
    }
    
    @Test
    public void ObtenerTipocomercio() {
        // Arrange
        Long comercioId = 1L;
        Comercio comercio = new Comercio();
        comercio.setIdComercio(comercioId);
        when(comercioBean.findById(comercioId)).thenReturn(comercio);
        Long contarRegistros = 0L;
        when(ctcBean.contar()).thenReturn(contarRegistros);

        // Act
        Response response = comercioResource.obtenerTipocomercio(comercioId);

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(comercio, response.getEntity());
        assertEquals(null, response.getHeaderString("Contar-Registros"));
        verify(comercioBean, times(1)).findById(comercioId);
        verify(ctcBean, times(1)).contar();
    }
    
     @Test
    public void AgregarTipoAcomercio_valido() {
        // Arrange
        Long idComercio = 1L;
        Integer idTipoComercio = 1;
        Comercio comercio = new Comercio();
        comercio.setIdComercio(idComercio);
        TipoComercio tipoComercio = new TipoComercio();
        tipoComercio.setIdTipoComercio(idTipoComercio);
        when(comercioBean.findById(idComercio)).thenReturn(comercio);
        when(tipoComerciobean.findById(idTipoComercio)).thenReturn(tipoComercio);
        ComercioTipoComercio ctc = new ComercioTipoComercio();
        //when(ctcBean.crear(any(ComercioTipoComercio.class))).thenReturn(ctc);

        // Act
        Response response = comercioResource.agregarTipoAcomercio(idComercio, idTipoComercio);

        // Assert
        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
        verify(comercioBean, times(1)).findById(idComercio);
        verify(tipoComerciobean, times(1)).findById(idTipoComercio);
        verify(ctcBean, times(1)).crear(any(ComercioTipoComercio.class));
    }
    
    @Test
    public void AgregarTipoAcomercio_comercio_erroneo() {
        // Arrange
        Integer idTipoComercio = 1;

        // Act
        Response response = comercioResource.agregarTipoAcomercio(null, idTipoComercio);

        // Assert
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
    }
    
     @Test
    public void testAgregarTipoAcomercio_WithNonExistingComercio_ReturnsResponseWithBadRequestStatus() {
        // Arrange
        Long idComercio = 1L;
        Integer idTipoComercio = 1;
        when(comercioBean.findById(idComercio)).thenReturn(null);

        // Act
        Response response = comercioResource.agregarTipoAcomercio(idComercio, idTipoComercio);

        // Assert
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
        verify(comercioBean, times(1)).findById(idComercio);
    }

    @Test
    public void testAgregarTipoAcomercio_WithNonExistingTipoComercio_ReturnsResponseWithBadRequestStatus() {
        // Arrange
        Long idComercio = 1L;
        Integer idTipoComercio = 1;
        Comercio comercio = new Comercio();
        comercio.setIdComercio(idComercio);
        when(comercioBean.findById(idComercio)).thenReturn(comercio);
        when(tipoComerciobean.findById(idTipoComercio)).thenReturn(null);

        // Act
        Response response = comercioResource.agregarTipoAcomercio(idComercio, idTipoComercio);

        // Assert
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
        verify(comercioBean, times(1)).findById(idComercio);
        verify(tipoComerciobean, times(1)).findById(idTipoComercio);
    }
    
    
        @Test
    public void testEocntrartodos_ReturnsResponseWithOkStatusAndHeaders() {
        // Arrange
        List<ComercioTipoComercio> registros = new ArrayList<>();
        when(ctcBean.findAll()).thenReturn(registros);
        when(ctcBean.contar()).thenReturn(10L);

        // Act
        Response response = comercioResource.eocntrartodos();

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    //    assertEquals(registros, response.getEntity());
        //assertEquals("*", response.getHeaderString("Access-Control-Allow-Origin"));
       // assertEquals("GET, POST, PUT, DELETE, OPTIONS", response.getHeaderString("Access-Control-Allow-Methods"));
       // assertEquals("Content-Type", response.getHeaderString("Access-Control-Allow-Headers"));
        //assertEquals("10", response.getHeaderString("total registros"));
        //verify(ctcBean, times(1)).findAll();
        //verify(ctcBean, times(1)).contar();
    }

 
}
