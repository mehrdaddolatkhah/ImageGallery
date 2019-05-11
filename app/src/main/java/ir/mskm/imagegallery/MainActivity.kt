package ir.mskm.imagegallery

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import ir.mskm.mskmimagegallery.Data.Models.EnumViews
import ir.mskm.mskmimagegallery.Data.Models.ImageGalleryModel
import ir.mskm.mskmimagegallery.GalleryWorker
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var imagesOb: ArrayList<ImageGalleryModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        initImages()
        val ops = GalleryWorker(this)
        ops.images = imagesOb

        val btnDialog = findViewById<Button>(R.id.btn_dialog)
        val btnLinear = findViewById<Button>(R.id.btn_linear)
        val btnFull = findViewById<Button>(R.id.btn_full)

        btnDialog.setOnClickListener {
            ops.openDialogView(this, EnumViews.DIALOGVIEW)
        }

        btnLinear.setOnClickListener {
            ops.openDialogView(this, EnumViews.LINEARVIEW)
        }

        btnFull.setOnClickListener {
            ops.openFullView(3)
        }


    }


    private fun initImages() {


        val items = arrayOf(
            "https://www.planwallpaper.com/static/images/3D-HD-Wallpaper-03-Dekstop.jpg",
            "https://www.planwallpaper.com/static/images/3d-nature-800x600.jpg",
            "https://www.planwallpaper.com/static/images/3d-butterfly-wallpaper-for-desktop-unique-nature-hd-wallpapers.jpg",
            "https://www.planwallpaper.com/static/images/3d-nature-wallpaper-wide-n5a2h.jpg",
            "https://www.planwallpaper.com/static/images/3D-Wallpapers-Green-Nature-Abstract.jpg",
            "https://cdn.asiatatler.com/asiatatler/ph/i/2018/10/story/45412/08143805-000_1944oa.dd9c0033203.original-c08144036-1584x780.jpg",
            "http://imagesvc.timeincapp.com/v3/foundry/image/?q=70&w=1440&url=https%3A%2F%2Ftimedotcom.files.wordpress.com%2F2017%2F06%2Fkhadh515.jpg%3Fquality%3D85",
            "https://www-assets0.herokucdn.com/assets/wallpapers/java/thumb-8ca3e635babeebdeeade5e6ae94d36e6cc604eec59c8576fb20acac86a0f0290.png",
            "https://vignette.wikia.nocookie.net/disney/images/4/49/ChickenLittle-0.png/revision/latest?cb=20171126021925",
            "https://dl4success.com/wp-content/uploads/2015/08/1718082_1_b_14301a73.jpg",
            "https://s3.amazonaws.com/creativetim_bucket/products/71/original/opt_mdr_thumbnail.jpg?1517307720",
            "https://www.wallpaper.net.in/file/5636/600x380/16:9/java-logo-wallpaper_16126385.png"
        )

        imagesOb = ArrayList<ImageGalleryModel>(items.size)

        for (i in items.indices) {
            val temp = ImageGalleryModel()
            temp.imagePath = items[i]
            temp.imageTitle = " عنوان $i "
            temp.imageDesc = " توضیحات $i"
            imagesOb.add(temp)
        }

    }

}
