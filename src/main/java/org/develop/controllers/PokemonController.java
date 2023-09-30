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
/**
 * La clase PokemonController es un controlador que gestiona la interacción con la Pokedex de Pokémon.
 * Es responsable de cargar la Pokedex y proporcionar acceso a ella a través de su instancia única.
 */
public class PokemonController {
    private static PokemonController instance;
    private Pokedex pokedex;

    /**
     * Constructor privado de PokemonController.
     * Carga la Pokedex al crear una instancia de la clase.
     */
    private PokemonController() {
        cargarPokedex();
    }

    /**
     * Obtiene la instancia única de PokemonController (patrón Singleton).
     * Si no existe una instancia previa, crea una nueva.
     *
     * @return La instancia única de PokemonController.
     */
    public static PokemonController getInstance() {
        if (instance == null) {
            instance = new PokemonController();
        }
        return instance;
    }

    /**
     * Cierra la instancia única de PokemonController.
     * Este método establece la instancia en null, lo que permite que se cree una nueva instancia.
     */
    public static void closeInstance() {
        instance=null;
    }

    /**
     * Carga la Pokedex de Pokémon desde un archivo JSON en el sistema de archivos.
     * Utiliza la biblioteca Gson para realizar la conversión de JSON a objetos Java.
     * La Pokedex cargada se almacena en la instancia actual de PokemonController.
     */
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

    /**
     * Obtiene la lista de Pokémon almacenados en la Pokedex.
     *
     * @return Una lista de objetos Pokemon que representa a todos los Pokémon en la Pokedex.
     */
    public ArrayList<Pokemon> obtenerPokemons(){
        return this.pokedex.pokemon;
    }

    /**
     * Obtiene un Pokémon específico por su ID.
     *
     * @param id El ID del Pokémon que se desea obtener.
     * @return El objeto Pokemon correspondiente al ID proporcionado, o null si no se encuentra.
     */

    public Pokemon getPokemonXId(int id){
        return this.pokedex.pokemon.get(id);
    }

    /**
     * Obtiene una lista de los nombres de los 10 primeros Pokémon en la Pokedex.
     *
     * @return Una lista de nombres de los 10 primeros Pokémon.
     */
    public List<String> obtenerNom10Primeros(){
        List<String> list = obtenerPokemons()
                .stream()
                .limit(10)
                .map(Pokemon::getName)
                .toList();

        return list;
    }

    /**
     * Obtiene una lista de los nombres de los últimos 5 Pokémon en la Pokedex.
     *
     * @return Una lista de nombres de los últimos 5 Pokémon.
     */
    public List<String> obtenerNom5Ultimos(){
         List<String> list = obtenerPokemons()
                .stream()
                 .skip(pokedex.pokemon.size()-5)
                 .map(Pokemon::getName)
                 .toList();

         return list;
    }

    /**
     * Obtiene un Pokémon por su nombre, ignorando mayúsculas y minúsculas.
     *
     * @param name El nombre del Pokémon que se desea obtener.
     * @return Un objeto Optional que contiene el Pokémon correspondiente al nombre proporcionado,
     *         o un Optional vacío si no se encuentra un Pokémon con ese nombre.
     */
    public Optional<Pokemon> getPokemonXName(String name){
        Optional<Pokemon> pokemon = obtenerPokemons()
                .stream()
                .filter(pk -> name.equalsIgnoreCase(pk.getName()))
                .findFirst();

        return pokemon.isPresent()?pokemon:Optional.of(new Pokemon());
    }

    /**
     * Obtiene la evolución de un Pokémon por su nombre.
     *
     * @param name El nombre del Pokémon del que se desea obtener la evolución.
     * @return Un objeto Optional que contiene la evolución del Pokémon correspondiente al nombre proporcionado,
     *         o un Optional vacío si el Pokémon no tiene una evolución registrada.
     */
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

        /**
         * Obtiene un Pokémon que representa la evolución del Pokémon especificado por su nombre.
         *
         * @param nextEvolutionName El nombre del Pokémon del que se desea obtener la evolución.
         * @return Un objeto Optional que contiene el Pokémon que representa la evolución del Pokémon especificado,
         *         o un Optional vacío si no se encuentra la evolución correspondiente.
         */
        Optional<Pokemon> EvPokemon = obtenerPokemons()
                .stream()
                .filter(pk -> pk.getName().equals(nextEvolution.get().getName()))
                .findFirst();

