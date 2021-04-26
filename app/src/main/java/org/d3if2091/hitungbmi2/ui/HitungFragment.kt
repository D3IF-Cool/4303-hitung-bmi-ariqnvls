package org.d3if2091.hitungbmi2.ui

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import org.d3if2091.hitungbmi2.R
import org.d3if2091.hitungbmi2.data.KategoriBmi
import org.d3if2091.hitungbmi2.databinding.FragmentHitungBinding

class HitungFragment : Fragment() {
    private val viewModel: HitungViewModel by viewModels()
    private lateinit var binding: FragmentHitungBinding
    private lateinit var kategoriBmi: KategoriBmi
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentHitungBinding.inflate(
            layoutInflater, container, false)
        binding.button.setOnClickListener { hitungBmi() }
        binding.saranButton.setOnClickListener { view: View ->
            view.findNavController().navigate(
                HitungFragmentDirections.
                actionHitungFragmentToSaranFragment(kategoriBmi)
            )
        }
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getHasilBmi().observe(viewLifecycleOwner, {
            if (it == null) return@observe
            binding.bmiTextView.text = getString(R.string.bmi_x, it.bmi)
            binding.kategoriTextView.text = getString(R.string.kategori_x,
                getKategori(it.kategori))
            binding.buttonGroup.visibility = View.VISIBLE
        })
    }

    private fun hitungBmi() {
        val berat = binding.beratEditText.text.toString()
        if (TextUtils.isEmpty(berat)) {
            Toast.makeText(context, R.string.berat_invalid, Toast.LENGTH_LONG).show()
            return
        }
        val tinggi = binding.tinggiEditText.text.toString()
        if (TextUtils.isEmpty(tinggi)) {
            Toast.makeText(context, R.string.tinggi_invalid, Toast.LENGTH_LONG).show()
            return
        }


        val selectedId = binding.radioGroup.checkedRadioButtonId
        if (selectedId == -1) {
            Toast.makeText(context, R.string.gender_invalid, Toast.LENGTH_LONG).show()
            return
        }
        val isMale = selectedId == R.id.priaRadioButton

    }
    private fun getKategori(kategori: KategoriBmi): String {

        val stringRes = when (kategori) {
            KategoriBmi.KURUS -> R.string.kurus
            KategoriBmi.IDEAL -> R.string.ideal
            KategoriBmi.GEMUK -> R.string.gemuk
        }
        return getString(stringRes)
    }


}