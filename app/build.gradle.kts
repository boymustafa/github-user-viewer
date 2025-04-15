plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("org.jetbrains.kotlin.kapt")
    alias(libs.plugins.dagger.hilt) // ✅ use alias here
}

android {
    namespace = "com.boy.githubuserviewer"
    compileSdk = 35
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.11"
    }

    defaultConfig {
        applicationId = "com.boy.githubuserviewer"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.ui.tooling.preview)
    debugImplementation(libs.androidx.ui.tooling)

    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.coil.compose)
    implementation(libs.lifecycle.viewmodel)
    implementation(libs.lifecycle.runtime)
    implementation(libs.activity.compose)
    implementation(libs.navigation.compose)
    implementation(libs.javapoet)
    implementation(libs.hilt.android)
    implementation(libs.hilt.navigation.compose)
    kapt(libs.hilt.compiler)
    kapt("com.squareup:javapoet:1.13.0")

}

configurations.configureEach {
    resolutionStrategy.eachDependency {
        if (requested.group == "com.squareup" && requested.name == "javapoet") {
            useVersion("1.13.0")
            because("Hilt's processor depends on javapoet with canonicalName() available only in 1.13.0+")
        }
    }
}