package vee.consensus.spos

import com.google.common.primitives.{Bytes, Longs}
import play.api.libs.json.{JsObject, Json}
import scorex.block.BlockField
import scorex.crypto.encode.Base58


case class SposConsensusBlockField(override val value: SposConsensusBlockData)
  extends BlockField[SposConsensusBlockData] {

  override val name: String = "SPOSConsensus"

  override def bytes: Array[Byte] =
    Bytes.ensureCapacity(Longs.toByteArray(value.mintTime), 8, 0) ++
      Bytes.ensureCapacity(Longs.toByteArray(value.mintBalance), 8, 0) ++
      value.generationSignature



  override def json: JsObject = Json.obj(name -> Json.obj(
    "mintTime" -> value.mintTime,
    "mintBalance" -> value.mintBalance,
    "generationSignature" -> Base58.encode(value.generationSignature)
  ))
}
