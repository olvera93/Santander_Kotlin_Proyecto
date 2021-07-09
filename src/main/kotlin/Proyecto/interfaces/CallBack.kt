package Proyecto.interfaces

import Proyecto.entity.Conductor

interface CallBack {
    fun onSuccess(conductor: Conductor)
    fun onFailure(exception: Exception)
}