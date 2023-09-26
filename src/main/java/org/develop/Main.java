package org.develop;

import org.develop.controllers.PokeReadCsv;
import org.develop.model.Pokemon;
import org.develop.repository.PokemonReposityImpl;
import org.develop.services.DatabaseManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class Main {

    public static void main(String[] args) throws SQLException {
        PokeReadCsv pokeReadCsv=new PokeReadCsv();
        DatabaseManager dbManager = DatabaseManager.getInstance();
        PokemonReposityImpl pkRepoImp = PokemonReposityImpl.getInstance(dbManager);
        ArrayList<Pokemon> pokemons = pokeReadCsv.leerCSV();

        pokemons.stream()
                .forEach(poke -> pkRepoImp.save(poke));

        Optional<Pokemon> pk = pkRepoImp.findById(20);
        System.out.println(pk.isPresent()?pk.get():"not found");

        Optional<Pokemon> pk2 = pkRepoImp.findByName("Pikachu");
        System.out.println(pk2.isPresent()?pk2.get():"not found");

//        System.out.println("Obtener Pokemon por ID");
//       Pokemon pokemon=pokemonController.getPokemonXId(25);
//
//        System.out.println("Obtener Primeros 10");
//       List<String> primerosDiez = pokemonController.obtenerNom10Primeros();git
//       primerosDiez.forEach(System.out::println);
//
//        System.out.println("Obtener Primeros 5");
//       List<String> cincoUltimos = pokemonController.obtenerNom5Ultimos();
//       cincoUltimos.forEach(System.out::println);
//
//        System.out.println();
//        Optional<Pokemon> pika = pokemonController.getPokemonXName("Pikachu");
//
//        System.out.println(pika.get());
//
//        Optional<Pokemon> nextEvolution = pokemonController.getEvolution("Charmander");
//        System.out.println(nextEvolution.orElse(new Pokemon()));
//
//        List<String> pokemonsXType=pokemonController.getPokeXType("Fire");
//        pokemonsXType.forEach(System.out::println);
//
//        List<String> weakPokemonsXType=pokemonController.getWeakPokeWatElec();
//        weakPokemonsXType.forEach(System.out::println);
//
//        long count = pokemonController.getCountOfWeak();
//        System.out.println(count);
//
//        Pokemon pokeMaxWeak = pokemonController.maxWeakPokemon().get();
//        System.out.println(pokeMaxWeak);
//
//        Pokemon pokeMinEvo = pokemonController.minEvoPokemon().get();
//        System.out.println(pokeMinEvo);
//
//        Pokemon pokeType = pokemonController.getPokeNotEvoType("Fire").get();
//        System.out.println(pokeType);
//
//        Pokemon evo = pokemonController.getEvolution(pokeType.getName()).get();
//        System.out.println(evo);
//
//        Optional<Pokemon> pokeMaxWei = pokemonController.getPokeMaxWeight();
//        System.out.println(pokeMaxWei.get());
//
//        Optional<Pokemon> pokeMaxHei = pokemonController.getPokeMaxHeight();
//        System.out.println(pokeMaxHei.get());
//
//        Optional<Pokemon> pokeMaxLength = pokemonController.getPokemonLength();
//        System.out.println(pokeMaxLength.get());
//
//        double mediaPeso = pokemonController.getAverageWeight();
//        System.out.println("Media de peso: " + mediaPeso);
//
//        double mediaAltura = pokemonController.getAverageHeight();
//        System.out.println("Media de altura: " + mediaAltura);
//
//        double mediaEvos = pokemonController.getAverageEvolutions();
//        System.out.println(mediaEvos);
//
//        double mediaWeak = pokemonController.getAverageWeaks();
//        System.out.println(mediaWeak);
//        System.out.println("Tipos de pokemon");
//        var groupType= pokemonController.getGroupType();
//        groupType.forEach((k,v)-> System.out.println("Type " + k + ": " + v));
//
//        System.out.println("Debilidades de pokemon");
//        var groupWeak=pokemonController.getGroupWeak();
//        groupWeak.forEach((k,v)-> System.out.println("Weak " + k + ": " + v));
//
//        var groupNumEvo = pokemonController.getPokeXNumEvo();
//        groupNumEvo.forEach((k,v) -> System.out.println("Num. Evoluciones " + k + ": " + v));
//
//        String commonWeak = pokemonController.getMostCommonWeak();
//        System.out.println("Most Common Weak is " +commonWeak);
    }
}