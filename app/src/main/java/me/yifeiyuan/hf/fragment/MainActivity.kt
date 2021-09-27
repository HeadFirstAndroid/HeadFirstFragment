package me.yifeiyuan.hf.fragment

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentOnAttachListener
import me.yifeiyuan.adh.showcase.AdhShowcaseActivity
import me.yifeiyuan.adh.showcase.AdhShowcaseItem

class MainActivity : AdhShowcaseActivity() {

    companion object{
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
    }

//    val mapper = mutableMapOf<IEventHost>()

    override fun onCreate(savedInstanceState: Bundle?) {

        Log.d(TAG, "MainActivity onCreate() called with: savedInstanceState = $savedInstanceState")

        supportFragmentManager.registerFragmentLifecycleCallbacks(object :
            FragmentManager.FragmentLifecycleCallbacks() {
            override fun onFragmentCreated(
                fm: FragmentManager,
                fragment: Fragment,
                savedInstanceState: Bundle?
            ) {
                Log.d(
                    TAG,
                    "onFragmentCreated() called with: fm = $fm, fragment = $fragment, savedInstanceState = $savedInstanceState"
                )

                if (fragment is HFCallbackDialogFragment) {
                    fragment.viewModel.events.observe(this@MainActivity) {
                        when (it.name) {
                            "setNegativeButton" -> {
                                Toast.makeText(
                                    this@MainActivity,
                                    "onFragmentCreated ${it.name},${it.args}",
                                    Toast.LENGTH_SHORT
                                )
                                    .show()
                            }
                            "setPositiveButton" -> {
                                Toast.makeText(
                                    this@MainActivity,
                                    "onFragmentCreated ${it.name},${it.args}",
                                    Toast.LENGTH_SHORT
                                )
                                    .show()
                            }
                        }
                    }
                }
            }

            override fun onFragmentDestroyed(fm: FragmentManager, f: Fragment) {
                super.onFragmentDestroyed(fm, f)
            }
        }, false)

        //也是无效 恢复不了
        supportFragmentManager.addFragmentOnAttachListener(object : FragmentOnAttachListener {
            override fun onAttachFragment(fragmentManager: FragmentManager, fragment: Fragment) {
                Log.d(
                    TAG,
                    "MainActivity onAttachFragment() called with: fragmentManager = $fragmentManager, fragment = $fragment"
                )
                if (fragment is HFCallbackDialogFragment) {
                    fragment.viewModel.events.observe(this@MainActivity) {
                        when (it.name) {
                            "setNegativeButton" -> {
                                Toast.makeText(
                                    this@MainActivity,
                                    "onAttachFragment ${it.name},${it.args}",
                                    Toast.LENGTH_SHORT
                                )
                                    .show()
                            }
                            "setPositiveButton" -> {
                                Toast.makeText(
                                    this@MainActivity,
                                    "onAttachFragment ${it.name},${it.args}",
                                    Toast.LENGTH_SHORT
                                )
                                    .show()
                            }
                        }
                    }
                }
            }
        })

        super.onCreate(savedInstanceState)
    }

    override fun provideShowcaseItems(): List<AdhShowcaseItem> {
        return mutableListOf(

            createShowcaseItem("Dialog 样式的 DialogFragment") {

                val dialog = HFDefaultDialogFragment.create(
                    0,
                    "string",
                    object : HFDefaultDialogFragment.Callback {
                        override fun onPositive() {
                            Toast.makeText(this@MainActivity, "onPositive", Toast.LENGTH_SHORT)
                                .show()
                        }

                        override fun onNegative() {
                            Toast.makeText(this@MainActivity, "onNegative", Toast.LENGTH_SHORT)
                                .show()
                        }
                    })

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
            },

            createShowcaseItem("HFCallbackDialogFragment ") {
                val callbackDialog = HFCallbackDialogFragment.create(30, "32323")
                // observe、observeForever 恢复后也都接收不到消息 ，所以通过 livedata 也行不通
                callbackDialog.fragmentEvents.observeForever() {
                    when (it.name) {
                        "setNegativeButton" -> {
                            Toast.makeText(
                                this@MainActivity,
                                "setNegativeButton callback",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        }
                        "setPositiveButton" -> {
                            Toast.makeText(
                                this@MainActivity,
                                "setPositiveButton callback",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        }
                    }
                }

                callbackDialog.show(supportFragmentManager, "ItemListDialogFragment")

                //    kotlin.UninitializedPropertyAccessException: lateinit property viewModel has not been initialized
//                callbackDialog.viewModel.events.observe(this){
//                    when(it.name){
//                        "setNegativeButton"->{
//                            Toast.makeText(this@MainActivity, "${it.name},${it.args}", Toast.LENGTH_SHORT)
//                                .show()
//                        }
//                        "setPositiveButton"->{
//                            Toast.makeText(this@MainActivity, "${it.name},${it.args}", Toast.LENGTH_SHORT)
//                                .show()
//                        }
//                    }
//                }
                //也逃不过恢复后丢失
                supportFragmentManager.addFragmentOnAttachListener(object :
                    FragmentOnAttachListener {
                    override fun onAttachFragment(
                        fragmentManager: FragmentManager,
                        fragment: Fragment
                    ) {
                        Log.d(
                            TAG,
                            "onAttachFragment() called with: fragmentManager = $fragmentManager, fragment = $fragment"
                        )
                        if (fragment is HFCallbackDialogFragment) {
                            fragment.viewModel.events.observe(this@MainActivity) {
                                when (it.name) {
                                    "setNegativeButton" -> {
                                        Toast.makeText(
                                            this@MainActivity,
                                            "${it.name},${it.args}",
                                            Toast.LENGTH_SHORT
                                        )
                                            .show()
                                    }
                                    "setPositiveButton" -> {
                                        Toast.makeText(
                                            this@MainActivity,
                                            "${it.name},${it.args}",
                                            Toast.LENGTH_SHORT
                                        )
                                            .show()
                                    }
                                }
                            }
                        }
                    }
                })
            },

            )
    }

    fun showDialogFragment(view: View) {}
}