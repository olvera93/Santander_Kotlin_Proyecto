package Proyecto.interfaces

interface Promocion {


    val descuento: Int //el descuento en porcentaje o en cantidad
    val tipoDescuento: Int //porcentaje o cantidad

    val obtenerDescuento: (Int) -> Int
}
