package co.com.datatools.transporte.sesion.bean;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import co.com.datatools.transporte.entidades.*;


import co.com.datatools.transporte.sesion.MapeadorTipoIdentificacion;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class TipoIdentificacionBean extends MapeadorAbstracta<TipoIdentificacion> implements MapeadorTipoIdentificacion 
{
    @PersistenceContext(unitName="Model")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }
    
    public TipoIdentificacionBean() {
        super(TipoIdentificacion.class);
    }
}
