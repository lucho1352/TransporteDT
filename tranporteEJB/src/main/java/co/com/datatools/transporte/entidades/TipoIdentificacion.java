package co.com.datatools.transporte.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="\"Tipo_Identificacion\"")
@NamedQueries({ @NamedQuery(name = "TipoIdentificacion.findAll", query="SELECT t FROM TipoIdentificacion t"),
			   @NamedQuery(name="TipoIdentificacion.findByTipoId", query="SELECT t FROM TipoIdentificacion t WHERE t.abreviatura = :tipoId")})
public class TipoIdentificacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "tipoIdSeq",sequenceName = "\"tipo_identificacion_id_seq\"",allocationSize=1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "tipoIdSeq")
	@Column(name="\"Id\"")
	private Long id;

	@Column(name="\"Abreviatura\"")
	private String abreviatura;

	@Column(name="\"Descripcion\"")
	private String descripcion;

	public TipoIdentificacion() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAbreviatura() {
		return this.abreviatura;
	}

	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}