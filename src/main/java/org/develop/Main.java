package org.develop;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.develop.controllers.PokeWriteCSV;
import org.develop.controllers.PokemonController;
import org.develop.model.NextEvolution;
import org.develop.model.Pokemon;
import org.develop.services.DatabaseManager;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
       PokemonController pokemonController = PokemonController.getInstance();
        DatabaseManager dbManager = DatabaseManager.getInstance();
        PokeWriteCSV writeCSV = new PokeWriteCSV();
        writeCSV.escribirCSV(pokemonController);
       //Pokemon pokemon=pokemonController.getPokemonXId(25);
       //List<String> primerosDiez = pokemonController.obtenerNom10Primeros();git

       //primerosDiez.forEach(System.out::println);

       //List<String> cincoUltimos = pokemonController.obtenerNom5Ultimos();

       //cincoUltimos.forEach(System.out::println);

        // Optional<Pokemon> pika = pokemonController.getPokemonXName("Pikacf");

        //System.out.println(pika);

        //Optional<Pokemon> nextEvolution = pokemonController.getEvolution("Raichu");

        //System.out.println(nextEvolution.orElse(new Pokemon()));

        //List<String> pokemonsXType=pokemonController.getPokeXType("Fire");
        //pokemonsXType.forEach(System.out::println);

        //List<String> weakPokemonsXType=pokemonController.getWeakPokeWatElec();
        //weakPokemonsXType.forEach(System.out::println);

        //long count = pokemonController.getCountOfWeak();
        //System.out.println(count);

        //Pokemon pokeMaxWeak = pokemonController.maxWeakPokemon().get();
        //System.out.println(pokeMaxWeak);

        //Pokemon pokeMinEvo = pokemonController.minEvoPokemon().get();
        //System.out.println(pokeMinEvo);

        //Pokemon pokeType = pokemonController.getPokeNotEvoType("Fire").get();
        //System.out.println(pokeType);

        //Pokemon evo = pokemonController.getEvolution(pokeType.getName()).get();
        //System.out.println(evo);

        //Optional<Pokemon> pokeMaxWei = pokemonController.getPokeMaxWeight();
        //System.out.println(pokeMaxWei.get());

        //Optional<Pokemon> pokeMaxHei = pokemonController.getPokeMaxHeight();
        //System.out.println(pokeMaxHei.get());

        //Optional<Pokemon> pokeMaxLength = pokemonController.getPokemonLength();
        //System.out.println(pokeMaxLength.get());

        //double mediaPeso = pokemonController.getAverageWeight();
        //System.out.println("Media de peso: " + mediaPeso);

        //double mediaAltura = pokemonController.getAverageHeight();
        //System.out.println("Media de altura: " + mediaAltura);

        //double mediaEvos = pokemonController.getAverageEvolutions();
        //System.out.println(mediaEvos);

        //double mediaWeak = pokemonController.getAverageWeaks();
        //System.out.println(mediaWeak);
        //System.out.println("Tipos de pokemon");
        //var groupType= pokemonController.getGroupType();
        //groupType.forEach((k,v)-> System.out.println("Type " + k + ": " + v));

        //System.out.println("Debilidades de pokemon");
        //var groupWeak=pokemonController.getGroupWeak();
        //groupWeak.forEach((k,v)-> System.out.println("Weak " + k + ": " + v));

        //var groupNumEvo = pokemonController.getPokeXNumEvo();
        //groupNumEvo.forEach((k,v) -> System.out.println("Num. Evoluciones " + k + ": " + v));

        //String commonWeak = pokemonController.getMostCommonWeak();
        //System.out.println("Most Common Weak is " +commonWeak);
    }
}