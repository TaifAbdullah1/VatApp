package com.example.vatapp
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import com.example.vatapp.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.btnCalculateVat.setOnClickListener{
            calculateVat()
        }
    }
    private fun calculateVat() {
        val stringInTextField = binding.etTotalCost.text.toString()
        val cost = stringInTextField.toDouble()
       //Radio Group
        val selectedId = binding.rgVatOption.checkedRadioButtonId
        //radio button selected
        val vatPercentage = when(selectedId){
            R.id.rb_vat_10 -> 0.10
            R.id.rb_vat_15 -> 0.15
           else -> 0.20
        }
        var vat = vatPercentage * cost
        var total = cost + vat
        //switch value
        val roundVat = binding.switchRoundUp.isChecked
        if (roundVat){
            total = kotlin.math.ceil(total)
        }
        //total formatting
      val formattedTotal = NumberFormat.getCurrencyInstance().format(total)
        binding.txtCostTotal.text = getString(R.string.total_amount, formattedTotal)
    }
}
