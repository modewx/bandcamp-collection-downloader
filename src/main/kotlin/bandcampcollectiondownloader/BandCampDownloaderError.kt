package bandcampcollectiondownloader

open class BandCampDownloaderError(s: String) : Exception(s)

/** An error not worth retrying (e.g. Bandcamp's bot check): Util.retry gives up at once. */
class GiveUpError(s: String) : BandCampDownloaderError(s)
