package com.example.bottomsheetdialog

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomsheet.BottomSheetDialog

class MainActivity : AppCompatActivity() {
    lateinit var button: Button

    @SuppressLint("InflateParams")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        button = findViewById(R.id.button)

        button.setOnClickListener {
            val view: View = layoutInflater.inflate(R.layout.bottom_sheet_layout, null)
            val deleteFeedLayout: LinearLayout = view.findViewById(R.id.deleteFeedLayout)

            deleteFeedLayout.setOnClickListener {
                showDeleteConfirmationDialog()
            }
            val dialog = BottomSheetDialog(this)
            dialog.setContentView(view)
            dialog.show()
        }
    }
    @SuppressLint("InflateParams")
    private fun showDeleteConfirmationDialog() {
        val inflater = LayoutInflater.from(this)
        val customTitleView: View = inflater.inflate(R.layout.delete_title_custom_layout, null)
        val builder = AlertDialog.Builder(this)
//        builder.setTitle("Delete Feed Confirmation")
        builder.setCustomTitle(customTitleView)
        builder.setMessage("Are you sure you want to delete this feed?")

        // Positive Button
        builder.setPositiveButton("Delete") { dialog, which ->
            // Handle delete action
            handleDeleteAction()
        }

        // Negative Button
        builder.setNegativeButton("Cancel") { dialog, which ->
            dialog.dismiss() // Dismiss the dialog
        }

        // Create and show the AlertDialog
        val alertDialog = builder.create()
        alertDialog.show()
    }

    private fun handleDeleteAction() {
        // Logic to delete the item
        // e.g., remove it from a list or database
    }
}