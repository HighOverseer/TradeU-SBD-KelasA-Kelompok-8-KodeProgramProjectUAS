package com.example.tradeu.data.entities

import androidx.room.*
import java.util.Date

@Entity(tableName = "Barang")
data class Item(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id_barang")
    val itemId:Long=0,
    @ColumnInfo(name="id_penjual")
    val sellerId:Long,
    @ColumnInfo(name="nama_barang")
    var itemName:String,
    @ColumnInfo(name="foto_produk")
    var itemPhoto:String,
    @ColumnInfo(name="harga")
    var price:Long,
    @ColumnInfo(name="stok")
    var stock:Short,
    @ColumnInfo(name="tgl_publish")
    var publishDate:Date,
    @ColumnInfo(name="deskripsi")
    var description:String
)