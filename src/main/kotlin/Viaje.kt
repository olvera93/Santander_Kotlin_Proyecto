package Proyecto

abstract class Viaje {

    protected var reserved = false
    protected var paidAmount = 0


    // Funcion para el ingresar las coordenadas del usuario
    fun ingresarCoordenadas(coordenadaActual: Int, coordenadaDestino: Int): Boolean {
        fun validate (input: Int): Boolean {
            if (input == null || input.equals("")){
                println("Error al ingresar coordenadas")
                return false
            }
            return true
        }

        if(reserved){
            println("""¡Ya pediste tu viaje!""".trimMargin())

        }
        val amount = getPrice(coordenadaDestino)
        reserved = true
        paidAmount = amount
        println("""¡Viaje pedido exitosamente!""".trimMargin())


        val coorActualValidate = validate(coordenadaActual)
        val coorDestinoValidate = validate(coordenadaDestino)


        return coorActualValidate && coorDestinoValidate
    }


    //es protected para que sólo se pueda obtener el valor numérico desde la clase
    protected abstract fun getPrice(numDays: Int): Int
}