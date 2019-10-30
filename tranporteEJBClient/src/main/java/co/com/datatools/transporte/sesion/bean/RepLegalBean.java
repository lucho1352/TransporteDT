package co.com.datatools.transporte.sesion.bean;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.com.datatools.transporte.entidades.jpersonas.RepresentanteLegal;
import co.com.datatools.transporte.sesion.MapeadorRepLegal;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class RepLegalBean extends MapeadorAbstracta<RepresentanteLegal> implements MapeadorRepLegal 
{
    @PersistenceContext(unitName="Model")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }
    
    public RepLegalBean() {
        super(RepresentanteLegal.class);
    }
}
