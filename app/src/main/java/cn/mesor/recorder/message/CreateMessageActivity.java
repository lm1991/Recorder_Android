package cn.mesor.recorder.message;

import android.net.Uri;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.mesor.recorder.R;
import jp.co.cyberagent.android.gpuimage.GPUImage;
import jp.co.cyberagent.android.gpuimage.GPUImage3x3ConvolutionFilter;
import jp.co.cyberagent.android.gpuimage.GPUImage3x3TextureSamplingFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageAddBlendFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageAlphaBlendFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageBilateralFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageBoxBlurFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageBrightnessFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageBulgeDistortionFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageCGAColorspaceFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageChromaKeyBlendFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageColorBalanceFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageColorBlendFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageColorBurnBlendFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageColorDodgeBlendFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageColorInvertFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageColorMatrixFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageContrastFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageSepiaFilter;

/**
 * Created by Limeng on 2016/7/25.
 */
public class CreateMessageActivity extends AppCompatActivity {

    @BindView(R.id.glSurfaceView)
    GLSurfaceView glSurfaceView;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private GPUImage mGPUImage;

    private GPUImageFilter[] filters = {
            new GPUImageSepiaFilter(), new GPUImage3x3ConvolutionFilter(), new GPUImage3x3TextureSamplingFilter(),
            new GPUImageAddBlendFilter(), new GPUImageAlphaBlendFilter(),
            new GPUImageBilateralFilter(), new GPUImageBoxBlurFilter(),
            new GPUImageBrightnessFilter(), new GPUImageBulgeDistortionFilter(),
            new GPUImageCGAColorspaceFilter(), new GPUImageChromaKeyBlendFilter(), new GPUImageColorBalanceFilter(),
            new GPUImageColorBlendFilter(), new GPUImageColorBurnBlendFilter(),
            new GPUImageColorDodgeBlendFilter(), new GPUImageColorInvertFilter(),
            new GPUImageColorMatrixFilter(), new GPUImageContrastFilter()
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_message);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mGPUImage = new GPUImage(this);
        mGPUImage.setScaleType(GPUImage.ScaleType.CENTER_INSIDE);
        mGPUImage.setBackgroundColor(.8f, .8f, .8f);
        mGPUImage.setGLSurfaceView(glSurfaceView);
        mGPUImage.setImage(Uri.fromFile(new File(getIntent().getStringExtra("path")))); // this loads image on the current thread, should be run in a thread
//        mGPUImage.setFilter(new GPUImageSepiaFilter());
        recyclerView.setAdapter(new Adapter());
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }

    private class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(CreateMessageActivity.this).inflate(android.R.layout.simple_list_item_1, parent, false));
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.textView.setText(filters[position].getClass().getSimpleName());
            holder.textView.setTag(position);
            holder.textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = Integer.valueOf(v.getTag().toString());
                    mGPUImage.setFilter(filters[position]);
                }
            });
        }

        @Override
        public int getItemCount() {
            return filters.length;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            public TextView textView;

            public ViewHolder(View itemView) {
                super(itemView);
                textView = (TextView) itemView;
            }
        }
    }

}
