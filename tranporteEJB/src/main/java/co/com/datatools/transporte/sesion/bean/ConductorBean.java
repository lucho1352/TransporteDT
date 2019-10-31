package co.com.datatools.transporte.sesion.bean;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.com.datatools.transporte.entidades.jpersonas.Conductor;
import co.com.datatools.transporte.sesion.MapeadorCondutor;


@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class ConductorBean extends MapeadorAbstracta<Conductor> implements MapeadorCondutor 
{
    @PersistenceContext(unitName="Model")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }
    
    public ConductorBean() {
        super(Conductor.class);
    }
}
