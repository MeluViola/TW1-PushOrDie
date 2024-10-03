function replaceSpecialChars(str) {
    return str
        .replace(/á/g, '&aacute;')
        .replace(/é/g, '&eacute;')
        .replace(/í/g, '&iacute;')
        .replace(/ó/g, '&oacute;')
        .replace(/ú/g, '&uacute;')
        .replace(/ñ/g, '&ntilde;')
        .replace(/Á/g, '&Aacute;')
        .replace(/É/g, '&Eacute;')
        .replace(/Í/g, '&Iacute;')
        .replace(/Ó/g, '&Oacute;')
        .replace(/Ú/g, '&Uacute;')
        .replace(/Ñ/g, '&Ntilde;');
}

const cocktails = [
    {
        title: "RUSO NEGRO",
        image: "imgStatic/rusoNegro.jpeg",
        ingredients: "- 2 oz (60 ml) vodka\n - 1 oz (30 ml) de licor de café o Kahlua\n - Hielo\n *Opcional: coca-cola para rellenar\n",
        details: "Preparación:\n1. Pon los cubos de hielo en un vaso de tamaño medio, idealmente un vaso de whiskey\n2. A continuación sirve el vodka\n3. Agrega el licor de café\n4. (Rellena con coca-cola)* opcional"
    },
    {
        title: "TINTO DE VERANO",
        image: "imgStatic/TintoVerano.jpg",
        ingredients: "- 4 oz (120 ml) de vino tinto\n - 4 oz (120 ml) de Sprite\n - Hielo\n - 3 rodajas de limón\n",
        details: "Preparación:\n1. Agrega el hielo a un vaso ancho, y posteriormente añade el vino y la Sprite. \n2. Revuelve un poco para unir todos los ingredientes.\n3. Decora con unas rodajas de limón."
    },
    {
        title: "GIN TONIC DE FRUTILLA",
        image: "imgStatic/ginTonicFrutilla.jpg",
        ingredients: "- 1.5 oz (45 ml) gin\n - 4 a 5 oz (120 ml a 150 ml) agua tónica\n - 3 frutillas cortadas en rodajas\n - Hielo\n - Una rodaja de lima o limón\n",
        details: "Preparación:\n 1. En un vaso ancho, agregar los hielos, las frutillas en rodajas y luego el gin escogido. \n 2. Agregar la tónica con la ayuda de una cuchara con forma de varilla.\n 3. Decora con una rodaja de lima o limón."
    },
    {
        title: "CUBA LIBRE",
        image: "imgStatic/CubaLibre.jpg",
        ingredients: "- 1.5 oz (45 ml) ron\n - 3 oz (90 ml) coca-cola\n - Algunas gotas de jugo de lima\n - Hielo\n - Media rodaja de lima",
        details: "Preparación:\n 1. En un vaso ancho, agregar los hielos, las frutillas en rodajas y luego el gin escogido.\n 2. Agregar la tónica con la ayuda de una cuchara con forma de varilla.\n 3. Decora con una rodaja de lima o limón."
    },
    {
        title: "NEGRONI",
        image: "imgStatic/negroni2.jpg",
        ingredients: "- 1 oz (30 ml) gin\n - 1 oz (30 ml) vermut rojo dulce\n - 1 oz (30 ml) Campari\n - Hielo\n - Una rodaja de naranja",
        details: "Preparación:\n 1. Llena un vaso con hielo.\n 2. Vierte el gin, el vermut y el Campari.\n 3.  Revuelve suavemente hasta que los ingredientes se mezclen.\n 4. Decora con una rodaja de naranja.\n"
    },
    {
        title: "TOM COLLINS",
        image: "imgStatic/tomCollins.jpg",
        ingredients: "- 2 oz (60 ml) gin\n - 1 oz (30 ml) jugo de limón fresco\n - 0.5 oz (15 ml) jarabe de azúcar\n -4 oz (120 ml) soda\n - Hielo\n - Una rodaja de limón\n - Una cereza",
        details: "Preparación:\n 1. Llena un vaso alto con hielo.\n 2. Agrega el gin, jugo de limón y jarabe de azúcar.\n 3.  Revuelve bien y añade la soda.\n 4. Decora con una rodaja de limón y una cereza.\n"
    },
    {
        title: "APEROL SPRITZ",
        image: "imgStatic/aperolSpritz.jpg",
        ingredients: "- 3 oz (90 ml) Prosecco\n - 2 oz (60 ml) Aperol\n - 1 oz (30 ml) agua con gas\n - Hielo\n - Media rodaja de naranja\n",
        details: "Preparación:\n 1. Llena una copa grande con hielo.\n 2. Añade el Prosecco, Aperol y agua con gas.\n 3.  Revuelve ligeramente.\n - 4. Decora con media rodaja de naranja."
    },
    {
        title: "SEX ON TEH BEACH",
        image: "imgStatic/sexOnTheBeach.jpg",
        ingredients: "- 1.5 oz (45 ml) vodka\n - 0.5 oz (15 ml) licor de durazno\n - 1.5 oz (45 ml) jugo de naranja\n - Hielo\n - 1.5 oz (45 ml) jugo de arándano\n - Una rodaja de naranja\n",
        details: "Preparación:\n 1. Llena un vaso alto con hielo.\n 2. Vierte el vodka, licor de durazno, jugo de naranja y jugo de arándano.\n 3.  Revuelve bien y decora con una rodaja de naranja.\n"
    },
    {
        title: "CAFE IRLANDES",
        image: "imgStatic/cafeIrlandes.jpg",
        ingredients: "- 1.5 oz (45 ml) whiskey irlandés\n - 1 cucharadita de azúcar\n - 4 oz (120 ml) café caliente\n - 1 oz (30 ml) crema batida\n",
        details: "Preparación:\n 1. En una taza, añade el azúcar y el whiskey.\n 2. Vierte el café caliente y mezcla hasta que el azúcar se disuelva.\n 3.  Coloca la crema batida sobre la bebida y sirve.\n"
    },
];

function openPopup(index) {
    const popup = document.getElementById("popup");
    const title = document.getElementById("popup-title");
    const img = document.getElementById("popup-img");
    const ingredients = document.getElementById("popup-ingredients");
    const details = document.getElementById("popup-details");

    title.textContent = cocktails[index].title;
    img.src = cocktails[index].image;
    ingredients.innerHTML = "Ingredientes: <br>" + replaceSpecialChars(cocktails[index].ingredients.split("\n").join("<br>"));
    details.innerHTML = replaceSpecialChars(cocktails[index].details.split("\n").join("<br>"));

    popup.style.display = "flex";
}


function closePopup() {
    const popup = document.getElementById("popup");
    popup.style.display = "none";
}
