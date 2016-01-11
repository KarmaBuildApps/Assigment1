package uk.ac.tae.myapp.immigrationform;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Karma on 15/12/15.
 */
public class ImmRecordAdapter extends RecyclerView.Adapter<ImmRecordAdapter.ViewHolder> {

    @Override
    public ImmRecordAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return null;
    }

    @Override
    public void onBindViewHolder(ImmRecordAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        ImageView ivImmigrantPhoto;
        TextView tvImmigrantName;
        public ViewHolder(View itemView) {
            super(itemView);
            ivImmigrantPhoto = (ImageView) itemView.findViewById(R.id.immigrant_photo);
            tvImmigrantName = (TextView) itemView.findViewById(R.id.immigrant_name);
            itemView.setTag(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }

        @Override
        public boolean onLongClick(View v) {
            return false;
        }
    }
}
