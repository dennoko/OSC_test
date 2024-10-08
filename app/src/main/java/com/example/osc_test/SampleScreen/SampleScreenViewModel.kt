package com.example.osc_test.SampleScreen

import androidx.lifecycle.ViewModel
import com.example.osc_test.osc.OSCManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SampleScreenViewModel: ViewModel() {
    private var _uiState = MutableStateFlow(SampleScreenUiState())
    val uiState = _uiState.asStateFlow()

    fun onIpAddressChanged(ipAddress: String) {
        _uiState.value = _uiState.value.copy(ipAddress = ipAddress)
    }

    fun onPortChanged(port: String) {
        _uiState.value = _uiState.value.copy(port = port)
    }

    fun onMessageChanged(message: String) {
        _uiState.value = _uiState.value.copy(message = message)
    }

    fun onSendOSCMessage() {
        val OSCManager = OSCManager(uiState.value.ipAddress, uiState.value.port.toInt())
        OSCManager.sendOSCMessage(address = uiState.value.ipAddress, arguments = listOf(uiState.value.message))
    }

    fun helloWorld() {
        // OSCManagerのインスタンスを作成
        val oscManager = OSCManager("192.168.0.14", 9000)

        // 送信するメッセージと引数を指定
        val address = "/chatbox/input"
        val arguments = listOf("Hello, World", true)

        // メッセージ送信
        oscManager.sendOSCMessage(address, arguments)

        // 送信後にリソースを解放（必要なら適切なタイミングで）
        oscManager.close()
    }

    fun moveForward() {
        // OSCManagerのインスタンスを作成
        val oscManager = OSCManager("192.168.0.14", 9000)

        // 送信するメッセージと引数を指定
        val address = "/input/Vertical"
        val arguments = listOf(1.0f)

        // メッセージ送信
        oscManager.sendOSCMessage(address, arguments)

        // 送信後にリソースを解放（必要なら適切なタイミングで）
        oscManager.close()
    }
}