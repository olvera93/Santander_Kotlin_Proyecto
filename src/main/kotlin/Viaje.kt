package Proyecto

abstract class Viaje {

    protected var reserved = false
    protected var paidAmount = 0f
    protected var contador: Int = 0



    // Funcion para el ingresar las coordenadas del usuario
    fun ingresarCoordenadas(coordenadaActual: Int, coordenadaDestino: Int): Boolean {
        fun validate (input: Int): Boolean {
            if (input == null || input.equals("")){
                println("Error al ingresar coordenadas")
                return false
            }
            return true
        }

        val coorActualValidate = validate(coordenadaActual)
        val coorDestinoValidate = validate(coordenadaDestino)

        if(reserved){
            println("""¡Ya pediste tu viaje!""".trimMargin())

        }
        val amount = calcularCostoViaje(coordenadaActual,coordenadaDestino)
        reserved = true
        paidAmount = amount
        println("""¡Viaje pedido exitosamente!""".trimMargin())


        return coorActualValidate && coorDestinoValidate
    }

    //Funcion para solicitar  viaje
    fun llegadaDestino(coordenadaActual: Int,coordenadaDestino: Int): Float{
        var diferenciaCoordenadas = coordenadaDestino - coordenadaActual
        var tiempoLlegada: Float = diferenciaCoordenadas.toFloat()
        val proximidad = diferenciaCoordenadas
        when (proximidad){
            0 -> println("Ha llegado a su destino")
            in 1..100 ->{
                for(i: Int in proximidad downTo 0){
                    tiempoLlegada-= contador
                    if (tiempoLlegada <= 0) {
                        break
                    } else {
                        println("Tardaras en llagar ${((tiempoLlegada)).toInt()} segundos")
                        contador ++
                    }

                    if (tiempoLlegada.toInt() == 0) {
                        println("Ha llegado a su destino")
                    }
                }

            }

            in 100..200 ->{
                for(i: Int in proximidad downTo -1){
                    tiempoLlegada-= contador
                    if ( tiempoLlegada < 0 ) {
                        break
                    } else {
                        println("Tardaras en llagar ${((tiempoLlegada)).toInt()} segundos")
                        contador ++
                    }

                    if (tiempoLlegada.toInt() == 0) {
                        println("Ha llegado a su destino")
                    }
                }
            }

            in 200..250 -> {
                for (i: Int in proximidad downTo -1){
                    tiempoLlegada-= contador
                    if (tiempoLlegada < 0) {
                        break
                    } else {
                        println("Tardaras en llagar ${((tiempoLlegada)).toInt()} segundos")
                        contador ++
                    }

                    if (tiempoLlegada.toInt() == 0) {
                        println("Ha llegado a su destino")
                    }
                }
            }

            in 250..550 -> {
                for (i: Int in proximidad downTo -1){
                    tiempoLlegada-= contador
                    if (tiempoLlegada < 0) {
                        break
                    } else {
                        println("Tardaras en llagar ${((tiempoLlegada)).toInt()} segundos")
                        contador ++
                    }
                    if (tiempoLlegada.toInt() == 0) {
                        println("Ha llegado a su destino")
                    }
                }
            }

            in 550..1050 -> {
                for (i: Int in proximidad downTo -1){
                    tiempoLlegada-= contador
                    if (tiempoLlegada < 0) {
                        break
                    } else {
                        println("Tardaras en llagar ${((tiempoLlegada)).toInt()} segundos")
                        contador ++
                    }

                    if (tiempoLlegada.toInt() == 0) {
                        println("Ha llegado a su destino")
                    }
                }
            }

            in 1050..1500 -> {
                for (i: Int in proximidad downTo -1){
                    tiempoLlegada-= contador
                    if (tiempoLlegada < 0) {
                        break
                    } else {
                        println("Tardaras en llagar ${((tiempoLlegada)).toInt()} segundos")
                        contador ++
                    }

                    if (tiempoLlegada.toInt() == 0) {
                        println("Ha llegado a su destino")
                    }
                }
            }
            else -> {
                println("Hay mucho tráfico")
            }
        }

        return proximidad.toFloat()
    }



    //es protected para que sólo se pueda obtener el valor numérico desde la clase
    protected abstract fun calcularCostoViaje(coorActual: Int, coorDestino: Int): Float

    // Indica el estado del viaje si esta en espera o ya termino su viaje el usuario
    protected abstract fun estadoViaje(viajeObj: String)
}