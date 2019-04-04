package cs.hku.hk.whenwhere.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cs.hku.hk.whenwhere.R;
import cs.hku.hk.whenwhere.model.Activities;
import java.util.List;

public class Activity_recycle extends RecyclerView.Adapter<Activity_recycle.UserViewHolder> {
    private List<Activities> listActivities;
    public Activity_recycle(List<Activities> listActivities) {
        this.listActivities = listActivities;
    }
    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflating recycler item view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_recycle, parent, false);

        return new UserViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        holder.textViewName.setText(listActivities.get(position).getName());
        holder.textViewTime.setText(listActivities.get(position).getTime());
        holder.textViewPlace.setText(listActivities.get(position).getPlace());
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
