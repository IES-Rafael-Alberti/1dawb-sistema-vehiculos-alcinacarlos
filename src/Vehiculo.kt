open class Vehiculo(
    val marca: String,
    val modelo: String,
    capacidadCombustible: Float,
    combustibleActual: Float,
    var kilometrosActuales: Int
) {
    val capacidadCombustible:Float = redondear(capacidadCombustible)
    var combustibleActual:Float = redondear(combustibleActual)
        set(value) {
            field = redondear(value)
        }

    fun obtenerInformacion():String{
        return "Con ${capacidadCombustible}km podemos recorrer ${calcularAutonomia()}"
    }
    open fun calcularAutonomia(): Int = (combustibleActual*10).toInt()
    open fun realizarViaje(distancia:Int):Int{
        if (distancia > calcularAutonomia()){
            combustibleActual -= distancia/10
            kilometrosActuales += distancia
            return 0
        }
        else{
            val distanciarestante = distancia - calcularAutonomia()
            combustibleActual -= (distancia - distanciarestante)/10
            kilometrosActuales += distancia - distanciarestante
            return distanciarestante
        }
    }
    fun repostar(cantidad:Float = 0f): Float{
        val combustibleRestante = capacidadCombustible - combustibleActual
        val cantidadRepostar = if (cantidad >= combustibleRestante || cantidad <=0) capacidadCombustible
        else cantidad
        combustibleActual += cantidad
        return cantidadRepostar
    }

    private fun redondear(numero: Float): Float {
        val factor = 100.0f
        return (numero * factor).toInt().toFloat() / factor
    }

}