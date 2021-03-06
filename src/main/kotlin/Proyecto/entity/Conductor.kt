package Proyecto.entity

class Conductor {private var nombre: String = ""
    private var coordenadasConductor: Int = 0
    private lateinit var automovil: Automovil
    private val calificaciones = listOf(5, 4, 3, 5, 5, 5, 5)

    constructor() {
    }

    constructor(nombre: String, coordenas: Int, automovil: Automovil) {
        this.nombre = nombre
        this.coordenadasConductor = coordenas
        this.automovil = automovil
        val promedio = (((calificaciones.sum() / calificaciones.size.toFloat()))*100).toInt()/100f
        println(
            """Los datos del conductor son:
            Nombre: ${this.nombre}
            Coordenadas: ${this.coordenadasConductor}
            Datos del Automovil:
            Marca: ${automovil.getMarca()}
            Modelo: ${automovil.getModelo()}
            Color: ${this.automovil.getColor()}
            Placas: ${this.automovil.getPlacas()}
            Calificacion: ${promedio}"""
        )

    }

    fun getNombre(): String {
        return nombre
    }

    fun setUsuario(nombre: String) {
        this.nombre = nombre
    }

    fun getCoordenadasConductor(): Int {
        return coordenadasConductor
    }

    fun setCoordenadasConductor(coordenas: Int) {
        this.coordenadasConductor = coordenas
    }

    // Funcion lambda para asignarle una calificacion al conductor
    val asignarCalificacion: (Int) -> String = { calif: Int ->

        when (calif) {
            1 -> "*"
            2 -> "* *"
            3 -> "* * *"
            4 -> "* * * *"
            5 -> "* * * * *"
            else -> {
                "Valor incorrecto"
            }
        }
    }

    //Funcion Lambda para asignarle un reconocimiento al conductor
    val asignarReconocimiento: (Int) -> String = { Recon: Int ->

        when (Recon) {
            1 -> "Auto limpio"
            2 -> "Buena conversacion"
            3 -> "Heroe de ultima hora"
            4 -> "Navegador experto"
            else -> {
                "Valor incorrecto"
            }
        }
    }

    data class Reconocimiento(
        val insignia: String,
        val Primeravez: String,
        val VecesObtenido: Double
    ) {
        var createdAt = ""

    }
}