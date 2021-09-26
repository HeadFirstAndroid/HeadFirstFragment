package me.yifeiyuan.hf.fragment

import android.os.Bundle
import android.view.View
import me.yifeiyuan.adh.showcase.AdhShowcaseActivity
import me.yifeiyuan.adh.showcase.AdhShowcaseItem

class MainActivity : AdhShowcaseActivity() {

    override fun provideShowcaseItems(): List<AdhShowcaseItem> {
        return mutableListOf(

            createShowcaseItem("Dialog 样式的 DialogFragment") {

                val dialog = HFDefaultDialogFragment()
                dialog.arguments = Bundle().apply {
                    putString("key","string")
                    putInt("intKey",0)
                }

                dialog.show(supportFragmentManager, "HFDefaultDialogFragment")
            },

            createShowcaseItem("自定义布局") {
                HFCustomViewDialogFragment().show(supportFragmentManager, "自定义布局")
            },

            createShowcaseItem("自定义宽高的 Dialog") {
                HFCustomWidthHeightDialogFragment().show(supportFragmentManager, "自定义宽高的")
            },
            createShowcaseItem("ItemListDialogFragment (高度不对)") {
                ItemListDialogFragment.newInstance(30)
                    .show(supportFragmentManager, "ItemListDialogFragment")
            }
        )
    }

    fun showDialogFragment(view: View) {}
}