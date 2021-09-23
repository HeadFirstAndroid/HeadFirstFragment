package me.yifeiyuan.hf.fragment

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager

/**
 *
 *
 * Created by 程序亦非猿 on 2021/9/23.
 */
class HFCustomWidthHeightDialogFragment : DialogFragment() {

    companion object {
        private const val TAG = "HFCustomWidthHeight"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate() called with: savedInstanceState = $savedInstanceState")

        setStyle(STYLE_NORMAL, R.style.HFDialog)
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
        val view = View.inflate(context, R.layout.dialog_layout_styled, null)
        //无效。。
//        view.layoutParams = ViewGroup.LayoutParams(MATCH_PARENT,WRAP_CONTENT)
        //无效
//        dialog.setContentView(view,ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT))

        //只需要调用 setContentView
        dialog.setContentView(view)

//        (view.parent as View).setBackgroundColor(resources.getColor(android.R.color.transparent))

        val win = dialog.window
        //宽高只能通过 window 来设置
        val lp: WindowManager.LayoutParams = win!!.attributes
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
//        lp.height = 900
//        lp.gravity =  Gravity.BOTTOM

        win?.attributes = lp
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

    override fun show(manager: FragmentManager, tag: String?) {
        super.show(manager, tag)
    }
}