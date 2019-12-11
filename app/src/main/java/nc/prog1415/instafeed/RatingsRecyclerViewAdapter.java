package nc.prog1415.instafeed;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import nc.sharedInstafeedClasses.Business;

public class RatingsRecyclerViewAdapter extends RecyclerView.Adapter<RatingsRecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "RatingsViewAdapter";

    private ArrayList<Business> businessArray = new ArrayList<>();
    private Context mContext;

    public RatingsRecyclerViewAdapter(Context mContext,ArrayList<Business> _businessArray) {
        this.businessArray = _businessArray;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_ratings, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        holder.businessName.setText(businessArray.get(position).BusinessName);

    }

    @Override
    public int getItemCount() {
        return businessArray.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView businessName;
        RatingBar ratingBar;
        LinearLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            businessName = itemView.findViewById(R.id.ratedBusinessName);
            ratingBar = itemView.findViewById(R.id.ratingBarRating);
            parentLayout = itemView.findViewById(R.id.ratings_parent_layout);
        }
    }
}
