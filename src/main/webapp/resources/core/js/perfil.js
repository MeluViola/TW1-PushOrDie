document.addEventListener('DOMContentLoaded', () => {
    const calculatorButton = document.querySelector('.calculator-button');
    const calculatorContainer = document.getElementById('calculator-container');
    const resultadosContainer = document.getElementById('resultados-container');
    const calcularBtn = document.getElementById('calcular-btn');
    const limpiarBtn = document.getElementById('limpiar-btn');
    const volverBtn = document.getElementById('volver-btn');

    const personasInput = document.getElementById('cantidadPersonas');
    const ingredientesList = document.getElementById('ingredientes-list');
    const totalCoctelesText = document.getElementById('total-cocteles');

    // Objeto receta con ingredientes por cóctel
    const receta = {
        Mojito: {
            ingredientes: {
                "Ron Blanco": { cantidad: 50, unidad: 'ml' },
                "Jugo de Lima": { cantidad: 25, unidad: 'ml' },
                "Azúcar": { cantidad: 10, unidad: 'g' },
                "Hojas de Menta": { cantidad: 5, unidad: 'hojas' },
                "Agua con Gas": { cantidad: 100, unidad: 'ml' }
            }
        },
        Margarita: {
            ingredientes: {
                "Tequila": { cantidad: 60, unidad: 'ml' },
                "Triple Sec": { cantidad: 30, unidad: 'ml' },
                "Jugo de Lima": { cantidad: 20, unidad: 'ml' }
            }
        },
        Daiquiri: {
            ingredientes: {
                "Ron Blanco": { cantidad: 45, unidad: 'ml' },
                "Jugo de Lima": { cantidad: 25, unidad: 'ml' },
                "Azúcar": { cantidad: 10, unidad: 'g' }
            }
        },
        TomCollins: {
            ingredientes: {
                "Ginebra": { cantidad: 60, unidad: 'ml' },
                "Jugo de Limón": { cantidad: 30, unidad: 'ml' },
                "Jarabe de Azúcar": { cantidad: 15, unidad: 'ml' },
                "Agua con Gas": { cantidad: 100, unidad: 'ml' }
            }
        },
        Gancia: {
            ingredientes: {
                "Gancia": { cantidad: 100, unidad: 'ml' }
            }
        },
        Fernet: {
            ingredientes: {
                "Fernet": { cantidad: 50, unidad: 'ml' }
            }
        },
        GinFizz: {
            ingredientes: {
                "Ginebra": { cantidad: 50, unidad: 'ml' },
                "Jugo de Limón": { cantidad: 25, unidad: 'ml' },
                "Agua con Gas": { cantidad: 100, unidad: 'ml' }
            }
        },
        Destornillador: {
            ingredientes: {
                "Vodka": { cantidad: 50, unidad: 'ml' },
                "Jugo de Naranja": { cantidad: 100, unidad: 'ml' }
            }
        },
        Garibaldi: {
            ingredientes: {
                "Campari": { cantidad: 45, unidad: 'ml' },
                "Jugo de Naranja": { cantidad: 90, unidad: 'ml' }
            }
        },
        ElectricDemon: {
            ingredientes: {
                "Vodka": { cantidad: 60, unidad: 'ml' },
                "Limonada": { cantidad: 120, unidad: 'ml' }
            }
        },
        BlackRussian: {
            ingredientes: {
                "Vodka": { cantidad: 50, unidad: 'ml' },
                "Licor de Café": { cantidad: 20, unidad: 'ml' }
            }
        },
        CaféIrlandes: {
            ingredientes: {
                "Whiskey": { cantidad: 40, unidad: 'ml' },
                "Café": { cantidad: 150, unidad: 'ml' },
                "Azúcar": { cantidad: 15, unidad: 'g' },
                "Crema": { cantidad: 30, unidad: 'ml' }
            }
        },
        Caipirinha: {
            ingredientes: {
                "Cachaça": { cantidad: 50, unidad: 'ml' },
                "Azúcar": { cantidad: 15, unidad: 'g' },
                "Lima": { cantidad: 20, unidad: 'g' } // Lima como gramos, ajusta si es necesario
            }
        },
        Caipiroska: {
            ingredientes: {
                "Vodka": { cantidad: 50, unidad: 'ml' },
                "Azúcar": { cantidad: 15, unidad: 'g' },
                "Lima": { cantidad: 20, unidad: 'g' }
            }
        },
        Caipirissima: {
            ingredientes: {
                "Ron": { cantidad: 50, unidad: 'ml' },
                "Azúcar": { cantidad: 15, unidad: 'g' },
                "Lima": { cantidad: 20, unidad: 'g' }
            }
        },
        Negroni: {
            ingredientes: {
                "Gin": { cantidad: 30, unidad: 'ml' },
                "Vermouth Rosso": { cantidad: 30, unidad: 'ml' },
                "Campari": { cantidad: 30, unidad: 'ml' }
            }
        }
    };

    // Función para alternar la visibilidad de la calculadora
    function toggleCalculator() {
        calculatorContainer.classList.toggle('hidden');
    }

    // Evento para mostrar/ocultar la calculadora
    calculatorButton.addEventListener('click', toggleCalculator);

    // Función para limpiar los campos de la calculadora
    limpiarBtn.addEventListener('click', () => {
        personasInput.value = ''; // Limpia el campo de personas
        const inputs = document.querySelectorAll('.coctel-selection input[type="number"]');

        // Limpia los campos de los cócteles
        inputs.forEach(input => input.value = '');

        // Limpia el campo de cócteles por persona
        const coctelesPorPersonaInput = document.querySelector('#coctelesPorPersona');
        if (coctelesPorPersonaInput) {
            coctelesPorPersonaInput.value = 'Ingresar Cócteles por Persona'; // Limpia el campo de cócteles por persona
        }

        // Limpia la lista de ingredientes y resultados
        ingredientesList.innerHTML = '';
        totalCoctelesText.textContent = '';
    });


    // Función para convertir ml a botellas
    function convertirABotellas(cantidad, unidad) {
        const conversion = 750; // mililitros por botella
        if (unidad === 'ml') {
            const botellas = Math.max(1, Math.floor(cantidad / conversion)); // Asegura mínimo 1 botella
            const sobrante = cantidad % conversion;
            return `${cantidad} ml (${botellas} ${botellas === 1 ? 'botella' : 'botellas'}${sobrante > 0 ? ` y ${sobrante} ml` : ''})`;
        }
        return `${cantidad} ${unidad}`; // Para otros tipos de unidades, no hacemos conversión
    }

// Función para calcular los resultados
    calcularBtn.addEventListener('click', () => {
        const personas = parseInt(personasInput.value);
        if (!personas) {
            totalCoctelesText.textContent = 'Por favor, ingresa el número de personas.';
            return;
        }

        // Obtenemos los ingredientes totales
        const ingredientesTotales = {};
        const inputs = document.querySelectorAll('.coctel-selection input[type="number"]');
        let totalCocteles = 0; // Total de cócteles

        inputs.forEach(input => {
            const coctel = input.name;
            const cantidad = parseInt(input.value);

            if (cantidad > 0 && receta[coctel.charAt(0).toUpperCase() + coctel.slice(1)]) {
                const ingredientes = receta[coctel.charAt(0).toUpperCase() + coctel.slice(1)].ingredientes;

                for (const [ingrediente, { cantidad: cantidadPorCoctel, unidad }] of Object.entries(ingredientes)) {
                    const cantidadTotal = cantidadPorCoctel * cantidad * personas;

                    if (ingredientesTotales[ingrediente]) {
                        ingredientesTotales[ingrediente].cantidad += cantidadTotal;
                    } else {
                        ingredientesTotales[ingrediente] = { cantidad: cantidadTotal, unidad };
                    }
                }
                totalCocteles += cantidad * personas; // Acumula el total de cócteles
            }
        });

        // Mostrar los ingredientes totales
        ingredientesList.innerHTML = '';
        if (Object.keys(ingredientesTotales).length === 0) {
            ingredientesList.innerHTML = '<li>No seleccionaste ningún cóctel.</li>';
        } else {
            for (const [ingrediente, { cantidad, unidad }] of Object.entries(ingredientesTotales)) {
                const li = document.createElement('li');
                li.textContent = `${ingrediente}: ${convertirABotellas(cantidad, unidad)}`;
                ingredientesList.appendChild(li);
            }
        }

        const minimoTragos = personas * 3; // Mínimo 3 tragos por persona
        if (totalCocteles < minimoTragos) {
            const advertencia = confirm(`Se han calculado ${totalCocteles} tragos, que es menos que el mínimo de ${minimoTragos} tragos (3 por persona). ¿Quieres continuar?`);
            if (!advertencia) return; // Si no se confirma, salir
        }

        // Cambiar a la vista de resultados
        calculatorContainer.classList.add('hidden');
        resultadosContainer.classList.remove('hidden');
    });

// Función para volver a la calculadora desde la vista de resultados
    volverBtn.addEventListener('click', () => {
        resultadosContainer.classList.add('hidden');
        calculatorContainer.classList.remove('hidden');
    });
});
