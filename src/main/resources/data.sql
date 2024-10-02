INSERT INTO Usuario(email, password, rol, activo, puntos, nivel)
VALUES ('test@unlam.edu.ar', 'test', 'ADMIN', true, 0, 1);
-- Insertar categorías
INSERT INTO Categoria(nombre_categoria)
VALUES ('Clasicos'), ('Tropicales'), ('Sin alcohol'), ('Amargos'), ('Dulces');

-- Insertar recetas
INSERT INTO Receta(nombre, puntaje, instrucciones, categoria_id_categoria)
VALUES
    ('Mojito', 5, 'Mezclar menta, azúcar, jugo de limón, ron blanco y soda.', 1),
    ('Margarita', 4, 'Mezclar tequila, triple sec y jugo de lima.', 2);


-- Insertar ingredientes
INSERT INTO Ingrediente(nombre_ingrediente)
VALUES
    ('Ron Blanco'),
    ('Tequila'),
    ('Menta'),
    ('Lima');

-- Insertar relaciones entre recetas e ingredientes
INSERT INTO RecetaIngrediente(receta_id_receta, ingrediente_id_ingrediente)
VALUES
(1, 1), -- Mojito con Ron Blanco
(2, 2); -- Margarita con Tequila