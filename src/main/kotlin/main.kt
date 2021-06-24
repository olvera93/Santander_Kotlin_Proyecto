
package Proyecto
import java.util.*
import kotlin.system.exitProcess
const val PRECIO_BASE = 30
const val PRECIO_POR_COR : Int = 5
fun main() {

    val usuario = Usuario()

    var Salidado = false
    var pago:String = ""

    //Menu Inicio de sesion, Registrar usuario y salir de la aplicacion
    do {
        println()
        println("Bienvenido a Carpool.")
        println("Por favor ingrese una opcion para utilizar nuestra aplicacion")
        println("1.- Registrarse")
        println("2.- Iniciar Sesion")
        println("3.- Salir de la aplicacion")
        var opcionMenu= readLine()?.toInt()
        when (opcionMenu){
            1 -> {
                println()
                println("Usted selecciono Registro")
                print("Ingresa tu nombre: ")
                val nombreUsuario = readLine().toString()
                print("Ingresa tu contraseña: ")
                val passwordUsuario = readLine().toString()
                do{
                    println("Ingresa tu forma de pago: Efectivo o Tarjeta: ")
                    pago = readLine()!!.toString()
                    when(pago) {
                        "Efectivo" -> {
                            println("Agregado Exitosamente el pago con efectivo")
                        break
                    }
                        "Tarjeta" -> {
                            println("Agregado Exitosamente el pago con tarjeta")
                        break
                        }

                        else -> {
                            println()
                            println("METODO DE PAGO INCORRECTO")
                        }
                    }
                } while (pago.isEmpty() || pago != "Efectivo" || pago != "Tarjeta")
                usuario.setUsuario(nombreUsuario)
                usuario.setPassword(passwordUsuario)
                usuario.RegistroUsuario(usuario.getUsuario(),usuario.getPassword(),pago)

            }

            2 -> {
                println()
                println("Usted selecciono Iniciar Sesion")
                //login de usuario
                print("Ingresa tu nombre: ")
                val nombreUsuario = readLine().toString()
                print("Ingresa tu contraseña: ")
                val passwordUsuario = readLine().toString()

                if (usuario.loginUsuario(usuario.getUsuario(), usuario.getPassword())) {
                    if (!usuario.getUsuario().equals("") && !usuario.getPassword().equals("")) {
                        println()
                        /*
                        El usuario después de iniciar sesion tiene que ingresar sus coordenadas
                        Coordenadas actuales en donde está ubicado actualmente
                        Coordenadas destino a donde quiere ir
                         */
                        print("Ingrese sus coordenadas actuales: ")
                        var coordenadasActuales = readLine()!!.toInt()
                        print("Ingrese las coordenadas de su destino: ")
                        var coordenadaDestino = readLine()!!.toInt()
                        usuario.setCoorActuales(coordenadasActuales)
                        usuario.setCoorDestino(coordenadaDestino)
                        usuario.ingresarCoordenadas(usuario.getCoorActuales(), usuario.getCoorDestino())

                        println()
                        // Muestra al chofer
                        val nombre = "Paco"
                        val coordenadasChofer = 100
                        println("El nombre de su chofer es: $nombre")
                        println("se encuentra en la coordenada: $coordenadasChofer")
                        println()
                        /*
                        //Escribe el código a ejecutar
                        println("El costo de su viaje es: ${calcularCostoViaje(coordenadasActuales, coordenadaDestino)}")
                        println("El tiempo estimado de su viaje es: ${calcularCostoViaje(coordenadasActuales, coordenadaDestino) * 2}")
                        tiempoLlegadaChofer(coordenadasChofer, coordenadasActuales)

                         */
                        break


                    } else
                        Salidado = false
                }
            }

                3 -> Salidado=true
                else ->{
                    println()
                    println("OPCIÓN INVALIDA!!")
                }
            }

        }while (!Salidado)
// Impresion
//println("$nombreCliente verifica las placas y la foto del conductor")
    }

    //Funcion para calcular el costo del viaje
    fun calcularCostoViaje(coorActual: Int, coorDestino: Int): Float {
        var distancia: Int = (coorDestino - coorActual)
        var precioViaje: Float = (PRECIO_POR_COR * distancia.toFloat()) + PRECIO_BASE
        return precioViaje
    }
    fun tiempoLlegadaChofer(coorChofer: Int, coorUsuario:Int): Float {
        var diferenciaCoordenadas = coorChofer - coorUsuario
        var tiempoLlegada: Float = diferenciaCoordenadas.toFloat()
        val proximidad = diferenciaCoordenadas
        when (proximidad){
            0 -> println("Su chofer ha llegado")
            in 1..100 -> println("Su Chofer tardara en llegar ${(tiempoLlegada)*2} segundos")
            in 100..200 -> println("Su Chofer tardara en llegar ${(tiempoLlegada)*5.5} segundos")
            in 200..250 -> println("Su Chofer tardara en llegar ${(tiempoLlegada)*8.5} segundos")
            else -> {
                println("Su chofer esta muy lejos de su ubicacion")
            }
        }
        return proximidad.toFloat()
    }
