package com.bambang.vendingmachine.utils

import android.app.Activity
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AlertDialog
import com.bambang.vendingmachine.R


/**
 * Created by Yaya N on 4/12/2019.
 */
object UiHelper {

    fun progressDialog(activity: Activity): AlertDialog {
        val builder = AlertDialog.Builder(activity)
        builder.setCancelable(false) // if you want user to wait for some process to finish,
        builder.setView(R.layout.layout_progress_dialog)
        val dialog = builder.create()
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }

}