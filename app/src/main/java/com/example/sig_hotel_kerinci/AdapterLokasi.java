package com.example.sig_hotel_kerinci;

public class AdapterLokasi {

    private String lokasiid;
    private String publisher;

    private String nama;
    private String Alamat;
    private String nomor;

    private String tipe1;
    private String tipe2;
    private String tipe3;

    private String harga1;
    private String harga2;
    private String harga3;

    private String latitude;
    private String longitude;

    private String imageUrl;
    private String video;

    public AdapterLokasi(String lokasiid, String publisher, String nama, String alamat, String nomor, String tipe1, String tipe2, String tipe3, String harga1, String harga2, String harga3, String latitude, String longitude, String imageUrl, String video) {
        this.lokasiid = lokasiid;
        this.publisher = publisher;
        this.nama = nama;
        Alamat = alamat;
        this.nomor = nomor;
        this.tipe1 = tipe1;
        this.tipe2 = tipe2;
        this.tipe3 = tipe3;
        this.harga1 = harga1;
        this.harga2 = harga2;
        this.harga3 = harga3;
        this.latitude = latitude;
        this.longitude = longitude;
        this.imageUrl = imageUrl;
        this.video = video;
    }

    public AdapterLokasi() {
    }

    public String getLokasiid() {
        return lokasiid;
    }

    public void setLokasiid(String lokasiid) {
        this.lokasiid = lokasiid;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return Alamat;
    }

    public void setAlamat(String alamat) {
        Alamat = alamat;
    }

    public String getNomor() {
        return nomor;
    }

    public void setNomor(String nomor) {
        this.nomor = nomor;
    }

    public String getTipe1() {
        return tipe1;
    }

    public void setTipe1(String tipe1) {
        this.tipe1 = tipe1;
    }

    public String getTipe2() {
        return tipe2;
    }

    public void setTipe2(String tipe2) {
        this.tipe2 = tipe2;
    }

    public String getTipe3() {
        return tipe3;
    }

    public void setTipe3(String tipe3) {
        this.tipe3 = tipe3;
    }

    public String getHarga1() {
        return harga1;
    }

    public void setHarga1(String harga1) {
        this.harga1 = harga1;
    }

    public String getHarga2() {
        return harga2;
    }

    public void setHarga2(String harga2) {
        this.harga2 = harga2;
    }

    public String getHarga3() {
        return harga3;
    }

    public void setHarga3(String harga3) {
        this.harga3 = harga3;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }
}