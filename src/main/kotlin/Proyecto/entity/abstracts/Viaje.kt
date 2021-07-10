package Proyecto.entity.abstracts

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.math.abs

abstract class Viaje {

    protected var viajePedido = false
    protected var montoPagado = 0f
    protected var contadorTiempoDestino: Int = 0
    protected var contadorTiempoChofer: Int = 0


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

        if(viajePedido){
            println("""¡Ya pediste tu viaje!""".trimMargin())

        }
        this.montoPagado = calcularCostoViaje(coordenadaActual,coordenadaDestino)
        this.viajePedido = true

        println()
        println("""¡Viaje pedido exitosamente!""".trimMargin())


        return coorActualValidate && coorDestinoValidate
    }

    //Funcion que calcula el tiempo estimado al lugar del destino del usuario
    fun llegadaDestino(coordenadaActual: Int,coordenadaDestino: Int): Float{
        var diferenciaCoordenadas = coordenadaDestino - coordenadaActual
        var tiempoLlegada: Float = diferenciaCoordenadas.toFloat()
        val proximidad = diferenciaCoordenadas
        /*
          Se agrega un pequeño de delay para simular el tiempo en el que va a llegar el usuario a su destino
          Y si el destino esta muy lejos el tiempo de espera es mas largo
        */
        when (proximidad){
            0 -> {
                runBlocking {
                    delay(400L)
                    println("Ha llegado a su destino")
                }
            }
            in 1..100 ->{
                runBlocking {
                    for(i: Int in proximidad downTo 0){
                        tiempoLlegada-= contadorTiempoDestino
                        if (tiempoLlegada <= 0) {
                            println("Ha llegado a su destino")
                            break
                        } else {
                            println("Tardaras en llegar ${((tiempoLlegada)).toInt()} segundos")
                            contadorTiempoDestino ++
                        }

                        if (tiempoLlegada.toInt() == 0) {
                            println("Ha llegado a su destino")
                        }
                        delay(400L)
                    }
                }
            }

            in 100..200 ->{

                runBlocking {
                    for(i: Int in proximidad downTo -1){
                        tiempoLlegada-= contadorTiempoDestino
                        if ( tiempoLlegada < 0 ) {
                            break
                        } else {
                            println("Tardaras en llegar ${((tiempoLlegada)).toInt()} segundos")
                            contadorTiempoDestino ++
                        }

                        if (tiempoLlegada.toInt() == 0) {
                            println("Ha llegado a su destino")
                        }
                        delay(400L)
                    }
                }

            }

            in 200..250 -> {
                runBlocking {
                    for (i: Int in proximidad downTo -1){
                        tiempoLlegada-= contadorTiempoDestino
                        if (tiempoLlegada < 0) {
                            break
                        } else {
                            println("Tardaras en llegar ${((tiempoLlegada)).toInt()} segundos")
                            contadorTiempoDestino ++
                        }

                        if (tiempoLlegada.toInt() == 0) {
                            println("Ha llegado a su destino")
                        }
                        delay(400L)
                    }
                }

            }

            in 250..550 -> {
                runBlocking {
                    for (i: Int in proximidad downTo -1){
                        tiempoLlegada-= contadorTiempoDestino
                        if (tiempoLlegada < 0) {
                            break
                        } else {
                            println("Tardaras en llegar ${((tiempoLlegada)).toInt()} segundos")
                            contadorTiempoDestino ++
                        }
                        if (tiempoLlegada.toInt() == 0) {
                            println("Ha llegado a su destino")
                        }
                        delay(400L)
                    }
                }

            }

            in 550..1050 -> {
                runBlocking {
                    for (i: Int in proximidad downTo -1){
                        tiempoLlegada-= contadorTiempoDestino
                        if (tiempoLlegada < 0) {
                            break
                        } else {
                            println("Tardaras en llagar ${((tiempoLlegada)).toInt()} segundos")
                            contadorTiempoDestino ++
                        }

                        if (tiempoLlegada.toInt() == 0) {
                            println("Ha llegado a su destino")
                        }
                        delay(400L)
                    }
                }

            }

            in 1050..1500 -> {
                runBlocking {
                    for (i: Int in proximidad downTo -1){
                        tiempoLlegada-= contadorTiempoDestino
                        if (tiempoLlegada < 0) {
                            break
                        } else {
                            println("Tardaras en llagar ${((tiempoLlegada)).toInt()} segundos")
                            contadorTiempoDestino ++
                        }

                        if (tiempoLlegada.toInt() == 0) {
                            println("Ha llegado a su destino")
                        }
                        delay(400L)

                    }
                }

            }
            else -> {
                runBlocking {
                    delay(600L)
                    println("Hay mucho tráfico")
                }
                println("Llegara en unos cuantos minutos...")
                // Se implemento un sleep para desimular el tiempo que llega el usuario a su destino
                Thread.sleep(5000)
                println("Ha llegado a su destino")

            }
        }

        return proximidad.toFloat()
    }

    //Funcion para solicitar  un viaje
    fun solicitarViaje(coorChofer: Int, coordenadaActual: Int): Float{
        val diferenciaCoordenadas = abs(coorChofer- coordenadaActual)
        val proximidad = diferenciaCoordenadas
        var tiempoLlegada = diferenciaCoordenadas.toFloat()
        when (proximidad){
            0 -> {
                runBlocking {
                    delay(500L)
                    println("Su chofer ha llegado")
                }
            }
            in 1..100 ->{
                /*
                Se agrega un pequeño de delay para simular el tiempo en el que llega el conductor
                Y si el conductor esta muy lejos el tiempo de espera es mas largo
                 */
                runBlocking {
                    for(i: Int in proximidad downTo -1){
                        tiempoLlegada-= contadorTiempoChofer
                        if (tiempoLlegada <= 0) {
                            println("Su chofer ha llegado")
                            break
                        } else {
                            println("Su Chofer tardara en llegar ${((tiempoLlegada)).toInt()} segundos")
                            contadorTiempoChofer ++
                        }

                        if (tiempoLlegada.toInt() == 0) {
                            println("Su chofer ha llegado")
                        }
                        delay(500L)
                    }
                }
            }

            in 100..200 ->{
                runBlocking {
                    for(i: Int in proximidad downTo -1){
                        tiempoLlegada-= contadorTiempoChofer
                        if ( tiempoLlegada < 0 ) {
                            break
                        } else {
                            println("Su Chofer tardara en llegar ${((tiempoLlegada)).toInt()} segundos")
                            contadorTiempoChofer ++
                        }

                        if (tiempoLlegada.toInt() == 0) {
                            println("Su chofer ha llegado")
                        }
                    }
                    delay(500L)
                }

            }

            in 200..250 -> {
                runBlocking {
                    for (i: Int in proximidad downTo -1){
                        tiempoLlegada-= contadorTiempoChofer
                        if (tiempoLlegada < 0) {
                            break
                        } else {
                            println("Su Chofer tardara en llegar ${((tiempoLlegada)).toInt()} segundos")
                            contadorTiempoChofer ++
                        }

                        if (tiempoLlegada.toInt() == 0) {
                            println("Su chofer ha llegado")
                        }
                    }
                    delay(500L)
                }


            }

            in 250..350 -> {
                runBlocking {
                    for (i: Int in proximidad downTo -1){
                        tiempoLlegada-= contadorTiempoChofer
                        if (tiempoLlegada < 0) {
                            break
                        } else {
                            println("Su Chofer tardara en llegar ${((tiempoLlegada)).toInt()} segundos")
                            contadorTiempoChofer ++
                        }

                        if (tiempoLlegada.toInt() == 0) {
                            println("Su chofer ha llegado")
                        }
                    }
                    delay(500L)
                }


            }

            in 350..650 -> {
                runBlocking {
                    for (i: Int in proximidad downTo -1){
                        tiempoLlegada-= contadorTiempoChofer
                        if (tiempoLlegada < 0) {
                            break
                        } else {
                            println("Su Chofer tardara en llegar ${((tiempoLlegada)).toInt()} segundos")
                            contadorTiempoChofer ++
                        }

                        if (tiempoLlegada.toInt() == 0) {
                            println("Su chofer ha llegado")
                        }
                    }
                    delay(500L)
                }


            }

            else -> {
                runBlocking {
                    delay(1500L)
                    println("Su chofer esta muy lejos de su ubicacion")
                }
                Thread.sleep(1000L)
            }

        }
        return proximidad.toFloat()
    }
    //es protected para que sólo se pueda obtener el valor numérico desde la clase
    protected abstract fun calcularCostoViaje(coorActual: Int, coorDestino: Int): Float

    // Indica el estado del viaje si esta en espera o ya termino su viaje el usuario
    protected abstract fun estadoViaje(viajeObj: String)
}