open class Vehiculo(
    val nombre:String,
    val marca: String,
    val modelo: String,
    capacidadCombustible: Float,
    combustibleActual: Float,
    var kilometrosActuales: Float
) {
    companion object{
        const val KM_POR_LITRO = 10f
    }

    val capacidadCombustible:Float = redondear(capacidadCombustible)
    var combustibleActual:Float = redondear(combustibleActual)
        set(value) {
            field = redondear(value)
        }

    /*
    * Devuelve una cadena con información sobre el vehículo
    *
    * */
    fun obtenerInformacion():String{
        return "Con ${capacidadCombustible}km podemos recorrer ${calcularAutonomia()}"
    }
    open fun obtenerKmLitro():Float{
        return KM_POR_LITRO
    }
    open fun calcularAutonomia(): Float = redondear(combustibleActual*KM_POR_LITRO)
    fun realizarViaje(distancia:Float, kmLitro:Float = obtenerKmLitro()):Float{
        if (distancia > calcularAutonomia()){
            try {
                actualizarCombustible(distancia/kmLitro)
                kilometrosActuales += distancia
                return 0f
            }catch (e:IllegalArgumentException){
                println(e)
            }
            return distancia
        }
        else{
            val distanciarestante = distancia - calcularAutonomia()
            try {
                actualizarCombustible((distancia - distanciarestante)/kmLitro)
                kilometrosActuales += distancia - distanciarestante
                return redondear(distanciarestante)
            }catch (e:IllegalArgumentException){
                println(e)
            }
            return distancia
        }
    }
    fun actualizarCombustible(combustible:Float){
        if (combustible > combustibleActual) throw IllegalArgumentException("Deposito Vacío")
        else combustibleActual -= combustible
    }
    fun repostar(cantidad:Float = 0f): Float{
        val combustibleRestante = capacidadCombustible - combustibleActual
        val cantidadARepostar = if (cantidad >= combustibleRestante || cantidad <=0) capacidadCombustible
        else cantidad
        combustibleActual += cantidadARepostar
        return redondear(cantidadARepostar)
    }

    fun redondear(numero: Float): Float {
        val factor = 100.0f
        return (numero * factor).toInt().toFloat() / factor
    }

}