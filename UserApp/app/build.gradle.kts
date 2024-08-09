val recyclerview: Any
    get() {
        TODO()
    }

plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.userapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.userapp"
        minSdk = 29
        targetSdk = 34
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
}

dependencies {


    implementation (androidx.recyclerview ;recyclerview;1.2.1)
    implementation (com.squareup.retrofit2 retrofit;2.9.0)
    implementation (com.squareup.retrofit2:converter-gson;2.9.0)
    implementation (androidx.room:room-runtime
    annotationProcessor(libs.room.compiler)
    annotationProcessor(libs.room.compiler);2.4.0)
    val compiler = null
    annotationProcessor (androidx.room:room-compiler;2.4.0)
    implementation (com.squareup.picasso:picasso;2.71828)
    implementation (androidx.lifecycle:lifecycle-extensions;2.2.0),


}