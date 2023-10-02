package org.develop.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
/** La clase Pokemon representa a una entidad que almacena informacion detallada sobre Pokemon,
 *  incluyendo su identificacion, nombre, tipo, caracteristicas fisicas, estadisticas y detalles evolutivos.
 *  Tambien en esta clase le asignamos los getters and setters para ocultar los detalles internos de una clase y proporcionar
 *  un control estructurado sobre como se accede y modifica su estado. */
@Data

public class Pokemon{

    private int id;
    private String num;
    private String name;
    private String img;
    private ArrayList<String> type;
    private String height;
    private String weight;
    private String candy;
    private int candy_count;
    private String egg;
    private double spawn_chance;
    private double avg_spawns;
    private String spawn_time;
    private ArrayList<Double> multipliers;
    private ArrayList<String> weaknesses;
    private ArrayList<NextEvolution> next_evolution;
    private ArrayList<PrevEvolution> prev_evolution;

 public int getId() {
  return id;
 }

 public void setId(int id) {
  this.id = id;
 }

 public String getNum() {
  return num;
 }

 public void setNum(String num) {
  this.num = num;
 }

 public String getName() {
  return name;
 }

 public void setName(String name) {
  this.name = name;
 }

 public String getImg() {
  return img;
 }

 public void setImg(String img) {
  this.img = img;
 }

 public ArrayList<String> getType() {
  return type;
 }

 public void setType(ArrayList<String> type) {
  this.type = type;
 }

 public String getHeight() {
  return height;
 }

 public void setHeight(String height) {
  this.height = height;
 }

 public String getWeight() {
  return weight;
 }

 public void setWeight(String weight) {
  this.weight = weight;
 }

 public String getCandy() {
  return candy;
 }

 public void setCandy(String candy) {
  this.candy = candy;
 }

 public int getCandy_count() {
  return candy_count;
 }

 public void setCandy_count(int candy_count) {
  this.candy_count = candy_count;
 }

 public String getEgg() {
  return egg;
 }

 public void setEgg(String egg) {
  this.egg = egg;
 }

 public double getSpawn_chance() {
  return spawn_chance;
 }

 public void setSpawn_chance(double spawn_chance) {
  this.spawn_chance = spawn_chance;
 }

 public double getAvg_spawns() {
  return avg_spawns;
 }

 public void setAvg_spawns(double avg_spawns) {
  this.avg_spawns = avg_spawns;
 }

 public String getSpawn_time() {
  return spawn_time;
 }

 public void setSpawn_time(String spawn_time) {
  this.spawn_time = spawn_time;
 }

 public ArrayList<Double> getMultipliers() {
  return multipliers;
 }

 public void setMultipliers(ArrayList<Double> multipliers) {
  this.multipliers = multipliers;
 }

 public ArrayList<String> getWeaknesses() {
  return weaknesses;
 }

 public void setWeaknesses(ArrayList<String> weaknesses) {
  this.weaknesses = weaknesses;
 }

 public ArrayList<NextEvolution> getNext_evolution() {
  return next_evolution;
 }

 public void setNext_evolution(ArrayList<NextEvolution> next_evolution) {
  this.next_evolution = next_evolution;
 }

 public ArrayList<PrevEvolution> getPrev_evolution() {
  return prev_evolution;
 }

 public void setPrev_evolution(ArrayList<PrevEvolution> prev_evolution) {
  this.prev_evolution = prev_evolution;
 }
 /**
  * Convierte el atributo de peso del Pokemon en formato String a un valor numerico.
  *
  * @return El peso del Pokemon en formato numerico (en kilogramos).
  */
 public double parseWeight(){
   String medidas[]=weight.split(" ");
   return Double.parseDouble(medidas[0]);
 }

 /**
  * Convierte el atributo de altura del Pokemon en formato String a un valor numerico.
  *
  * @return La altura del Pokemon en formato numerico (en metros).
  */
 public double parseHeight(){
   String medidas[]=height.split(" ");
   return Double.parseDouble(medidas[0]);
 }

 /**
  * Devuelve una representacion en forma de cadena del objeto Pokemon.
  * Esta representacion incluye todos los atributos del Pokemon, como su ID, numero,
  * nombre, imagen, tipo, altura, peso, golosina, cantidad de golosina, huevo, probabilidad de aparicion,
  * promedio de apariciones, tiempo de aparicion, multiplicadores, debilidades, evoluciones previas
  * y evoluciones posteriores.
  *
  * @return Una cadena que representa de manera detallada el objeto Pokemon.
  */
 @Override
    public String toString() {
        return "Pokemon{" +
                "id=" + id +
                ", num='" + num + '\'' +
                ", name='" + name + '\'' +
                ", img='" + img + '\'' +
                ", type=" + type +
                ", height='" + height + '\'' +
                ", weight='" + weight + '\'' +
                ", candy='" + candy + '\'' +
                ", candy_count=" + candy_count +
                ", egg='" + egg + '\'' +
                ", spawn_chance=" + spawn_chance +
                ", avg_spawns=" + avg_spawns +
                ", spawn_time='" + spawn_time + '\'' +
                ", multipliers=" + multipliers +
                ", weaknesses=" + weaknesses +
                ", next_evolution=" + next_evolution +
                ", prev_evolution=" + prev_evolution +
                '}';
    }
}
