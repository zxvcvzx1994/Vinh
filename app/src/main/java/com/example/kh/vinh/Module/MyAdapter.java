package com.example.kh.vinh.Module;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.kh.vinh.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.ArrayList;

/**
 * Created by kh on 4/20/2017.
 */

public class MyAdapter  extends ArrayAdapter<Movies>{
    private Context context;
    private int resource;
   private ImageLoader imageLoader;
    private LayoutInflater inflater;
    private ArrayList<Movies> arrayList = new ArrayList<Movies>();
    public MyAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<Movies> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.arrayList = objects;
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Nullable
    @Override
    public Movies getItem(int position) {
        return arrayList.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v  = convertView;
         ViewHolder holder = null;
        if(v == null){
            v = inflater.inflate(resource,null);
            holder = new ViewHolder();

          //  v.setTag(holder);

            holder.txtmovie = (TextView) v.findViewById(R.id.txtmovie);
            holder.txtyear = (TextView) v.findViewById(R.id.txtyear);
            holder.ratingBar = (RatingBar) v.findViewById(R.id.rating);
            holder.txtduration= (TextView) v.findViewById(R.id.txtduration);
            holder.txttagline= (TextView) v.findViewById(R.id.txttagline);
            holder.txtdirector=(TextView) v.findViewById(R.id.txtdirector);
            holder.txtstory = (TextView) v.findViewById(R.id.txtstory);
            holder.txtcast =(TextView) v.findViewById(R.id.txtcast);
            holder.image = (ImageView) v.findViewById(R.id.img);
            v.setTag(holder);

        }
        else
          holder= (ViewHolder) v.getTag();
       final ProgressBar progressbar = (ProgressBar) v.findViewById(R.id.progress);
        holder.ratingBar.setRating((float)arrayList.get(position).getRating());
        holder.txtduration.setText(arrayList.get(position).getDuration());
        holder.txttagline.setText(arrayList.get(position).getTagline());
        holder.txtdirector.setText(arrayList.get(position).getDirector());
        holder.txtstory.setText(arrayList.get(position).getStory());

        holder.txtmovie.setText(arrayList.get(position).getMovie());
        holder.txtyear.setText(String.valueOf(arrayList.get(position).getYear()));

        StringBuffer cast = new StringBuffer();

       for(int i  = 0 ; i<arrayList.get(position).getCast().size();i++){
           cast.append( arrayList.get(position).getCast().get(i).getName()+", ");
       }
        String cast1 =  cast.toString();
        String cast2 = cast1.substring(0,cast1.length()-2)+".";
        holder.txtcast.setText(cast2);
        holder.txtimg  = arrayList.get(position).getImage();


        imageLoader= ImageLoader.getInstance();


         ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                 .threadPriority(Thread.NORM_PRIORITY - 2)
                .memoryCacheSize(20 * 1024 * 1024) // 20 Mb
                .diskCacheSize(20 * 1024 * 1024) //20 Mb
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .threadPoolSize(30)
                .build();
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .resetViewBeforeLoading(false)  // default
                .delayBeforeLoading(0)
                .cacheInMemory(false) // default
                .cacheOnDisk(true) // default
                .build();
     //   imageLoader.init(ImageLoaderConfiguration.createDefault(context));

            imageLoader.init(config);
            imageLoader.displayImage(holder.txtimg, holder.image, options, new ImageLoadingListener() {
                @Override
                public void onLoadingStarted(String s, View view) {
                   progressbar.setVisibility(view.VISIBLE);
                }

                @Override
                public void onLoadingFailed(String s, View view, FailReason failReason) {
                 progressbar.setVisibility(view.GONE);
                }

                @Override
                public void onLoadingComplete(String s, View view, Bitmap bitmap) {
                    progressbar.setVisibility(view.GONE);
                }

                @Override
                public void onLoadingCancelled(String s, View view) {
                  progressbar.setVisibility(view.GONE);
                }
            });
        return v;
    }
      class  ViewHolder{
        private  TextView txtmovie;
        private  TextView txtyear;
        private   RatingBar ratingBar;
        private TextView txtduration;
        private TextView txttagline;
        private String txtimg;
        private TextView txtdirector;
        private ImageView image;
        private TextView txtstory;
        private TextView txtcast;

    }


}
