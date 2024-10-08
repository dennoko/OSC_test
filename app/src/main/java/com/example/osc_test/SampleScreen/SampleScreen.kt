package com.example.osc_test.SampleScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SampleScreen(
    uiState: SampleScreenUiState,
    modifier: Modifier = Modifier,
    inputAddress: (String) -> Unit = {},
    inputPort: (String) -> Unit = {},
    inputMessage: (String) -> Unit = {},
    onSendButtonClick: () -> Unit = {},
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxSize()
            .then(modifier)
    ) {
        // Set IP address and port number and message
        TextField(value = uiState.ipAddress, onValueChange = { inputAddress(it) }, label = { Text("IP Address") })
        TextField(value = uiState.port, onValueChange = { inputPort(it) }, label = { Text("Port") })
        TextField(value = uiState.message, onValueChange = { inputMessage(it) }, label = { Text("Message") })

        // Send OSC message
        Button(
            onClick = { onSendButtonClick() }
        ) {
            Text(text = "Send OSC Message")
        }
    }
}

@Preview
@Composable
fun SampleScreenPreview() {
    SampleScreen(SampleScreenUiState())
}