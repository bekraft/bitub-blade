package bitub.dto.common

import java.nio.ByteBuffer
import java.util.UUID

import com.google.protobuf.ByteString

object Guids {
	private[Guids] def fromUUIDtoByteArray(uuid: UUID): Array[Byte] = {
		val bb: ByteBuffer = ByteBuffer.wrap(new Array[Byte](16))
		bb.putLong(uuid.getMostSignificantBits)
		bb.putLong(uuid.getLeastSignificantBits)
		bb.array()
	}

	private[Guids] def fromByteArrayToUUID(bytes: Array[Byte]): UUID = {
		val byteBuffer = ByteBuffer.wrap(bytes)
		val high = byteBuffer.getLong
		val low = byteBuffer.getLong
		new UUID(high, low)
	}

	def newRandomGuid(): Guid = Guid(ByteString.copyFrom(fromUUIDtoByteArray(UUID.randomUUID())))
	def fromUUID(uuid: UUID): Guid = Guid(ByteString.copyFrom(fromUUIDtoByteArray(uuid)))
	def toUUID(guid: Guid): UUID = fromByteArrayToUUID(guid.raw.toByteArray)

	object implicits {
		import scala.language.implicitConversions

		implicit def fromUUID(uuid: UUID): Guid = Guids.fromUUID(uuid)
		implicit def toUUID(guid: Guid): UUID = Guids.toUUID(guid)
	}
}
