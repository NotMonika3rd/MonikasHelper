plugins {
    id("java")
    id("xyz.wagyourtail.unimined") version "1.4.36-kappa"
}

group = "rip.sayori"
version = "1.1"

unimined.minecraft {
    version = "1.12.2"

    mappings.mcp("stable", "39-1.12")

    cleanroom {
        loader("0.6.12-alpha")
        runs.all{
            dependsOn("build")
            systemProperties(
                "rip.sayori.helper.path" to tasks.jar.get().archiveFile.get().asFile.path,
                "crl.dev.mixin" to "monika.mixin.json"
            )
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
        "Can-Retransform-Classes" to true,
        "ModType" to "CRL",
        "MixinConfigs" to "monika.mixin.json"
    )
}

