package fitness.test.kit.presentation.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.toColorInt
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import fitness.test.kit.R
import fitness.test.kit.data.Trainers
import fitness.test.kit.databinding.HeaderLayoutBinding
import fitness.test.kit.databinding.LessonLayoutBinding
import fitness.test.kit.domain.LessonDomainEntity

class LessonsAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val TAG = "Adapter"

    private var list = ArrayList<LessonDomainEntity>()

    private var trainersList = ArrayList<Trainers>()

    fun setLessonsAndTrainersList(lessonsList: ArrayList<LessonDomainEntity>, trainersList: ArrayList<Trainers>) {
        this.list = lessonsList
        this.trainersList = trainersList
        notifyDataSetChanged()
    }


    override fun getItemViewType(position: Int): Int {
        return list.get(position).isHeader
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            0 -> {
                val inflater = LayoutInflater.from(parent.context)
                val binding = LessonLayoutBinding.inflate(inflater,parent,false)
                return LessonsHolder(binding)
            }
            else -> {
                val inflater = LayoutInflater.from(parent.context)
                val binding = HeaderLayoutBinding.inflate(inflater,parent,false)
                return Header(binding)
            }
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(list.get(position).isHeader) {
            0 -> {
                val lessonsHolder = holder as LessonsHolder
                val lessonEntity = list.get(position)
                lessonsHolder.binding.apply {
                    try {
                        colorImageView.drawable.setTint(lessonEntity.lessons.color!!.toColorInt())
                    }
                    catch (e: Exception) {
                        colorImageView.drawable.setTint("#ffffff".toColorInt())
                    }
                    lessonNameTextview.text = lessonEntity.lessons.name?.trim()
                    startTimeTextview.text = lessonEntity.lessons.startTime?.trim()
                    durationTextview.text = lessonEntity.duration
                    endTimeTextView.text = lessonEntity.lessons.endTime?.trim()
                    personTextView.text = trainersList.find { lessonEntity.lessons.coachId == it.id }?.name.also {
                        if (it.isNullOrEmpty()) {
                            personImageView.visibility = View.GONE
                            personTextView.visibility = View.GONE
                        }
                        else {
                            personImageView.visibility = View.VISIBLE
                            personTextView.visibility = View.VISIBLE
                            Glide.with(personImageView.context)
                                .load(trainersList.find { lessonEntity.lessons.coachId == it.id }?.imageUrlSmall)
                                .placeholder(personImageView.context.getDrawable(R.drawable.baseline_person_24))
                                .centerCrop()
                                .into(personImageView)
                        }
                    }
                    if (lessonEntity.lessons.place.isNullOrEmpty()) {
                        placeImageView.visibility = View.GONE
                        placeTextView.visibility = View.GONE
                    }
                    else {
                        placeImageView.visibility = View.VISIBLE
                        placeTextView.visibility = View.VISIBLE
                        placeTextView.text = lessonEntity.lessons.place?.trim()
                    }
                    lessonDescriptionTextView2.text = lessonEntity.lessons.description?.trim()
                }
            }
            else -> {
                val header = holder as Header
                val lessonEntity = list.get(position)
                header.binding.textView.text = lessonEntity.date
            }
        }
    }

    class LessonsHolder(val binding: LessonLayoutBinding): RecyclerView.ViewHolder(binding.root)

    class Header(val binding: HeaderLayoutBinding): RecyclerView.ViewHolder(binding.root)
}