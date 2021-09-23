package me.yifeiyuan.hf.fragment

import android.view.View
import me.yifeiyuan.adh.showcase.AdhShowcaseActivity
import me.yifeiyuan.adh.showcase.AdhShowcaseItem

class MainActivity : AdhShowcaseActivity() {

    override fun provideShowcaseItems(): List<AdhShowcaseItem> {
        return mutableListOf(

            createShowcaseItem("Dialog 样式的 DialogFragment") {
                HFDefaultDialogFragment().show(supportFragmentManager, "dialogfragment")
            },

            createShowcaseItem("自定义布局") {
                HFCustomViewDialogFragment().show(supportFragmentManager, "dialogfragment")
            }
        )
    }

    fun showDialogFragment(view: View) {}
}