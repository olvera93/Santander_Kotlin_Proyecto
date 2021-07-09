package r1

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun main() {
   //La funcion delay no puede llamarse sola(should be called only from a coroutine or another suspend function) delay(1000)
    println("Antes de la corutina")

    GlobalScope.launch {
        println("Delay")
        Thread.sleep(100)
        println("Delay de un segundo")
    }
    println("Ingrese un numero")
    val password23= readLine()?.toString()


    /* Thread.sleep(300)
    println("Despues de la corutina ")
    No se ejecuto nada puesto que el programa termino de inmediato antes de detener el hilo de la corutina
     */

}
