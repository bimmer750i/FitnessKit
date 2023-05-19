package fitness.test.kit.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.shuhart.stickyheader.StickyHeaderItemDecorator
import fitness.test.kit.App
import fitness.test.kit.data.FailureLessonsResult
import fitness.test.kit.data.PendingLessonsResult
import fitness.test.kit.data.SuccessLessonsResult
import fitness.test.kit.databinding.FragmentLessonsBinding
import fitness.test.kit.presentation.viewmodels.LessonsViewModel
import fitness.test.kit.presentation.viewmodels.LessonsViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LessonsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LessonsFragment : Fragment(),FragmentController {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    val TAG = "JAC"

    @Inject
    lateinit var factory: LessonsViewModelFactory

    private lateinit var viewModel: LessonsViewModel

    private lateinit var binding: FragmentLessonsBinding

    private lateinit var adapter: LessonsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        (requireActivity().application as App).appComponent.inject(this)
        viewModel = ViewModelProvider(this,factory)[LessonsViewModel::class.java]
        adapter = LessonsAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLessonsBinding.inflate(layoutInflater,container,false)
        viewModel.lessons.observe(viewLifecycleOwner, Observer {
            when (it) {
                is PendingLessonsResult -> showLoading()
                is SuccessLessonsResult -> {
                    showResult()
                    adapter.setLessonsAndTrainersList(it.lessons,it.trainers)
                }
                is FailureLessonsResult -> showError()
            }
        })
        binding.swiperefresh.setOnRefreshListener {
            lifecycleScope.launch { viewModel.getLessons() }
            binding.swiperefresh.isRefreshing = false
        }
        binding.lessonsrecyclerview.layoutManager = LinearLayoutManager(requireContext())
        binding.lessonsrecyclerview.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch(Dispatchers.IO) {
            viewModel.getLessons()
        }

    }

    override fun hideAll() {
        binding.errorTextview.visibility = View.GONE
        binding.lessonsrecyclerview.visibility = View.GONE
        binding.loadingProgressbar.visibility = View.GONE
    }

    override fun showLoading() {
        hideAll()
        binding.loadingProgressbar.visibility = View.VISIBLE
    }

    override fun showResult() {
        hideAll()
        binding.lessonsrecyclerview.visibility = View.VISIBLE
    }

    override fun showError() {
        hideAll()
        binding.errorTextview.visibility = View.VISIBLE
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LessonsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LessonsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}