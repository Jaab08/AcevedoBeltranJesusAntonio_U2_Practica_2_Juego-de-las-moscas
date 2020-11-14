package mx.tecnm.tepic.ladm_u2_practica2_juegodelasmoscas

import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.CountDownTimer
import android.view.MotionEvent
import android.view.View

class Lienzo (p:MainActivity) : View(p) {
    var numMoscas = (80..100).random()
    var moscas = arrayListOf<Imagen>()
    var manchas = arrayListOf<Imagen>()

    var lleno = false
    var terminoHilo = false
    var ganar = true
    var contadorTimer = 60
    val LAPSO = 1000
    val TIEMPOTOTAL = 60000

    var main : MainActivity ?= null

    var timer = object : CountDownTimer(TIEMPOTOTAL.toLong(), LAPSO.toLong()){
        override fun onTick(p0: Long) {
            contadorTimer --
        }

        override fun onFinish() {
            if (moscas.size != 0) {
                ganar = false
            }
            terminoHilo = true
        }
    }

    override fun onDraw(c: Canvas) {
        super.onDraw(c)

        if (lleno == false) {
            timer.start()
            for (i in 0..(numMoscas)) {
                moscas.add(
                    Imagen(
                        this,
                        (0..900).random().toFloat(),
                        (0..2000).random().toFloat(),
                        R.drawable.mosca
                    )
                )
                lleno = true
            }
        }

        var p = Paint()

        for (i in 0..(manchas.size-1)){
            manchas[i].pintar(c)
        }

        for (i in 0..(moscas.size-1)){
            moscas[i].pintar(c)
        }

        var puntuacion = moscas.size
        p.textSize = 100f
        p.color = (Color.RED)
        c.drawText("Moscas restantes: " + puntuacion.toString(), 100f, 100f, p)
        p.textSize = 100f
        p.color = (Color.BLUE)
        c.drawText("Tiempo restante: " + contadorTimer, 100f, 1900f, p)

        if (terminoHilo == true || puntuacion == 0) {
            if (ganar == true) {
                c.drawColor(Color.WHITE)
                p.textSize = 150F
                p.color = (Color.GREEN)
                c.drawText("FELICIDADES", 80f, 850F, p)
                c.drawText("GANASTE", 200f, 1000f, p)
                var trofeo = Imagen(this,400f,1200f,R.drawable.trofeo)
                trofeo.pintar(c)
                var artificiales = Imagen(this,400f,400f,R.drawable.fuegos)
                artificiales.pintar(c)

            } else {
                c.drawColor(Color.BLACK)
                p.textSize = 150f
                p.color = (Color.RED)
                c.drawText("GAME", 300f, 850f, p)
                c.drawText("OVER", 320f, 1000f, p)
            }
        }

    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                for (i in 0..(moscas.size-1)) {
                    if(moscas[i].estaEnArea(event.x,event.y)){
                        moscas[i].imagen = BitmapFactory.decodeResource(resources, R.drawable.manchar)
                        manchas.add(moscas[i])
                        moscas[i].parar(event.x, event.y)
                        moscas.removeAt(i)
                        break;
                    }
                }
            }
            MotionEvent.ACTION_MOVE -> {

            }
            MotionEvent.ACTION_UP -> {

            }
        }
        invalidate()
        return true
    }

    fun animacionMoscas() {
        for (i in 0..(moscas.size-1)){
            moscas[i].rebote(width,height)
        }

        invalidate()
    }

}