package ar.edu.ort.tpfinal_tp3.model
import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Character (
    var name: String?,
    var status: String?,
    var image: String?,
    var species: String?,
    var origin: Origin?

    ) : Parcelable {
        constructor(parcel : Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readParcelable(Origin::class.java.classLoader)
        )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(status)
        parcel.writeString(image)
        parcel.writeString(species)
        parcel.writeParcelable(origin, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Character> {
        override fun createFromParcel(parcel: Parcel): Character {
            return Character(parcel)
        }

        override fun newArray(size: Int): Array<Character?> {
            return arrayOfNulls(size)
        }
    }
}