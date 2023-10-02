package org.develop.controllers;

import com.opencsv.CSVReader;
import org.develop.model.Pokemon;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * La clase PokeReadCsv se utiliza para leer datos desde un archivo CSV y crear una lista de Pokemon.
 */
public class PokeReadCsv {
    String path;  //Ruta al archivo CSV

    /**
     * Lee datos desde un archivo CSV y crea una lista de Pokemon a partir de los datos leidos.
     *
     * @return Una lista de Pokemon creada a partir de los datos del archivo CSV.
     */
    public ArrayList<Pokemon> leerCSV(){
        ArrayList<Pokemon> pokedex = new ArrayList<Pokemon>();
        path= Paths.get("").toAbsolutePath().toString() + File.separator + "data" + File.separator + "pokedex.csv";

        try (CSVReader reader = new CSVReader(new FileReader(path))){
            String[] line;
            //Saltamos la primera linea con la información
            reader.readNext();

            //Leeremos todos los datos del csv
            while ((line = reader.readNext()) != null){
                Pokemon pk=new Pokemon();
                pk.setId(Integer.parseInt(line[0]));
                pk.setNum(line[1]);
                pk.setName(line[2]);
                pk.setHeight(line[3]);
                pk.setWeight(line[4]);
                pokedex.add(pk);
            }
        }catch (IOException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
        return pokedex;
    }
}
