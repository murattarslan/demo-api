package restaurant.core.text

class EN: Language() {
    override val baseErrorTitle: String
        get() = "(english) Hata"
    override val sqlErrorTitle: String
        get() = "(english) Sql Hatası"
    override val illegalStateErrorTitle: String
        get() = "(english) İstenmeyen Durum"
    override val illegalArgumentErrorTitle: String
        get() = "(english) İstenmeyen Veri"
    override val classCastErrorTitle: String
        get() = "(english) Dönüşüm Hatası"
    override val indexOfBoundErrorTitle: String
        get() = "(english) İndex Boyut Uyumsuzluğu"
    override val noSuchElementErrorTitle: String
        get() = "(english) Veri Bulunamadı"
    override val nullPointerErrorTitle: String
        get() = "(english) Boş Veri Hatası"

    override val baseErrorMessage: String
        get() = "(english) Bilinmeyen bir hata oluştu"
    override val sqlErrorMessage: String
        get() = "(english) SQL sorgusu temelli bir hata oluştu"
    override val illegalStateErrorMessage: String
        get() = "(english) İstenilenin dışında bir durum oluştu"
    override val illegalArgumentErrorMessage: String
        get() = "(english) İstenilenin dışında bir veri var"
    override val classCastErrorMessage: String
        get() = "(english) Mevcut nesne olması gereken nesneye dönüştürülemedi"
    override val indexOfBoundErrorMessage: String
        get() = "(english) Mevcut index liste boyutunu aşıyor"
    override val noSuchElementErrorMessage: String
        get() = "(english) Aranan veri bulunamadı"
    override val nullPointerErrorMessage: String
        get() = "(english) Olmayan bir nesneden veri alınamaz"

    override val getAllCategorySuccessTitle: String
        get() = "(english) Başarılı"
    override val getAllCategorySuccessMessage: String
        get() = "(english) Tüm kategori listesi başarıyla alındı"

    override val getAllUserSuccessTitle: String
        get() = "(english) Başarılı"
    override val getAllUserSuccessMessage: String
        get() = "(english) Tüm kullanıcı listesi başarıyla alındı"

    override val checkUserSuccessTitle: String
        get() = "(english) Başarılı"
    override val checkUserSuccessMessage: String
        get() = "(english) Kullanıcı bulundu ve parola eşleşti"

    override val checkUserWrongPasswordTitle: String
        get() = "(english) Başarısız"
    override val checkUserWrongPasswordMessage: String
        get() = "(english) Parola ve kullanıcı eşleşmiyor"

    override val checkUserNotFoundTitle: String
        get() = "(english) Başarısız"
    override val checkUserNotFoundMessage: String
        get() = "(english) Bu kullanıcı kayıtlı değil"
}