package Controladores;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.Serializable;
import java.sql.Connection;

public class Ctrl_F47011 implements Serializable {

    private static final long serialVersionUID = 1L;

    public Ctrl_F47011() {
    }
    
    public String select_f47011(String division, String ambiente, String edct, Long edoc) {
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

            conn.setAutoCommit(false);

            conn.commit();
            conn.setAutoCommit(false);

            resultado = "0,SELECT F47011 EXISTO.";
            Gson gson = new GsonBuilder().serializeNulls().create();
            resultado = gson.toJson(resultado);
        } catch (Exception ex) {
            try {
                conn.rollback();
                conn.setAutoCommit(true);

                System.out.println("PROYECTO:api-sfc-jde|CLASE:" + this.getClass().getName() + "|METODO:select_f47011()|ERROR:" + ex.toString());
                resultado = "PROYECTO:api-sfc-jde|CLASE:" + this.getClass().getName() + "|METODO:select_f47011()|ERROR:" + ex.toString();
            } catch (Exception ex1) {
                System.out.println("PROYECTO:api-sfc-jde|CLASE:" + this.getClass().getName() + "|METODO:select_f47011()-rollback|ERROR:" + ex1.toString());
                resultado = "PROYECTO:api-sfc-jde|CLASE:" + this.getClass().getName() + "|METODO:select_f47011()-rollback|ERROR:" + ex1.toString();
            }
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {
                System.out.println("PROYECTO:api-sfc-jde|CLASE:" + this.getClass().getName() + "|METODO:select_f47011()-finally|ERROR:" + ex.toString());
                resultado = "PROYECTO:api-sfc-jde|CLASE:" + this.getClass().getName() + "|METODO:select_f47011()-finally|ERROR:" + ex.toString();
            }
        }

        return resultado;
    }

    public String insert_f47011(String division, String ambiente, String jsonString) {
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

            conn.setAutoCommit(false);

            conn.commit();
            conn.setAutoCommit(false);

            resultado = "0,INSERT F47011 EXISTO.";
            Gson gson = new GsonBuilder().serializeNulls().create();
            resultado = gson.toJson(resultado);
        } catch (Exception ex) {
            try {
                conn.rollback();
                conn.setAutoCommit(true);

                System.out.println("PROYECTO:api-sfc-jde|CLASE:" + this.getClass().getName() + "|METODO:insert_f47011()|ERROR:" + ex.toString());
                resultado = "PROYECTO:api-sfc-jde|CLASE:" + this.getClass().getName() + "|METODO:insert_f47011()|ERROR:" + ex.toString();
            } catch (Exception ex1) {
                System.out.println("PROYECTO:api-sfc-jde|CLASE:" + this.getClass().getName() + "|METODO:insert_f47011()-rollback|ERROR:" + ex1.toString());
                resultado = "PROYECTO:api-sfc-jde|CLASE:" + this.getClass().getName() + "|METODO:insert_f47011()-rollback|ERROR:" + ex1.toString();
            }
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {
                System.out.println("PROYECTO:api-sfc-jde|CLASE:" + this.getClass().getName() + "|METODO:insert_f47011()-finally|ERROR:" + ex.toString());
                resultado = "PROYECTO:api-sfc-jde|CLASE:" + this.getClass().getName() + "|METODO:insert_f47011()-finally|ERROR:" + ex.toString();
            }
        }

        return resultado;
    }
    
    public String update_f47011(String division, String ambiente, String jsonString) {
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

            conn.setAutoCommit(false);

            conn.commit();
            conn.setAutoCommit(false);

            resultado = "0,UPDATE F47011 EXISTO.";
            Gson gson = new GsonBuilder().serializeNulls().create();
            resultado = gson.toJson(resultado);
        } catch (Exception ex) {
            try {
                conn.rollback();
                conn.setAutoCommit(true);

                System.out.println("PROYECTO:api-sfc-jde|CLASE:" + this.getClass().getName() + "|METODO:update_f47011()|ERROR:" + ex.toString());
                resultado = "PROYECTO:api-sfc-jde|CLASE:" + this.getClass().getName() + "|METODO:update_f47011()|ERROR:" + ex.toString();
            } catch (Exception ex1) {
                System.out.println("PROYECTO:api-sfc-jde|CLASE:" + this.getClass().getName() + "|METODO:update_f47011()-rollback|ERROR:" + ex1.toString());
                resultado = "PROYECTO:api-sfc-jde|CLASE:" + this.getClass().getName() + "|METODO:update_f47011()-rollback|ERROR:" + ex1.toString();
            }
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {
                System.out.println("PROYECTO:api-sfc-jde|CLASE:" + this.getClass().getName() + "|METODO:update_f47011()-finally|ERROR:" + ex.toString());
                resultado = "PROYECTO:api-sfc-jde|CLASE:" + this.getClass().getName() + "|METODO:update_f47011()-finally|ERROR:" + ex.toString();
            }
        }

        return resultado;
    }
    
    public String delete_f47011(String division, String ambiente, String jsonString) {
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

            conn.setAutoCommit(false);

            conn.commit();
            conn.setAutoCommit(false);

            resultado = "0,DELETE F47011 EXISTO.";
            Gson gson = new GsonBuilder().serializeNulls().create();
            resultado = gson.toJson(resultado);
        } catch (Exception ex) {
            try {
                conn.rollback();
                conn.setAutoCommit(true);

                System.out.println("PROYECTO:api-sfc-jde|CLASE:" + this.getClass().getName() + "|METODO:delete_f47011()|ERROR:" + ex.toString());
                resultado = "PROYECTO:api-sfc-jde|CLASE:" + this.getClass().getName() + "|METODO:delete_f47011()|ERROR:" + ex.toString();
            } catch (Exception ex1) {
                System.out.println("PROYECTO:api-sfc-jde|CLASE:" + this.getClass().getName() + "|METODO:delete_f47011()-rollback|ERROR:" + ex1.toString());
                resultado = "PROYECTO:api-sfc-jde|CLASE:" + this.getClass().getName() + "|METODO:delete_f47011()-rollback|ERROR:" + ex1.toString();
            }
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {
                System.out.println("PROYECTO:api-sfc-jde|CLASE:" + this.getClass().getName() + "|METODO:delete_f47011()-finally|ERROR:" + ex.toString());
                resultado = "PROYECTO:api-sfc-jde|CLASE:" + this.getClass().getName() + "|METODO:delete_f47011()-finally|ERROR:" + ex.toString();
            }
        }

        return resultado;
    }

}
