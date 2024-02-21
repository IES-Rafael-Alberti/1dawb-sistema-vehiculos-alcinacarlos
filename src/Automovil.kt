class Automovil(
    nombre:String,
    marca: String,
    modelo: String,
    capacidadCombustible: Float,
    combustibleActual: Float,
    kilometrosActuales: Float,
    val esHibrido:Boolean
):Vehiculo(nombre, marca, modelo, capacidadCombustible, combustibleActual, kilometrosActuales) {
    companion object{
        const val AHORRO_ELECTRICO = KM_POR_LITRO + 5
        var condicionBritanica:Boolean = false
        fun cambiarCondicionBritanica(nuevaCondicion: Boolean){
            condicionBritanica = nuevaCondicion
        }
    }
    override fun calcularAutonomia(): Float {
        return if (esHibrido) redondear(combustibleActual* AHORRO_ELECTRICO ) else super.calcularAutonomia()
    }
    override fun obtenerKmLitro(): Float {
        return if (esHibrido) AHORRO_ELECTRICO else KM_POR_LITRO
    }

    fun realizarDerrape():Float{
        try {
            actualizarCombustible(if (esHibrido) 6.25f/obtenerKmLitro() else 7.5f/obtenerKmLitro())
        }catch (e:IllegalArgumentException){
            println(e)
        }
        return combustibleActual
    }
}