package com.coding.app

import org.gradle.api.Project

fun Project.setNamespace(name: String) {
    androidExtension.apply {
        namespace = "com.coding.app.$name"
    }
}
