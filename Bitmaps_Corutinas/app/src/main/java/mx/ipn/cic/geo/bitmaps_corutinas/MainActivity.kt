package mx.ipn.cic.geo.bitmaps_corutinas

import android.view.Menu
import android.view.MenuItem
import kotlin.math.max
import android.os.Bundle
import android.widget.Toast
import android.widget.Button
import kotlin.math.roundToInt
import android.graphics.Bitmap
import android.widget.ImageView
import kotlinx.coroutines.launch
import kotlinx.coroutines.MainScope
import android.content.res.Resources
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.switchmaterial.SwitchMaterial
import mx.ipn.cic.geo.bitmaps_corutinas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
   private lateinit var binding: ActivityMainBinding
   private var buttonDrawBitmap: Button? = null
   private var imageViewBitmap: ImageView? = null

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView(R.layout.activity_main)

      initBindingView()
      initButtonListener()

      buttonDrawBitmap = findViewById(R.id.btn_corutina)
      imageViewBitmap = findViewById(R.id.imageViewBitmap)

   }

   private fun initBindingView() {
      binding = ActivityMainBinding.inflate(layoutInflater)
      val view = binding.root
      setContentView(view)

      // Ocultar el ActionBar.
      this.supportActionBar?.hide()
   }
    private fun loadBitmap() {
        val randomNumber = (0..9).random()
        when(randomNumber){
            0 -> imageViewBitmap?.setImageBitmap(decodeSampledBitmapFromResource(resources, R.drawable.picture_0, imageViewBitmap!!.width,imageViewBitmap!!.height))
            1 -> imageViewBitmap?.setImageBitmap(decodeSampledBitmapFromResource(resources, R.drawable.picture_1, imageViewBitmap!!.width,imageViewBitmap!!.height))
            2 -> imageViewBitmap?.setImageBitmap(decodeSampledBitmapFromResource(resources, R.drawable.picture_2, imageViewBitmap!!.width,imageViewBitmap!!.height))
            3 -> imageViewBitmap?.setImageBitmap(decodeSampledBitmapFromResource(resources, R.drawable.picture_3, imageViewBitmap!!.width,imageViewBitmap!!.height))
            4 -> imageViewBitmap?.setImageBitmap(decodeSampledBitmapFromResource(resources, R.drawable.picture_4, imageViewBitmap!!.width,imageViewBitmap!!.height))
            5 -> imageViewBitmap?.setImageBitmap(decodeSampledBitmapFromResource(resources, R.drawable.picture_5, imageViewBitmap!!.width,imageViewBitmap!!.height))
            6 -> imageViewBitmap?.setImageBitmap(decodeSampledBitmapFromResource(resources, R.drawable.picture_6, imageViewBitmap!!.width,imageViewBitmap!!.height))
            7 -> imageViewBitmap?.setImageBitmap(decodeSampledBitmapFromResource(resources, R.drawable.picture_7, imageViewBitmap!!.width,imageViewBitmap!!.height))
            8 -> imageViewBitmap?.setImageBitmap(decodeSampledBitmapFromResource(resources, R.drawable.picture_8, imageViewBitmap!!.width,imageViewBitmap!!.height))
            9 -> imageViewBitmap?.setImageBitmap(decodeSampledBitmapFromResource(resources, R.drawable.picture_9, imageViewBitmap!!.width,imageViewBitmap!!.height))
            else -> "No options"
        }
    }

   private fun initButtonListener() {
      // Recuperar la referencia al widget usando vinculación de vistas.
      binding.btnCorutina.setOnClickListener {
         MainScope().launch {
            Toast.makeText(applicationContext, "Subrutina iniciada...", Toast.LENGTH_SHORT).show()
            loadBitmap()
            //delay(3000)
            //Toast.makeText(applicationContext, "Subrutina terminada...", Toast.LENGTH_SHORT).show()
         }
      }
   }

   private fun switch(randomNumber: Int, function: () -> Nothing) {

   }

   /*private fun loadBitmap (){
      // Agregar el código para el listener cuando hace click sobre el botón.
      val buttonListen = decodeSampledBitmapFromResource( resources,
               R.drawable.forest_restoration_map_small,
               imageViewBitmap!!.width,
               imageViewBitmap!!.height
         )
   }*/
   private fun calculateInSampleSize(options: BitmapFactory.Options, reqWidth: Int, reqHeight: Int): Int {
      // Raw height and width of image
      val height = options.outHeight
      val width = options.outWidth
      var inSampleSize = 1

      // Tamaño original del mapa de bits (width 3300 x height 1650).
      // Tamaño deseado (reqWidth 330 x reqHeight 200).
      if (height > reqHeight || width > reqWidth) {

         // Calculate ratios of height and width to requested height and width
         // piso (floor) , techo (cell).
         val heightRatio = (height.toFloat() / reqHeight.toFloat()).roundToInt() // Res: 8
         val widthRatio = (width.toFloat() / reqWidth.toFloat()).roundToInt() // Res: 10.

         // Choose the smallest ratio as inSampleSize value, this will guarantee
         // a final image with both dimensions larger than or equal to the
         // requested height and width.
         inSampleSize = max(heightRatio, widthRatio) // 8
      }
      return inSampleSize
   }

   private fun decodeSampledBitmapFromResource(res: Resources?, resId: Int,
                                               reqWidth: Int, reqHeight: Int): Bitmap? {
      // First decode with inJustDecodeBounds=true to check dimensions
      val options = BitmapFactory.Options()
      // De forma predeterminada el valor predeterminado es false. (default value is false).
      options.inJustDecodeBounds = true
      BitmapFactory.decodeResource(res, resId, options)

      // Calculate inSampleSize
      options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight)

      // Decode bitmap with inSampleSize set
      options.inJustDecodeBounds = false
      return BitmapFactory.decodeResource(res, resId, options)
   }
}