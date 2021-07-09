package Proyecto.entity

import java.util.*

class Comentario {

    // El usuario puede agregarle un comentario al conductor
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