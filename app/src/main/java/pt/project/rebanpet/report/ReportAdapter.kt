package pt.project.rebanpet.report

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
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

        holder.adapterBinding.contentLocal.text = reportList[position].reportName
        //holder.adapterBinding.contentDate.text = reportList[position].userAge.toString()
        holder.adapterBinding.contentDate.text = reportList[position].reportLocal

        /*holder.adapterBinding.linearLayout.setOnClickListener {

            val intent = Intent(context, UpdateUserActivity::class.java)
            intent.putExtra("id", userList[position].userId)
            intent.putExtra("name", userList[position].userName)
            intent.putExtra("age", userList[position].userAge)
            intent.putExtra("email", userList[position].userEmail)
            context.startActivity(intent)

        }*/
    }

    fun getReportId(position: Int): String {
        return reportList[position].reportId
    }
}