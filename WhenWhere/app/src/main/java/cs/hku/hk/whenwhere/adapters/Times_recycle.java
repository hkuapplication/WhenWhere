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
import cs.hku.hk.whenwhere.model.Times;

public class Times_recycle extends RecyclerView.Adapter<Times_recycle.UserViewHolder> {
    private List<Times> listTimes;
    public Times_recycle(List<Times> listTimes) {
        this.listTimes = listTimes;
    }
    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflating recycler item view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_time_recycle, parent, false);

        return new UserViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(Times_recycle.UserViewHolder holder, int position) {
        holder.textViewDate.setText(listTimes.get(position).getDate());
        holder.textViewTime.setText(listTimes.get(position).getTime());
        holder.textViewMember.setText(listTimes.get(position).getMember());
    }

    @Override
    public int getItemCount() {
        Log.v(Activity_recycle.class.getSimpleName(),""+listTimes.size());
        return listTimes.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewDate;
        public TextView textViewTime;
        public TextView textViewMember;

        public UserViewHolder(View view) {
            super(view);
            textViewDate = view.findViewById(R.id.textViewDate);
            textViewTime = view.findViewById(R.id.textViewTime);
            textViewMember = view.findViewById(R.id.textViewMember);
        }
    }
}
