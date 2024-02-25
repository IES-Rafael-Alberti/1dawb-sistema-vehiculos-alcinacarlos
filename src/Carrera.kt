import kotlin.random.Random

class Carrera(
    val nombreCarrera: String,
    val distanciaTotal: Float,
    val participantes: List<Vehiculo>,
    var estadoCarrera: Boolean = false,
    var historialAcciones: MutableMap<String, MutableList<String>> = mutableMapOf(),
    posiciones: MutableList<Pair<String, Int>> = mutableListOf()
) {
    var posiciones:MutableList<Pair<String, Int>> = participantes.map { vehiculo -> Pair(vehiculo.nombre, vehiculo.kilometrosActuales.toInt())}.toMutableList()
    companion object{
        const val KM_SEGMENTO = 20f
        const val DISTANCIA_MINIMA = 10
        const val DISTANCIA_MAXIMA = 200
    }

    /**
     * Inicia la carrera, estableciendo estadoCarrera a true y comenzando el ciclo de iteraciones donde los vehículos avanzan y realizan acciones.
     */
    fun iniciarCarrera(){
        estadoCarrera = true
        while (estadoCarrera){
            avanzarVehiculo(participantes.random())
            actualizarPosiciones()
            determinarGanador()
        }
        obtenerResultados()
    }

    /**
     * Identificado el vehículo, le hace avanzar una distancia aleatoria entre 10 y 200 km. Si el vehículo necesita repostar, se llama al método repostarVehiculo() antes de que pueda continuar. Este método llama a realizar filigranas.
     */
    fun avanzarVehiculo(vehiculo: Vehiculo){
        var distanciaRandomRestante = Random.nextInt(DISTANCIA_MINIMA, DISTANCIA_MAXIMA).toFloat()
        registrarAccion(vehiculo.nombre, "avanzar")
        while (distanciaRandomRestante > 0){
            var distanciaARecorrer = KM_SEGMENTO

            if (distanciaRandomRestante < 20){
                distanciaARecorrer = distanciaRandomRestante

            }
            if (vehiculo.realizarViaje(distanciaARecorrer) == distanciaRandomRestante){
                repostarVehiculo(vehiculo)
                vehiculo.realizarViaje(distanciaARecorrer)
            }
            realizarFiligrana(vehiculo)
            distanciaRandomRestante -= distanciaARecorrer
        }
    }

    /**
     * Reposta el vehículo seleccionado, incrementando su combustibleActual y registrando la acción en historialAcciones.
     */
    fun repostarVehiculo(vehiculo: Vehiculo, cantidad: Float = 0f){
        vehiculo.repostajes++
        vehiculo.repostar(cantidad)
        registrarAccion(vehiculo.nombre, "repostar")
    }

    /**
     * Determina aleatoriamente si un vehículo realiza una filigrana (derrape o caballito) y registra la acción.
     */
    fun realizarFiligrana(vehiculo: Vehiculo){
        repeat(Random.nextInt(1, 2)){
            when(vehiculo){
                is Motocicleta -> {
                    vehiculo.realizarCaballito()
                    registrarAccion(vehiculo.nombre, "caballito")
                }
                is Automovil -> {
                    vehiculo.realizarDerrape()
                    registrarAccion(vehiculo.nombre, "derrape")
                }
            }
        }


    }

    /**
     * Actualiza posiciones con los kilómetros recorridos por cada vehículo después de cada iteración, manteniendo un seguimiento de la competencia.
     */
    fun actualizarPosiciones(){
       posiciones = participantes.mapIndexed { index, vehiculo ->  Pair(vehiculo.nombre, (vehiculo.kilometrosActuales.toInt() - posiciones[index].second))}.toMutableList()
    }

    /**
     * Revisa posiciones para identificar al vehículo (o vehículos) que haya alcanzado o superado la distanciaTotal, estableciendo el estado de la carrera a finalizado y determinando el ganador.
     */
    fun determinarGanador(){
        posiciones.forEachIndexed { index, (nombre, km)  ->
            if (km >= distanciaTotal){
                estadoCarrera = false
            }
        }
    }

    /**
     * Devuelve una clasificación final de los vehículos, cada elemento tendrá el nombre del vehiculo, posición ocupada, la distancia total recorrida, el número de paradas para repostar y el historial de acciones. La collección estará ordenada por la posición ocupada.
     */
    fun obtenerResultados() {
        var posiciones2xd = posiciones.sortedByDescending { it.second }
        println("!!!!!!!!!!!!Enohrabuena ${posiciones2xd[0].first}")
        participantes.forEachIndexed { index, vehiculo -> println(ResultadoCarrera(vehiculo, posiciones.get(index).second, vehiculo.kilometrosActuales,vehiculo.repostajes)) }
    }
    data class ResultadoCarrera(
        val vehiculo: Vehiculo,
        val posicion: Int,
        val kilometraje: Float,
        val paradasRepostaje: Int
    ){
        override fun toString(): String {
            return "$vehiculo".replace(if (vehiculo is Automovil) "Automovil" else "Motocicleta", vehiculo.nombre) + "\n Km Hechos: $posicion\n Paradas a repostar: $paradasRepostaje\n Acciones en carrera:"
        }
    }

    /**
     * Añade una acción al historialAcciones del vehículo especificado.
     */
    fun registrarAccion(vehiculo: String, accion: String){
        historialAcciones[vehiculo]?.add(accion)
    }
}