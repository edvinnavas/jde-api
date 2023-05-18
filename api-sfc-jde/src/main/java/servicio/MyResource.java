package servicio;

import Controladores.Ctrl_JDE;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.Serializable;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
            resultado = "PROYECTO:api-grupoterra-svfel-v3|CLASE:" + this.getClass().getName() + "|METODO:obetener_texto_encabezado_orden_ventas()|ERROR:" + ex.toString();
            Gson gson = new GsonBuilder().serializeNulls().create();
            resultado = gson.toJson(resultado);
            System.out.println("PROYECTO:api-grupoterra-svfel-v3|CLASE:" + this.getClass().getName() + "|METODO:obetener_texto_encabezado_orden_ventas()|ERROR:" + ex.toString());
        }

        return resultado;
    }

}
