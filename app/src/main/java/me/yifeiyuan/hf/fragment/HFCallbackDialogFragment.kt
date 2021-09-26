package me.yifeiyuan.hf.fragment

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import java.io.Serializable

/**
 *
 * 尝试 Callback 沟通
 *
 * 当页面恢复的时候 callback 会为 null ===> 此路不通
 *
 * Created by 程序亦非猿 on 2021/9/23.
 */
class HFCallbackDialogFragment : DialogFragment() {

    companion object {
        private const val TAG = "HFDefaultDialogFragment"

        fun create(
            intValue: Int,
            stringValue: String,
            cb: Callback?=null
        ): HFCallbackDialogFragment {

            val bundle = Bundle().apply {
                putString("stringKey", stringValue)
                putInt("intKey", intValue)
//                putSerializable("sKey", cb)
//                putParcelable("pKey",callback)
            }

            return HFCallbackDialogFragment().apply {
                arguments = bundle
//                callback = cb
            }
        }
    }

    var callback: Callback? = null

    var fragmentEvents: MutableLiveData<Event> = MutableLiveData()

    lateinit var viewModel : HFCallbackVM

    class Event(val name: String, var args: Any?=null)

    interface Callback : Serializable {
        fun onPositive()
        fun onNegative()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate() called with: savedInstanceState = $savedInstanceState")

        viewModel = ViewModelProvider(this).get(HFCallbackVM::class.java)
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
        Log.d(TAG, "onCreateDialog() $arguments")

//        callback = arguments?.getParcelable("sKey") as Callback?

        val dialog = AlertDialog.Builder(requireContext())
            .setMessage("自定义 Message")
            .setTitle("自定义 Title ${arguments?.getString("stringKey")}")
            .setView(R.layout.dialog_layout_hf)
            .setNegativeButton("取消") { dialog, which ->
                Toast.makeText(
                    requireContext(),
                    "点击了取消",
                    Toast.LENGTH_SHORT
                ).show()
                callback?.onNegative()

                fragmentEvents.postValue(Event("setNegativeButton", "setNegativeButton Arg 1"))

                viewModel.events.postValue(Event("setNegativeButton", "viewModel Arg 2"))
            }
            .setCancelable(false)
            .setPositiveButton(
                "确定"
            ) { dialog, which ->
                Toast.makeText(requireContext(), "点击了确定", Toast.LENGTH_SHORT).show()
                callback?.onPositive()
                fragmentEvents.postValue(Event("setPositiveButton", "setPositiveButton Arg 2"))

                viewModel.events.postValue(Event("setPositiveButton", "viewModel Arg 2"))
            }
            .create()
        dialog.setCancelable(false) //依赖没有用- -
        dialog.setCanceledOnTouchOutside(true) //依赖没有用- -

        isCancelable = false //才有用
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

//    override fun onSaveInstanceState(outState: Bundle) {
//        outState.putSerializable("sKey",callback)
//        super.onSaveInstanceState(outState)
//    }
}