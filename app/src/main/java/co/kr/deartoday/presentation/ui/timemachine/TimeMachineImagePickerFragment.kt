package co.kr.deartoday.presentation.ui.timemachine

import android.Manifest
import android.app.Activity.RESULT_CANCELED
import android.app.Activity.RESULT_OK
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import co.kr.deartoday.R
import co.kr.deartoday.databinding.FragmentTimeMachineImagePickerBinding
import co.kr.deartoday.presentation.ui.base.BaseFragment
import co.kr.deartoday.presentation.viewmodel.timemachine.TimeMachineViewModel
import co.kr.deartoday.util.MySoundPlayer
import co.kr.deartoday.util.MySoundPlayer.initSounds
import co.kr.deartoday.util.getTodayString
import co.kr.deartoday.util.setOnSingleClickListener
import co.kr.deartoday.util.shortToast
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class TimeMachineImagePickerFragment : BaseFragment<FragmentTimeMachineImagePickerBinding>() {
    override val TAG: String
        get() = TimeMachineImagePickerFragment::class.java.simpleName
    override val layoutRes: Int
        get() = R.layout.fragment_time_machine_image_picker

    private val viewModel by activityViewModels<TimeMachineViewModel>()

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
                navigateGallery()
            } else {
                requireContext().shortToast("갤러리 접근 권한이 없습니다.")
            }
        }
    private val galleryLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK && it.data != null) {
                val imageUri = it.data?.data
                imageUri?.let { image ->
                    viewModel.imageUri.value = image
                } ?: requireContext().shortToast("문제가 발생했습니다")
            } else if (it.resultCode == RESULT_CANCELED) {
                requireContext().shortToast("사진 선택이 취소되었습니다")
            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewmodel = viewModel
        initView()
        initOnClickListener()
        observeData()
    }

    private fun initView() {
        initTvDateView()
    }

    private fun initOnClickListener() {
        initSounds(requireContext())
        binding.ivVideoTape.setOnSingleClickListener {
            when {
                checkSelfPermissionGranted() -> {
                    navigateGallery()
                }
                shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE) -> {
                    showInContextUI()
                }
                else -> {
                    requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
                }
            }
        }
        binding.tvDate.setOnSingleClickListener {
            openDatePickerDialog()
        }
        binding.ivExit.setOnClickListener {
            requireActivity().onBackPressed()
        }
        binding.layoutRewind.setOnClickListener {
            viewModel.parseDate()
            parentFragmentManager.commit {
                MySoundPlayer.play(MySoundPlayer.SOUND_PLAYER)
                replace<TimeMachineLottieFragment>(R.id.fcv_time_machine)
            }
        }
    }

    private fun initTvDateView() {
        val today = getTodayString()
        val todayTokenized = today.split('.')
        with(binding) {
            tvTodayYear.text = todayTokenized[0]
            tvTodayMonth.text = todayTokenized[1]
            tvTodayDay.text = todayTokenized[2]
        }
    }

    private fun openDatePickerDialog() {
        val datePickerDialog = DatePickerDialog(
            requireContext(),
            R.style.MySpinnerDatePickerStyle,
            { _, year, month, day ->
                viewModel.date.value = "${year}.${String.format("%02d", month + 1)}.${String.format("%02d",day)}"
            },
            requireNotNull(viewModel.date.value).split('.')[0].toInt(),
            requireNotNull(viewModel.date.value).split('.')[1].toInt() - 1,
            requireNotNull(viewModel.date.value).split('.')[2].toInt(),
        )
        val dateString = "20000101"
        val simpleDateFormat = SimpleDateFormat("yyyyMMdd")
        val date: Date = simpleDateFormat.parse(dateString) as Date
        val startDate = date.time
        with(datePickerDialog) {
            datePicker.minDate = startDate
            datePicker.maxDate = System.currentTimeMillis()
            setCanceledOnTouchOutside(false)
            show()
        }
    }

    private fun checkSelfPermissionGranted(): Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.READ_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun navigateGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = MediaStore.Images.Media.CONTENT_TYPE
        galleryLauncher.launch(intent)
    }

    private fun showInContextUI() {
        AlertDialog.Builder(requireContext())
            .setTitle("권한 동의 필요")
            .setMessage("프로필 사진 수정을 위해 갤러리 접근 권한이 필요합니다.")
            .setPositiveButton("동의") { _, _ ->
                requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
            }
            .setNegativeButton("거부") { _, _ ->
                requireContext().shortToast("갤러리 접근 권한이 없습니다.")
            }
            .create()
            .show()
    }

    private fun observeData() {
        with(viewModel) {
            imageUri.observe(viewLifecycleOwner) {
                binding.ivPicture.clipToOutline = true
                Glide.with(binding.ivPicture)
                    .load(it)
                    .into(binding.ivPicture)
                setDateFromImage(it)
            }
            date.observe(viewLifecycleOwner) {
                binding.tvDate.text = it
            }
        }
    }

    private fun setDateFromImage(imageUri: Uri) {
        val cursor: Cursor = requireNotNull(
            requireActivity().contentResolver.query(
                imageUri,
                null,
                null,
                null,
                null
            )
        )
        cursor.moveToFirst()
        val columnIndexDateAdded: Int =
            cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATE_ADDED)
        val dateAdded: String = requireNotNull(cursor.getString(columnIndexDateAdded))
        val formatter = SimpleDateFormat("yyyy.MM.dd")
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = dateAdded.toLong() * 1000
        viewModel.date.value = formatter.format(calendar.time).toString()
    }

    companion object {
        fun newInstance() = TimeMachineImagePickerFragment()
    }
}