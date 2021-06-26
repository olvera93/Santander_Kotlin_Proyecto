package Proyecto

import java.util.*
import kotlin.concurrent.schedule
import kotlin.system.exitProcess

class Usuario: Viaje(), CancelarViaje, Promocion {

    private var usuario: String = ""
    private var password: String = ""
    private var coorActuales: Int = 0
    private var coorDestino: Int = 0
    var condor: Int = 0

    override  val discount = 10 //es porcentaje, o sea 10%
    override val typeDiscount = 0 //0 para porcentaje, 1 para cantidad
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
                println("Bienvenido $usuario")
                this.estatus = "Solicitando Viaje"
                //println("${this.estatus}")
                return true
            }
            else
                println("Usuario o contraseña incorrectos")
            return false
        }
        val userValidate = validate(user,password)
        return userValidate
    }

    //Funcion para solicitar  viaje
    fun solicitarViaje(coordenadaActual: Int,coordenadaDestino: Int,coorChofer: Conductor): Float{
        var diferenciaCoordenadas = coorChofer.getCoordenadasConductor() - coordenadaActual
        var tiempoLlegada: Float = diferenciaCoordenadas.toFloat()
        val proximidad = diferenciaCoordenadas.toInt()
        when (proximidad){
            0 -> println("Su chofer ha llegado")
            in 1..100 ->{
                for(i: Int in proximidad downTo -1){
                    tiempoLlegada-= condor
                    if (tiempoLlegada <= 0) {
                        break
                    } else {
                        println("Su Chofer tardara en llegar ${((tiempoLlegada)).toInt()} segundos")
                        condor ++
                    }

                }

            }

            in 100..200 ->{
                for(i: Int in proximidad downTo -1){
                    tiempoLlegada-= condor
                    if ( tiempoLlegada < 0 ) {
                        break
                    } else {
                        println("Su Chofer tardara en llegar ${((tiempoLlegada)).toInt()} segundos")
                        condor ++
                    }


                }
            }

            in 200..250 -> {
                for (i: Int in proximidad downTo -1){
                    tiempoLlegada-= condor
                    if (tiempoLlegada < 0) {
                        break
                    } else {
                        println("Su Chofer tardara en llegar ${((tiempoLlegada)).toInt()} segundos")
                        condor ++
                    }

                }

            }else -> {

                println("Su chofer esta muy lejos de su ubicacion")
            }
        }

        return proximidad.toFloat()
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

    override fun cancelarViaje() {
        if (reserved) {
            reserved = false
            paidAmount = 0
            println("Viaje se ha cancelado exitosamente")
        } else {
            println("No has hecho ningun viaje")
        }
    }
    fun registroUsuario(usuario: String,password: String, pago: String){ //Comprobacion que en el registro no tenga valores invalidos
        if(usuario.isEmpty()||password.isEmpty()){
            println("No ingreso un dato valido")
        }else{
            this.usuario=usuario
            this.password=password
            this.estatus="Usuario Registrado"
            println("Registro exitoso")}
    }
    //Costo de viaje
    fun calcularCostoViaje(coorActual: Int, coorDestino: Int): Float {
        var distancia: Int = (coorDestino - coorActual)
        var precioViaje: Float = (PRECIO_POR_COR * distancia.toFloat()) + PRECIO_BASE
        return precioViaje
    }

    //
    public fun estadoViaje(viajeObj: String){
        when(viajeObj){
            "Viaje Terminado" -> {
                println()
                estatus = "Viaje Terminado"
            }
            else -> println("Aun no acaba su viaje")

        }
    }

    override fun getPrice(coordenadaDestino: Int): Int {
        val lugar = coordenadaDestino
        return if (lugar==null) 0 else lugar * coordenadaDestino //regresamos 0 si no hay tarifa, si sí, hacemos el cálculo
    }
}
