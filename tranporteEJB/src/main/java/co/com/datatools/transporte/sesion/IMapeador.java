package co.com.datatools.transporte.sesion;

import java.util.Map;
import java.util.List;

public interface IMapeador<T> {

	/**
	 * Firma para buscar Objeto por ID
	 * @param id
	 */
	public T buscar(Object id);

	/**
	 * Firma para buscar Objeto por Name Query
	 * @param params
	 * @param nameQuery
	 */
	public Object buscarObjetoPorNameQuery(String nameQuery, Map<String, Object> params) throws Exception;

	/**
	 * Firma para buscar listado de objetos por Name Query
	 * @param nameQuery
	 * @param params
	 */
	public List<T> buscarPorNameQuery(String nameQuery, Map<String, Object> params);

	/**
	 * Buscar listado de objetos por query nativa
	 * @param nativeQuery
	 * @param params
	 */
	public List<T> buscarPorNativeQuery(String nativeQuery, Map<String, Object> params);

	/**
	 * Firma para buscar listado de propiedades(Columna) por name query
	 * @param nameQuery
	 * @param params
	 */
	public List<String> buscarPropiedadPorNameQuery(String nameQuery, Map<String, Object> params);

	/**
	 * Firma para eliminar entidad de la base de datos
	 * @param entity
	 */
	public void eliminar(T entity);

	/**
	 * Firma para guardar entidad en base de datos
	 * @param entity
	 */
	public T guardar(T entity);

	/**
	 * Guardar entidad en base de datos con refresco inmediato
	 * @param entity
	 */
	public T guardarConRefresco(T entity);

	/**
	 * Firma para modificar entidad en base de datos
	 * @param entity
	 */
	public void modificar(T entity);

}