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
@XmlRootElement(name = "TipoCambioRangoResponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class TipoCambioRangoResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    @XmlElement(name = "TipoCambioRangoResult")
    private TipoCambioRangoResult TipoCambioRangoResult;

    @Override
    public String toString() {
        return "TipoCambioRangoResponse{" + "TipoCambioRangoResult=" + TipoCambioRangoResult + '}';
    }

}
