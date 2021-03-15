package org.d3if2091.hitungbmi2.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs

import kotlinx.android.synthetic.main.fragmen_saran.*
import org.d3if2091.hitungbmi2.R
import org.d3if2091.hitungbmi2.data.KategoriBmi

class SaranFragment {
    class SaranFragment(val root: View?) : Fragment() {
        private val args: SaranFragmentArgs by navArgs()
        private lateinit var binding: SaranFragment
        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            return binding.root
        }
        private fun updateUI(kategori: KategoriBmi) {
            val actionBar = (requireActivity() as AppCompatActivity)
                .supportActionBar
            when (kategori) {
                KategoriBmi.KURUS -> {
                    actionBar?.title = getString(R.string.judul_kurus)
                    binding.imageView.setImageResource(R.drawable.kurus)
                    binding.textView.text = getString(R.string.saran_kurus)
                }
                KategoriBmi.IDEAL -> {
                    actionBar?.title = getString(R.string.judul_ideal)
                    binding.imageView.setImageResource(R.drawable.ideal)
                    binding.textView.text = getString(R.string.saran_ideal)
                }
                KategoriBmi.GEMUK -> {
                    actionBar?.title = getString(R.string.judul_gemuk)
                    binding.imageView.setImageResource(R.drawable.gemuk)
                    binding.textView.text = getString(R.string.saran_gemuk)
                }
            }
        }
    }
}

