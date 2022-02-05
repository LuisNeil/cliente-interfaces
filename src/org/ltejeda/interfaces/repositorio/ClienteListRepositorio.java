package org.ltejeda.interfaces.repositorio;

import org.ltejeda.interfaces.modelo.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteListRepositorio extends AbstractaListRepositorio<Cliente> {

    @Override
    public Cliente porId(Integer id) {
        Cliente resultado = null;
        for(Cliente cli: dataSource){
            if(cli != null && cli.getId().equals(id)){
                resultado = cli;
                break;
            }
        }
        return resultado;
    }

    @Override
    public void editar(Cliente cliente) {
        Cliente c = this.porId(cliente.getId());
        c.setNombre(cliente.getNombre());
        c.setApellido(cliente.getApellido());
    }

    @Override
    public List<Cliente> listar(String campo, Direccion dir) {
        List<Cliente> listaOrdenada = new ArrayList<>(this.dataSource);
        listaOrdenada.sort((a,b) -> {
                int resultado = 0;
                if(dir == Direccion.ASC){
                    resultado = ordenar(campo, a, b);
                }else if(dir == Direccion.DESC){
                    resultado = ordenar(campo, b, a);
                }
                return resultado;
        });
        return listaOrdenada;
    }

    public static int ordenar(String campo, Cliente a, Cliente b){
        int resultado = 0;
        switch (campo){
            case "id" ->
                    resultado = b.getId().compareTo(a.getId());
            case "nombre" ->
                    resultado = b.getNombre().compareTo(a.getNombre());
            case "apellido" ->
                    resultado = b.getApellido().compareTo(a.getApellido());
        }
        return resultado;
    }

}
