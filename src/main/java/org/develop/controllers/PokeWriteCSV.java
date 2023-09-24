package org.develop.controllers;

import com.opencsv.CSVWriter;
import org.develop.model.Pokemon;

import java.io.FileWriter;
import java.io.IOException;
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

    public static void main(String[] args) {
        // Supongamos que tienes una lista de Pokémon llamada 'pokedex'
        PokemonController pokedex = PokemonController.getInstance(); // Debes proporcionar tu propia implementación de obtenerPokedex()
        Properties properties = new Properties();
        String dataUrl = properties.getProperty("data.url","pokedex.csv");
        // Ruta del archivo CSV donde deseas guardar la pokedex

        // Crear un objeto de PokemonCSVWriter y guardar la pokedex en el archivo CSV
        PokeWriteCSV csvWriter = new PokeWriteCSV();
        csvWriter.writePokedexToCSV(pokedex.obtenerPokemons(), dataUrl);

        System.out.println("Pokedex guardada en " + dataUrl);
    }

}
