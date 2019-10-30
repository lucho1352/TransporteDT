package co.com.datatools.transporte.sesion;

import javax.ejb.Remote;

import co.com.datatools.transporte.entidades.TipoIdentificacion;

@Remote
public interface MapeadorTipoIdentificacion extends IMapeador<TipoIdentificacion> {
}
