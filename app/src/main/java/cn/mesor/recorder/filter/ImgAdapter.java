package cn.mesor.recorder.filter;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.LinkedHashMap;

import cn.mesor.photofilter.ImageProcessingConstants;
import cn.mesor.photofilter.ImageProcessor;
import cn.mesor.recorder.R;

/**
 * Created by Limeng on 2016/7/22.
 */
public class ImgAdapter extends RecyclerView.Adapter<ImgAdapter.ViewHolder> {

    private final Context context;
    private ImageProcessor mImageProcessor;
    private int itemBackground;
    private OnItemClickListener listener;
    private LinkedHashMap<Integer, Bitmap> map;

    public ImgAdapter(Context context, ImageProcessor mImageProcessor, OnItemClickListener listener) {
        this.context = context;
        this.mImageProcessor = mImageProcessor;
        TypedArray a = context.obtainStyledAttributes(R.styleable.MyGallery);
        itemBackground = a.getResourceId(R.styleable.MyGallery_android_galleryItemBackground, 0);
        a.recycle();
        this.listener = listener;
        map = new LinkedHashMap<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_photo_filter, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.imageView.setImageBitmap(getBitmap(position));
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.OnClick(getBitmap(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return 48;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
//            imageView.setLayoutParams(new ViewGroup.LayoutParams(200, 200));
            imageView.setBackgroundResource(itemBackground);
        }
    }

    public interface OnItemClickListener {
        void OnClick(Bitmap bitmap);
    }

    public Bitmap getBitmap(int position) {
        if(map.get(position) != null)
            return map.get(position);
        Bitmap skullBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.photo);
        switch (position) {
            case 0:
                skullBitmap = mImageProcessor.doHighlightImage(skullBitmap, 15, Color.RED);
                break;
            case 1:
                skullBitmap = mImageProcessor.doInvert(skullBitmap);
                break;
            case 2:
                skullBitmap = mImageProcessor.doGreyScale(skullBitmap);
                break;
            case 3:
                skullBitmap = mImageProcessor.doGamma(skullBitmap, 0.6, 0.6, 0.6);
                break;
            case 4:
                skullBitmap = mImageProcessor.doGamma(skullBitmap, 1.8, 1.8, 1.8);
                break;
            case 5:
                skullBitmap = mImageProcessor.doColorFilter(skullBitmap, 1, 0, 0);
                break;
            case 6:
                skullBitmap = mImageProcessor.doColorFilter(skullBitmap, 0, 1, 0);
                break;
            case 7:
                skullBitmap = mImageProcessor.doColorFilter(skullBitmap, 0, 0, 1);
                break;
            case 8:
                skullBitmap = mImageProcessor.doColorFilter(skullBitmap, 0.5, 0.5, 0.5);
                break;
            case 9:
                skullBitmap = mImageProcessor.doColorFilter(skullBitmap, 1.5, 1.5, 1.5);
                break;
            case 10:
                skullBitmap = mImageProcessor.createSepiaToningEffect(skullBitmap, 150, 0.7, 0.3, 0.12);
                break;
            case 11:
                skullBitmap = mImageProcessor.createSepiaToningEffect(skullBitmap, 150, 0.8, 0.2, 0);
                break;
            case 12:
                skullBitmap = mImageProcessor.createSepiaToningEffect(skullBitmap, 150, 0.12, 0.7, 0.3);
                break;
            case 13:
                skullBitmap = mImageProcessor.createSepiaToningEffect(skullBitmap, 150, 0.12, 0.3, 0.7);
                break;
            case 14:
                skullBitmap = mImageProcessor.decreaseColorDepth(skullBitmap, 32);
                break;
            case 15:
                skullBitmap = mImageProcessor.decreaseColorDepth(skullBitmap, 64);
                break;
            case 16:
                skullBitmap = mImageProcessor.decreaseColorDepth(skullBitmap, 128);
                break;
            case 17:
                skullBitmap = mImageProcessor.createContrast(skullBitmap, 50);
                break;
            case 18:
                skullBitmap = mImageProcessor.createContrast(skullBitmap, 100);
                break;
            case 19:
                skullBitmap = mImageProcessor.rotate(skullBitmap, 40);
                break;
            case 20:
                skullBitmap = mImageProcessor.rotate(skullBitmap, 340);
                break;
            case 21:
                skullBitmap = mImageProcessor.doBrightness(skullBitmap, -60);
                break;
            case 22:
                skullBitmap = mImageProcessor.doBrightness(skullBitmap, 30);
                break;
            case 23:
                skullBitmap = mImageProcessor.doBrightness(skullBitmap, 80);
                break;
            case 24:
                skullBitmap = mImageProcessor.applyGaussianBlur(skullBitmap);
                break;
            case 25:
                skullBitmap = mImageProcessor.createShadow(skullBitmap);
                break;
            case 26:
                skullBitmap = mImageProcessor.sharpen(skullBitmap, 11);
                break;
            case 27:
                skullBitmap = mImageProcessor.applyMeanRemoval(skullBitmap);
                break;
            case 28:
                skullBitmap = mImageProcessor.smooth(skullBitmap, 100);
                break;
            case 29:
                skullBitmap = mImageProcessor.emboss(skullBitmap);
                break;
            case 30:
                skullBitmap = mImageProcessor.engrave(skullBitmap);
                break;
            case 31:
                skullBitmap = mImageProcessor.boost(skullBitmap, ImageProcessingConstants.RED, 1.5);
                break;
            case 32:
                skullBitmap = mImageProcessor.boost(skullBitmap, ImageProcessingConstants.GREEN, 0.5);
                break;
            case 33:
                skullBitmap = mImageProcessor.boost(skullBitmap, ImageProcessingConstants.BLUE, 0.67);
                break;
            case 34:
                skullBitmap = mImageProcessor.roundCorner(skullBitmap, 45);
                break;
            case 35:
                skullBitmap = mImageProcessor.flip(skullBitmap, ImageProcessingConstants.FLIP_VERTICAL);
                break;
            case 36:
                skullBitmap = mImageProcessor.tintImage(skullBitmap, 50);
                break;
            case 37:
                skullBitmap = mImageProcessor.replaceColor(skullBitmap, Color.BLACK, Color.BLUE);
                break;
            case 38:
                skullBitmap = mImageProcessor.applyFleaEffect(skullBitmap);
                break;
            case 39:
                skullBitmap = mImageProcessor.applyBlackFilter(skullBitmap);
                break;
            case 40:
                skullBitmap = mImageProcessor.applySnowEffect(skullBitmap);
                break;
            case 41:
                skullBitmap = mImageProcessor.applyShadingFilter(skullBitmap, Color.MAGENTA);
                break;
            case 42:
                skullBitmap = mImageProcessor.applyShadingFilter(skullBitmap, Color.BLUE);
                break;
            case 43:
                skullBitmap = mImageProcessor.applySaturationFilter(skullBitmap, 1);
                break;
            case 44:
                skullBitmap = mImageProcessor.applySaturationFilter(skullBitmap, 5);
                break;
            case 45:
                skullBitmap = mImageProcessor.applyHueFilter(skullBitmap, 1);
                break;
            case 46:
                skullBitmap = mImageProcessor.applyHueFilter(skullBitmap, 5);
                break;
            case 47:
                skullBitmap = mImageProcessor.applyReflection(skullBitmap);
                break;
        }
        map.put(position, skullBitmap);
        return skullBitmap;
    }
}
