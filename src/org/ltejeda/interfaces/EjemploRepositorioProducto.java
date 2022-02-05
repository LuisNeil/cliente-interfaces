package org.ltejeda.interfaces;

import org.ltejeda.interfaces.modelo.Producto;
import org.ltejeda.interfaces.repositorio.Direccion;
import org.ltejeda.interfaces.repositorio.OrdenablePaginableCrudRepositorio;
import org.ltejeda.interfaces.repositorio.excepciones.*;
import org.ltejeda.interfaces.repositorio.lista.ProductoListRepositorio;

import java.util.List;

public class EjemploRepositorioProducto {
    public static void main(String[] args){

        try{
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
            List<Producto> productosOrdenAsc = repo.listar("apellido", Direccion.ASC);
            for(Producto producto: productosOrdenAsc){
                System.out.println(producto);
            }

            System.out.println("===== editar =====");
            Producto lamparaActualizar = new Producto("lampara escritorio", 23.99);
            lamparaActualizar.setId(3);
            repo.editar(lamparaActualizar);
            Producto lampara = repo.porId(2);
            System.out.println(lampara);
            System.out.println(" ============= ");
            repo.listar("precio", Direccion.ASC).forEach(System.out::println);
            System.out.println("===== eliminar ======");
            repo.eliminar(2);
            repo.listar().forEach(System.out::println);
            System.out.println("===== total ===== ");
            System.out.println("Total registros: " + repo.total());
        }catch (RegistroDuplicadoAccesoDatoException e){
            System.out.println("Registro duplicado: " + e.getMessage());
            e.printStackTrace();
        }catch (LecturaAccesoDatoException e){
            System.out.println("Lectura: " + e.getMessage());
            e.printStackTrace();
        }catch (EscrituraAccesoDatoException e){
            System.out.println("Escritura: " + e.getMessage());
            e.printStackTrace();
        }catch (AccesoDatoException e){
            System.out.println("Generica: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
