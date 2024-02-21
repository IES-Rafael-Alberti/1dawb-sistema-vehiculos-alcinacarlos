class Motocicleta(
    nombre:String,
    marca: String,
    modelo: String,
    capacidadCombustible: Float,
    combustibleActual: Float,
    kilometrosActuales: Float,
    val cilindrada:Int
):Vehiculo(nombre, marca, modelo, capacidadCombustible, combustibleActual, kilometrosActuales) {
    init {
        comprobarCilindrada(cilindrada)
    }
    companion object{
        const val KM_POR_LITRO_MOTO_DEFAULT = 20f
    }
    fun comprobarCilindrada(cilindrada: Int){
        require(cilindrada in 125..1000){"La cilindrada no podrá ser inferior a 125 ni superior a 1000 cc"}
    }
    override fun obtenerKmLitro():Float{
        return redondear(KM_POR_LITRO_MOTO_DEFAULT - ((1000 - cilindrada) / 1000.0f))
    }
    override fun calcularAutonomia(): Float {
        if (cilindrada < 1000) {
            return combustibleActual * obtenerKmLitro()
        }
        return combustibleActual* KM_POR_LITRO_MOTO_DEFAULT
    }

    fun realizarCaballito():Float{
        actualizarCombustible(6.5f/obtenerKmLitro())
        return combustibleActual
    }
}