package com.example.suitmedia

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

data class StudentResponse(
    val data: List<Student>
)

data class Student(
    val id: Int,
    val first_name: String,
    val last_name: String,
    val email: String,
    val avatar: String
)

interface ApiService {
    @GET("users?page=1&per_page=10")
    fun getStudents(): Call<StudentResponse>
}


class UserAdapter(
    private val students: MutableList<Student>
) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    inner class UserViewHolder(view: android.view.View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.user_name)
        val address: TextView = view.findViewById(R.id.user_address)
        val avatar: android.widget.ImageView = view.findViewById(R.id.user_avatar)

        fun bind(student: Student) {
            name.text = "${student.first_name} ${student.last_name}"
            address.text = student.email
            Glide.with(itemView).load(student.avatar).into(avatar)
        }
    }

    override fun onCreateViewHolder(parent: android.view.ViewGroup, viewType: Int): UserViewHolder {
        val view = android.view.LayoutInflater.from(parent.context)
            .inflate(R.layout.item_user, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(students[position])
    }

    override fun getItemCount() = students.size

    fun updateData(newStudents: List<Student>) {
        students.clear()
        students.addAll(newStudents)
        notifyDataSetChanged()
    }
}


class SecondActivity : AppCompatActivity() {
    private lateinit var adapter: UserAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val userNameTextView = findViewById<TextView>(R.id.userNameTextView)
        userNameTextView.text = intent.getStringExtra("USER_NAME") ?: "User"


        recyclerView = findViewById(R.id.recyclerView)
        adapter = UserAdapter(mutableListOf())
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        fetchStudents()
    }

    private fun fetchStudents() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://reqres.in/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(ApiService::class.java)
        service.getStudents().enqueue(object : Callback<StudentResponse> {
            override fun onResponse(call: Call<StudentResponse>, response: Response<StudentResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        adapter.updateData(it.data)
                    }
                }
            }

            override fun onFailure(call: Call<StudentResponse>, t: Throwable) {
            }
        })
    }
}
