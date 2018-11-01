package com.deligence.omdbmovierating.utility

import android.app.AlertDialog
import android.content.Context

class AlertUtil {

    companion object {

        fun show(context: Context, title: String, message: String){

            val builder = AlertDialog.Builder(context)
            builder.setTitle(title)
            builder.setMessage(message)
            builder.create().show()
        }
    }

}