package mx.tecnm.tepic.ladm_u2_practica2_juegodelasmoscas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    var lienzo : Lienzo ?= null
    var hiloAnimacion = AnimacionHilo(p = this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lienzo = Lienzo(p = this)
        setContentView( lienzo )

        hiloAnimacion.start()
    }
}