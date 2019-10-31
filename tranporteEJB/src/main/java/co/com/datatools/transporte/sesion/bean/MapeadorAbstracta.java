package co.com.datatools.transporte.sesion.bean;

import co.com.datatools.transporte.sesion.IMapeador;

import java.util.List;
import java.util.Map;

import javax.persistence.CacheRetrieveMode;
import javax.persistence.EntityManager;

import javax.persistence.Query;

import org.eclipse.persistence.config.QueryHints;


/**
 * Clase abstracta con la lógica para gestionar persistencia
 */
public abstract class MapeadorAbstracta<T> implements IMapeador<T> {

    private Class<T> entityClass;
    
    protected abstract EntityManager getEntityManager();
        
    /**
    * Constructor de la clase 
    * @param entityClass
    */
    public MapeadorAbstracta(Class<T> entityClass){
        this.entityClass = entityClass;
    }

    /**
     * Buscar entidad por id
     * @param id
     */
    public T buscar(Object id){
         return getEntityManager().find(entityClass, id);
    }

    /**
     * Buscar objeto por name query, devuelve un único objeto si tiene mas de 1 genera error.
     * @param params
     * @param nameQuery
     */
    public Object buscarObjetoPorNameQuery(String nameQuery, Map<String, Object> params) throws Exception {
        Query query = getEntityManager().createNamedQuery(nameQuery);
        if (params != null && params.size() > 0) {
            for (Map.Entry<String, Object> pair : params.entrySet()) {
                query.setParameter(pair.getKey(), pair.getValue());
            }
        }
        
        List<T> resultados = query.setHint(QueryHints.CACHE_RETRIEVE_MODE, CacheRetrieveMode.BYPASS).getResultList();
        if(resultados.size() == 1){
            return resultados.get(0);
        } else if (resultados.size() == 0){
            return null; 
        } else {
            throw new Exception("Se obtuvieron más de un resultado en la consulta: " + nameQuery);
        }
    }

    /**
     * Buscar lista de entidades por name query
     * @param nameQuery
     * @param params
     */
    public List<T> buscarPorNameQuery(String nameQuery, Map<String, Object> params){
        Query query = getEntityManager().createNamedQuery(nameQuery);
        if (params != null && params.size() > 0) {
            for (Map.Entry<String, Object> pair : params.entrySet()) {
                query.setParameter(pair.getKey(), pair.getValue());
            }
        }
        return query.getResultList();
    }

    /**
     * Buscar lista de entidades por native query
     * @param nativeQuery
     * @param params
     */
    public List<T> buscarPorNativeQuery(String nativeQuery, Map<String, Object> params){
        Query query = getEntityManager().createNativeQuery(nativeQuery);
        if (params != null && params.size() > 0) {
            for (Map.Entry<String, Object> pair : params.entrySet()) {
                query.setParameter(pair.getKey(), pair.getValue());
            }
        }
        return query.setHint(QueryHints.CACHE_RETRIEVE_MODE, CacheRetrieveMode.BYPASS).getResultList();
    }

    /**
     * Buscar propiedad(columna) por name query
     * @param nameQuery
     * @param params
     */
    public List<String> buscarPropiedadPorNameQuery(String nameQuery, Map<String, Object> params){
        Query query = getEntityManager().createNamedQuery(nameQuery);
        if (params != null && params.size() > 0) {
            for (Map.Entry<String, Object> pair : params.entrySet()) {
                query.setParameter(pair.getKey(), pair.getValue());
            }
        }
        return query.getResultList();
    }

    /**
     * Eliminar entidad
     * @param entity
     */
    public void eliminar(T entity){
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    /**
     * Guardar entidad
     * @param entity
     */
    public T guardar(T entity){
        getEntityManager().persist(entity);
        return entity;
    }

    /**
     * Guardar entidad con refresco inmediato de la base de datos
     * @param entity
     */
    public T guardarConRefresco(T entity){
        getEntityManager().persist(entity);
        return entity;
    }

    /**
     * Modificar entidad.
     * @param entity
     */
    public void modificar(T entity){
        getEntityManager().merge(entity);
    }
}