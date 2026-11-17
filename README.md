# Norm-Launcher
A "Normal" Android Launcher 👍

A plain Android home-screen launcher, nothing fancy: it lists every installed
app in a grid and launches whichever one you tap.

## Building

Open the project in Android Studio, or just push to `main` — a GitHub Actions
workflow (`.github/workflows/build.yml`) builds a debug APK automatically on
every push and attaches it to a GitHub Release, and also leaves it as a
downloadable workflow artifact under the **Actions** tab.

## Install

Grab `app-debug.apk` from the latest release (or from the Actions run's
artifacts), copy it to your phone, and open it — Android will offer to
install it as an unsigned debug build (you'll need "install unknown apps"
enabled for whatever app you used to open it). Once installed, go to
Settings → Apps → Default apps → Home app and pick it.
