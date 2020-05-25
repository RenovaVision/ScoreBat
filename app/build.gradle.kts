plugins {
    id("com.android.application")
    kotlin("android")
    id("androidx.navigation.safeargs.kotlin")
    id("koin")
}

android {
    compileSdkVersion(AndroidConfig.compileSdkVersion)
    defaultConfig {
        minSdkVersion(AndroidConfig.minSdkVersion)
        targetSdkVersion(AndroidConfig.targetSdkVersion)
        versionCode = 1
        versionName = "1.0.0"
        buildConfigField(
            "String",
            "API_URL",
            "\"https://www.scorebat.com/\""
        )
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
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
    lintOptions {
        isWarningsAsErrors = true
    }
    viewBinding {
        isEnabled = true
    }
    testOptions {
        animationsDisabled = true
        unitTests.isIncludeAndroidResources = true
    }
}

dependencies {
    implementation(project(":matches"))
    implementation(project(":common"))
    implementation(project(":ui"))
    implementation(Deps.Androidx.activity)
    implementation(Deps.Androidx.appcompat)
    implementation(Deps.Androidx.core)
    implementation(Deps.Androidx.fragment)
    implementation(Deps.Androidx.navFrag)
    implementation(Deps.Androidx.material)
    implementation(Deps.Androidx.constraintlayout)
    implementation(Deps.Koin.core)
    implementation(Deps.Koin.viewModel)
    implementation(Deps.Koin.fragment)
    implementation(Deps.Kotlin.coroutines)
    implementation(Deps.Kotlin.coroutinesAndroid)

    testImplementation(Deps.Koin.test)
    testImplementation(Deps.Test.junit)
    testImplementation(Deps.Androidx.testRules)
    testImplementation(Deps.Androidx.testRunner)
    testImplementation(Deps.Androidx.coreTesting)
}
