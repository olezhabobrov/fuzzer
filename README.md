# Kotlin Fuzzer
## How to launch

`gradlew core:runCoordinator` -- launches central verticle `com.stepanov.bbf.bugfinder.coordinator.Coordinator` 
managing all the fuzzing process.

Then you should launch the compiler verticle, you want to fuzz: 
either `gradlew JVMCompiler:run` or `gradlew NativeCompiler:run`

Now the server is started and you can start fuzzing by sending request with fuzzing settings. Examples of that you can see at `mutationRequests` 

You can also send request to `http://localhost:8888/filter-results` address to filter (deduplicate) results

### JVMCompiler

Make sure to download all the necessary kotlin libs. For that launch download task from Gradle. Or download them manually and put to `JVMCompiler/tmp/lib`

### NativeCompiler

To launch NativeCompiler verticle you should specify at `NativeCompiler/build.gradle` a path to local JAR with Kotlin/Native API (you can download it from Kotlin Github)

### klib-fuzzing branch

Fuzzer targeted to fuzz evolving klibs. For that more specific transformations
implemented, `Invocator` added and very specific compilation process 
configured

## To future developers

`Coordinator` is the central verticle coordinating others. `BugManager` is the verticle responsible
for storing bugs, `Mutator` -- for applying transformations.

Such a weird project structure is required, because we wanted to have both JVMCompiler and NativeCompiler, and we couldn't 
do it in one project because there would be a dependency conflict.

When adding new transformation, don't forget to add it to `ListOfAllTransformations.kt`.

Don't forget to dispose `com.stepanov.bbf.bugfinder.project.Project` after you are done with it to avoid memory leak.



If you have any questions, feel free to reach me at [Telegram](https://t.me/olezhkabobrov) or at `olezhkabobrov@gmail.com`