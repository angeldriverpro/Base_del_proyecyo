package com.example.prueba_map

import android.accounts.AccountManager
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.prueba_map.databinding.ActivityLoginBinding
import com.huawei.hms.support.account.AccountAuthManager
import com.huawei.hms.support.account.request.AccountAuthParams
import com.huawei.hms.support.account.request.AccountAuthParamsHelper
import com.huawei.hms.support.account.service.AccountAuthService

class login : AppCompatActivity() {
    val TAG = "adx2099IDActivity"
    private var mAuthManager:AccountAuthService?=null
    private var mAuthParam : AccountAuthParams?=null

    private lateinit var binding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val textView: TextView = findViewById(R.id.textView)
        val actor:String

        intent.extras?.getString("actor").let {
            actor = it.toString()

            Toast.makeText(this, actor, Toast.LENGTH_SHORT).show()
        }
        binding.btnRegistrarme.setOnClickListener{

            if (actor == "cliente"){
                val inten = Intent(this,registro_usuario::class.java)
                startActivity(inten)
            }


        }



        binding.BtnInicio.setOnClickListener{



            val pref = applicationContext.getSharedPreferences("datos",0)
            val correo = binding.etEmail.text.toString()
            

            val pass = binding.etPassword.toString()


            var email =pref.getString("correo ${correo}", "no correo")
            val password = pref.getString("contraseña ${correo}", "no contraseña")

            if(binding.etEmail.text.isNotBlank() && binding.etPassword.text.isNotBlank()){
                if(correo==email){
                   if (binding.etPassword.text.toString()==password){
                       Toast.makeText(this, "Bienvenido", Toast.LENGTH_SHORT).show()
                   }

                }
            }else{
                Toast.makeText(this, "Faltan datos", Toast.LENGTH_SHORT).show()
            }



        }

/*------------------------------LOGIN---------------------------*/
       // val database = AppDatabase.getDatabase(this)




/*-------------------------------------------------------------------*/



       // binding.iniciar.setOnClickListener(mOnClickListener)
    }

    /*private fun signIn(){
        mAuthParam = AccountAuthParamsHelper(AccountAuthParams.DEFAULT_AUTH_REQUEST_PARAM)
            .setIdToken()
            .setProfile()
            .setAccessToken()
            .createParams()
        mAuthManager = AccountAuthManager.getService(this@login, mAuthParam)
        startActivityForResult(mAuthManager?.signInIntent, Constant.REQUEST_SIGN_IN_LOGIN)
    }

    private val mOnClickListener: View.OnClickListener=object :View.OnClickListener{
        override fun onClick(v: View?) {
            when(v?.id){
                R.id.iniciar -> signIn()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode==Constant.REQUEST_SIGN_IN_LOGIN){
            val authAccoutTask = AccountAuthManager.parseAuthResultFromIntent(data)
            if (authAccoutTask.isSuccessful){
               val authAccount = authAccoutTask.result
               Log.d(TAG, authAccount.getDisplayName()+"signIn success")
                Log.d(TAG, "Acces Token"+authAccount.getAccessToken())
            }else{
                Log.d(TAG, "Sign in fallo")
            }
        }*/
    //}





}