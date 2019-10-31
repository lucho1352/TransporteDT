package co.com.datatools.transporte.sesion.bean;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.com.datatools.transporte.entidades.jpersonas.Empresa;
import co.com.datatools.transporte.sesion.MapeadorEmpresa;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class EmpresaBean extends MapeadorAbstracta<Empresa> implements MapeadorEmpresa
{
    @PersistenceContext(unitName="Model")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }
    
    public EmpresaBean() {
        super(Empresa.class);
    }
}
