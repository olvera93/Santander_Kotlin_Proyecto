package s7.e1.e3

fun main() {
    if("obj" !is String){
        println("No string")
    }

    fun imprimirNumero(x: Any){
        when (x){
            is Int -> println(x/1)
            is String -> println(x.length +1)
            is IntArray -> println(x.sum())}


        }
    imprimirNumero(20)
    imprimirNumero("asereje")
    imprimirNumero(intArrayOf(2,4,6))

    val obj:Any? = null
    val str: String = obj as String
}
