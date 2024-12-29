plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("androidx.navigation.safeargs")
    id("com.google.devtools.ksp")
    id("com.google.gms.google-services")

}

android {
    namespace = "com.example.note_tab"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.note_tab"
        minSdk = 28
        targetSdk = 355
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        viewBinding = true
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}
val nav_version = "2.8.4"
dependencies {
    implementation (libs.lottie)
    implementation(libs.firebase.bom)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.firebase.auth)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui)
    implementation(libs.dotsindicator)
    implementation(libs.play.services.auth)
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)


}