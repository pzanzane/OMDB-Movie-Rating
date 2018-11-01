package com.deligence.omdbmovierating.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.deligence.omdbmovierating.R
import com.deligence.omdbmovierating.enums.EnumTypes

class FragmentListItem : Fragment(){

    companion object {
        val TAG = "FragmentListItem"
        val EXTRA_TYPE = "Type"

        fun getInstance(type: String, context: Context): Fragment{

            val bundle = Bundle()
            bundle.putString(EXTRA_TYPE, type)

            val frag: FragmentListItem  = Fragment.instantiate(context,
                    FragmentListItem::class.java.name) as FragmentListItem
            frag.arguments = bundle

            return frag
        }
    }

    var type: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        type = arguments?.getString(EXTRA_TYPE) ?: EnumTypes.ALL.name
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.frag_list_movie, null)
        return view
    }
}
