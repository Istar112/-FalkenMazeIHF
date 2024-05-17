/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedro.ieslaencanta.com.falkensmaze.model;

import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * representa un bloque
 * un bloque puede tener un valor que puede ser nulo.
 * esta clase es serializable, lo que significa que sus objetos pueden ser convertidos 
 * en un formato que puede ser almacenado o transmitido y luego reconstruido
 * @author Istar
 */
@XmlRootElement
public class Block implements  Serializable {
    private String value;
    /**
     * constructor predeterminado que inicia el valor del bloque en nulo
     */
    public Block(){
        this.value=null;
    }
    /**
     * obtiene el valor del bloque
     * @return el valor del bloque
     */
    public String getValue(){
        return this.value;
    }
    /**
     * establece el valor del bloque
     * @param value el nuevo valor del bloque
     */
    public void setValue(String value){
        this.value=value;
    }
    /**
     * comprueba si el bloque está vacío su valor es nulo
     * @return verdadero si el bloque está vacío, falso en caso contrario
     */
    public boolean isEmpty(){
        return this.value==null;
    }
}
