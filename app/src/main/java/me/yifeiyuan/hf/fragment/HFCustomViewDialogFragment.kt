package me.yifeiyuan.hf.fragment

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment

/**
 * 自定义布局
 *
 * Created by 程序亦非猿 on 2021/9/23.
 */
class HFCustomViewDialogFragment : DialogFragment() {

    companion object {
        private const val TAG = "HFDialogFragment"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate() called with: savedInstanceState = $savedInstanceState")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d(TAG, "onActivityCreated() called with: savedInstanceState = $savedInstanceState")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "onAttach() called with: context = $context")
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        Log.d(TAG, "onCancel() called with: dialog = $dialog")
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        Log.d(TAG, "onDismiss() called with: dialog = $dialog")
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        Log.d(TAG, "onCreateDialog() called with: savedInstanceState = $savedInstanceState")
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.setContentView(R.layout.dialog_layout_hf)
        return dialog
    }

    override fun onOptionsMenuClosed(menu: Menu) {
        super.onOptionsMenuClosed(menu)
        Log.d(TAG, "onOptionsMenuClosed() called with: menu = $menu")
    }

    override fun onStart() {
        super.onStart()

//        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        Log.d(TAG, "onStart() called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }

    override fun onGetLayoutInflater(savedInstanceState: Bundle?): LayoutInflater {
        Log.d(TAG, "onGetLayoutInflater: ")
        return super.onGetLayoutInflater(savedInstanceState)
    }
}