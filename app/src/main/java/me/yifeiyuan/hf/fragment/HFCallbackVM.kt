package me.yifeiyuan.hf.fragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Created by 程序亦非猿 on 2021/9/26.
 */
class HFCallbackVM : ViewModel() {

    var events = MutableLiveData<HFCallbackDialogFragment.Event>()
}