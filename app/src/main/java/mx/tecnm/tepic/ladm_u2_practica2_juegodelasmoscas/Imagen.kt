package mx.tecnm.tepic.ladm_u2_practica2_juegodelasmoscas

import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint

class Imagen (punterolienzo: Lienzo, posX:Float, posY:Float, nombreImagen: Int) {
    var x = posX
    var y = posY
    var incX = 5
    var incY = 5
    var imagen = BitmapFactory.decodeResource(punterolienzo.resources, nombreImagen)

    fun pintar(c: Canvas) {
        c.drawBitmap(imagen, x, y, Paint())
    }

    fun estaEnArea(toqueX: Float, toqueY: Float): Boolean {
        var x2 = x + imagen.width
        var y2 = y + imagen.height

        if (toqueX >= x && toqueX <= x2) {
            if (toqueY >= y && toqueY <= y2) {
                return true
            }
        }

        return false
    }

    fun rebote(ancho: Int, alto: Int) {
        x += incX
        if (x <= 0 || x >= ancho-imagen.height) {
            incX *= -1
            if (incX > 0) {
                incX = (5..10).random()
            } else {
                incX = (-10..-5).random()
            }
        }

        y += incY
        if (y <= 0 || y >= alto-imagen.width) {
            incY *= -1
            if (incY > 0) {
                incY = (5..10).random()
            } else {
                incY = (-10..-5).random()
            }
        }
    }

    fun parar(xP : Float, yP : Float) {
        x = xP
        y = yP
    }

}