plugins {
    kotlin("jvm")
    kotlin("kapt")
}

dependencies {
    implementation(Deps.Square.okhttp)
    implementation(Deps.Square.retrofit)
    implementation(Deps.Square.retrofitConverterMoshi)
    implementation(Deps.Square.moshi)
    implementation(Deps.Square.moshiAdapters)
    implementation(Deps.Koin.core)
    kapt(Deps.Square.moshiKotlinCodegen)
}
