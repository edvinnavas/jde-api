package servicio;

import Controladores.Ctrl_JDE;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.Serializable;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("jde")
public class MyResource implements Serializable {

    private static final long serialVersionUID = 1L;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it! (JDE).";
    }

    @Path("obetener_texto_encabezado_orden_ventas/{division}/{ambiente}/{DOCO_JDE}/{DCTO_JDE}/{KCOO_JDE}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String obetener_texto_encabezado_orden_ventas(
            @PathParam("division") String division,
            @PathParam("ambiente") String ambiente,
            @PathParam("DOCO_JDE") String DOCO_JDE,
            @PathParam("DCTO_JDE") String DCTO_JDE,
            @PathParam("KCOO_JDE") String KCOO_JDE) {

        String resultado;

        try {
            Ctrl_JDE ctrl_jde = new Ctrl_JDE();
            resultado = ctrl_jde.obetener_texto_encabezado_orden_ventas(division, ambiente, DOCO_JDE, DCTO_JDE, KCOO_JDE);
            Gson gson = new GsonBuilder().serializeNulls().create();
            resultado = gson.toJson(resultado);
        } catch (Exception ex) {
            resultado = "PROYECTO:api-sfc-jde|CLASE:" + this.getClass().getName() + "|METODO:obetener_texto_encabezado_orden_ventas()|ERROR:" + ex.toString();
            Gson gson = new GsonBuilder().serializeNulls().create();
            resultado = gson.toJson(resultado);
            System.out.println("PROYECTO:api-sfc-jde|CLASE:" + this.getClass().getName() + "|METODO:obetener_texto_encabezado_orden_ventas()|ERROR:" + ex.toString());
        }

        return resultado;
    }
    
    @Path("jde_tasa_cambio_gt/{division}/{ambiente}/{fecha}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String jde_tasa_cambio_gt(
            @PathParam("division") String division,
            @PathParam("ambiente") String ambiente,
            @PathParam("fecha") String fecha) {

        String resultado;

        try {
            Ctrl_JDE ctrl_jde = new Ctrl_JDE();
            resultado = ctrl_jde.jde_tasa_cambio_gt(division, ambiente, fecha);
        } catch (Exception ex) {
            resultado = "PROYECTO:api-sfc-jde|CLASE:" + this.getClass().getName() + "|METODO:jde_tasa_cambio_gt()|ERROR:" + ex.toString();
            Gson gson = new GsonBuilder().serializeNulls().create();
            resultado = gson.toJson(resultado);
            System.out.println("PROYECTO:api-sfc-jde|CLASE:" + this.getClass().getName() + "|METODO:jde_tasa_cambio_gt()|ERROR:" + ex.toString());
        }

        return resultado;
    }

    @Path("Obtener_Electronic_Data_Interchange/{division}/{ambiente}/{edct}/{edoc}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String Obtener_Electronic_Data_Interchange(
            @PathParam("division") String division,
            @PathParam("ambiente") String ambiente,
            @PathParam("edct") String edct,
            @PathParam("edoc") Long edoc) {

        String resultado;

        try {
            Ctrl_JDE ctrl_jde = new Ctrl_JDE();
            resultado = ctrl_jde.Obtener_Electronic_Data_Interchange(division, ambiente, edct, edoc);
        } catch (Exception ex) {
            resultado = "PROYECTO:api-sfc-jde|CLASE:" + this.getClass().getName() + "|METODO:Obtener_Electronic_Data_Interchange()|ERROR:" + ex.toString();
            Gson gson = new GsonBuilder().serializeNulls().create();
            resultado = gson.toJson(resultado);
            System.out.println("PROYECTO:api-sfc-jde|CLASE:" + this.getClass().getName() + "|METODO:Obtener_Electronic_Data_Interchange()|ERROR:" + ex.toString());
        }

        return resultado;
    }
    
}
