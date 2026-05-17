plugins {
    id("java")
    id("xyz.wagyourtail.unimined") version "1.4.18-kappa"
}

group = "rip.sayori"
version = "1.0-SNAPSHOT"

unimined.minecraft {
    version = "1.12.2"

    mappings.mcp("stable", "39-1.12")

    cleanroom {
        loader("0.5.12-alpha")
        runs.all{
            dependsOn("build")
            systemProperties("rip.sayori.helper.path" to tasks.jar.get().archiveFile.get().asFile.path)
        }
    }
}

repositories {
    mavenCentral()
}

tasks.jar.get().manifest {
    attributes(
        "Agent-Class" to "rip.sayori.helper.agent.MonikaAgent",
        "Can-Redefine-Classes" to true,
        "Can-Retransform-Classes" to true
    )
}
