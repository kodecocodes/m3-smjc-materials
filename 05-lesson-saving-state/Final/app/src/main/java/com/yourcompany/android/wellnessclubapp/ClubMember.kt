package com.yourcompany.android.wellnessclubapp

import android.os.Parcelable
import androidx.compose.runtime.saveable.mapSaver
import kotlinx.parcelize.Parcelize

data class ClubMember(
  val name: String = "",
  val email: String = ""
)


val ClubMemberSaver = run {
  val nameKey = "name"
  val emailKey = "email"
  mapSaver(
    save = { mapOf(nameKey to it.name, emailKey to it.email)},
    restore = {ClubMember(name = it[nameKey] as String, email = it[emailKey] as String)}
  )
}
