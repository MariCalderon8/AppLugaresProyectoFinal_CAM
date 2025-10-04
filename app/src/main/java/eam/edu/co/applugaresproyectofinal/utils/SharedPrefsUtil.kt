package eam.edu.co.applugaresproyectofinal.utils

import android.content.Context
import androidx.core.content.edit
import eam.edu.co.applugaresproyectofinal.model.Role

object SharedPrefsUtil {

    fun savePreferences(context: Context, userId: String, rol: Role) {
        val sharedPreferences = context.getSharedPreferences("sesion", Context.MODE_PRIVATE)
        sharedPreferences.edit {
            putString("userId", userId)
            putString("role", rol.toString())
        }
    }

    fun clearPreferences(context: Context) {
        val sharedPreferences = context.getSharedPreferences("sesion", Context.MODE_PRIVATE)
        sharedPreferences.edit{
            clear()
        }
    }

    fun getPreferences(context: Context): Map<String, String> {
        val sharedPreferences = context.getSharedPreferences("sesion", Context.MODE_PRIVATE)
        val userId = sharedPreferences.getString("userId", null)
        val role = sharedPreferences.getString("role", null)

        return if(userId.isNullOrEmpty() || role.isNullOrEmpty()){
            emptyMap()
        }else{
            mapOf("userId" to userId, "role" to role)
        }
    }
}
