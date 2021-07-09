package s4p2
open class National(override val city:String): Travel(){
    override val country = "Mexico"

    //este map es sun catálogo los diferentes precios por día dependiendo de la ciudad
    protected val fees = mapOf(
        "Monterrey" to 400,
        "Guadalajara" to 350,
        "CDMX" to 360,
        "San Cristóbal de las casas" to 240,
        "San Miguel de Allende" to 320
    )

    override fun getPrice(numDays: Int): Int {
        val fee = fees[city] //obtenemos la tarifa según la ciudad
        return if (fee==null) 0 else fee*numDays //regresamos 0 si no hay tarifa, si sí, hacemos el cálculo
    }

}