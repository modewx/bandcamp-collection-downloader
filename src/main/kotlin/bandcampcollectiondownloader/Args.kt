package bandcampcollectiondownloader

import picocli.CommandLine
import java.nio.file.Path
import java.nio.file.Paths

data class Args(
        @CommandLine.Parameters(arity = "1..1",
                description = ["The bandcamp user account from which all releases must be downloaded."])
        var bandcampUser: String = "",

        @CommandLine.Option(names = ["--cookies-file", "-c"], required = false,
                description = [
                  "A file containing valid bandcamp credential cookies.",
                  """The file must either be obtained using the Firefox extension "Cookie Quick Manager" (https://addons.mozilla.org/en-US/firefox/addon/cookie-quick-manager/) or using the Chrome extension "cookies.txt" (https://chrome.google.com/webstore/detail/cookiestxt/njabckikapfpffapmjgojcnbfjonfjfg).""",
                  "If no cookies file is provided, cookies from the local Firefox installation are used (Windows and Linux)."
                ])
        var pathToCookiesFile: Path? = null,

        @CommandLine.Option(names = ["--audio-format", "-a"], required = false,
                description = ["The chosen audio format of the files to download (default: \${DEFAULT-VALUE}).", "Possible values: flac, wav, aac-hi, mp3-320, aiff-lossless, vorbis, mp3-v0, alac."])
        var audioFormat: String = "vorbis",

        @CommandLine.Option(names = ["--download-folder", "-d"], required = false,
                description = ["The folder in which downloaded releases must be extracted.", "The following structure is considered: <pathToDownloadFolder>/<artist>/<year> - <release>.", "(default: current folder)"])
        var pathToDownloadFolder: Path = Paths.get("."),

        @CommandLine.Option(names = ["-h", "--help"], usageHelp = true, description = ["Display this help message."])
        var help: Boolean = false,

        @CommandLine.Option(names = ["-r", "--retries"], usageHelp = false, description = ["Amount of retries for each HTTP connection (default: 3)."])
        var retries: Int = 3,

        @CommandLine.Option(names = ["-t", "--timeout"], usageHelp = false, description = ["Timeout in ms before giving up an HTTP connection (default: 50000)."])
        var timeout: Int = 50000,

        @CommandLine.Option(names = ["-e", "--skip-failed-releases"], description = ["Skip releases that fail to download after the specified number of retries."])
        var ignoreFailedReleases: Boolean = false,

        @CommandLine.Option(
                names = ["--skip-hidden", "-s"], required = false,
                description = ["Don't download hidden items of the collection."]
        )
        var skipHiddenItems: Boolean = false,

        @CommandLine.Option(names = ["-j", "--jobs"], usageHelp = false, description = ["Amount of parallel jobs (threads) to use (default: 4)."])
        var jobs: Int = 4,

        @CommandLine.Option(names = ["-v", "--version"], versionHelp = true, description = ["Display the version and exits."])
        var version: Boolean = false,
		
		@CommandLine.Option(names = ["--preorders", "-p"], required = false,
                description = ["Enables downloading preorders", "(default: false)"])
        var preorders: Boolean = false,
		
		@CommandLine.Option(names = ["--string-filter", "-w"], required = false,
                description = ["Substring search on album title or artist acting as a filter.", "If different than \"*\" it ignores cache and re-downloads albums|tracks that contain the substring.", "(default: *)"])
        var stringFilter: String = "*",
		
		@CommandLine.Option(names = ["--number", "-n"], required = false,
                description = ["The number of items to download.", "Will not consider local cache, just takes top n items from collection to download.", """(default: ${Int.MAX_VALUE})"""])
        var number: Int = Int.MAX_VALUE,
		
		@CommandLine.Option(names = ["--force", "-f"], required = false,
                description = ["Ignore cache and force download.", """(default: false)"""])
        var force: Boolean = false,

        @CommandLine.Option(names = ["--item-id", "-i"], required = false, split = ",",
                description = ["Download exactly these collection items: a<album id>, t<track id> or the item's Bandcamp URL.",
                    "Repeatable or comma-separated. Respects the cache unless --force; --string-filter and --number are ignored."])
        var itemIds: List<String> = ArrayList(),

        @CommandLine.Option(names = ["--user-agent"], required = false,
                description = ["The User-Agent sent with every request (default: the HTTP libraries' own)."])
        var userAgent: String? = null

)