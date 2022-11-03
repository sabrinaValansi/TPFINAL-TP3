package ar.edu.ort.tpfinal_tp3.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Origin (
    @SerializedName("origin") val name : String?
        ) : Parcelable {
            constructor(parcel : Parcel) : this(
                parcel.readString()
            )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Origin> {
        override fun createFromParcel(parcel: Parcel): Origin {
            return Origin(parcel)
        }

        override fun newArray(size: Int): Array<Origin?> {
            return arrayOfNulls(size)
        }
    }
}