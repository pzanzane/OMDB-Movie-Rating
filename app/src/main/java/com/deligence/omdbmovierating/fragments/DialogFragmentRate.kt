package com.deligence.omdbmovierating.fragments

import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import com.deligence.omdbmovierating.R
import com.deligence.omdbmovierating.utility.ToastUtils

class DialogFragmentRate: DialogFragment(), View.OnClickListener {


    lateinit var ratingBar: RatingBar
    lateinit var edtName: EditText
    lateinit var edtReview: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setStyle(DialogFragment.STYLE_NO_TITLE,
                android.R.style.Theme_Material);
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view: View = inflater.inflate(R.layout.dialog_view_rate,null)
        view.findViewById<Button>(R.id.btnSubmit).setOnClickListener(this)

        edtName = view.findViewById<EditText>(R.id.edtName)
        edtReview = view.findViewById<EditText>(R.id.edtReview)
        ratingBar = view.findViewById<RatingBar>(R.id.ratingBar)

        return view
    }

    override fun onClick(view: View?) {

        //Need to Save this rating to Server
        ToastUtils.showToast("Saved Rating")
        dismiss()
    }

}