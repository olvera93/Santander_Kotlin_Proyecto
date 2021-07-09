package s7r1

fun main() {
    val names: List<String?> = listOf("Name One", null,"Name Three")

    names.forEach { if (it !=null){
    println(it)}}

    names.forEach { it?.let{ println(it)} }

    names.forEach {
        val print = it ?: "No se puede imprimir null"
        println(print)

        val person = Person("Juan","Gerardo")
        println(person.getApellido())
        println(person.getName())

        val person2 = Person("Juan")
        println(person2.getApellido())
        println(person2.getName())
    }}

    class Person(
      private val Nombre:String,
        private val Apellido:String? =null){
        fun getName():String{
            val lName =  Apellido ?: throw IllegalArgumentException("Necesitas apellidos")
            val Nm = Nombre?:throw java.lang.IllegalArgumentException("Necesitas Nombre")
            return "$lName $Nm"
        }
    fun getApellido():String{
        val app = Apellido ?: ("No se puede recibir valores nulos")
        return app
    }
    }