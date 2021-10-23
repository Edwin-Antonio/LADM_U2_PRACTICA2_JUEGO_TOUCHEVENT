package mx.tecnm.tepic.ladm_u2_practica2_moscas_touchevent

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import androidx.core.graphics.component1
import java.util.*

class Imagen (l:Lienzo) {
    var x = 0f
    var y = 0f
    var imagen = BitmapFactory.decodeResource(l.resources,R.drawable.firefox)
    var dirY = true
    var dirX = true
    var invisible = false

    init {
        x = (Math.random()*2100).toFloat()
        y = ((Math.random()*2000)*-1).toFloat() //El -1 es para que la dirección de las imagenes vaya hacía abajo
        var rand = ((Math.random()*3)+1).toInt() // variable random para elegir entre las diferentes imagenes
        when(rand){
            1 ->{
                imagen = BitmapFactory.decodeResource(l.resources,R.drawable.twitter)
            }
            2 ->{
                imagen = BitmapFactory.decodeResource(l.resources,R.drawable.youtube)
            }
            3 ->{
                imagen = BitmapFactory.decodeResource(l.resources,R.drawable.twitch)
            }
        }
    }

    fun moveBubble(){
        if (y>750 && dirY==true) { //Si el objeto ya llego hasta el piso en Y
            dirY=false
        }
        if (y<-20 && dirY==false) { //Si el objeto ya llego hasta el TOP en Y
            dirY=true
        }
        if (x>1630 && dirX==true) { // Si el objeto ya llego al limite de las X
            dirX=false
        }
        if (x<-20 && dirX==false) { // Si el objeto ya llego al inicio de las X
            dirX=true
        }

        // Velocidad de las imagenes
        if (dirY) y+=imagen.height/8
        if (!dirY) y-=imagen.height/8
        if (dirX) x+=imagen.width/8
        if (!dirX) x-=imagen.width/8
    }

    //Pintar la imagen
    fun paintImage(c: Canvas){
        if (invisible) return
        c.drawBitmap(imagen,x,y, Paint())
    }

    //En caso de que el puntero se encuentre sobre el area de la imagen
    fun onArea(touchX: Float, touchY: Float) : Boolean{
        var y2 = y + imagen.height
        var x2 = x + imagen.width
        if(touchX>=x && touchX<=x2){
            if(touchY>=y && touchY<=y2){
                return true
            }
        }
        return false
    }
}