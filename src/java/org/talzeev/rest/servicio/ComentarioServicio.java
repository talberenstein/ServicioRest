package org.talzeev.rest.servicio;

import java.util.List;
import org.talzeev.rest.excepciones.DatosNoEncontradosExcepcion;
import org.talzeev.rest.modelo.Articulo;
import org.talzeev.rest.modelo.Comentario;

public class ComentarioServicio {
    
    private final ArticuloServicio articuloServicio = new ArticuloServicio();
    
    public List<Comentario> getComentarios(int articuloId){
        return articuloServicio.getArticulo(articuloId).getComentarios();
    }
    
    public Comentario getComentario(int articuloId, int comentarioId){
        Comentario respuesta = null;
        List<Comentario> comentarios = articuloServicio.getArticulo(articuloId).getComentarios();
        for(Comentario comentario : comentarios){
            if(comentario.getId() == comentarioId){
                respuesta = comentario;
            }
        }
        if(respuesta == null){
            throw new DatosNoEncontradosExcepcion("No se encuentra el comentario");
        }
        return respuesta;
    }
    
    public Comentario addComentario(int articuloId, Comentario comentario){
        Articulo articulo = articuloServicio.getArticulo(articuloId);
        comentario.setId(getMaximo(articulo));
        articulo.getComentarios().add(comentario);
        return comentario;
    }
    
    public Comentario updateComentario(int articuloId, Comentario comentario){
        Articulo articulo = articuloServicio.getArticulo(articuloId);
        int posicion = getPosicion(articulo, comentario.getId());
        try{
            articulo.getComentarios().set(posicion, comentario);
        } catch (IndexOutOfBoundsException ioobe){
            return null;
        }
        return comentario;
    }
    
    public void deleteComentario(int articuloId, int comentarioId){
        Articulo articulo = articuloServicio.getArticulo(articuloId);
        int posicion = getPosicion(articulo, comentarioId);
        articulo.getComentarios().remove(posicion);
    }
    
    private int getPosicion(Articulo articulo, int comentarioId){
        List<Comentario> comentarios = articulo.getComentarios();
        for(int i=0 ; i < comentarios.size() ; i++){
            if(comentarios.get(i).getId() == comentarioId){
                return i;
            }
        }
        return -1;
    }
    
    private int getMaximo(Articulo articulo){
        int size = articulo.getComentarios().size();
        if(size > 0){
            return articulo.getComentarios().get(size - 1).getId() +1;
        }
        return 1;
    }
    
}
