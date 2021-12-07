package ms.mizune.edgetoedgesample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import ms.mizune.edgetoedgesample.databinding.ActivityMainBinding
import ms.mizune.edgetoedgesample.models.ListViewItem

class MainActivity : AppCompatActivity() {
    private val itemList = makeSampleData()

    private val adapter: ListViewAdapter by lazy {
        ListViewAdapter(itemList)
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
    }

    private fun initRecyclerView(){
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }

    companion object {
        fun makeSampleData(): List<ListViewItem> = (1..20).map {
            ListViewItem(
                "User $it",
                R.drawable.ic_launcher_foreground,
                R.drawable.ic_launcher_background
            )
        }
    }
}