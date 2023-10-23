package com.shiva.retrovpn.activities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.florent37.expansionpanel.ExpansionLayout
import com.github.florent37.expansionpanel.viewgroup.ExpansionLayoutCollection
import com.shiva.retrovpn.R


class FaqActivity : AppCompatActivity() {
    private var recyclerView: RecyclerView? = null
    private var backButton: ImageView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_faq)
        backButton = findViewById<ImageView>(R.id.faq_back_button)
        recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        faq_questions = resources.getStringArray(R.array.faq_questions)
        faq_answers = resources.getStringArray(R.array.faq_answers)
        recyclerView?.layoutManager = LinearLayoutManager(this)
        val adapter = RecyclerAdapter()
        recyclerView?.adapter = adapter
        backButton?.setOnClickListener { finish() }

        //fill with empty objects
        val list: MutableList<Any> = ArrayList()
        for (i in 0..29) {
            list.add(Any())
        }
        adapter.setItems(list)
    }

    class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.RecyclerHolder>() {
        private val list: MutableList<Any> = ArrayList()
        private val expansionsCollection = ExpansionLayoutCollection()

        init {
            expansionsCollection.openOnlyOne(true)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerHolder {
            return RecyclerHolder.buildFor(parent)
        }

        //Set the Faq details to the corresponding Question and answer Fields
        override fun onBindViewHolder(holder: RecyclerHolder, position: Int) {
            holder.bind(list[position])
            holder.setTextViews(
                faq_questions[position],
                faq_answers[position]
            )
            expansionsCollection.add(holder.expansionLayout)
        }

        //Get the lenght pf the array of the Faq details...
        override fun getItemCount(): Int {
            return faq_questions.size
        }

        fun setItems(items: List<Any>?) {
            list.addAll(items!!)
            notifyDataSetChanged()
        }

        class RecyclerHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var expansionLayout: ExpansionLayout
            var question_text: TextView
            var answer_text: TextView

            //Initialization
            init {
                expansionLayout = itemView.findViewById<ExpansionLayout>(R.id.expansionLayout)
                question_text = itemView.findViewById<View>(R.id.question_text) as TextView
                answer_text = itemView.findViewById<View>(R.id.answer_text) as TextView
            }

            fun bind(`object`: Any?) {
                expansionLayout.collapse(false)
            }

            fun setTextViews(ques: String?, ans: String?) {
                question_text.text = ques
                answer_text.text = ans
            }

            companion object {
                private val LAYOUT: Int = R.layout.expansion_panel_recycler_cell
                fun buildFor(viewGroup: ViewGroup): RecyclerHolder {
                    return RecyclerHolder(
                        LayoutInflater.from(viewGroup.context).inflate(LAYOUT, viewGroup, false)
                    )
                }
            }
        }
    }

    companion object {
        private lateinit var faq_questions: Array<String>
        private lateinit var faq_answers: Array<String>
    }
}
