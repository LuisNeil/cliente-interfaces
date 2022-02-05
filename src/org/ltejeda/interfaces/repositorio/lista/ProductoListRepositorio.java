package org.ltejeda.interfaces.repositorio.lista;

import org.ltejeda.interfaces.modelo.Producto;
import org.ltejeda.interfaces.repositorio.AbstractaListRepositorio;
import org.ltejeda.interfaces.repositorio.Direccion;

import java.util.ArrayList;
import java.util.List;

public class ProductoListRepositorio extends AbstractaListRepositorio<Producto> {
    @Override
    public void editar(Producto producto) {
        Producto p = porId(producto.getId());
        p.setDescripcion(p.getDescripcion());
        p.setPrecio(p.getPrecio());
    }

    public static int ordenar(String campo, Producto a, Producto b){
        int resultado = 0;
        switch (campo){
            case "id" ->
                    resultado = a.getId().compareTo(b.getId());
            case "descripcion" ->
                    resultado = a.getDescripcion().compareTo(b.getDescripcion());
            case "precio" ->
                    resultado = a.getPrecio().compareTo(b.getPrecio());
        }
        return resultado;
    }

    @Override
    public List<Producto> listar(String campo, Direccion dir) {
        List<Producto> listaOrdenada = new ArrayList<>(this.dataSource);
        listaOrdenada.sort((a,b) -> {
            int resultado = 0;
            if(dir == Direccion.ASC){
                resultado = ordenar(campo,a,b);
            } else if (dir == Direccion.DESC){
                resultado = ordenar(campo,b,a);
            }
            return resultado;
        });
        return listaOrdenada;
    }
}
