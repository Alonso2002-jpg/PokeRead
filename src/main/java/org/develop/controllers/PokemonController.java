package org.develop.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.develop.model.NextEvolution;
import org.develop.model.Pokedex;
import org.develop.model.Pokemon;

import java.awt.*;
import java.io.File;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

public class PokemonController {
    private static PokemonController instance;
    private Pokedex pokedex;
 
    private PokemonController() {
        cargarPokedex();
    }

    public static PokemonController getInstance() {
        if (instance == null) {
            instance = new PokemonController();
        }
        return instance;
    }
    public void cargarPokedex() {
       Path rutaRelativa = Paths.get("");
        String rutaAbsoluta = rutaRelativa.toAbsolutePath().toString();
        String rutaData = rutaAbsoluta + File.separator + "data";
        String rutaPokemon = rutaData + File.separator + "pokemon.json";
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        // Actualizar a try-with-resources
        try (Reader reader = Files.newBufferedReader(Paths.get(rutaPokemon))) {
            this.pokedex = gson.fromJson(reader, new TypeToken<Pokedex>() {}.getType());
            System.out.println("Pokedex loaded! There are: " + pokedex.pokemon.size());
        } catch (Exception e) {
            System.out.println("Error loading Pokedex!");
            System.out.println("Error: "); e.printStackTrace();
        }
    }

    public ArrayList<Pokemon> obtenerPokemons(){
        return this.pokedex.pokemon;
    }

    public Pokemon getPokemonXId(int id){
        return this.pokedex.pokemon.get(id);
    }

    public List<String> obtenerNom10Primeros(){
        List<String> list = obtenerPokemons()
                .stream()
                .limit(10)
                .map(Pokemon::getName)
                .toList();

        return list;
    }

    public List<String> obtenerNom5Ultimos(){
         List<String> list = obtenerPokemons()
                .stream()
                 .skip(pokedex.pokemon.size()-5)
                 .map(Pokemon::getName)
                 .toList();

         return list;
    }

    public Optional<Pokemon> getPokemonXName(String name){
        Optional<Pokemon> pokemon = obtenerPokemons()
                .stream()
                .filter(pk -> name.equalsIgnoreCase(pk.getName()))
                .findFirst();

        return pokemon.isPresent()?pokemon:Optional.of(new Pokemon());
    }

    public Optional<Pokemon> getEvolution(String name){
        Optional<NextEvolution> nextEvolution =
                obtenerPokemons()
                .stream()
                .filter(pk -> name.equalsIgnoreCase(pk.getName()))
                .findFirst()
                .flatMap(pk -> pk.getNext_evolution().stream().findFirst());

        if (nextEvolution.isEmpty()){
            return Optional.of(new Pokemon());
        }

        Optional<Pokemon> EvPokemon = obtenerPokemons()
                .stream()
                .filter(pk -> pk.getName().equals(nextEvolution.get().getName()))
                .findFirst();

        return EvPokemon;
    }

    public List<String> getPokeXType(String type){
        List<String> pokemonxType= obtenerPokemons()
                .stream()
                .filter(pk -> pk.getType().contains(type))
                .map(Pokemon::getName)
                .toList();

        return pokemonxType;
    }

    public List<String> getWeakPokeWatElec(){
        List<String> weakPoke = obtenerPokemons()
                .stream()
                .filter(pk -> pk.getWeaknesses().contains("Water") || pk.getWeaknesses().contains("Electric"))
                .map(Pokemon::getName)
                .toList();

        return weakPoke;
    }

    public long getCountOfWeak(){
        long count = obtenerPokemons()
                .stream()
                .filter(pk -> pk.getWeaknesses().size()==1)
                .count();

        return count;
    }

    public Optional<Pokemon> maxWeakPokemon(){
        Optional<Pokemon> countWeak = obtenerPokemons()
                .stream()
                .max((a,b) -> a.getWeaknesses().size()-b.getWeaknesses().size());

        if (countWeak.isEmpty()){
            return Optional.of(new Pokemon());
        }
        return countWeak;
    }

     public Optional<Pokemon> minEvoPokemon(){
        Optional<Pokemon> countEvo = obtenerPokemons()
                .stream()
                .filter(pk -> pk.getNext_evolution() != null)
                .min((a,b) -> a.getNext_evolution().size()-b.getNext_evolution().size());

        if (countEvo.isEmpty()){
            return Optional.of(new Pokemon());
        }
        return countEvo;
    }

