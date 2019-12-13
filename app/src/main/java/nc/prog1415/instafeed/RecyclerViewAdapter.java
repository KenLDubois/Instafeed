package nc.prog1415.instafeed;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import static nc.prog1415.instafeed.MainActivity.locationTask;
import static nc.prog1415.instafeed.SplashActivity.connectionTask;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<Business> businessArray = new ArrayList<>();

    private Context mContext;

    public RecyclerViewAdapter(Context mContext,ArrayList<Business> _businessArray) {
        this.businessArray = _businessArray;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_quickedit, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        holder.businessName.setText(businessArray.get(position).BusinessName);
        holder.businessAddress.setText(businessArray.get(position).Address);

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked on: " + businessArray.get(position).BusinessName);

                Intent i = new Intent(mContext, MyRatingsActivity.class);
                mContext.startActivity(i);

            }
        });

        holder.detailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked on: " + businessArray.get(position).BusinessName);

                Intent i = new Intent(mContext, RatingDetailsActivity.class);
                i.putExtra("business",businessArray.get(position));
                mContext.startActivity(i);

            }
        });

        holder.rateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences prefs = mContext.getSharedPreferences(mContext.getString(R.string.shared_prefs),0);
                String userName = prefs.getString(mContext.getString(R.string.user_name),"Unknown Legend");

                Rating quickRating = new Rating(
                        businessArray.get(position),
                        holder.ratingBar.getRating(),
                        locationTask.location.getLatitude(),
                        locationTask.location.getLongitude(),
                        userName
                );

                connectionTask.sendRating(quickRating);

                String message = "Your rating for " + businessArray.get(position).BusinessName + " has been saved.";
                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return businessArray.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView businessName;
        TextView businessAddress;
        RatingBar ratingBar;
        Button rateButton;
        Button detailButton;
        LinearLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            businessName = itemView.findViewById(R.id.businessName);
            businessAddress = itemView.findViewById(R.id.businessAddress);
            ratingBar = itemView.findViewById(R.id.barQuickRate);
            rateButton = itemView.findViewById(R.id.btnQuickRate);
            detailButton = itemView.findViewById(R.id.btnDetailRate);
            parentLayout = itemView.findViewById(R.id.quick_edit_parent_layout);
        }
    }
}
