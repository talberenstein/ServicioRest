/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.talzeev.rest.dao;

//Patron de diseño Singleton (Una sola instancia)

import java.util.List;
import java.util.ArrayList;
import org.talzeev.rest.modelo.Articulo;
import org.talzeev.rest.modelo.Comentario;

public class BaseDeDatos {
    private final static BaseDeDatos baseDeDatos = new BaseDeDatos();
    private final List<Articulo> listado = new ArrayList<>();

    private BaseDeDatos(){
    Articulo art1 = new Articulo(1, "Primer Articulo", "Primer Contenido", "TalZeev");
    Articulo art2 = new Articulo(2, "Segundo Articulo", "Segundo Contenido", "TalZeev");    
    Articulo art3 = new Articulo(3, "Tercer Articulo", "Tercer Contenido", "TalZeev");
    
    art1.getComentarios().add(new Comentario(1, "Muy buen articulo", "Daniel"));
    
    listado.add(art1);
    listado.add(art2);
    listado.add(art3);
    }
    
    public static BaseDeDatos getInstancia(){
    return baseDeDatos;
    }
    
    public List<Articulo> getListado(){
      return listado;  
    }
    
    }
    

