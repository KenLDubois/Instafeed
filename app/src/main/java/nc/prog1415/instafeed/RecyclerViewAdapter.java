package nc.prog1415.instafeed;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<String> businessNames = new ArrayList<>();
    private Context mContext;

    public RecyclerViewAdapter(Context mContext,ArrayList<String> businessNames) {
        this.businessNames = businessNames;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_quickedit, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        holder.businessName.setText(businessNames.get(position));
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked on: " + businessNames.get(position));

                Toast.makeText(mContext, businessNames.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return businessNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView businessName;
        LinearLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            businessName = itemView.findViewById(R.id.businessName);
            parentLayout = itemView.findViewById(R.id.quick_edit_parent_layout);
        }
    }
}
