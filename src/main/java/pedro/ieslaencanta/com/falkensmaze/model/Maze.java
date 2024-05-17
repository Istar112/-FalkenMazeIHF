/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedro.ieslaencanta.com.falkensmaze.model;

import com.google.gson.Gson;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Serializable;


import java.io.UnsupportedEncodingException;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import pedro.ieslaencanta.com.falkensmaze.Size;

/**
 * Representa el tablero, que consiste en un conjunto de bloques organizados,
 * los bloques pueden tener un valor y el tablero tiene dimensiones específicas, tiempo y sonido.
 * Esta clase es serializable, lo que permite guardar y cargar instancias de Maze desde diferentes formatos de archivo
 * @author Istar
 */
@XmlRootElement
public class Maze implements Serializable {

    private Size size;
    private Block[][] blocks;
    private double time;
    private String sound;

    /**
     * Constructor predeterminado sin argumentos
     */
    public Maze() {
    }
    /**
     * Inicializa el tablero mediante la creación de los bloques
     */
    public void init() {
        this.setBlocks(new Block[this.getSize().getHeight()][this.getSize().getWidth()]);
        for (int i = 0; i < this.getBlocks().length; i++) {
            for (int j = 0; j < this.getBlocks()[i].length; j++) {
                this.getBlocks()[i][j] = new Block();

            }
        }
    }
     /**
     * Restablece todos los valores de bloques a nulo
     */
    public void reset() {
        for (int i = 0; i < this.getBlocks().length; i++) {
            for (int j = 0; j < this.getBlocks()[i].length; j++) {
                this.getBlocks()[i][j] = null;

            }
        }
        this.setBlocks(null);
    }
    /**
     * Restablece el tablero a un nuevo tamaño y rellena con nuevos bloques
     * @param newSize el nuevo tamaño para el tablero
     */
    public void reset(Size newsize) {
        this.reset();;
        this.setSize(newsize);
        this.init();
    }
    /**
     * Establece el valor de un bloque en una posición concreta
     * @param value el nuevo valor para el bloque
     * @param row la fila del bloque
     * @param col la columna del bloque
     */

    public void setBlockValue(String value, int row, int col) {
        this.getBlocks()[col][row].setValue(value);
    }
    
    /**
     * Obtiene el valor de un bloque en una posición concreta
     * @param row la fila del bloque
     * @param col la columna del bloque
     * @return el valor del bloque
     */

    public String getBlockValue(int row, int col) {
        return this.getBlocks()[row][col].getValue();
    }
    
    
    /**
     * obtiene el valor del tamaño
     * @return el valor del tamaño
     */
    public Size getSize() {
        return size;
    }
    /**
     * establece el valor del tamaño
     * @param size el nuevo valor del tamaño
     */
    public void setSize(Size size) {
        this.size = size;
    }
    /**
     * obtiene el valor del tiempo
     * @return el valor del tiempo
     */
    public double getTime() {
        return time;
    }
    /**
     * establece el valor del tiempo
     * @param time el nuevo valor del tiempo
     */
    public void setTime(double time) {
        this.time = time;
    }

    /**
     * obtiene el valor del sonido
     *
     * @return el valor del sonido
     */
    public String getSound() {
        return sound;
    }

    /**
     * establece el valor del sonido
     *
     * @param sound el nuevo valor del sonido
     */
    public void setSound(String sound) {
        this.sound = sound;
    }

    /**
     * obtiene el valor del bloque
     *
     * @return el valor del bloque
     */
    public Block[][] getBlocks() {
        return blocks;
    }

    /**
     * establece el valor del bloque
     *
     * @param blocks el nuevo valor del bloque
     */
    public void setBlocks(Block[][] blocks) {
        this.blocks = blocks;
    }

    /**
     * Carga un laberinto desde un archivo.
     *
     * @param file el archivo desde el cual cargar el tablero
     * @return el tablero cargado
     * @throws JAXBException si hay un problema con el archivo
     * @throws IOException si hay un problema al leer el archivo
     * @throws FileNotFoundException si el archivo no existe
     * @throws ClassNotFoundException si el archivo no contiene un objeto
     * @throws Exception si ocurre un error no especificado
     */

    public static Maze load(File file) throws JAXBException, IOException, FileNotFoundException, ClassNotFoundException, Exception {
        String extension = file.getName().substring(file.getName().lastIndexOf(".") + 1);
        Maze m = null;
        if (extension.equals("xml")) {
            m = Maze.loadXML(file);
        } else {
            if (extension.equals("json")) {
                m = Maze.loadJSON(file);

            } else {
                if (extension.equals("bin")) {
                    m = Maze.loadBin(file);
                } else {
                    throw new Exception("Exencsión " + extension + " no permitida");

                }
            }

        }
        return m;

    }
    
    /**
     * Guarda un tablero en un archivo
     * @param maze el tablero a guardar
     * @param file el archivo donde guardar el tablero
     * @throws JAXBException si hay un problema al serializar el tablero
     * @throws Exception si ocurre un error no especificado
     */

    public static void save(Maze maze, File file) throws JAXBException, Exception {
        if (maze.sound == null || maze.sound.equals("")) {
            throw new Exception("Es necesario indicar el sonido del laberinto");
        }
        String extension = file.getName().substring(file.getName().lastIndexOf(".") + 1);
        if (extension.equals("xml")) {
            Maze.saveXML(maze, file);
        } else {
            if (extension.equals("json")) {
                Maze.saveJSON(maze, file);

            } else {
                if (extension.equals("bin")) {
                    Maze.saveBin(maze, file);
                } else {
                    throw new Exception("Exencsión " + extension + " no permitida");

                }
            }

        }
    }
    
    /**
     * Serie de métodos privados que cargan y guardan archivos en diferentes
     * lenguajes de marcado, JSON, XML y binario
    */

    private static Maze loadJSON(File file) throws FileNotFoundException, IOException {
        Gson gson = new Gson();
        Reader reader;
        reader = Files.newBufferedReader(file.toPath());
        Maze m = gson.fromJson(reader, Maze.class);
        reader.close();
        return m;
    }

    private static Maze loadXML(File file) throws JAXBException, IOException {

           JAXBContext context = JAXBContext.newInstance(Maze.class);
                Unmarshaller unmarshaller = context.createUnmarshaller();
                Maze maze = (Maze) unmarshaller.unmarshal(
                        file);
                return maze;
          
    }

    public static Maze loadBin(File file) throws FileNotFoundException, IOException, ClassNotFoundException {
        FileInputStream os = new FileInputStream(file);
        
        ObjectInputStream oos = new ObjectInputStream(os);
        Maze m = (Maze) oos.readObject();
        oos.close();;
        os.close();
        return m;
    }

    private static void saveJSON(Maze maze, File file) throws FileNotFoundException, UnsupportedEncodingException {
        Gson gson = new Gson();
        String json = gson.toJson(maze);
        java.io.PrintWriter pw = new PrintWriter(file, "UTF-8");
        pw.print(json);
        pw.close();
    }

    private static void saveXML(Maze maze, File file) throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(maze.getClass());
        // create an instance of `Marshaller`
        Marshaller marshaller = context.createMarshaller();
        // enable pretty-print XML output
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        // write XML to `StringWriter`
        FileWriter fw = new FileWriter(file, StandardCharsets.UTF_8);//(file, "UTF-8");
        marshaller.marshal(maze, fw);
        fw.close();

    }

    public static void saveBin(Maze maze, File file) throws FileNotFoundException, IOException {
        OutputStream os = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(os);
        oos.writeObject(maze);
        oos.close();;
        os.close();
    }

}
