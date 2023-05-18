package Controladores;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Ctrl_JDE implements Serializable {

    private static final long serialVersionUID = 1L;

    public Ctrl_JDE() {
    }

    public String obetener_texto_encabezado_orden_ventas(String division, String ambiente, String DOCO_JDE, String DCTO_JDE, String KCOO_JDE) {
        String resultado = "";
        Connection conn = null;

        try {
            Ctrl_Base_Datos ctrl_base_datos = new Ctrl_Base_Datos();
            conn = ctrl_base_datos.obtener_conexion(division, ambiente);

            String esquema_db;
            if (ambiente.equals("PY")) {
                esquema_db = "CRPDTA";
            } else {
                esquema_db = "PRODDTA";
            }

            String cadenasql = "SELECT REPLACE(REGEXP_REPLACE(REPLACE(UTL_RAW.CAST_TO_VARCHAR2(DBMS_LOB.SUBSTR(D.GDTXFT,DBMS_LOB.GETLENGTH(D.GDTXFT),1)),CHR(0),''), '<.+?>|(' || '&' || 'nbsp;)'), CHR(13) || CHR(10) ) DESCRIPCION "
                    + "FROM " + esquema_db + ".F00165 D "
                    + "WHERE D.GDOBNM = 'GT4201A   ' AND D.GDTXKY = '" + DOCO_JDE + "|" + DCTO_JDE + "|" + KCOO_JDE + "' AND D.GDMOSEQN=1";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(cadenasql);
            while (rs.next()) {
                resultado = rs.getString(1);
            }
            rs.close();
            stmt.close();

            conn.close();
        } catch (Exception ex) {
            System.out.println("PROYECTO:api-grupoterra-svfel-v3|CLASE:" + this.getClass().getName() + "|METODO:obetener_texto_encabezado_orden_ventas()|ERROR:" + ex.toString());
        }

        return resultado;
    }

}
