package Proyecto

class FormaPago {
    var credito=10000f

    open fun Pago(monto:Float):Boolean{
        if (credito > monto){
            this.credito -= monto
            return true
        }else{
            return false
        }
    }

}