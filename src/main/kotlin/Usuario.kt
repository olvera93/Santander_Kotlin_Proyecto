package Proyecto

class Usuario {

    private var usuario: String = ""
    private var password: String = ""

    private var coorActuales: Int = 0
    private var coorDestino: Int = 0

    private var tipoPago: String = ""
        fun setTipoPago(pago: String){  //Utilizamos un setter para guardar un valor para el pago ya sea Efectivo o Tarjeta
            if(pago == "Efectivo" || pago == "Tarjeta"){
                this.tipoPago = pago
            }
        }


    fun getTipoPago(): String {
        return tipoPago
    }

    fun getUsuario(): String {
        return usuario
    }

    fun setUsuario(usuario: String) {
        this.usuario = usuario
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
            }
            return true
     }
        val userValidate = validate(user,password)
        return userValidate
    }

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





}