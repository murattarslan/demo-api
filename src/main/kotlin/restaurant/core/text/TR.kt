package restaurant.core.text

class TR: Language() {
    override val baseErrorTitle: String
        get() = "Hata"
    override val sqlErrorTitle: String
        get() = "Sql Hatası"
    override val illegalStateErrorTitle: String
        get() = "İstenmeyen Durum"
    override val illegalArgumentErrorTitle: String
        get() = "İstenmeyen Veri"
    override val classCastErrorTitle: String
        get() = "Dönüşüm Hatası"
    override val indexOfBoundErrorTitle: String
        get() = "İndex Boyut Uyumsuzluğu"
    override val noSuchElementErrorTitle: String
        get() = "Veri Bulunamadı"
    override val nullPointerErrorTitle: String
        get() = "Boş Veri Hatası"

    override val baseErrorMessage: String
        get() = "Bilinmeyen bir hata oluştu"
    override val sqlErrorMessage: String
        get() = "SQL sorgusu temelli bir hata oluştu"
    override val illegalStateErrorMessage: String
        get() = "İstenilenin dışında bir durum oluştu"
    override val illegalArgumentErrorMessage: String
        get() = "İstenilenin dışında bir veri var"
    override val classCastErrorMessage: String
        get() = "Mevcut nesne olması gereken nesneye dönüştürülemedi"
    override val indexOfBoundErrorMessage: String
        get() = "Mevcut index liste boyutunu aşıyor"
    override val noSuchElementErrorMessage: String
        get() = "Aranan veri bulunamadı"
    override val nullPointerErrorMessage: String
        get() = "Olmayan bir nesneden veri alınamaz"

    override val getAllCategorySuccessTitle: String
        get() = "Başarılı"
    override val getAllCategorySuccessMessage: String
        get() = "Tüm kategori listesi başarıyla alındı"

    override val getAllUserSuccessTitle: String
        get() = "Başarılı"
    override val getAllUserSuccessMessage: String
        get() = "Tüm kullanıcı listesi başarıyla alındı"

    override val checkUserSuccessTitle: String
        get() = "Başarılı"
    override val checkUserSuccessMessage: String
        get() = "Kullanıcı bulundu ve parola eşleşti"

    override val checkUserWrongPasswordTitle: String
        get() = "Başarısız"
    override val checkUserWrongPasswordMessage: String
        get() = "Parola ve kullanıcı eşleşmiyor"

    override val checkUserNotFoundTitle: String
        get() = "Başarısız"
    override val checkUserNotFoundMessage: String
        get() = "Bu kullanıcı kayıtlı değil"
}