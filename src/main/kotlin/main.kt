
package Proyecto

import java.util.*
import kotlin.system.exitProcess

const val PRECIO_BASE = 30
const val PRECIO_POR_COR : Int = 5

fun main() {
    val usuario: MutableMap <String, String> = mutableMapOf(
        "Stuart" to "Stuart123",
        "Marco" to "Marco123",
        "Gonzalo" to "Gonzalo")
    var Salidado = false
    //Menu Inicio de sesion, Registrar usuario y salir de la aplicacion
    do {
        println()
        println("Bienvenido a Carpool.")
        println("Por favor ingrese una opcion para utilizar nuestra aplicacion")
        println("1.- Iniciar Sesion")
        println("2.- Registrarse")
        println("3.- Salir de la aplicacion")
        var opcionMenu= readLine()?.toInt()
        when (opcionMenu){
            1 -> {
                println()
                println("Usted selecciono Iniciar Sesion")
                //login de usuario
                print("Ingresa tu nombre: ")
                val nombreUsuario = readLine().toString()
                print("Ingresa tu contraseña: ")
                val passwordUsuario = readLine().toString()

                var Aut = LoginUsuario(nombreUsuario,passwordUsuario,usuario)
                if (Aut){

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

                    val coordenas = ingresarCoordenadas(coordenadasActuales, coordenadaDestino)
                    println("Usuario metio bien sus coordenadas? $coordenas")

                    /*
                    El usuario después de poner sus coordenadas se le pedirá un método de pago
                    Los predeterminados son: Efectivo y tarjeta
                    */
                    do {
                        println()
                        print("Ingrese su método de pago: ")
                        val pago = readLine()!!.toString()
                        val payment = metodoDePago(pago)

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
                                println("METODO DE PAGO INCORRECTO")
                            }
                        }
                    } while (pago.isEmpty() || pago != "Efectivo" || pago != "Tarjeta")
                    println()

                    // Muestra al chofer
                    val nombre = "Paco"
                    val coordenadasChofer = 100

                    println("El nombre de su chofer es: $nombre")
                    println("se encuentra en la coordenada: $coordenadasChofer")
                    println()


                    //Escribe el código a ejecutar
                    println("El costo de su viaje es: ${calcularCostoViaje(coordenadasActuales, coordenadaDestino)}")
                    println("El tiempo estimado de su viaje es: ${calcularCostoViaje(coordenadasActuales, coordenadaDestino) * 2}")
                    tiempoLlegadaChofer(coordenadasChofer, coordenadasActuales)



                    break
                }

                else
                    println("No ingreso sus datos correctamente")
                Salidado =false


            }
            2 -> {
                println()
                println("Usted selecciono Registro")
                registroUsuario(usuario)
            }

            3 -> Salidado=true


            else ->{
                println()
                println("OPCIÓN INVALIDA!!")
            }
        }
    }while (!Salidado)



// Impresion
//println(“$nombreCliente verifica las placas y la foto del conductor”)

}

//Funcion para el inicio de sesion del usuario
fun LoginUsuario(user: String, password: String, usuario: MutableMap<String,String>): Boolean{

    fun validate(input: String, input2: String):Boolean {
        if(input.isEmpty() || input2.isEmpty()){
            return false
        }
        else{
            if(input in usuario ){
                return  true
            } else
                return false
        }}

    val userValidate = validate(user,password)

    return userValidate
}

// Funcion para el ingresar las coordenadas del usuario
fun ingresarCoordenadas(coordenadaActual: Int, coordenadaDestino: Int): Boolean {
    fun validate (input: Int): Boolean {
        if (input == null || input.equals("")){
            return false
        }
        return true
    }

    val coorActualValidate = validate(coordenadaActual)
    val coorDestinoValidate = validate(coordenadaDestino)

    return coorActualValidate && coorDestinoValidate
}

//Funcion para validar que haya ingresado un metodo de pago
fun metodoDePago(pago:String): Boolean {
    fun validate(input: String): Boolean {
        if (input.isEmpty()) {
            println("NO HA INGRESADO NINGUN METODO DE PAGO")
            return false
        }
        return true
    }
    val pagoValidate = validate(pago)

    return pagoValidate
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
fun registroUsuario(usuarios: MutableMap<String, String>){
    println("Ingrese el nombre de usuario")
    var Nombre = readLine()!!
    println("Ingrese su contraseña")
    var Contrasena = readLine()!!
    usuarios[Nombre] = Contrasena
}
