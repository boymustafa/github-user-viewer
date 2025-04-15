# GitHub User Viewer

A clean and modern Android app built with **Jetpack Compose**, **Hilt**, and **Retrofit** that allows you to:

- 🔍 Search GitHub users
- 👤 View detailed profiles
- 📦 See repositories (excluding forks)
- 🌐 Open repos in a browser

### 🚀 Features

- Built with **Jetpack Compose**
- Follows **MVVM architecture**
- Dependency Injection using **Hilt**
- REST API integration with **Retrofit**
- Image loading with **Coil**
- Navigation with **Navigation-Compose**
- Proper handling of **WindowInsets** (status bar & nav bar)

### 📦 Tech Stack

- Kotlin 1.9.23
- Jetpack Compose BOM 2024.09.00
- Hilt 2.48
- Retrofit 2.9
- Coil
- Compose Material 3

### 🧠 Notes

- Uses `Scaffold` to ensure consistent spacing for status and navigation bars
- All repositories shown are **non-forked**
- Easily extendable to include bookmarks, theming, or pagination

