package Proyecto

interface CallBack {
    fun onSuccess(conductor: Conductor)
    fun onFailure(exception: Exception)
}