package Entidad;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@XmlRootElement(name = "Var")
@XmlAccessorType(XmlAccessType.FIELD)
public class Var implements Serializable {

    private static final long serialVersionUID = 1L;

    @XmlElement(name = "moneda")
    private Integer moneda;

    @XmlElement(name = "fecha")
    private String fecha;

    @XmlElement(name = "venta")
    private Double venta;

    @XmlElement(name = "compra")
    private Double compra;

    @Override
    public String toString() {
        return "Var{" + "moneda=" + moneda + ", fecha=" + fecha + ", venta=" + venta + ", compra=" + compra + '}';
    }

}
