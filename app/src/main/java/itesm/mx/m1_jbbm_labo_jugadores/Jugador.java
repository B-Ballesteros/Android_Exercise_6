package itesm.mx.m1_jbbm_labo_jugadores;

/**
 * Created by benji on 18/02/17.
 */

public class Jugador {

    String nombre;
    int posicion;
    String nacionalidad;
    int idImagePosicion;
    byte[] byteArrayFoto;

    public Jugador(){
    }

    public Jugador(String nombre, int posicion, String nacionalidad, int idImagenPosicion,
                   byte[] byteArrayFoto){
        this.nombre = nombre;
        this.posicion = posicion;
        this.nacionalidad = nacionalidad;
        this.idImagePosicion = idImagenPosicion;
        this.byteArrayFoto = byteArrayFoto;
    }

    //region Getters & Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getPosicion() { return posicion; }
    public  void setPosicion(int posicion) { this.posicion = posicion; }

    public String getNacionalidad() { return nacionalidad; }
    public void setNacionalidad(String nacionalidad) { this.nacionalidad = nacionalidad; }

    public int getIdImagePosicion() {return idImagePosicion; }
    public void setIdImagePosicion(int idImagePosicion) { this.idImagePosicion = idImagePosicion; }

    public byte[] getByteArrayFoto() { return  byteArrayFoto; }
    public void setByteArrayFoto(byte[] byteArrayFoto) { this.byteArrayFoto = byteArrayFoto; }
    //endregion
}
