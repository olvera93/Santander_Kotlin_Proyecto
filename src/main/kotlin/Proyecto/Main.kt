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
    var pago: String
    var nombreUsuario: String
    var passwordUsuario : String
    val conductor = Conductor()

        //Menu Inicio de sesion, Registrar usuario y salir de la aplicacion
    do {
        //No hay errores, si es que no se ingresa un Int, se marca como opción inválida en el menu.
        val opcionMenu = validateInput("Int", "Bienvenido a Carpool.\n" +
                "Por favor ingrese el numero correspondiente de opcion para utilizar nuestra aplicacion\n" +
                "1.- Registrarse\n" +
                "2.- Iniciar Sesion\n" +
                "3.- Salir de la aplicacion")
        when (opcionMenu){
            1 -> {
                println()
                println("Usted selecciono Registro")
                do{
                    print("Ingresa tu nombre de usuario: ")
                    nombreUsuario = readLine() ?: ""
                    print("Ingresa tu contraseña: ")
                    passwordUsuario = readLine() ?: ""
                    if(nombreUsuario =="" || passwordUsuario==""){
                        println("Lamentablemente no somos adivinos :( favor de llenar los campos\n")
                    }
                }while(nombreUsuario=="" || passwordUsuario=="")
                do{
                    print("Ingresa tu forma de pago: Efectivo o Tarjeta: ")
                    pago = readLine() ?: ""
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
                if(nombreUsuario!="" && passwordUsuario !="") {
                    usuario.setUsuario(nombreUsuario)
                    usuario.setPassword(passwordUsuario)
                    usuario.registroUsuario(usuario.getUsuario(), usuario.getPassword(), pago)
                }
            }

            2 -> {
                println()
                println("Usted selecciono Iniciar Sesion")

                //login de usuario
                print("Ingresa tu nombre: ")
                nombreUsuario = readLine() ?: ""
                print("Ingresa tu contraseña: ")
                passwordUsuario = readLine() ?: ""

                if (usuario.loginUsuario(nombreUsuario, passwordUsuario)) {
                    if (usuario.getUsuario() != "" && usuario.getPassword() != "") {
                        println()
                        /*
                        El usuario después de iniciar sesion tiene que ingresar sus coordenadas
                        Coordenadas actuales en donde está ubicado actualmente
                        Coordenadas destino a donde quiere ir
                         */
                        val now = Date()
                        val formatDate = SimpleDateFormat("E dd.MM.yyyy 'at' hh:mm:ss a ")
                        println("La fecha actual es: ${formatDate.format(now)}")
                        val coordenadasActuales = validateInput("Int", "Ingrese sus coordenadas actuales: ") as Int
                        val coordenadaDestino = validateInput("Int", "Ingrese las coordenadas de su destino: ") as Int

                        usuario.setCoorActuales(coordenadasActuales)
                        usuario.setCoorDestino(coordenadaDestino )

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

                        println()
                        val opcionMenu2= validateInput("Int", "¿Desea cancelar su viaje?\n" +
                                "1.- Si\n" +
                                "2.- No")
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
                                var montoF = usuario.calcularCostoViaje(coordenadasActuales, coordenadaDestino)
                                println("El costo de su viaje es: ${usuario.calcularCostoViaje(coordenadasActuales, coordenadaDestino)}")
                                val forma= FormaPago()
                                println()

                                // La condicion es por si el precio es mayor a 1000 al usuario le haran un descuento de un monto de $200
                                if (usuario.calcularCostoViaje(coordenadasActuales, coordenadaDestino) > 500) {
                                    val descuento = usuario.obtenerDescuento(200)
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
                                var salidaCalificacion = true
                                do{
                                    var calificacion = validateInput("Int", "Ingrese la calificacion del chofer del 1 al 5: ") as Int
                                    print(calificacion)
                                    if(calificacion !in 1..5){
                                        println("Por favor selecciona una calificación en el rango :D" )
                                    }else{
                                        salidaCalificacion=false
                                        println()
                                        println("La califación del conductor es de: ${conductor.asignarCalificacion(calificacion)}")
                                        Comentario.ingresarComentario()
                                    }
                                }while(salidaCalificacion)

                                println()
                                var salidaReconocimiento = true
                                do{
                                    var reconocimiento = validateInput("Int", "Ingrese un reconocimiento al chofer: 1 = Auto limpio, 2 = Buena conversacion, 3 = Heroe 4 = Experto") as Int
                                    if(reconocimiento !in 1..4){
                                        println("Por favor selecciona una opción de las disponibles :)")
                                    }else{
                                        salidaReconocimiento=false
                                        println()
                                        println("El reconocimiento del conductor es: ${conductor.asignarReconocimiento(reconocimiento)}")
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
                                    }
                                }while(salidaReconocimiento)

                                break
                            }

                            else -> {
                                println()
                                println("OPCIÓN INVALIDA!!")
                            }
                        }
                        break
                    } else
                        salida = false
                }
            }

            3 -> {
                salida = true
                println("Gracias por utilizar nuestra aplicación, que tenga buen día :D")
            }
            else ->{
                println()
                println("OPCIÓN INVALIDA!!")
            }
        }
    }while (!salida)
}

fun validateInput(expected: String, inputMessage:String):Any{
    do{
        println(inputMessage)
        val value = readLine() ?: ""
        var esperado = "otra cosa"
        try {
            when(expected) {
                "String" -> {
                    esperado = "una cadena de texto"
                    return value
                }
                "Int" -> {
                    esperado = "un número entero"
                    return value.toInt()
                }
                "Float" -> {
                    esperado = "un número con decimales"
                    return value.toFloat()
                }
                else->{
                    println("Falta declarar tipo a esperar")
                }
            }
        }catch(e:Exception) {
            println("No seas así :( se esperaba que pusieras $esperado")
        }
    }while(true)
}

