class Carrera(
    val nombreCarrera: String,
    val distanciaTotal: Float,
    val participantes: List<Vehiculo>,
    var estadoCarrera: Boolean,
    var historialAcciones: MutableMap<String, MutableList<String>>,
    var posiciones: MutableList<Pair<String, Int>>
) {
    fun iniciarCarrera(){

    }
    fun avanzarVehiculo(vehiculo: Vehiculo){

    }
    fun repostarVehiculo(vehiculo: Vehiculo, cantidad: Float){

    }
    fun realizarFiligrana(vehiculo: Vehiculo){

    }
    fun actualizarPosiciones(){

    }
    fun determinarGanador(){

    }
    fun obtenerResultados(){

    }
}