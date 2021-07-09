package s5

fun main() {

    topPrecio(10000.0,"PROMO2020", ::ObtenerPrecio)
}

fun ObtenerPrecio(Precio:Double, Cupon:String):Double{
    return when(Cupon){
        "NOIVA" -> Precio
        "HALFIVA" ->Precio*.08
        "MINUS" ->Precio-100
        "PROMO2020"-> return when(Precio){
             in 0.0..1000.0 ->Precio*.12
             in 1000.0 .. 2000.0 ->Precio*.4
             in 2000.0 .. 4000.0 ->(Precio*.16)/.5
            else-> Precio*.75
        }
        else -> Precio*.16
    }
}
fun topPrecio(Precio: Double,Cupon: String,ObtenerPrecio:(Double,String)->Double){
    println("El precio es:${ObtenerPrecio(Precio,Cupon)} y el cupon es: $Cupon")
}

val Ahorrador :(Double,Double)->String= {Expected:Double, Reality:Double ->
    val Performance = Expected/Reality
    when{
        Performance > 1 -> "Ahorrador pro"
        Performance== 1.0 -> "Buen ahorrador"
        Performance < 1.0 && Performance >=0.8 ->"Almost"
        else ->"Aprendiz Saver"

    }
}

//Lambda Function

fun getPromo(price:Double,coupon:String){

    val getPrice:( Double, (Double) -> Double) -> Unit = { price,operation ->  //Ahora falta implementar esto en el proyecto de Uber chafa como meto esta madre en este proyecto
        println("El precio actual de tu producto es ${operation(price)}")
    }

    val totalPrice = {price:Double ->  price*1.16}
    val halfIva = {price:Double ->  price*1.08}
    val noIva  = {price:Double ->  price}
    val ranges  = {price:Double ->
        when(price){
            in 0.0..1000.0 -> price * 1.12
            in 1000.0..2000.0 -> price * 1.04
            in 2000.0..4000.0 -> (price * 1.16) / 2
            else -> price/3
        } }
    val minus100 = {price:Double ->  price*1.16 - 100 }


    when (coupon){
        "PROMO2020" -> getPrice(price,ranges)
        "NOIVA" -> getPrice(price,noIva)
        "MINUS100" -> getPrice(price,minus100)
        "HALFIVA" -> getPrice(price,halfIva)
        else -> getPrice(price,totalPrice)
    }


}
