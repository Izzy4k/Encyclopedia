package com.example.encyclopedia

import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter

class ListAdapter(
    private val list: List<String>
) : BaseAdapter() {
    override fun getCount(): Int = list.size

    override fun getItem(p0: Int): Any {
        TODO("Not yet implemented")
    }

    override fun getItemId(p0: Int): Long {
        TODO("Not yet implemented")
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        TODO("Not yet implemented")
    }
}