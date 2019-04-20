package cs.hku.hk.whenwhere.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import cs.hku.hk.whenwhere.R;
import cs.hku.hk.whenwhere.model.Activities;
import cs.hku.hk.whenwhere.model.Events;

public class Events_recycle extends RecyclerView.Adapter<Events_recycle.UserViewHolder> {
    private List<Events> listEvents;
    public Events_recycle(List<Events> listEvents) {
        this.listEvents = listEvents;
    }
    @Override
    public Events_recycle.UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflating recycler item view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_calendar_recycle, parent, false);

        return new Events_recycle.UserViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(Events_recycle.UserViewHolder holder, int position) {
        holder.textViewName.setText(listEvents.get(position).getDiscription());
      //  holder.textViewTime.setText(listActivities.get(position).getTime());
      //  holder.textViewPlace.setText(listActivities.get(position).getPlace());
    }

    @Override
    public int getItemCount() {
        Log.v(Activity_recycle.class.getSimpleName(),""+listEvents.size());
        return listEvents.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewName;
        public TextView textViewTime;
        public TextView textViewPlace;

        public UserViewHolder(View view) {
            super(view);
            textViewName = view.findViewById(R.id.get_discription);

        }
    }
}
