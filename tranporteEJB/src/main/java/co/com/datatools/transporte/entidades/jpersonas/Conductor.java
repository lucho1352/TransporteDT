package co.com.datatools.transporte.entidades.jpersonas;

import co.com.datatools.transporte.entidades.Persona;
import co.com.datatools.transporte.entidades.Vehiculo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@DiscriminatorValue("3")
public class Conductor extends Persona {

    @ManyToMany(fetch=FetchType.EAGER) 
    @JoinTable(
       name="\"Rel_Vehiculo_Conductor\"",
       joinColumns=@JoinColumn(name="\"Id_Conductor\"", referencedColumnName="\"Id\""),
       inverseJoinColumns=@JoinColumn(name="\"Id_Vehiculo\"", referencedColumnName="\"Id\""))
	private List<Vehiculo> listVehiculo = new ArrayList<>();
	
	public Conductor() {}

	private List<Vehiculo> getListVehiculo() {
		return listVehiculo;
	}

	private void setListVehiculo(List<Vehiculo> listVehiculo) {
		this.listVehiculo = listVehiculo;
	}
	
	private void addVehiculo(Vehiculo in_objVehiculo) {
		this.listVehiculo.add(in_objVehiculo);
	}
	
	private void removeVehiculo(Vehiculo in_objVehiculo) {
		this.listVehiculo.remove(in_objVehiculo);
	}
}
