/**Esta sentencia SQL crea una tabla llamada "Pokemon" para almacenar información sobre Pokémon,
  *La tabla tiene las siguientes columnas:
  *id: Identificador único de Pokémon.
  *num: Número de Pokémon.
  *name: Nombre del Pokémon.
  *height: Altura del Pokémon.
  *weight: Peso del Pokémon.
  */
DROP TABLE IF EXISTS Pokemon;
CREATE TABLE IF NOT EXISTS Pokemon
(
    id     INTEGER PRIMARY KEY,
    num    TEXT,
    name   TEXT,
    height  TEXT,
    weight TEXT
);