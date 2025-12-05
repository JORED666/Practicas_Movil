package com.project.intents_camera_app

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.project.intents_camera_app.ui.theme.Intents_camera_appTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Intents_camera_appTheme {
                CameraScreen()
            }
        }
    }
}

@Composable
fun CameraScreen() {

    val context = LocalContext.current
    var photoBitmap by remember { mutableStateOf<Bitmap?>(null) }

    // Launcher para obtener la imagen desde la cámara
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {

            val imageBitmap = result.data?.extras?.get("data") as? Bitmap

            if (imageBitmap != null) {
                photoBitmap = imageBitmap
                Log.d("Camera", "Foto recibida correctamente")
            } else {
                Log.e("Camera", "⚠ La cámara no devolvió una imagen")
            }

        } else {
            Log.e("Camera", "❌ No se recibió ninguna imagen")
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        // Botón para abrir cámara
        Button(
            onClick = {
                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

                val cameraApp = intent.resolveActivity(context.packageManager)

                if (cameraApp != null) {
                    launcher.launch(intent)
                } else {
                    Toast.makeText(
                        context,
                        "❌ No hay aplicación de cámara disponible",
                        Toast.LENGTH_LONG
                    ).show()

                    Log.e("Camera", "NO existe una app para manejar ACTION_IMAGE_CAPTURE")
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Abrir Cámara")
        }

        Spacer(modifier = Modifier.height(20.dp))


        photoBitmap?.let { bmp ->
            Image(
                bitmap = bmp.asImageBitmap(),
                contentDescription = "Foto tomada",
                modifier = Modifier.size(250.dp)
            )
        }
    }
}
