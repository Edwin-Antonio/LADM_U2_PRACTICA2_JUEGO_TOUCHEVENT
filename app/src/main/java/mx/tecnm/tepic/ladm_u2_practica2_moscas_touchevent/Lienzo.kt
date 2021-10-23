package mx.tecnm.tepic.ladm_u2_practica2_moscas_touchevent

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.MotionEvent
import android.view.View
import java.util.ArrayList

class Lienzo(act:MainActivity) : View(act) {
    val principal = act
    val objThread = objMovement(this)
    var pointer : Imagen ?= null

    init {
        objThread.start()
    }

    override fun onDraw(c: Canvas) {
        super.onDraw(c)

        val p = Paint()
        c.drawColor(Color.BLUE)

        // Dibujar los copos de nieve  0..499
        (0..49).forEach {
            objThread.obj[it].paintImage(c)
        }
    }
    override fun onTouchEvent(e: MotionEvent) : Boolean {
        super.onTouchEvent(e)

        //Información sobr el toque
        //Coordenadas del toque X,Y
        //Accion = Presionar, Mover, levantar dedo

        //Opciones de las acciones
        val accion = e.action

        when(accion){
            MotionEvent.ACTION_DOWN ->{ // Estas acciones son solamente para cuando se este presionando la imagen

                (0..49).forEach {
                    if (objThread.obj[it].onArea(e.x,e.y)){
                        pointer = objThread.obj[it]
                        pointer!!.invisible = true
                        return true // El return true es para que salga de la estructura
                    }
                }
            }
            MotionEvent.ACTION_UP ->{
                pointer=null
            }
        }
        invalidate()
        return true
    }

}

class objMovement(p:Lienzo) : Thread(){
    var pointer = p
    val obj = ArrayList<Imagen>()

    init {
        (1..50).forEach {  //1..500
            val img = Imagen(p)
            obj.add(img)
        }
    }

    override fun run() { //Indica el movimiento del copo
        super.run()
        while (true){
            (0..49).forEach { //0..499
                obj[it].moveBubble()
            }
            pointer.principal.runOnUiThread{
                pointer.invalidate() //Se vuelve a llamar al onDraw para repintar
            }
            sleep(80)
        }
    }
}

