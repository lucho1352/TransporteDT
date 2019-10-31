package co.com.datatools.transporte.sesion.bean;

import co.com.datatools.transporte.entidades.Vehiculo;
import co.com.datatools.transporte.sesion.MapeadorVehiculo;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class VehiculoBean extends MapeadorAbstracta<Vehiculo> implements MapeadorVehiculo
{
    @PersistenceContext(unitName="Model")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }
    
    public VehiculoBean() {
        super(Vehiculo.class);
    }
}
