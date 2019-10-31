package co.com.datatools.transporte.entidades.jpersonas;

import co.com.datatools.transporte.entidades.Persona;
import javax.persistence.*;

@Entity
@DiscriminatorValue("2")
public class RepresentanteLegal extends Persona 
{
	public RepresentanteLegal() {}

}
