package Entidad;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@XmlRootElement(name = "TipoCambioRangoResult")
@XmlAccessorType(XmlAccessType.FIELD)
public class TipoCambioRangoResult implements Serializable {

    private static final long serialVersionUID = 1L;

    @XmlElementWrapper(name = "Vars")
    @XmlElement(name = "Var")
    private List<Var> vars;

    @Override
    public String toString() {
        return "TipoCambioRangoResult{" + "vars=" + vars + '}';
    }

}
