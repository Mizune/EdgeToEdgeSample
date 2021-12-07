package ms.mizune.edgetoedgesample

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
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
        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.navigationBarColor = getNavigationBarColorInt()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun getNavigationBarColorInt() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        getTransparentNavigationBarColor()
    } else {
        getTranslucentNavigationBarColor()
    }

    private fun getTransparentNavigationBarColor(): Int {
        return getColor(android.R.color.transparent)
    }

    private fun getTranslucentNavigationBarColor(): Int {
        return getColor(R.color.translucent)
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