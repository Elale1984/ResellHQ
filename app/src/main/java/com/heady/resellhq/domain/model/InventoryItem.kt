package com.heady.resellhq.domain.model

import java.util.*


data class InventoryItem(
    val itemType: String,
    val brandName: String,
    val size: String,
    val purchasePrice: Double,
    val purchaseDate: Date,
    val details: String,
    val sellDate: Date,
    val sellPrice: Double
) {


}
