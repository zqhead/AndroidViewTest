package com.csii.androidviewtest.Util.kotlin

import com.csii.androidviewtest.Util.LogUtil

/**
 * Test kotlin 基础
 * Created by zqhead on 2018/4/3.
 */
class testNormal {
    val sum: Int = 1

    /**
     * 测试kotlin
     * */
     private fun f1(a: Int, b: Int) : Int {
        if (a > b) {
            return a
        } else {
            return b
        }
    }

    companion object {
        val str: String = "hello world"
        @JvmStatic fun getStr1() : String {
            return str
        }
    }



    fun maxOf(a: Int, b: Int) = f1(a, b)
}