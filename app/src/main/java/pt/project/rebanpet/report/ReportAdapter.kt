package pt.project.rebanpet.report

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import pt.project.rebanpet.databinding.ReportItemBinding
import pt.project.rebanpet.fragments.HistoricalFragment

class ReportAdapter(
    var context: HistoricalFragment,
    var reportList: ArrayList<Report>) : RecyclerView.Adapter<ReportAdapter.ReportViewHolder>(){

    inner class ReportViewHolder(val adapterBinding: ReportItemBinding) :
        RecyclerView.ViewHolder(adapterBinding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReportViewHolder {
        val binding = ReportItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ReportViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return reportList.size
    }

    override fun onBindViewHolder(holder: ReportViewHolder, position: Int) {
        holder.adapterBinding.contentAnimal.text = reportList[position].reportDescription
        holder.adapterBinding.contentLocal.text = reportList[position].reportLocal
        holder.adapterBinding.contentDate.text = reportList[position].reportDateTime
        Glide.with(holder.adapterBinding.imageAnimal.context)
            .load(reportList[position].reportPhotoUrl)
            //.placeholder(R.drawable.placeholder) // Placeholder image
            //.error(R.drawable.error) // Error image
            .into(holder.adapterBinding.imageAnimal)
    }

    fun getReportId(position: Int): String {
        return reportList[position].reportId
    }
}