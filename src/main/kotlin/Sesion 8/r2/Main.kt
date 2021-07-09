package r2

import kotlinx.coroutines.*

inline fun measureTimeMillis(block: () -> Unit){
    val start= System.currentTimeMillis()
    block()
    val end = System.currentTimeMillis()-start
    println("Tiempo transcurrido: ${end/1_000L} segundos")
}
suspend fun getPlay():Int {

    delay(3000)
    val stock = 5299
    return stock
}suspend fun getScooter():Int {

    delay(2000)
    val stock = 2400
    return stock
}

fun main()= runBlocking {
    //Ejercicio 2
    runBlocking{
        println("Ejercicio 2")
        println("El precio del PS4 es: ${getPlay()}")
    println("El precio del Scooter es: ${getScooter()} ")}


    //Ejercicio 3
    measureTimeMillis {
        runBlocking {
            println("//EJERCICIO 3")
            val total = getPlay() + getScooter()
            println("El precio total es: $total")
        }}

        //Ejercicio 4
    measureTimeMillis{
        runBlocking {
            println("Ejercicio 4")
            val precioScooterasc = async { getScooter() }
            val precioPlayasc = async { getPlay() }
            val totalasc = precioPlayasc.await() + precioScooterasc.await()
            println("El precio total asincrono es: $totalasc")
        }}


    }