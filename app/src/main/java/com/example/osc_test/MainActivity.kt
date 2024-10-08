package com.example.osc_test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.osc_test.SampleScreen.HelloWorldScreen
import com.example.osc_test.SampleScreen.SampleScreen
import com.example.osc_test.SampleScreen.SampleScreenUiState
import com.example.osc_test.SampleScreen.SampleScreenViewModel
import com.example.osc_test.ui.theme.OSC_testTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val vm: SampleScreenViewModel by viewModels()

        setContent {
            val uiState by vm.uiState.collectAsState()

            OSC_testTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HelloWorldScreen(
                        modifier = Modifier.padding(innerPadding),
                        sendMessage = { vm.helloWorld() },
                        moveForward = { vm.moveForward() }
                    )
                  //SampleScreen(
                  //    uiState = uiState,
                  //    modifier = Modifier.padding(innerPadding),
                  //    inputAddress = { vm.onIpAddressChanged(it) },
                  //    inputPort = { vm.onPortChanged(it) },
                  //    inputMessage = { vm.onMessageChanged(it) },
                  //    onSendButtonClick = { vm.onSendOSCMessage() }
                  //)
                }
            }
        }
    }
}
