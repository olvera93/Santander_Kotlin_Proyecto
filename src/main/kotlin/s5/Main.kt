package s5

fun main() {}
   /* var saludo = { println("Andres")}
    saludo

    val suma = {a: Int, b: Int -> a+b}
    println(suma(5,5))

    val presentarse:(String,Int)->String = {name, age -> "Mi nombre es $name y tengo $age"}

    println(presentarse("Stuart",24))

    var square: (Int)->Int={it*it}
    println(square(5))*/


    //Funcion Lambda
    /*val saverGrade: (Double,Double) -> String = { expected: Double, saved: Double ->
        val percentage = saved / expected

        when {
            percentage > 1  -> "Ahorrador pro"
            percentage == 1.0   -> "Buen ahorrador"
            percentage < 1.0 && percentage >= 0.8 -> "Almost"
            else     -> "aprendiz saver"
        }
    }

    //println(saverGrade(100.0,50.0))

    //Funcion Anonima
    val saverGrade2 =  fun(expected: Double, saved: Double):String  {
        val percentage = saved / expected

        return  when {
            percentage > 1  -> "Ahorrador pro"
            percentage == 1.0   -> "Buen ahorrador"
            percentage < 1.0 && percentage >= 0.8 -> "Almost"
            else     -> "aprendiz saver"
        }
    }
    //println(saverGrade2(100.0,120.0))

    //Funciones de orden superior
    fun calculadora(a: Int, b: Int, operacion: (Int,Int)-> Int): Int{
        return  operacion(a,b)
    }

    fun suma(a: Int, b:Int)= a + b
    fun  resta(a: Int, b:Int)= a - b
    fun multi(a: Int, b:Int)= a * b
    fun division(a: Int, b:Int)= a / b

    val suma=calculadora(10,5, ::suma)
    val resta=calculadora(10,5, ::resta)
    val multi=calculadora(10,5, ::multi)
    val division=calculadora(10,5, ::division)


    println(suma)
    println(multi)
    println(resta)
    println(division)*/

   /* fun normal(Precio: Int,Cupon: String ):String{
       return when(Cupon){
        "Noiva"-> "Precio sin Iva"
            "HALFIVA"->{val preciofinal=Precio*.08
                "Se cobra el precio con un impuesto del 8%, precio final: $preciofinal"}

                "Minus100" -> {val preciofinal=Precio-100
                    "Se cobra el precio con un impuesto de 100 pesos, precio final: $preciofinal"}

            "PROMO2020" -> return when(Precio){
                 in 1..1000 -> {val preciofinal=Precio*.12
                    "Se cobra el precio con un impuesto del 12%, precio final: $preciofinal"}
                in 1000..2000 -> {val preciofinal=Precio*.04
                    "Se cobra el precio con un impuesto del 4%, precio final: $preciofinal"}
                in 2000..4000 -> {val preciofinal=Precio*.5
                    "Se cobra el precio con un impuesto del 50%, precio final: $preciofinal"}
                Precio > 4000 -> {val preciofinal=Precio*.75
                    "Se cobra el precio con un impuesto del 75%, precio final: $preciofinal"}

            }

    }
    }

    fun superior(Precio: Int, Cupon: String, normal: (Int,String)->String):String{
        return normal(Precio,Cupon)
    }

}*/