        return EvPokemon;
    }

    /**
     * Obtiene una lista de nombres de Pokémon que pertenecen al tipo especificado.
     *
     * @param type El tipo de Pokémon que se desea buscar.
     * @return Una lista de nombres de Pokémon que pertenecen al tipo especificado.
     */

    public List<String> getPokeXType(String type){
        List<String> pokemonxType= obtenerPokemons()
                .stream()
                .filter(pk -> pk.getType().contains(type))
                .map(Pokemon::getName)
                .toList();

        return pokemonxType;
    }


    /**
     * Obtiene una lista de nombres de Pokémon que tienen debilidades frente a "Water" o "Electric".
     *
     * @return Una lista de nombres de Pokémon con debilidades frente a "Water" o "Electric".
     */
    public List<String> getWeakPokeWatElec(){
        List<String> weakPoke = obtenerPokemons()
                .stream()
                .filter(pk -> pk.getWeaknesses().contains("Water") || pk.getWeaknesses().contains("Electric"))
                .map(Pokemon::getName)
                .toList();

        return weakPoke;
    }

    /**
     * Obtiene la cantidad de Pokémon que tienen exactamente una debilidad.
     *
     * @return El número de Pokémon que tienen una sola debilidad.
     */
    public long getCountOfWeak(){
        long count = obtenerPokemons()
                .stream()
                .filter(pk -> pk.getWeaknesses().size()==1)
                .count();

        return count;
    }

    /**
     * Obtiene el Pokémon con la mayor cantidad de debilidades.
     *
     * @return Un objeto Optional que contiene el Pokémon con la mayor cantidad de debilidades,
     *         o un Optional vacío si no se encuentra ningún Pokémon.
     */

    public Optional<Pokemon> maxWeakPokemon(){
        Optional<Pokemon> countWeak = obtenerPokemons()
                .stream()
                .max((a,b) -> a.getWeaknesses().size()-b.getWeaknesses().size());

        if (countWeak.isEmpty()){
            return Optional.of(new Pokemon());
        }
        return countWeak;
    }

    /**
     * Obtiene el Pokémon con la menor cantidad de evoluciones.
     *
     * @return Un objeto Optional que contiene el Pokémon con la menor cantidad de evoluciones,
     *         o un Optional vacío si no se encuentra ningún Pokémon con evoluciones.
     */
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

    /**
     * Obtiene un Pokémon que no evoluciona a un Pokémon del tipo especificado.
     *
     * @param type El tipo de Pokémon que se desea evitar en las evoluciones.
     * @return Un objeto Optional que contiene un Pokémon que no evoluciona a un Pokémon del tipo especificado,
     *         o un Optional vacío si no se encuentra ningún Pokémon que cumpla con esta condición.
     */
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

    /**
     * Obtiene el Pokémon con el mayor peso.
     *
     * @return Un objeto Optional que contiene el Pokémon con el mayor peso,
     *         o un Optional vacío si no se encuentra ningún Pokémon.
     */

    public Optional<Pokemon> getPokeMaxWeight(){
        OptionalDouble max = obtenerPokemons()
                .stream()
                .mapToDouble(Pokemon::parseWeight)
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

    /**
     * Obtiene el Pokémon con la mayor altura.
     *
     * @return Un objeto Optional que contiene el Pokémon con la mayor altura,
     *         o un Optional vacío si no se encuentra ningún Pokémon.
     */
    public Optional<Pokemon> getPokeMaxHeight(){
        // Encuentra el valor máximo de altura utilizando la función de mapeo y luego busca el Pokémon correspondiente.
        OptionalDouble max = obtenerPokemons()
                .stream()
                .mapToDouble(Pokemon::parseHeight)
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


    /**
     * Obtiene el Pokémon cuyo nombre tiene la longitud máxima.
     *
     * @return Un objeto Optional que contiene el Pokémon cuyo nombre tiene la longitud máxima,
     *         o un Optional vacío si no se encuentra ningún Pokémon.
     */
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

    /**
     * Calcula el promedio de un flujo de valores de tipo Double.
     *
     * @param stream El flujo de valores Double para el cual se desea calcular el promedio.
     * @return El promedio de los valores en el flujo como un número de punto flotante.
     */
    public double getAverage(DoubleStream stream){
        return stream.average().getAsDouble();
    }

     /**
      * Obtiene el promedio de peso de todos los Pokémon en la Pokedex.
      *
      * @return El promedio de peso de todos los Pokémon como un número de punto flotante.
      */
    public double getAverageWeight(){
        var averageWeight = obtenerPokemons()
                .stream()
                .mapToDouble(Pokemon::parseWeight);

        return getAverage(averageWeight);
    }

    /**
     * Obtiene el promedio de altura de todos los Pokémon en la Pokedex.
     *
     * @return El promedio de altura de todos los Pokémon como un número de punto flotante.
     */
    public double getAverageHeight(){
        var averageHeight = obtenerPokemons()
                .stream()
                .mapToDouble(Pokemon::parseHeight);

        return getAverage(averageHeight);
    }

    /**
     * Obtiene el promedio de la cantidad de evoluciones de los Pokémon en la Pokedex.
     *
     * @return El promedio de la cantidad de evoluciones de los Pokémon como un número de punto flotante.
     */
    public double getAverageEvolutions(){
        var averageEvolutions = obtenerPokemons()
                .stream()
                .filter(pk -> pk.getNext_evolution()!=null)
                .mapToDouble(pk -> pk.getNext_evolution().size());
        System.out.println(averageEvolutions);

        return getAverage(averageEvolutions);
    }

    /**
     * Obtiene el promedio de la cantidad de tipos a los que pertenecen los Pokémon en la Pokedex.
     *
     * @return El promedio de la cantidad de tipos a los que pertenecen los Pokémon como un número de punto flotante.
     */
    public double getAverageWeaks(){
        var averageWeaks = obtenerPokemons()
                .stream()
                .filter(pk -> pk.getType() != null)
                .mapToDouble(pk -> pk.getType().size());

        return getAverage(averageWeaks);
    }

    /**
     * Obtiene un mapa que agrupa los Pokémon por tipo.
     *
     * @return Un mapa donde las claves son los tipos y los valores son listas de Pokémon que pertenecen a ese tipo.
     */
    public Map<String, List<Pokemon>> getGroupType(){

        return obtenerPokemons()
                .stream()
                .flatMap(pk-> pk.getType().stream())
                .distinct()
                .collect(Collectors
                        .toMap(type -> type,
                                type -> obtenerPokemons().stream().filter(pk -> pk.getType().contains(type))
                                        .toList()));
    }

    public Map<String, Long> getGroupWeak(){
        return obtenerPokemons()
                .stream()
                .flatMap(pk-> pk.getWeaknesses().stream())
                .distinct()
                .collect(Collectors
                        .toMap(weak -> weak,
                                weak -> obtenerPokemons().stream().filter(pk -> pk.getWeaknesses().contains(weak))
                                        .toList()));

    }

    /**
     * Obtiene un mapa que agrupa los Pokémon por la cantidad de evoluciones que tienen.
     *
     * @return Un mapa donde las claves son la cantidad de evoluciones y los valores son la cantidad de Pokémon que tienen esa cantidad de evoluciones.
     */
    public Map<Integer, Long> getPokeXNumEvo(){


        return obtenerPokemons()
                .stream()
                .filter(pk -> pk.getNext_evolution() != null)
                .collect(Collectors.groupingBy(pk -> pk.getNext_evolution().size(),Collectors.counting()))
                .entrySet()
                .stream()
                .collect( Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    /**
     * Obtiene el tipo de debilidad más común entre los Pokémon.
     *
     * @return El tipo de debilidad más común entre los Pokémon.
     */
    public String getMostCommonWeak(){

        return obtenerPokemons()
                .stream()
                .flatMap(pk -> pk.getWeaknesses().stream())
                .collect(Collectors.groupingBy(mp -> mp, Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Comparator.comparingLong(Map.Entry::getValue)))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse("NaN");
    }
    public static void main(String[] args) {
        var poke = PokemonController.getInstance();
        //poke.getGroupWeak().forEach((k,v) -> System.out.println(k + " : " + v));
        poke.obtenerPokemons().stream()
                .flatMap(pk -> pk.getWeaknesses().stream())
                .collect(Collectors.groupingBy(pk-> pk, Collectors.counting()))
                        .forEach((a,b) -> System.out.println(a + " : " + b));
        System.out.println("Weak mas frecuente: " + poke.getMostCommonWeak());

    }
}
