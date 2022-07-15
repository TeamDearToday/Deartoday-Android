package co.kr.deartoday.presentation.ui.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import co.kr.deartoday.util.DeartodayDefaultLifecycleObserver
import timber.log.Timber

abstract class BaseFragment<T : ViewDataBinding> : Fragment() {
    private var _binding: T? = null
    protected val binding get() = _binding ?: error("Binding not Initialized")
    abstract val TAG: String
    abstract val layoutRes: Int

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Timber.tag(TAG).i("onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(DeartodayDefaultLifecycleObserver())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Timber.tag(TAG).i("onCreateView")
        _binding = DataBindingUtil.inflate(layoutInflater, layoutRes, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imm: InputMethodManager =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(getView()?.windowToken, 0)
        Timber.tag(TAG).i("onViewCreated")
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        Timber.tag(TAG).i("onViewStateRestored")
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Timber.tag(TAG).i("onSaveInstanceState")
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
        Timber.tag(TAG).i("onDestroyView")
    }

    override fun onDetach() {
        super.onDetach()
        Timber.tag(TAG).i("onDetach")
    }
}