package org.ltejeda.interfaces;

import org.ltejeda.interfaces.modelo.Cliente;
import org.ltejeda.interfaces.repositorio.*;

import java.util.List;

public class EjemploRepositorio {
    public static void main(String[] args) {

        CompletoRepositorio repo = new ClienteListRepositorio();
        repo.crear(new Cliente("Juan","Perez"));
        repo.crear(new Cliente("Bea", "Gonzalez"));
        repo.crear(new Cliente("Luci", "Martinez"));
        repo.crear(new Cliente("Luis","Tejeda"));

        List<Cliente> clientes = repo.listar();
        clientes.forEach(System.out::println);

        System.out.println("====== paginable ======");
        List<Cliente> paginable = ((PaginableRepositorio) repo).listar(0,2);
        paginable.forEach(System.out::println);

        System.out.println("===== ordenable =====");
        List<Cliente> ordenable = ((OrdenableRepositorio) repo).listar("nombre", Direccion.ASC);
        ordenable.forEach(System.out::println);

        System.out.println("====== editar =======");
        Cliente beaActualizar = new Cliente("Bea","Mena");
        beaActualizar.setId(2);
        repo.editar(beaActualizar);
        ((OrdenableRepositorio) repo).listar("apellido", Direccion.DESC).forEach(System.out::println);
        System.out.println("===== eliminar =====");
        repo.eliminar(2);
        repo.listar().forEach(System.out::println);
        System.out.println("Total registros: " + repo.total());
    }
}
