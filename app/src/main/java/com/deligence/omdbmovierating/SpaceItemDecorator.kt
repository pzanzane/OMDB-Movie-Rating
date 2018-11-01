package com.deligence.omdbmovierating

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View


class SpacesItemDecoration(private val space: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)

        outRect?.bottom = space

//        // Add top margin only for the first item to avoid double space between items
//        if (parent?.getChildAdapterPosition(parent) == 0) {
//            outRect?.top = space
//        }
    }

}