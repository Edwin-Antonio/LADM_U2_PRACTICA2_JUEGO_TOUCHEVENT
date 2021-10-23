package mx.tecnm.tepic.ladm_u2_practica2_moscas_touchevent

import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint

class Imagen (l:Lienzo) {
    var x = 0f
    var y = 0f
    var imagen = BitmapFactory.decodeResource(l.resources,R.drawable.firefox)
    var dirY = true
    var dirX = true
    var invisible = false

    init {
        x = (Math.random()*2100).toFloat()
        y = ((Math.random()*2000)*-1).toFloat() //El -1 es para que la dirección de los copos vaya hacía abajos
        //col = Color.rgb((Math.random()*200).toInt(),(Math.random()*200).toInt(),(Math.random()*200).toInt())
    }

    fun moveBubble(){
        /*y+=tam/2
        if (y>1000) y = ((Math.random()*2000)*-1).toFloat()*/
        if (y>850 && dirY==true) {
            dirY=false
        }
        if (y<0 && dirY==false) {
            dirY=true
        }
        if (x>1770 && dirX==true) {
            dirX=false
        }
        if (x<0 && dirX==false) {
            dirX=true
        }
        if (dirY)y+=imagen.height/8
        if (!dirY)y-=imagen.height/8
        if (dirX)x+=imagen.width/8
        if (!dirX)x-=imagen.width/8
    }

    fun paintImage(c: Canvas){
        if (invisible) return
        c.drawBitmap(imagen,x,y, Paint())
    }

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