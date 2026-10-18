
# CyberDrive EV — Car Infotainment UI

An Android Automotive infotainment interface concept built with **Jetpack Compose** and **Kotlin**, featuring custom UI components, responsive state management, and an icon design exploration showcasing a **Frutiger Aero** (2000s skeuomorphic glass) aesthetic.

---

## 🚗 Overview

**CyberDrive EV** is a high-performance, dark-themed automotive head unit UI designed for 1024x600 widescreen display form factors. The application provides an intuitive dual-zone climate control system, media player with active playlist tracking, turn-by-turn navigation preview, vehicle telemetry (speedometer, TPMS, drive modes), phone dialer, and system ambient light customization.

---

## ✨ Features & Screens

- **🏠 Home Dashboard:** Multi-widget main interface consolidating navigation turn cards, active media playback with interactive controls, vehicle speed telemetry, and dual-zone climate summary.
- **❄️ Dual-Zone Climate Control:** Individual driver & passenger temperature dials, 3-level seat heating, 7-level fan speed selector, A/C, AUTO, SYNC, Recirculation, and mirror defrost toggles.
- **🎵 Media Player:** Album art view, playback progress bar, volume control slider, track skipping, and an interactive "Up Next" playlist.
- **🗺️ Navigation & Map:** Canvas-rendered route preview, location search with voice input, quick navigation chips (*Home, Work, EV Charging, Coffee*), turn-by-turn direction card, and map controls.
- **⚡ Vehicle Telemetry & TPMS:** Large digital speedometer gauge, drive mode selector (*ECO, COMFORT, SPORT, BOOST*), real-time battery % and estimated range, 4-wheel Tire Pressure Monitoring System (TPMS PSI), headlight toggle, and central door lock control.
- **📞 Phone Dialer & Speed Dial:** Touch dialpad with backspace, active call timer, speed dial contact shortcuts, and one-tap calling.
- **⚙️ Settings & Ambient Preferences:** Dynamic LED ambient light theme selector (*Cyan, Electric Blue, Amber Gold, Emerald Green, Neon Purple, Ruby Red*), display brightness slider, speed unit toggle (*KM/H vs MPH*), and system info.
- **📊 Persistent Navigation Dock & Status Bar:** Top bar displaying drive mode, lock state, outside weather temp, clock, battery/range, Bluetooth, and Wi-Fi. Bottom dock providing quick climate controls and screen navigation.

---

## 🎨 Icon Design Exploration: Frutiger Aero Style

This project includes a design study exploring **Frutiger Aero / Aqua UI** skeuomorphism inside modern Jetpack Compose.

### What is Frutiger Aero?
Frutiger Aero (popular approx. 2004–2013) is a vibrant visual design language characterized by glossy glass surfaces, tropical aqua gradients, curved reflection glares, 3D depth, and tactile skeuomorphic elements.

### Implementation Details (`FrutigerAeroIcon.kt`)
Standard flat Material vector icons are wrapped inside custom 3D glass containers that render:
- **Top Curved Glass Reflection Glare:** A semi-elliptical gradient reflection (`Brush.verticalGradient` with white glass shine) positioned at the top half of the orb/tile.
- **Multi-Stop Vibrant Gloss Gradients:** Layered background gradients blending aqua cyan (`#00E5FF`), vibrant accent colors, and translucent deep ocean tones.
- **Reflective Rim Highlight:** Vertical gradient borders shifting from bright white top highlights (`Color.White.copy(alpha = 0.75f)`) to subtle translucent glows.
- **Ambient Depth Shadow:** Soft outer elevation shadows and reflective bottom water/glass highlights.

```kotlin
FrutigerAeroIcon(
    imageVector = Icons.Default.MusicNote,
    contentDescription = "Media",
    iconSize = 22.dp,
    containerSize = 38.dp,
    tint = Color.White,
    glossColor = Color(0xFF00E5FF),
    isSelected = true
)
```

---

## 🛠️ Tech Stack & Architecture

- **Language:** Kotlin
- **UI Framework:** Jetpack Compose (Material 3)
- **State Management:** `ViewModel`, Kotlin `StateFlow`, `collectAsState()`
- **Architecture:** Unidirectional Data Flow (UDF) / MVVM
- **Graphics:** Compose `Canvas`, Custom Path Drawing, Gradient Brushes, Dynamic Color Animation (`animateColorAsState`)

---

## 📁 Project Structure
<img width="1192" height="748" alt="image" src="https://github.com/user-attachments/assets/851de024-12b4-4b6e-aa52-c7a8308a66e0" />

```
com.example.car_ui
├── model/
│   ├── CarState.kt             # Data models (MediaTrack, ClimateState, VehicleState, SettingsState, NavScreen)
├── ui/
│   ├── CarMainScreen.kt        # Main screen container & layout
│   ├── components/
│   │   ├── FrutigerAeroIcon.kt # Custom Frutiger Aero Glossy Glass Icon System
│   │   ├── NavigationDock.kt   # Persistent bottom dock & quick temp controls
│   │   └── TopStatusBar.kt     # Top vehicle status & system bar
│   ├── screens/
│   │   ├── HomeScreen.kt       # Dashboard widget layout
│   │   ├── MediaScreen.kt      # Audio player & playlist
│   │   ├── ClimateScreen.kt    # Dual-zone climate controls
│   │   ├── NavigationScreen.kt # Map preview & turn-by-turn guidance
│   │   ├── VehicleScreen.kt    # Speedometer, drive modes & TPMS
│   │   ├── PhoneScreen.kt      # Keypad dialer & speed dial contacts
│   │   └── SettingsScreen.kt   # Ambient light themes & display settings
│   └── theme/
│       ├── Color.kt            # Automotive dark theme palette & accent colors
│       ├── Theme.kt            # Compose Material3 Theme definition
│       └── Type.kt             # Typography
└── viewmodel/
    └── CarUiViewModel.kt       # Central state management & actions
```

---

## 🚀 Getting Started

### Prerequisites
- Android Studio Ladybug (2024.2.1) or newer
- JDK 17
- Android SDK 34 (Android 14 / Automotive)

### Building & Running
1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/car-ui.git
   ```
2. Open the project in **Android Studio**.
3. Sync Gradle dependencies.
4. Select an Android Emulator or connected device (recommended resolution: 1024x600 landscape widescreen tablet or Android Automotive AVD).
5. Run the `app` module (`Shift + F10`).

---

## 📄 License

Distributed under the MIT License. See `LICENSE` for details.
