package mx.tecnm.tepic.ladm_u2_practica2_moscas_touchevent

import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.View

class Objetos {
    var x = 0f
    var y = 0f
    var tam = 0f
    var col = Color.rgb((Math.random()*200).toInt(),(Math.random()*200).toInt(),(Math.random()*200).toInt())
    var dirY = true
    var dirX = true

    init {
        x = (Math.random()*2100).toFloat()
        y = ((Math.random()*2000)*-1).toFloat() //El -1 es para que la dirección de los copos vaya hacía abajos
        tam = ((Math.random()*50)+10).toFloat()
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
        if (dirY)y+=tam/2
        if (!dirY)y-=tam/2
        if (dirX)x+=tam/2
        if (!dirX)x-=tam/2
    }

    fun paintBubble(c: Canvas){
        val p = Paint()
        p.color = col
        c.drawCircle(x,y,tam, p)
    }
}