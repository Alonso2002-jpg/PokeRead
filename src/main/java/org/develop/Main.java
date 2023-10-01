package org.develop;

import org.develop.controllers.PokeReadCsv;
import org.develop.controllers.PokeWriteCSV;
import org.develop.controllers.PokemonController;
import org.develop.model.Pokemon;
import org.develop.repository.PokemonReposityImpl;
import org.develop.services.DatabaseManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Main {

    public static void main(String[] args) throws SQLException {
        PokeReadCsv pokeReadCsv=new PokeReadCsv();
        PokeWriteCSV pokeWriteCSV = new PokeWriteCSV();
        DatabaseManager dbManager = DatabaseManager.getInstance();
        PokemonController pokemonController = PokemonController.getInstance();
        PokemonReposityImpl pkRepoImp = PokemonReposityImpl.getInstance(dbManager);
        ArrayList<Pokemon> pokemons = pokeReadCsv.leerCSV();

        pokemons.stream()
                .forEach(poke -> pkRepoImp.save(poke));

        Optional<Pokemon> pk = pkRepoImp.findById(20);
        System.out.println(pk.isPresent()?pk.get():"not found");

        Optional<Pokemon> pk2 = pkRepoImp.findByName("Pikachu");
        System.out.println(pk2.isPresent()?pk2.get():"not found");

        System.out.println("Obtener Pokemon por ID");
       Pokemon pokemon=pokemonController.getPokemonXId(25);

        System.out.println("Obtener Primeros 10");
       List<String> primerosDiez = pokemonController.obtenerNom10Primeros();
       primerosDiez.forEach(System.out::println);

        System.out.println("Obtener Primeros 5");
        List<String> cincoUltimos = pokemonController.obtenerNom5Ultimos();
       cincoUltimos.forEach(System.out::println);

        System.out.println("Obtener PIKACHU");
        Optional<Pokemon> pika = pokemonController.getPokemonXName("Pikachu");
        System.out.println(pika.orElse(new Pokemon()));

        System.out.println("Obtener evolucion de Charmander");
        Optional<Pokemon> nextEvolution = pokemonController.getEvolution("Charmander");
        System.out.println(nextEvolution.orElse(new Pokemon()));

        System.out.println("Obtener Pokemons tipo 'Fire'");
        List<String> pokemonsXType=pokemonController.getPokeXType("Fire");
        pokemonsXType.forEach(System.out::println);

        System.out.println("Obtener Pokemons con Debilidad Water o Electric");
        List<String> weakPokemonsXType=pokemonController.getWeakPokeWatElec();
        weakPokemonsXType.forEach(System.out::println);

        System.out.println("Obtener el numero total de debilidades");
        long count = pokemonController.getCountOfWeak();
        System.out.println(count);

        System.out.println("Obtener el pokemon con mas Debilidades");
        Pokemon pokeMaxWeak = pokemonController.maxWeakPokemon().orElse(new Pokemon());
        System.out.println(pokeMaxWeak);

        System.out.println("Obtener el pokemon con menos evoluciones");
        Pokemon pokeMinEvo = pokemonController.minEvoPokemon().orElse(new Pokemon());
        System.out.println(pokeMinEvo);

        System.out.println("Obtener Pokemons que no tengan evoluciones tipo 'Fire'");
        Pokemon pokeType = pokemonController.getPokeNotEvoType("Fire").orElse(new Pokemon());
        System.out.println(pokeType);

        System.out.println("Obtener Pokemon de mas Peso");
        Pokemon pokeMaxWei = pokemonController.getPokeMaxWeight().orElse(new Pokemon());
        System.out.println(pokeMaxWei);

        System.out.println("Obtener Pokemon de mas Altura");
        Pokemon pokeMaxHei = pokemonController.getPokeMaxHeight().orElse(new Pokemon());
        System.out.println(pokeMaxHei);

        System.out.println("Obtener Pokemon con el nombre mas largo");
        Optional<Pokemon> pokeMaxLength = pokemonController.getPokemonLength();
        System.out.println(pokeMaxLength.get());

        System.out.println("Obtener la media de Pesos");
        double mediaPeso = pokemonController.getAverageWeight();
        System.out.println("Media de peso: " + mediaPeso);

        System.out.println("Obtener la media de Alturas");
        double mediaAltura = pokemonController.getAverageHeight();
        System.out.println("Media de altura: " + mediaAltura);

        System.out.println("Obtener la media de evoluciones");
        double mediaEvos = pokemonController.getAverageEvolutions();
        System.out.println(mediaEvos);

        System.out.println("Obtener la media de debilidades");
        double mediaWeak = pokemonController.getAverageWeaks();
        System.out.println(mediaWeak);

        System.out.println("Tipos de pokemon");
        var groupType= pokemonController.getGroupType();
        groupType.forEach((k,v)-> System.out.println("Type " + k + ": " + v));

        System.out.println("Debilidades de pokemon");
        var groupWeak=pokemonController.getGroupWeak();
        groupWeak.forEach((k,v)-> System.out.println("Weak " + k + ": " + v));

        System.out.println("Obtener Pokemons x num de evoluciones");
        var groupNumEvo = pokemonController.getPokeXNumEvo();
        groupNumEvo.forEach((k,v) -> System.out.println("Num. Evoluciones " + k + ": " + v));

        System.out.println("Obtener Debilidad mas comun");
        String commonWeak = pokemonController.getMostCommonWeak();
        System.out.println("Most Common Weak is " +commonWeak);

        System.out.println("Escribiendo CSV de Pokemons");
        pokeWriteCSV.escribirCSV(pokemonController);
    }
}