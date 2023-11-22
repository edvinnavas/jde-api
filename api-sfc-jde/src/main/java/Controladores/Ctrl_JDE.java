package Controladores;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;
import jakarta.xml.soap.MessageFactory;
import jakarta.xml.soap.SOAPMessage;
import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
            System.out.println("PROYECTO:api-sfc-jde|CLASE:" + this.getClass().getName() + "|METODO:obetener_texto_encabezado_orden_ventas()|ERROR:" + ex.toString());
        }

        return resultado;
    }

    public String jde_tasa_cambio_gt(String division, String ambiente, String fecha) {
        String resultado = "";
        Connection conn = null;

        try {
            SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyyMMdd");
            SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy");

            Date date_fecha_init = dateFormat1.parse(fecha);

            Calendar date_calendar = Calendar.getInstance();
            date_calendar.setTime(date_fecha_init);
            date_calendar.add(Calendar.DATE, 1);

            Date date_fecha_fin = date_calendar.getTime();

            String xml_request = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ws=\"http://www.banguat.gob.gt/variables/ws/\">"
                    + "<soapenv:Header/>"
                    + "<soapenv:Body>"
                    + "<ws:TipoCambioRango>"
                    + "<ws:fechainit>" + dateFormat2.format(date_fecha_init) + "</ws:fechainit>"
                    + "<ws:fechafin>" + dateFormat2.format(date_fecha_fin) + "</ws:fechafin>"
                    + "</ws:TipoCambioRango>"
                    + "</soapenv:Body>"
                    + "</soapenv:Envelope>";

            ClienteRest.Cliente_Rest_BancoGT cliente_rest_banco_gt = new ClienteRest.Cliente_Rest_BancoGT();
            String xml_response = cliente_rest_banco_gt.Tipo_Cambio_Rango(xml_request);

            xml_response = xml_response.replaceAll(" xmlns=\"http://www.banguat.gob.gt/variables/ws/\"", "");

            SOAPMessage soap_response = MessageFactory.newInstance().createMessage(null, new ByteArrayInputStream(xml_response.getBytes("UTF-8")));
            Unmarshaller unmarshaller = JAXBContext.newInstance(Entidad.TipoCambioRangoResponse.class).createUnmarshaller();
            Entidad.TipoCambioRangoResponse tipo_cambio_rango_response = (Entidad.TipoCambioRangoResponse) unmarshaller.unmarshal(soap_response.getSOAPBody().extractContentAsDocument());

            Ctrl_Base_Datos ctrl_base_datos = new Ctrl_Base_Datos();
            conn = ctrl_base_datos.obtener_conexion(division, ambiente);

            String esquema_db;
            String db_link;
            switch (ambiente) {
                case "PY": {
                    esquema_db = "CRPDTA";
                    db_link = "JDEPY";
                    break;
                }
                case "PYB": {
                    esquema_db = "CRPDTAB";
                    db_link = "JDEPY";
                    break;
                }
                case "PYC": {
                    esquema_db = "CRPDTAC";
                    db_link = "JDEPY";
                    break;
                }
                default: {
                    esquema_db = "PRODDTA";
                    db_link = "JDEPD";
                    break;
                }
            }

            conn.setAutoCommit(false);

            for (Integer i = 0; i < tipo_cambio_rango_response.getTipoCambioRangoResult().getVars().size(); i++) {
                DecimalFormat decimalFormat1 = new DecimalFormat("0.00000");
                DecimalFormat decimalFormat2 = new DecimalFormat("0.0000000");

                Date fecha_tasa = dateFormat2.parse(tipo_cambio_rango_response.getTipoCambioRangoResult().getVars().get(i).getFecha());
                Integer moneda = tipo_cambio_rango_response.getTipoCambioRangoResult().getVars().get(i).getMoneda();
                Double compra = tipo_cambio_rango_response.getTipoCambioRangoResult().getVars().get(i).getCompra();
                Double venta = tipo_cambio_rango_response.getTipoCambioRangoResult().getVars().get(i).getVenta();
                Double factor = 1 / venta;
                Calendar date_calendar_1 = Calendar.getInstance();
                Integer hora = date_calendar_1.get(Calendar.HOUR_OF_DAY);
                Integer minuto = date_calendar_1.get(Calendar.MINUTE);
                Integer segundo = date_calendar_1.get(Calendar.SECOND);
                String formato_hora = hora.toString() + minuto.toString() + segundo.toString();

                String CXCRCD = "USD";
                String CXCRDC = "GTQ";
                String CXAN8 = "0";
                String CXRTTYP = "  ";
                String CXEFT = ctrl_base_datos.ObtenerString("SELECT TO_NUMBER(SUBSTR(TO_CHAR(TO_DATE('" + dateFormat2.format(fecha_tasa) + "','dd/MM/yyyy'),'ccYYddd'),2,6)) CXUPMJ FROM DUAL", conn);
                String CXCLMETH = "1";
                String CXCRCM = "Y";
                String CXTRCR = "   ";
                String CXCRR = decimalFormat1.format(venta);
                String CXCRRD = decimalFormat2.format(factor);
                String CXCSR = "1";
                String CXUSER = "WSUSER";
                String CXPID = "WSTASA";
                String CXJOBN = "JDEWEB1   ";
                String CXUPMJ = ctrl_base_datos.ObtenerString("SELECT TO_NUMBER(SUBSTR(TO_CHAR(TO_DATE('" + dateFormat2.format(date_calendar.getTime()) + "','dd/MM/yyyy'),'ccYYddd'),2,6)) CXUPMJ FROM DUAL", conn);
                String CXUPMT = formato_hora;

                Double tasa_cambio = ctrl_base_datos.ObtenerDouble("SELECT F.CXCRR FROM " + esquema_db + ".F0015@" + db_link + " F WHERE CXEFT=" + CXEFT + " AND F.CXCRCD='USD' AND F.CXCRDC='GTQ'", conn);
                if (tasa_cambio == null) {
                    String cadenasql = "INSERT INTO " + esquema_db + ".F0015@" + db_link + " ("
                            + "CXCRCD, "
                            + "CXCRDC, "
                            + "CXAN8, "
                            + "CXRTTYP, "
                            + "CXEFT, "
                            + "CXCLMETH, "
                            + "CXCRCM, "
                            + "CXTRCR, "
                            + "CXCRR, "
                            + "CXCRRD, "
                            + "CXCSR, "
                            + "CXUSER, "
                            + "CXPID, "
                            + "CXJOBN, "
                            + "CXUPMJ, "
                            + "CXUPMT) VALUES ('"
                            + CXCRCD + "','"
                            + CXCRDC + "',"
                            + CXAN8 + ",'"
                            + CXRTTYP + "',"
                            + CXEFT + ",'"
                            + CXCLMETH + "','"
                            + CXCRCM + "','"
                            + CXTRCR + "',"
                            + CXCRR + ","
                            + CXCRRD + ",'"
                            + CXCSR + "','"
                            + CXUSER + "','"
                            + CXPID + "','"
                            + CXJOBN + "',"
                            + CXUPMJ + ","
                            + CXUPMT + ")";
                    Statement stmt = conn.createStatement();
                    // System.out.println("CADENASQL: " + cadenasql);
                    stmt.executeUpdate(cadenasql);
                    stmt.close();

                    cadenasql = "INSERT INTO " + esquema_db + ".F0015@" + db_link + " ("
                            + "CXCRCD, "
                            + "CXCRDC, "
                            + "CXAN8, "
                            + "CXRTTYP, "
                            + "CXEFT, "
                            + "CXCLMETH, "
                            + "CXCRCM, "
                            + "CXTRCR, "
                            + "CXCRR, "
                            + "CXCRRD, "
                            + "CXCSR, "
                            + "CXUSER, "
                            + "CXPID, "
                            + "CXJOBN, "
                            + "CXUPMJ, "
                            + "CXUPMT) VALUES ('"
                            + CXCRDC + "','"
                            + CXCRCD + "',"
                            + CXAN8 + ",'"
                            + CXRTTYP + "',"
                            + CXEFT + ",'"
                            + CXCLMETH + "','"
                            + CXCRCM + "','"
                            + CXTRCR + "',"
                            + CXCRRD + ","
                            + CXCRR + ",'"
                            + CXCSR + "','"
                            + CXUSER + "','"
                            + CXPID + "','"
                            + CXJOBN + "',"
                            + CXUPMJ + ","
                            + CXUPMT + ")";
                    stmt = conn.createStatement();
                    // System.out.println("CADENASQL: " + cadenasql);
                    stmt.executeUpdate(cadenasql);
                    stmt.close();
                } else {
                    // System.out.println("ACTUALIZAR TASA DE CAMBIO.");
                }
            }

            conn.commit();
            conn.setAutoCommit(false);

            Gson gson = new GsonBuilder().serializeNulls().create();
            resultado = gson.toJson(tipo_cambio_rango_response);
        } catch (Exception ex) {
            try {
                conn.rollback();
                conn.setAutoCommit(true);

                System.out.println("PROYECTO:api-sfc-jde|CLASE:" + this.getClass().getName() + "|METODO:jde_tasa_cambio_gt()|ERROR:" + ex.toString());
                resultado = "PROYECTO:api-sfc-jde|CLASE:" + this.getClass().getName() + "|METODO:jde_tasa_cambio_gt()|ERROR:" + ex.toString();
            } catch (Exception ex1) {
                System.out.println("PROYECTO:api-sfc-jde|CLASE:" + this.getClass().getName() + "|METODO:jde_tasa_cambio_gt()-rollback|ERROR:" + ex1.toString());
                resultado = "PROYECTO:api-sfc-jde|CLASE:" + this.getClass().getName() + "|METODO:jde_tasa_cambio_gt()-rollback|ERROR:" + ex1.toString();
            }
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {
                System.out.println("PROYECTO:api-sfc-jde|CLASE:" + this.getClass().getName() + "|METODO:jde_tasa_cambio_gt()-finally|ERROR:" + ex.toString());
                resultado = "PROYECTO:api-sfc-jde|CLASE:" + this.getClass().getName() + "|METODO:jde_tasa_cambio_gt()-finally|ERROR:" + ex.toString();
            }
        }

        return resultado;
    }

    public String Obtener_Electronic_Data_Interchange(String division, String ambiente, String edct, Long edoc) {
        String resultado = "";
        Connection conn = null;

        try {
            Ctrl_Base_Datos ctrl_base_datos = new Ctrl_Base_Datos();
            conn = ctrl_base_datos.obtener_conexion(division, ambiente);

            String esquema_db;
            String db_link;
            switch (ambiente) {
                case "PY": {
                    esquema_db = "CRPDTA";
                    db_link = "JDEPY";
                    break;
                }
                case "PYB": {
                    esquema_db = "CRPDTAB";
                    db_link = "JDEPY";
                    break;
                }
                case "PYC": {
                    esquema_db = "CRPDTAC";
                    db_link = "JDEPY";
                    break;
                }
                default: {
                    esquema_db = "PRODDTA";
                    db_link = "JDEPD";
                    break;
                }
            }

            List<Entidad.F47012_1> Detalle_F47012_1 = new ArrayList<>();
            Entidad.F47012_1 f47012_1 = new Entidad.F47012_1();
            Detalle_F47012_1.add(f47012_1);
            
            List<Entidad.F47012_2> Detalle_F47012_2 = new ArrayList<>();
            Entidad.F47012_2 f47012_2 = new Entidad.F47012_2();
            Detalle_F47012_2.add(f47012_2);
            
            Entidad.F4714 f4714 = new Entidad.F4714();
            
            Entidad.F47011 f47011 = new Entidad.F47011();
            f47011.setDetalle_F47012_1(Detalle_F47012_1);
            f47011.setDetalle_F47012_2(Detalle_F47012_2);
            f47011.setF4714(f4714);
            
            Entidad.ElectronicDataInterchange electronic_data_interchange = new Entidad.ElectronicDataInterchange();
            electronic_data_interchange.setNombre_lanza("Lanza_Porta_IT");
            electronic_data_interchange.setUsuario_r47011("PORTALIT");
            electronic_data_interchange.setF47011(f47011);
            electronic_data_interchange.setMsg_resultado("Mensaje de prueba inicial.");
            
            conn.setAutoCommit(false);

            conn.commit();
            conn.setAutoCommit(false);

            Gson gson = new GsonBuilder().serializeNulls().create();
            resultado = gson.toJson(electronic_data_interchange);
        } catch (Exception ex) {
            try {
                conn.rollback();
                conn.setAutoCommit(true);

                System.out.println("PROYECTO:api-sfc-jde|CLASE:" + this.getClass().getName() + "|METODO:Obtener_Electronic_Data_Interchange()|ERROR:" + ex.toString());
                resultado = "PROYECTO:api-sfc-jde|CLASE:" + this.getClass().getName() + "|METODO:Obtener_Electronic_Data_Interchange()|ERROR:" + ex.toString();
            } catch (Exception ex1) {
                System.out.println("PROYECTO:api-sfc-jde|CLASE:" + this.getClass().getName() + "|METODO:Obtener_Electronic_Data_Interchange()-rollback|ERROR:" + ex1.toString());
                resultado = "PROYECTO:api-sfc-jde|CLASE:" + this.getClass().getName() + "|METODO:Obtener_Electronic_Data_Interchange()-rollback|ERROR:" + ex1.toString();
            }
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {
                System.out.println("PROYECTO:api-sfc-jde|CLASE:" + this.getClass().getName() + "|METODO:Obtener_Electronic_Data_Interchange()-finally|ERROR:" + ex.toString());
                resultado = "PROYECTO:api-sfc-jde|CLASE:" + this.getClass().getName() + "|METODO:Obtener_Electronic_Data_Interchange()-finally|ERROR:" + ex.toString();
            }
        }

        return resultado;
    }
    
}
