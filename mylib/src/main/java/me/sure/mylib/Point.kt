package me.sure.mylib

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by wangshuo on 2020-05-31.
 */
class Point() : Parcelable {
    var x: Int = 0
    var y: Int = 0

    constructor(parcel: Parcel) : this() {
        readFromParcel(parcel)
    }

    private fun readFromParcel(parcel: Parcel) {
        x = parcel.readInt()
        y = parcel.readInt()
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeInt(x)
        dest?.writeInt(y)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun toString(): String {
        return "Point(x=$x, y=$y)"
    }

    companion object CREATOR : Parcelable.Creator<Point> {
        override fun createFromParcel(parcel: Parcel): Point {
            return Point(parcel)
        }

        override fun newArray(size: Int): Array<Point?> {
            return arrayOfNulls(size)
        }
    }
}