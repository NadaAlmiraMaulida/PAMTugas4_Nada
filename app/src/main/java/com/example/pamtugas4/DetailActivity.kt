package com.example.pamtugas4

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivity : AppCompatActivity() {

    private lateinit var tvHeader: TextView
    private lateinit var tvTitle: TextView
    private lateinit var tvBody: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        tvHeader = findViewById(R.id.tvHeaderDetail)
        tvHeader.text = "235150701111037 - Maulidya I'tikaf Maghfiroh"

        tvTitle = findViewById(R.id.tvTitleDetail)
        tvBody = findViewById(R.id.tvBodyDetail)

        val postId = intent.getIntExtra("postId", 1)

        RetrofitClient.instance.getPostById(postId).enqueue(object : Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                if (response.isSuccessful) {
                    val post = response.body()
                    post?.let {
                        tvTitle.text = it.title
                        tvBody.text = it.body
                    }
                }
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                Toast.makeText(this@DetailActivity, "Gagal: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
