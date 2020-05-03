package hu.ait.aitpuritytest

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    fun loginClick(v: View){
        if (!isFormValid()) {
            return
        }
        FirebaseAuth.getInstance().signInWithEmailAndPassword(
            etEmail.text.toString(), etPassword.text.toString()
        ).addOnSuccessListener {
            //startActivity(Intent(this@LoginActivity, ForumActivity::class.java))
            Toast.makeText(this@LoginActivity,
                "logged in",
                Toast.LENGTH_LONG).show()
        }.addOnFailureListener{
            Toast.makeText(this@LoginActivity,
                "Login error: ${it.message}",
                Toast.LENGTH_LONG).show()
        }

    }

    fun registerClick(v: View){
        if (!isFormValid()) {
            return
        }

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(
            etEmail.text.toString(), etPassword.text.toString()
        ).addOnSuccessListener {
            Toast.makeText(this@LoginActivity,
                "Registration Ok",
                Toast.LENGTH_LONG).show()
        }. addOnFailureListener {
            Toast.makeText(this@LoginActivity,
                "Error: ${it.message}",
                Toast.LENGTH_LONG).show()
        }

    }

    fun isFormValid(): Boolean {
        return when {
            etEmail.text.isEmpty() -> {
                etEmail.error = "This field cannot be empty"
                false
            }
            etPassword.text.isEmpty() -> {
                etPassword.error = "The password cannot be empty"
                false
            }
            else -> true
        }
    }

}
