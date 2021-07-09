package s5

fun main() {

    val calificaciones = listOf<Int>(10,8, 9 ,5,3,2,5,1,7,6,9,4,10,3,6,2,6,5)

    val promedio = calificaciones.sum()/calificaciones.size.toDouble()

    val menor = !calificaciones.none{(it>4)}

    val puntoextra = calificaciones.map { if (it>=10) it else it+1 }


    println(promedio)
    println(menor)
    println(puntoextra)
}