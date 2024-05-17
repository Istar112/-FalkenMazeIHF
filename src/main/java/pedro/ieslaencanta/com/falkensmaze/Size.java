/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedro.ieslaencanta.com.falkensmaze;

import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Esta clase representa el tamaño de un objeto
 * Es una clase simple que contiene dos atributos enteros: ancho y alto
 * Implementa {@link Serializable} para permitir la serialización de objetos de este tipo
 * También implementa {@link Cloneable} para permitir la creación de copias de objetos Size
 * @author Istar
 */
@XmlRootElement
public class Size implements Cloneable, Comparable<Size>, Serializable {
    private int width;
    private int height;
    /**
     * Constructor predeterminado que crea objeto Size con valores por defecto
     */
    public Size() {
    }

    /**
     * Constructor que crea un objeto Size con los valores de ancho y alto
     * especificados
     * @param width para el ancho
     * @param height para la altura
     */
    public Size(int width, int height) {
        this.width = width;
        this.height = height;
    }
    /**
     * Devuelve una copia del objeto Size actual
     * @return Una nueva instancia de Size con los mismos valores de ancho y alto
     * @throws CloneNotSupportedException si la operación falla
     */
    public Object clone() throws CloneNotSupportedException{
        return new Size(this.getWidth(), this.getHeight());
    }
    /**
     * Determina si dos objetos Size son iguales comparando su ancho y alto
     * @param o el objeto Size con el que comparamos
     * @return Verdadero si ambos objetos tienen el mismo ancho y alto, falso en caso contrario
     */
    @Override
    public boolean equals(Object o){
        if(! (o instanceof Size)){
            return false;
        }
        if (this.getWidth()==((Size)(o)).getWidth() && this.getHeight()==((Size)(o)).getHeight()){
            return true;
        }else{
            return false;
        }
        
    }
    /**
     * Compara el objeto Size actual con otro objeto Size
     * @param o el objeto Size con el que se va a comparar
     * @return -1 si el ancho del objeto es menor que el del objeto comparado,
     * 0 si son iguales, 1 si es mayor
     */
     @Override
    public int compareTo(Size o) {
        if(this.getWidth()==o.getWidth() && this.getHeight()==o.getHeight())
            return 0;
        if(this.getWidth()<o.getWidth())
            return -1;
        else
            return 1;
    }
    /**
     * la representación en cadena del objeto 
     * @return cadena que muestra el ancho y la altura del objeto 
     * @author Istar
     */
    public String toString(){
        return "W:"+this.width+" H:"+this.height;
    }
    /**
     * obtiene el valor del ancho del objeto
     * @return el valor del ancho
     */
    public int getWidth() {
        return width;
    }

    /**
     *  obtiene el valor de la altura del objeto
     * @return el valor de la altura
     */
    public int getHeight() {
        return height;
    }

    /**
     * establece el nuevo valor dela ancho del objeto
     * @param width el nuevo valor del ancho del objeto
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * establece el nuevo valor del alto del objeto
     * @param height el nuevo valor del alto del objeto
     */
    public void setHeight(int height) {
        this.height = height;
    }
 

}
