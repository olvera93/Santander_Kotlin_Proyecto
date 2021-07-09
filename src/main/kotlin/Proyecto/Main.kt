package Proyecto


import Proyecto.entity.*
import Proyecto.interfaces.CallBack
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import java.text.SimpleDateFormat
import java.util.*



const val PRECIO_BASE = 2
const val PRECIO_POR_COR = .5f



private suspend fun fetchConductorCoroutine(username: String): Conductor =
    suspendCancellableCoroutine { cancellableContinuation ->
        fetchConductor(object : CallBack {
            override fun onSuccess(conductor: Conductor) {
                cancellableContinuation.resume(conductor)
            }

            override fun onFailure(exception: Exception) {
                cancellableContinuation.resumeWithException(exception)
            }
        })
    }

private fun fetchConductor(callback: CallBack) {
    Thread {
        Thread.sleep(3_000)

        callback.onSuccess(
            // Muestra los datos del conductor
            Conductor("Juan",100, Automovil("Nissan", "Azul", "Versa"))
        )
    }.start()
}

suspend fun main() {

    val usuario = Usuario()
    var salida = false
    var pago:String = ""
    var nombreUsuario: String = ""
    var passwordUsuario: String = ""
    val conductor = Conductor()

        //Menu Inicio de sesion, Registrar usuario y salir de la aplicacion
    do {
        println()
        println("Bienvenido a Carpool.")
        println("Por favor ingrese el numero correspondiente de opcion para utilizar nuestra aplicacion")
        println("1.- Registrarse")
        println("2.- Iniciar Sesion")
        println("3.- Salir de la aplicacion")
        var opcionMenu= readLine()?.toInt()
        when (opcionMenu){
            1 -> {
                println()
                println("Usted selecciono Registro")
                print("Ingresa tu nombre de usuario: ")
                var nombreUsuario = readLine().toString()
                print("Ingresa tu contraseña: ")
                var passwordUsuario = readLine().toString()
                do{
                    print("Ingresa tu forma de pago: Efectivo o Tarjeta: ")
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
                usuario.registroUsuario(usuario.getUsuario(),usuario.getPassword(),pago)

            }

            2 -> {
                println()
                println("Usted selecciono Iniciar Sesion")

                //login de usuario
                print("Ingresa tu nombre: ")
                nombreUsuario = readLine().toString()
                print("Ingresa tu contraseña: ")
                passwordUsuario = readLine().toString()



                if (usuario.loginUsuario(nombreUsuario, passwordUsuario)) {
                    if (!usuario.getUsuario().equals("") && !usuario.getPassword().equals("")) {
                        println()
                        /*
                        El usuario después de iniciar sesion tiene que ingresar sus coordenadas
                        Coordenadas actuales en donde está ubicado actualmente
                        Coordenadas destino a donde quiere ir
                         */
                        val now = Date()
                        val formatDate = SimpleDateFormat("E dd.MM.yyyy 'at' hh:mm:ss a ")
                        println("La fecha actual es: ${formatDate.format(now)}")
                        print("Ingrese sus coordenadas actuales: ")
                        var coordenadasActuales = readLine()!!.toInt()
                        print("Ingrese las coordenadas de su destino: ")
                        var coordenadaDestino = readLine()!!.toInt()
                        usuario.setCoorActuales(coordenadasActuales)
                        usuario.setCoorDestino(coordenadaDestino)

                        usuario.ingresarCoordenadas(coordenadasActuales, coordenadaDestino)

                        // Muestra los datos del conductor
                        println()
                        try {
                            println("Asignando conductor a su viaje...")
                            val conductor = fetchConductorCoroutine("Juan")
                        } catch (exception: Exception) {
                            println("Error: $exception")
                        }
                        println()

                        usuario.solicitarViaje(conductor.getCoordenadasConductor(), coordenadasActuales)

                        do {
                            println()
                            println("Desea cancelar su viaje")
                            println("1.- Si")
                            println("2.- No")
                            var opcionMenu2= readLine()?.toInt()
                            when(opcionMenu2){
                                1 -> {
                                    println()
                                    println("Se le descontara: $${usuario.obtenerPenalizacion(100)} pesos por cancelar el viaje: ")
                                    usuario.cancelarViaje()

                                }

                                2 -> {
                                    println()
                                    // Muesta el tiempo en el que el usuario podría llegar a su destino
                                    usuario.llegadaDestino(coordenadasActuales, coordenadaDestino)
                                    println()
                                    var montoF=usuario.calcularCostoViaje(coordenadasActuales, coordenadaDestino)
                                    println("El costo de su viaje es: ${usuario.calcularCostoViaje(coordenadasActuales, coordenadaDestino)}")
                                    var forma= FormaPago()
                                    println()

                                    // La condicion es por si el precio es mayor a 1000 al usuario le haran un descuento de un monto de $200
                                    if (usuario.calcularCostoViaje(coordenadasActuales, coordenadaDestino) > 500) {
                                        var descuento = usuario.obtenerDescuento(200)
                                        println("Obtienes un descuento de $${descuento}")
                                        println("El total que pagarías es de $${usuario.calcularCostoViaje(coordenadasActuales, coordenadaDestino) - descuento}")
                                        montoF=usuario.calcularCostoViaje(coordenadasActuales, coordenadaDestino)-descuento
                                        if (forma.Pago(montoF)){
                                            println("Viaje Pagado Exitosamente")
                                        }
                                        else{
                                            println("No se pudo realizar el pago")
                                        }
                                    }
                                    println()

                                    print("Ingrese la calificacion del chofer del 1 al 5: ")
                                    val calificacion = readLine()!!.toInt()
                                    println()
                                    println("La califación del conductor es de: ${conductor.asignarCalificacion(calificacion)}")
                                    Comentario.ingresarComentario()

                                    println()
                                    println("Ingrese un reconocimiento al chofer: 1 Auto limpio, 2 Buena conversacion, 3 Heroe 4 Experto")
                                    val reconocmiento = readLine()!!.toInt()
                                    println()
                                    println("El reconocimiento del conductor es: ${conductor.asignarReconocimiento(reconocmiento)}")
                                    println()
                                    val reconocimiento1 = Conductor.Reconocimiento("Navegador Experto","Alexa Lopez",20.0)
                                    println("El reconocimiento mas frecuente del conductor es:")
                                    println(reconocimiento1.insignia)
                                    reconocimiento1.createdAt = "Obtenido por primera vez en 2019"
                                    println(reconocimiento1.createdAt)
                                    println("El reconocimiento fue otorgado por primera vez por:")
                                    println(reconocimiento1.Primeravez)
                                    println("Veces que el conductor ha obtenido el reconocimiento:")
                                    println(reconocimiento1.VecesObtenido)
                                    println()
                                    usuario.estadoViaje("Viaje Terminado")
                                    break
                                }

                                else -> {
                                    println()
                                    println("OPCIÓN INVALIDA!!")
                                }
                            }

                        }while (opcionMenu2 != 1 && opcionMenu2 != 2)

                        break
                    } else
                        salida = false
                }
            }

            3 -> salida = true
            else ->{
                println()
                println("OPCIÓN INVALIDA!!")
            }
        }

    }while (!salida)
}