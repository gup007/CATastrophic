package com.zalora.catastrophic.home

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.zalora.catastrophic.rest.APIResponse

@Entity
class Cat() : APIResponse(), Parcelable {

    @PrimaryKey
    var id: String = ""
    var url: String? = null
    var width: Int = 0
    var height: Int = 0

    constructor(parcel: Parcel) : this() {
        id = parcel.readString()!!
        url = parcel.readString()
        width = parcel.readInt()
        height = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(url)
        parcel.writeInt(width)
        parcel.writeInt(height)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Cat> {
        override fun createFromParcel(parcel: Parcel): Cat {
            return Cat(parcel)
        }

        override fun newArray(size: Int): Array<Cat?> {
            return arrayOfNulls(size)
        }
    }


}