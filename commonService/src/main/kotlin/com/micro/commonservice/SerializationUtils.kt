package com.micro.commonservice

import java.io.ByteArrayOutputStream
import java.io.ObjectOutputStream
import java.io.ByteArrayInputStream
import java.io.ObjectInputStream

class SerializationUtils {

     fun serializeEntity(entity: Any): ByteArray {
        val byteArrayOutputStream = ByteArrayOutputStream()
        val objectOutputStream = ObjectOutputStream(byteArrayOutputStream)
        objectOutputStream.writeObject(entity)
        objectOutputStream.close()
        return byteArrayOutputStream.toByteArray()
    }

    fun deserializeEntity(serializedData: ByteArray): Any {
        val byteArrayInputStream = ByteArrayInputStream(serializedData)
        val objectInputStream = ObjectInputStream(byteArrayInputStream)
        val deserializedEntity = objectInputStream.readObject() as Any
        objectInputStream.close()
        return deserializedEntity
    }
}