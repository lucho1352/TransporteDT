package co.com.datatools.transporte.entidades;

import java.io.Serializable;
import javax.persistence.*;

//Mediante notaciones JPA no es requerida esta clase, pero se
//deja en caso de requerir incluir atributos de rompimiento

@Entity
@Table(name="\"Rel_Vehiculo_Conductor\"")
@NamedQuery(name="Rel_Vehiculo_Conductor.findAll", query="SELECT r FROM Rel_Vehiculo_Conductor r")
public class RelVehiculoConductor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @SequenceGenerator(name = "relVehConSeq",sequenceName = "\"rel_vehiculo_conductor_id_seq\"",allocationSize=1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "relVehConSeq")
	@Column(name="\"Id\"")
	private Long id;

	@ManyToOne
	@JoinColumn(name="\"Id_Conductor\"")
	private Persona conductor;

	@ManyToOne
	@JoinColumn(name="\"Id_Vehiculo\"")
	private Vehiculo vehiculo;

	public RelVehiculoConductor() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Persona getPersona() {
		return this.conductor;
	}

	public void setPersona(Persona persona) {
		this.conductor = persona;
	}

	public Vehiculo getVehiculo() {
		return this.vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

}