    public Optional<Pokemon> getPokeNotEvoType(String type){
        List<NextEvolution> evoNotType = obtenerPokemons()
                .stream()
                .filter(pk-> pk.getNext_evolution()!= null)
                .flatMap(pkE-> pkE.getNext_evolution().stream())
                .filter(pkEv -> !getPokeXType(type).contains(pkEv.getName()))
                .toList();

        Optional<Pokemon> pokeNotType = obtenerPokemons()
                .stream()
                .filter(pk-> pk.getNext_evolution()!=null)
                        .filter(pk -> pk.getNext_evolution().stream().anyMatch(evoNotType::contains))
                                .findFirst();

    if (pokeNotType.isEmpty()){
        return Optional.of(new Pokemon());
    }
        return pokeNotType;
    }

    public Optional<Pokemon> getPokeMaxWeight(){
        OptionalDouble max = obtenerPokemons()
                .stream()
                .mapToDouble(pk -> pk.parseWeight())
                .max();

        Optional<Pokemon> pokeMaxWeight = obtenerPokemons()
                .stream()
                .filter(pk -> pk.parseWeight() == max.getAsDouble())
                .findFirst();

        if (pokeMaxWeight.isEmpty()){
            return Optional.of(new Pokemon());
        }

        return pokeMaxWeight;
    }

    public Optional<Pokemon> getPokeMaxHeight(){
        OptionalDouble max = obtenerPokemons()
                .stream()
                .mapToDouble(pk -> pk.parseHeight())
                .max();

        Optional<Pokemon> pokeMaxHeight = obtenerPokemons()
                .stream()
                .filter(pk -> pk.parseHeight() == max.getAsDouble())
                .findFirst();

        if (pokeMaxHeight.isEmpty()){
            return Optional.of(new Pokemon());
        }

        return pokeMaxHeight;
    }

    public Optional<Pokemon> getPokemonLength(){
        var maxLength = obtenerPokemons()
                .stream()
                .map(pk -> pk.getName().length())
                .max(Integer::compare);

        Optional<Pokemon> pokeMaxLength = obtenerPokemons()
                .stream()
                .filter(pk -> pk.getName().length() == maxLength.get())
                .findFirst();

        if (pokeMaxLength.isEmpty()){
            return Optional.of(new Pokemon());
        }

        return pokeMaxLength;
    }

    public double getAverage(DoubleStream stream){
        return stream.average().getAsDouble();
    }
    public double getAverageWeight(){
        var averageWeight = obtenerPokemons()
                .stream()
                .mapToDouble(pk -> pk.parseWeight());

        return getAverage(averageWeight);
    }

    public double getAverageHeight(){
        var averageHeight = obtenerPokemons()
                .stream()
                .mapToDouble(pk -> pk.parseHeight());

        return getAverage(averageHeight);
    }

    public double getAverageEvolutions(){
        var averageEvolutions = obtenerPokemons()
                .stream()
                .filter(pk -> pk.getNext_evolution()!=null)
                .mapToDouble(pk -> pk.getNext_evolution().size());

        return getAverage(averageEvolutions);
    }

    public double getAverageWeaks(){
        var averageWeaks = obtenerPokemons()
                .stream()
                .filter(pk -> pk.getType() != null)
                .mapToDouble(pk -> pk.getType().size());

        return getAverage(averageWeaks);
    }

    public Map<String, Long> getGroupType(){
        Map<String, Long> pokeTypes = obtenerPokemons()
                .stream()
                .map(pk -> pk.getType())
                .flatMap(ArrayList::stream)
                        .collect(Collectors.groupingBy(tp -> tp, Collectors.counting()));
        return pokeTypes;
    }

    public Map<String, Long> getGroupWeak(){
        Map<String, Long> pokeWeak = obtenerPokemons()
                .stream()
                .map(pk -> pk.getWeaknesses())
                .flatMap(ArrayList::stream)
                .collect(Collectors.groupingBy(pw -> pw, Collectors.counting()));
        return pokeWeak;
    }

    public Map<Integer, Long> getPokeXNumEvo(){
        Map<Integer, Long> pokesxEvo = obtenerPokemons()
                .stream()
                .filter(pk -> pk.getNext_evolution() != null)
                .collect(Collectors.groupingBy(pk -> pk.getNext_evolution().size(),Collectors.counting()))
                .entrySet()
                .stream()
                .collect( Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));


        return pokesxEvo;
    }

    public String getMostCommonWeak(){

        String commonWeak = getGroupType()
                .entrySet()
                .stream()
                .max(Comparator.comparingInt(m -> Long.valueOf(m.getValue()).intValue()))
                .get()
                .getKey();

        return commonWeak;
    }
    public static void main(String[] args) {
        var poke = PokemonController.getInstance();
        ArrayList<List<Integer>> lista = new ArrayList<>();
        lista.add(Arrays.asList(1,2,3));
        lista.add(Arrays.asList(4,5,6));
        lista.add(Arrays.asList(7,8,9));

        lista.forEach(System.out::println);

        var nuevaLista = lista.stream()
                .findAny()
                .stream()
                .flatMap( nu -> nu.stream())
                        .toList();

        System.out.println(nuevaLista);


    }
}
