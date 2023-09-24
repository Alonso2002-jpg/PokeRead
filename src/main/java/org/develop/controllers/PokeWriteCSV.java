package org.develop.controllers;

import com.opencsv.CSVWriter;
import org.develop.model.Pokemon;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;

public class PokeWriteCSV {

        public void writePokedexToCSV(List<Pokemon> pokedex, String filePath) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
            // Escribir el encabezado del CSV
            String[] header = {"Id", "Número","Nombre", "Altura", "Peso"};
            writer.writeNext(header);

            // Escribir los datos de los Pokémon
            for (Pokemon pokemon : pokedex) {
                String[] data = {
                        String.valueOf(pokemon.getId()),
                        pokemon.getNum(),
                        pokemon.getName(),
                        pokemon.getHeight(),
                        pokemon.getWeight(),
                };
                writer.writeNext(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void escribirCSV(PokemonController pkController){
        Path rutaRelativa = Paths.get("");
        String rutaAbsoluta = rutaRelativa.toAbsolutePath().toString();
        String rutaData = rutaAbsoluta + File.separator + "data";
        String rutaEscritura = rutaData +File.separator + "pokedex.csv";
        PokeWriteCSV csvWriter = new PokeWriteCSV();
        csvWriter.writePokedexToCSV(pkController.obtenerPokemons(), rutaEscritura);

        System.out.println("Pokedex guardada en " + rutaEscritura);
    }

}
