package Proyecto

import java.util.*

class Comentario {
    companion object {
        private var comentario: String? = null

        fun ingresarComentario() {
            val sc = Scanner(System.`in`)
            println()
            println("Ingresa un comentario al conductor: ")
            comentario = sc.nextLine()
        }
    }
}