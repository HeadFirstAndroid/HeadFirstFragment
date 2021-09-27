package me.yifeiyuan.hf.fragment

import androidx.lifecycle.LiveData

/**
 * Created by 程序亦非猿 on 2021/9/27.
 */
interface IEventHost {
    fun getEvents(): LiveData<Event>
}

class Event(val name: String, var args: Any?=null)
