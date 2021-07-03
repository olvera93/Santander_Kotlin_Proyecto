package Proyecto

class Conductor {
    private var nombre: String = ""
    private var coordenadasConductor: Int = 0
    private var calificacion = 4.5f
    private lateinit var automovil: Automovil

    constructor(nombre:String, coordenas: Int, automovil: Automovil) {
        this.nombre = nombre
        this.coordenadasConductor = coordenas
        this.automovil = automovil

        println("""Los datos del conductor son:
            Nombre: ${this.nombre}
            Coordenadas: ${this.coordenadasConductor}
            Datos del Automovil:
            Marca: ${automovil.getMarca()}
            Modelo: ${automovil.getModelo()}
            Color: ${this.automovil.getColor()}
            Placas: ${this.automovil.getPlacas()}""")
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

    // Funcion lambda que
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

}