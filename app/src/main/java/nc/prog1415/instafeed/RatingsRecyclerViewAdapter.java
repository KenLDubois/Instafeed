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
import nc.sharedInstafeedClasses.Rating;

public class RatingsRecyclerViewAdapter extends RecyclerView.Adapter<RatingsRecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "RatingsViewAdapter";

    private ArrayList<Rating> ratingArray;

    private Context mContext;

    public RatingsRecyclerViewAdapter(Context mContext,ArrayList<Rating> _ratingArray) {
        this.ratingArray = _ratingArray;
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

        holder.businessName.setText(ratingArray.get(position).RatedBusiness.BusinessName);
        holder.address.setText(ratingArray.get(position).RatedBusiness.Address);
        holder.ratingBar.setRating(ratingArray.get(position).StarRating);
        holder.ratingTitle.setText(ratingArray.get(position).Title);
        holder.ratingDescript.setText(ratingArray.get(position).Description);

    }

    @Override
    public int getItemCount() {
        return ratingArray.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView businessName;
        RatingBar ratingBar;
        LinearLayout parentLayout;
        TextView address;
        TextView ratingTitle;
        TextView ratingDescript;

        public ViewHolder(View itemView) {
            super(itemView);

            businessName = itemView.findViewById(R.id.ratedBusinessName);
            ratingBar = itemView.findViewById(R.id.ratingBarRating);
            parentLayout = itemView.findViewById(R.id.ratings_parent_layout);
            address = itemView.findViewById(R.id.ratedBusinessAddress);
            ratingTitle = itemView.findViewById(R.id.txtRatingTitle);
            ratingDescript = itemView.findViewById(R.id.txtReviewDescription);
        }
    }
}
