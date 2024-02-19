class Motocicleta(
    marca: String,
    modelo: String,
    capacidadCombustible: Float,
    combustibleActual: Float,
    kilometrosActuales: Int,
    val cilindrada:Int
):Vehiculo(marca, modelo, capacidadCombustible, combustibleActual, kilometrosActuales) {
    override fun calcularAutonomia(): Int {
        return (combustibleActual*20).toInt()
    }
    override fun realizarViaje(distancia:Int):Int{
        val kmporlitro = 20
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
    fun realizarCaballito():Float{
        realizarViaje(5)
        return combustibleActual
    }
}