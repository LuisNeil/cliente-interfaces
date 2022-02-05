package org.ltejeda.interfaces;

import org.ltejeda.interfaces.modelo.Cliente;
import org.ltejeda.interfaces.repositorio.Direccion;
import org.ltejeda.interfaces.repositorio.OrdenablePaginableCrudRepositorio;
import org.ltejeda.interfaces.repositorio.excepciones.*;
import org.ltejeda.interfaces.repositorio.lista.ClienteListRepositorio;

import java.util.List;

public class EjemploRepositorio {
    public static void main(String[] args){

        try{
            OrdenablePaginableCrudRepositorio<Cliente> repo = new ClienteListRepositorio();
            repo.crear(new Cliente("Carlos","Barrios"));
            repo.crear(new Cliente("Maria", "Ochoa"));
            repo.crear(new Cliente("Luis", "Tejeda"));
            repo.crear(new Cliente("Rafael","Lopez"));

            List<Cliente> clientes = repo.listar();
            clientes.forEach(System.out::println);
            System.out.println("===== paginable =====");
            List<Cliente> paginable = repo.listar(1, 4);
            paginable.forEach(System.out::println);

            System.out.println("===== ordenar =====");
            List<Cliente> clientesOrdenAsc = repo.listar("apellido", Direccion.ASC);
            for(Cliente cliente: clientesOrdenAsc){
                System.out.println(cliente);
            }

            System.out.println("===== editar =====");
            Cliente mariaActualizar = new Cliente("Maria", "Sierra");
            mariaActualizar.setId(3);
            repo.editar(mariaActualizar);
            Cliente maria = repo.porId(2);
            System.out.println(maria);
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
