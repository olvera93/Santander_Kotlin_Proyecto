package s5

fun main(){

    val calificaciones = listOf(10,8, 9 ,5,3,2,5,1,7,6,9,4,10,3,6,2,6,5)



    val promedio = calificaciones.sum().toDouble()/calificaciones.size
    println("promedio: $promedio")

    val menos4 = !calificaciones.none{(it<4)  }
    println("Los alumnos sacaron menos de 4: ${menos4}")


    val califExtra = calificaciones.map { if (it>=10) it else it + 1 }
    println("Las calificaciones con punto extra son: $califExtra")

}