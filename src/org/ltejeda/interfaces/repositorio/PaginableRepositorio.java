package org.ltejeda.interfaces.repositorio;

import org.ltejeda.interfaces.modelo.Cliente;

import java.util.List;

public interface PaginableRepositorio {
    List listar(int desde, int hasta);
}
