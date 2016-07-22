package cn.mesor.recorder.home.adapter;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import cn.mesor.recorder.R;
import cn.mesor.recorder.home.view.PinnedSectionListView;
import cn.mesor.recorder.info.InfoMessage;

/**
 * Created by Limeng on 2016/7/22.
 */
public class HomeAdapter extends BaseAdapter implements PinnedSectionListView.PinnedSectionListAdapter {

    private final Fragment fragment;
    private List<InfoMessage> contentList;

    public HomeAdapter(Fragment fragment) {
        this.fragment = fragment;
        contentList = new ArrayList<>();
    }

    public void setContentList(List<InfoMessage> list) {
        contentList.clear();
        if (list != null) {
            contentList.addAll(list);
        }
        notifyDataSetChanged();
    }

    @Override
    public boolean isItemViewTypePinned(int viewType) {
        return viewType == 0;
    }

    @Override
    public int getItemViewType(int position) {
        return position % 2;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getCount() {
        return contentList.size();
    }

    @Override
    public Object getItem(int position) {
        return contentList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        InfoMessage message = contentList.get(position);
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(fragment.getActivity()).inflate(R.layout.item_home, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (position % 2 == 0) {
            holder.userNameTv.setText(message.userName);
            Glide.with(fragment).load(message.avatar).into(holder.avatarImageView);
            holder.sectionV.setVisibility(View.VISIBLE);
            holder.contentV.setVisibility(View.GONE);
        } else {
            Glide.with(fragment).load(message.imagePath).into(holder.imageView);
            holder.sectionV.setVisibility(View.GONE);
            holder.contentV.setVisibility(View.VISIBLE);
        }
        return convertView;
    }

    public class ViewHolder {
        public View sectionV, contentV;
        public ImageView imageView;
        public TextView userNameTv;
        public ImageView avatarImageView;

        public ViewHolder(View v) {
            sectionV = v.findViewById(R.id.sectionV);
            contentV = v.findViewById(R.id.contentV);
            imageView = (ImageView) v.findViewById(R.id.contentImage);
            userNameTv = (TextView) v.findViewById(R.id.userNameTv);
            avatarImageView = (ImageView) v.findViewById(R.id.avatarImageView);
        }
    }
}
