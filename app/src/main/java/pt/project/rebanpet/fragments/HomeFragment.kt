package pt.project.rebanpet.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import com.qamar.curvedbottomnaviagtion.CurvedBottomNavigation
import pt.project.rebanpet.R

class HomeFragment : Fragment(){


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                          savedInstanceState: Bundle?,
    ): View {
        val view =  inflater.inflate(R.layout.fragment_home, container, false)

        //setContentView(R.layout.fragment_home)
        //onBackPressedDispatcher.addCallback(this, onBackPressedCallback)

        val bottomNavigation = view.findViewById<CurvedBottomNavigation>(R.id.bottomNavigation)
        bottomNavigation.add(
            CurvedBottomNavigation.Model(1, "Histórico", R.drawable.baseline_list_alt_24)
        )
        bottomNavigation.add(
            CurvedBottomNavigation.Model(2, "Denúncia", R.drawable.baseline_add_circle_outline_24)
        )
        bottomNavigation.add(
            CurvedBottomNavigation.Model(3, "Info", R.drawable.baseline_info_24)
        )

        bottomNavigation.setOnClickMenuListener {
            when (it.id) {
                1 -> {
                    replaceFragment(HistoricalFragment())
                    activity?.title = "Histórico"
                }

                2 -> {
                    replaceFragment(ReportFragment())
                    activity?.title = "Denúncia"
                }

                3 -> {
                    replaceFragment(InfoFragment())
                    activity?.title = "Informações Gerais"

                }
            }
        }

        // default bottom Tab Selected
        replaceFragment(ReportFragment())
        bottomNavigation.show(2)
        return view
    }

    private fun replaceFragment(fragment: Fragment) {
        parentFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()

    }
}