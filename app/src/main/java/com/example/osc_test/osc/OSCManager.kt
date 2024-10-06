package com.example.osc_test.osc

import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress
import java.nio.ByteBuffer
import java.nio.charset.Charset

class OSCManager(private val ipAddress: String, private val port: Int) {
    private val inetAddress = InetAddress.getByName(ipAddress)
    private val socket = DatagramSocket()

    // OSC send method
    fun sendOSCMessage(address: String, arguments: List<Any>) {
        Thread {
            try {
                val messageBytes = createOSCMessage(address, arguments)
                val packet = DatagramPacket(messageBytes, messageBytes.size, inetAddress, port)
                socket.send(packet)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }.start()
    }

    // OSC message creation method
    private fun createOSCMessage(address: String, arguments: List<Any>): ByteArray {
        val addressBytes = oscStringToBytes(address)
        val typeTagBytes = oscStringToBytes("," + getTypeTag(arguments))
        val argumentsBytes = arguments.flatMap { oscArgumentToBytes(it) }.toByteArray()

        return addressBytes + typeTagBytes + argumentsBytes
    }

    // OSC string to bytes conversion method
    private fun oscStringToBytes(str: String): ByteArray {
        val strBytes = str.toByteArray(Charset.forName("UTF-8"))
        val padding = (4 - strBytes.size % 4) % 4
        return strBytes + ByteArray(padding)
    }

    // OSC argument to bytes conversion method
    private fun getTypeTag(arguments: List<Any>): String {
        return arguments.joinToString("") {
            when (it) {
                is Int -> "i"
                is Float -> "f"
                is String -> "s"
                else -> throw IllegalArgumentException("Unsupported argument type")
            }
        }
    }

    // OSC argument to bytes conversion method
    private fun oscArgumentToBytes(arg: Any): List<Byte> {
        return when (arg) {
            is Int -> ByteBuffer.allocate(4).putInt(arg).array().toList()
            is Float -> ByteBuffer.allocate(4).putFloat(arg).array().toList()
            is String -> oscStringToBytes(arg).toList()
            else -> throw IllegalArgumentException("Unsupported argument type")
        }
    }

    // OSC close method
    fun close() {
        socket.close()
    }
}