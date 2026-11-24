# IPTV-Glass

Android TV IPTV home-screen starter for Android 5.1.1 (API 22).

## Current scope
- Apple TV-inspired dark glass home screen
- D-pad focusable category tiles
- Android 5.1.1 minimum SDK
- GitHub Actions debug APK workflow

**Important:** This starter currently contains the home screen only. It does not yet parse M3U playlists or play channels. Add only playlists and streams you are authorized to use.

## Build
GitHub Actions runs `gradle assembleDebug` using Java 17 and Gradle 7.6.4. Download the `IPTV-Glass-APK` artifact from the successful workflow run.

## Next development steps
1. Add M3U/M3U8 playlist import and parsing.
2. Add an Android-5.1.1-compatible HLS player.
3. Add categories, search, favorites, watch history and EPG.
4. Add optional cloud backup after configuring a secure backend.

## Cloud storage note
Do not put Cloudflare R2 secret keys in the Android app or public repository. Use a backend to issue short-lived upload/download permissions. Configure bucket access, quotas and costs in Cloudflare's dashboard before enabling sync.
