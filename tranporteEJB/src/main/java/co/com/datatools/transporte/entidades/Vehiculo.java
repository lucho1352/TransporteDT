package co.com.datatools.transporte.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="\"Vehiculo\"")
@NamedQuery(name="Vehiculo.findAll", query="SELECT v FROM Vehiculo v")
public class Vehiculo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "vehiculoSeq",sequenceName = "\"vehiculo_id_seq\"",allocationSize=1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "vehiculoSeq")
	@Column(name="\"Id\"")
	private Long id;

	@Column(name="\"Cantidad_Puertas\"")
	private Long cantidad_Puertas;

	@Column(name="\"Chasis\"")
	private String chasis;

	@Temporal(TemporalType.DATE)
	@Column(name="\"Fecha_Matricula\"")
	private Date fecha_Matricula;

	@Column(name="\"Linea\"")
	private String linea;

	@Column(name="\"Marca\"")
	private String marca;

	@Column(name="\"Modelo\"")
	private String modelo;

	@Column(name="\"Motor\"")
	private String motor;

	@Column(name="\"Pasajeros_de_Pie\"")
	private Long pasajeros_de_Pie;

	@Column(name="\"Pasajeros_Senados\"")
	private Long pasajeros_Senados;

	@Column(name="\"Peso_Bruto\"")
	private BigDecimal peso_Bruto;

	@Column(name="\"Peso_Seco\"")
	private BigDecimal peso_Seco;

	@Column(name="\"Placa\"")
	private String placa;

	public Vehiculo() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCantidad_Puertas() {
		return this.cantidad_Puertas;
	}

	public void setCantidad_Puertas(Long cantidad_Puertas) {
		this.cantidad_Puertas = cantidad_Puertas;
	}

	public String getChasis() {
		return this.chasis;
	}

	public void setChasis(String chasis) {
		this.chasis = chasis;
	}

	public Date getFecha_Matricula() {
		return this.fecha_Matricula;
	}

	public void setFecha_Matricula(Date fecha_Matricula) {
		this.fecha_Matricula = fecha_Matricula;
	}

	public String getLinea() {
		return this.linea;
	}

	public void setLinea(String linea) {
		this.linea = linea;
	}

	public String getMarca() {
		return this.marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return this.modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getMotor() {
		return this.motor;
	}

	public void setMotor(String motor) {
		this.motor = motor;
	}

	public Long getPasajeros_de_Pie() {
		return this.pasajeros_de_Pie;
	}

	public void setPasajeros_de_Pie(Long pasajeros_de_Pie) {
		this.pasajeros_de_Pie = pasajeros_de_Pie;
	}

	public Long getPasajeros_Senados() {
		return this.pasajeros_Senados;
	}

	public void setPasajeros_Senados(Long pasajeros_Senados) {
		this.pasajeros_Senados = pasajeros_Senados;
	}

	public BigDecimal getPeso_Bruto() {
		return this.peso_Bruto;
	}

	public void setPeso_Bruto(BigDecimal peso_Bruto) {
		this.peso_Bruto = peso_Bruto;
	}

	public BigDecimal getPeso_Seco() {
		return this.peso_Seco;
	}

	public void setPeso_Seco(BigDecimal peso_Seco) {
		this.peso_Seco = peso_Seco;
	}

	public String getPlaca() {
		return this.placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

}