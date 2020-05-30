package jp.cordea.daggerhiltdemo

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class User(
    val firstName: String,
    val lastName: String,
    val title: String,
    val phoneNumber: String,
    val emailAddress: String,
    val company: String
) : Parcelable
