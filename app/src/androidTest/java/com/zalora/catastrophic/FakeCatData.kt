package com.zalora.catastrophic

import com.zalora.catastrophic.home.room.Cat

object FakeCatData {

    val cats = arrayOf(
        Cat().let {
            it.id = "1"
            it.url = ""
        },
        Cat().let {
            it.id = "2"
            it.url = ""
        },
        Cat().let {
            it.id = "3"
            it.url = ""
        },
        Cat().let {
            it.id = "4"
            it.url = ""
        },
        Cat().let {
            it.id = "5"
            it.url = ""
        }
    )

}