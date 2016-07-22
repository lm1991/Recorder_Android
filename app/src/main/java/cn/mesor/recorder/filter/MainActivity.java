package cn.mesor.recorder.filter;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import cn.mesor.photofilter.ImageProcessor;
import cn.mesor.recorder.R;

public class MainActivity extends AppCompatActivity {
    private ImageView mOriginalImageView;
    private RecyclerView mGallery;
    private ImageProcessor mImageProcessor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        initialize();
//        loadBitmaps();
        setAdapterAndListener();
    }

    private void initialize() {
        mOriginalImageView = (ImageView) findViewById(R.id.imageView);
        mGallery = (RecyclerView) findViewById(R.id.gallery);
        mImageProcessor = new ImageProcessor();
    }

    private void loadBitmaps() {
//        Bitmap skullBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.skull);
//        Bitmap carBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.car);
                /*, carBitmap,
                mImageProcessor.doHighlightImage(carBitmap, 15, Color.RED),
                mImageProcessor.doInvert(carBitmap), mImageProcessor.doGreyScale(carBitmap),
                mImageProcessor.doGamma(carBitmap, 0.6, 0.6, 0.6),
                mImageProcessor.doGamma(carBitmap, 1.8, 1.8, 1.8),
                mImageProcessor.doColorFilter(carBitmap, 1, 0, 0),
                mImageProcessor.doColorFilter(carBitmap, 0, 1, 0),
                mImageProcessor.doColorFilter(carBitmap, 0, 0, 1),
                mImageProcessor.doColorFilter(carBitmap, 0.5, 0.5, 0.5),
                mImageProcessor.doColorFilter(carBitmap, 1.5, 1.5, 1.5),
                mImageProcessor.createSepiaToningEffect(carBitmap, 150, 0.7, 0.3, 0.12),
                mImageProcessor.createSepiaToningEffect(carBitmap, 150, 0.8, 0.2, 0),
                mImageProcessor.createSepiaToningEffect(carBitmap, 150, 0.12, 0.7, 0.3),
                mImageProcessor.createSepiaToningEffect(carBitmap, 150, 0.12, 0.3, 0.7),
                mImageProcessor.decreaseColorDepth(carBitmap, 32),
                mImageProcessor.decreaseColorDepth(carBitmap, 64),
                mImageProcessor.decreaseColorDepth(carBitmap, 128),
                mImageProcessor.createContrast(carBitmap, 50),
                mImageProcessor.createContrast(carBitmap, 100), mImageProcessor.rotate(carBitmap, 40),
                mImageProcessor.rotate(carBitmap, 340), mImageProcessor.doBrightness(carBitmap, -60),
                mImageProcessor.doBrightness(carBitmap, 30), mImageProcessor.doBrightness(carBitmap, 80),
                mImageProcessor.applyGaussianBlur(carBitmap), mImageProcessor.createShadow(carBitmap),
                mImageProcessor.sharpen(carBitmap, 11), mImageProcessor.applyMeanRemoval(carBitmap),
                mImageProcessor.smooth(carBitmap, 100), mImageProcessor.emboss(carBitmap),
                mImageProcessor.engrave(carBitmap),
                mImageProcessor.boost(carBitmap, ImageProcessingConstants.RED, 1.5),
                mImageProcessor.boost(carBitmap, ImageProcessingConstants.GREEN, 0.5),
                mImageProcessor.boost(carBitmap, ImageProcessingConstants.BLUE, 0.67),
                mImageProcessor.roundCorner(carBitmap, 45),
                mImageProcessor.flip(carBitmap, ImageProcessingConstants.FLIP_VERTICAL),
                mImageProcessor.tintImage(carBitmap, 50),
                mImageProcessor.replaceColor(carBitmap, Color.BLACK, Color.BLUE),
                mImageProcessor.applyFleaEffect(carBitmap), mImageProcessor.applyBlackFilter(carBitmap),
                mImageProcessor.applySnowEffect(carBitmap),
                mImageProcessor.applyShadingFilter(carBitmap, Color.MAGENTA),
                mImageProcessor.applyShadingFilter(carBitmap, Color.BLUE),
                mImageProcessor.applySaturationFilter(carBitmap, 1),
                mImageProcessor.applySaturationFilter(carBitmap, 5),
                mImageProcessor.applyHueFilter(carBitmap, 1), mImageProcessor.applyHueFilter(carBitmap, 5),
                mImageProcessor.applyReflection(carBitmap)
        };*/
        //   writeToDisk();
    }

    ImgAdapter adapter;
    private void setAdapterAndListener() {
        adapter = new ImgAdapter(this, mImageProcessor, new ImgAdapter.OnItemClickListener() {
            @Override
            public void OnClick(Bitmap bitmap) {
                mOriginalImageView.setImageBitmap(bitmap);
            }
        });
        mGallery.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mGallery.setAdapter(adapter);
//    mGallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//      public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
//        mOriginalImageView.setImageBitmap(mBitmapArray[position]);
//      }
//    });
    }
 /* private void writeToDisk() {
    String[] mBitmapNameArray = new String[] {
        "Skull", "Skull-doHighlightImage", "Skull-doInvert", "Skull-doGreyScale", "Skull-doGamma1",
        "Skull-doGamma2", "Skull-doColorFilter1", "Skull-doColorFilter2", "Skull-doColorFilter3",
        "Skull-doColorFilter4", "Skull-doColorFilter5", "Skull-createSepiaToningEffect1",
        "Skull-createSepiaToningEffect2", "Skull-createSepiaToningEffect3",
        "Skull-createSepiaToningEffect4", "Skull-decreaseColorDepth1", "Skull-decreaseColorDepth2",
        "Skull-decreaseColorDepth3", "Skull-createContrast1", "Skull-createContrast2",
        "Skull-rotate1", "Skull-rotate2", "Skull-doBrightness1", "Skull-doBrightness2",
        "Skull-doBrightness3", "Skull-applyGaussianBlur", "Skull-createShadow", "Skull-sharpen",
        "Skull-applyMeanRemoval", "Skull-smooth", "Skull-emboss", "Skull-engrave", "Skull-boost1",
        "Skull-boost2", "Skull-boost3", "Skull-roundCorner", "Skull-flip", "Skull-tintImage",
        "Skull-replaceColor", "Skull-applyFleaEffect", "Skull-applyBlackFilter",
        "Skull-applySnowEffect", "Skull-applyShadingFilter1", "Skull-applyShadingFilter2",
        "Skull-applySaturationFilter1", "Skull-applySaturationFilter2", "Skull-applyHueFilter1",
        "Skull-applyHueFilter2", "Skull-applyReflection", "Car", "Car-doHighlightImage",
        "Car-doInvert", "Car-doGreyScale", "Car-doGamma1", "Car-doGamma2", "Car-doColorFilter1",
        "Car-doColorFilter2", "Car-doColorFilter3", "Car-doColorFilter4", "Car-doColorFilter5",
        "Car-createSepiaToningEffect1", "Car-createSepiaToningEffect2",
        "Car-createSepiaToningEffect3", "Car-createSepiaToningEffect4", "Car-decreaseColorDepth1",
        "Car-decreaseColorDepth2", "Car-decreaseColorDepth3", "Car-createContrast1",
        "Car-createContrast2", "Car-rotate1", "Car-rotate2", "Car-doBrightness1",
        "Car-doBrightness2", "Car-doBrightness3", "Car-applyGaussianBlur", "Car-createShadow",
        "Car-sharpen", "Car-applyMeanRemoval", "Car-smooth", "Car-emboss", "Car-engrave",
        "Car-boost1", "Car-boost2", "Car-boost3", "Car-roundCorner", "Car-flip", "Car-tintImage",
        "Car-replaceColor", "Car-applyFleaEffect", "Car-applyBlackFilter", "Car-applySnowEffect",
        "Car-applyShadingFilter1", "Car-applyShadingFilter2", "Car-applySaturationFilter1",
        "Car-applySaturationFilter2", "Car-applyHueFilter1", "Car-applyHueFilter2",
        "Car-applyReflection"
    };
    FileOutputStream out = null;
    String path = Environment.getExternalStorageDirectory().toString();
    try {
      for (int i = 0; i < mBitmapArray.length; i++) {
        File file = new File(path, "Images/" + mBitmapNameArray[i] + ".png"); // the File to save to
        out = new FileOutputStream(file);
        mBitmapArray[i].compress(Bitmap.CompressFormat.PNG, 100,
            out); // bmp is your Bitmap instance
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        if (out != null) {
          out.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
*/
}
