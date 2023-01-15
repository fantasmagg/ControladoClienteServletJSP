/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import datos.ClienteDaoJDBC;
import dominio.Cliente;
import java.util.List;

/**
 *
 * @author Fanta
 */
public class tests {
    public static void main(String[] args){
        
        Cliente ca = new Cliente(8);
        
        Cliente cl = new ClienteDaoJDBC().encontrar(ca);
        
        System.out.println(cl);
        
       /* cl.insertar(ca);
        
        //System.out.println(ca.getNombre());
        
        List<Cliente> c = cl.listar();
        
        for(Object cs:c){
            System.out.println(cs);
        }*/
        
        
        
    }
}
