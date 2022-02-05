package org.ltejeda.interfaces;

import org.ltejeda.interfaces.modelo.Cliente;
import org.ltejeda.interfaces.modelo.Producto;
import org.ltejeda.interfaces.repositorio.*;
import org.ltejeda.interfaces.repositorio.lista.ClienteListRepositorio;
import org.ltejeda.interfaces.repositorio.lista.ProductoListRepositorio;

import java.util.List;

public class EjemploRepositorio {
    public static void main(String[] args) {

        OrdenablePaginableCrudRepositorio<Producto> repo = new ProductoListRepositorio();
        repo.crear(new Producto("mesa",50.99));
        repo.crear(new Producto("silla", 18.00));
        repo.crear(new Producto("lampara", 15.5));
        repo.crear(new Producto("notebook",400.99));

        List<Producto> productos = repo.listar();
        productos.forEach(System.out::println);
        System.out.println("===== paginable =====");
        List<Producto> paginable = repo.listar(1, 4);
        paginable.forEach(System.out::println);

        System.out.println("===== ordenar =====");
        List<Producto> clientesOrdenAsc = repo.listar("apellido", Direccion.ASC);
        for(Producto producto: clientesOrdenAsc){
            System.out.println(producto);
        }

        System.out.println("===== editar =====");
        Producto lamparaActualizar = new Producto("lampara escritorio", 23.99);
        lamparaActualizar.setId(3);
        repo.editar(lamparaActualizar);
        Producto lampara = repo.porId(2);
        System.out.println(lampara);
        System.out.println(" ============= ");
        repo.listar("nombre", Direccion.ASC).forEach(System.out::println);
        System.out.println("===== eliminar ======");
        repo.eliminar(2);
        repo.listar().forEach(System.out::println);
        System.out.println("===== total ===== ");
        System.out.println("Total registros: " + repo.total());
    }
}
