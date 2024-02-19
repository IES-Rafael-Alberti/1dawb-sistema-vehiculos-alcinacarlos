class Automovil(
    marca: String,
    modelo: String,
    capacidadCombustible: Float,
    combustibleActual: Float,
    kilometrosActuales: Int,
    val esElectrico:Boolean
):Vehiculo(marca, modelo, capacidadCombustible, combustibleActual, kilometrosActuales) {
    override fun calcularAutonomia(): Int {
        return if (esElectrico) (combustibleActual*5).toInt() else super.calcularAutonomia()
    }

    override fun realizarViaje(distancia:Int):Int{
        val kmporlitro = if (esElectrico) 5 else 10
        if (distancia > calcularAutonomia()){
            combustibleActual -= distancia/kmporlitro
            kilometrosActuales += distancia
            return 0
        }
        else{
            val distanciarestante = distancia - calcularAutonomia()
            combustibleActual -= (distancia - distanciarestante)/kmporlitro
            kilometrosActuales += distancia - distanciarestante
            return distanciarestante
        }
    }
    companion object{
        var condicionBritania = false
        fun cambiarCondicionBritania(condicion:Boolean){
            condicionBritania = condicion
        }
    }
    fun realizarDerrape():Float{
        realizarViaje(5)
        return combustibleActual
    }
}