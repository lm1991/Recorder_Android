package cn.mesor.recorder.home;

import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import cn.mesor.recorder.R;
import cn.mesor.recorder.detail.DetailActivity;

/**
 * Created by Limeng on 2016/7/20.
 */
public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private final Fragment fragment;
    private List<String> pathList;

    public ListAdapter(Fragment fragment){
        this.fragment = fragment;
        pathList = new ArrayList<>();
    }

    public void setContent(List<String> list){
        this.pathList.clear();
        if(list != null){
            this.pathList.addAll(list);
        }
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(fragment.getActivity()).inflate(R.layout.item_home, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Glide.with(fragment).load(pathList.get(position)).into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(fragment.getActivity(), DetailActivity.class);
                intent.putExtra("path", pathList.get(position));
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(fragment.getActivity(), holder.imageView, "content_image");
                ActivityCompat.startActivity(fragment.getActivity(), intent, options.toBundle());
            }
        });
    }

    @Override
    public int getItemCount() {
        return pathList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.contentImage);

        }
    }
}
