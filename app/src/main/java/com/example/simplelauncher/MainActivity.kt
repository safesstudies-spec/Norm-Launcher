package com.example.simplelauncher

import android.content.Intent
import android.content.pm.ResolveInfo
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * A plain Android launcher. All it does is:
 *   1. Ask Android for every app that has a launcher icon.
 *   2. Show those apps in a grid.
 *   3. Open an app when its icon is tapped.
 *
 * Because the manifest declares this Activity with the HOME category,
 * Android offers it as a Home screen choice.
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val appList = loadInstalledApps()

        val grid = findViewById<RecyclerView>(R.id.appGrid)
        // 4 columns across the screen; change this number for more/fewer per row.
        grid.layoutManager = GridLayoutManager(this, 4)
        grid.adapter = AppAdapter(appList) { app ->
            // Launch the tapped app.
            val launchIntent = packageManager.getLaunchIntentForPackage(app.packageName)
            if (launchIntent != null) {
                startActivity(launchIntent)
            }
        }
    }

    /**
     * Queries the system for every activity that responds to
     * ACTION_MAIN + CATEGORY_LAUNCHER — in other words, every app
     * that shows up in a normal app drawer.
     */
    private fun loadInstalledApps(): List<AppInfo> {
        val intent = Intent(Intent.ACTION_MAIN, null)
        intent.addCategory(Intent.CATEGORY_LAUNCHER)

        val resolvedApps: List<ResolveInfo> = packageManager.queryIntentActivities(intent, 0)

        return resolvedApps
            .map { resolveInfo ->
                AppInfo(
                    label = resolveInfo.loadLabel(packageManager).toString(),
                    packageName = resolveInfo.activityInfo.packageName,
                    icon = resolveInfo.loadIcon(packageManager)
                )
            }
            .sortedBy { it.label.lowercase() }
    }
}

/** Simple holder for the three things each grid item needs. */
data class AppInfo(
    val label: String,
    val packageName: String,
    val icon: Drawable
)
