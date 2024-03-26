plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs")
}

android {
    namespace = "com.example.wheatherapptest"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.wheatherapptest"
        minSdk = 26
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
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures{
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //hilt
    implementation(DaggerHilt.android)
    kapt(DaggerHilt.compiler)

    //retrofit
    implementation(Retrofit.retrofit)
    implementation (Retrofit.json)

    //glide
    implementation (Glide.glide)
    implementation (AnnotationProcessor.annotationProcessor)
    val nav_version = "2.7.6"
    //navigation
    implementation (Navigation.navigationFragment)
    implementation (Navigation.navigationKtx)

    //dotsIndic
    implementation (DotsIndicator.dotsindicator)
    implementation (DotsIndicator.circleindicator)

    //vp2
    implementation (Viewpager2.viewpager2)

    //okkhtp
    implementation (Okhttp.interceptor)
    implementation (Okhttp.okhttp)

    //Coroutines
    implementation (Coroutines.coroutines)

    //Viewbindingpropertydelegate
    implementation (Viewbindingpropertydelegate.viewbindingpropertydelegate)

    implementation (LottieAnimations.lottieAnimations)
    implementation ("com.github.PhilJay:MPAndroidChart:v3.1.0")
    testImplementation ("org.junit.jupiter:junit-jupiter:5.8.2")
    testImplementation ("org.mockito:mockito-core:4.0.0")
    testImplementation ("org.mockito:mockito-inline:4.0.0")
    testImplementation ("org.mockito.kotlin:mockito-kotlin:4.0.0")
    testImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.5.2")
}