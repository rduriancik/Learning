package com.example.robert.facebookrecipes.support;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

import org.robolectric.annotation.Implementation;
import org.robolectric.annotation.Implements;
import org.robolectric.annotation.RealObject;

/**
 * Created by robert on 9.10.2017.
 */
@Implements(RecyclerView.Adapter.class)
public class ShadowRecyclerViewAdapter {
    @RealObject
    private RecyclerView.Adapter realObject;

    private RecyclerView recyclerView;
    private SparseArray<RecyclerView.ViewHolder> holders = new SparseArray<>();

    @Implementation
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    @Implementation
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        realObject.onBindViewHolder(viewHolder, position);
        holders.put(position, viewHolder);
    }

    public int getItemCount() {
        return realObject.getItemCount();
    }

    public boolean performItemClick(int position) {
        View holder = holders.get(position).itemView;
        return holder.performClick();
    }

    public boolean performItemClickOverViewInHolder(int position, int viewId) {
        boolean valueToReturn = false;
        View holder = holders.get(position).itemView;
        View view = holder.findViewById(viewId);

        if (view != null) {
            valueToReturn = view.performClick();
        }

        return valueToReturn;
    }

    public void itemVisible(int position) {
        RecyclerView.ViewHolder holder = realObject.createViewHolder(recyclerView,
                realObject.getItemViewType(position));
        onBindViewHolder(holder, position);
    }

    public View getViewForHolderPosition(int position) {
        return holders.get(position).itemView;
    }
}
