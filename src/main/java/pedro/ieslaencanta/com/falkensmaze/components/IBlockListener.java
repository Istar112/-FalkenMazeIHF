/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pedro.ieslaencanta.com.falkensmaze.components;

/**
 * Interfaz para escuchar eventos relacionados con acciones de clic en bloques
 * Define dos métodos que deben ser implementados por cualquier clase que desee recibir notificaciones
 * cuando un bloque es clickeado o doblemente clickeado
 *
 * @author Istar
 */
public interface IBlockListener {
    /**
     * Se inicia cuando un bloque es clickeado
     * la implementación del método debe responder a la acción de clic en un bloque
     * @param b el bloque que es el bloque en elq ue se ha hecho clic
     */
    public void onClicked(Block b);
    /**
     * Se inicia cuando en un bloque se ha hecho dos veces clic
     * la implementación del método debe responder a la acción de clic en un bloque
     * @param b el bloque que es el bloque en el que se ha hecho clic dos veces
     */
    public void onDoubleClicked(Block b);
}
