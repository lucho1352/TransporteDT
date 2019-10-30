package co.com.datatools.transporte.entidades.jpersonas;

import co.com.datatools.transporte.entidades.Persona;
import co.com.datatools.transporte.entidades.RelEmpresaVehiculo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@DiscriminatorValue("1")
public class Empresa extends Persona
{

	@ManyToOne
	@JoinColumn(name="\"Id_Representante\"")
	private Persona representanteLegal;

	@OneToMany(mappedBy="empresa", fetch=FetchType.EAGER)
	private List<RelEmpresaVehiculo> listRelEmpresaVehiculos = new ArrayList<>();
	
	public Empresa(){}

	private Persona getRepresentanteLegal() {
		return representanteLegal;
	}

	private void setRepresentanteLegal(Persona representanteLegal) {
		this.representanteLegal = representanteLegal;
	}

	private List<RelEmpresaVehiculo> getRelEmpresaVehiculos() {
		return listRelEmpresaVehiculos;
	}

	private void setRelEmpresaVehiculos(List<RelEmpresaVehiculo> relEmpresaVehiculos) {
		this.listRelEmpresaVehiculos = relEmpresaVehiculos;
	}
	
	private void addRelEmpresaVehiculo(RelEmpresaVehiculo in_objRelEmpresaVehiculo){
		this.listRelEmpresaVehiculos.add(in_objRelEmpresaVehiculo);
	}
	
	private void  removeRelEmpresaVehiculo(RelEmpresaVehiculo in_objRelEmpresaVehiculo){
		this.listRelEmpresaVehiculos.remove(in_objRelEmpresaVehiculo);
	}
}
