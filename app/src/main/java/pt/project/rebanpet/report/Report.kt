package pt.project.rebanpet.report

import android.net.Uri

data class Report(val reportId:String = "",
                  val reportDescription:String = "",
                  val reportLocal:String = "",
                  val reportDateTime:String = "",
                  val reportPhotoUrl:String = "")