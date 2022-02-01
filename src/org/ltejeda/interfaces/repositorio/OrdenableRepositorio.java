package org.ltejeda.interfaces.repositorio;

import org.ltejeda.interfaces.modelo.Cliente;

import java.util.List;

public interface OrdenableRepositorio {
    List listar(String campo, Direccion dir);
}
