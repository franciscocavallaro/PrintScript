plugins{
    `maven-publish`
}


publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/maxigeist/PrintScript")
            credentials {
                username = project.findProperty("gpr.user") as String? ?: System.getenv("GITHUB_AUTHOR")
                password = project.findProperty("gpr.key") as String? ?: System.getenv("GITHUB_TOKEN")
            }
        }
    }
    publications {
        create<MavenPublication>("maven") {
            version = "1.1.3-SNAPSHOT"
            from(components["java"])
        }
    }
}