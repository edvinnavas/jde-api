package Entidad;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ElectronicDataInterchange implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nombre_lanza;
    private String usuario_r47011;
    private F47011 F47011;
    private String msg_resultado;

    @Override
    public String toString() {
        return "ElectronicDataInterchange{" + "nombre_lanza=" + nombre_lanza + ", usuario_r47011=" + usuario_r47011 + ", F47011=" + F47011 + ", msg_resultado=" + msg_resultado + '}';
    }
    
}
