package Proyecto.entity

import Proyecto.PRECIO_BASE
import Proyecto.PRECIO_POR_COR
import Proyecto.entity.abstracts.Viaje
import Proyecto.interfaces.CancelarViaje
import Proyecto.interfaces.Penalizacion
import Proyecto.interfaces.Promocion
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.util.*
import kotlin.concurrent.schedule
import kotlin.system.exitProcess


class Usuario: Viaje(), CancelarViaje, Promocion, Penalizacion {
    private var usuario: String = ""
    private var password: String = ""
    private var coorActuales: Int = 0
    private var coorDestino: Int = 0

    override  val descuento = 10 //es porcentaje, o sea 10%
    override val tipoDescuento = 0 //0 para porcentaje, 1 para cantidad

    override val obtenerDescuento : (Int) -> Int ={it ->
        (it* 100-descuento)/100}
    private var tipoPago: String = ""

        set(value) {
            field=value
            when(field) {
                "Tarjeta" -> {
                    field=value
                }
                "Efectivo" -> {
                    field=value
                }
                else -> {
                    println("Método de pago incorrecto $tipoPago")
                }
            }}
    private var estatus: String= "Solicitando Viaje!!"
        set(value) {
            val before=field
            field= value
            println("Tu estado ahora es $field")
            if (value == "Viaje Terminado"){
                Timer("SettingUp",false).schedule(3000){
                    field=before
                    println("Por favor $usuario no olvides tus pertenencias antes de bajar del vehiculo ")
                    exitProcess(0)
                }
            }
        }

    fun getEstatus(): String{
        return "$estatus"
    }

    fun getTipoPago(): String {
        return tipoPago
    }


    fun getUsuario(): String {
        return usuario
    }

    fun setUsuario(usuario: String) {
        if(usuario.isEmpty()){
            println("No ingreso un dato valido")
        }else {
            this.usuario = usuario
        }
    }

    fun getPassword(): String {
        return password
    }

    fun setPassword(password: String) {
        this.password = password
    }

    fun getCoorActuales(): Int {
        return coorActuales
    }
    fun setCoorActuales(coordenadaActual: Int) {
        this.coorActuales = coorActuales
    }
    fun getCoorDestino(): Int {
        return coorDestino
    }
    fun setCoorDestino(coordenadaDestino: Int) {
        this.coorDestino = coorDestino
    }

    //Funcion para el inicio de sesion del usuario
    fun loginUsuario(user: String, password: String): Boolean{
        fun validate(input: String, input2: String):Boolean {
            if (input.isEmpty() || input2.isEmpty()) {
                println("Falta un parametro")
                return false
            }else if(input==this.usuario && input2==this.password){
                println()
                println("**********************")
                println("Bienvenido $usuario")
                println("**********************")
                println()

                runBlocking {
                    delay(1500L)
                }
                this.estatus = "Solicitando Viaje"
                Thread.sleep(2000)

                return true
            }
            else
                println("Usuario o contraseña incorrectos")
            return false
        }

        val userValidate = validate(user,password)
        return userValidate
    }



    // Funcion por si el usuario quiere cancelar su viaje
    override fun cancelarViaje() {
        if (viajePedido) {
            viajePedido = false
            montoPagado = 0f
            println()
            println("¡Viaje se ha cancelado exitosamente!")
        } else {
            println("No has hecho ningun viaje")
        }
    }

    // Funcion para que el usuario se registre
    fun registroUsuario(usuario: String,password: String, pago: String){ //Comprobacion que en el registro no tenga valores invalidos
        if(usuario.isEmpty() || password.isEmpty() || pago.isEmpty()){
            println("No ingreso un dato valido")
        }else{
            this.usuario=usuario
            this.password=password
            println()
            println("Registro exitoso")}
    }

    // Funcion que calcula el costo del viaje
    public override fun calcularCostoViaje(coorActual: Int, coorDestino: Int): Float {
        val distancia: Int = (coorDestino - coorActual)
        val precioViaje: Float = (PRECIO_POR_COR * distancia.toFloat()) + PRECIO_BASE
        return precioViaje
    }

    // Indica el estado del viaje
    public override fun estadoViaje(viajeObj: String){
        when(viajeObj){
            "Viaje Terminado" -> {
                println()
                estatus = "Viaje Terminado"
            }
            else -> println("Aun no acaba su viaje")
        }
    }



}
