package ms.mizune.edgetoedgesample

import android.content.res.Resources
import android.os.Build
import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.*
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
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
    }

    override fun onStart() {
        super.onStart()
        window.navigationBarColor = getNavigationBarColorInt()
        adjustPadding()
        adjustMargin()
    }

    private fun initRecyclerView() {
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun adjustPadding() {
        ViewCompat.setOnApplyWindowInsetsListener(binding.recyclerView) { view, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.updatePadding(bottom = insets.bottom)

            windowInsets
        }
    }

    private fun adjustMargin() {
        ViewCompat.setOnApplyWindowInsetsListener(binding.fab) { view, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                rightMargin = insets.right + FAB_MARGIN
                bottomMargin = insets.bottom + FAB_MARGIN
            }

            windowInsets
        }
    }

    private fun getNavigationBarColorInt() =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q || isGestureNavigationEnabled()) {
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

    private fun isGestureNavigationEnabled(): Boolean {
        val gestureModeInt = 2

        val navigationModeResId =
            resources.getIdentifier("config_navBar_interactionMode", "integer", "android")
                .takeIf { it > 0 }

        val navigationModeInt = try {
            navigationModeResId?.let(resources::getInteger)
        } catch (_: Resources.NotFoundException) {
            null
        }

        return navigationModeInt == gestureModeInt
    }

    companion object {
        fun makeSampleData(): List<ListViewItem> = (1..20).map {
            ListViewItem(
                "User $it",
                R.drawable.ic_launcher_foreground,
                R.drawable.ic_launcher_background
            )
        }

        const val FAB_MARGIN = 24
    }
}