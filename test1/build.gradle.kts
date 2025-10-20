import com.coding.app.setNamespace

plugins {
    alias(libs.plugins.coding.android.library)
}

android {
    setNamespace("test1")
}
