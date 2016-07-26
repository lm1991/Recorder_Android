package cn.mesor.recorder.message;


import android.Manifest;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.content.PermissionChecker;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.mesor.recorder.R;
import cn.mesor.recorder.message.view.DragImageView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GalleryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GalleryFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    @BindView(R.id.imageView)
    DragImageView imageView;
    @BindView(R.id.gridView)
    RecyclerView gridView;

    private List<String> showUriList;
    private String currentPath;

    public String getCurrentPath() {
        return currentPath;
    }

    public static GalleryFragment newInstance() {
        GalleryFragment fragment = new GalleryFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);
        ButterKnife.bind(this, view);
        imageView.post(new Runnable() {
            @Override
            public void run() {
                imageView.setScreen_W(imageView.getWidth());
                imageView.setScreen_H(imageView.getHeight());
                imageView.setmActivity(getActivity());
                imageView.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.photo));
            }
        });

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M && PermissionChecker.checkSelfPermission(getActivity(), Manifest.permission
                .WRITE_EXTERNAL_STORAGE) != PermissionChecker.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        } else {
            initData();
        }
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 0:
                if (PermissionChecker.checkSelfPermission(getActivity(), Manifest.permission
                        .WRITE_EXTERNAL_STORAGE) == PermissionChecker.PERMISSION_GRANTED) {
                    initData();
                } else {

                }
                break;
        }
    }

    private void initData() {
        showUriList = new ArrayList<>();
        new AsyncTask<Void, Void, Void>() {

            protected void onPreExecute() {

            }

            @Override
            protected Void doInBackground(Void... params) {
                try {
                    final String[] columns = {MediaStore.Images.Media.DATA, MediaStore.Images.Media._ID};
                    final String orderBy = MediaStore.Images.Media._ID;

                    Cursor imagecursor = getActivity().getContentResolver().query(
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI, columns, null, null, orderBy);

                    if (imagecursor != null && imagecursor.getCount() > 0) {

                        while (imagecursor.moveToNext()) {
                            int dataColumnIndex = imagecursor.getColumnIndex(MediaStore.Images.Media.DATA);

                            String sdcardPath = imagecursor.getString(dataColumnIndex);

                            showUriList.add(sdcardPath);
//                                String dirPath = sdcardPath.replaceAll("[^/]*$", "");
//                                InfoDir infoDir = null;
//                                for (InfoDir info : dirMap) {
//                                    if (info.pathString.equals(dirPath)) {
//                                        infoDir = info;
//                                        infoDir.fileCount++;
//                                        break;
//                                    }
//                                }
//                                if (infoDir == null) {
//                                    infoDir = new InfoDir();
//                                    infoDir.firstFilePath = sdcardPath;
//                                    infoDir.pathString = dirPath;
//                                    infoDir.nameString = dirPath.substring(0, dirPath.length() - 1);
//                                    infoDir.nameString = infoDir.nameString.replaceAll("^.*/", "");
//                                    infoDir.fileCount++;
//                                    dirMap.add(infoDir);
//                                }
//
                        }
//                            InfoDir infoDir = new InfoDir();
//                            infoDir.firstFilePath = imgUriList.get(0);
//                            infoDir.pathString = infoDir.firstFilePath.replaceAll("[^/]*$", "");
//                            infoDir.nameString = "所有图片";
//                            infoDir.fileCount = imgUriList.size();
//                            infoDir.isShowing = true;
//                            dirMap.add(0, infoDir);
//                            Collections.sort(dirMap);
//                            currentDirString = infoDir.pathString;
//                            showUriList = new ArrayList<String>();
//                            showUriList.addAll(imgUriList);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

            protected void onPostExecute(Void result) {
//                    hideProgress();
                if (showUriList == null) {
                    showUriList = new ArrayList<>();
                }
                ImagePickAdapter adapter = new ImagePickAdapter(GalleryFragment.this, showUriList);
                gridView.setLayoutManager(new GridLayoutManager(getActivity(), 4));
                gridView.addItemDecoration(new RecyclerView.ItemDecoration() {

                    @Override
                    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                        super.onDraw(c, parent, state);
                    }

                    private int getSpanCount(RecyclerView parent) {
                        // 列数
                        int spanCount = -1;
                        GridLayoutManager layoutManager = (GridLayoutManager) parent.getLayoutManager();
                        spanCount = layoutManager.getSpanCount();
                        return spanCount;
                    }

                    @Override
                    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                        int spanCount = getSpanCount(parent);
                        int childCount = parent.getAdapter().getItemCount();
                        int itemPosition = parent.indexOfChild(view);
                        if (isLastRaw(itemPosition, spanCount, childCount))// 如果是最后一行，则不需要绘制底部
                        {
                            outRect.set(0, 0, (int) getResources().getDimension(R.dimen.px_7), 0);
                        } else if (isLastColum(itemPosition, spanCount, childCount))// 如果是最后一列，则不需要绘制右边
                        {
                            outRect.set(0, 0, 0, (int) getResources().getDimension(R.dimen.px_7));
                        } else {
                            outRect.set(0, 0, (int) getResources().getDimension(R.dimen.px_7),
                                    (int) getResources().getDimension(R.dimen.px_7));
                        }
                    }

                    private boolean isLastColum(int itemPosition, int spanCount, int childCount) {
                        if ((itemPosition + 1) % spanCount == 0)// 如果是最后一列，则不需要绘制右边
                        {
                            return true;
                        }
                        return false;
                    }

                    private boolean isLastRaw(int itemPosition, int spanCount, int childCount) {
                        childCount = childCount - childCount % spanCount;
                        if (itemPosition >= childCount)// 如果是最后一行，则不需要绘制底部
                            return true;
                        return false;
                    }

                });
                gridView.setAdapter(adapter);
            }

        }.execute();

    }

    class ImagePickAdapter extends RecyclerView.Adapter<ImagePickAdapter.ViewHolder> {

        private final Fragment context;
        private final List<String> list;

        private final float size;

        public ImagePickAdapter(Fragment context, List<String> list) {
            this.context = context;
            this.list = list;
            size = (getResources().getDisplayMetrics().widthPixels - getResources().getDimension(R.dimen.px_7) * 2) / 4;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            ViewHolder holder = new ViewHolder(LayoutInflater.from(context.getContext()).inflate(R.layout.item_gallery, parent, false));
            holder.parentView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    currentPath = v.getTag().toString();
                    Glide.with(context).load(currentPath).into(imageView);
                    imageView.setParam(imageView.getWidth(), imageView.getHeight());
                }
            });
            return holder;
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            holder.parentView.setTag(list.get(position));
            Glide.with(context).load(list.get(position)).into(holder.imageView);
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            ImageView imageView;
            View parentView;

            public ViewHolder(View itemView) {
                super(itemView);
                parentView = itemView;
                imageView = (ImageView) itemView.findViewById(R.id.imageView);
                itemView.setLayoutParams(new ViewGroup.LayoutParams((int) size, (int) (size)));
            }
        }
    }

}
