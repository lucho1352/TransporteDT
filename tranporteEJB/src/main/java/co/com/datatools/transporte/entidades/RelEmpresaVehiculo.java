package co.com.datatools.transporte.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="\"Rel_Empresa_Vehiculo\"")
@NamedQuery(name="RelEmpresaVehiculo.findAll", query="SELECT r FROM RelEmpresaVehiculo r")
public class RelEmpresaVehiculo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @SequenceGenerator(name = "relEmpVehSeq",sequenceName = "\"rel_empresa_vehiculo_id_seq\"",allocationSize=1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "relEmpVehSeq")
	@Column(name="\"Id\"")
	private Long id;

	@Column(name="\"Fecha_Afiliacion\"")
	private Timestamp fechaAfiliacion;

	@Column(name="\"Fecha_Retiro\"")
	private Timestamp fechaRetiro;

	@ManyToOne
	@JoinColumn(name="\"Id_Empresa\"")
	private Persona empresa; //Se recupera una empresa

	@ManyToOne
	@JoinColumn(name="\"Id_Vehiculo\"")
	private Vehiculo vehiculo;

	public RelEmpresaVehiculo() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getFecha_Afiliacion() {
		return this.fechaAfiliacion;
	}

	public void setFecha_Afiliacion(Timestamp fecha_Afiliacion) {
		this.fechaAfiliacion = fecha_Afiliacion;
	}

	public Timestamp getFecha_Retiro() {
		return this.fechaRetiro;
	}

	public void setFecha_Retiro(Timestamp fecha_Retiro) {
		this.fechaRetiro = fecha_Retiro;
	}

	public Persona getPersona() {
		return this.empresa;
	}

	public void setPersona(Persona persona) {
		this.empresa = persona;
	}

	public Vehiculo getVehiculo() {
		return this.vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

}