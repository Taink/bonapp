package fr.iut.info.app.appmob.bonapp.ui.widgets

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.iut.info.app.appmob.bonapp.R
import fr.iut.info.app.appmob.bonapp.db.models.Step

open class StepRecyclerAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var items: List<Step> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return StepViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.step_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is StepViewHolder -> {
                holder.bind(items[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

     open fun submitList(stepList: List<Step>) {
        items = stepList
    }

    class StepViewHolder (
        itemView: View
    ): RecyclerView.ViewHolder(itemView) {
        val stepName = itemView.findViewById<TextView>(R.id.step_name)
        val stepText = itemView.findViewById<TextView>(R.id.step_text)

        fun bind(step: Step) {
            stepName.setText(itemView.context.getString(R.string.step_name, 1))
            stepText.setText(step.text)
        }
    }
}