package cs.hku.hk.whenwhere.adapters;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.maps.MapFragment;

import cs.hku.hk.whenwhere.R;
import cs.hku.hk.whenwhere.model.Activities;
import cs.hku.hk.whenwhere.utils.InnerNavigationController;

import java.util.List;

public class Activity_recycle extends RecyclerView.Adapter<Activity_recycle.UserViewHolder> {
    private List<Activities> listActivities;
    private ImageButton button;
    public Activity_recycle(List<Activities> listActivities) {
        this.listActivities = listActivities;
    }
    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflating recycler item view
        final View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_recycle, parent, false);
       // button=itemView.findViewById(R.id.imageButton);

       // button.setOnitemClickListener(new View.setOnitemClickListener() {
          //  @Override
         //   public void onClick(View v) {
         //       Intent intent=new Intent(itemView.getContext(), InnerNavigationController.class);
         //       itemView.getContext().startActivity(intent);
         //   }
      //  });
        return new UserViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(final UserViewHolder holder, int position) {
        final int layoutPosition = holder.getLayoutPosition ();
        //必须在itemView的点击事件里面判断
        holder.itemView.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {

                if(onItemClickListener!=null){
                    onItemClickListener.onItemClickListener (layoutPosition);
                }

            }
        });
        holder.textViewName.setText(listActivities.get(position).getName());
        holder.textViewTime.setText(listActivities.get(position).getTime());
        holder.textViewPlace.setText(listActivities.get(position).getPlace());
    }
    private OnItemClickListener onItemClickListener;
    //创建接口
    public interface OnItemClickListener{
        void onItemClickListener(int position);
    }
    //外界调用的方法
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
    @Override
    public int getItemCount() {
        Log.v(Activity_recycle.class.getSimpleName(),""+listActivities.size());
        return listActivities.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewName;
        public TextView textViewTime;
        public TextView textViewPlace;

        public UserViewHolder(View view) {
            super(view);
            textViewName = view.findViewById(R.id.get_name);
            textViewTime = view.findViewById(R.id.get_time);
            textViewPlace = view.findViewById(R.id.get_place);
        }
    }
}
