package ms.mizune.edgetoedgesample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ms.mizune.edgetoedgesample.models.ListViewItem

class ListViewAdapter(
    private val itemList: List<ListViewItem>
) : RecyclerView.Adapter<ListViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.list_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val item = itemList[position]

        viewHolder.image.setImageResource(item.iconForegroundResId)
        viewHolder.image.setBackgroundResource(item.iconBackgroundResId)
        viewHolder.name.text = item.name
    }

    override fun getItemCount() = itemList.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.icon_image)
        val name: TextView = view.findViewById(R.id.name)
    }
}