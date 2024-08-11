plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("com.google.devtools.ksp")
    id ("kotlin-kapt")
}

android {
    namespace = "com.motivation.inspiria"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.motivation.inspiria"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {


    dependencies {

        implementation(libs.androidx.core.ktx)
        implementation(libs.androidx.lifecycle.runtime.ktx)
        implementation(libs.androidx.activity.compose)
        implementation(platform(libs.androidx.compose.bom))
        implementation(libs.androidx.ui)
        implementation(libs.androidx.ui.graphics)
        implementation(libs.androidx.ui.tooling.preview)
        implementation(libs.androidx.material3)

        implementation("androidx.core:core-splashscreen:1.0.1")
        implementation("com.google.accompanist:accompanist-pager:0.28.0")
        implementation("com.google.accompanist:accompanist-pager-indicators:0.28.0")
        implementation("com.google.accompanist:accompanist-flowlayout:0.24.3-alpha")

        //Room Database
        implementation("androidx.room:room-runtime:2.6.1")
        implementation(libs.androidx.animation.graphics.android)
        implementation(libs.androidx.lifecycle.runtime.compose.android)
        implementation(libs.androidx.work.runtime.ktx)
        implementation(libs.androidx.appcompat)

        val dataStoreVersion = "1.0.0"
        implementation("androidx.datastore:datastore-core:$dataStoreVersion")
        implementation("androidx.datastore:datastore-preferences:$dataStoreVersion")

        annotationProcessor ("androidx.room:room-compiler:2.6.1")
        implementation("androidx.room:room-ktx:2.6.1")
        ksp("androidx.room:room-compiler:2.6.1")

        implementation("io.insert-koin:koin-core:3.1.5")
        implementation("io.insert-koin:koin-android:3.1.5")
        implementation("io.insert-koin:koin-androidx-compose:3.1.5")
        implementation("com.google.accompanist:accompanist-permissions:0.31.1-alpha")
        implementation("org.threeten:threetenbp:1.6.8")

        implementation("com.google.accompanist:accompanist-systemuicontroller:0.30.1")


        implementation(libs.androidx.navigation.compose)
        testImplementation(libs.junit)
        androidTestImplementation(libs.androidx.junit)
        androidTestImplementation(libs.androidx.espresso.core)
        androidTestImplementation(platform(libs.androidx.compose.bom))
        androidTestImplementation(libs.androidx.ui.test.junit4)
        debugImplementation(libs.androidx.ui.tooling)
        debugImplementation(libs.androidx.ui.test.manifest)
    }


}