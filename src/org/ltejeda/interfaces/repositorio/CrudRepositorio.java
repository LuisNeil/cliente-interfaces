package org.ltejeda.interfaces.repositorio;

import org.ltejeda.interfaces.modelo.Cliente;

import java.util.List;

public interface CrudRepositorio<T> {
    List<T> listar();
    T porId(Integer id);
    void crear(T cliente);
    void editar(T cliente);
    void eliminar(Integer id);

}
