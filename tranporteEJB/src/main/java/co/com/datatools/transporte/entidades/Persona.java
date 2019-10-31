package co.com.datatools.transporte.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;


@Entity
@NamedQueries({@NamedQuery(name="Persona.findAll", query="SELECT p FROM Persona p")})
@Table(name="\"Persona\"")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="\"Tipo\"",discriminatorType = DiscriminatorType.INTEGER)
public abstract class Persona implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @SequenceGenerator(name = "personaSeq",sequenceName = "\"persona_id_seq\"",allocationSize=1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "personaSeq")
	@Column(name="\"Id\"")
	private Long id;

	@ManyToOne
	@JoinColumn(name="\"Id_Tipo_Identificacion\"", nullable = false)
	private TipoIdentificacion tipoIdentificacion;

	@Column(name="\"Ciudad\"")
	private String ciudad;

	@Column(name="\"Departamento\"")
	private String departamento;

	@Column(name="\"Direccion\"")
	private String direccion;
	
	@Column(name="\"Nombre\"")
	private String nombre;

	@Column(name="\"Numero_Identificacion\"")
	private String numeroIdentificacion;

	@Column(name="\"Pais\"")
	private String pais;

	@Column(name="\"Telefono\"")
	private String telefono;

	public Persona() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCiudad() {
		return this.ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getDepartamento() {
		return this.departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNumero_Identificacion() {
		return this.numeroIdentificacion;
	}

	public void setNumero_Identificacion(String numero_Identificacion) {
		this.numeroIdentificacion = numero_Identificacion;
	}

	public String getPais() {
		return this.pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public TipoIdentificacion getTipoIdentificacion() {
		return this.tipoIdentificacion;
	}

	public void setTipoIdentificacion(TipoIdentificacion tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
	}
}