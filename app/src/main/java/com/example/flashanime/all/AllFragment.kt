package com.example.flashanime.all

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.navigation.findNavController
import com.example.flashanime.NavigationDirections
import com.example.flashanime.TemporaryFile
import com.example.flashanime.all.category.CategoryDialog
import com.example.flashanime.databinding.FragmentAllBinding
import com.example.flashanime.databinding.FragmentHomeBinding
import com.example.flashanime.ext.getVmFactory
import com.example.flashanime.home.HomeViewModel
import com.example.flashanime.home.viewpage2.season.SeasonListAdapter

class AllFragment: Fragment(), CategoryDialog.CategoryDialogListener {

    private val viewModel by viewModels<AllViewModel> { getVmFactory() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentAllBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        val adapter = AllListAdapter{
            view?.findNavController()?.navigate(NavigationDirections.navigateToDetailFragment(it))
        }

        binding.recyclerView.adapter = adapter


        binding.fab.setOnClickListener{
            viewModel.selecting = false
            val dialog = CategoryDialog()
            dialog.setCategoryDialogListener(this)
            dialog.show(childFragmentManager, "CATEGORY_DIALOG_TAG")
        }


        viewModel.combinedList.observe(viewLifecycleOwner, Observer {
            if (!viewModel.selecting){
                adapter.submitList(it)
            } else {
                viewModel.setSelectedList(viewModel.categoriesSelected)
            }
        })

        viewModel.selectedCategoryList.observe(viewLifecycleOwner, Observer {
            viewModel.selecting = true
            adapter.submitList(it)

            Log.i("zxcvzxcv","$it")

        })






        return binding.root
    }

    override fun onCategoriesSelected(categories: List<String>) {
        Log.i("onCategoriesSelected","$categories")
        viewModel.categoriesSelected = categories
        viewModel.setSelectedList(categories)
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.selecting = false
    }

}