package mx.tecnm.tepic.ladm_u2_practica2_juegodelasmoscas

class AnimacionHilo (p:MainActivity):Thread(){
    var puntero = p
    var mantener = true

    fun detener(){
        mantener = false
    }

    override fun run() {
        super.run()
        while(mantener){
            sleep(10)
            puntero.runOnUiThread {
                puntero.lienzo!!.animacionMoscas()

            }
        }
    }
}