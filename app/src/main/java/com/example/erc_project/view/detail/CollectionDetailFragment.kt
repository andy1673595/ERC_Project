package com.example.erc_project.view.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.erc_project.R
import com.example.erc_project.view.BaseFragment
import com.example.erc_project.view.main.CollectionViewModel
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.erc_project.view.webView.WebViewActivity

class CollectionDetailFragment : BaseFragment() {

    private val viewModel by activityViewModels<CollectionViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_collection_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registerBaseViewModel(viewModel)

        viewModel.collectionDetail.observe(viewLifecycleOwner, Observer { detail ->

            view.findViewById<TextView>(R.id.text_name_fragment_detail).text = detail.name
            view.findViewById<TextView>(R.id.text_description_fragment_detail).text = detail.description

            Glide.with(this)
                .load(detail.imgUrl)
                .into(view.findViewById<ImageView>(R.id.img_fragment_detail))
        })

        viewModel.itemSelected.value?.let { collectionItem ->
            findNavController().currentDestination?.label = collectionItem.name

            viewModel.getCollectionDetail(collectionItem)
        }

        view.findViewById<Button>(R.id.btn_permalink_fragment_detail).setOnClickListener {
            val permalink = viewModel.collectionDetail.value?.permalink ?: ""
            val bundle = Bundle()
            bundle.putString(WebViewActivity.URL_KEY, permalink)

            findNavController().navigate(R.id.action_fragment_detail_to_activity_web_view, bundle)
        }
    }
}