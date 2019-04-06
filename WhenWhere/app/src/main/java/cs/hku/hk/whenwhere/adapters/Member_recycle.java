package cs.hku.hk.whenwhere.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import cs.hku.hk.whenwhere.R;
import cs.hku.hk.whenwhere.model.Member;

public class Member_recycle extends RecyclerView.Adapter<Member_recycle.UserViewHolder> {
    private List<Member> listMembers;
    public Member_recycle(List<Member> listMembers) {
        this.listMembers = listMembers;
    }
    @Override
    public Member_recycle.UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflating recycler item view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user_recycle, parent, false);

        return new Member_recycle.UserViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(Member_recycle.UserViewHolder holder, int position) {
        holder.textViewName.setText(listMembers.get(position).getName());
       // holder.textViewTime.setText(listActivities.get(position).getTime());
       // holder.textViewPlace.setText(listActivities.get(position).getPlace());
    }
    @Override
    public int getItemCount() {
        Log.v(Activity_recycle.class.getSimpleName(),""+listMembers.size());
        return listMembers.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewName;
      //  public TextView textViewTime;
      //  public TextView textViewPlace;

        public UserViewHolder(View view) {
            super(view);
            textViewName = view.findViewById(R.id.get_name);
        //    textViewTime = view.findViewById(R.id.get_time);
        //    textViewPlace = view.findViewById(R.id.get_place);
        }
    }
}
